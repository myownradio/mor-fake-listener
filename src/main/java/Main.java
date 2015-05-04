import service.ChannelsList;
import service.entities.ChannelEntry;
import service.entities.JSONResponse;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Roman on 04.05.2015.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(24);
        while (true) {
            JSONResponse response = ChannelsList.getList();
            ChannelEntry entry = response.getData()
                    .getChannels()
                    .getItems()
                    .get((int) (Math.random() * response.getData().getChannels().getItems().size()));
            System.out.println(entry.getName());
            Thread.sleep(1000);
        }
    }
}
