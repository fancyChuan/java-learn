package base.oop.innerClass;

class Out {
    class In {
        public In(String msg) {
            System.out.println(msg);
        }
    }
}

public class CreateInnerInstance {
    public static void main(String[] args) {
        Out.In in = new Out().new In("啊啊啊");
        // 上面的语句等价于下面3个语句
        Out.In in2;
        Out out = new Out();
        in2 = out.new In("第2个测试"); // 非静态内部类的构造器必须通过外部类的实例对象来调用
    }
}

/**
 * 非静态内部类被SubClass子类继承，那么构造器里面的super其实是指代了In这个内部类
 * 但是对于非静态内部类的实例化必须通过外部类的实例对象进行调用，
 * 所以，SubClass子类的构造器必须显式的传入外部类Out的对象out，通过out.super实现实例化
 *
 * super用于指代父类，这个例子中，父类就是In
 */
class SubClass extends Out.In {
    public SubClass (Out out) {
        out.super("hello"); // 通过传入的Out对象显式调用In的构造器
    }

    // public SubClass(String msg) {super(msg)} 这是idea默认生成的构造器，但是这个构造器有问题，因为没有外部类对象的存在，内部类无法生存
}