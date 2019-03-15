package generics;

import java.util.List;

public class Canvas {
    /**
     * 带类型通配符，在使用的时候需要强制转换，不够优雅，而且繁琐，甚至传了其他类型的进来，在运行时强制转换可能报错
     */
    public void drawAll1(List<?> shapes) {
        for (Object object: shapes) {
            Shape s = (Shape) object;
            s.draw(this);
        }
    }

    /**
     * 带限制的类型通配符，表示所有Shape泛型List的父类
     * 也就是说：
     *      List<? extends Shape> 可以表示 List<Circle> 也可以表示 List<Rectangle>
     *
     * 换句话说，？还是表示未知的类型，只不过这个未知的类型一定是Shape的子类
     */
    public void drawAll2(List<? extends Shape> shapes) {
        for (Shape s: shapes) {
            s.draw(this);
        }
    }

    /**
     * 类似的，由于程序不确定shapes的类型，下面添加一个Rectangel对象的方法是错误的
     */
    public void addShape(List<? extends Shape> shapes) {
        // shapes.add(0, new Rectangle());
    }
}
