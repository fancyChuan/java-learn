package reflect;

public class ReflectAndSingleton {

    public static void main(String[] args) {
        // Singleton.getInstance()这种实现方法，在多线程环境下，会出现多个线程同时进行实例化
//        for (int i = 0 ; i < 3 ; i ++) {
//            new Thread(() -> {Singleton.getInstance().print();},"单例消费端-" + i).start();
//        }
        // 改进方法：加上synchronized关键词 如Singleton.getInstance2()
        System.out.println("==========================");
        for (int i = 0 ; i < 3 ; i ++) {
            new Thread(() -> {Singleton.getInstance2().print();},"单例消费端-" + i).start();
        }
        // 但是这个synchronized加的有点草率，我们只需要在实例化的时候进行同步的判断
        // 改进方法，在getInstance()方法体内部进行synchronized处理，如Singleton.getInstance3()
//        System.out.println("==========================");
//        for (int i = 0 ; i < 3 ; i ++) {
//            new Thread(() -> {Singleton.getInstance3().print();},"单例消费端-" + i).start();
//        }
    }
}

/**
 * 懒汉式单例模式
 */
class Singleton {
    // 单例要求跟在实例化的时候应该与主内存中的对象保持一致，而不是存副本！所以要用volatile关键词
    private static volatile Singleton instance = null;
    // 构造方法私有化，单例模式的必备条件
    private Singleton() {
        System.out.println("【" + Thread.currentThread().getName() + "】实例化Singleton类对象" );
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static synchronized Singleton getInstance2() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public static synchronized Singleton getInstance3() {
        if (instance == null) {
            // 空的时候在进行同步处理，只针对类的实例化的时候
            synchronized (Singleton.class) { // 这里不能使用synchronized(this) {}
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void print() {
        System.out.println("666");
    }
}