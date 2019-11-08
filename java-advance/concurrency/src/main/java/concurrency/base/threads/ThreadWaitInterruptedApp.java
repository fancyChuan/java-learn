package concurrency.base.threads;

/**
 * 演示wait方法调用后被interrupt会报异常
 */
public class ThreadWaitInterruptedApp {
    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("-- begin --");
                    synchronized (obj) {
                        obj.wait(); // 阻塞线程
                    }
                    System.out.println("-- end --");
                } catch (InterruptedException e) {
                    System.out.println("报错了！信息为：");
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        Thread.sleep(1000);
        System.out.println("-- begin interrupt threadA --");
        threadA.interrupt();
        System.out.println("-- end interrupt threadA --");
    }
}
