package com.xw.service.activemq.impl;

import com.xw.dao.activemq.ActiveMqDao;
import com.xw.service.activemq.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangdongbin on 2018/4/15.
 */
@Service
public class ActiveMqServiceImpl implements ActiveMqService {

    @Autowired
    private ActiveMqDao activeMqDao;
    @Override
    public int saveMsg(String container, String msgidProd, long msgIdseq, long expidation, String msg, long priority) {
        return activeMqDao.saveMsg(container,msgidProd,msgIdseq,expidation,msg,priority);
    }
}
