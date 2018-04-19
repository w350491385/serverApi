package com.xw.dao.ack.impl;

import com.common.generated.tables.records.AckTaskDataRecord;
import com.xw.dao.ack.AckTaskDataDao;
import com.xw.dao.distributed.AckTaskDataStatusEnum;
import com.xw.dao.distributed.BusinessEnum;
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

import static com.common.generated.Tables.*;

/**
 * Created by huangdongbin on 2018/4/16.
 */
@Repository
public class AckTaskDataDaoImpl implements AckTaskDataDao {

    private final static int exeNum = 3;
    private final static int intervalSeconds = 40;

    @Autowired
    private DSLContext dslContext;

    @Override
    public int saveAckTask(BusinessEnum businessEnum, long businessId, String url) {
        AckTaskDataRecord ackTaskDataRecord = dslContext.newRecord(ACK_TASK_DATA);
        ackTaskDataRecord.setBusinessId(businessId);
        ackTaskDataRecord.setBusinessType(businessEnum.getType());
        ackTaskDataRecord.setStatus(AckTaskDataStatusEnum.DEFAULT.getStatus());
        ackTaskDataRecord.setExeNum(0);
        ackTaskDataRecord.setIp("");
        ackTaskDataRecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return ackTaskDataRecord.insert();
    }

    @Override
    public Pagination<AckTaskDataRecord> listAckTask(int pageNo, int pageSize, AckTaskDataStatusEnum ackTaskDataStatusEnum, long exeTime, String ip) {
        int limit = (pageNo - 1) * pageSize;
        Condition condition = ACK_TASK_DATA.STATUS.eq(ackTaskDataStatusEnum.getStatus())
                .and(ACK_TASK_DATA.IP.eq(ip)).and(ACK_TASK_DATA.NEXT_SEND_TIME.le(new Timestamp(exeTime))).and(ACK_TASK_DATA.EXE_NUM.le(exeNum));
        int totalCount = dslContext.selectCount().from(ACK_TASK_DATA).where(condition).fetchOne(0,int.class);
        Result<AckTaskDataRecord> records=dslContext.selectFrom(ACK_TASK_DATA).where(condition).limit(limit,pageSize).fetch();
        Pagination<AckTaskDataRecord> page = new PaginationImpl<>(totalCount,pageNo,pageSize);
        page.addAll(records);
        dslContext.update(ACK_TASK_DATA)
                .set(ACK_TASK_DATA.EXE_NUM,ACK_TASK_DATA.EXE_NUM.add(1))
                .set(ACK_TASK_DATA.NEXT_SEND_TIME, DSL.timestampAdd(ACK_TASK_DATA.NEXT_SEND_TIME,intervalSeconds, DatePart.SECOND))
                .where(condition).execute();
        return page;
    }

    @Override
    public int deleteAckTaskData(List<Integer> ackTaskDataIds) {
        Assert.notEmpty(ackTaskDataIds, "Cannot delete empty ackTaskDataIds.");
        Condition condition = ACK_TASK_DATA.ID.in(ackTaskDataIds).and(ACK_TASK_DATA.EXE_NUM.gt(exeNum));
        return dslContext.delete(ACK_TASK_DATA).where(condition).execute();
    }

    @Override
    public int backupAckTaskData(List<Integer> ackTaskDataIds) {
        Assert.notEmpty(ackTaskDataIds, "Cannot backup empty ackTaskDataIds.");
        int num = dslContext.insertInto(ACK_TASK_DATA_BAK,ACK_TASK_DATA_BAK.ID,ACK_TASK_DATA_BAK.BUSINESS_TYPE,ACK_TASK_DATA_BAK.BUSINESS_ID,
                ACK_TASK_DATA_BAK.STATUS,ACK_TASK_DATA_BAK.CREATE_TIME,ACK_TASK_DATA_BAK.NEXT_SEND_TIME,ACK_TASK_DATA_BAK.EXE_NUM,
                ACK_TASK_DATA_BAK.UPDATE_TIME,ACK_TASK_DATA_BAK.EXCEPTION_TEXT,ACK_TASK_DATA_BAK.IP,
                ACK_TASK_DATA_BAK.BACKUP_TIME,ACK_TASK_DATA_BAK.URL)
                .select(dslContext.select(ACK_TASK_DATA.ID,ACK_TASK_DATA.BUSINESS_TYPE,ACK_TASK_DATA.BUSINESS_ID,ACK_TASK_DATA.STATUS,
                        ACK_TASK_DATA.CREATE_TIME,ACK_TASK_DATA.NEXT_SEND_TIME,ACK_TASK_DATA.EXE_NUM,ACK_TASK_DATA.UPDATE_TIME,
                        ACK_TASK_DATA.EXCEPTION_TEXT,ACK_TASK_DATA.IP,DSL.currentTimestamp().as("backup_time"),ACK_TASK_DATA.URL)
                        .from(ACK_TASK_DATA).where(ACK_TASK_DATA.ID.in(ackTaskDataIds)).and(ACK_TASK_DATA.EXE_NUM.gt(exeNum))).execute();
        return num;
    }

    @Override
    public List<AckTaskDataRecord> updateAndListAckTask(int limitSize, String ip, AckTaskDataStatusEnum oldAckTaskDataStatusEnum, AckTaskDataStatusEnum newAckTaskDataStatusEnum) {
        // 标记ip为“”，exe_num为0，status状态为oldAckTaskDataStatusEnum
        Condition defaultCondition = ACK_TASK_DATA.IP.eq("").and(ACK_TASK_DATA.EXE_NUM.eq(0)).and(ACK_TASK_DATA.STATUS.eq(oldAckTaskDataStatusEnum.getStatus()));
        Table tempTable = dslContext.select(ACK_TASK_DATA.ID).from(ACK_TASK_DATA).where(defaultCondition).asTable("temp");
        int num = dslContext.update(ACK_TASK_DATA)
                .set(ACK_TASK_DATA.STATUS,newAckTaskDataStatusEnum.getStatus())
                .set(ACK_TASK_DATA.IP,ip)
                .set(ACK_TASK_DATA.EXE_NUM, ACK_TASK_DATA.EXE_NUM.add(1))
                .set(ACK_TASK_DATA.UPDATE_TIME,new Timestamp(System.currentTimeMillis()))
                .set(ACK_TASK_DATA.NEXT_SEND_TIME,DSL.timestampAdd(ACK_TASK_DATA.NEXT_SEND_TIME,intervalSeconds, DatePart.SECOND))
                .where(ACK_TASK_DATA.ID.in(dslContext.select(tempTable.field(ACK_TASK_DATA.ID)).from(tempTable).limit(limitSize).fetch())).execute();

        List<AckTaskDataRecord> list = new ArrayList<>();
        if (num > 0) {
            Condition condition = ACK_TASK_DATA.IP.eq(ip).and(ACK_TASK_DATA.EXE_NUM.eq(1)).and(ACK_TASK_DATA.STATUS.eq(newAckTaskDataStatusEnum.getStatus()));
            list = dslContext.selectFrom(ACK_TASK_DATA).where(condition).fetch();
        }
        return list;
    }
}
