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