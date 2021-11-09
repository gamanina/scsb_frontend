package com.scsb.controller.remindEmainSetting;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsb.config.Constants;
import com.scsb.config.MessageConstants;
import com.scsb.model.Ldap;
import com.scsb.model.Manager;
import com.scsb.model.RemindEmailSetting;
import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;
import com.scsb.service.CheckRightService;
import com.scsb.service.CommonService;
import com.scsb.service.RemindEmailSettingService;
import com.scsb.service.SendEmailService;
import com.scsb.service.SheetApprovalService;
import com.scsb.service.SheetService;
import com.scsb.util.LogUtil;

@Controller
@RequestMapping("/remindEmailSetting")
public class RemindEmailSettingController {
	
	@Autowired
    private CommonService commonService;


	@Autowired
	private RemindEmailSettingService remindEmailSettingService;
	@Autowired
	private RemindEmailSettingValidator formValidator;
	
	@Value("${web.upload-path}")
	private String uploadPath;
	@Value("${business.banner.image}")
	private String imageFolder;
	
	// TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	
	protected final String rightsString = Constants.PAGE_KEY_REMIND_EMAIL_SETTING;
	protected final String sheetType = Constants.BUSINESS_BANNER_SHEET_TYPE;
	protected final String reFlieName = "remindEmailSetting";
	protected final String viewAddUrl = "views/remindEmailSetting/edit";
	
	private void setCommon(Model model, HttpServletRequest request, RemindEmailSettingForm form) throws Exception
	{
		/** 當前頁TASK ID **/
		model.addAttribute("taskId", rightsString);
	}
	
	@RequestMapping("/edit")
	 public String businessBanner(@ModelAttribute RemindEmailSettingForm form,Model model, HttpServletRequest request)
	{
		try 
		{
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask) 
			{
				Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
				LogUtil.setActionLog("hasTask error: ", "rights: " + reFlieName + " manager: " + manager.getId());
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			setCommon(model, request, form);
			RemindEmailSetting setting = remindEmailSettingService.get();
			if(setting != null) {
				BeanUtils.copyProperties(setting,form);
			}
		} 
		catch (Exception e) 
		{			
			// TODO 錯誤時應改倒轉至其他頁面
			LogUtil.setErrorLog(reFlieName + " edit", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
		}
		
		return viewAddUrl;
	 }

	@RequestMapping("/save")
	public String save(Model model, HttpServletRequest request, @ModelAttribute RemindEmailSettingForm form, BindingResult bindingResult) 
	{
		try 
		{
			Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask)
			{
				LogUtil.setActionLog("hasTask error: ", "rights: " + reFlieName + " manager: " + manager.getId());
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			
			setCommon(model, request, form);
			formValidator.validate(form, bindingResult);
			if (bindingResult.hasErrors()) 
			{
				return viewAddUrl;
			}

			RemindEmailSetting setting = remindEmailSettingService.get();
			if(setting == null) {
				setting = new RemindEmailSetting();
				setting.setId(1);
			}
			BeanUtils.copyProperties(form, setting);
			setting.setModifier(manager.getLdap().getGivenName());
			remindEmailSettingService.save(setting);
		    LogUtil.setActionLog(reFlieName + " edit: ",  " manager: " + manager.getId());

		    
		    return commonService.alertPageSetUp(model, Constants.RESULT_SUCCESS, MessageConstants.MESSAGE_UPDATE_SUCCESS, Constants.REMIND_EMAIL_URL);
		}
		catch (Exception e) 
		{
			// TODO 錯誤時應改倒轉至其他頁面
			LogUtil.setErrorLog(reFlieName + " save", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
		}
	}
}