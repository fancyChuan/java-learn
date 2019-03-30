import org.junit.Test;

import java.io.IOException;

public class JavaResourceTest {

    @Test
    public void test() throws IOException {
        JavaResource resourceLoader = new JavaResource();
        resourceLoader.loadProperties1();
    }
}
