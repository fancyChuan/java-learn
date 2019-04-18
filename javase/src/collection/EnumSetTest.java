package collection;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;

enum Season {
    SPRING, SUMMER, FALL, WINTER
}
enum Season2 {
    SPRING, SUMMER, FALL, WINTER, MYSELF
}
public class EnumSetTest {

    /**
     * 1. EnumSet的常规用法
     */
    public static void testCreateEnumSet() {
        EnumSet es1 = EnumSet.allOf(Season.class);
        System.out.println(es1);

        EnumSet es2 = EnumSet.noneOf(Season.class);
        es2.add(Season.WINTER);
        es2.add(Season.SPRING); // 添加后也会排序
        System.out.println(es2);

        EnumSet<Season> es3 = EnumSet.of(Season.SUMMER, Season.SPRING);
        System.out.println(es3);

        EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
        System.out.println(es4);

        EnumSet es5 = EnumSet.complementOf(es4);
        System.out.println(es5);
    }

    /**
     * 2. 复制EnumSet集合
     */
    public static void testCopy() {
        Collection c = new HashSet();
        c.clear();
        c.add(Season.SPRING);
        c.add(Season.SUMMER);

        EnumSet enumSet = EnumSet.copyOf(c);
        System.out.println(enumSet);

        // c.add("xx");
        // enumSet = EnumSet.copyOf(c); // 这行代码报错，因为c中含有非枚举值
        c.add(Season2.MYSELF);
        System.out.println(EnumSet.copyOf(c)); // 含有不同枚举类的枚举值也不行，因为元素需要比较，会报ClassCastException的错误
    }

    public static void main(String[] args) {
        // testCreateEnumSet();
        testCopy();
    }
}
