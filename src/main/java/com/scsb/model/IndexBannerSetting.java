package com.scsb.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 建立日期：2020/06/04
 * 程式摘要：com.scsb.model
 * 類別名稱：IndexBannerSetting.java 
 * 程式內容說明：首頁廣告輪播時間設定物件
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "SCSB_INDEX_BANNER_SETTING")
public class IndexBannerSetting
{
	@Id
	@Column(name = "ID")
	private Integer id;
	@Column(name = "SETTING_DAY")
	private Integer settingDay;
	@Column(name = "MODIFIER")
	private String modifier;
	@Column(name = "MODIFY_TIME", insertable = false)
	private Timestamp modifyTime;
	
	public IndexBannerSetting()
	{
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getSettingDay() {
		return settingDay;
	}

	public void setSettingDay(Integer settingDay) {
		this.settingDay = settingDay;
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
