package com.xw.dao.ack;

import com.common.generated.tables.records.AckTaskDataRecord;
import com.xw.dao.distributed.AckTaskDataStatusEnum;
import com.xw.dao.distributed.BusinessEnum;
import com.xw.common.paginator.Pagination;

import java.util.List;

/**
 * Created by huangdongbin on 2018/4/16.
 */
public interface AckTaskDataDao {


    int saveAckTask(BusinessEnum businessEnum,long businessId,String url);


    Pagination<AckTaskDataRecord> listAckTask(int pageNo, int pageSize,
                                              AckTaskDataStatusEnum ackTaskDataStatusEnum, long exeTime, String ip);
    /**
     * 删除主表数据
     * @param ackTaskDataIds
     * @return
     */
    int deleteAckTaskData(List<Integer> ackTaskDataIds);

    /**
     * 把主表数据移到备份表
     * @param ackTaskDataIds
     * @return
     */
    int backupAckTaskData(List<Integer> ackTaskDataIds);

    /**
     *
     * @param limitSize
     * @param ip
     * @param oldAckTaskDataStatusEnum
     * @param newAckTaskDataStatusEnum
     * @return
     */
    List<AckTaskDataRecord> updateAndListAckTask(int limitSize,String ip,
                                                 AckTaskDataStatusEnum oldAckTaskDataStatusEnum,
                                                 AckTaskDataStatusEnum newAckTaskDataStatusEnum);

}
