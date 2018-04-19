package com.xw.dao.distributed.data;

import java.sql.Timestamp;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public class DistributedTaskData {

    private long id;//数据id
    private byte businessType;//业务类型
    private long businessId;//业务对象记录id
    private String exception;//异常数据
    private byte status;//0:默认(未处理),1:已被获取,2:正常完成,3:异常
    private int exeNum;//执行次数
    private Timestamp createTime;//创建时间
    private Timestamp nextSendTime;//下个执行时间
    private Timestamp updateTime;//修改时间
    private Timestamp ackTime;//消费回调确认时间
    private String ip;//ip地址

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getExeNum() {
        return exeNum;
    }

    public void setExeNum(int exeNum) {
        this.exeNum = exeNum;
    }

    public Timestamp getNextSendTime() {
        return nextSendTime;
    }

    public void setNextSendTime(Timestamp nextSendTime) {
        this.nextSendTime = nextSendTime;
    }

    public Timestamp getAckTime() {
        return ackTime;
    }

    public void setAckTime(Timestamp ackTime) {
        this.ackTime = ackTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getBusinessType() {
        return businessType;
    }

    public void setBusinessType(byte businessType) {
        this.businessType = businessType;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
