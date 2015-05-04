package service.listener;

import tools.FakeSSL;
import tools.Props;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Roman on 04.05.2015.
 */
public class ClientSession implements Runnable {

    private Client client;

    public ClientSession(Client client) {
        this.client = client;
    }

    public void run() {
        String channel = String.format(Props.getPropertyOrFail("channel.url"), client.getChannelId());
        long start = System.currentTimeMillis();
        HttpsURLConnection connection = FakeSSL.openConnection(channel);
        try (InputStream is = connection.getInputStream()) {
            byte[] buffer = new byte[1024];
            long finish = start + client.getListeningTime();
            while (is.read(buffer) != -1 && (System.currentTimeMillis() < finish));
        } catch (IOException e) {
            /* NOP */
        }
        connection.disconnect();
    }
}
