package reflect;

public class ReflectAndFactory {

    public static void main(String[] args) {
        // TODO：如何理解下面这行代码存在的耦合问题？
        IMessage msg1 = new NetMessage(); // 直接实例化就会有耦合问题，这是因为接口的主要作用就是为不同的层提供一个操作的标准，如果直接为子类设置为接口实例化操作，就会存在耦合问题？
        // 解决方法：使用工厂设计模式 例如：MessageFactory.getInstance()
        System.out.println("=========== 静态工厂方法 ===========");
        IMessage msg2 = MessageFactory.getInstance("NetMessage");
        msg2.send();
        // 但是上面这种传统的工厂模式，在每增加一个接口实现类的时候都需要修改工厂的方法，这也是静态工厂方法的不足
        // 解决方法：通过反射实现动态工厂方法 例如：MessageFactory.getInstanceDyn()
        System.out.println("=========== 动态工厂方法 ===========");
        IMessage msg = MessageFactory.getInstanceDyn("reflect.CloudMessage");
        msg.send();
        // 那么问题又来了，但有更多的接口的时候，还是需要去修改工厂类
        // 解决方法：使用泛型+反射 例如：GenericsFactory
        System.out.println("=========== 泛型+动态工厂方法 ===========");
        IMessage genDynInstance = GenericsFactory.getInstance("reflect.NetMessage", IMessage.class);
        genDynInstance.send();
    }
}


interface IMessage {
    public void send();
}

class NetMessage implements IMessage {
    @Override
    public void send() {
        System.out.println("【发送消息】I am fancy");
    }
}

class CloudMessage implements IMessage {
    @Override
    public void send() {
        System.out.println("【云网络消息】from cloud");
    }
}

/**
 * 工厂类的核心作用是解决子类和客户端的耦合问题
 */
class MessageFactory {
    private MessageFactory() {} // 不允许被实例化，因为这个类没有产生实例化的意义

    /**
     * 这种是静态工厂方法，能够解决接口和客户端的耦合问题，
     * 但缺点在于，没新增一个接口的实现类，都需要修改工厂类的代码，
     * 这也是通过关键词new来实例化对象的缺点，它要求类一定要存在
     */
    public static IMessage getInstance(String className) {
        if ("netmessage".equalsIgnoreCase(className)) {
            return new NetMessage();
        } else if ("cloudmessage".equalsIgnoreCase(className)) {
            return new CloudMessage();
        }
        return null;
    }

    /**
     * 动态工厂方法，每新增一个接口的实现类，不需要修改工厂方法
     */
    public static IMessage getInstanceDyn(String className) {
        IMessage msg = null;
        try {
            msg = (IMessage) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }
}

/**
 * 泛型工厂类
 */
class GenericsFactory {
    private GenericsFactory() {}

    /**
     * 获取接口实例化对象
     * @param className 接口的子类命（全限定类名）
     * @param clazz     描述的是接口的类型
     * @param <T>       泛型
     * @return          如果子类存在则返回指定接口实例化对象
     */
    public static <T> T getInstance(String className, Class<T> clazz) {
        T instance = null;
        try {
            instance = (T) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}