package com.scsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.better.util.StringTools;
import com.scsb.config.Constants;
import com.scsb.model.Manager;
import com.scsb.model.repository.ManagerRepository;


@Service
public class ManagerService 
{
	@Autowired
    private ManagerRepository repository;

	public Manager login(String account, String unencryptedPassword) 
	{
		String password = StringTools.encryptSHA1toHex(unencryptedPassword, Constants.CHARSET_UTF8);
		return repository.findByZhhoAndMimaAndStatus(account, password,Constants.STATUS_ENABLE);
	}
	

}
