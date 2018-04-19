package com.xw;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class MethodData {
    private boolean check;//true:检查登录session
    private String methodName;//方法名

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
