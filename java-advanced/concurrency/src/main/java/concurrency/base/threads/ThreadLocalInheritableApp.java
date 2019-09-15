package concurrency.base.threads;

/**
 * 支持继承性的InheritableThreadLocal
 */
public class ThreadLocalInheritableApp {
    static InheritableThreadLocal<String> locals = new InheritableThreadLocal<>();

    public static void main(String[] args){
        locals.set("I'm main thread");
        Thread subThread = new Thread(() -> {
            System.out.println("sub thread: " + locals.get());
        });
        subThread.start();
    }
}
