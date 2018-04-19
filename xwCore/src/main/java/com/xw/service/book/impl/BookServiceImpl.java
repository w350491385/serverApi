package com.xw.service.book.impl;

import com.xw.dao.book.BookDao;
import com.xw.service.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public int addBook(String desc, int type, int userId) {
        return bookDao.add(userId,type,desc);
    }
}
