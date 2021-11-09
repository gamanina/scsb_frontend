package com.scsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scsb.model.SheetApproval;
import com.scsb.model.SheetCancel;
import com.scsb.model.repository.SheetCancelRepository;


@Service
public class SheetCancelService 
{
	@Autowired
    private SheetCancelRepository repository;

	//儲存停刊申請表單
	public SheetCancel save(SheetCancel model) 
	{
		return repository.save(model);
	}
	
	//管理者取得 表單停刊審核流程
	public List<SheetCancel> getCancelApplicantById(int id) 
	{
		List<SheetCancel> datasList = repository.getCancelApplicantById(id);
		
		return datasList;
	}
	//一般表單取得 表單停刊審核流程
	public List<SheetCancel> getCancelApplicantByIdAndUserId(int id) 
	{
		List<SheetCancel> datasList = repository.getCancelApplicantById(id);
		
		return datasList;
	}
	
	// 取得停刊紀錄By表單Id
	public List<SheetCancel> getCancelListBySheetId(int id) 
	{
		List<SheetCancel> dataList = repository.getCancelListBySheetId(id);
		
		return dataList;
	}
	
	// 取得停刊申請人名字
	public SheetCancel getCancelApplicantBySheetId(int id) 
	{
		SheetCancel data = repository.getCancelApplicantBySheetId(id);
		
		return data;
	}
}
