package com.xw.dao.distributed.service;

import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.data.DistributedTaskData;
import com.xw.dao.distributed.exception.UnSupportException;
import com.xw.common.paginator.Pagination;

import java.util.List;

/**
 * 分布式系统 处理业务
 * Created by huangdongbin on 2018/4/15.
 */
public interface DistributedTaskDataService {

    /**
     * 保存分布式 通知任务
     * @param businessEnum
     * @param businessId
     * @return
     * @throws UnSupportException
     */
    int saveTaskData(BusinessEnum businessEnum,long businessId) throws UnSupportException;

    /**
     * 获取已被获取过的消息
     * @param pageNo 页号
     * @param pageSize 每页大小
     * @param exeTime 运行时间(查询出来的确认消息必须早于此时间)
     * @return
     * @throws UnSupportException
     */
    Pagination<DistributedTaskData> listTask(int pageNo, int pageSize, long exeTime, String ip)throws UnSupportException;

    /**
     * 接受到 ack确认消息后 进行备份
     * @param taskDataIds
     * @return
     * @throws UnSupportException
     */
    int backupTaskData(List<Integer> taskDataIds) throws UnSupportException;

    /**
     * 标记并获取相应的通知信息(mq 发送通知任务标记消息 并且获取标记的消息)
     * @param limitSize  限制条数
     * @param ip ip地址
     * @return
     * @throws UnSupportException
     */
    List<DistributedTaskData> updateAndListTask(int limitSize, String ip) throws UnSupportException;


}
