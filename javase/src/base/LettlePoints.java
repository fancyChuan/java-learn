package base;

/**
 * 零散的小知识点
 */
public class LettlePoints {

    public static void main(String[] args) {
        testToCharArray();
    }

    /**
     * String.toCharArray() 把字符串转为数组，每个元素分别为一个字符
     * "abc" --> ["a", "b", "c"]
     * "中文" --> ["中", "文"]
     */
    public static void testToCharArray() {
        String text = new String("中文的啊");
        char[] chars = text.toCharArray();
        for (char c: chars) {
            System.out.println(c);
        }
    }
}
