## NIO
传统的IO中
- 输入输出流都是阻塞式的，如BufferedReader、InputStream读取数据时，如果没有读到有效数据就会阻塞该线程
- 传统的额输入流、输出流都是通过字节的移动来处理的，也就是说，面向流的输入、输出系统一次只能处理一个字节，通常效率不高

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
    
    

    