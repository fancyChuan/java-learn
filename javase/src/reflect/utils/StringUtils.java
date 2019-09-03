package reflect.utils;

public class StringUtils {

    /**
     * 首字母大写
     */
    public static String initcap(String string) {
        if (string == null || "".equals(string)) {
            return string;
        }
        if (string.length() == 1) {
            return string.toUpperCase();
        } else {
            return string.substring(0, 1).toUpperCase() + string.substring(1);
        }
    }
}
