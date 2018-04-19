package com.xw.task;

import com.sun.jmx.snmp.Timestamp;
import com.xw.dao.distributed.service.AckTaskDataService;
import com.xw.task.task.AbstractTask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by huangdongbin on 2018/4/18.
 */
public class AckTask extends AbstractTask {

    @Autowired
    private AckTaskDataService ackTaskDataService;

    @Override
    protected void doExecute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("---------AckTask-------"+new Timestamp(System.currentTimeMillis()));
    }

}
