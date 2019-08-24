package concurrency.base.others;

/**
 * 缓存行、伪共享：
 * 单线程下多个变量被放入同一个缓存行是有利的，代码执行会更快。
 * 但在多线程下并发修改一个缓存行中的多个变量就会竞争缓存行，从而降低程序运行性能
 */
public class CacheFakeShareApp {
    public static void main(String[] args) {
        test1();
        test2();
    }

    static final int LINE_NUM = 1024;
    static final int COLUM_NUM = 1024;

    public static void test1() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < LINE_NUM; i++) {
            for (int j = 0; j < COLUM_NUM; j++) {
                array[i][j] = i * 2 + j; // 顺序读写
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println("cache time: " + cacheTime);
    }

    public static void test2() {
        long[][] array = new long[LINE_NUM][COLUM_NUM];
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < COLUM_NUM; i++) {
            for (int j = 0; j < LINE_NUM; j++) {
                array[j][i] = i * 2 + j; // 跳跃式读写
            }
        }
        long endTime = System.currentTimeMillis();
        long cacheTime = endTime - startTime;
        System.out.println("no cache time: " + cacheTime);
    }
}
