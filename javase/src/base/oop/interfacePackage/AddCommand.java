package base.oop.interfacePackage;

public class AddCommand implements Command {
    @Override
    public void process(int[] target) {
        int sum = 0;
        for (int tmp: target) {
            sum += tmp;
        }
        System.out.println("求和命令，结果为： " + sum);
    }
}
