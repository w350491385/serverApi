package com.xw.task;

import com.sun.jmx.snmp.Timestamp;
import com.xw.task.server.SystemTaskDataService;
import com.xw.task.task.AbstractTask;
import com.xw.service.systemTask.impl.SystemTaskDataServerImpl;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class DistributedSendTask extends AbstractTask {

    @Autowired
    private SystemTaskDataService taskDataServer;

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("-----------------------------------"+new Timestamp(System.currentTimeMillis()));
    }

}
