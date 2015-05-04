package service.entities;

import java.util.List;

/**
 * Created by Roman on 04.05.2015.
 */
public class ChannelsResponse {
    private int count;
    private List<ChannelEntry> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ChannelEntry> getItems() {
        return items;
    }

    public void setItems(List<ChannelEntry> items) {
        this.items = items;
    }

}
