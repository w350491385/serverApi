package com.pay.dispatcher;

import com.pay.DefaultServiceProcessor;
import com.pay.cache.Handler;
import com.pay.cache.InstanceCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huangdongbin on 2018/4/18.
 */
public class PayBackServiceDispatcher extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int length = req.getContextPath().length();
        String serviceName = req.getRequestURI().substring(length);
        if (serviceName.startsWith("/")){
            serviceName = serviceName.substring(1);
        }
        Handler handler = InstanceCache.getService(serviceName);
        handler.getInstance().exe(req, resp);
    }
}
