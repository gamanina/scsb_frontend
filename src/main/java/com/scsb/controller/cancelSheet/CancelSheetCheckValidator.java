package com.scsb.controller.cancelSheet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.scsb.model.Sheet;

@Component
public class CancelSheetCheckValidator 
{
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(CancelSheetCheckForm form, BindingResult result, boolean finalStepCheck) throws Exception 
	{
		if (!finalStepCheck)
		{
			if (StringUtils.isBlank(form.getNextApproverId()))
			{
				result.rejectValue("nextApprover", "error", "請選擇審核人");
			}
		}
	}
}
