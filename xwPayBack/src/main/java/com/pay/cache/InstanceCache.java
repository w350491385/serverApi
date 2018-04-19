package com.pay.cache;

import com.pay.DefaultServiceProcessor;
import com.utils.spring.ApplicationContextUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huangdongbin on 2018/4/18.
 */
public class InstanceCache {
    private final static Map<String,Handler> handlerMap = new ConcurrentHashMap<>(10);

    private final static String DEFAULT = "defaultServiceProcessor";

    public static void addService(Class<? extends DefaultServiceProcessor> clazz){
        String simpleName = getServiceName(clazz);
        Handler handler = handlerMap.get(simpleName);
        if (handler == null){
            ApplicationContextUtils.registerBean(clazz);
            DefaultServiceProcessor instance = ApplicationContextUtils.getBean(clazz);
            handler = new Handler(clazz,instance,clazz.getName());
        }
        handlerMap.put(simpleName,handler);
    }

    private static String getServiceName(Class<? extends DefaultServiceProcessor> clazz) {
        String simpleName = clazz.getSimpleName();
        simpleName = simpleName.substring(0,1).toLowerCase()+ simpleName.substring(1);
        return simpleName;
    }

    public static Handler getService(String serviceName){
        Handler handler = handlerMap.get(serviceName);
        serviceName = serviceName == null || "".equals(serviceName) ? DEFAULT:serviceName;
        if (handler == null){
            handler = getHandler(serviceName, handler);
        }
        handler = getDefaultHandler(handler);
        return handler;
    }

    private static Handler getDefaultHandler(Handler handler) {
        if (handler == null)
            handler = handlerMap.get(DEFAULT);
        if (handler == null)
            handler = getHandler(DEFAULT,handler);
        if (handler == null)
            throw new RuntimeException("can not get handler");
        return handler;
    }

    private static Handler getHandler(String serviceName, Handler handler) {
        DefaultServiceProcessor instance  = null;
        try {
            instance = (DefaultServiceProcessor) ApplicationContextUtils.getBean(serviceName);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (instance != null)
            handler = new Handler(instance.getClass(),instance,serviceName);
        return handler;
    }
}
