package com.scsb.controller.remindEmainSetting;

import org.springframework.web.multipart.MultipartFile;

/**
 * 企業金融廣告物件
 */
public class RemindEmailSettingForm 
{
	private Integer settingHour;

	public Integer getSettingHour() {
		return settingHour;
	}

	public void setSettingHour(Integer settingHour) {
		this.settingHour = settingHour;
	}
	
}
