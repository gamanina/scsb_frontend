package com.scsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scsb.model.SheetApproval;
import com.scsb.model.repository.SheetApprovalRepository;


@Service
public class SheetApprovalService 
{
	@Autowired
    private SheetApprovalRepository repository;

	public SheetApproval save(SheetApproval model) 
	{
		return repository.save(model);
	}
	

	//取得表單上架審核流程
	public List<SheetApproval> getApplicantById(int id) 
	{
		List<SheetApproval> datasList = repository.getApplicantById(id);
		
		return datasList;
	}
	

	// 取得簽核紀錄By表單Id與步驟數
	public SheetApproval getApprovalBySheetIdAndStep(int id, int step) 
	{
		SheetApproval data = repository.getApprovalBySheetIdAndStep(id, step);
		
		return data;
	}
}
