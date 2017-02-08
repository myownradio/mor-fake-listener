import service.ChannelsList;
import service.JobController;
import service.entities.ChannelEntry;
import service.entities.JSONResponse;
import service.listener.Client;
import service.listener.ClientSession;
import tools.ThreadTool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        final ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
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

        while (!Thread.currentThread().isInterrupted()) {
            JSONResponse response = ChannelsList.getRandomChannel();
            ChannelEntry entry = response.getData();
            Client client = new Client() {{
                this.setChannelId(entry.getSid());
                this.setListeningTime((long) (Math.random() * 7_200_000L));
            }};
            ClientSession session = new ClientSession(client);
            System.out.println(entry.getName() + " will be listened for " + (client.getListeningTime() / 1000) + " seconds");
            executorService.submit(session);
            ThreadTool.sleep((long) (Math.random() * 60_000L));
        }
    }
}
