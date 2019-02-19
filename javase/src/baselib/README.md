## java基础类库

### 1. 与用户交互
主要介绍如何获得用户的键盘输入
#### 1.2 使用Scanner获取键盘输入
- Scanner是一个基于正则表达式的文本扫描器，可以从文件、输入流、字符串中解析出基本类型值和字符串值。
- 默认情况下，Scanner使用空白（包括空格、tab、回车作为多个输入项之间的分隔符）

### 2. 系统相关
#### 2.1 System类
- 获取环境变量 System.getenv()
- 获取系统变量 System.getProperties() getProperty()
- gc() 通知系统进行垃圾回收
- runFinalization() 通知系统进行资源清理
- currentTimeMillis() nanoTime() 系统时间，分别对应微秒、纳秒
- in/out/err标准流，setIn() setOut() setErr() 改变标准流
- identityHashCode(Object x) 根据地址计算hasCode。如果两个对象的identityHashCode相同，那么两对象绝对是同一个

> 加载文件和动态链接库主要对native方法有用，对于一些java无法实现的功能（如访问操作系统底层硬件设备），必须借助C语音来实现。

#### 2.2 Runtime类
java程序运行时环境，每个程序都有一个与之对应的Runtime实例

可以访问JVM的相关信息，如处理器数量、内存信息等

### 3. 常用类
#### 3.1 Object
Object是所有类型的父类，常用方法有：
- boolean equals(Object obj) 判断是否同一对象
- protected void finalize() 无对象引用到该对象时，垃圾回收器用该方法来回收资源
- int hashCode() 与System.identityHashCode() 方法一样
- wait() notify() notifyAll() 控制线程
- protected clone() 复制成完全隔离的对象副本（该方法只能被子类重写或调用）
> clone() 只是浅克隆 不会对引用类型的成员变量值所引用的对象进行克隆。

#### 3.2 java7新增的Objects类
Objects是一个工具类，它的方法大多是“空指针”安全的。

#### 3.3 String/StringBuffer/StringBuilder
- String： 不可变类型，一旦创建不可改变，拼接时会产生临时数据，性能较差
- StringBuffer: 可变字符序列，一旦转换成想要的字符序列，可以调用toString()转为不可变类型
- StringBuilder: 和StringBuffer基本类似，但StringBuilder不是线程安全的，性能略高。通常情况下，优先考虑StringBuilder

API列表见《java疯狂讲义》P249-250，需要注意的方法如下：

用途 | 说明
--- | ---
String() | 返回一个长度为0的字符序列，并不是返回null 
String(String original) | 新建的String是original的副本，不是同一个对象的不同应用
String(StringBuffer buff) | 也可以 buff.toString() 
String(StringBuilder buff) | 也可以 buff.toString()
intcompareTo(String another) | 返回值的情况很多，相等时返回0
boolean contentEquals(StringBuffer buf) | 包含完全相同的字符序列时返回true
static String copyValueOf(char[] data) | 把字符数组连成字符串，与String(char[] data)构造器功能一样
void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) | 复制字符数组

#### 3.4 Math类 
> 见《java疯狂讲义》P252-253
#### 3.5 java7中的ThreadLocalRandom和Random
- Random用于产生伪随机数，两个构造器：一个以当前时间作为种子，一个需要程序员手动传入种子
- ThreadLocalRandom是Random的增强版，并发环境下可减少多线程竞争资源，使系统具有更好的线程安全性

> 见《java疯狂讲义》P253-255
#### 3.6 BigDecimal类
float double容易引起进度丢失。
> 见《java疯狂讲义》P255-257

### 4. java8的日期、时间类
