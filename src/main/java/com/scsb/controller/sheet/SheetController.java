package com.scsb.controller.sheet;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import javax.naming.LinkLoopException;
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
import com.scsb.controller.sheetSuperior.AllSheetForm;
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

//import oracle.net.aso.c;
//import oracle.net.aso.l;

@Controller
@RequestMapping("/recordSheet")
public class SheetController {

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
	private CancelSheetValidator formValidator;

	/** All controller about web page must have */
	protected final String reFlieName = "sheet";
	protected final String rightsString = Constants.PAGE_KEY_HISTORY;

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
	 public String recordSheet(@ModelAttribute AllSheetForm form,Model model, HttpServletRequest request){
		
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
			
		
		Manager member = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		
		if(member == null) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_RIGHTS_ERROR);
			model.addAttribute("redirectUrl","login");
			
			return "views/common/alert";
		}
		

		model.addAttribute("msg",Constants.MSG_NO_CONDITION);
		model.addAttribute("type",dataOption.getSheetTypeMap());
		model.addAttribute("status",dataOption.getSheetStatusMap());
		
		
		return "views/recordSheet/history";
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
			
		
		Manager member = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		
		if(member == null) {
			LogUtil.setActionLog("member NULL error: ", "rights: " + reFlieName );
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_RIGHTS_ERROR, Constants.LOGIN_URL);
	
		}
		
		//取得登入者ID
		List<Sheet> sheets = null;
		String memberId = String.valueOf(member.getLdap().getCn());
		
		sheets = sheetService.getSheetByApplicant(memberId,form);
		List<Sheet> sheetList = sheets.stream().distinct().collect(Collectors.toList());

		model.addAttribute("msg",Constants.MSG_NO_DATA);
		model.addAttribute("sheetList",sheetList);
		model.addAttribute("type",dataOption.getSheetTypeMap());
		model.addAttribute("status",dataOption.getSheetStatusMap());
		
		
		return "views/recordSheet/history";
	 }
	
	@RequestMapping("/detail")
	 public String recordSheetDetail(@ModelAttribute Sheet sheet, @ModelAttribute CancelSheetForm form, Model model, HttpServletRequest request){
		
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
		sheet = sheetService.getSheetById(id);

		//取得審核紀錄清單
		List<SheetApproval> approvalList = sheetApprovalService.getApplicantById(id);
		//取得停刊審核人清單
		List<SheetCancel> cancelList = sheetCancelService.getCancelApplicantById(id);
		
		
		//取得登入者資訊，判斷只有參與上架審核者才能看到下架按鈕
		Manager member = (Manager) request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		String memberId = String.valueOf(member.getLdap().getCn());
		Boolean offShelf = false;

		for(SheetApproval s : approvalList) {
			if(memberId.equals(s.getApproverId())) {
				offShelf = true;
			}
		}
		
		
		if(sheet == null || approvalList==null) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_RIGHTS_ERROR);
			model.addAttribute("redirectUrl","list");
			
			return "views/common/alert";
		}
		
		//取得流程
		List<String> processList = commonService.getFormProcessFormType(sheet.getType(),sheet.getStatus());

		model.addAttribute("processList", processList);
		model.addAttribute("sheet", sheet);
		model.addAttribute("approvalList", approvalList);
		model.addAttribute("offShelf",offShelf);
		model.addAttribute("type",dataOption.getSheetTypeMap());
		model.addAttribute("status",dataOption.getSheetStatusMap());
		model.addAttribute("cancelList", cancelList);
		
		//取得最大Loop的相關的筆數
		if(sheet.getStatus().equals("3")) {
			model.addAttribute("processCount",-1);
			
		}else if(cancelList != null && cancelList.size()>0) {
			long maxLoopCount = cancelList.stream()
								.filter(l -> l.getLoop()== (cancelList.stream().max(Comparator.comparing(SheetCancel::getLoop)).get().getLoop()))
								.count();
			model.addAttribute("processCount",maxLoopCount);
		}else {
			model.addAttribute("processCount",approvalList.size());
		}
		
		
		//LDAP取得審核人清單 : 停刊審核人挑選
		List<Ldap> approverList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_APPROVERS);
        model.addAttribute("approverList", approverList);
        
        
		return "views/recordSheet/history-detail";
	 }
	
	@RequestMapping("/cancelSave")
	public String cancelSave(@ModelAttribute CancelSheetForm form, Model model, HttpServletRequest request ,BindingResult bindingResult){
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
			sheet.setNextApproverId(form.getNextApproverId());
			sheet.setNextApprover(ldapMap.get(form.getNextApproverId()));
			sheet.setAgentId(form.getAgentId());
			sheet.setAgent(ldapMap.get(form.getAgentId()));
			sheet.setArchiveReason(form.getArchiveReason());
			sheet.setStep(1); //審核階段
			sheet.setStatus("4"); //狀態 -下架處理中
			sheet.setModifier(ldap.getCn());
			sheet.setModifyTime(sheet.getDateTime());

			//取前端資料Cancel sheet
			sheetCancel.setScsbSheetId(id);
			sheetCancel.setApproverId(ldap.getCn());

			sheetCancel.setApprover(ldapMap.get(ldap.getCn()));
//			sheetCancel.setApprover("測試人");

			sheetCancel.setSort(0);
			sheetCancel.setStatus("0");
			sheetCancel.setCreator(ldap.getCn());
			sheetCancel.setModifier(ldap.getCn());
			
			//取得停刊Loop
			List<SheetCancel> cancelList = sheetCancelService.getCancelApplicantById(id);
			if (cancelList != null && !cancelList.isEmpty())
			{
				int maxLoop = cancelList.stream().max(Comparator.comparing(SheetCancel::getLoop)).get().getLoop();
				sheetCancel.setLoop(maxLoop + 1);
			}
			else
			{
				sheetCancel.setLoop(Constants.SORT_0);
			}

			sheetCancelService.save(sheetCancel);
			sheetService.save(sheet);
			
			return commonService.alertPageSetUp(model, Constants.RESULT_SUCCESS, MessageConstants.MESSAGE_INSERT_SUCCESS, Constants.RECORDSHEET_URL);

		} catch (Exception e) {
			LogUtil.setErrorLog(reFlieName + " save", e);
			return commonService.alertPageSetUp(model, Constants.RESULT_ERROR, MessageConstants.MESSAGE_SYSTEM_ERROR, Constants.RECORDSHEET_URL);
					
		}
		
			}
}