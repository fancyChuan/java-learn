package concurrency.base.threads;

/**
 * 线程上有多个锁，共享变量上调用wait()只会是否该变量上的额锁，其他锁不会释放
 */
public class ThreadWithTwoLocksApp {
    // 创建资源
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (resourceA) {
                        System.out.println("threadA get resourceA lock");
                    }
                    synchronized (resourceB) {
                        System.out.println("threadA get resourceB lock");
                        //
                        System.out.println("threadA release resourceA lock");
                        resourceA.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB 尝试获取resourceB lock ...");
                        synchronized (resourceB) {
                            System.out.println("threadB get resourceB lock");
                            System.out.println("threadB释放resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // 启动线程
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        System.out.println("main over");
    }
}
