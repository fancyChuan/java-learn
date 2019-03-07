package base.oop.innerClass;

public class Cow {
    private double weight;

    public Cow() {}
    public Cow(double weight) {
        this.weight = weight;
    }

    /**
     * 将内部类的使用封装成public的函数供其他类使用，但是就把内部类的所有细节都隐藏起来了
     */
    public void test() {
        CowLeg cowLeg = new CowLeg(1.12, "花白的");
        cowLeg.info();
    }

    /**
     * 定义一个非静态内部类，权限可以private，表示属于外部类Cow的私有成员，只能在Cow内部使用
     * 对于外部类Cow来说，就不能把类权限设置为private，因为没有人能访问得到
     */
    private class CowLeg {
        private double length;
        private String color;

        public CowLeg() {}
        public CowLeg(double length, String color) {
            this.length = length;
            this.color = color;
        }
        // 其实这个设置为诶public没有多少意义，因为类是private，意味着除了Cow类，其他调用不到这个方法
        public void info() {
            System.out.println("当前牛腿的颜色： " + color);
            System.out.println("长度： " + length);
            System.out.println("体重： " + weight); // 访问外部类的成员
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }
}
