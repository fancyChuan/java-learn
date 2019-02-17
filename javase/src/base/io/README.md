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

- 输入输出流体系 见《疯狂java讲义》P675

分类 | 字节输入流 | 字节输出流 | 字符输入流 | 字符输出流
--- | --- | --- | --- | ----
抽象基类 | InputStream | OutputStream | Reader | Writer
访问文件 | FileInputStream | FileOutputStream | FileReader | FileWriter
访问数组 | ByteArrayInputStream | ByteArrayOutputStream | ByteArrayReader | ByteArrayWriter
访问管道 | PipedInputStream | PipedOutputStream | PipedReader | PipedWriter
访问字符串 | | | StringReader | StringWriter
 | | | ==以上为节点流== | 
缓冲流 | BufferedInputStream | BufferedOutputStream | BufferedReader | BufferedWriter
转换流 | | | InputStreamReader | OutputStreamWriter
对象流 | ObjectInputStream | ObjectOutputStream | | 
抽象基类 | FilterInputStream | FilterOutputStream | FilterReader | FilterWriter
打印流 | | PrintStream | | PrintWriter
推回输入流 | PushbackInputStream | | PushbackReader |
特殊流 | DataInputStream | DataOutputStream | | 


### 5. 重定向标准输入/输出
System中有三个重定向标准输入输出的方法：
- static void setErr(PrintStream err)       重定向标准错误输出流
- static void setIn(PrintStream in)          重定向标准输入流
- static void setOut(PrintStream out)          重定向标准输出流

> 示例参见：Redirect.java

注意三个方法都需要使用PrintStream作为参数

### 6. java虚拟机读写其他进程的数据
Runtime对象的exec()方法可以运行其他程序，会产生一个Process对象，该对象是由此程序产生的**子进程**。

Process提供的三个方法
- InputStream getErrorStream(): 获得子进程的错误流
- InputStream getInputStream(): 获取子进程的输入流
- OutputStream getOutputStream(): 获取子进程的输出流

> 让子进程读取程序中的数据，应该使用==输出流==。要注意理解这里的输入输出流。这里是要把数据读到子进程中，类似于把数据存到文件节点。应该用输出流。

> 示例参见：ProcessReadWrite.java

### 7. RandomAccessFile
RandomAccessFile是java输入输出体系中功能最丰富的**文件内容访问类**，可以自由访问文件的任意位置，允许自由定位文件指针。==只能读写文件==

方法：
- long getFilePointer()     获取文件记录的指针位置
- void seek(long pos)       将文件记录指针定位到pos位置

有两个构造器：一个使用String指定文件名，一个使用File包装文件，有一个mode参数，含义如下：
- "r"       只读
- "rw"      读写，文件不存在会新建
- "rws"     读写，还要求对文件的内容或**元数据**的每个更新都同步写入底层存储设备
- "rwd"     读写，还要求对文件的内容的每个更新都同步写入底层存储设备

有一个 length() 方法可以获取文件的大小

> 示例参见： RandomAccessFileTest.java

### 8. 对象序列化
序列化：把对象转为二进制流并保存在磁盘中

要实现序列化，需要让类实现如下两个接口：
- Serializable: 一个标记接口，实现该接口不需要实现任何方法
- Externalizable

反序列化读取java对象时，需要提高该java对象所属类的class文件，或者会报 ClassNotFoundException 异常
>反序列化读取的仅仅是对象的数据，并没有类的内容

反序列化读取java对象时，不需要通过构造器来初始化对象

序列化写入多个对象时，需按照写入顺序顺序读取。

当一个可序列化对象包含父类时，这些父类要么有无参构造方法，要么也是可以序列化的

#### 对象引用序列化
比如Teacher类持有一个Person类的引用，只有当Person是可序列化的时候，Teacher类才是可序列化的，无论Teacher是否实现了Serializable、Externalizable接口

java序列化机智采用的序列化算法：
- 所有保存到磁盘中的对象都有一个序列化编号
- 在序列化对象前会检查该对象是否被序列化过，只有从没被（本JVM）序列化过的才会序列化
- 如果已经序列化过了，程序就直接输出一个序列化编号

> 这个机制也有一个潜在的问题，对于改变过的对象，第二次序列化的时候仍值输出序列化编号，而不是重新序列化 参见ObjectSerializable.testChangeObject()

#### 自定义序列化
递归序列化：对某个对象序列化的时候，系统会对该对象的所有变量依次序列化。

使用transient关键词，指定java在序列化的时候忽略该实例变量
> transient只能修饰实例变量，不可修饰java程序中的其他成分

自定义序列化需要用到的方法：
- private void writeObject(java.io.ObjectOutputStream out) throws IOException
- private void readObject(java.io.ObjectInputStream out) throws IOException, ClassNotFoundException
- private void readObjectNoData() throws ObjectStreamException

当序列化流不完整是，readObjectNoData()会被执行，比如接收方使用的反序列化类版本与发送方的不一致时
> writeObject()序列化变量的顺序需要跟readObject()方法中的顺序一致，否则不能正常恢复

> 示例参见 PersonSelf.java

更为彻底的自定义序列化方法：
ANY-ACCESS-MODIFIER Object writeReplace() throws ObjectStreamException;
> 示例参见 PersonReplace.java