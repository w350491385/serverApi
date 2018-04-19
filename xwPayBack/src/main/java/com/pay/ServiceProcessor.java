package com.pay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by huangdongbin on 2018/4/18.
 */
public interface ServiceProcessor {

    void exe(HttpServletRequest  request, HttpServletResponse response);


}
