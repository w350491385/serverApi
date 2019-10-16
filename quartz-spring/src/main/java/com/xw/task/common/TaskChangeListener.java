package com.xw.task.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.spring.ApplicationContextUtils;
import com.xw.task.server.SystemTaskDataService;
import com.xw.task.task.AbstractTask;
import com.xw.task.task.SystemTaskData;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class TaskChangeListener implements ApplicationListener,DisposableBean {
    private static Logger logger = LoggerFactory.getLogger(TaskChangeListener.class);
    private ScheduledExecutorService listenerPool = Executors.newScheduledThreadPool(1);
    private long initialDelay = 120;
    private long delay = 30;
    private volatile boolean runListenerFlag = false;//监听器线程 任务是否在运行，false:没有；true:在运行
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        final Long[] currentTime = {System.currentTimeMillis()};//启动初始化过
        if (ContextRefreshedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            listenerPool.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    long tempCurrentTime = System.currentTimeMillis();
                    if (ApplicationContextUtils.applicationContext != null && !runListenerFlag) {
                        runListenerFlag = true;
                        SystemTaskDataService taskDataService = (SystemTaskDataService) ApplicationContextUtils.applicationContext.getBean(SystemTaskDataService.class);
                        List<SystemTaskData> list = taskDataService.queryTaskData(currentTime[0],tempCurrentTime);//不包含结尾时间[startTime,endTime)
                        int size = list != null && list.size() > 0 ? list.size() : 0 ;
                        try {
                            logger.info("taskChangeListener query task data`s size is {} ,change list {}" , size,new ObjectMapper().writeValueAsString(list));
                        } catch (JsonProcessingException e) {
                            logger.error("",e);
                        }
                        if (size > 0){
                            changeTask(list);
                        }
                        currentTime[0] = tempCurrentTime;
                        runListenerFlag = false;
                    }else {
                        logger.warn("listener task_config change,you must configure com.xw.task.common.ApplicationContextUtils");
                    }
                }
            },initialDelay,delay, TimeUnit.SECONDS);//延迟120秒执行，之后每个30秒查询一次数据库
        }
    }

    private void changeTask(List<SystemTaskData> list) {
        Scheduler scheduler = QuartzScheduleMgr.getInstanceScheduler();
        for (SystemTaskData systemTaskData : list){
            logger.info("fullclass is {},corn is {},status is {}", systemTaskData.getFullClass(), systemTaskData.getCorn(), systemTaskData.getStatus());
            TriggerKey triggerKey = new TriggerKey(systemTaskData.getGroupName(), systemTaskData.getGroupName());
            JobKey jobKey = new JobKey(systemTaskData.getName(), systemTaskData.getGroupName());
            if (systemTaskData.getStatus() == 0){//正常运行
                runJob(scheduler, systemTaskData, triggerKey, jobKey);
            }else if (systemTaskData.getStatus() == 1){//删除 job
                delJob(scheduler, jobKey);
            }else if (systemTaskData.getStatus() == 2){//停止job运行
                pauseJob(scheduler, jobKey);
            }else{
                logger.info("{} status is error", systemTaskData.getFullClass());
            }
        }
    }

    private void pauseJob(Scheduler scheduler, JobKey jobKey) {
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("",e);
        }
    }

    private void delJob(Scheduler scheduler, JobKey jobKey) {
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("",e);
        }
    }

    private void runJob(Scheduler scheduler, SystemTaskData systemTaskData, TriggerKey triggerKey, JobKey jobKey) {
        try {
            if (scheduler.getJobDetail(jobKey) != null) {//当job存在时,刪除之前的。
                scheduler.deleteJob(jobKey);
            }
            Class<? extends AbstractTask> jobClass = (Class<? extends AbstractTask>) Class.forName(systemTaskData.getFullClass());
            JobDetail job = JobBuilder.newJob(jobClass).withIdentity(systemTaskData.getName(), systemTaskData.getGroupName())
                    .usingJobData("biz_code", systemTaskData.getBizCode()).build();
            Trigger newTrigger = TriggerBuilder.newTrigger().withIdentity(systemTaskData.getTriggerName(), systemTaskData.getGroupName())
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(systemTaskData.getCorn()))
                    .build();
            scheduler.scheduleJob(job, newTrigger);//设置调度相关的Job
        } catch (SchedulerException |ClassNotFoundException e) {
            logger.error("",e);
        }
    }

    @Override
    public void destroy() throws Exception {
        listenerPool.shutdown();
    }

    public void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
}
