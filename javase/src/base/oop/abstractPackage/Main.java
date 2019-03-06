package base.oop.abstractPackage;

public class Main {

    public static void main(String[] args) {
        testCarSpeedMeter();
    }

    public static void testCarSpeedMeter() {
        CarSpeedMeter csm = new CarSpeedMeter();
        csm.setTurnRate(15);
        System.out.println(csm.getSpeed());
    }
}
