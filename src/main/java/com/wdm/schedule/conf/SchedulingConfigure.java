package com.wdm.schedule.conf;

import com.wdm.schedule.pojo.TaskModule;
import com.wdm.schedule.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.conf
 * @Classname SchedulingConfjgure
 * @Description TODO
 * @Date 2022/8/26 2:21 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@Configuration
@EnableScheduling
@Slf4j
public class SchedulingConfigure implements SchedulingConfigurer {
    private static Logger logger = LoggerFactory.getLogger(SchedulingConfigure.class);
    @Resource
    private TaskService taskService;

    private static List<TaskModule> list;
    private static List<TaskModule> newList;
    private static HashMap<String, TaskModule> map = new HashMap<>();
    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        scheduledTaskRegistrar = taskRegistrar;
        //使用线程池
        taskRegistrar.setScheduler(ThreadPoolConfigure.threadPoolTaskScheduler());
        list = taskService.getAllTask();

        list.forEach(taskModule -> {
            map.put(taskModule.getTaskId(), taskModule);
        });

        if (list != null) {
            for (TaskModule taskModule : list) {
                Trigger trigger = trigger(taskModule);
                Object task = task(taskModule.getTaskClass(), taskModule.getTaskStatus());
                if (task != null) {
                    taskRegistrar.addTriggerTask((Runnable) task, trigger);
                }
            }
        }
    }

    private Object task(String taskClassPath, String taskStatus) {
        logger.info("定时任务类-------------" + taskClassPath);
        Class taskClass = null;
        Object task = null;
        if ("off".equals(taskStatus)) {
            return null;
        }
        try {
            taskClass = Class.forName(taskClassPath);
        } catch (ClassNotFoundException e) {
            logger.error("未找到对应的任务:" + taskClassPath, e);
        }
        try {
            task = taskClass.newInstance();
        } catch (Exception e) {
            logger.error("创建任务实体类失败:" + taskClassPath, e);
        }
        return task;
    }

    private Trigger trigger(TaskModule task) {
        logger.info("定时任务执行周期----------------" + task.getTaskCron());
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                newList = taskService.getAllTask();
                if (newList != null && newList.size() > 0) {
                    for (TaskModule taskModule : newList) {
                        if (map.containsKey(taskModule.getTaskId())) {
                            if (taskModule.getTaskId().equals(task.getTaskId())) {
                                if (!task.getTaskCron().equals(taskModule.getTaskCron()) || !task.getTaskStatus().equals(taskModule.getTaskStatus())) {
                                    task.setTaskCron(taskModule.getTaskCron());
                                    task.setTaskStatus(taskModule.getTaskStatus());
                                }
                            }
                        } else {
                            map.put(taskModule.getTaskId(), taskModule);
                            Trigger triggerNew = trigger(task);
                            Object taskNew = task(taskModule.getTaskClass(), taskModule.getTaskStatus());
                            if (taskNew != null) {
                                scheduledTaskRegistrar.addTriggerTask((Runnable) taskNew, triggerNew);
                            }
                        }
                    }
                } else {
                    task.setTaskStatus("off");
                }
                CronTrigger trigger = new CronTrigger(task.getTaskCron());
                return trigger.nextExecutionTime(triggerContext);
            }
        };
    }

}
