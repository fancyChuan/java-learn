package base.oop;

import base.oop.interfacePackage.Command;
import base.oop.interfacePackage.ProcessArray;

public class LambdaTest {

    public static void main(String[] args) {
        testLambdaAnonymous();
    }

    public static void testLambdaAnonymous() {
        ProcessArray p = new ProcessArray();
        int[] array = {1, 3, 5, 8};
        p.process(array, new Command() {
            @Override
            public void process(int[] array) {
                int sum = 0;
                for (int tmp: array) {
                    sum += tmp;
                }
                System.out.println("使用匿名内部类实现的命令，结果为：" + sum);
            }
        });

        p.process(array, (int[] target) -> {
            for (int tmp: target) {
                System.out.println("使用Lambda表达式实现的命令，元素有： " + tmp);
            }
        });
    }
}
