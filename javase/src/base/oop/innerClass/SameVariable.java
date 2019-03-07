package base.oop.innerClass;

public class SameVariable {
    private String prop = "外部类变量";

    public void test() {
        InClass inClass = new InClass();
        inClass.info();
    }

    private class InClass {
        private String prop = "内部成员变量";

        public void info() {
            String prop = "内部类局部变量";
            System.out.println(prop);
            System.out.println(this.prop);
            System.out.println(SameVariable.this.prop);
        }
    }
}
