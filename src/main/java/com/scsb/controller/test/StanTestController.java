package com.scsb.controller.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsb.config.Constants;
import com.scsb.model.Ldap;
import com.scsb.model.ManagerTask;
import com.scsb.model.RemindEmailSetting;
import com.scsb.model.Sheet;
import com.scsb.model.repository.ManagerRepository;
import com.scsb.model.vo.EmailRecipientVo;
import com.scsb.service.CommonService;
import com.scsb.service.LdapService;
import com.scsb.service.RemindEmailSettingService;
import com.scsb.service.SendEmailService;
import com.scsb.service.SheetService;

@Controller
@RequestMapping("/")
public class StanTestController 
{	
	@Autowired
	private SheetService sheetService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private RemindEmailSettingService remindEmailSettingService;
	@Autowired
	private LdapService ldapService;
	// TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	@RequestMapping("/stantest")
	public String subAgencyList(Model model, HttpServletRequest request) throws Exception 
	{
		//TODO(帶刪除)
//		sendEmailService.sendRemindApproveEmail("stan100522@hotmail.com","evilbuo0212@gmail.com");
//		RemindEmailSetting data = remindEmailSettingService.get();
		
		List<String> statusList = new ArrayList<String>();
		statusList.add(Constants.SHEET_STATUS_PROCESSING);
		statusList.add(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING);
		List<Sheet> remindSheetList = sheetService.getSheetByStatus(statusList);
		if (remindSheetList.size() == 0)
		{
			return "views/test";
		}
		Map<String,EmailRecipientVo> emailMap = new HashMap<String,EmailRecipientVo>();
		//所有未審核表單
		for (Sheet sheet : remindSheetList)
		{
			try 
	    	{
				Ldap approver ;
				Ldap agent ;
				if(isTest) {
					approver = ldapService.getFakeDataByEmpNo(sheet.getNextApproverId());
					agent = ldapService.getFakeDataByEmpNo(sheet.getAgentId());
				} else {
					approver = ldapService.getDataByEmpNo(sheet.getNextApproverId());
					agent = ldapService.getDataByEmpNo(sheet.getAgentId());
				}

				//收件人為空則忽略
				if(approver == null) {
					continue;
				}
				processEmailContent(emailMap,sheet,sheet.getNextApproverId(),approver);
				if(agent == null) {
				} else {
					//設置代理人email參數
					processEmailContent(emailMap,sheet,sheet.getAgentId(),agent);
				}
//				sendEmailService.sendRemindApproveEmail(approver.getMail(),agent.getMail());
				
	    	}
			catch (Exception e)
			{
				System.out.println("error");
			}
		}
		//將整理好結果寄出
		for (Map.Entry<String,EmailRecipientVo> entry : emailMap.entrySet()) {
			sendEmailService.sendRemindApproveEmail(entry.getValue());
		}
	

		return "views/test";
	}
	
	private void processEmailContent(Map<String, EmailRecipientVo> emailMap, Sheet sheet, String recipientId, Ldap recipient) {
		EmailRecipientVo emailRecipient = new EmailRecipientVo();
		if(emailMap.containsKey(recipientId)) {
			 emailRecipient = emailMap.get(recipientId);
			emailRecipient.setSheetCount(emailRecipient.getSheetCount()+1);
			//若是下架審核優先取代
			if(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING.contentEquals(sheet.getStatus())) {
				emailRecipient.setEmailUrlType(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING);
			}
			//原是上架審核
			if(Constants.SHEET_STATUS_PROCESSING.contentEquals(sheet.getStatus()) && Constants.SHEET_STATUS_PROCESSING.contentEquals(emailRecipient.getEmailUrlType())) {
				emailRecipient.setEmailUrlType(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING);
			}
			emailRecipient.setRecipientEmail(recipient.getMail());
			
		} else {
			emailRecipient.setSheetCount(1);
			emailRecipient.setEmailUrlType(sheet.getStatus());
			emailRecipient.setRecipientEmail(recipient.getMail());
			emailMap.put(recipientId, emailRecipient);
		}
		
	}
}