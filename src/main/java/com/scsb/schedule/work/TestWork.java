package com.scsb.schedule.work;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TestWork 
{	
	private Logger log = LogManager.getLogger();
	
    public void shop() 
    {
    	log.info("===initialDelay: shop");
    }
}