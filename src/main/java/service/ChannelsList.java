package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import service.entities.JSONResponse;
import tools.FakeSSL;
import tools.Props;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;

/**
 * Created by Roman on 04.05.2015.
 */
public class ChannelsList {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    }

    public static JSONResponse getList() {
        try {
            HttpsURLConnection connection = FakeSSL.openConnection(Props.getPropertyOrFail("rest.url").concat("v2/channels/popular"));
            JSONResponse jsonResponse = objectMapper.readValue(connection.getInputStream(), JSONResponse.class);
            connection.disconnect();
            return jsonResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
