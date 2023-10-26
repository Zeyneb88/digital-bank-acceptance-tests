package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
   static {

        String filePath ="src/test/resources/properties/digitalbank.properties";
        FileInputStream input = null;
        try {
            input = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    //    System.out.println(properties.get("my_name"));
      //  System.out.println(properties.get("browser"));
        //System.out.println(properties.get("environment"));

    }
    public static String getPropertiesValue(String key){
       return properties.getProperty(key);
    }
}
