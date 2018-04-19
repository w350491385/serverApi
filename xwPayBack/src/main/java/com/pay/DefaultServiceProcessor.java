package com.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by huangdongbin on 2018/4/19.
 */
public class DefaultServiceProcessor implements ServiceProcessor {

    private static Logger logger = LoggerFactory.getLogger(DefaultServiceProcessor.class);

    private String id;

    @Override
    public void exe(HttpServletRequest request, HttpServletResponse response) {
        try (OutputStream outputStream = response.getOutputStream();
             PrintWriter writer = new PrintWriter(outputStream);) {
             response.setContentType("text/html");
             writer.write("serviceName " + request.getRequestURI() + " is null");
        }catch (Exception e){
            logger.error("",e);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
