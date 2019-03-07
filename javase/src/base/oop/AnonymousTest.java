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

public class AnonymousTest {
    public void test(Product product) {
        System.out.println("购买了一个： " + product.getName() + "\t价格是： " + product.getPrice());
    }


    public static void main(String[] args) {
        AnonymousTest anonymous = new AnonymousTest();
        anonymous.test(new Product() { // 注意这个位置
            @Override
            public double getPrice() {
                return 66.66;
            }

            @Override
            public String getName() {
                return "哇~";
            }
        });
    }
}
