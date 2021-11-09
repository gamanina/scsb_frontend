package com.scsb.controller.cancelSheet;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.scsb.config.DataOption;
import com.scsb.config.MessageConstants;
import com.scsb.controller.pendingSheet.PendingSheetForm;
import com.scsb.model.Ldap;
import com.scsb.model.Manager;
import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;
import com.scsb.model.SheetCancel;
import com.scsb.service.CheckRightService;
import com.scsb.service.CommonService;
import com.scsb.service.SendEmailService;
import com.scsb.service.SheetCancelService;
import com.scsb.service.SheetService;
import com.scsb.util.LogUtil;

@Controller
@RequestMapping("/cancelSheet")
public class CancelSheetController 
{
	@Autowired
    private CommonService commonService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private SheetService sheetService;
	@Autowired
	private SheetCancelService sheetCancelService;
	@Autowired
	private CancelSheetCheckValidator formValidator;
	@Autowired
	private DataOption dataOption;
	
	// TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	
	protected final String rightsString = Constants.PAGE_KEY_CANCEL;
	protected final String reFlieName = "cancelSheet";
	
	private Ldap setCommon(Model model, HttpServletRequest request) throws Exception
	{
		// 使用者
		Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		Ldap ldap = manager.getLdap();
					
		Map<String, String> typeMap = dataOption.getSheetTypeMap();
		
		model.addAttribute("typeMap", typeMap);
		model.addAttribute("taskId", rightsString);
		
		return ldap;
	}
	
	// 細節頁上方預覽
	private void setSheetView(Model model, CancelSheetCheckForm form, Ldap ldap, HttpServletRequest request) throws Exception
	{
		// 表單
		Sheet sheet = sheetService.getSheetById(Integer.valueOf(form.getScsbSheetId()));
		
		// 檢查表單是否屬於此使用者
		if (!sheet.getNextApproverId().equals(ldap.getCn()))
		{
			if (sheet.getAgentId() != null && !sheet.getAgentId().equals(ldap.getCn()))
			{
				throw new Exception();
			}
		}
		
		// 流程
		List<String> processList = commonService.getFormProcessFormType(sheet.getType(), sheet.getStatus());
		
		// 檢查是否為最後一步驟
		boolean finalStepFilter = commonService.checkFinalStep(sheet, Constants.PAGE_KEY_CANCEL);
		
		// 拿取簽核人與代理人
		List<Ldap> approverList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_APPROVERS);
		
		// 停刊申請人
		SheetCancel cancelApplicant = sheetCancelService.getCancelApplicantBySheetId(sheet.getId());
		
		model.addAttribute("processList", processList);
		model.addAttribute("finalStepFilter", finalStepFilter);
		model.addAttribute("approverList", approverList);
		model.addAttribute("sheet", sheet);
		model.addAttribute("applicantName", cancelApplicant.getApprover());
	}
	
	@RequestMapping("/list")
	public String list(Model model, HttpServletRequest request) 
	{
		try 
		{
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask) 
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			
			// 拿取待核停刊表單列表
			Ldap ldap = setCommon(model, request);
			List<Sheet> sheetList = sheetService.getCancelSheetListByCn(ldap.getCn());
			
			model.addAttribute("sheetList", sheetList);
		} 
		catch (Exception e) 
		{			
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
		}
		return "views/cancelSheet/list";
	}
	
	@RequestMapping("/detail")
	public String edit(@ModelAttribute CancelSheetCheckForm form, Model model, HttpServletRequest request) 
	{
		try 
		{
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask) 
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			Ldap ldap = setCommon(model, request);
			setSheetView(model, form, ldap, request);
			
		} 
		catch (Exception e) 
		{			
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
		}
		return "views/cancelSheet/detail";
	}
	
	@RequestMapping("/save")
	public String save(Model model, HttpServletRequest request, @ModelAttribute CancelSheetCheckForm form, BindingResult bindingResult) 
	{
		try 
		{
			// 檢查權限
			boolean hastask = CheckRightService.findTask(request, rightsString);
			
			if (!hastask)
			{
				// TODO 錯誤時應改倒轉至其他頁面
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			
			// 檢查簽核選擇值
			if (!form.getCheck().equals(Constants.SHEET_APPROVAL_PASS) && !form.getCheck().equals(Constants.SHEET_APPROVAL_REFUSE))
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
			
			// 表單
			Sheet sheet = sheetService.getSheetById(Integer.valueOf(form.getScsbSheetId()));
			boolean finalStepCheck = commonService.checkFinalStep(sheet, Constants.PAGE_KEY_CANCEL);
			Ldap ldap = setCommon(model, request);
			formValidator.validate(form, bindingResult, finalStepCheck);
			if (bindingResult.hasErrors()) 
			{
				setSheetView(model, form, ldap, request);
				return "views/cancelSheet/detail";
			}
			
			// 檢查是否是指定的簽核人
			if (!sheet.getNextApproverId().equals(ldap.getCn()) && !sheet.getAgentId().equals(ldap.getCn()))
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.PENDINGSHEET_URL);
			}
			
			// 檢查此步驟是否已經被簽核人或代理人審核過
			List<SheetCancel> cancelList = sheetCancelService.getCancelListBySheetId(sheet.getId());
			if (cancelList == null || cancelList.size() == 0)
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.CANCELSHEET_URL);
			}
			
			int maxLoop = cancelList.stream().max(Comparator.comparing(SheetCancel::getLoop)).get().getLoop();
			int maxSort = cancelList.stream().filter(x -> x.getLoop() == maxLoop).max(Comparator.comparing(SheetCancel::getSort)).get().getSort();
			
			if (sheet.getStep() == maxSort)
			{
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_APPROVED_ERROR, Constants.CANCELSHEET_URL);
			}
			
			// 儲存表單
			SheetCancel sheetCancel = new SheetCancel();
			BeanUtils.copyProperties(form, sheetCancel);
			sheetCancel.setScsbSheetId(sheet.getId());
			sheetCancel.setApproverId(ldap.getCn());
			sheetCancel.setApprover(ldap.getGivenName());
			sheetCancel.setSort(sheet.getStep());
			sheetCancel.setCreator(ldap.getCn());
			sheetCancel.setModifier(ldap.getCn());
			sheetCancel.setStatus(form.getCheck());
			sheetCancel.setLoop(maxLoop);
			
			sheetCancelService.save(sheetCancel);
			
			// 若為退回
			if (form.getCheck().equals(Constants.SHEET_APPROVAL_REFUSE))
			{
				sheet.setStatus(Constants.SHEET_STATUS_RETURNED);
			}
			else if (form.getCheck().equals(Constants.SHEET_APPROVAL_PASS))
			{
				if(!finalStepCheck)
				{
					sheet.setNextApproverId(form.getNextApproverId());
					sheet.setNextApprover(form.getNextApprover());
					sheet.setAgentId(form.getAgentId());
					sheet.setAgent(form.getAgent());
					sheet.setStep(sheet.getStep() + 1);
				}
				else
				{
					sheet.setStatus(Constants.SHEET_STATUS_OFF_SHELF);
				}
			}
			
			sheet.setModifier(ldap.getCn());
			sheetService.save(sheet);
			
		    try 
		    {
		    	if (!finalStepCheck)
		    	{
		    		sendEmailService.sendSheetApproveEmail(request, sheet.getNextApproverId(), sheet.getAgentId());
		    	}
			} 
		    catch (Exception e) 
		    {
				LogUtil.setErrorLog(reFlieName + " sendSheetApproveEmail", e);
			}
		    
			return commonService.alertPageSetUp(model, Constants.RESULT_SUCCESS, MessageConstants.MESSAGE_INSERT_SUCCESS, Constants.PENDINGSHEET_URL);
		}
		catch (Exception e) 
		{
			// TODO 錯誤時應改倒轉至其他頁面
			LogUtil.setErrorLog(reFlieName + " save", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.LOGIN_URL);
		}
	}
}