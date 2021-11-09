package com.scsb.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
public class MultiScheduleConfig  {
	//自定義執行緒池
	@Bean(name = "multiThreadPoolTaskExecutor")
	public TaskExecutor  getMyThreadPoolTaskExecutor() {
	    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
	    taskExecutor.setCorePoolSize(20);
	    taskExecutor.setMaxPoolSize(200);
	    taskExecutor.setQueueCapacity(25);
	    taskExecutor.setKeepAliveSeconds(200);
	    taskExecutor.setThreadNamePrefix("Haina-ThreadPool-");
	    // 執行緒池對拒絕任務（無執行緒可用）的處理策略，目前只支援AbortPolicy、CallerRunsPolicy；預設為後者
	    taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
	    //排程器shutdown被呼叫時等待當前被排程的任務完成
	    taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
	    //等待時長
	    taskExecutor.setAwaitTerminationSeconds(60);
	    taskExecutor.initialize();
	    return taskExecutor;
	}
}

