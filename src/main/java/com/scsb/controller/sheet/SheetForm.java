package com.scsb.controller.sheet;

import org.springframework.web.multipart.MultipartFile;

/**
 *一般表單搜尋物件
 */
public class SheetForm 
{
	private String strDate;
	private String endDate;
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
