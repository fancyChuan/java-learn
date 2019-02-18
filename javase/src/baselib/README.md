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