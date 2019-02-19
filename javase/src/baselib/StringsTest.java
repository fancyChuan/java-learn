package baselib;

/**
 * String/StringBuffer/StringBuilder实践
 */
public class StringsTest {
    public static void main(String[] args) {
        // testStringString();
        // testComapreTo();
        // testContentEquals();
        testGetChars();
    }

    /**
     * 测试 new String(String origin) 得到String对象的副本，不是同一对象
     */
    public static void testStringString() {
        String aa = new String("fancy");
        String bb = new String(aa);
        System.out.println(aa == bb); // false
        System.out.println(aa.equals(bb)); // true
        System.out.println(System.identityHashCode(aa) == System.identityHashCode(bb)); // false
    }

    /**
     * compareTo() 两字符不相等时的多种结果
     */
    public static void testComapreTo() {
        String aa = new String("fancy");
        String bb = new String("fancyChuan-o");
        String cc = new String("helloworld");
        String dd = new String("fabaoBao");

        System.out.println("aa VS bb -> " + aa.compareTo(bb)); // -7 aa的长度-bb的长度，得到-7
        System.out.println("aa VS cc -> " + aa.compareTo(cc)); // 第一个不相同的字符是 f h 他们所对应的ASCII分别为102 104 所以得到-2
        System.out.println("aa VS dd -> " + aa.compareTo(dd)); // 同上，n->110 b->98 得到12

    }

    public static void testContentEquals() {
        System.out.println("abc".contentEquals(new StringBuffer("abc")));
        System.out.println("abc".contentEquals(new StringBuffer("abcd")));
        System.out.println("abcd".contentEquals(new StringBuffer("abc")));
    }

    public static void testGetChars() {
        char[] s = {'a', 'b', '你', '我', 'c'};
        String s2 = "啊呀哦";
        s2.getChars(1,3, s, 2);
        System.out.println(s); // ab呀哦c
    }
}
