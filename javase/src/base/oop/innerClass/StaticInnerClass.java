package base.oop.innerClass;

public class StaticInnerClass {
    private int prop1 = 5;
    private static int prop2 = 9;

    public void test() {
        StaticInner a = new StaticInner();
        a.accessOuterProp();
    }

    static class StaticInner {
        private static int age = 1;
        int age2 = 2;

        /**
         * 实例方法依然不能访问非实例变量，因为该实例方法其实是在整个静态内部类内的
         */
        public void accessOuterProp() {
            // 可以随意访问内部类的成员
            System.out.println(age);
            System.out.println(age2);
            // 可以访问外部类的静态变量
            System.out.println(prop2);
            // System.out.println(prop1); 这行代码编译报错，不能直接访问非静态成员
            System.out.println(new StaticInnerClass().prop1); // 只能通过实例化外部类来获取
        }
    }


    public static void main(String[] args) {
        StaticInnerClass a = new StaticInnerClass();
        a.test();
        System.out.println(a.prop2);
    }
}
