package dataSources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	static InputStream inputstream;
	

	public static String getPropertyValue(String file, String key) {
        try (InputStream inputStream = new FileInputStream(file)) {
            Properties prop = new Properties();
            prop.load(inputStream);
            return prop.getProperty(key); // Returns null if key is not found
        } catch (IOException e) {
            e.printStackTrace(); // You could log this using a logging framework instead
            return null;
        }
    }
    }

