package multithreads;

public class FirstThread extends Thread {
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
