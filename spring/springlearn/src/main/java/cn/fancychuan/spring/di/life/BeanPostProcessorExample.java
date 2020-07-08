package cn.fancychuan.spring.di.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorExample implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessorExample"
                + "调用postProcessBeforeInitialization方法,参数【"
                + bean.getClass().getSimpleName()
                + "】【" + beanName + "】"
        );
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessorExample"
                + "调用postProcessAfterInitialization方法,参数【"
                + bean.getClass().getSimpleName()
                + "】【" + beanName + "】"
        );
        return bean;
    }
}
