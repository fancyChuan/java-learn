package multithreads;

public class RunableThread implements Runnable {
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
