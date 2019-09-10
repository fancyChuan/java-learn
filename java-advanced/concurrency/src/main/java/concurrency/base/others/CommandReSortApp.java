package concurrency.base.others;

/**
 * java指令重排序下的并发编程问题
 *
 * 这个示例会出现多种情况。
 * 1. 正常的输出4
 * 2. 输出0
 * 3. 没有输出。如下所示
[x]main exit
Read Thread...
Write Thread set over...
 *
 * 原因分析：这个代码的变量没有声明为volatile（volatile本身可以避免指令重排序问题），也没有任何同步措施，会有变量内存可见性问题。另外，也存在指令重排序的问题
 *
 * 代码(3)(4)不存在依赖关系，所以可能WriteThread中先执行了(4)再执行的(3).
 * 而执行了(4)以后可能ReadThread已经执行完了(1)，并且在(3)执行之前开始执行(2)这时就会输出0
 *
 * 结论：重排序在多线程下会导致不可预期的运行结果，我们使用volatile来修饰ready变量就可以避免重排序和内存可见性问题
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
        System.out.println("[x]main exit: " + num + "    " + ready);
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
