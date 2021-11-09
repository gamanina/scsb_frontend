package com.scsb.controller.sheet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.scsb.model.Sheet;

@Component
public class CancelSheetValidator {
	
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(CancelSheetForm form, BindingResult result ) throws Exception 
	{
	
		if (StringUtils.isBlank(form.getNextApproverId()))
		{
			result.rejectValue("nextApprover", "error", "請選擇審核人");
		}
	
	
		
	}
	
}
