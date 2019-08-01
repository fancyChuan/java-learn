package concurrency.base.threads;

/**
 * 测试并发执行和串行执行的速度
 * 【结论】并发执行不一定比串行执行快：执行次数不超过百万次的时候并行执行速度要鳗鱼串行执行
 *
 */
public class ConcurrencyAndSerialApp {
    private static final long count = 100000000l;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    // 并发执行
    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b --;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency: " + time + "ms, b=" + b);
    }

    // 串行执行
    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }

        int b = 0;
        for (int i = 0; i < count; i++) {
            b --;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial: " + time + "ms, b=" + b);
    }
}
