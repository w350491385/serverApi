package com.xw.dao.distributed.impl;

import com.common.generated.tables.records.DistributedTaskDataRecord;
import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.DistributedTaskDataDao;
import com.xw.dao.distributed.DistributedTaskDataStatusEnum;
import com.xw.common.paginator.Pagination;
import com.xw.common.paginator.PaginationImpl;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import  static com.common.generated.Tables.*;

/**
 * Created by huangdongbin on 2018/4/16.
 */
@Repository
public class DistributedTaskDataDaoImpl implements DistributedTaskDataDao {

    private final static int exeNum = 3;
    private final static int intervalSeconds = 40;

    @Autowired
    private DSLContext dslContext;

    @Override
    public int saveTaskData(BusinessEnum businessEnum,long businessId) {
        DistributedTaskDataRecord distributedTaskDataRecord = dslContext.newRecord(DISTRIBUTED_TASK_DATA);
        distributedTaskDataRecord.setBusinessId(businessId);
        distributedTaskDataRecord.setBusinessType(businessEnum.getType());
        distributedTaskDataRecord.setStatus(DistributedTaskDataStatusEnum.DEFAULT.getStatus());
        distributedTaskDataRecord.setExeNum(0);
        distributedTaskDataRecord.setIp("");
        distributedTaskDataRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return distributedTaskDataRecord.insert();
    }

    /**
     * 获取ip下exeTime之前 相应状态的通知消息
     * @param pageNo
     * @param pageSize
     * @param distributedTaskDataStatusEnum
     * @param exeTime
     * @param ip
     * @return
     */
    @Override
    public Pagination<DistributedTaskDataRecord> listTask(int pageNo, int pageSize,DistributedTaskDataStatusEnum distributedTaskDataStatusEnum, long exeTime, String ip) {
        int limit = (pageNo - 1) * pageSize;
        Condition condition = DISTRIBUTED_TASK_DATA.STATUS.eq(distributedTaskDataStatusEnum.getStatus())
                .and(DISTRIBUTED_TASK_DATA.IP.eq(ip)).and(DISTRIBUTED_TASK_DATA.NEXT_SEND_TIME.le(new Timestamp(exeTime))).and(DISTRIBUTED_TASK_DATA.EXE_NUM.le(exeNum));
        int totalCount = dslContext.selectCount().from(DISTRIBUTED_TASK_DATA).where(condition).fetchOne(0,int.class);
        Result<DistributedTaskDataRecord> records=dslContext.selectFrom(DISTRIBUTED_TASK_DATA).where(condition).limit(limit,pageSize).fetch();
        Pagination<DistributedTaskDataRecord> page = new PaginationImpl<>(totalCount,pageNo,pageSize);
        page.addAll(records);
        dslContext.update(DISTRIBUTED_TASK_DATA)
                .set(DISTRIBUTED_TASK_DATA.EXE_NUM,DISTRIBUTED_TASK_DATA.EXE_NUM.add(1))
                .set(DISTRIBUTED_TASK_DATA.NEXT_SEND_TIME,DSL.timestampAdd(DISTRIBUTED_TASK_DATA.NEXT_SEND_TIME,intervalSeconds, DatePart.SECOND))
                .where(condition).execute();
        return page;
    }

    @Override
    public int deleteTaskData(List<Integer> taskDataIds) {
        Assert.notEmpty(taskDataIds, "Cannot delete empty taskDataIds.");
        Condition condition = DISTRIBUTED_TASK_DATA.ID.in(taskDataIds).and(DISTRIBUTED_TASK_DATA.EXE_NUM.gt(exeNum));
        return dslContext.delete(DISTRIBUTED_TASK_DATA).where(condition).execute();
    }

    @Override
    public int backupTaskData(List<Integer> taskDataIds) {
        Assert.notEmpty(taskDataIds, "Cannot backup empty taskDataIds.");
        int num = dslContext.insertInto(DISTRIBUTED_TASK_DATA_BAK,DISTRIBUTED_TASK_DATA_BAK.ID,DISTRIBUTED_TASK_DATA_BAK.BUSINESS_TYPE,DISTRIBUTED_TASK_DATA_BAK.BUSINESS_ID,
                DISTRIBUTED_TASK_DATA_BAK.STATUS,DISTRIBUTED_TASK_DATA_BAK.CREATE_TIME,DISTRIBUTED_TASK_DATA_BAK.NEXT_SEND_TIME,DISTRIBUTED_TASK_DATA_BAK.EXE_NUM,
                DISTRIBUTED_TASK_DATA_BAK.UPDATE_TIME,DISTRIBUTED_TASK_DATA_BAK.ACK_TIME,DISTRIBUTED_TASK_DATA_BAK.EXCEPTION_TEXT,DISTRIBUTED_TASK_DATA_BAK.IP,
                DISTRIBUTED_TASK_DATA_BAK.BACKUP_TIME)
                .select(dslContext.select(DISTRIBUTED_TASK_DATA.ID,DISTRIBUTED_TASK_DATA.BUSINESS_TYPE,DISTRIBUTED_TASK_DATA.BUSINESS_ID,DISTRIBUTED_TASK_DATA.STATUS,
                        DISTRIBUTED_TASK_DATA.CREATE_TIME,DISTRIBUTED_TASK_DATA.NEXT_SEND_TIME,DISTRIBUTED_TASK_DATA.EXE_NUM,DISTRIBUTED_TASK_DATA.UPDATE_TIME,
                        DISTRIBUTED_TASK_DATA.ACK_TIME,DISTRIBUTED_TASK_DATA.EXCEPTION_TEXT,DISTRIBUTED_TASK_DATA.IP,DSL.currentTimestamp().as("backup_time"))
                        .from(DISTRIBUTED_TASK_DATA).where(DISTRIBUTED_TASK_DATA.ID.in(taskDataIds)).and(DISTRIBUTED_TASK_DATA.EXE_NUM.gt(exeNum))).execute();
        return num;
    }

    /**
     * 标记并获取通知消息
     * @param limitSize
     * @param ip
     * @param oldDistributedTaskDataStatusEnum
     * @param newDistributedTaskDataStatusEnum
     * @return
     */
    @Override
    public List<DistributedTaskDataRecord> updateAndListTask(int limitSize,String ip,DistributedTaskDataStatusEnum oldDistributedTaskDataStatusEnum,
                                                       DistributedTaskDataStatusEnum newDistributedTaskDataStatusEnum) {
        // 标记ip为“”，exe_num为0，status状态为oldDistributedTaskDataStatusEnum
        Condition defaultCondition = DISTRIBUTED_TASK_DATA.IP.eq("").and(DISTRIBUTED_TASK_DATA.EXE_NUM.eq(0)).and(DISTRIBUTED_TASK_DATA.STATUS.eq(oldDistributedTaskDataStatusEnum.getStatus()));
        Table tempTable = dslContext.select(DISTRIBUTED_TASK_DATA.ID).from(DISTRIBUTED_TASK_DATA).where(defaultCondition).asTable("temp");
        int num = dslContext.update(DISTRIBUTED_TASK_DATA)
                .set(DISTRIBUTED_TASK_DATA.STATUS,newDistributedTaskDataStatusEnum.getStatus())
                .set(DISTRIBUTED_TASK_DATA.IP,ip)
                .set(DISTRIBUTED_TASK_DATA.EXE_NUM, DISTRIBUTED_TASK_DATA.EXE_NUM.add(1))
                .set(DISTRIBUTED_TASK_DATA.UPDATE_TIME,new Timestamp(System.currentTimeMillis()))
                .set(DISTRIBUTED_TASK_DATA.NEXT_SEND_TIME,DSL.timestampAdd(DISTRIBUTED_TASK_DATA.NEXT_SEND_TIME,intervalSeconds, DatePart.SECOND))
        .where(DISTRIBUTED_TASK_DATA.ID.in(dslContext.select(tempTable.field(DISTRIBUTED_TASK_DATA.ID)).from(tempTable).limit(limitSize).fetch())).execute();

        List<DistributedTaskDataRecord> list = new ArrayList<>();
        if (num > 0) {
            Condition condition = DISTRIBUTED_TASK_DATA.IP.eq(ip).and(DISTRIBUTED_TASK_DATA.EXE_NUM.eq(1)).and(DISTRIBUTED_TASK_DATA.STATUS.eq(newDistributedTaskDataStatusEnum.getStatus()));
            list = dslContext.selectFrom(DISTRIBUTED_TASK_DATA).where(condition).fetch();
        }
        return list;
    }

}
