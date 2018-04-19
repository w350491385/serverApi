package com.xw.dao.distributed.service.impl;

import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.data.AckTaskData;
import com.xw.dao.distributed.exception.UnSupportException;
import com.xw.dao.distributed.service.AckTaskDataService;
import com.xw.common.paginator.Pagination;

import java.util.List;

/**
 * Created by huangdongbin on 2018/4/16.
 */
public abstract class DefaultAckTaskDataServiceImpl implements AckTaskDataService {

    @Override
    public Pagination<AckTaskData> list(int pageNo, int pageSize, long exeTime, String ip) throws UnSupportException {
        throw new UnSupportException();
    }

    @Override
    public int saveAckTaskData(BusinessEnum businessEnum, long businessId,
                               String url) throws UnSupportException {
        throw new UnSupportException();
    }

    @Override
    public List<AckTaskData> updateAndList(int limitSize, BusinessEnum businessEnum, String ip) throws UnSupportException {
        throw new UnSupportException();
    }

    @Override
    public int backupTaskData(List<Integer> ackTaskDataIds) throws UnSupportException {
        throw new UnSupportException();
    }
}
