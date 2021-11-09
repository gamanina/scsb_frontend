package com.scsb.controller.indexBanner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.scsb.config.Constants;

@Component
public class IndexBannerValidator {
	
	/**
	 * 表單驗證
	 * @param errors
	 * @throws Exception 
	 */
	public void validate(IndexBannerForm form, BindingResult result) throws Exception 
	{
		// 檔案類型取得
		String imageName = form.getImageFile().getOriginalFilename();
		String fileType = imageName.substring(imageName.lastIndexOf(".") + 1, imageName.length());
		
		String imageMobileName = form.getImageMobileFile().getOriginalFilename();
		String mobileFileType = imageMobileName.substring(imageMobileName.lastIndexOf(".") + 1, imageMobileName.length());
		
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
		
		if (form.getImageMobileFile() == null || form.getImageMobileFile().isEmpty()) 
		{
			result.rejectValue("imageMobileFile", "error", "請上傳圖片");
        }
		else if (!Constants.FILETYPES.contains(mobileFileType))
		{
			result.rejectValue("imageMobileFile", "error", "請上傳圖片格式檔案(jpg、jpeg、png、gif)");
		}
		else if (form.getImageMobileFile().getSize() >= 500 * 1024)
		{
			result.rejectValue("imageMobileFile", "error", "圖片大小不得超過500k");
        }
	}
	
}
