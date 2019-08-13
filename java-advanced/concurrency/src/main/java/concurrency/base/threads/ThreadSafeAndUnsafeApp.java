package concurrency.base.threads;

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
