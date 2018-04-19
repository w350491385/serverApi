package com.xw.dao.distributed;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public enum MqQueueNameEnum {
    SEND_QUEUE("send_queue","消息发送队列名"),RECEIVE_QUEUE("receive_queue","消息消费会后调队列名称");

    private String queueName;
    private String desc;

    MqQueueNameEnum(String queueName, String desc) {
        this.queueName = queueName;
        this.desc = desc;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
