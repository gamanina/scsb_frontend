package com.scsb.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 建立日期：2020/05/24
 * 程式摘要：com.scsb.model
 * 類別名稱：RemindEmailSetting.java 
 * 程式內容說明：提醒email物件
 * @author Stan
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "SCSB_REMIND_EMAIL_SETTING")
public class RemindEmailSetting
{
	@Id
	@Column(name="ID")
	private Integer id;
	@Column(name="SETTING_HOUR")
	private Integer settingHour;
	@Column(name="MODIFIER")
	private String modifier;
	@Column(name="MODIFY_TIME", insertable=false, updatable=false)
	private Timestamp modifyTime;
	
	public RemindEmailSetting()
	{
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSettingHour() {
		return settingHour;
	}

	public void setSettingHour(Integer settingHour) {
		this.settingHour = settingHour;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	
}
