package com.wdm.schedule.service.iml;

import com.wdm.schedule.mapper.TaskMapper;
import com.wdm.schedule.pojo.TaskModule;
import com.wdm.schedule.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.service.iml
 * @Classname TaskServiceImpl
 * @Description 任务
 * @Date 2022/8/26 2:48 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    /**
     * @return java.util.List<com.wdm.schedule.pojo.TaskModule>
     * @Author wudengming
     * @Description 获取所有任务
     * @Date 2022/8/26
     * @Param []
     **/
    @Override
    public List<TaskModule> getAllTask() {
        return taskMapper.getAllTask();
    }
}
