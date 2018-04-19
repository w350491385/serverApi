package com.utils.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class ApplicationContextUtils implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    public static void registerBean(String beanName,Class<?> clazz){
        Assert.notNull(applicationContext,"com.utils.spring.ApplicationContextUtils.applicationContext is null");
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory)ApplicationContextUtils.applicationContext.getAutowireCapableBeanFactory();
        if (!beanFactory.containsBeanDefinition(beanName)) {
            RootBeanDefinition rootBean = (RootBeanDefinition) BeanDefinitionBuilder.rootBeanDefinition(clazz).getBeanDefinition();
            rootBean.getPropertyValues().add("id", beanName);
            beanFactory.registerBeanDefinition(beanName, rootBean);
        }
    }

    public static void registerBean(Class<?> clazz){
        registerBean(clazz.getName(),clazz);
    }
    public static Object getBean(String beanName){
        Assert.notNull(applicationContext,"com.utils.spring.ApplicationContextUtils.applicationContext is null");
        return applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> clazz){
        Assert.notNull(applicationContext,"com.utils.spring.ApplicationContextUtils.applicationContext is null");
        return applicationContext.getBean(clazz);
    }
}
