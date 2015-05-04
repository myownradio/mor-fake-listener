import service.ChannelsList;
import service.entities.ChannelEntry;
import service.entities.JSONResponse;
import service.listener.Client;
import service.listener.ClientSession;
import tools.Props;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Roman on 04.05.2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Props.getPropertyAsIntegerOrFail("listeners.max"));
//        BlockingQueue<Runnable> q = new ArrayBlockingQueue<>(20);
//        ThreadPoolExecutor ex = new ThreadPoolExecutor(4, 10, 20, TimeUnit.SECONDS, q);
        while (true) {
            JSONResponse response = ChannelsList.getRandomChannel();
            ChannelEntry entry = response.getData();
            Thread.sleep(1000);
            long duration = (long) (Math.random() * 7_200_000L);
            int channelId = entry.getSid();
            Client client = new Client() {{
                this.setChannelId(channelId);
                this.setListeningTime(duration);
            }};
            ClientSession session = new ClientSession(client);
            System.out.println(entry.getName() + " will be listened for " + (duration / 1000) + " seconds");
            executorService.submit(session);
            Thread.sleep((long) (Math.random() * 30_000L));
        }
    }
}
