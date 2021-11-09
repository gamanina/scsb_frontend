package com.scsb.controller.sheetSuperior;

import org.springframework.web.multipart.MultipartFile;

/**
 *管理者表單搜尋物件
 */
public class AllSheetForm 
{
	private String strDate;
	private String endDate;
	private String applicant;
	private String tableName;
	private String status;
	public String getStrDate() {
		return strDate;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
