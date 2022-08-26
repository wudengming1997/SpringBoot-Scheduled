package com.wdm.schedule.mapper;

import com.wdm.schedule.pojo.TaskModule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.mapper
 * @Classname TaskMapper
 * @Description TODO
 * @Date 2022/8/26 2:48 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@Mapper
public interface TaskMapper {

    List<TaskModule> getAllTask();
}
