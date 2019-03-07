package base.oop.interfacePackage;

public class PrintCommand implements Command {
    @Override
    public void process(int[] target) {
        for (int tmp: target) {
            System.out.println("print命令，结果： " + tmp);
        }
    }
}
