package concurrency.using;

import java.util.concurrent.Exchanger;

/**
 * Exchanger的使用:
 *  1. exchange
 *  2. 超过两个线程时，随机交换信息
 *  3. exchange(V x, long timeout, TimeUnit unit) 支持超时。在某段时间内无法配对成功的话，报错
 */
public class ExchangerApp {
    public static void main(String[] args) {
        ExchangerApp app = new ExchangerApp();
        // testExchange();
        app.testMoreThreadExchange();
    }

    /**
     * 1. exchange()方法：阻塞，执行后会阻塞直到其他线程来获取它的数据
     */
    public static void testExchange() {
        Exchanger<String> exchanger = new Exchanger<>();
        Thread threadA = new Thread(() -> {
            try {
                System.out.println("线程A中得到线程B的值=" + exchanger.exchange("中国人A"));
                System.out.println("A end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadA");
        Thread threadB = new Thread(() -> {
            try {
                System.out.println("线程B中得到线程A的值=" + exchanger.exchange("中国人B"));
                System.out.println("B end!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "threadB");
        threadA.start();
        threadB.start();
    }

    /**
     * 2. 当超过2个线程时，线程之间信息的交换就是随机的。也就是两两匹配，如果有落单的，则阻塞
     */
    public void testMoreThreadExchange() {
        Exchanger<String> exchanger = new Exchanger<>();
        ExchangerThread threadA = new ExchangerThread(exchanger, "from threadA", "threadA");
        ExchangerThread threadB = new ExchangerThread(exchanger, "from threadB", "threadB");
        ExchangerThread threadC = new ExchangerThread(exchanger, "from threadC", "threadC");
        ExchangerThread threadD = new ExchangerThread(exchanger, "from threadD", "threadD");
        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }


    class ExchangerThread extends Thread {
        private Exchanger<String> exchanger;
        private String string;
        private String threadName;

        public ExchangerThread(Exchanger<String> exchanger, String string,
                               String threadName) {
            this.exchanger = exchanger;
            this.string = string;
            this.threadName = threadName;
        }

        public void run() {
            try {
                System.out.println(threadName + ": " + exchanger.exchange(string));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
