package base.oop.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 引用类示例
 */
public class ReferenceTest {
    public static void main(String[] args) {
        // testWeakReference();
        testPhantomReference();
        testReInstance();
    }

    /**
     * 1. 测试弱引用
     */
    public static void testWeakReference() {
        // 这里不能用String str="what" 因为这种方式系统会采取常量池来管理，使用的是强引用
        String str = new String("what the what ");
        // 创建一个弱引用
        WeakReference wr = new WeakReference(str);
        // 切断str引用于字符串之间的联系
        str = null;
        // 取出弱引用所引用的对象
        System.out.println(wr.get());

        System.gc();
        System.runFinalization();
        System.out.println(wr.get());
    }

    /**
     * 2. 测试虚引用
     *     当程序强制垃圾回收时，只有虚引用引用的字符串对象会被垃圾回收。
     *     回收后，对于的虚引用被添加到引用队列中。
     */
    public static void testPhantomReference() {
        String str = new String("what the what ");
        ReferenceQueue rq = new ReferenceQueue();
        PhantomReference pr = new PhantomReference(str, rq); // 需要传入引用队列
        str = null;
        System.out.println(pr.get()); // 无法通过虚引用获取被引用的对象，此处为null

        System.gc();
        System.runFinalization();
        System.out.println(rq.poll() == pr); // 取出最先进入队列的引用于pr比较
    }

    /**
     * 3. 重新取出软、弱引用
     *
     * 第2种方式更好，第一种方式，在(1)(2)之间被垃圾回收时，(2)得到的obj还是可能为null
     */
    public static void testReInstance() {
        String str = new String("fancyChuan");
        SoftReference sr = new SoftReference(str);
        Object obj = sr.get();

        // 第一种方式：
        if (obj == null) {
            sr = new SoftReference(str); // 重新创建 (1)
            obj = sr.get(); // (2)
        }
        // 第二种方式：
        if (obj == null) {
            obj = new String("fancyChuan"); // 使用强引用
            sr = new SoftReference(obj);
            obj = sr.get();


        }
    }
}

/* 内存示意图


栈内存                 堆内存
wr      --->        WeakReference对象
                         |
                         | 弱引用
                         |
str     --->        "what"字符串

 */
