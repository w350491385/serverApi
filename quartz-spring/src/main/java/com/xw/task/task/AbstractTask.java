package com.xw.task.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by huangdongbin on 2018/4/11
 */
public abstract class AbstractTask implements Job {

    private String id;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        doExecute(context);
    }
    protected abstract void doExecute(JobExecutionContext context) throws JobExecutionException;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
