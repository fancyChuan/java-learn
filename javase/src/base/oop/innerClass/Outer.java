package base.oop.innerClass;

public class Outer {
    private int outProp = 9;

    /**
     * 外部类访问非静态内部类成员变量时，必须显式实例化内部类，再通过实例化对象去调用
     */
    public void accessInnerProp() {
        System.out.println("内部类的值： " + new Inner().inProp);
        // System.out.println(inProp); 是会报编译错误的
    }

    class Inner {
        private int inProp = 5;
        public void accessOuterProp() {
            System.out.println("外部类成员变量： " + outProp);
        }
    }
}
