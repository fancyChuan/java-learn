## Java多线程
关于线程的几个知识：
- 使用多线程技术时，代码的运行结果与代码执行顺序或者调用顺序是无关的
- 线程是一个子任务，CPU以不确定的方式，或者说以随机的时间来调用线程中的run方法
- 线程的start()方法通知“线程规划器”该线程已经准备就绪，等待调用线程对象的run方法。如果直接调用thread.run()那就不是异步了，而是同步执行，由main主线程调用该方法
- Thread也实现了Runable接口，意味着new Thread(Runnable target)也可以传入一个Thread对象，将一个Thread对象的run方法交给其他线程调用

多线程的实现方法：
- 继承Thread类
    - 好处：在run()方法中获取当前线程直接使用this就可以了，无须使用Thread.currentThread() 方法。也方便传参，通过set方法或者构造函数
    - 坏处：1.java不支持多继承，继承了Thread类就无法继承其他类。 2.任务和代码没有分开，当多个线程需要执行相同的任务，就需要多次写同样的代码
- 实现Runable接口
    - 代码可复用
    - 坏处：只能使用主线程中被声明为final的变量；任务没有返回值。继承Thread类也一样，没有返回值
- 使用FutureTask方式
    - 可以获取返回值


线程的控制：
- join
- 后台线程
- sleep
- 线程让步：yield
- 改变线程的优先级
    - 优先级高的线程能获得更多执行机会。线程的优先级默认与创建它的父线程相同
    - Thread.MAX_PRIORITY 10; Thread.MIN_PRIORITY 0; Thread.NORM_PRIORITY 5; main线程具有普通优先级，也就是5
    - 通过setPriority()设置

线程同步
- synchronized
    - 释放同步监视器的情况
        - 正常结束、break/return终止代码块、未处理的错误或异常
        - 调用wait()方法
    - 不会释放同步监视器锁：
        - 调用Thread.sleep() Thread.yield()方法
        - 线程执行同步代码块，其他线程调用了该线程的supend方法将线程挂起。
> 尽可能避免使用supend()/resume()来控制线程
- 同步锁Lock
    - JDK1.5开始提供
    - 两个根接口：Lock，ReadWriteLock读写锁
    - 两个实现类：ReentrantLock可重入锁、ReentrantReadwriteLock
- 死锁

线程通信
- 传统的线程通信：wait() notify() notifyAll()
- 使用Condition控制线程通信
- 使用阻塞队列BlockingQueue控制线程通信