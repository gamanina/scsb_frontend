package com.scsb.controller.sheetSuperior;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.scsb.model.Sheet;

@Component
public class CloseSheetValidator {
	
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(CloseSheetForm form, BindingResult result ) throws Exception 
	{
	
		if (StringUtils.isBlank(form.getArchiveReason()))
		{
			result.rejectValue("ArchiveReason", "error", "請填寫更動原因");
		}
	
	
		
	}
	
}
