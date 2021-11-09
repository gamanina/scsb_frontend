package com.scsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scsb.model.RemindEmailSetting;
import com.scsb.model.repository.RemindEmailSettingRepository;

@Service
public class RemindEmailSettingService {
	@Autowired
	private RemindEmailSettingRepository repository;

	public RemindEmailSetting save(RemindEmailSetting remindEmailSetting) {
		return repository.save(remindEmailSetting);
	}

	// 取得單筆
	public RemindEmailSetting get() {

		RemindEmailSetting data = repository.get();
		return data;
	}
	

	
}
