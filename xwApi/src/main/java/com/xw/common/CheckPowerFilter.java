package com.xw.common;

import com.xw.bean.User;
import com.xw.utils.RequestContextUtils;
import com.xw.verification.PermissionAuthFilter;

/**
 * 检查是否登录
 * Created by huangdongbin on 2018/4/11.
 */
public class CheckPowerFilter implements PermissionAuthFilter {

    @Override
    public boolean checkPower(String sessionId, String method) throws Exception {
        if (sessionId != null && "XWKJxwkj1234".equals(sessionId)) {
            RequestContextUtils.set("user",new User());
            return true;
        }
        return false;
    }
}
