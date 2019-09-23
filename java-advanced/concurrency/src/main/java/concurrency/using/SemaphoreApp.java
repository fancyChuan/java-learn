package concurrency.using;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Semaphore的使用
 *  1. 基本用法
 *  2. 使用release方法动态添加许可
 *  3. 使用acquireUninterruptibly()来设置线程不可被打断
 *  4. drainPermits() availablePermits
 *  5. 获取当前等待许可的线程队列信息
 *  6. 公平与非公平信号量
 *  7. tryAcquire方法的使用：尝试获取许可
 *  8. 多进路-单处理-多出路
 *  9. 使用Semaphore创建字符串池
 */
public class SemaphoreApp {
    public static void main(String[] args) throws InterruptedException {
        SemaphoreApp app = new SemaphoreApp();
        // app.testBaseUsing();
        // testAddPermits();
        // app.testCanNotInterrupt();
        // testPermitsMethod();
        // app.testQueueInfo();
        // app.testFairNonFair();
        // app.testTryAcquire();
        // app.testMoreOne();
        app.testSemaphorePoolList();
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

    /**
     * 6. 公平与非公平信号量
     * new Semaphore(1, isFair) 默认是非公平的，意思是线程的启动顺序与获得许可semaphore.acquire()的顺序无关，先启动不代表先获得许可
     * 公平信号量则表示启动顺序与获得锁的顺序有关，但也有小概率的可能获得锁的顺序与启动顺序无关
     */
    public void testFairNonFair() throws InterruptedException {
        Service service = new Service();
        Runnable runnable = () -> {
            try {
                service.semaphore.acquire();
                System.out.println("线程" + Thread.currentThread().getName() + "启动了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                service.semaphore.release();
            }
        };
        System.out.println("=========== 非公平信号量 ============");
        Thread thread1 = new Thread(runnable, "thread1");
        Thread thread2 = new Thread(runnable, "thread2");
        Thread thread3 = new Thread(runnable, "thread3");
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("=========== 公平信号量 ============");
        service.setFair(true); // 设置为公平信号量
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(runnable, "thread" + i);
            thread.start();
        }
    }

    /**
     * 7. tryAcquire方法的使用：尝试获取许可
     *  tryAcquire() 默认尝试获取一个许可，非阻塞
     *  tryAcquire(int permits) 默认尝试获取指定数量的许可
     *  tryAcquire(long timeout, TimeUnit nuit) 带超时的尝试获取1个许可
     *  tryAcquire(int permits, long timeout, TimeUnit nuit) 带超时的尝试获取多个许可
     */
    public void testTryAcquire() {
        Service service = new Service(3);
        Runnable runnable = service::testTryAcquire;
        Thread threadAA = new Thread(runnable, "threadAA");
        Thread threadBB = new Thread(runnable, "threadBB");
        threadAA.start();
        threadBB.start();
    }

    /**
     * 8. 多进路-单处理-多出路：允许多个线程同时处理任务，但是执行任务的顺序是同步的，也就是阻塞的
     * 把 LockService类中的 lock.lock()和lock.unlock() 去掉，则是 多进路-多处理-多出路
     */
    public void testMoreOne() {
        LockService service = new LockService();
        Runnable runnable = service::sayHello;
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable, "thread" + i);
            thread.start();
        }
    }

    /**
     * 9. 使用Semaphore创建字符串池
     *  使用ReentrantLock加锁
     *  使用Condition做线程间通信
     */
    public void testSemaphorePoolList() {
        ListPool pool = new ListPool();
        Runnable runnable = () -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String string = pool.get();
                System.out.println(Thread.currentThread().getName() + "取得值： " + string);
                pool.put(string);
            }
        };
        for (int i = 0; i < 12; i++) {
            Thread thread = new Thread(runnable, "thread" + i);
            thread.start();
        }
    }

    class Service {
        Semaphore semaphore;
        int permits;
        boolean canInterrupt;  // 是否允许被中断，默认允许
        boolean isFair; // 信号量的获取是否公平，默认是false，也就是非公平
        public Service(int permits) {
            this.permits = permits;
            this.canInterrupt = true;
            isFair = false;
            this.semaphore = new Semaphore(this.permits, isFair); // 构造参数permits是许可的意思，表示同一时间内最多允许多少个线程同时执行acquire()/release()
        }
        public Service() {this(1);}

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
        void testTryAcquire() {
            try {
                if (semaphore.tryAcquire(3, 3, TimeUnit.SECONDS)) {
                    System.out.println("线程" + Thread.currentThread().getName() + "成功进入");
                    for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                        Math.random();// 把这行代码注释掉，那么两个线程都能够顺利拿到许可。因为在3s之内许可已经释放了
                    }
                    semaphore.release(2);
                } else {
                    System.out.println("线程" + Thread.currentThread().getName() + "未能成功进入");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        void setFair(boolean fair) {
            this.isFair = fair;
            semaphore = new Semaphore(1, this.isFair);
        }
        boolean isCanInterrupt() {
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

    class LockService {
        private Semaphore semaphore = new Semaphore(3);
        private ReentrantLock lock =  new ReentrantLock();
        public void sayHello() {
            try {
                String threadName = Thread.currentThread().getName();
                semaphore.acquire();
                System.out.println("[x]线程" + threadName + "准备 " + System.currentTimeMillis());
                lock.lock(); // 从lock.lock()到lock.unlock() 这部分代码是同步的，能够保证统一时间只有一个线程在执行
                System.out.println(threadName + " 开始执行   " + System.currentTimeMillis());
                for (int i = 0; i < 5; i++) {
                    System.out.println(threadName + "\t" + i);
                }
                Thread.sleep(11); // 计算机执行速度太快了，结果会看起来像同时运行一样，区分下
                lock.unlock();
                semaphore.release();
                System.out.println("[x]线程" + threadName + "结束 " + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    // poolList只有三个元素，用完之后放回。
    class ListPool {
        int poolMaxSize = 3;
        int semaphorePermits = 5;
        private List<String> list = new ArrayList<>();
        private Semaphore semaphore = new Semaphore(semaphorePermits);
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public ListPool() {
            // super(); // todo: 为什么示例要加这行代码？都没有继承类啊
            for (int i = 0; i < poolMaxSize; i++) {
                list.add("fancy" + (i + 1));
            }
        }
        public String get() {
            String getString = null;
            try {
                semaphore.acquire();
                lock.lock();
                while (list.size() == 0) {
                    condition.await();
                }
                getString = list.remove(0);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getString;
        }
        public void put(String value) {
            lock.lock();
            list.add(value);
            condition.signalAll();
            lock.unlock();
            semaphore.release();
        }
    }
}
