package utils;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileReader {

    private static Properties prop;

    public static Properties loadProperties() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir")
                + "/src/test/resources/config.properties"
            );
            prop.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }
}
