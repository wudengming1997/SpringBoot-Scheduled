package com.wdm.schedule.service;


import com.wdm.schedule.pojo.TaskModule;

import java.util.List;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.service
 * @Classname TaskService
 * @Description 任务
 * @Date 2022/8/26 2:47 下午
 * @Created by wudengming
 * @Version: 1.0
 */
public interface TaskService {
    /**
     * @return java.util.List<com.wdm.schedule.pojo.TaskModule>
     * @Author wudengming
     * @Description 获取所有任务
     * @Date 2022/8/26
     * @Param []
     **/
    List<TaskModule> getAllTask();
}
