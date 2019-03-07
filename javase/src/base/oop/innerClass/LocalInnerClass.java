package base.oop.innerClass;

/**
 * 局部内部类的使用
 */
public class LocalInnerClass {
    public static void main(String[] args) {

        // 定义一个局部内部类，权限访问控制符在局部内部类没有使用的意义, statci也是
        class InnerBase {
            int a;
        }
        // 定义一个局部内部类的子类
        class InnerSub extends InnerBase {
            int b;
        }

        InnerSub innerSub = new InnerSub();
        innerSub.a = 5;
        innerSub.b = 8;
        System.out.println(innerSub.a + innerSub.b);
    }
}
