package base.oop.interfacePackage;

public class Main {

    public static void main(String[] args) {
        // testPrinter();
        testCommand();
    }

    public static void testPrinter() {
        // Printer对象，当作Output使用
        Output output = new Printer();
        output.getData("fancy");
        output.getData("Chuan");
        output.out();
        output.getData("ff");
        output.getData("cc");
        output.out();
        output.print("猪八戒", "沙和尚", "孙悟空");
        output.test();

        // Printer对象，当作Product使用
        Product product = new Printer();
        System.out.println(product.getProductTime());

        // 所有接口类型的引用变量都可以直接赋值给Object类型的变量（利用了向上转型）
        Object object = product;
    }

    public static void testCommand() {
        // 待处理的数组
        int[] target = {2, 4 ,6 ,8};
        // 新建2个命令对象
        Command addCommand = new AddCommand();
        Command printCommand = new PrintCommand();

        ProcessArray process = new ProcessArray();
        process.process(target, addCommand); // 把命令对象传到执行者中进行调用
        process.process(target, printCommand);
    }
}
