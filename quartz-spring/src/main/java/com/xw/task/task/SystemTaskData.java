package com.xw.task.task;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class SystemTaskData implements Serializable{
    private long id;
    private String bizCode;
    private String fullClass;
    private Date createTime;
    private String corn;
    private String name;
    private String groupName;
    private String triggerName;
    private Date updateTime;
    private byte status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullClass() {
        return fullClass;
    }

    public void setFullClass(String fullClass) {
        this.fullClass = fullClass;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCorn() {
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
