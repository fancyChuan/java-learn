package confpkg;

import confpkg.ReadConf;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadConfTest {

    @Test
    public void t1() {
        String path = ReadConf.class.getClassLoader().getResource("").getPath();
        System.out.println(path);

        String fileStr = path + "/conf/sample.conf";

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileStr)));
            while (br.readLine() != null) {
                System.out.println(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
