package concurrency.base.threads;

/**
 * 守护线程&用户线程
 */
public class DaemonAndUserThreadApp {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (; ; ) {
            }
        });
        thread.setDaemon(true); // 这行代码注释掉之后，虽然主线程运行完了，但是子线程还没有执行结束，这个时候JVM并没有退出
        // 但是，如果加上上面这行代码，这个时候线程变成了守护线程，主线程执行完成之后发现没有其他用户线程了，这个时候JVM进程就会结束退出
        // 说明子线程的生命周期是不受父线程的影响的
        thread.start();
        System.out.println("main done!");
    }
}
