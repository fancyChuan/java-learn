package concurrency.base.threads;

/**
 * join方法的使用
 */
public class ThreadJoinApp {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadOne 执行结束");
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("threadTwo 执行结束");
            }
        });
        threadOne.start();
        threadTwo.start();
        System.out.println("等待所有线程执行完成");
        threadOne.join(); // 开始阻塞
        threadTwo.join(); // 再次阻塞
        System.out.println("done");



    }
}
