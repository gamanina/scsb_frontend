package com.scsb.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.scsb.config.Constants;

@Service
public class CheckRightService {

	public static Boolean findTask(HttpServletRequest request , String id) {
		
		Map<String, String> rights = (Map<String, String>) request.getSession().getAttribute(Constants.SESSION_MEMBER_RISHTS);
		List<String> taskList = rights.keySet().stream().collect(Collectors.toList());
	
		for(String task : taskList) {
			if(task.equals(id)) {
				return true;
			}
		}
		
		return false;
	}
	
}
