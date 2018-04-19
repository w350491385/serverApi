package com.xw.activemq;

import com.xw.base.TestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public class ActivemqTest extends TestBase {

    @Autowired
    private JmsTemplate smsJmsTemplate;

    @Test
    public void testSendMs(){
        smsJmsTemplate.convertAndSend("你好啊");
    }

    @Test
    public void testReceiveMsg(){
        String message = null;
        while(( message= (String) smsJmsTemplate.receiveAndConvert()) != null){
            System.out.println("----------------收到消息------" + message);
            message = null;
        }
    }
}
