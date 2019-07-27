## 网络编程

### 1.java的基本网络支持
#### 1.1 使用InetAddress
该类没有提供构造器，但是有静态方法：getByName(String host) getByAddress(byte[] ip)

InetAddress实例获取对应IP地址和主机的方法：
- getCanonicalHostName() 获取全限定域名
- getHostAddress() 获取IP地址字符串
- getHostName() 获取主机名
- isReachable() 是否可达

#### 1.2 使用URLDecoder和URLEncoder
主要是两个静态方法decode()和encode()

### 
socket编程中需要注意的点：
- 关注哪些函数是阻塞的
    - new ServerSocket().accept() 有客户端连接过来了，才往下走
    - new BufferedReader().readLine() 也是阻塞的，而且需要有分隔符才算读取了完整的一行
- 分隔符相关
    - new BufferedReader().readLine() 需要有分隔符 
    - OutputStream.write("xxx\n".getBytes()) 才算发送完整一行
    - new PrintStream(socket.getOutputStream()).println("xxxx") 这里自动会加上\n
