package com.xw.dao.distributed.service.impl;

import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.data.DistributedTaskData;
import com.xw.dao.distributed.exception.UnSupportException;
import com.xw.dao.distributed.service.DistributedTaskDataService;
import com.xw.common.paginator.Pagination;

import java.util.List;

/**
 * Created by huangdongbin on 2018/4/16.
 */
public  class DefaultDistributedTaskDataServiceImpl implements DistributedTaskDataService {


    @Override
    public int saveTaskData(BusinessEnum businessEnum, long businessId) throws UnSupportException {
        throw new UnSupportException();
    }

    @Override
    public Pagination<DistributedTaskData> listTask(int pageNo, int pageSize, long exeTime, String ip)throws UnSupportException{
        throw new UnSupportException();
    }

    @Override
    public int backupTaskData(List<Integer> taskDataIds)  throws UnSupportException {
        throw new UnSupportException();
    }

    @Override
    public List<DistributedTaskData> updateAndListTask(int limitSize, String ip)  throws UnSupportException {
        throw new UnSupportException();
    }
}
