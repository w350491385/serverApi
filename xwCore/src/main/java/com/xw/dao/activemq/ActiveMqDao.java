package com.xw.dao.activemq;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public interface ActiveMqDao {

    int saveMsg(String container,String msgidProd,long msgIdseq,long expidation,String msg,long priority);
}
