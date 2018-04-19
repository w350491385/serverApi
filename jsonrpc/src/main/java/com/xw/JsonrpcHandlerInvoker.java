package com.xw;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class JsonrpcHandlerInvoker {

    private Object handler;//
    private boolean check;////true:检查登录session
    private Map<String,MethodData> methodDataMap = new HashMap<>();

    public Object getHandler() {
        return handler;
    }

    public void setHandler(Object handler) {
        this.handler = handler;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Map<String, MethodData> getMethodDataMap() {
        return methodDataMap;
    }

    public void setMethodDataMap(Map<String, MethodData> methodDataMap) {
        this.methodDataMap = methodDataMap;
    }

    /**
     * 是否需要验证session 登录
     * @param methodName true：需要;false：不需要
     * @return
     */
    public boolean isNeedCheck(String methodName){
        MethodData methodData = methodDataMap.get(methodName);
        if (methodData == null)
            return this.check;
        return methodData.isCheck();
    }
}
