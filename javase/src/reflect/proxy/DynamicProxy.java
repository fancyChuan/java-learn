package reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class DynamicProxy {
    public static void main(String[] args) {
        MLDNProxy proxy = new MLDNProxy();
        IMessage instance = (IMessage) proxy.bind(new MessageImpl());
        instance.send();
    }
}


class MLDNProxy implements InvocationHandler {
    private Object target; // 真是业务对象

    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public boolean connect() {
        System.out.println("【消息代理】进行消息发送通道的连接");
        return true;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("【动态代理】执行方法：" + method);
        Object result = null;
        if (this.connect()) {
            result = method.invoke(this.target, args);
        }
        return result;
    }
}