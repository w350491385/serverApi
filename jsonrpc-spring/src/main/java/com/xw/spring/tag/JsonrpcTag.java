package com.xw.spring.tag;

import com.xw.JsonrpcHandlerInvoker;
import com.xw.MethodData;
import com.xw.spring.factory.JsonRpcMultiServerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangdongbin on 2018/4/10.
 */
public class JsonrpcTag<T> implements InitializingBean {

    private String id;
    private String name;
    private String interfaceClazz;
    private Object clazz;//服务实现类
    private T ref;//对象引用
    private String serviceName;//服务名称
    private boolean check;
    private Map<String,MethodTag> methodTagMap = new HashMap<>();//方法是否检查session登录

    public Object getClazz() {
        return clazz;
    }

    public void setClazz(Object clazz) {
        this.clazz = clazz;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Map<String, MethodTag> getMethodTagMap() {
        return methodTagMap;
    }

    public void setMethodTagMap(Map<String, MethodTag> methodTagMap) {
        this.methodTagMap = methodTagMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInterfaceClazz() {
        return interfaceClazz;
    }

    public void setInterfaceClazz(String interfaceClazz) {
        this.interfaceClazz = interfaceClazz;
    }

    public T getRef() {
        return ref;
    }

    public void setRef(T ref) {
        this.ref = ref;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if ((clazz == null || "".equals(clazz)) && (ref == null || "".equals(ref)))
            throw new RuntimeException("clazz or ref must config ");
        if (serviceName == null || "".equals(serviceName))
            throw new RuntimeException("serviceName is " + serviceName);
        JsonrpcHandlerInvoker invoker = getRpcInvoker();
        if (interfaceClazz == null || "".equals(interfaceClazz))
            JsonRpcMultiServerFactory.getInatance().getJsonRpcServer().addService(serviceName,invoker);
        else
            JsonRpcMultiServerFactory.getInatance().getJsonRpcServer().addService(serviceName,invoker,Class.forName(interfaceClazz));
    }

    private JsonrpcHandlerInvoker getRpcInvoker() {
        JsonrpcHandlerInvoker invoker = new JsonrpcHandlerInvoker();
        if (clazz != null || "".equals(clazz)){
            invoker.setHandler(clazz);
        }
        if (ref != null  || "".equals(ref))
            invoker.setHandler(ref);
        invoker.setCheck(this.check);
        Map<String,MethodData> methodDataMap = new HashMap<>();
        for (Map.Entry<String,MethodTag> entry :this.methodTagMap.entrySet()){
            MethodData data = new MethodData();
            data.setCheck(entry.getValue().isCheck());
            String methodName = entry.getValue().getMethodName();
            data.setMethodName(methodName);
            methodDataMap.put(methodName,data);
        }
        invoker.setMethodDataMap(methodDataMap);
        return invoker;
    }
}
