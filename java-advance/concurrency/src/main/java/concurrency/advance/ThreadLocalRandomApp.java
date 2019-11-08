package concurrency.advance;

import java.util.concurrent.ThreadLocalRandom;

/**
 * ThreadLocalRandom的使用
 *
 * TODO：ThreadLocalRandom类的源码分析
 */
public class ThreadLocalRandomApp {
    public static void main(String[] args) {
        demo();
    }

    public static void demo() {
        ThreadLocalRandom random = ThreadLocalRandom.current(); // 获得当前线程的随机生成器
        for (int i = 0; i < 10; ++i) {
            System.out.println(random.nextInt(5));
        }
    }
}
