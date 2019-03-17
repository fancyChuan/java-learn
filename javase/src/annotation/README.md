## 注解

从JDK5开始，java增加了对元数据的支持，也就是Annotation。通过使用注解，可以在不改变原有逻辑的基础上为源文件嵌入一些补充信息。

Annotation是一个接口，程序通过反射来获取指定元素的Annotation对象，然后通过Annotation对象去的注解里的元数据

访问和处理Annotation的工具统称为APT

基本Annotation
- 5个基本的Annotation
    
    类型 | 说明 | 修饰的对象
    --- | --- | ---
    @Override | 限定重写父类方法，要求编译器检查被修饰的方法 | 只能是方法
    @Deprecated | 标示已过时，其他程序使用时，编译器会给出警告 | 类、接口、方法等
    @SuppressWarnings | 抑制编译器警告，作用于程序元素及其所有子元素 | 所有程序单元
    @SafeVarargs | “堆污染”警告，不带泛型对象赋值给对泛型对象时发生 | 修饰方法
    @FunctionalInterface | 指明是函数式接口，java8新增 | 接口

JDK的元Annotation：用于修饰其他Annotation
- @Retention
    - 只能用于修饰Annotation定义，指定被修饰的Annotation可以保留多长时间
    - 包含一个RetentionPolicy类型的value成员变量，取值只能为：
        - RetentionPolicy.CLASS，默认值,Annotation记录在class文件中，JVM不可获取Annotation信息
        - RetentionPolicy.RUNTIME，Annotation记录在class文件中，JVM可以获取，程序通过反射获取该Annotation信息
        - RetentionPolicy.SOURCE，Annotation保留在源码中，编译器直接丢弃这种Annotation
```
// Annotation保留到运行时
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Testable{}
```
- @Target
    - 只能修饰一个Annotation定义，指定被修饰的Annotation可以修饰哪些程序单元
    - value的取值： ElementType.Annotation/CONSTRUCTOR/FIELD/LOCAL_VARIABLE/METHOD/PACKAGE/PARAMETER/TYPE
```
// 规定Annotation Testable只能用于修饰参数
@Target(ElementType.PARAMETER)
public @interface Testable{}
// ElementType.TYPE 表示被修饰的Annotation可以修饰类、接口（包括注解）、枚举定义
```
- @Documented
    - 指定被修饰的Annotation类可以被javadoc提取成文档
- @Inherited
    - 指定被修饰的Annotation类具有继承性
    - 如下面的代码，一个类使用了@Inheritable修饰，那么它的子类也会默认使用@Inheritable修饰 参见testInheritedAnnotation()
```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Inheritable{}
```

