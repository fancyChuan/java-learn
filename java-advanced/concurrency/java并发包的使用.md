## java并发包
### 1. Semaphore和Exchanger的使用
- Semaphore：主要用于控制线程并发的数量 参见[SemaphoreApp.java](https://github.com/fancychuan/java-learn/tree/master/java-advanced/concurrency/src/main/java/concurrency/using/SemaphoreApp.java)
    - 基本用法
    - 使用release方法动态添加许可
    - 使用acquireUninterruptibly()来设置线程不可被打断
    - drainPermits() availablePermits
    - 获取当前等待许可的线程队列信息
    - 公平与非公平信号量
    - tryAcquire方法的使用：尝试获取许可
    - 多进路-单处理-多出路
    - 使用Semaphore创建字符串池
    - 使用Semaphore实现多生产者/多消费者模式
- Exchanger：使两个线程之间的通信更加方便
