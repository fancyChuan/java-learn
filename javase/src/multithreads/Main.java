package multithreads;

public class Main {
    public static void main(String[] args) {
        firstThread();
    }

    /**
     * 1. 线程初体验
     * 注意：一定要调用start() 方法启动线程，如果使用 run() 方法，那么还是单线程运行的
     *      同一个线程只能被启动一次，多次启动(调用start())会报错
     *  start()方法中最后有一行语句：private native void start0();
     *  其中start0是JVM根据系统提供的底层函数实现的线程启动方法，跟系统有关，但是经过JVM实现后具有跨系统性
     */
    public static void firstThread() {
        new FirstThread("x", "线程A").start();
        new FirstThread("y", "线程B").start();
        new FirstThread("z", "线程C").start();
    }
}
