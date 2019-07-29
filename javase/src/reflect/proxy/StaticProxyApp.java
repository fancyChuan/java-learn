package reflect.proxy;

/**
 * 传统的静态代理模式
 *
 * 这种方式更好的一种做法是使用代理工厂类来获取代理对象
 *
 * 优点： 在不需要更改目标对象（也就是接口实现类）的前提下对目标对象进行功能增强/扩展，不会污染源代码
 * 弊端： 1.一个代理类需要实现和目标对象一样的接口。如果接口增加方法，代理类也需要增加方法；
 *       2. 一个代理类只能为一个接口服务，当接口众多的时候，代理类也变多，代码很臃肿
 */
public class StaticProxyApp {
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