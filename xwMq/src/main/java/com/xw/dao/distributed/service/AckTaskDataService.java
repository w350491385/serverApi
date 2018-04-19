package com.xw.dao.distributed.service;

import com.xw.dao.distributed.BusinessEnum;
import com.xw.dao.distributed.data.AckTaskData;
import com.xw.dao.distributed.exception.UnSupportException;
import com.xw.common.paginator.Pagination;

import java.util.List;

/**
 * 消息确人业务
 * Created by huangdongbin on 2018/4/16.
 */
public interface AckTaskDataService {


    /**
     * 获取(已被获取的)确认消息数据(ackTask 获取消息 对URL发送请求确认)
     * @param pageNo 页号
     * @param pageSize 每页大小
     * @param exeTime 执行时间
     * @param ip ip地址
     * @return
     * @throws UnSupportException
     */
    Pagination<AckTaskData> list(int pageNo, int pageSize, long exeTime, String ip) throws UnSupportException;

    /**
     * 保存确认消息
     * @param businessEnum 业务类型
     * @param businessId 业务id
     * @param url 请求地址
     * @return
     */
    int saveAckTaskData(BusinessEnum businessEnum,long businessId,String url) throws UnSupportException;

    /**
     * 标记并获取相应的确认信息(mq 任务标记消息 并且获取标记的消息)
     * @param limitSize 限制条数
     * @param businessEnum 业务类型
     * @param ip ip地址
     * @return
     */
    List<AckTaskData> updateAndList(int limitSize, BusinessEnum businessEnum,String ip) throws UnSupportException;


    /**
     * 消息确认成功 后转备份(ackTask 对URL发送请求确认处理成功的 转备份)
     * @param ackTaskDataIds
     * @return
     */
    int backupTaskData(List<Integer> ackTaskDataIds) throws UnSupportException;
}
