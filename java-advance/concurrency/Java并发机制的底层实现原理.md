## Java并发机制的底层实现原理
java所使用的并发机制依赖于JVM实现和CPU的指令

### 1. volatile的应用
volatile
- 是轻量级的synchronized
- 在多处理器开发中保证了共享变量的“可见性”
- 不会引起线程上下文切换和调度。使用恰当的话，比synchronized的使用和执行成本更低
> 可见性：一个线程修改了变量的值，另一个线程可以读取到修改后的值
#### 1.1 volatile的定义和实现原理
如果一个字段被声明为volatile，java线程内存模型确保所有线程看到这个变量的值是一致的：线程对共享变量执行写入操作时不会把值缓存到寄存器或者其他地方，而是把值刷新到主内存中。

volatile的两条实现原则：
- Lock前缀指令会引起处理器缓存写回到内存
- 一个处理器的缓存写到内存会导致其他处理器的缓存无效
> 处理器采用嗅探技术保证它的内部缓存、系统内存和其他处理器的缓存的数据在总线上保持一致



java内存模型规定，将所有变量都存在主内存中，当线程使用变量时，会把主内存中的变量复制到自己的工作空间或者叫工作内存
- synchronized的内存语义：
    - （加锁）进入synchronized块：把使用到的变量从线程的工作内存中清除，这样synchronized使用该变量时直接从主内存中获取
    - （释放锁）退出synchronized块：把synchronized块内对共享变量的修改刷新到主内存中
> synchronized也经常被用来实现原子性操作
- volatile的内存语义：
    - 线程写入等价于退出synchronized块，把写入工作内存的值刷新到主内存
    - 线程读取等价于进入synchronized块，把线程本地内存的该变量的值清空，再从主内存获取最新值
> 很多时候synchronized和volatile是等价的。但是volatile的操作不是原子性的

使用volatile的场景：
- 写入变量值不依赖变量的当前值时。如果依赖当前值，那么就是获取-计算-写入三步操作，这三步操作不是原子性的，不能用volatile
- 读写变量值时没有加锁。因为加了锁就已经保证了内存可见性，不需要再把变量声明为volatile


synchronized使用的注意点：
- 通过wait方法可以释放锁，但是需要注意调用该方法的时候需要在synchronized代码块内
```
synchronized (resourceA) {
    System.out.println("threadA get resourceA lock");
}
synchronized (resourceB) {
    System.out.println("threadA get resourceB lock");
    System.out.println("threadA release resourceA lock");
    resourceA.wait(); // 这个地方执行执行会报IllegalMonitorStateException的错误，因为上面的代码已经退出了synchronized的代码块，锁已经释放，这里调用wait方法是没有拿到监视器的
}
```