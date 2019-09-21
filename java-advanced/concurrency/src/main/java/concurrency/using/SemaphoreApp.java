package concurrency.using;

import java.util.concurrent.Semaphore;

/**
 * Semaphore的使用
 *  1. new Semaphore(n) 表示同一时间只有n个线程同时执行acquire方法。 n>1的时候不能保证线程安全性
 *  2. semaphore.acquire(2) 表示使用2个许可
 *  3. 使用release方法动态添加许可
 */
public class SemaphoreApp {
    public static void main(String[] args) throws InterruptedException {
        SemaphoreApp app = new SemaphoreApp();
        // app.testBaseUsing();
        app.testAddPermits();
    }

    /**
     * 基本用法
     */
    public void testBaseUsing() {
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
     * 动态增加permits
     */
    public void testAddPermits() throws InterruptedException {
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

    class Service {
        private Semaphore semaphore = new Semaphore(2); // 构造参数permits是许可的意思，表示同一时间内最多允许多少个线程同时执行acquire()/release()
        public void testMethod() {
            try {
                semaphore.acquire(); // 也可以加一个参数，表示一次消费多少个许可
                System.out.println(Thread.currentThread().getName() + " begin time = " + System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " end   time = " + System.currentTimeMillis());
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
