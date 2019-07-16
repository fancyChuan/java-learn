package reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 扩展功能的对象池工厂：
 */
public class ExtendedObjectPoolFactory {
    // 定义一个对象池，前面是hi对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    private Properties config = new Properties();

    // 创建类的实例
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance(); // 用了第一种方式创建类的实例，这需要类有默认构造器
    }

    public void init(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPool() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        for (String name : config.stringPropertyNames()) {
            if (! name.contains("%")) { // 如果key中不包含%，则表示需要调用createObject()来创建对象，比如 a=java.util.Date
                objectPool.put(name, createObject(config.getProperty(name)));
            }
        }
    }
    // 从配置文件中注入属性值
    public void initProperty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        for (String name : config.stringPropertyNames()) {
            if (name.contains("%")) { // key中包含%，表示通过set方法注入属性的的值。比如 person%Name=fancy表示给person对象的那么赋值：person.setName(fancy)
                Object target = getObject(name.split("%")[0]); // 这个已经创建了一个Person实例，我们命名为target
                String field = name.split("%")[1];

                Class<?> targetClass = target.getClass();
                // 获取到setter方法
                Method setterMethod = targetClass.getMethod("set" + field, String.class);
                // 执行setter方法
                setterMethod.invoke(target, config.getProperty(name)); // invoke中需要传入的就是执行该方法的实例对象
            }
        }
    }

    public Object getObject(String name) {
        return objectPool.get(name);
    }
}
