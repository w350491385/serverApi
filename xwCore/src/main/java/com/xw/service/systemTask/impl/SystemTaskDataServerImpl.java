package com.xw.service.systemTask.impl;

import com.common.generated.tables.records.TaskConfigRecord;
import com.xw.dao.systemTask.SystemTaskDataDao;
import com.xw.task.server.SystemTaskDataService;
import com.xw.task.task.SystemTaskData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdongbin on 2018/4/11.
 */
@Service
public class SystemTaskDataServerImpl implements SystemTaskDataService {

    @Autowired
    private SystemTaskDataDao systemTaskDataDao;

    @Override
    public List<SystemTaskData> list() {
        List<TaskConfigRecord> records = systemTaskDataDao.list();
        List<SystemTaskData> systemTaskDatas = buildTaskData(records);
        return systemTaskDatas;
    }

    @Override
    public List<SystemTaskData> queryTaskData(long startTime, long endTime) {
        List<TaskConfigRecord> records = systemTaskDataDao.queryTaskData(startTime,endTime);
        List<SystemTaskData> systemTaskDatas = buildTaskData(records);
        return systemTaskDatas;
    }

    private List<SystemTaskData> buildTaskData(List<TaskConfigRecord> records) {
        List<SystemTaskData> systemTaskDatas = new ArrayList<>();
        for (TaskConfigRecord record : records){
            SystemTaskData systemTaskData = new SystemTaskData();
            BeanUtils.copyProperties(record, systemTaskData);
            systemTaskDatas.add(systemTaskData);
        }
        return systemTaskDatas;
    }
}
