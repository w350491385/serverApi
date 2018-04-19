package com.xw.verification;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public interface PermissionAuthFilter {

    boolean checkPower(String sessionid,String method) throws Exception;
}
