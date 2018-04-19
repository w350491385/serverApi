package com.pay.cache;

import com.pay.DefaultServiceProcessor;
import com.pay.ServiceProcessor;

/**
 * Created by huangdongbin on 2018/4/18.
 */
public class Handler {
    private  Class<?> clazz;
    private DefaultServiceProcessor instance;
    private String beanName;

    public Handler(Class<?> clazz, DefaultServiceProcessor instance, String beanName) {
        this.clazz = clazz;
        this.instance = instance;
        this.beanName = beanName;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ServiceProcessor getInstance() {
        return instance;
    }

    public void setInstance(DefaultServiceProcessor instance) {
        this.instance = instance;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

}
