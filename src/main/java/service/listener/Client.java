package service.listener;

/**
 * Created by Roman on 04.05.2015.
 */
public class Client {

    private int ChannelId;
    private long listeningTime;

    public int getChannelId() {
        return ChannelId;
    }

    public void setChannelId(int channelId) {
        ChannelId = channelId;
    }

    public long getListeningTime() {
        return listeningTime;
    }

    public void setListeningTime(long listeningTime) {
        this.listeningTime = listeningTime;
    }

}
