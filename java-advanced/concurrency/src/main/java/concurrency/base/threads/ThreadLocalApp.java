package concurrency.base.threads;

/**
 * 变量线程本地化ThreadLocal
 *
 * TODO：实现原理
 */
public class ThreadLocalApp {
    static ThreadLocal<String> localVariable = new ThreadLocal<>();
    static void print(String string) { //打印当前线程本地内存中的共享变量值
        System.out.println(string + " : " + localVariable.get());
        localVariable.remove(); // 清除当前线程本地内存中的共享变量
    }


    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            localVariable.set("threadA lcoal variable"); // 这里设置的是线程A本地内存中的一个副本，这个副本线程B访问不了
            print("threadOne");                          // 调用后删除的是本地内存的副本，不影响另外线程的该共享变量
            System.out.println("threadA remove after: " + localVariable.get());
        });
        Thread threadB = new Thread(() -> {
            localVariable.set("threadB lcoal variable");
            print("threadOne");
            System.out.println("threadB remove after: " + localVariable.get());
        });

        threadA.start();
        threadB.start();
    }


}
