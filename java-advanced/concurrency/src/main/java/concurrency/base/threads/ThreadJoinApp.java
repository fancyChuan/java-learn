package concurrency.base.threads;

/**
 * join方法的使用
 */
public class ThreadJoinApp {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadOne 执行结束");
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadTwo 执行结束");
            }
        });
        threadOne.start();
        threadTwo.start();
        System.out.println("等待所有线程执行完成");
        threadOne.join(); // 开始阻塞
        threadTwo.join(); // 再次阻塞
        System.out.println("done");

        System.out.println("======= 如果在线程A调用线程B的join方法，等待过程中其他线程调用线程A的interrupt方法，则报错 =======");
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("threadC开始运行...");
                for (; ; ) {
                }
            }
        });
        final Thread mainThread = Thread.currentThread(); // 获的主线程
        Thread threadD = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThread.interrupt(); // 中断主线程
            System.out.println("threadD done!");
            // threadC.interrupt(); // 这里调用threadC是不能退出线程C的，因为线程C里面没有能够抛出InterruptedException的方法，也就是说不支持Interrupt除非用stop方法

        });
        threadC.start();
        threadD.start();
        try {
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadD.join();
        System.out.println("xxxx"); // TODO：为什么这个地方执行后程序并没有真正的结束？难道线程C的死循环还没有关掉？
    }
}
