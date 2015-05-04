package tools;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by Roman on 04.05.2015.
 */
public class Props {
    final private static Properties properties;
    static {
        properties = new Properties();
        try {
            properties.load(Props.class.getResourceAsStream("/my.properties"));
        } catch (IOException e) { }
    }
    public static String getPropertyOrFail(String key) {
        if (properties.containsKey(key)) {
            return properties.getProperty(key);
        } else {
            throw new RuntimeException(String.format("Property \"%s\" not found in properties file!", key));
        }
    }
    public static Optional<String> getProperty(String key) {
        return Optional.ofNullable(properties.getProperty(key));
    }
}
