package concurrency.base.threads;

/**
 * 演示死锁
 * TODO：如何查看每个线程在做什么事情
 */
public class DeadLockApp {
    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) throws InterruptedException {
        // deadLock();
        avoidDeadLock();
    }

    private static void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronized (A) {
                    System.out.println("[线程1]拿到资源A");
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[线程1]wait for B ...");
                    synchronized (B) {
                        System.out.println("[线程1]拿到资源B");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                synchronized (B) {
                    System.out.println("[线程2]拿到资源B");
                    System.out.println("[线程2]wait for A ...");
                    synchronized (A) {
                        System.out.println("[线程2]拿到资源A啦");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }

    public static void avoidDeadLock() throws InterruptedException {
        Thread threadX = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    System.out.println("[线程x]拿到资源A");
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[线程x]wait for B ...");
                    synchronized (B) {
                        System.out.println("[线程x]拿到资源B");
                    }
                }
            }
        });

        Thread threadY = new Thread(() -> {
            synchronized (A) {
                System.out.println("[线程y]拿到资源A");
                try {
                    Thread.currentThread().sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("[线程y]wait for B ...");
                synchronized (B) {
                    System.out.println("[线程y]拿到资源B");
                }
            }
        });

        threadX.start();
        threadY.start();
        threadX.join();
        threadY.join();
        System.out.println("main done ");
    }
}
