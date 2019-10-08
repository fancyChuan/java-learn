package multithreads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CreateThreadApp {
    // 使用FutureTask来实现线程
    public static void main(String[] args) {}
}

/**
 * 继承Thread类
 */
class FirstThread extends Thread {
    private String title;

    public FirstThread(String name, String title) {
        super(name);
        this.title = title;
    }

    @Override
    public void run() { // 线程的主体方法
        for (int i = 0; i < 5; i++) {
            System.out.println(this.title + "运行, Thread Name为：" + getName() + " i为：" + i);
        }
    }
}

/**
 * 实现Runable接口
 */
class RunableThread implements Runnable {
    private String title;

    public RunableThread(String title) {
        this.title = title;
    }

    @Override
    public void run() { // 线程的主体方法
        for (int i = 0; i < 5; i++) {
            System.out.println(this.title + "运行，" + " i为：" + i);
        }
    }
}

/**
 * 使用Callable
 */
class CallerTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "hello, FutrueTask ~";
    }
}