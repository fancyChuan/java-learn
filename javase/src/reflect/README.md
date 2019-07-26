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
    - 全盘负责：某个类的所有依赖、引用都由一个类加载器负责，除非显式使用另一个类加载器
    - 父类委托：先让父类的加载器尝试加载，失败时才由自己加载
        > 类加载器之间的父子关系是类加载器实例之间的关系，而不是类继承上的父子关系
    - 缓存机制：所有加载过的类都会缓存起来，当使用某个类时优先到缓存中寻找
```
类加载器加载Class的一般流程：
1. 检查Class是否被加载过，即缓存区有没有改Class，有就直接到第5步，否则第2步
2. 父类加载器存在则请求父类加载器去加载目标类，然后第3步；不存在（parent是根类加载器或者本身就是根类加载器）则使用根类加载器去载入目标类，失败则到第4步
3. 父类加载器加载成功则第5步，失败则子类尝试自己去加载目标类，成功则5，失败则4
4. 抛出ClassNotFoundException异常
5. 返回对于的java.lang.Class对象
```
- 类加载器有几个特性：
    - 每个类加载器都有自己预定义的搜索范围，用来加载class文件
    - 类和它的类加载器共同确定了类的唯一性。也就是说同一个class文件被不同类加载器加载到JVM中，那么这两个类就是不同的类
    - 双亲委派模型
        - 所有类加载器都是有层级结构的，每个类加载器都有一个父类类加载器（通过组合实现，而不是继承），除了根类加载器（bootstrap classloader）
        - 当一个类加载器接收到一个类加载请求时，首先会委派给它的父类去加载，依次类推直到根类加载器，如果父类加载器无法加载，子类才会尝试自己去加载
    - 通过双亲委派模型，实现了类加载器的三个特性：
        - 委派
        - 可见性：子类加载器可访问父类加载器加载的类，而父类不能访问子类加载器加载的类
        - 唯一性：保证每个类都只被加载一次
- 使用自定义类加载器
    - 两个关键方法
        - loadClass() 加载器的入口，执行步骤如下：
            - findLoadedClass(String) 检查是否已经加载类
            - 在父类加载器上调用loadClass(),父类加载器为null，则使用更累加载器
            - 调用findClass() 方法查找类
        - findClass() 根据指定类型查找类
    - 通常推荐重写findClass()而不是loadClass()方法，可以避免覆盖默认类加载器的父类委托、缓冲机制
    - 还有一个核心方法：Class defineClass(String name, byte[] b, int off, int len) 负责吧指定类的字节码文件读到b中，并转为Class对象
- URLClassLoader类
    - 该类是系统类加载器和扩展类加载器的父类（此处的父类，就是类与类之间的继承关系）
    - 可以从本地文件系统中获取二进制文件来加载类，也可以从远程主机中获取、加载

通过反射查看类信息
- 对象运行时的两种类型：编译时类型、运行时类型。如 Person p = new Student()
    - 编译时类型是 Person
    - 运行时类型是 Student
    - 很多情况下需要调用运行时类型的方法、属性，有两种解决方法：
        - 编译时知道类型的具体信息，通过 instanceof 判断之后做**强制类型转换**
        - 编译时不知道类型的具体信息，通过反射来解决
- 获得Class对象
    - 三种方式：
        - Class.forName(String className)  className需要是全限定类名
        - 类名.Class
        - 对象实例.getClass()
    - 相比第一种，第二种方式更好：
        - 代码更安全，类名.Class在编译的时候编译器会做检查，以免类不存在
        - 性能更好，因为第二种方式不需要调用方法
- 获取Class对象的信息
    - 获取构造器
        - getConstructor()
        - getDeclaredConstructor()
    - 获取方法 
        - getMethod() getMethods() 获取public方法
        - getDeclaredMethod() getDeclaredMethods() 与访问权限无关的方法
    - 获取成员变量
        - getField() getFields() 获取public成员变量
        - getDeclaredField() getDeclaredFields() 与访问权限无关的成员变量
    - 获取注解
        - getAnnotation() getAnnotations() 
        - getDeclaredAnnotation() getDeclaredAnnotations() 获取**直接**修饰该类的注解
        - getAnnotationByType() getAnnotationsByType() 因java8新增重复注解，这里用于获取指定注解类型的多各注解
    - 其他
        - getDeclaringClass() 获取Class对象所在的外部类
        - getDeclaredClass() 获取包含的所有内部类
        - getInterface() 获取类所实现的接口
        - getSubClass()
        - getModifiers() 获取类、接口的所有修饰符。返回的整数需要通过Modifier工具解码
        - getPackage() getName() getSimpleName() isXxx()...
```
public void info()
public void info(String name)
public void info(String name, Integer num)
=============== 获取方法时，指定方法名，同时需要指定形参列表，而获取构造器则只需要形参列表的类型
clazz.getMethod("info", String.Class)  获取到 info(String name)
clazz.getMethod("info", String.Class, Integer.Class)  获取到 info(String name, Integer num)
```


### 4. 使用反射生成并操作对象
Class对象可以获得该类的方法（由Method对象表示）、构造器（由Constructor对象表示）、成员变量（由Field表示），三个类都位于java.lang.reflect中，实现了java.lang.reflect.Member接口
- 我们可以通过Method对象来执行对应的方法
- 通过Constructor对象来调用对应的构造器创建实例
- 通过Field对象直接访问并修改对象的成员变量值

#### 4.1 创建对象
两种方式：
- 使用Class对象的newInstance()方法，要求该Class对象有默认构造器，否则失败（JDK1.9以后该方法被废弃，主要因为需要有默认构造器）
- 先获取Class对象的Constructor对象，然后调用Constructor对象的newInstance()方法

JEE很多框架都需要根据配置文件信息来创建java对象（读到类名，也就是字符串，再通过反射创建实例） 参见 ObjectPoolFactory.java
> 使用反射创建对象时性能会相对差一点

#### 4.2 调用方法
- 通过Class对象的getMethod()方法拿到Method对象后，执行 invoke(Object obj, Object... args)方法，obj是方法的主调，args是传入的参数
- 在调用时，java会要求obj有执行该方法的权限
    - 需要调用某个对象的private方法时，可以先调用Method对象的setAccessible(boolean flag)，为true表示取消java的访问权限检查
    
#### 4.3 访问成员变量值
- 通过getFields()或者getField()方法获得该类的成员变量
- Field对象通过getXxx() setXxx()来获取、设置成员变量的值，相当于调用getter、setter方法

#### 4.4 操作数组
java.lang.reflect包下有一个Array类，可以代表所有数组，有以下几类方法：
- static Object newInstance(type, int... length) 创建一个具有指定元组类型、指定维度的新数组
- static xxx getXxx(Ojbect array, int index) 获取array数组的第index个元素，xxx为基本数据类型，如果是引用类型，则方法为：get(array, index)
- static setXxx(Object array, int index, xxx value) 给第index个元素赋值

#### 5. 使用反射生成JDK动态代理
java.lang.reflect提供一个Proxy类和InvocationHandler接口



### 二、应用
#### 1. 反射与工厂设计模式
工厂设计模式：解决客户端中接口与接口实现类之间的耦合问题

接口 -> 接口实现子类 -> 静态工厂 -> 动态工厂 -> 泛型动态工厂

参见： reflect.ReflectAndFactory.java

#### 2. 反射与单例模式
单例模式的核心本质：类的构造方法私有化，在类的内部产生实例对象之后通过static方法获取实例化对象

单例模式一共有两类：懒汉式、饿汉式

懒汉式： 参见 reflect/ReflectAndSingleton.java

【面试题】请编写单例模式
- 【100%】直接编写一个饿汉式的单例设计模式，并且实现构造方法私有化
- 【120%】在java中哪里使用到了单例模式？Runtime类、Class、Spring
- 【200%】懒汉式单例模式的问题？

Unsafe类：可以利用反射来获取对象的相关信息，并在底层直接使用c++来代替JVM执行，即：可以绕过JVM的对象管理机制。一旦所使用了Unsafe类，就无法继续使用JVM的内存管理机制和垃圾回收。一般不建议使用
- 构造方法：private Unsafe(){}
- 私有常量：private static final Unsafe theUnsafe = new Unsafe()
> 这个类并没有提供static方法，并且构造方法也是私有的，要想获得这个对象，就只能通过反射机制来完成。比如Main.testUnsafe()
- Unsafe类最大的特点扩展了java语言的表达能力，便于在更高层的代码里实现原本要在更底层实现的核心类库

#### 3. 属性自动赋值（bean注入）
传统的对象属性赋值存在的弊端：setter编写麻烦，且存在大量重复
```
// 传统的赋值操作，有多少个属性就得调用多少次setter方法
Person person = new Person()
person.setXxx(yyy)
person.setXxx(yyy)
person.setXxx(yyy)
```

属性自动化赋值的思路：
- 传入字符串的格式："属性名:属性值|属性名:属性值"
- 开发一个ClassInstanceFactory，将传入的字符串注入到bean，完成对象实例化
- BeanUtils类负责完成注入
- StringUtils负责把首字母大写