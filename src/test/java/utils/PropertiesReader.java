package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesReader
{
    // Load the properties file from the folder
    public static Properties apiProperties = loadProperties(System.getProperty("user.dir") + "/src/test/resources/api.properties");


    private static Properties loadProperties(String path)
    {
        Properties pro = new Properties();
        // stream for reading file
        try {
            FileInputStream stream = new FileInputStream(path);
            pro.load(stream);

            // *** OPTIONAL *** If you want to update the properties file also
            OutputStream output = new FileOutputStream(path);
            pro.store(output, null);
        } catch (Exception e) {
            System.out.println("Error occurred :  " + e.getMessage());
        }

        return pro;
    }
}
