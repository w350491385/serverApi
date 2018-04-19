package com.xw.dao.activemq.impl;

import com.xw.dao.activemq.ActiveMqDao;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.common.generated.Tables.*;
/**
 * Created by huangdongbin on 2018/4/15.
 */
@Repository
public class ActiveMqDaoImpl implements ActiveMqDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public int saveMsg(String container, String msgidProd, long msgIdseq, long expidation, String msg, long priority) {
//        ActivemqMsgsRecord activemqMsgsRecord = dslContext.newRecord(ACTIVEMQ_MSGS);
//        activemqMsgsRecord.setContainer(container);
//        activemqMsgsRecord.setExpiration(expidation);
//        activemqMsgsRecord.setMsgidProd(msgidProd);
//        activemqMsgsRecord.setMsg(msg.getBytes());
//        activemqMsgsRecord.setMsgidSeq(msgIdseq);
//        activemqMsgsRecord.setPriority(priority);
//        return activemqMsgsRecord.insert();
        return 0;
    }
}

