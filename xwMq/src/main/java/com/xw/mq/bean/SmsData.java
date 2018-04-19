package com.xw.mq.bean;

import org.apache.commons.net.ntp.TimeStamp;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public class SmsData {

    private String from;
    private String to;
    private String context;
    private TimeStamp sendTime;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public TimeStamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(TimeStamp sendTime) {
        this.sendTime = sendTime;
    }
}
