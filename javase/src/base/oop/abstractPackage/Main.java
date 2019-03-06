package base.oop.abstractPackage;

public class Main {

    public static void main(String[] args) {
        // testCarSpeedMeter();
        testTriangele();
    }

    public static void testCarSpeedMeter() {
        CarSpeedMeter csm = new CarSpeedMeter();
        csm.setTurnRate(15);
        System.out.println(csm.getSpeed());
    }


    public static void testTriangele() {
        Shape s = new Triangle("blue", 3 ,4 ,5);
        System.out.println(s.getType() + ": " + s.calPerimeter());
    }
}
