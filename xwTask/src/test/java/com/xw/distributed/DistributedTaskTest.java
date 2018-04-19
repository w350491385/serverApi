package com.xw.distributed;

import com.xw.base.TestBase;
import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.data.DistributedTaskData;
import com.xw.dao.distributed.service.DistributedTaskDataService;
import com.xw.common.paginator.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdongbin on 2018/4/17.
 */
public class DistributedTaskTest extends TestBase {

    @Autowired
    private DistributedTaskDataService distributedTaskDataService;

    @Test
    public void  testSave(){
        int num = distributedTaskDataService.saveTaskData(BusinessEnum.PAY,5);
        System.out.println("testSave --- num is " + num);
    }

    @Test
    public void testUpdateAndListTask() throws UnknownHostException {
        int limitSize = 10;
        String ip = InetAddress.getLocalHost().getHostAddress();
        List<DistributedTaskData> list = distributedTaskDataService.updateAndListTask(limitSize,ip);
        System.out.println("testUpdateAndListTask -- list's size is " + list.size());
    }

    @Test
    public void testListTask() throws UnknownHostException {
        int pageNo = 1;
        int pageSize = 10;
        long exeTime = System.currentTimeMillis();
        String ip = InetAddress.getLocalHost().getHostAddress();
        Pagination<DistributedTaskData> page = distributedTaskDataService.listTask(pageNo,pageSize,exeTime,ip);
        System.out.println("testListTask --- totalCount is " + page.getTotalCount());
    }

    @Test
    public void testBackupTaskData(){
        List<Integer> taskIds = new ArrayList<>();
        taskIds.add(1);
        int num = distributedTaskDataService.backupTaskData(taskIds);
        System.out.println("testBackupTaskData --- num is " + num);
    }
}
