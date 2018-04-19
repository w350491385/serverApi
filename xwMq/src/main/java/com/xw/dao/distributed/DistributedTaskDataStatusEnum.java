package com.xw.dao.distributed;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public enum DistributedTaskDataStatusEnum {
    DEFAULT((byte)0,"默认(未处理)"),OBTAINED((byte)1,"已被获取"),FINISH((byte)2,"正常完成"),EXCEPTION((byte)3,"异常");

    private byte status;
    private String desc;

    DistributedTaskDataStatusEnum(byte status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
