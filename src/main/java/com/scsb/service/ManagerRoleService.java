package com.scsb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.scsb.model.ManagerRole;
import com.scsb.model.repository.ManagerRoleRepository;


@Service
public class ManagerRoleService 
{
	//TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	
	@Autowired
    private ManagerRoleRepository repository;

	public ManagerRole getRoleById(String id) 
	{
		Optional<ManagerRole> opt = repository.findById(id);
		return opt.isPresent() == true ? opt.get() : null;
	}
	
	public String getRoleIdByDepartmentNumber(String DepartmentNumber) 
	{
		//TODO 刪除
		if (isTest)
		{
			return "8";
		}
		
		// TODO 有子部門問題		
		switch(DepartmentNumber)
		{
			case "012100":
				return "2";
			case "870000":
				return "3";
			case "890000":
				return "4";
			case "840000":
				return "5";
			case "850000":
				return "6";
			case "850100":
				return "6";
			default:
				return "7";
		}
	}
}
