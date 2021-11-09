package com.scsb.model.vo;

import java.util.List;

/**
 * 
 * 建立日期：2021/05/31
 * 程式摘要：com.scsb.model
 * 類別名稱：Ldap.java 
 * 程式內容說明：Email排程收件者物件
 * @author Stan
 * @version 1.0
 * @since 1.0
 */
public class EmailRecipientVo {
	
	/** 收件者 email **/
	private String recipientEmail;
	/** 收件者 待核筆數**/
	private int sheetCount;
	/** 收件者 email連結類型 0:處理中-刊登,4:處理中-停刊**/
	private String emailUrlType;
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public int getSheetCount() {
		return sheetCount;
	}
	public void setSheetCount(int sheetCount) {
		this.sheetCount = sheetCount;
	}
	public String getEmailUrlType() {
		return emailUrlType;
	}
	public void setEmailUrlType(String emailUrlType) {
		this.emailUrlType = emailUrlType;
	}

}
