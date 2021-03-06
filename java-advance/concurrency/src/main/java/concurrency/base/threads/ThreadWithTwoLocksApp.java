package concurrency.base.threads;

/**
 * 线程上有多个锁，共享变量上调用wait()只会是否该变量上的额锁，其他锁不会释放
 *
 * 这个应用不能正常结束。因为resourceB在线程A中没有释放，而线程B又在等待这个锁
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
                        synchronized (resourceB) {
                            System.out.println("threadA get resourceB lock");
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait(); // 释放了resourceA上的锁，同时阻塞线程，也就是下面的代码是暂时不会执行的
                            resourceB.wait(); // 这里的代码要执行，要么线程B通过resourceA.notify()唤醒线程，要么上一行代码resourceA.wait(t)加个超时参数
                        }
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
                    Thread.sleep(1000); // 这里休眠是为了让threadA能够顺利拿到两个资源的锁
                    synchronized (resourceA) {
                        System.out.println("threadB get resourceA lock");
                        System.out.println("threadB 尝试获取resourceB lock ...");
                        synchronized (resourceB) { // 下面的代码是不会执行的，因为线程A并没有释放resourceB的锁，这里就会一直堵塞
                            System.out.println("threadB get resourceB lock");
                            System.out.println("threadB释放resourceA lock");
                            resourceA.wait(); // 这里就算执行了无法唤醒线程A，唤醒线程A继续往下执行需要调用notifyAll/notify方法
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
