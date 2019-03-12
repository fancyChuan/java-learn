package base.oop.enumPackage;

public class Main {
    public static void main(String[] args) {
        testFirstEnum();
    }

    public static void testFirstEnum() {

        // 遍历所有枚举值
        for (SeasonEnum s: SeasonEnum.values()) {
            System.out.println(s);
            jedge(s);
            System.out.println("------");
        }
    }

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
