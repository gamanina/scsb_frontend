package com.scsb.model;

import java.util.List;

/**
 * 
 * 建立日期：2021/05/03
 * 程式摘要：com.scsb.model
 * 類別名稱：Ldap.java 
 * 程式內容說明：Ldap帳號物件
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
public class Ldap {
	
	/** 行員編號 **/
	private String cn;
	/** 行員姓名 **/
	private String givenName;
	/** 所在單位代碼 **/
	private String departmentNumber;
	/** 所在單位中文名稱 **/
	private String departmentNumberName;
	/** 就職狀況; 若為 A* 為在職, 若為D* 為留職停薪或離職退休 **/
	private String employmentStatus;
	/** 行員Email, 若要寄到Notes 則為行編 + @notes.scsb.com.tw **/
	private String mail;
	/** 直屬主管行編 **/
	private String supervisorAcno;
	/** 直屬主管姓名 **/
	private String supervisorName;
	/** 職稱 **/
	private String cardtitle;
	/** 職位名稱 **/
	private String title;
	/** 該值若為A 則為A級主管; B為B級主管; 空值為經辦 **/
	private String titleno;
	
	/** 單位代號 **/
	private String ou;
	/** 單位名稱 **/
	private String description;
	/** 單位主管 **/
	private String manAcctno;
	/** 遵法主管 **/
	private String observer;
	/** 權限群組字串 **/
	private List<String> securityEquals;
	/** 權限群組物件 **/
	private SecurityEquals seObject;

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	public String getDepartmentNumberName() {
		return departmentNumberName;
	}

	public void setDepartmentNumberName(String departmentNumberName) {
		this.departmentNumberName = departmentNumberName;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSupervisorAcno() {
		return supervisorAcno;
	}

	public void setSupervisorAcno(String supervisorAcno) {
		this.supervisorAcno = supervisorAcno;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getCardtitle() {
		return cardtitle;
	}

	public void setCardtitle(String cardtitle) {
		this.cardtitle = cardtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleno() {
		return titleno;
	}

	public void setTitleno(String titleno) {
		this.titleno = titleno;
	}

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManAcctno() {
		return manAcctno;
	}

	public void setManAcctno(String manAcctno) {
		this.manAcctno = manAcctno;
	}

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}
	
	public List<String> getSecurityEquals() {
		return securityEquals;
	}

	public void setSecurityEquals(List<String> securityEquals) {
		this.securityEquals = securityEquals;
	}

	public SecurityEquals getSeObject() {
		return seObject;
	}

	public void setSeObject(SecurityEquals seObject) {
		this.seObject = seObject;
	}
	
}
