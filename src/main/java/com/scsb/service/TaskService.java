package com.scsb.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scsb.config.Constants;
import com.scsb.model.ManagerTask;
import com.scsb.model.repository.ManagerTaskRepository;

@Service
public class TaskService 
{
	@Autowired
    private ManagerTaskRepository repository;
	
	// 拿取超級使用者功能區
	public List<ManagerTask> superList() 
	{
		List<String> strList = Arrays.asList(Constants.PAGE_KEY_HISTORY_SUPERIOR, Constants.PAGE_KEY_REMIND_EMAIL_SETTING);
		return repository.findByIds(strList);
	}
	
	// 將Task整理成有父子階層
	public Map<String, List<ManagerTask>> organizeTasks(List<ManagerTask> tasks)
	{
		Map<String, List<ManagerTask>> allList = null;
		
		// 尋找父階層Tasks
		List<ManagerTask> parentList = tasks.stream().filter(s -> s.getParentId() == null ).sorted(Comparator.comparing(ManagerTask::getSort)).collect(Collectors.toList());		

		// 尋找子階層Tasks
		List<ManagerTask> child_list = tasks.stream().filter(s -> s.getParentId() != null ).sorted(Comparator.comparing(ManagerTask::getSort)).collect(Collectors.toList());
		// 將子階層分類至同個Key
		Map<String, List<ManagerTask>> childMap = (Map<String, List<ManagerTask>>) child_list.stream().collect(Collectors.groupingBy(ManagerTask::getParentId));	

		// 將分類完的Map Set至父功能的List
		List<ManagerTask> childTaskList = null;
		LinkedHashMap<String, List<ManagerTask>> childCateMap = null;
		for (ManagerTask parentTask : parentList) {

			if (childMap.containsKey(parentTask.getId())) {
				childTaskList =childMap.get(parentTask.getId());
				if(childTaskList != null) {
					
					childCateMap = (LinkedHashMap<String, List<ManagerTask>>) childTaskList.stream().sorted(Comparator.comparing(ManagerTask::getSort)).collect(Collectors.groupingBy(ManagerTask::getCategory, LinkedHashMap::new,Collectors.toList()));	
					parentTask.setChildTaskMap(childCateMap);
				}
			}
		}
	
		// 將父階層分類至同個Key
		Map<String, List<ManagerTask>> parentMap = (Map<String, List<ManagerTask>>) parentList.stream().collect(Collectors.groupingBy(ManagerTask::getCategory, LinkedHashMap::new,Collectors.toList()));	
	
		return parentMap;
	}
}
