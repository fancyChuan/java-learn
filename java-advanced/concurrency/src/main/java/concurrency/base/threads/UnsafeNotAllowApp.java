package concurrency.base.threads;

import sun.misc.Unsafe;

/**
 * 使用Unsafe类：不允许的用法
 *
 * 这个代码是跑不通的，主要是因为Unsafe是直接操作内存的，不安全。
 * JDK开发组在Unsafe源码中做了限制，不让开发人员在正规渠道使用Unsafe类，而是在rt.jar包的核心类中使用
 */
public class UnsafeNotAllowApp {
    static final Unsafe unsafe = Unsafe.getUnsafe(); // 获取Unsafe的实例 // 这行代码是会报错的，因为源码中不允许这样使用，不安全
    static long stateOffset; // 用来记录变量state在类中的偏移量
    private volatile long state = 0;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(UnsafeNotAllowApp.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnsafeNotAllowApp test = new UnsafeNotAllowApp();
        boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
    }
}
