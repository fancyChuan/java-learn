package concurrency.base.threads;

/**
 * java指令重排序下的并发编程问题
*/
public class CommandReSortApp {
    private static int num = 0;
    private static boolean ready = false;

    public static void main(String[] args) throws InterruptedException {
        ReadThread read = new ReadThread();
        read.start();
        Writethread write = new Writethread();
        write.start();
        Thread.sleep(10);
        read.interrupt();
        System.out.println("[x]main exit");
    }
    // 读线程
    public static class ReadThread extends Thread {
        @Override
        public void run() {
            while (! Thread.currentThread().isInterrupted()) {
                if (ready) {                            // (1)
                    System.out.println(num + num);      // (2)
                }
            }
            System.out.println("Read Thread...");
        }
    }
    // 写线程
    public static class Writethread extends Thread {
        @Override
        public void run() {
            num = 2;                                    // (3)
            ready = true;                               // (4)
            System.out.println("Write Thread set over...");
        }
    }
}
