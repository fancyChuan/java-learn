package multithreads;

import java.util.Random;

/**
 * 传统的线程通信：模拟账户只允许一次存款一次取款交替进行
 * TODO：如何修改确保ABC每个人都能够存款4次，而AAAA和YYYY每个个都刚好取款6次？
 * 在这个场景下，需要两次竞争锁
 *  1. 竞争account锁进入draw()或者deposit()函数，synchronized的同步方法用法
 *  2. 被唤醒后竞争drawFlag锁执行取款或者存款，synchronized的同步代码块用法
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
        // 三个人存款，每个人存4次。2个人取款，每个人6次
        new Thread(depositRunnable, "人物A").start();
        new Thread(depositRunnable, "人物B").start();
        new Thread(depositRunnable, "人物C").start();
        new Thread(drawRunnable, "AAAA").start();
        new Thread(drawRunnable, "YYYY").start();
    }

    class Account {
        private int balance;
        private Boolean drawFlag; // flag表示是否允许取款，需要保证值修改后所有线程可见
        public Account(int balance) {
            this.balance = balance;
            this.drawFlag = false; // 要求先存款
        }
        // 余额不能随便修改，只提供getter方法
        public double getBalance() {
            return balance;
        }
        public synchronized void draw(int drawAmt, int index) {
            try {
                while (true) {
                    synchronized (drawFlag) { // 同时有多个取款线程竞争锁，如果拿到后需要再次检查是否允许取款
                        if (!drawFlag) { // 不允许取款
                            wait(); // 这里地方可能会被存款线程唤醒，也可能被取款线程唤醒
                            // continue; // 被唤醒换需要再次判断当前是否能够取款
                        } else {
                            balance -= drawAmt;
                            System.out.println(Thread.currentThread().getName() + " " + index + "次开始取款 " + drawAmt + "\t之后余额为 " + balance);
                            drawFlag = false;
                            notifyAll(); // 其实这里更合理的应该是notify存款线程，而不需要唤醒取款线程
                            break;
                        }
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public synchronized void deposit(int depositAmt, int index) {
            try {
                while (true) {
                    synchronized (drawFlag) {
                        if (drawFlag) {
                            notifyAll();
                            wait();
                        } else {
                            balance += depositAmt;
                            System.out.println(Thread.currentThread().getName() + " " + index + "次开始存款 " + depositAmt + "\t之后余额为 " + balance);
                            drawFlag = true;
                            notifyAll(); // 这里也是一样，需要唤醒取款线程而不需要唤醒存款线程
                            break;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
