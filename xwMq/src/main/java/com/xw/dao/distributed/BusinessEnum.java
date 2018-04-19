package com.xw.dao.distributed;

/**
 * Created by huangdongbin on 2018/4/15.
 */
public enum BusinessEnum {
    PAY("pay","支付业务",(byte)1);

    private String name;
    private String desc;
    private byte type;

    BusinessEnum(String name, String desc, byte type) {
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
