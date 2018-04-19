package com.xw.dao.systemTask.impl;

import com.common.generated.tables.records.TaskConfigRecord;
import com.xw.dao.systemTask.SystemTaskDataDao;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static com.common.generated.Tables.*;

/**
 * Created by huangdongbin on 2018/4/11.
 */
@Repository
public class SystemTaskDataDaoImpl implements SystemTaskDataDao {

    @Autowired
    private DSLContext dslContext;

    @Override
    public List<TaskConfigRecord> list() {
        Result<TaskConfigRecord> records = dslContext.fetch(TASK_CONFIG,TASK_CONFIG.STATUS.eq((byte)0));
        return records;
    }

    @Override
    public List<TaskConfigRecord> queryTaskData(long startTime,long endTime) {
        Condition condition = TASK_CONFIG.ID.gt(0l);
        if (startTime > 0)
            condition = condition.and(TASK_CONFIG.UPDATE_TIME.ge(new Timestamp(startTime)));
        if (endTime > 0)
            condition = condition.and(TASK_CONFIG.UPDATE_TIME.lt(new Timestamp(endTime)));
        Result<TaskConfigRecord> records = dslContext.fetch(TASK_CONFIG,condition);
        return records;
    }
}
