package multithreads;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    /**
     * 1. 线程初体验
     * 注意：一定要调用start() 方法启动线程，如果使用 run() 方法，那么还是单线程运行的
     *      同一个线程只能被启动一次，多次启动(调用start())会报错
     *  start()方法中最后有一行语句：private native void start0();
     *  其中start0是JVM根据系统提供的底层函数实现的线程启动方法，跟系统有关，但是经过JVM实现后具有跨系统性
     */
    @Test
    public  void firstThread() {
        new FirstThread("x", "线程A").start();
        new FirstThread("y", "线程B").start();
        new FirstThread("z", "线程C").start();
    }

    /**
     * 2. 通过Runable接口实现多线程
     */
    @Test
    public  void testRunableThread() {
        Thread threadA = new Thread(new RunableThread("线程A"));
        Thread threadB = new Thread(new RunableThread("线程B"));
        Thread threadC = new Thread(new RunableThread("线程C"));
        threadA.start();
        threadB.start();
        threadC.start();
    }

    /**
     * 3.使用Lambda表示式实现Runable接口
     */
    @Test
    public  void testLambdaRunable() {
        for (int i = 0; i < 3; i++) {
            String title = "线程对象-" + i;
            Runnable run = () -> {
                for (int x = 0; x < 5; x ++) {
                    System.out.println(title + "运行，x=" + x);
                }
            };
            new Thread(run).start();
        }
    }

    /**
     * 4. 使用FutureTask来获取线程执行后的返回值
     */
    @Test
    public  void testFutureTask() {
        try {
            FutureTask<String> future = new FutureTask<>(new CallerTask());
            new Thread(future).start(); // 启动线程
            try {
                System.out.println("开始调用获取线程返回值的方法");
                String result = future.get(); // 这个地方其实是阻塞的，如果线程还没有完成任务的话
                System.out.println("获取线程返回值结束");
                System.out.println(result);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 5. 使用FutureTask：用Lambda表达式生成Callable
     */
    @Test
    public  void testLambdaCallable() {
        FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
            int i = 0;
            for (; i < 100; i++) { // 注意这里的for循环的写法，不需要再写int i=0;
                System.out.println(Thread.currentThread().getName() + "的循环变量i的值： " + i);
            }
            return i;
        });
        for (int i = 0; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + "的循环变量i的值： " + i);
            if (i == 20) {
                new Thread(task, "带返回值的线程threadX").start();
            }
        }
        try {
            System.out.println("子线程返回值：" + task.get()); // 获取返回值可以设置超时
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testYield() throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                if (i == 20) {
                    Thread.yield();
                }
            }
        };
        Thread threadA = new Thread(runnable, "高级线程A");
        Thread threadB = new Thread(runnable, "低级线程B");
        threadA.setPriority(Thread.MAX_PRIORITY); // 这两行不设置优先级的时候，可能出现A线程执行到20的时候就切换到线程B
        threadB.setPriority(Thread.MIN_PRIORITY); // 设置了优先级之后，线程A虽然赞同，但是没有比他优先级相同或者更好
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
