package com.scsb.model;

/**
 * 
 * 建立日期：2021/05/26
 * 程式摘要：com.scsb.model
 * 類別名稱：SecurityEquals.java 
 * 程式內容說明：Ldap的SecurityEquals物件
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
public class SecurityEquals {

	/** 使用者身分 **/
	private String cn; //generalGrp(一般使用者)、superuserGrp(超級使用者)
	/** 系統身分 **/
	private String ou; //scsbwebDraftSys(擁有上稿後台系統權限)
	private String o;
	private String c;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

}
