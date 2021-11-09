package com.scsb.controller.sheetSuperior;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsb.config.Constants;
import com.scsb.config.DataOption;
import com.scsb.config.MessageConstants;
import com.scsb.model.Ldap;
import com.scsb.model.Manager;
import com.scsb.model.Sheet;
import com.scsb.model.SheetApproval;
import com.scsb.model.SheetCancel;
import com.scsb.service.CheckRightService;
import com.scsb.service.CommonService;
import com.scsb.service.SheetApprovalService;
import com.scsb.service.SheetCancelService;
import com.scsb.service.SheetService;
import com.scsb.util.LogUtil;

@Controller
@RequestMapping("/recordAllSheet")
public class AllSheetController {

	@Autowired
    private CommonService commonService;
	@Autowired
    private SheetService sheetService ;
	@Autowired
    private SheetApprovalService sheetApprovalService ;
	@Autowired
    private SheetCancelService sheetCancelService ;
	@Autowired
	private DataOption dataOption;
	@Autowired
	private CloseSheetValidator formValidator;

	/** All controller about web page must have */
	protected final String rightsString = Constants.PAGE_KEY_HISTORY_SUPERIOR;
	protected final String reFlieName = "allSheet";

	private Ldap setCommon(Model model, HttpServletRequest request) throws Exception
	{
		// 使用者
		Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		Ldap ldap = manager.getLdap();
					
		Map<String, String> typeMap = dataOption.getSheetTypeMap();
		
		model.addAttribute("typeMap", typeMap);
		
		return ldap;
	}
	
	@RequestMapping("/list")
	 public String recordSheet(@ModelAttribute AllSheetForm form, Model model, HttpServletRequest request){
		
		/** START -- All controller about web page must have */
		try 
		{
			/*判斷是否有權限*/
			boolean hastask = CheckRightService.findTask(request, rightsString);
			/*取當前頁TASK ID*/
			model.addAttribute("taskId", rightsString);
			if(!hastask) 
			{
				Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
				LogUtil.setActionLog("hasTask error: ", "rights: " + reFlieName + " manager: " + manager.getId());
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
		}catch (Exception e)
		{	
			LogUtil.setErrorLog(reFlieName + " add", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
		}
		/** END -- All controller about web page must have */

		// 設置頁面功能表
		commonService.setCommonInfo(model, request);
		
		model.addAttribute("msg",Constants.MSG_NO_CONDITION);
		model.addAttribute("type",dataOption.getSheetTypeMap());
		model.addAttribute("status",dataOption.getSheetStatusMap());		
		return "views/recordAllSheet/history-superior";
	 }

	@RequestMapping("/searchList")
	 public String searchSheet(@ModelAttribute AllSheetForm form, Model model, HttpServletRequest request){
		
		/** START -- All controller about web page must have */
		try 
		{
			/*判斷是否有權限*/
			boolean hastask = CheckRightService.findTask(request, rightsString);
			/*取當前頁TASK ID*/
			model.addAttribute("taskId", rightsString);
			if(!hastask) 
			{
				Manager manager = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
				LogUtil.setActionLog("hasTask error: ", "rights: " + reFlieName + " manager: " + manager.getId());
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
			}
		}catch (Exception e)
		{	
			LogUtil.setErrorLog(reFlieName + " add", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
		}
		/** END -- All controller about web page must have */

		
		// 設置頁面功能表
		commonService.setCommonInfo(model, request);
		
		/*判斷超級使用者(全表單可看)*/
		Manager member = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		
		if(member == null) {
			LogUtil.setActionLog("member NULL error: ", "rights: " + reFlieName );
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
	
		}
		
		//取得登入者ID
		
		String roleId = member.getRole().getId();
		List<Sheet> sheetList = null;
		
		//TODO: 最後要調整將ROLE_ID_ADMIN改成LDAP提供的管理者
		
		if( roleId.equals(Constants.ROLE_ID_ADMIN) || roleId.equals(Constants.ROLE_ID_TEST)){
			try {
				sheetList = sheetService.getSearchList(form);
				
			} catch (Exception e) {
				LogUtil.setErrorLog(reFlieName + " add", e);
				return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.EMPTY_DATA, Constants.LOGIN_URL);
			}
			
		}
			
		model.addAttribute("msg",Constants.MSG_NO_DATA);
		model.addAttribute("sheetList",sheetList);
		model.addAttribute("type",dataOption.getSheetTypeMap());
		model.addAttribute("status",dataOption.getSheetStatusMap());
		
		
		return "views/recordAllSheet/history-superior";
	 }

	@RequestMapping("/detail")
	 public String recordSheetDetail(@ModelAttribute CloseSheetForm form, Model model, HttpServletRequest request){
		
		/** START -- All controller about web page must have */

		/*判斷是否有權限*/
		boolean hastask = CheckRightService.findTask(request, rightsString);
		/*取當前頁TASK ID*/
		model.addAttribute("taskId", rightsString);
		if(!hastask) {
			
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_LOAD_PAGE_ERROR);
			model.addAttribute("redirectUrl","login");
			
			return "views/common/alert";
		}
		
		/** END -- All controller about web page must have */

		// 設置頁面功能表
		commonService.setCommonInfo(model, request);
		int id = Integer.parseInt(request.getParameter("sheet_id"));
		
		//取得表單
		Sheet sheet = sheetService.getSheetById(id);
		
		//取得上架審核人清單
		List<SheetApproval> approvalList = sheetApprovalService.getApplicantById(id);
		//取得停刊審核人清單
		List<SheetCancel> cancelList = sheetCancelService.getCancelApplicantById(id);
		

		//取得流程
		List<String> processList = commonService.getFormProcessFormType(sheet.getType(),sheet.getStatus());

		model.addAttribute("processList", processList);
		model.addAttribute("sheet", sheet);
		model.addAttribute("approvalList", approvalList);
		model.addAttribute("type",dataOption.getSheetTypeMap());
		model.addAttribute("status",dataOption.getSheetStatusMap());
		model.addAttribute("cancelList", cancelList);

		//TODO:未完成 要看如何顯示流程
		//取得最大Loop的相關的筆數
		if(sheet.getStatus().equals("3")) {
			model.addAttribute("processCount",-1);
			
		}else if(cancelList != null && cancelList.size()>0){
			long maxLoopCount = cancelList.stream().filter(l -> l
					.getLoop() == (cancelList.stream().max(Comparator.comparing(SheetCancel::getLoop)).get().getLoop()))
					.count();
			model.addAttribute("processCount", maxLoopCount);
		}else {
			model.addAttribute("processCount",approvalList.size());
		}
		
		return "views/recordAllSheet/history-detail-superior";
	 }

	@RequestMapping("/cancelSave")
	public String cancelSave(@ModelAttribute CloseSheetForm form, Model model, HttpServletRequest request ,BindingResult bindingResult){
		try {
			/** START -- All controller about web page must have */

			/*判斷是否有權限*/
			boolean hastask = CheckRightService.findTask(request, rightsString);
			/*取當前頁TASK ID*/
			model.addAttribute("taskId", rightsString);
			if(!hastask) {
				
				model.addAttribute("result",Constants.RESULT_ERROR);
				model.addAttribute("msg",MessageConstants.MESSAGE_LOAD_PAGE_ERROR);
				model.addAttribute("redirectUrl","login");
				
				return "views/common/alert";
			}
			
			/** END -- All controller about web page must have */

			// 設置頁面功能表
			commonService.setCommonInfo(model, request);
			Ldap ldap = setCommon(model, request);
			
			//取前端資料前置作業
			int id = Integer.parseInt(form.getScsbSheetId());

			Sheet sheet = sheetService.getSheetById(id);
			SheetCancel sheetCancel = new SheetCancel();
			BeanUtils.copyProperties(form, sheet);
			BeanUtils.copyProperties(form, sheetCancel);
			
			//檢核必填欄位
			formValidator.validate(form, bindingResult);
			if (bindingResult.hasErrors()) 
			{
				return "redirect:detail?sheet_id="+id;
			}
			
			//LDAP取得審核人清單
			List<Ldap> approverList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_APPROVERS);
			Map<String, String> ldapMap = new HashMap<String, String>();
			for(Ldap approver : approverList) {
				ldapMap.put(approver.getCn(), approver.getGivenName());
			}

			//取前端資料 sheet
			sheet.setId(id);
			sheet.setArchiveReason(form.getArchiveReason());
			sheet.setStatus("3"); //狀態 -已下架
			sheet.setModifier(ldap.getCn());
			sheet.setModifyTime(sheet.getDateTime());

			//取前端資料Cancel sheet
			sheetCancel.setScsbSheetId(id);
			sheetCancel.setApproverId(ldap.getCn());
//			TODO: 正式接的時候要改這個
//			sheetCancel.setApprover(ldapMap.get(ldap.getCn()));
			sheetCancel.setApprover("測試人");

			sheetCancel.setSort(0);
			sheetCancel.setStatus("3"); //狀態:緊急下架
			sheetCancel.setCreator(ldap.getCn());
			sheetCancel.setModifier(ldap.getCn());
			
			//取得停刊Loop
			List<SheetCancel> cancelList = sheetCancelService.getCancelApplicantById(id);
			int loop = 0 ;
			for(SheetCancel c : cancelList) {
				if(loop < c.getLoop()) {
					loop = c.getLoop();
				}
			}
			sheetCancel.setLoop(loop+1);
			
			sheetCancelService.save(sheetCancel);
			sheetService.save(sheet);
			
			return commonService.alertPageSetUp(model, Constants.RESULT_SUCCESS, MessageConstants.MESSAGE_INSERT_SUCCESS, Constants.RECORDSHEET_URL);

		} catch (Exception e) {
			LogUtil.setErrorLog(reFlieName + " save", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.RECORDSHEET_URL);
					
		}
	}
}