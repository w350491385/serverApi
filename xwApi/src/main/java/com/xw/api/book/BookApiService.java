package com.xw.api.book;

import com.xw.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;

public class BookApiService {

    @Autowired
    private BookService bookService;

    public int add(int userId,int type, String desc) {
        return bookService.addBook(desc,type,userId);
    }
}
