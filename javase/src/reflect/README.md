## 类加载机制与反射

类的加载、连接、和初始化
- JVM和类
    - 同一JVM的所有线程、所有变量都处于同一个进程里，他们都是用JVM的内存区
    - 类变量，也就是静态变量虽然共享内存区，但是也是在同一个JVM里面的，如果用两个命令在不同的字段运行java程序，类变量也是各自不相关的
- 


类加载器
- 负责把.class文件加载到内存中，并生成对应的java.lang.Class对象
- 唯一标识：全限定类名（包名+类名）+类加载器
- JVM启动时，会形成3个类加载器组成的初始类加载器层次结构

    加载器 | 名称 | 说明
    --- | --- | ---
    Bootstrap ClassLoader | 根类加载器 | 也被成为引导类加载器，负责加载java的核心类，不是java.lang.ClassLoader的子类，而是JVM自身实现
    Extension ClassLoader | 扩展类加载器 | 负责加载JRE的扩展目录(%JAVA_HOME%/jre/lib/ext或由java.ext.dirs指定的目录)中的jar包的类
    Sysytem ClassLoader | 系统类加载器 | 也叫应用加载器，负责JVM启动时加载来自java命令的-classpath，java.class.path或者CLASSPATH环境变量所指的jar包或类路径