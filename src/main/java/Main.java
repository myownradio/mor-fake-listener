import service.ChannelsList;
import service.entities.ChannelEntry;
import service.entities.JSONResponse;
import service.listener.Client;
import service.listener.ClientSession;
import tools.Props;
import tools.ThreadTool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Roman on 04.05.2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        int max = Props.getPropertyAsIntegerOrFail("listeners.max");

        final ThreadPoolExecutor executorService = new ThreadPoolExecutor(max, max, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(200));

        while (!Thread.currentThread().isInterrupted()) {
            Thread thread = new Thread(() -> {
                JSONResponse response = ChannelsList.getRandomChannel();
                ChannelEntry entry = response.getData();
                long duration = (long) (Math.random() * 7_200_000L);
                int channelId = entry.getSid();
                Client client = new Client() {{
                    this.setChannelId(channelId);
                    this.setListeningTime(duration);
                }};
                ClientSession session = new ClientSession(client);
                System.out.println(entry.getName() + " will be listened for " + (duration / 1000) + " seconds");
                executorService.submit(session);
                ThreadTool.sleep((long) (Math.random() * 5_000L));
            });
            thread.start();
            thread.join();
        }
    }
}
