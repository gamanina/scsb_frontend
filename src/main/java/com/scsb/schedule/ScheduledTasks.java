package com.scsb.schedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.scsb.schedule.work.SendAprroveRemindEmailWork;
import com.scsb.schedule.work.TestWork;

@Component
public class ScheduledTasks 
{
	@Value("${is.test}")
	private boolean isTest;
	
	private Logger log = LogManager.getLogger();
	@Autowired
	private SendAprroveRemindEmailWork sendAprroveRemindEmailWork;

	//TODO 詹時關閉排程
//	@Scheduled(cron = "0 0 * * * ?")//每小時執行一次
//	@Async("multiThreadPoolTaskExecutor")
//	public void sendAprroveRemindEmailCron() {
//		if(!isTest) {
//			log.info("===SendAprroveRemindEmailCron: start ===");
//			sendAprroveRemindEmailWork.work();
//			log.info("===SendAprroveRemindEmailCron: end ===");
//		}
//	}

}