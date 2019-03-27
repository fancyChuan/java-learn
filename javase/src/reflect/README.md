## 类加载机制与反射

类的加载、连接、和初始化
- JVM和类
    - 同一JVM的所有线程、所有变量都处于同一个进程里，他们都是用JVM的内存区
    - 类变量，也就是静态变量虽然共享内存区，但是也是在同一个JVM里面的，如果用两个命令在不同的字段运行java程序，类变量也是各自不相关的
- 类的加载 
    - 把.class文件读入内存，并创建一个java.lang.Class对象
    - JVM提供系统类加载器，可以通过继承 ClassLoader 基类来创建自定义的加载器
    - 加载来源：class文件、jar文件、网络class文件、源文件动态编译并加载
- 类的连接
    - 负责把类的二进制数据合并到JRE中，分为三阶段：验证、准备、解析
- 类的初始化
    - 给类变量（静态变量）初始化
    - 类初始化的时机（6种情况）
    > 对于在编译时就确定下来的final类变量，就相当于宏变量，也就是在编译时编译器会直接替换它所出现的位置，相当于常量，也就是不会有初始化的情况发生
    - 使用ClassLoader类的loadClass()方法不会初始化，只是加载，而Class.forName()则会初始化

类加载器
- 负责把.class文件加载到内存中，并生成对应的java.lang.Class对象
- 唯一标识：全限定类名（包名+类名）+类加载器
- JVM启动时，会形成3个类加载器组成的初始类加载器层次结构

    加载器 | 名称 | 说明
    --- | --- | ---
    Bootstrap ClassLoader | 根类加载器 | 也被成为引导类加载器，负责加载java的核心类，不是java.lang.ClassLoader的子类，而是JVM自身实现
    Extension ClassLoader | 扩展类加载器 | 负责加载JRE的扩展目录(%JAVA_HOME%/jre/lib/ext或由java.ext.dirs指定的目录)中的jar包的类
    Sysytem ClassLoader | 系统类加载器 | 也叫应用加载器，负责JVM启动时加载来自java命令的-classpath，java.class.path或者CLASSPATH环境变量所指的jar包或类路径
- 类加载机制
    - 全盘负责：某个类的所有以来、引用都由一个类加载器负责，除非显式使用另一个类加载器
    - 父类委托：先让父类的加载器尝试加载，失败时才由自己加载
        > 类加载器之间的父子关系是类加载器实例之间的关系，而不是类继承上的父子关系
    - 缓存机制：所有加载过的类都会缓存起来，当使用某个类时优先到缓存中寻找
- 使用自定义类加载器
    - 两个关键方法
        - loadClass() 加载器的入口，执行步骤如下：
            - findLoadedClass(String) 检查是否已经加载类
            - 在父类加载器上调用loadClass(),父类加载器为null，则使用更累加载器
            - 调用findClass() 方法查找类
        - findClass() 根据指定类型查找类
    - 通常推荐重写findClass()而不是loadClass()方法，可以避免覆盖默认类加载器的父类委托、缓冲机制
    - 还有一个核心方法：Class defineClass(String name, byte[] b, int off, int len) 负责吧指定类的字节码文件读到b中，并转为Class对象