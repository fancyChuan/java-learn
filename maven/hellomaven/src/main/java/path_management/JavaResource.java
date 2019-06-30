package path_management;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class JavaResource {
    public static void main(String[] args) throws IOException {
        JavaResource resourceLoader = new JavaResource();
        resourceLoader.loadProperties1();

    }

    public void loadProperties1() throws IOException {
        InputStream input = null;
        input = this.getClass().getResourceAsStream("/config.properties");
        printProperties(input);
    }

    private void printProperties(InputStream input) throws IOException {
        Properties properties = new Properties();
        properties.load(input);
        System.out.println(properties.getProperty("name"));
    }

}
