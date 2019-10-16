package com.xw.task.init;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.utils.spring.ApplicationContextUtils;
import com.xw.task.common.QuartzScheduleMgr;
import com.xw.task.server.SystemTaskDataService;
import com.xw.task.task.AbstractTask;
import com.xw.task.task.SystemTaskData;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

/**
 * 任务初始化
 * Created by huangdongbin on 2018/4/11
 */
public class TaskInitContainer implements InitializingBean,ApplicationListener {
    private static Logger logger = LoggerFactory.getLogger(TaskInitContainer.class);

    private SystemTaskDataService taskDataService;
    private Object object = new Object();
    private volatile boolean initFlag = false;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (ContextRefreshedEvent.class.getName().equals(event.getClass().getName())) {
            synchronized (object) {
                if (!initFlag)
                    initTask();
                initFlag = true;
            }
        }
    }

    private void initTask() {
        logger.info("-------------start init task---------------");
        try {
            List<SystemTaskData> list = taskDataService.list();
            Scheduler scheduler = QuartzScheduleMgr.getInstanceScheduler();
            logger.info("------SystemTaskData list------{}",new ObjectMapper().writeValueAsString(list));
            if (list == null || list.size() == 0) {
                throw new RuntimeException("init task list is null");
            }
            for (SystemTaskData task : list) {
                try {
                    Class<? extends AbstractTask> jobClass = (Class<? extends AbstractTask>) Class.forName(task.getFullClass());
                    JobDetail job = JobBuilder.newJob(jobClass).withIdentity(task.getName(), task.getGroupName())
                            .usingJobData("biz_code", task.getBizCode()).build();
                    Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getTriggerName(), task.getGroupName())
                            .startNow()
                            .withSchedule(CronScheduleBuilder.cronSchedule(task.getCorn()))
                            .build();
                    scheduler.scheduleJob(job, trigger);//设置调度相关的Job
                }catch (Exception e){
                    logger.error("", e);
                }
            }
            scheduler.start();
        } catch (Exception e) {
            logger.error("", e);
        }
        logger.info("-------------end init task---------------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (ApplicationContextUtils.applicationContext == null) {
            throw new RuntimeException("must config ApplicationContextUtils");
        }
        taskDataService = (SystemTaskDataService)ApplicationContextUtils.applicationContext.getBean(SystemTaskDataService.class);
    }

//    private Map<String,Object> build(String fullClassName,Class<? extends Job> jobClass) {
//        Constructor<?>[] constructors = jobClass.getConstructors();
//        if (constructors.length > 1)
//            throw new RuntimeException("fullClassName is " + fullClassName + " has Multiple constructor");
//        Map<String,Object> map = new HashMap<>();
//        Constructor<?> constructor = constructors[0];
//        Type[]  types = constructor.getGenericParameterTypes();
//        for (Type type:types){
//            String typeName = type.getTypeName();
//            try {
//                if (typeName!=null || !"".equals(typeName) || !"java".equals(typeName)) {
//                    if (ApplicationContextUtils.applicationContext == null){
//                        throw new RuntimeException("must config ApplicationContextUtils");
//                    }
//                    Object object = ApplicationContextUtils.applicationContext.getBean(Class.forName(typeName));
//                    map.put(typeName,object);
//                }
//            } catch (ClassNotFoundException e) {
//                logger.error("", e);
//            }
//        }
//        return map;
//    }
}
