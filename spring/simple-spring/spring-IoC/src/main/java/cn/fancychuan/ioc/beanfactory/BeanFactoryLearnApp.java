package cn.fancychuan.ioc.beanfactory;

import cn.fancychuan.ioc.Car;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.testng.annotations.Test;

import java.io.IOException;

public class BeanFactoryLearnApp {

    public static void main(String[] args) throws IOException {
        BeanFactoryLearnApp app = new BeanFactoryLearnApp();
        app.testXmlBeanFactory();
    }

    @Test
    public void testXmlBeanFactory() throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource res = resolver.getResource("classpath:cn/fancychuan/ioc/beanfactory/beans.xml");
        System.out.println(res.getURI());
        XmlBeanFactory beanFactory = new XmlBeanFactory(res);
        long startTime = System.currentTimeMillis();
        System.out.println("init bean factory: " + startTime);
        Car car = beanFactory.getBean("car1", Car.class);
        System.out.println("first get beam: " + (System.currentTimeMillis() - startTime));
        Car car2 = beanFactory.getBean("car1", Car.class);
        System.out.println("second get beam: " + (System.currentTimeMillis() - startTime));
        car2.introduce();
    }
}
