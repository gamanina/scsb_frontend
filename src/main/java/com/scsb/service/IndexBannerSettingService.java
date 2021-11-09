package com.scsb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scsb.model.IndexBannerSetting;
import com.scsb.model.RemindEmailSetting;
import com.scsb.model.repository.IndexBannerSettingRepository;
import com.scsb.model.repository.RemindEmailSettingRepository;

@Service
public class IndexBannerSettingService 
{
	@Autowired
	private IndexBannerSettingRepository repository;

	public IndexBannerSetting save(IndexBannerSetting indexBannerSetting) 
	{
		return repository.save(indexBannerSetting);
	}

	// 取得單筆
	public IndexBannerSetting get() 
	{
		IndexBannerSetting data = repository.get();
		return data;
	}
}
