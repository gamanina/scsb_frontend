package com.src.scsb.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.src.scsb.config.Constants;
import com.src.scsb.model.Sheet;
import com.src.scsb.model.repository.SheetRepository;

@Service
public class SheetService 
{
	@Autowired
	private SheetRepository repository;

	// For首頁輪播
	public List<Sheet> getIndexBanners() 
	{
		LocalDateTime now = LocalDateTime.now();
		return repository.getIndexBanners(now, Constants.INDEX_BANNER_SHEET_TYPE, Constants.SHEET_STATUS_PROCESSING,
				Constants.SHEET_STATUS_PASSED);
	}
	
	public List<Sheet> getPassedListByType(LocalDateTime now, String type)
	{
		return repository.getListByTypeAndStatus(now, type, Constants.SHEET_STATUS_PASSED);
	}
	
	public List<Sheet> getListByNewsLimit3(LocalDateTime now)
	{
		return repository.getListByNewsLimit3(now);
	}
	
	public List<Sheet> getDetail(LocalDateTime now,Number id)
	{
		return repository.getDetail(now,id);
	}

	public List<Sheet> getDetailList(LocalDateTime now, String type) {
		return repository.getDetailList(now,type);
	}
}
