package base.oop.abstractPackage;

public abstract class Shape {
    {
        System.out.println("执行Shape的初始化块");
    }

    private String color;
    public abstract double calPerimeter();
    public abstract String getType();

    // 这里的构造器不是为了创建对象，而是用于被子类调用
    public Shape(){}
    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
