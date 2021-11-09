package com.scsb.controller.digitAd;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.scsb.config.Constants;

@Component
public class DigitAdValidator {
	
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(DigitAdForm form, BindingResult result) throws Exception 
	{
		// 檔案類型取得
		String fileName = form.getImageFile().getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		
		if (StringUtils.isBlank(form.getOnTimeDate()) || StringUtils.isBlank(form.getOnTimeHour())
		|| StringUtils.isBlank(form.getOnTimeMin()) || StringUtils.isBlank(form.getOffTimeDate())
		|| StringUtils.isBlank(form.getOffTimeHour()) || StringUtils.isBlank(form.getOffTimeMin())) 
		{
			result.rejectValue("onTimeDate", "error", "請輸入時間");
		}
		else
		{
			String onTimeStr = form.getOnTimeDate() + " " +form.getOnTimeHour() + ":" + form.getOnTimeMin() + ":00";
			String offTimeStr = form.getOffTimeDate() + " " +form.getOffTimeHour() + ":" + form.getOffTimeMin() + ":00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT_YYYYMMDD_HHMMSS);
			LocalDateTime onTime = LocalDateTime.parse(onTimeStr, formatter);
			LocalDateTime offTime = LocalDateTime.parse(offTimeStr, formatter);
			
			Duration duration = Duration.between(onTime, offTime);
			
			if (onTime.isAfter(offTime))
			{
				result.rejectValue("onTimeDate", "error", "刊登時間必須早於停刊時間");
			}
			else if (duration.toDays() > 365)
			{
				result.rejectValue("onTimeDate", "error", "刊登時間不得超過一年");
			}
		}
		
		if (StringUtils.isBlank(form.getNextApproverId()))
		{
			result.rejectValue("nextApprover", "error", "請選擇審核人");
		}
		
		if (StringUtils.isBlank(form.getTitle()))
		{
			result.rejectValue("title", "error", "請輸入標題");
		}
		
		if (form.getImageFile() == null || form.getImageFile().isEmpty()) 
		{
			result.rejectValue("imageFile", "error", "請上傳圖片");
        }
		else if (!Constants.FILETYPES.contains(fileType))
		{
			result.rejectValue("imageFile", "error", "請上傳圖片格式檔案(jpg、jpeg、png、gif)");
		}
		else if (form.getImageFile().getSize() >= 500 * 1024)
		{
			result.rejectValue("imageFile", "error", "圖片大小不得超過500k");
        }
	}
	
}
