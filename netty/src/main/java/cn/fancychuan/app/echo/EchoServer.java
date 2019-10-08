package cn.fancychuan.app.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * echo应用的引导服务端
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
        }
        int port = Integer.valueOf(args[0]); // 这行代码是可能会抛出NumberFormatException异常的
        new EchoServer(port).start();
    }

    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class) // 指定使用的nio传输channel
                    .localAddress(new InetSocketAddress(port)) // 指定socket地址
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 添加一个EchoChannelHandler到子Channel的ChannelPipeline

                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture f = bootstrap.bind().sync(); // 异步的绑定服务器，调用sync方法阻塞直到绑定完成
            f.channel().closeFuture().sync(); // 获取Channel，调用closeFuture()方法并阻塞当前线程直到完成
        } finally {
            group.shutdownGracefully().sync(); // 关闭EventLoopGroup并释放所有资源，同样要阻塞直到完成
        }

    }
}
