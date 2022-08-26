package com.wdm.schedule.jobs;

import com.wdm.schedule.conf.SchedulingConfigure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.jobs
 * @Classname TestTaskOne
 * @Description 测试任务2
 * @Date 2022/8/26 3:25 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@Component
public class TestTaskTwo implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TestTaskTwo.class);

    @Override
    public void run() {
        logger.info("-------------------------------------------测试任务二");
    }
}
