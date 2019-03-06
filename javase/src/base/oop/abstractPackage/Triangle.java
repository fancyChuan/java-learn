package base.oop.abstractPackage;

public class Triangle extends Shape {

    private double a;
    private double b;
    private double c;

    public Triangle(String color, double a, double b, double c) {
        super(color); // 因为抽象类有构造器，这里必须要调用抽象父类的构造器
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setSides(double a, double b, double c) {
        if (a >= b+c || b >= a+c || c >= a+b) {
            System.out.println("两边之和必须大于第三边");
            return ; // 这里return 空是为了不执行后面的代码
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double calPerimeter() {
        return a + b + c;
    }

    @Override
    public String getType() {
        return "三角形";
    }
}
