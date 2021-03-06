package service.entities;

/**
 * Created by Roman on 04.05.2015.
 */
public class JSONResponse {

    private int code;
    private String message;
    private ChannelEntry data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ChannelEntry getData() {
        return data;
    }

    public void setData(ChannelEntry data) {
        this.data = data;
    }

}
