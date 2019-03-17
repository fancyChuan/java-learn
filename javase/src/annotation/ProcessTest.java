package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 提取Annotation的信息并处理
 */
public class ProcessTest {
    /* 使用 annotation.MyTest 做示例，得到的结果如下
方法 public static void annotation.MyTest.m3() 运行失败，异常：java.lang.IllegalArgumentException: 参数出错啦!
方法 public static void annotation.MyTest.m4() 运行失败，异常：java.lang.RuntimeException: 程序运行出错啦
一共有方法：13 个
运行了：3 个
成功了：1 个
失败了：2 个
     */
    public static void process(String clazz) throws ClassNotFoundException {
        int passed = 0;
        int failed = 0;
        int all = 0;
        // 遍历类的所有方法，使用反射的技术
        for (Method method: Class.forName(clazz).getMethods()) {
            all++;
            if (method.isAnnotationPresent(Testable.class)) {
                try {
                    // 调用method方法
                    method.invoke(null);
                    passed++;
                } catch (Exception e) {
                    System.out.println("方法 " + method + " 运行失败，异常：" + e.getCause());
                    // e.printStackTrace();
                    failed++;
                }
            }
        }

        System.out.println("一共有方法：" + all + " 个");
        System.out.println("运行了：" + (passed+failed) + " 个");
        System.out.println("成功了：" + passed + " 个");
        System.out.println("失败了：" + failed + " 个");
    }
}
