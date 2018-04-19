package com.xw.task.server;

import com.xw.task.task.SystemTaskData;

import java.util.List;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public interface SystemTaskDataService {

    List<SystemTaskData> list();
    List<SystemTaskData> queryTaskData(long startTime, long endTime);

}
