package com.scsb.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scsb.config.Constants;
import com.scsb.controller.sheetSuperior.AllSheetForm;
import com.scsb.model.Sheet;
import com.scsb.model.repository.SheetRepository;
import com.scsb.util.LogUtil;

@Service
public class SheetService {
	@Autowired
	private SheetRepository repository;

	public Sheet save(Sheet sheet) {
		return repository.save(sheet);
	}

	// For首頁輪播
	public List<Sheet> getIndexBanners() {
		LocalDateTime now = LocalDateTime.now();
		return repository.getIndexBanners(now, Constants.INDEX_BANNER_SHEET_TYPE, Constants.SHEET_STATUS_PROCESSING,
				Constants.SHEET_STATUS_PASSED);
	}

	// 依員工編號取得待審表單
	public List<Sheet> getPendingSheetListByCn(String Cn) 
	{
		List<Sheet> datasList = repository.getSheetListByStatusAndCn(Constants.SHEET_STATUS_PROCESSING, Cn);
		return datasList;
	}

	// 依員工編號取得停刊表單
	public List<Sheet> getCancelSheetListByCn(String Cn) 
	{
		List<Sheet> datasList = repository.getSheetListByStatusAndCn(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING, Cn);
		return datasList;
	}
	
	// 管理者取得所有表單
	public List<Sheet> getSheetList() {

		List<Sheet> datasList = repository.getAllSheets();

		return datasList;
	}

	// 管理者依條件取得表單
	public List<Sheet> getSearchList(AllSheetForm form) throws Exception {
		List<Sheet> datasList = new ArrayList<Sheet>();
		List<Sheet> searchList = new ArrayList<Sheet>();

		String strDate = form.getStrDate();
		String endDate = form.getEndDate();
		String applicant = form.getApplicant();
		String tableName = form.getTableName();
		String status = form.getStatus();

		// 判斷條件是否為空 True為空
		boolean strDateBoolean = strDate == null || strDate.equals("");
		boolean endDateBoolean = endDate == null || endDate.equals("");
		boolean applicantBoolean = applicant == null || applicant.equals("");
		boolean tableNameBoolean = tableName == null || tableName.equals("");
		boolean statusBoolean = status == null || status.equals("");

		// 管理者取得所有表單
		if (strDateBoolean && endDateBoolean && applicantBoolean && tableNameBoolean && statusBoolean) {

			datasList = repository.getAllSheets();
			return datasList;
		}
		// 根據條件取資料(申請人、表單類型、狀態)
		searchList = repository.getSearchSheets(applicant, tableName, status);
		try {

			// 時間處理

			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

			for (Sheet s : searchList) {
//				
//				//取申請使間
				long time = s.getLongTime();

				if (strDateBoolean && endDateBoolean) { // 未填始末時間
					datasList = searchList;
				} else if (!strDateBoolean && endDateBoolean) { // 只填開始時間
					Date s_date = df.parse(strDate);
					long s_time = s_date.getTime();
					if (time >= s_time) {
						datasList.add(s);
					}
				} else if (strDateBoolean && !endDateBoolean) { // 只填結束時間

					Date e_date = df.parse(endDate);
					long e_time = e_date.getTime();
					if (time <= e_time) {
						datasList.add(s);
					}

				} else if (!strDateBoolean && !endDateBoolean) { // 有填開始&結束時間

					Date s_date = df.parse(strDate);
					long s_time = s_date.getTime();
					Date e_date = df.parse(endDate);
					long e_time = e_date.getTime();

					if (time >= s_time && time <= e_time) {
						datasList.add(s);
					}

				}

			}

		} catch (java.text.ParseException e) {
			LogUtil.setErrorLog("SearchList: ", e);

		}
		return datasList;

	}

	// 根據員編取得表單
	public List<Sheet> getSheetByApplicant(String memberId, AllSheetForm form) {

		List<Sheet> datasList = new ArrayList<Sheet>();
		List<Sheet> searchList = new ArrayList<Sheet>();

		// 取條件
		String strDate = form.getStrDate();
		String endDate = form.getEndDate();
		String applicant = form.getApplicant();
		String tableName = form.getTableName();
		String status = form.getStatus();

		// 判斷條件是否為空 True為空
		boolean strDateBoolean = strDate == null || strDate.equals("");
		boolean endDateBoolean = endDate == null || endDate.equals("");
		boolean applicantBoolean = applicant == null || applicant.equals("");
		boolean tableNameBoolean = tableName == null || tableName.equals("");
		boolean statusBoolean = status == null || status.equals("");

		// 依員編取得表單
		if (strDateBoolean && endDateBoolean && applicantBoolean && tableNameBoolean && statusBoolean) {

			datasList = repository.getSheetByApplicant(memberId);
			return datasList;
		}
		// 根據條件取資料(申請人、表單類型、狀態)
		searchList = repository.getSheetAndConditionByApplicant(memberId, applicant, tableName, status);
		try {

			// 時間處理

			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			
			for (Sheet s : searchList) {
//						
//						//取申請使間
				long time = s.getLongTime();

				if (strDateBoolean && endDateBoolean) { // 未填始末時間
					datasList = searchList;
				} else if (!strDateBoolean && endDateBoolean) { // 只填開始時間
					Date s_date = df.parse(strDate);
					long s_time = s_date.getTime();
					if (time >= s_time) {
						
						datasList.add(s);
					}
				} else if (strDateBoolean && !endDateBoolean) { // 只填結束時間

					Date e_date = df.parse(endDate);
					long e_time = e_date.getTime();
					if (time <= e_time) {
						datasList.add(s);
					}

				} else if (!strDateBoolean && !endDateBoolean) { // 有填開始&結束時間

					Date s_date = df.parse(strDate);
					long s_time = s_date.getTime();
					Date e_date = df.parse(endDate);
					long e_time = e_date.getTime();

					if (time >= s_time && time <= e_time) {
						datasList.add(s);
					}

				}

			}

		} catch (java.text.ParseException e) {
			LogUtil.setErrorLog("SheetByApplicant: ", e);
		}

		return datasList;
	}

	// 取得單筆表單
	public Sheet getSheetById(int id) {

		Sheet data = repository.getSheetById(id);

		return data;
	}
	
	// 取得指定狀態表單
	public List<Sheet> getSheetByStatus(List<String> status) {

		List<Sheet> list = repository.getSheetByStatus(status);

		return list;
	}
	
}
