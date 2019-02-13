## java I/O

- 字节流： 以字节为单位进行处理
- 字符流： 以字符为单位进行处理

java的IO流使用了一种装饰器设计模式，将IO流分成**底层节点流**和**上层应用流**
> 底层节点流：用于和底层的物理节点直接关联

java.nio是java.io包的升级版

### 1. File类
用于操作文件和目录，但不能访问文件内容，访问内容需要输入输出流

#### 1.1 文件名相关的方法
new File(String name) 下面的几个方法都是针对name来说的。name可以使绝对路径也可以是相对路径

方法名 | 说明
--- | ---
getName() | 返回该对象的文件名或者路径名(不一定等于name)
getPath() | 返回File(String name)实例化的时候传入的name
String getAbsolutePath() | 返回绝对路径名 
File getAbsoluteFile() | 和getAbsolutePath()类似，只不过返回的是File对象
getParent() |
boolean renameTo(File newName) | 

#### 1.2 文件检测相关
方法名 | 说明
--- | ---
exists() | 文件或者目录是否存在
canWrite() |
canRead() |
isFile() | 
isDirectory() | 
isAbsolute() | 是否绝对路径
#### 1.3 常规文件信息
方法名 | 说明
--- | ---
long lastModified() | 最后修改时间
long length() | 文件内存的长度
#### 1.4 文件操作
方法名 | 说明
--- | ---
boolean createNewFile() | 不存在则新建，存在应该就覆盖
boolean delete() |
static File createTempFile() | 静态方法，可以直接通过File.createTempFile()调用，创建临时文件
void deleteOnExit() | 注册一个删除钩子，指定当JVM退出时删除
#### 1.5 目录操作
方法名 | 说明
--- | ---
boolean mkdir() | 新建目录
String[] list() | 
File[] listFiles() | 
static File[] listRoots() | 系统所有根目录

##### 文件过滤器
list方法可以接受一个FilenameFilter参数，过滤出特定的文件 实例参见 FilenameFilterTest.java

java.io.FilenameFilter是一个接口，只有一个抽象方法accept，返回值类型是boolean，可以使用Lambda表达式创建实现该接口的对象

### 2. 理解java的IO流
java把所有传统的流类型都放在java.io中，使得开发者可以使用一致的代码去读写不同的IO流节点

- 分类
    - 输入流、输出流
        - 输入流和输出流的划分是从程序运行时所在的内存的角度来划分的。
        - 输入流以InputStream和Reader为基类，输出流以OutStream和Writer为基类
    - 字节流、字符流（字节8位，字符16位）
    - 节点流和处理流（流的角色划分）
        - 节点流：低级流，一般是特定的IO设备
        - 处理流：高级流、包装流，一般对已存在的流进行封装
            - 使用处理流的好处是，使用相同的处理流就可以处理不同的数据源，泛化能力强。
            - 处理流的实现采用了 装饰器设计模式

- 支持的方法

流类型 |方法名 | 说明
--- | --- | ---
InputStream/Reader | mark(int readAheadLimit) | 在指针当前位置设置一个mark
InputStream/Reader | boolean markSupported() | 是否支持mark操作
InputStream/Reader | reset() | 把流指针重置到上一个mark的位置
InputStream/Reader | long skip(long n) | 指针向前移动n个字节/字符
OutStream/Writer | writer(int c)