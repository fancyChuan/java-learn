package reflect.utils;

public class BeanUtils {
    public static void setValue(Object obj, String value) {
        String results [] = value.split("\\|");
        for (String item : results) {
            String[] attval = item.split(":");

        }
    }
}
