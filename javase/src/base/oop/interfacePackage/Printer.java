package base.oop.interfacePackage;


interface Product {
    int getProductTime();
}

public class Printer implements Output, Product {
    private String[] printData = new String[MAX_LINE];
    private int dataNum = 0;

    @Override
    public void out() {
        while (dataNum > 0) {
            System.out.println("打印机打印： " + printData[0]);
            // 把作业队列整体前移一位，并把剩下的作业数减1
            System.arraycopy(printData, 1, printData, 0, --dataNum);
        }
    }

    @Override
    public void getData(String msg) {
        if(dataNum >= MAX_LINE) {
            System.out.println("输出队列已经满了，添加失败");
        }
        else {
            // 把打印数据添加到队列中，已保存数据的数量+1
            printData[dataNum++] = msg;
        }
    }

    @Override
    public int getProductTime() {
        return 45;
    }
}
