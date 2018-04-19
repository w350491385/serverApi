package com.xw.service.ack.impl;

import com.common.generated.tables.records.AckTaskDataRecord;
import com.xw.dao.ack.AckTaskDataDao;
import com.xw.dao.distributed.AckTaskDataStatusEnum;
import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.data.AckTaskData;
import com.xw.dao.distributed.exception.UnSupportException;
import com.xw.dao.distributed.service.AckTaskDataService;
import com.xw.dao.distributed.service.impl.DefaultAckTaskDataServiceImpl;
import com.xw.common.paginator.Pagination;
import com.xw.common.paginator.PaginationImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdongbin on 2018/4/16.
 */
@Service
public class AckTaskDataServiceImpl extends DefaultAckTaskDataServiceImpl implements AckTaskDataService {

    @Autowired
    private AckTaskDataDao ackTaskDataDao;

    @Override
    public Pagination<AckTaskData> list(int pageNo, int pageSize, long exeTime, String ip) throws UnSupportException {
        Pagination<AckTaskDataRecord> page = ackTaskDataDao.listAckTask(pageNo,pageSize, AckTaskDataStatusEnum.ACK_SEND,exeTime,ip);
        List<AckTaskData> list = buildAckTaskData(page.getObjects());
        Pagination<AckTaskData> newPage = new PaginationImpl<>(page.getTotalCount(),pageNo,pageSize);
        newPage.addAll(list);
        return newPage;
    }

    private List<AckTaskData> buildAckTaskData(List<AckTaskDataRecord> records) {
        List<AckTaskData> list = new ArrayList<>();
        for (AckTaskDataRecord record : records){
            AckTaskData data = new AckTaskData();
            BeanUtils.copyProperties(record,data);
            list.add(data);
        }
        return list;
    }

    @Override
    public int saveAckTaskData(BusinessEnum businessEnum, long businessId, String url) throws UnSupportException {
        int num = ackTaskDataDao.saveAckTask(businessEnum, businessId, url);
        return num;
    }

    @Override
    public List<AckTaskData> updateAndList(int limitSize, BusinessEnum businessEnum, String ip) throws UnSupportException {
        List<AckTaskDataRecord> list = ackTaskDataDao.updateAndListAckTask(limitSize,ip,
                AckTaskDataStatusEnum.DEFAULT,AckTaskDataStatusEnum.ACK_SEND);
        List<AckTaskData> newList = buildAckTaskData(list);
        return newList;
    }

    @Override
    public int backupTaskData(List<Integer> ackTaskDataIds) throws UnSupportException {
        ackTaskDataDao.backupAckTaskData(ackTaskDataIds);//备份
        return ackTaskDataDao.deleteAckTaskData(ackTaskDataIds);
    }
}
