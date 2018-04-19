package com.xw.dao.distributed;

import com.common.generated.tables.records.DistributedTaskDataRecord;
import com.xw.common.paginator.Pagination;

import java.util.List;

/**
 * Created by huangdongbin on 2018/4/16.
 */
public interface DistributedTaskDataDao {

    int saveTaskData(BusinessEnum businessEnum,long businessId);

    Pagination<DistributedTaskDataRecord> listTask(int pageNo, int pageSize,
                                                   DistributedTaskDataStatusEnum distributedTaskDataStatusEnum, long exeTime, String ip);
    /**
     * 删除主表数据
     * @param taskDataIds
     * @return
     */
    int deleteTaskData(List<Integer> taskDataIds);

    /**
     * 把主表数据移到备份表
     * @param taskDataIds
     * @return
     */
    int backupTaskData(List<Integer> taskDataIds);

    List<DistributedTaskDataRecord> updateAndListTask(int limitSize,String ip,
                                                DistributedTaskDataStatusEnum oldDistributedTaskDataStatusEnum,
                                                DistributedTaskDataStatusEnum newDistributedTaskDataStatusEnum);


}
