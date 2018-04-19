package com.xw.dao.systemTask;

import com.common.generated.tables.records.TaskConfigRecord;

import java.util.List;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public interface SystemTaskDataDao {

    List<TaskConfigRecord> list();

    List<TaskConfigRecord> queryTaskData(long startTime,long endTime);
}
