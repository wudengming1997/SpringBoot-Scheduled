package com.wdm.schedule.pojo;

import com.wdm.schedule.ScheduleApplication;
import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.pojo
 * @Classname TaskModule
 * @Description 任务数据库配置实体类
 * @Date 2022/8/26 2:52 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@Data
public class TaskModule {
    private String taskId;
    private String taskName;
    private String taskClass;
    private String taskStatus;
    private String taskCron;
    private String create;
    private Date createTime;
    private String update;
    private Date updateTime;
}
