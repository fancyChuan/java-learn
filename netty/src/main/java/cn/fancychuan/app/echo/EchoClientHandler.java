package cn.fancychuan.app.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * 当被通知channel是活跃的时候向服务端发送一条消息
     * 也就是，在导服务器的连接已经建立之后被调用
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 每当接收到数据时都会调用这个方法
     * 注意：如果服务器发送了5字节，不能保证这个5个字节一次性收到，有可能第一次收到了3个字节，第二次2个，那么该方法就需要调用两次
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        System.out.println("[client]received: " + in.toString(CharsetUtil.UTF_8));
    }
}
