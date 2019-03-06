package base.oop.abstractPackage;

/**
 * 速度表抽象类
 *
 * 对于速度表来说，车的半径是不一样的，这里就用了抽象方法，把获取半径大小延迟到子类中实现
 * 这里其实采用的就是模板方法的设计模式
 */
public abstract class SpeedMeter {

    private double turnRate; // 使用private权限只允许本类使用，但是提供public方法供子类设置值

    public SpeedMeter() {
    }

    public abstract double getRadius();

    public void setTurnRate(double turnRate) {
        this.turnRate = turnRate;
    }

    // 计算速度的通用方法
    public double getSpeed() {
        return Math.PI * 2 * getRadius() * turnRate;
    }
}
