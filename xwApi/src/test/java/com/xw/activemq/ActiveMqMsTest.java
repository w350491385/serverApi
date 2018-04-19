package com.xw.activemq;

import com.xw.base.TestBase;
import com.xw.service.activemq.ActiveMqService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public class ActiveMqMsTest extends TestBase {

    @Autowired
    private ActiveMqService activeMqService;

    @Test
    public void  testSaveMsg(){
        for (int i = 0 ; i < 10 ; i++) {
            int num = activeMqService.saveMsg("queue://xw.sms", "ID:USER-20160301XI-616"+i+"3-1523782315779-1:1:1:1", 1l, 0l, "自定义数据", 0);
            System.out.println(" ----------- num is " + num);
        }
    }
}
