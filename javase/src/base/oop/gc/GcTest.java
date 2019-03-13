package base.oop.gc;

/**
 * 垃圾回收机制
 */
public class GcTest {
    private static GcTest gcTest;
    public void info() {
        System.out.println("调用了对象的info方法");
    }

    // 重写了finalize()方法
    public void finalize() {
        System.out.println("系统正在清理GcTest对象的垃圾...");
        gcTest = this;
    }

    public static void main(String[] args) {
        // 创建对象后立刻进入可恢复状态
        new GcTest();
        // 通知系统进行资源回收，下面两行代码效果一样
        System.gc();
        // Runtime.getRuntime().gc();

        // 强制垃圾回收机制调用可恢复对象的finalize()。
        // 执行了System.gc()后，因为回收机制的不确定性，如果不执行下面的代码，而系统内存也不紧张就可能出现空指针异常
        Runtime.getRuntime().runFinalization();
        System.runFinalization();
        gcTest.info();
    }
}
