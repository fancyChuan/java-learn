package reflect.proxy;

/**
 * 传统的静态代理模式
 *
 * 这种方式更好的一种做法是使用代理工厂类来获取代理对象
 *
 * 弊端： 一个代理类只为一个接口服务
 */
public class StaticProxy {
    public static void main(String[] args) {
        MessageProxy msg = new MessageProxy(new MessageImpl());
        msg.send();
    }
}


interface IMessage { // 面向接口编程，传统的代理模式必须有接口
    public void send();
}


class MessageImpl implements IMessage {

    @Override
    public void send() {
        System.out.println("【消息实现类】发送消息");
    }
}


class MessageProxy implements IMessage {
    private IMessage message; // 代理对象，一定是业务接口实例

    public MessageProxy(IMessage message) {
        this.message = message;
    }

    public boolean connect() {
        System.out.println("【消息代理】进行消息发送通道的连接");
        return true;
    }

    @Override
    public void send() {
        if (this.connect()) {
            message.send();
        }
    }
}