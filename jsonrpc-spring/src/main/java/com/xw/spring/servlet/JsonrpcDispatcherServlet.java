package com.xw.spring.servlet;

import com.googlecode.jsonrpc4j.JsonRpcMultiServer;
import com.xw.spring.factory.JsonRpcMultiServerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by huangdongbin on 2018/4/10.
 */
public class JsonrpcDispatcherServlet extends HttpServlet {

    private JsonRpcMultiServer jsonRpcMultiServer = null;

    @Override
    public void init() throws ServletException {
        super.init();
        jsonRpcMultiServer = getJsonRpcMultiServer();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        jsonRpcMultiServer.handle(req,resp);
        resp.getOutputStream().flush();
    }

    private JsonRpcMultiServer getJsonRpcMultiServer(){
        return JsonRpcMultiServerFactory.getInatance().getJsonRpcServer();
    }
}
