package service.listener;

import service.entities.FlowListener;
import tools.ThreadTool;

import java.sql.SQLException;

/**
 * Created by Roman on 04.05.2015.
 */
public class ClientSession implements Runnable {

    private Client client;

    public ClientSession(Client client) {
        this.client = client;
    }

    public void run() {

        try (FlowListener listener = new FlowListener("127.0.0.1", "FL/1.0", "mp3_128k",
                client.getChannelId())) {
            ThreadTool.sleep(client.getListeningTime());
        } catch (SQLException e) {
            /* NOP */
        }

    }
}
