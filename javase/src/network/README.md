## 网络编程

### java的基本网络支持

### 
socket编程中需要注意的点：
- 关注哪些函数是阻塞的
    - new ServerSocket().accept() 有客户端连接过来了，才往下走
    - new BufferedReader().readLine() 也是阻塞的，而且需要有分隔符才算读取了完整的一行
- 分隔符相关
    - new BufferedReader().readLine() 需要有分隔符 
    - OutputStream.write("xxx\n".getBytes()) 才算发送完整一行
    - new PrintStream(socket.getOutputStream()).println("xxxx") 这里自动会加上\n
