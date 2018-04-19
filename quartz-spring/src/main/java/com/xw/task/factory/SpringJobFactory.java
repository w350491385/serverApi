package com.xw.task.factory;

import com.utils.spring.ApplicationContextUtils;
import org.quartz.*;
import org.quartz.simpl.PropertySettingJobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Created by huangdongbin on 2018/4/12.
 */
public class SpringJobFactory extends PropertySettingJobFactory {

    private static Logger logger = LoggerFactory.getLogger(SpringJobFactory.class);

    private SpringJobFactory() {
    }

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        Job job = selfNnewJob(bundle, scheduler);
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(scheduler.getContext());
        jobDataMap.putAll(bundle.getJobDetail().getJobDataMap());
        jobDataMap.putAll(bundle.getTrigger().getJobDataMap());
        setBeanProps(job, jobDataMap);
        return job;
    }

    private Job selfNnewJob(TriggerFiredBundle bundle, Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class<? extends Job> jobClass = jobDetail.getJobClass();
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Producing instance of Job '" + jobDetail.getKey() +
                                "', class=" + jobClass.getName());
            }
            ApplicationContextUtils.registerBean(jobClass);
            return ApplicationContextUtils.getBean(jobClass);
        } catch (Exception e) {
            SchedulerException se = new SchedulerException(
                    "Problem instantiating class '"
                            + jobDetail.getJobClass().getName() + "'", e);
            throw se;
        }
    }

    public static SpringJobFactory getInstance() {
        return InnerClass.instance;
    }

    private static class InnerClass {
        private static final SpringJobFactory instance = new SpringJobFactory();
    }
}
