package concurrency.base.others;

/**
 * 线程安全与不安全的示例
 *
 * 注意synchronized和volatile使用方式的不同
 */
public class ThreadSafeAndUnsafeApp {
    public static void main(String[] args) {
    }
}

class ThreadUnSafeInteger {
    private int value; // 这个变量是线程不安全的

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}

// 使用synchronized
class ThreadSafeIntegerSync {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}

// 使用volatile，不保证操作是原子性的
class ThreadSafeIntegerVolatile {
    private volatile int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

//***********************************************************************//

/**
 * 设计计数字器的时候，就需要考虑线程安全的问题
 */
class ThreadUnSafeCount {
    private long value;
    public void inc() {
        ++ value; // 先加1再使用
    }
    public long getValue() {
        return value;
    }
}

/**
 * synchronized可以解决线程安全问题，但是因为是独占锁，大大降低了并发性
 */
class ThreadSafeCount {
    private long value;
    public synchronized void inc() {
        ++ value;
    }
    public synchronized long getValue() { // 虽然是读操作，但是这里的synchronized不能去掉，因为我们需要靠它来保证value的内存可见性
        return value;
    }
}