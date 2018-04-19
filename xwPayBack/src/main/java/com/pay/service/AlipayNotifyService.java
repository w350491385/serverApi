package com.pay.service;


import com.pay.DefaultServiceProcessor;
import com.xw.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * Created by huangdongbin on 2018/4/18.
 */
public class AlipayNotifyService extends DefaultServiceProcessor {

    @Autowired
    private BookService bookService;

    @Override
    public void exe(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(bookService + "----------------------------" + new Timestamp(System.currentTimeMillis()));
    }

}
