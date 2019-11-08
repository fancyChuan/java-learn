package concurrency.base.threads;

/**
 * yield方法的使用
 *
 * 注意这个示例会出现多种结果，因为线程调度器的调度是不可预测的
 */
public class ThreadYieldApp implements Runnable {
    public ThreadYieldApp() {
        // 创建线程并启动
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if ((i % 5) == 0) {
                System.out.println(Thread.currentThread() + " yield cpu ...");
                Thread.yield(); // 当前线程放弃时间片，进行下一轮调度
            }
        }
        System.out.println(Thread.currentThread() + " is done ");
    }

    public static void main(String[] args) {
        new ThreadYieldApp();
        new ThreadYieldApp();
        new ThreadYieldApp();
        new ThreadYieldApp();
    }
}
/*
Thread[Thread-1,5,main] yield cpu ...
Thread[Thread-3,5,main] yield cpu ...
Thread[Thread-0,5,main] yield cpu ...
Thread[Thread-1,5,main] is done
Thread[Thread-2,5,main] yield cpu ...
Thread[Thread-3,5,main] is done
Thread[Thread-0,5,main] is done
Thread[Thread-2,5,main] is done

比如这种结果，说明前三个线程让出CPU成功了，直接调度了另外的线程
 */