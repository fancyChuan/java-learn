package concurrency.base.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * sleep方法执行后，持有的锁仍不会释放
 *
 * 这个例子中线程A或B都可能先获得锁，也就是不会出现线程AB交叉输出的情况
 */
public class ThreadSleepApp {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            lock.lock(); // 获取独占锁
            try {
                System.out.println("线程A休眠...");
                Thread.sleep(2000);
                System.out.println("线程A结束休眠...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // 释放锁
            }
        });
        Thread threadB = new Thread(() -> {
            lock.lock(); // 获取独占锁
            try {
                System.out.println("线程B休眠...");
                Thread.sleep(2000);
                System.out.println("线程B结束休眠...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // 释放锁
            }
        });

        threadA.start();
        threadB.start();
    }
}
