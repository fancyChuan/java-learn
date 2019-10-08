## 用netty实现echo服务

所有netty服务都需要有以下两部分：
- 至少一个ChannelHandler——实现服务器从客户端接收到的数据的处理，也就是核心业务逻辑
- 引导——配置服务器的启动代码，比如绑定要监听的端口

4个类：
- EchoServerHandler
    - 继承 ChannelInboundHandlerAdapter, 这个类提供了ChannelInboundHandler的默认实现
    - 覆盖channelRead/channelReadComplete/exceptionCaught三个方法
- EchoServer
    - 绑定服务器的端口并监听
    - 配置Channel，以将入站消息通知给EchoServerHandler实例
- EchoClientHandler
    - 用来处理客户端即将输入的信息
- EchoClient
    - 连接到服务端

如果不捕获异常，会发生什么呢
>每个Channel 都拥有一个与之相关联的ChannelPipeline，其持有一个ChannelHandler 的
  实例链。在默认的情况下，ChannelHandler 会把对它的方法的调用转发给链中的下一个Channel-
  Handler。因此，如果exceptionCaught()方法没有被该链中的某处实现，那么所接收的异常将会被
  传递到ChannelPipeline 的尾端并被记录。为此，你的应用程序应该提供至少有一个实现了
  exceptionCaught()方法的ChannelHandler