package concurrency.base.threads;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 使用Unsafe：通过反射的方式使用
 *
 * TODO：Unsafe类到底在什么场景需要使用？？
 */
public class UnsafeReflectApp {
    static final Unsafe unsafe;
    static long stateOffset; // 用来记录变量state在类中的偏移量
    private volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true); // 设置为可存取
            unsafe = (Unsafe) field.get(null); // 获取该变量的值
            stateOffset = unsafe.objectFieldOffset(UnsafeReflectApp.class.getDeclaredField("state"));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new Error(e);
        }
    }

    public static void main(String[] args) {
        UnsafeReflectApp test = new UnsafeReflectApp();
        boolean success = unsafe.compareAndSwapInt(test, stateOffset, 0, 1);
        System.out.println(success);
    }
}
