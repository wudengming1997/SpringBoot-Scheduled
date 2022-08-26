package com.wdm.schedule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule
 * @Classname ScheduleApplication
 * @Description TODO
 * @Date 2022/8/26 2:03 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@SpringBootApplication
@MapperScan("com.wdm.schedule.mapper")
public class ScheduleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class,args);
    }
}
