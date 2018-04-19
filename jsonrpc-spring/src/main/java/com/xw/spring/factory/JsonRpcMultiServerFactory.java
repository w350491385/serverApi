package com.xw.spring.factory;

import com.googlecode.jsonrpc4j.*;
import com.xw.spring.exception.ModelExceptionResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huangdongbin on 2018/4/10.
 */
public class JsonRpcMultiServerFactory implements ApplicationListener, InitializingBean, BeanFactoryAware {
    private static Logger logger = LoggerFactory.getLogger(JsonRpcMultiServerFactory.class);

    private static JsonRpcMultiServer jsonRpcMultiServer = null;
    private ReentrantLock lock = new ReentrantLock();
    private ConfigurableListableBeanFactory beanFactory;
    private ErrorResolver instance;

    private JsonRpcMultiServerFactory() {
    }

    public static JsonRpcMultiServerFactory getInatance() {
        return InnerClass.Instance;
    }

    public JsonRpcMultiServer getJsonRpcServer() {
        try {
            lock.lock();
            if (jsonRpcMultiServer == null) {
                jsonRpcMultiServer = new JsonRpcMultiServer();
                jsonRpcMultiServer.setSeparator('_');
            }
        } finally {
            lock.unlock();
        }
        return jsonRpcMultiServer;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (ContextRefreshedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            if (instance == null)
                instance = ModelExceptionResolver.INSTANCE;
            ErrorResolver xwErrorResolver = new MultipleErrorResolver(
                    instance,
                    AnnotationsErrorResolver.INSTANCE,
                    DefaultErrorResolver.INSTANCE
            );
            getJsonRpcServer().setErrorResolver(xwErrorResolver);
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] errorResolvers = beanFactory.getBeanNamesForType(ErrorResolver.class, true, false);
        if (errorResolvers == null || errorResolvers.length == 0) {
            logger.warn("not config ErrorResolver");
            return;
        }
        if (errorResolvers != null && errorResolvers.length > 1) {
            throw new RuntimeException("Multi ErrorResolver");
        }
        String beanName = errorResolvers[0];
        if (beanName != null && !"".equals(beanName) && beanFactory.isTypeMatch(beanName, ErrorResolver.class)) {
            instance = beanFactory.getBean(beanName, ErrorResolver.class);
        } else {
            logger.warn(beanName + " not match type");
        }
    }

    private static class InnerClass {
        private final static JsonRpcMultiServerFactory Instance = new JsonRpcMultiServerFactory();
    }
}
