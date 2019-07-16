package reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 对象池工厂：用来从配置文件中读取类名并实例化出来该类的对象
 */
public class ObjectPoolFactory {
    // 定义一个对象池，前面是hi对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    // 创建类的实例
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance(); // 用了第一种方式创建类的实例，这需要类有默认构造器
    }

    public void initPool(String fileName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            Properties props = new Properties();
            props.load(fis);
            for (String name : props.stringPropertyNames()) {
                objectPool.put(name, createObject(props.getProperty(name)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getObject(String name) {
        return objectPool.get(name);
    }
}
