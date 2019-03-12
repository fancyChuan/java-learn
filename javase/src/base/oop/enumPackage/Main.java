package base.oop.enumPackage;

public class Main {
    public static void main(String[] args) {
        // testFirstEnum();
        // testGender();
        testAbstractEnum();
    }

    /**
     * 枚举类入门示例
     */
    public static void testFirstEnum() {

        // 遍历所有枚举值
        for (SeasonEnum s: SeasonEnum.values()) {
            System.out.println(s);
            jedge(s);
            System.out.println("------");
        }
    }

    /**
     * 常用方法
     */
    public static void testGender() {
        // 通过Enum的valueOf()方法获取指定枚举类的枚举值。TODO：好像是反射？
        Gender g = Enum.valueOf(Gender.class, "FEMALE");
        g.name = "女";
        System.out.println(g + " 代表： " + g.name);
        System.out.println("所在位置：" + g.ordinal()); // 枚举值所在位置的索引
        System.out.println("和MALE比较： " + g.compareTo(Gender.MALE)); // 两个索引值相减
        System.out.println("OTHER compare MALE: " + Gender.OTHER.compareTo(Gender.MALE)); // 2-0=2
    }

    public static void testAbstractEnum() {
        System.out.println(Operation.PLUS.eval(3, 4));
        System.out.println(Operation.MINUS.eval(3, 4));
        System.out.println(Operation.TIMES.eval(3, 4));
        System.out.println(Operation.DIVEDE.eval(3, 4));
    }


    /**
     * 判断枚举值
     */
    private static void jedge(SeasonEnum s) {
        switch (s) {
            case SPRING:
                System.out.println("春暖花开");
                break;
            case SUMMER:
                System.out.println("夏夏夏");
                break;
            case FALL:
                System.out.println("秋秋秋");
                break;
            case WINTER:
                System.out.println("冬啊");
                break;
        }
    }
}
