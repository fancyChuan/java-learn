package concurrency.using;

import java.util.concurrent.Semaphore;

/**
 * Semaphore的使用
 *  1. 基本用法
 *  2. 使用release方法动态添加许可
 *  3. 使用acquireUninterruptibly()来设置线程不可被打断
 *  4. drainPermits() availablePermits
 *  5. 获取当前等待许可的线程队列信息
 */
public class SemaphoreApp {
    public static void main(String[] args) {
        SemaphoreApp app = new SemaphoreApp();
        // app.testBaseUsing();
        // testAddPermits();
        // app.testCanNotInterrupt();
        // testPermitsMethod();
        app.testQueueInfo();
    }

    /**
     * 1. 基本用法
     *  new Semaphore(n) 表示同一时间只有n个线程同时执行acquire方法。 n>1的时候不能保证线程安全性
     *  semaphore.acquire(2) 表示使用2个许可
     */
    public void testBaseUsing() {
        Service service = new Service();
        Thread threadA = new Thread(new MyRunable(service));
        threadA.setName("A");
        Thread threadB = new Thread(new MyRunable(service), "B"); // 这种方式也可以设置线程名
        // threadB.setName("B");
        Thread threadC = new Thread(new MyRunable(service));
        threadC.setName("C");
        threadA.start();
        threadB.start();
        threadC.start();
    }

    /**
     * 2.动态增加permits
     */
    public static void testAddPermits() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        semaphore.acquire();
        semaphore.acquire();
        System.out.println("可用permits: " + semaphore.availablePermits());
        semaphore.release();
        semaphore.release();
        semaphore.release(); // 多释放了一个，所以可用就变成了3
        System.out.println("可用permits: " + semaphore.availablePermits());
        semaphore.release(3); // 再释放了3个，可用就变为了6个
        System.out.println("可用permits: " + semaphore.availablePermits());
    }

    /**
     * 3.测试使用acquireUninterruptibly()来设置线程不可被打断
     * 注意：如果方法里面有Thread.sleep()等其他会抛出InterruptedException异常的代码，那么即使使用了acquireUninterruptibly还是会被打断
     * 比如 Service.testInterrupt()里的for循环改成Thread.sleep(10000) 就能够被打断
     */
    public void testCanNotInterrupt() {
        Service service = new Service();
        Runnable runnable = () -> service.testInterrupt();
        Thread thread1 = new Thread(runnable, "thread1");
        thread1.start();
        thread1.interrupt();
        System.out.println("main thread 中断了 " + thread1.getName());

        // 休眠。让上面的代码完成执行完毕
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("sleep被打断");
        }
        System.out.println("====================");
        service.setCanInterrupt(false); // 设置不允许中断
        Thread thread2 = new Thread(runnable, "thread2");
        thread2.start();
        thread2.interrupt();
    }

    /**
     * 4.测试permit相关的两个方法
     *  availablePermits: 当前可用的许可数，一般用于调试
     *  drainPermits: 获取并返回立即可用的所有许可数，并将可用许可数置为0
     */
    public static void testPermitsMethod() {
        Semaphore semaphore = new Semaphore(2);
        try {
            semaphore.acquire();
            System.out.println(semaphore.availablePermits());
            System.out.println(semaphore.drainPermits() + "\t" + semaphore.availablePermits());
            System.out.println(semaphore.drainPermits() + "\t" + semaphore.availablePermits());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 5. 获取当前等待许可的线程队列信息
     */
    public void testQueueInfo() {
        Service service = new Service();
        Runnable runnable = () -> service.testQueueInfo();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable, "thread" + i);
            thread.start();
        }
    }

    class Service {
        boolean canInterrupt;
        public Service() {
            this.canInterrupt = true;
        }

        private Semaphore semaphore = new Semaphore(1); // 构造参数permits是许可的意思，表示同一时间内最多允许多少个线程同时执行acquire()/release()
        void testMethod() {
            try {
                semaphore.acquire(); // 也可以加一个参数，表示一次消费多少个许可
                semaphore.acquireUninterruptibly();
                System.out.println(Thread.currentThread().getName() + " begin time = " + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " end   time = " + System.currentTimeMillis());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        void testInterrupt() {
            try {
                if (canInterrupt) semaphore.acquire();
                else semaphore.acquireUninterruptibly();
                System.out.println(Thread.currentThread().getName() + " begin time = " + System.currentTimeMillis());
                for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
                    Math.random();
                }
                System.out.println(Thread.currentThread().getName() + " end   time = " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "被中断了");
                e.printStackTrace();
            }
        }
        void testQueueInfo() {
            try {
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println("大约还有 " + semaphore.getQueueLength() + " 个线程在等待");
                System.out.println("是否还有线程正在等待信号量：" + semaphore.hasQueuedThreads());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
        public boolean isCanInterrupt() {
            return canInterrupt;
        }
        void setCanInterrupt(boolean canInterrupt) {
            this.canInterrupt = canInterrupt;
        }
    }

    class MyRunable implements Runnable {
        private Service service;
        public MyRunable(Service service) {
            this.service = service;
        }
        @Override
        public void run() {
            service.testMethod();
        }
    }
}
