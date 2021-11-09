package com.scsb.controller.indexBanner;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
import com.scsb.model.IndexBannerSetting;
import com.scsb.model.Ldap;
import com.scsb.model.Manager;
import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;
import com.scsb.service.CheckRightService;
import com.scsb.service.CommonService;
import com.scsb.service.IndexBannerSettingService;
import com.scsb.service.SendEmailService;
import com.scsb.service.SheetApprovalService;
import com.scsb.service.SheetService;

@Controller
@RequestMapping("/indexBanner")
public class IndexBannerController {
	
	@Autowired
    private CommonService commonService;
	@Autowired
	private SheetService sheetService;
	@Autowired
	private SheetApprovalService sheetApprovalService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private IndexBannerSettingService indexBannerSettingService;
	@Autowired
	private IndexBannerValidator formValidator;
	
	@Value("${web.upload-path}")
	private String uploadPath;
	@Value("${index.banner.image}")
	private String imageFolder;
	
	// TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	
	protected final String rightsString = Constants.PAGE_KEY_INDEX_BANNER;
	protected final String sheetType = Constants.INDEX_BANNER_SHEET_TYPE;
	protected final String reFlieName = "indexBanner";
	protected final String reFlieName2 = "indexBannerMobile";
	protected final String viewAddUrl = "views/indexBanner/add";
	// 預設廣告輪播刊登天數
	protected final int publishedDays = 14;
	
	private void setCommon(Model model, HttpServletRequest request, IndexBannerForm form, IndexBannerSetting ibs) throws Exception
	{
		try
		{
			// 審核人
			Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
			Ldap ldap = manager.getLdap();
			form.setApplicantId(ldap.getCn());
			form.setApplicant(ldap.getGivenName());
			form.setApplicantUnitId(ldap.getDepartmentNumber());
	        form.setApplicantUnit(ldap.getDepartmentNumberName());
			
			List<Ldap> approverList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_APPROVERS);
			
			// 刊登顯示 true:綠色 false:橘色
			boolean publishFilter = true;
			String msg = MessageConstants.MESSAGE_INDEX_BANNER_PUBLISH;
			List<Sheet> sheetList = sheetService.getIndexBanners();
			if (sheetList.size() >= 5)
			{
				publishFilter = false;
				Sheet sheet = sheetList.stream().min(Comparator.comparing(Sheet::getOffTime)).get();
				msg = MessageConstants.getPublishTimeMessage(sheet.getOffTime());
			}
			for (Sheet sheet : sheetList)
			{
				if (sheet.getApplicantUnitId().equals(form.getApplicantUnitId()))
				{
					publishFilter = false;
					msg = MessageConstants.getPublishTimeMessage(sheet.getOffTime());
				}
			}
			// 審核人名單
			model.addAttribute("approverList", approverList);
			// 前端刊登class顯示
			model.addAttribute("publishFilter", publishFilter);
			model.addAttribute("msg", msg);
			// 天數顯示
			model.addAttribute("days", ibs.getSettingDay());
			// 當前頁TASK ID
			model.addAttribute("taskId", rightsString);
		}
		catch(Exception e)
		{
			throw new Exception();
		}
	}
	
	@RequestMapping("/add")
	public String add(Model model, HttpServletRequest request, @ModelAttribute IndexBannerForm form) 
	{
		try 
		{
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask) 
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			IndexBannerSetting ibs = indexBannerSettingService.get();
			setCommon(model, request, form, ibs);
		} 
		catch (Exception e) 
		{			
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
		}
		
		return viewAddUrl;
	}
	
	@RequestMapping("/save")
	public String save(Model model, HttpServletRequest request, @ModelAttribute IndexBannerForm form, BindingResult bindingResult) 
	{
		try 	
		{
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask)
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			
			List<Sheet> sheetList = sheetService.getIndexBanners();
			if (sheetList.size() >= 5)
			{
				Sheet sheet = sheetList.stream().min(Comparator.comparing(Sheet::getOffTime)).get();
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.getFullUploadTimeMessage(sheet.getOffTime()), Constants.INDEX_BANNER_URL);
			}
			
			for (Sheet sheet : sheetList)
			{
				if (sheet.getApplicantUnitId().equals(form.getApplicantUnitId()))
				{
					return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.getRepeatUploadTimeMessage(sheet.getOffTime()), Constants.INDEX_BANNER_URL);
				}
			}
			
			formValidator.validate(form, bindingResult);
			if (bindingResult.hasErrors()) 
			{
				IndexBannerSetting ibs = indexBannerSettingService.get();
				setCommon(model, request, form, ibs);
				return viewAddUrl;
			}
			
			// 圖片重新命名
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT_YYYYMMDDHHMMSS);
			LocalDateTime now = LocalDateTime.now();
			File file = new File(uploadPath + imageFolder + now.getYear());
			if (!file.exists())
			{
				file.mkdirs();
			}
			String oldName = form.getImageFile().getOriginalFilename();
			String newName = now.format(formatter) + reFlieName + oldName.substring(oldName.lastIndexOf("."), oldName.length());
			File newFile = new File(file.getPath(), newName);
			form.getImageFile().transferTo(newFile);
			// 手機版圖片
			String oldMobileName = form.getImageMobileFile().getOriginalFilename();
			String newMobileName = now.format(formatter) + reFlieName2 + oldMobileName.substring(oldMobileName.lastIndexOf("."), oldMobileName.length());
			File newMobileFile = new File(file.getPath(), newMobileName);
			form.getImageMobileFile().transferTo(newMobileFile);
			
			// 儲存表單
			Sheet sheet = new Sheet();
			BeanUtils.copyProperties(form, sheet);
			sheet.setImage(imageFolder + now.getYear() + "/" + newName);
			sheet.setImageMobile(imageFolder + now.getYear() + "/" + newMobileName);
			sheet.setType(sheetType);
			sheet.setStatus(Constants.SHEET_STATUS_PROCESSING);
			sheet.setApplicantUnitId(form.getApplicantUnitId());
			sheet.setApplicantUnit(form.getApplicantUnit());
			sheet.setStep(Constants.STEP_1);
			sheet.setCreator(form.getApplicantId());
			sheet.setModifier(form.getApplicantId());
			// 時間處理
			LocalDateTime nowLdt = LocalDateTime.now();
			IndexBannerSetting ibs = indexBannerSettingService.get();
		    sheet.setOnTime(Timestamp.valueOf(nowLdt));
		    sheet.setOffTime(Timestamp.valueOf(nowLdt.plusDays(ibs.getSettingDay())));
		    
		    Sheet resultSheet = sheetService.save(sheet);

		    // SheetApproval Model Save
		    if (resultSheet.getId() > 0)
		    {
		    	SheetApproval sheetApproval = new SheetApproval();
			    sheetApproval.setScsbSheetId(resultSheet.getId());
			    sheetApproval.setApproverId(resultSheet.getApplicantId());
			    sheetApproval.setApprover(resultSheet.getApplicant());
			    sheetApproval.setSort(Constants.SORT_0);
			    sheetApproval.setCreator(resultSheet.getApplicantId());
			    sheetApproval.setModifier(resultSheet.getApplicantId());
			    sheetApproval.setStatus(Constants.SHEET_APPROVAL_APPLICATION);
			    sheetApprovalService.save(sheetApproval);
		    }
		    
		    try {
		    	sendEmailService.sendSheetApproveEmail(request, resultSheet.getNextApproverId(), resultSheet.getAgentId());
			} catch (Exception e) {
				// TODO: handle exception
			}
		    
			return commonService.alertPageSetUp(model, Constants.RESULT_SUCCESS, MessageConstants.MESSAGE_INSERT_SUCCESS, Constants.RECORDSHEET_URL);
		}
		catch (Exception e) 
		{
			// TODO 錯誤時應改倒轉至其他頁面
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
		}
	}
}