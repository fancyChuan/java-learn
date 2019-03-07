package base.oop;

/**
 * 匿名内部类的使用
 * 语法：
 *      new 接口 | 父类构造器(实参列表) {内部类实现部分}
 */

interface Product {
    public double getPrice();
    public String getName();
}
abstract class Device {
    private String name;
    public abstract double getPrice();
    public Device() {}

    public Device(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class AnonymousTest {
    public static void main(String[] args) {
        AnonymousTest anonymous = new AnonymousTest();
        // 实现接口的匿名内部类
        anonymous.testInterface(new Product() { // 注意这个位置
            @Override
            public double getPrice() {
                return 66.66;
            }

            @Override
            public String getName() {
                return "哇~";
            }
        });
        // 调用继承父类的匿名内部类
        anonymous.testAbstractClass(new Device("电子示波器") {
            @Override
            public double getPrice() {
                return 66.77;
            }
        });

        Device d = new Device() {
            { // 匿名内部类也可以有初始化块
                System.out.println("这是无参的继承父类的匿名内部类");
            }
            @Override
            public double getPrice() {
                return 0.11;
            }
            @Override
            public String getName() { // 重写了父类的实例方法
                return "键盘";
            }
        };
        System.out.println("=====");
        anonymous.testAbstractClass(d);
    }


    public void testInterface(Product product) {
        System.out.println("购买了一个： " + product.getName() + "\t价格是： " + product.getPrice());
    }

    public void testAbstractClass (Device device) {
        System.out.println(device.getName() + "\t 价格为： "  + device.getPrice());
    }
}


