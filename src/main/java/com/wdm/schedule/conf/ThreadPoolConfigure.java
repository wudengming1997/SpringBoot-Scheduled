package com.wdm.schedule.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @BelongsProject: SpringBoot-Scheduled
 * @BelongsPackage: com.wdm.schedule.conf
 * @Classname ThreadPoolConfigure
 * @Description TODO
 * @Date 2022/8/26 2:36 下午
 * @Created by wudengming
 * @Version: 1.0
 */
@Configuration
@EnableAsync
public class ThreadPoolConfigure {
    private static volatile ThreadPoolTaskScheduler threadPool;

    @Bean("threadPoolTaskScheduler")
    public static Executor threadPoolTaskScheduler() {
        if(threadPool == null) {
            synchronized (ThreadPoolTaskScheduler.class) {
                threadPool = new ThreadPoolTaskScheduler();
                // 最大线程数
                threadPool.setPoolSize(10);
                threadPool.setThreadNamePrefix("task-job-");

                // 线程空闲时间
                threadPool.setAwaitTerminationSeconds(60);
                // 该方法用来设置 线程池关闭 的时候 等待 所有任务都完成后，再继续 销毁 其他的 Bean，
                // 这样这些 异步任务 的 销毁 就会先于 数据库连接池对象 的销毁。
                threadPool.setWaitForTasksToCompleteOnShutdown(true);
                // 任务的等待时间 如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
                threadPool.setAwaitTerminationSeconds(60);
                // 线程不够用时由调用的线程处理该任务
                threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            }
        }
        return threadPool;
    }
}
