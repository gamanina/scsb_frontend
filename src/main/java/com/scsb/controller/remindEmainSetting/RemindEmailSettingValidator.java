package com.scsb.controller.remindEmainSetting;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class RemindEmailSettingValidator {
	
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(RemindEmailSettingForm form, BindingResult result) throws Exception 
	{
		
		
		if (form.getSettingHour() == null)
		{
			result.rejectValue("settingHour", "error", "請選擇時間");
		}
		
		
	}
	
}
