package com.xw.jooq;

import com.xw.base.TestBase;
import com.xw.service.book.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JooqTest extends TestBase {

    @Autowired
    private BookService bookService;

    @Test
    public void test1() throws Exception {
        bookService.addBook("测试",1,2);
    }
}
