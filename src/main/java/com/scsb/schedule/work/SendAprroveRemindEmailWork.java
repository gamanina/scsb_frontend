package com.scsb.schedule.work;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scsb.config.Constants;
import com.scsb.model.Ldap;
import com.scsb.model.RemindEmailSetting;
import com.scsb.model.Sheet;
import com.scsb.model.vo.EmailRecipientVo;
import com.scsb.service.LdapService;
import com.scsb.service.RemindEmailSettingService;
import com.scsb.service.SendEmailService;
import com.scsb.service.SheetService;


/**
 * 寄送審核提醒email
 * @author Stan
 *
 */
@Component
public class SendAprroveRemindEmailWork 
{	
	@Autowired
	private SheetService sheetService;
	@Autowired
	private SendEmailService sendEmailService;
	@Autowired
	private RemindEmailSettingService remindEmailSettingService;
	@Autowired
	private LdapService ldapService;
	private Logger log = LogManager.getLogger();
	
    public void work() 
    {
    	
    	log.info("=== SendAprroveRemindEmailWork: start ===");
    	LocalDateTime now = LocalDateTime.now();
    	RemindEmailSetting emailSetting = remindEmailSettingService.get();
    	//設定檔為空
    	if(emailSetting == null) {
    		log.info("=== RemindEmailSetting is empty ===");
    		log.info("=== SendAprroveRemindEmailWork: end ===");
    		return;
    	}
    	//設定檔時間未到
    	if(emailSetting.getSettingHour() == now.getHour()) {
    		log.info("=== RemindEmailSetting time is not up yet  ===");
    		log.info("=== SendAprroveRemindEmailWork: end ===");
    		return;
    	}
    	try 
    	{
    		List<String> statusList = new ArrayList<String>();
    		statusList.add(Constants.SHEET_STATUS_PROCESSING);
    		statusList.add(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING);
    		List<Sheet> remindSheetList = sheetService.getSheetByStatus(statusList);
			if (remindSheetList.size() == 0)
			{
				log.info("=== Info: remindSheetList.size() == 0 ===");
				log.info("=== SendAprroveRemindEmailWork: end ===");
				return;
			}
			Map<String,EmailRecipientVo> emailMap = new HashMap<String,EmailRecipientVo>();
			//所有未審核表單
			for (Sheet sheet : remindSheetList)
			{
				try 
		    	{
					Ldap approver = ldapService.getDataByEmpNo(sheet.getNextApproverId());
					Ldap agent = ldapService.getDataByEmpNo(sheet.getAgentId());
					//收件人為空則忽略
					if(approver == null) {
						log.info("=== sheet send remind email error sheet id:" + sheet.getId() + " ===");
						log.info("=== approver null error approver cn:" + sheet.getNextApproverId() + " ===");
						continue;
					}
					processEmailContent(emailMap,sheet,sheet.getNextApproverId(),approver);
					if(agent == null) {
						log.info("=== sheet send remind email error sheet id:" + sheet.getId() + " ===");
						log.info("=== agent null error agent cn:" + sheet.getAgentId() + " ===");
					} else {
						//設置代理人email參數
						processEmailContent(emailMap,sheet,sheet.getAgentId(),agent);
					}
//					sendEmailService.sendRemindApproveEmail(approver.getMail(),agent.getMail());
					
		    	}
				catch (Exception e)
				{
					log.info("=== SendAprroveRemindEmailWork has error ===");
				}
			}
			//將整理好結果寄出
			for (Map.Entry<String,EmailRecipientVo> entry : emailMap.entrySet()) {
				sendEmailService.sendRemindApproveEmail(entry.getValue());
			}
    	}
    	catch (Exception e) 
    	{
			log.info("=== SendAprroveRemindEmailWork has error ===");
		}
    	log.info("=== SendAprroveRemindEmailWork: end ===");
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