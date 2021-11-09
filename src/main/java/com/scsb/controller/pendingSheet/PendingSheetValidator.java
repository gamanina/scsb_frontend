package com.scsb.controller.pendingSheet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.scsb.model.Sheet;

@Component
public class PendingSheetValidator {
	
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(PendingSheetForm form, BindingResult result, boolean finalStepCheck) throws Exception 
	{
		if (!finalStepCheck)
		{
			if (StringUtils.isBlank(form.getNextApproverId()))
			{
				result.rejectValue("nextApprover", "error", "請選擇審核人");
			}
		}
		
		if (form.getFile() != null && !form.getFile().isEmpty())
		{
			if (form.getFile().getSize() >= 10 * 1024 * 1024)
			{
				result.rejectValue("file", "error", "檔案大小不得超過10Mb");
			}
		}
	}
	
}
