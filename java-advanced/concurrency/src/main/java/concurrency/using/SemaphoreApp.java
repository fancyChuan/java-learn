package concurrency.using;

import java.util.concurrent.Semaphore;

/**
 * Semaphore的使用
 *  1. new Semaphore(n) 表示同一时间只有n个线程同时执行acquire方法。 n>1的时候不能保证线程安全性
 *  2. semaphore.acquire(2) 表示使用2个许可
 */
public class SemaphoreApp {
    public static void main(String[] args) {
        SemaphoreApp app = new SemaphoreApp();
        app.test1();
    }

    public void test1() {
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
        Thread threadB = new Thread(new MyRunable(service));
        threadB.setName("B");
        Thread threadC = new Thread(new MyRunable(service));
        threadC.setName("C");
        threadA.start();
        threadB.start();
        threadC.start();
    }

    class Service {
        private Semaphore semaphore = new Semaphore(1); // 构造参数permits是许可的意思，表示同一时间内最多允许多少个线程同时执行acquire()/release()
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
