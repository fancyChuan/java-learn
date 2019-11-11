package learning;

import java.util.ArrayList;

/**
 * VM options: -Xms10m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOMApp {

    static class OOMObject {}

    public static void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
