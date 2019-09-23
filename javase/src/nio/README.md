## NIO
传统的IO中
- 输入输出流都是阻塞式的，如BufferedReader、InputStream读取数据时，如果没有读到有效数据就会阻塞该线程
- 传统的输入流、输出流都是通过字节的移动来处理的，也就是说，面向流的输入、输出系统一次只能处理一个字节，通常效率不高

新IO
- 采用内存映射文件的方式来处理输入、输出
    - 新IO将文件或文件的一段区域映射到内存中
    - 可以像访问内存一样访问文件（模拟了操作系统的虚拟内存的概念，速度比传统的快很多）
- 面向块处理
    - Channel（通道）和Buffer（缓冲）是nio的两个核心对象
    - Channel：对传统输入/输出的模拟，提供了一个map方法，用来将“一块”数据映射到内存中
    - Buffer：一个容器，本质是一个数组，发送到Channel或者从Channel中读取的所有对象都要先放到Buffer中
- 提供了直接将Unicode字符串映射成字节序列以及逆映射操作的Charset类和支持非阻塞输入/输出的Selector类
- 主要的包
    - java.nio包：各种与Buffer相关的类
    - java.nio.channels：包含与Channel和Selector相关的类
    - java.nio.channels.spi：包含与Channel相关的服务提供者编程接口
    - java.nio.charset：包含与字符集相关的类
    - java.nio.charset.spi：包含与字符集相关的服务提供者编程接口
    
    
### 1. 使用Buffer
Buffer是一个抽象类，有ByteBuffer、CharBuffer、IntBuffer等常用子类，一般通过XxxBuffer.allocate(int cap)创建对象
- MappedByteBuffer用于表示Channel将磁盘文件的部分或全部内存映射到内存中得到的结果
- ByteBuffer还要一个allocateDirect()方法来创建直接Buffer，成本高但是效率也更高，一般只用在长生存期的Buffer

Buffer中有三个重要的概念
- 容量capacity：该Buffer对象缓冲区的大小，不能为负值，创建后不能改变
- 界限limit：位于limit后的数据不可被读写
- 位置position：下一个可以被读写的索引，相当于IO流中的指针
- 支持一个可选标记mark，允许将position定位到该mark处
> 0 <= mark <= position <= limit <= capacity

两个重要方法：
- flip() 在Buffer装完数据以后调用，把limit=position，然后position=0，为读取数据做准备
- clear() 为重新想Buffer写入数据做准备，position=0，limit=capacity

其他常用方法：
- Buffer position(int newPos) 设置position的位置，返回修改后的Buffer对象
- Buffer rewind() 把position=0，取消设置的mark
- Buffer reset() 把position转到mark所在的位置

用put/get来访问数据时，有两种形式：
- 相对：从当前position出读或写，然后position按照处理的元素个数增加
- 绝对：直接根据索引进行读或写，不会影响position的值

### 2. 使用Channel
与传统流对象的区别：
- Channel可以直接把文件的部门或全部直接映射成buffer
- 程序不能直接访问Channel中的数据，Channel只能与Buffer交互。

新IO里的Channel是按功能来划分的：
- Pipe.SinkChannel/Pipe.SourceChannel用于支持线程间通信的管道Channel
- ServerSocketChannel/SocketChannel用于支持TCP网络通信的Channel
- DatagramChannel用于支持UDF网络通信的Channel
- FileChannel
- SelectableChannel

Channel的获取：不通过构造器，而是通过InputStream、OutputStream的getChannel()方法来返回相对应的Channel

最常用的方法：
- map(mode, start, end) 将Channel对应的部分或全部数据映射成ByteBuffer，第一个参数用于控制Buffer是只读还是读写的权限，第2/3个参数控制哪部分数据映射成Buffer
- read()/write() 从Buffer中读或写，有一系列重载方法

### 3. 字符集和Charset
- Charset.availableCharset() 获取当前JDK支持的所有字符集
- charset = Charset.forName("GBK") 创建字符集对象
- charset.newDecoder()/newEncoder() 
- 调用decode() 和 encode() 方法完成编码解码 