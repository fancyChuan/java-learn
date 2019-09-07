package concurrency.base.threads;

/**
 * 线程中断退出
 */
public class ThreadInterruptApp {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) { // 如果没有被中断则循环打印
                System.out.println(Thread.currentThread() + " hello~");
            }
        });

        thread.start();
        Thread.sleep(10); // 主线程休眠，让子线程能够打印出一些内容
        System.out.println("主线程开始中断子线程");
        thread.interrupt();
        thread.join();
        System.out.println("main is over");

        System.out.println("========== interrupted和isInterrupted的区别 ==========");

    }
}
