package reflect.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StaticDynamicProxyApp {
    public static void main(String[] args) {
        System.out.println("******** 不使用代理 *********");
        TargetClass target = new TargetClassImpl();
        target.sayHello();

        System.out.println("******** 使用静态代理 *********");
        StaticProxyClass staticProxy = new StaticProxyClass(new TargetClassImpl());
        staticProxy.sayHello();

        System.out.println("******** 使用动态代理 *********");
        TargetClass dynamicProxy = (TargetClass) Proxy.newProxyInstance(TargetClass.class.getClassLoader(),
                new Class[]{TargetClass.class},
                new DynamicProxyClass(new TargetClassImpl()));
        dynamicProxy.sayHello();
    }
}

/**
 * DynamicProxyClass是动态代理的一部分，不是真正的代理类，这个类是辅助真正的代理类去工作的
 */
class DynamicProxyClass implements InvocationHandler {
    private Object target;
    public DynamicProxyClass(Object target) {
        this.target = target;
    }
    /**
     * 这个方法在目标类的方法被执行的时候，会被调用
     * 也就是说：我们在调用目标类的sayHello()方法时，会先执行该invoke()方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("【动态代理】目标方法调用前");
        // 真正调用目标方法的地方
        Object result = method.invoke(target, args);
        System.out.println("【动态代理】目标方法调用后");
        return result;
    }
}

class StaticProxyClass implements TargetClass {
    TargetClass target;
    public StaticProxyClass(TargetClass target) { // 通过构造器的方法和目标对象绑定在一起，也就是代理了目标对象
        this.target = target;
    }
    @Override
    public void sayHello() { // 实现接口方法，对目标对象的方法进行了功能拓展
        System.out.println("【静态代理】方法调用前");
        target.sayHello();
        System.out.println("【静态代理】方法调用后");
    }
}


interface TargetClass {
    public void sayHello();
}

class TargetClassImpl implements TargetClass {
    @Override
    public void sayHello() {
        System.out.println("Hello, I'm fine~");
    }
}