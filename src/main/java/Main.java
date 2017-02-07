import service.ChannelsList;
import service.JobController;
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
                new LinkedBlockingQueue<>(8));

        executorService.setRejectedExecutionHandler((r, executor) -> {
            try {
                System.out.println("Waiting for free slots...");
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down...");
            executorService.shutdownNow();
            try {
                executorService.awaitTermination(30L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));

        JobController controller = new JobController(executorService);
        controller.init();

        while (!Thread.currentThread().isInterrupted()) {
            Thread thread = new Thread(() -> {
                JSONResponse response = ChannelsList.getRandomChannel();
                ChannelEntry entry = response.getData();
                Client client = new Client() {{
                    this.setChannelId(entry.getSid());
                    this.setListeningTime((long) (Math.random() * 7_200_000L));
                }};
                ClientSession session = new ClientSession(client);
                System.out.println(entry.getName() + " will be listened for " + (client.getListeningTime() / 1000) + " seconds");
                executorService.submit(session);
                ThreadTool.sleep((long) (Math.random() * 3_000_000L));
            });
            thread.start();
            thread.join();
        }
    }
}
