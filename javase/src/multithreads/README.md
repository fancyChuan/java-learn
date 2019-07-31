## Java多线程

多线程的实现方法：
- 继承Thread类
    - 好处：在run()方法中获取当前线程直接使用this就可以了，无须使用Thread.currentThread() 方法
    - 坏处：1.java不支持多继承，继承了Thread类就无法继承其他类。 2.任务和代码没有分开，当多个线程需要执行相同的任务，就需要多次写同样的代码
- 实现Runable接口
    - 坏处：任务没有返回值。继承Thread类也一样，没有返回值
- 使用FutureTask方式