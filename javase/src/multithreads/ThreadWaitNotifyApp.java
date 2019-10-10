package multithreads;

import java.util.Random;

/**
 * 传统的线程通信：模拟账户只允许一次存款一次取款交替进行
 *
 */
public class ThreadWaitNotifyApp {
    public static void main(String[] args) throws InterruptedException {
        ThreadWaitNotifyApp app = new ThreadWaitNotifyApp();

        Account account = app.new Account(0);
        Random random = new Random();
        Runnable depositRunnable = () -> {
            for (int i = 0; i < 4; i++) {
                account.deposit(random.nextInt(10), i);
            }
        };
        Runnable drawRunnable = () -> {
            for (int i = 0; i < 6; i++) {
                account.draw(random.nextInt(10), i);
            }
        };
        // 三个人存款，每个人存10次。2个人取款，每个人
        new Thread(depositRunnable, "人物A").start();
        new Thread(depositRunnable, "人物B").start();
        new Thread(depositRunnable, "人物C").start();
        new Thread(drawRunnable, "AAAA").start();
        new Thread(drawRunnable, "YYYY").start();
    }

    class Account {
        private int balance;
        private boolean drawFlag; // flag表示是否允许取款
        public Account(int balance) {
            this.balance = balance;
            this.drawFlag = false; // 要求实现存款
        }
        // 余额不能随便修改，只提供getter方法
        public double getBalance() {
            return balance;
        }
        public synchronized void draw(int drawAmt, int index) {
            try {
                if (!drawFlag) { // 不允许取款
                    wait();
                }
                synchronized (this) {
                    balance -= drawAmt;
                    System.out.println(Thread.currentThread().getName() + " " + index + "次开始取款 " + drawAmt + "\t之后余额为 " + balance);
                    drawFlag = false;
                    notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public synchronized void deposit(int depositAmt, int index) {
            try {
                if (drawFlag) {
                    wait();
                }
                synchronized (this) {
                    balance += depositAmt;
                    System.out.println(Thread.currentThread().getName() + " " + index + "次开始存款 " + depositAmt + "\t之后余额为 " + balance);
                    drawFlag = true;
                    notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
