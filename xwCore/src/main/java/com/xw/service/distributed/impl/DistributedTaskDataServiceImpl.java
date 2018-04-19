package com.xw.service.distributed.impl;

import com.common.generated.tables.records.DistributedTaskDataRecord;
import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.DistributedTaskDataDao;
import com.xw.dao.distributed.DistributedTaskDataStatusEnum;
import com.xw.dao.distributed.data.DistributedTaskData;
import com.xw.dao.distributed.exception.UnSupportException;
import com.xw.dao.distributed.service.AckTaskDataService;
import com.xw.dao.distributed.service.DistributedTaskDataService;
import com.xw.dao.distributed.service.impl.DefaultDistributedTaskDataServiceImpl;
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
public class DistributedTaskDataServiceImpl extends DefaultDistributedTaskDataServiceImpl implements DistributedTaskDataService {

    @Autowired
    private DistributedTaskDataDao distributedTaskDataDao;

    @Autowired
    private AckTaskDataService ackTaskDataService;

    /**
     * 保存分布式 通知任务
     * @param businessEnum
     * @param businessId
     * @return
     * @throws UnSupportException
     */
   public int saveTaskData(BusinessEnum businessEnum,long businessId) throws UnSupportException{
        int num = distributedTaskDataDao.saveTaskData(businessEnum,businessId);
       ackTaskDataService.saveAckTaskData(businessEnum,businessId,"");
       return num ;
   }

    /**
     * 获取已被获取过的消息
     * @param pageNo 页号
     * @param pageSize 每页大小
     * @param exeTime 运行时间(查询出来的确认消息必须早于此时间)
     * @param ip
     * @return
     * @throws UnSupportException
     */
    public Pagination<DistributedTaskData> listTask(int pageNo, int pageSize,long exeTime,String ip) throws UnSupportException{
        Pagination<DistributedTaskDataRecord> page = distributedTaskDataDao.listTask(pageNo,pageSize,DistributedTaskDataStatusEnum.OBTAINED,exeTime,ip);
        List<DistributedTaskData> list = buildTaskData(page.getObjects());
        Pagination<DistributedTaskData> newPage = new PaginationImpl<>(page.getTotalCount(),pageNo,pageSize);
        newPage.addAll(list);
        return newPage;
    }

    private List<DistributedTaskData> buildTaskData(List<DistributedTaskDataRecord> records) {
        List<DistributedTaskData> list = new ArrayList<>();
        for (DistributedTaskDataRecord record : records){
            DistributedTaskData data = new DistributedTaskData();
            BeanUtils.copyProperties(record,data);
            list.add(data);
        }
        return list;
    }

    /**
     * 接受到 ack确认消息后 进行备份
     * @param taskDataIds
     * @return
     * @throws UnSupportException
     */
    public int backupTaskData(List<Integer> taskDataIds) throws UnSupportException{
            distributedTaskDataDao.backupTaskData(taskDataIds);//备份
        return distributedTaskDataDao.deleteTaskData(taskDataIds);
    }

    /**
     * 标记并获取相应的通知信息(mq 发送通知任务标记消息 并且获取标记的消息)
     * @param limitSize  限制条数
     * @param ip ip地址
     * @return
     * @throws UnSupportException
     */
   public List<DistributedTaskData> updateAndListTask(int limitSize, String ip) throws UnSupportException{
       List<DistributedTaskDataRecord> list = distributedTaskDataDao.updateAndListTask(limitSize,ip,
               DistributedTaskDataStatusEnum.DEFAULT,DistributedTaskDataStatusEnum.OBTAINED);
       List<DistributedTaskData> newList = buildTaskData(list);
       return newList;
   }

}
