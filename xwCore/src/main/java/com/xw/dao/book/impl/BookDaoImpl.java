package com.xw.dao.book.impl;

import com.xw.dao.book.BookDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

import static com.common.generated.Tables.*;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public int add(int userId, int type, String desc) {
        return dslContext.insertInto(BOOK)
                .set(BOOK.USER_ID, userId)
                .set(BOOK.TYPE, (byte) type)
                .set(BOOK.DESCRIPTION, desc)
                .set(BOOK.CREATE_TIME, new Timestamp(System.currentTimeMillis()))
                .execute();
    }
}
