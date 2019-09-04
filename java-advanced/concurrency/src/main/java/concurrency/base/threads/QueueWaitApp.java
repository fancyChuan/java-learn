package concurrency.base.threads;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;

/**
 * wait方法的使用，同时避免虚假唤醒
 */
public class QueueWaitApp {
    public static String getNowTime() {
        //获取默认时区中从系统时钟获取当前日期时间。
        LocalDateTime localDateTime = LocalDateTime.now();
        //创建一个格式化程序使用指定的模式。
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = localDateTime.format(formatter);
        return formatDateTime;
    }

    public static void main(String[] args) throws InterruptedException {
        int MAX_SIZE = 10;
        Queue<String> queue = new LinkedList<String>();

        // 队列生产者线程
        Thread produceThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("启动生产者~");
                synchronized (queue) {
                    while (queue.size() == MAX_SIZE) { // 队列满了，需要一直阻塞直到消费者开始消费，这里用while循环是为了避免虚假唤醒
                        try {
                            queue.wait(); // Object类有wait方法，也就是说所有对象都有wait方法，而执行了wait方法就会被阻塞挂起，暂时让出同步锁。注意：synchronized锁是在这里释放的
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String ele = getNowTime();
                    queue.add(ele);
                    System.out.println("[生产者]add： " + ele);
                    queue.notifyAll(); // 产生数据了，通知消费者 // 注意：这里不释放锁，只是通知调用了wait方法的线程可以去参与所的竞争
                }
            }
        });

        // 消费者线程
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("启动消费者~");
                synchronized (queue) {
                    while (queue.size() == 0) { // 队列没有内容，需要一直阻塞
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("[消费者]： " + queue.peek());
                    queue.notifyAll(); // 消费了一个元素，通知生产者
                }
            }
        });
        // 先启动消费者，然后会一直阻塞，直到生产者往队列写入元素之后才消费
        consumerThread.start();
        Thread.sleep(5000);
        produceThread.start();
    }
}
