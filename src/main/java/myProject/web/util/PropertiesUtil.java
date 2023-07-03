package myProject.web.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesUtil {
    private static final Properties properties = new Properties(); //

    static {
        loadProperties();
    }

    private static void loadProperties(){
        try{
            InputStream stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
            properties.load(stream);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public static String getByKey(String key){
        return properties.getProperty(key);
    }
}
