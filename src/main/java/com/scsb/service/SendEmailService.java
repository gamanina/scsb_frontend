package com.scsb.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.scsb.config.Constants;
import com.scsb.model.Ldap;
import com.scsb.model.vo.EmailRecipientVo;
import com.scsb.util.LogUtil;


@Service
public class SendEmailService {
	
	@Value("${service.domain}")
	private String serviceDomain;
	
	@Value("${from.email}")
	private String fromEmail;
	
	// TODO 刪除
	@Value("${is.test}")
	private boolean isTest;
	
	@Autowired
	private TemplateEngine templateEngine;

	private JavaMailSender javaMailSender;

	@Autowired
	public SendEmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * 寄送新表單審核通知email
	 * @param request
	 * @param nextApproverId
	 * @param agentId
	 * @throws Exception 
	 */
	public void sendSheetApproveEmail(HttpServletRequest request, String nextApproverId, String agentId) throws Exception {
		List<Ldap> approverList = (List<Ldap>) request.getSession().getAttribute(Constants.SESSION_APPROVERS);
		Map<String, String> emailMap = approverList.stream().collect(Collectors.toMap(Ldap::getCn, Ldap::getMail));
		String approverEmail = emailMap.get(nextApproverId);
		String agentEmail = emailMap.get(agentId);
		sendRemindApproveEmail(approverEmail,agentEmail);
	}
	
	/**
	 * 寄送新表單審核通知email
	 * @param request
	 * @param nextApproverId
	 * @param agentId
	 * @throws Exception 
	 */
	public void sendRemindApproveEmail(EmailRecipientVo vo) throws Exception {
		String subject = "主網站後台管理系統待審核通知"; 
		Map<String,String> map = new HashMap<String,String>();
		map.put("subject", subject);
    	map.put("sheetCount", vo.getSheetCount()+"");
    	if(Constants.SHEET_STATUS_OFF_SHELF_PROCESSING.equals(vo.getEmailUrlType())){
    		map.put("URL", serviceDomain + Constants.CANCELSHEET_URL);
    	}
    	if(Constants.SHEET_STATUS_PROCESSING.equals(vo.getEmailUrlType())){
    		map.put("URL", serviceDomain + Constants.PENDINGSHEET_URL);
    	}
    	
		List<String> toList = new ArrayList<String>();
		if(isTest) {
			//測試寄給測試email
			toList.add(fromEmail);
		} else {
			toList.add(vo.getRecipientEmail());
		}
		sendthymeleafEmail(toList,subject,"email/remindEmail.html",map);
	}
	
	/**
	 * 寄送新表單審核通知email
	 * @param request
	 * @param nextApproverId
	 * @param agentId
	 * @throws Exception 
	 */
	public void sendRemindApproveEmail(String approverEmail, String agentEmail) throws Exception {
		String subject = "主網站後台管理系統待審核通知"; 
		String text = "您好，您有待審核的申請，請您盡速燈入主網站後台管理系統審核";
		Map<String,String> map = new HashMap<String,String>();
    	map.put("text", text);
		List<String> toList = new ArrayList<String>();
		toList.add(approverEmail);
		toList.add(agentEmail);
    	sendthymeleafEmail(toList,subject,"email/remindEmail.html",map);
//		sendEmail(Arrays.asList(approverEmail,agentEmail),subject,text);

	}
	
	/**
	 * 發送Email
	 * @param to
	 * @param subject
	 * @param text
	 */
	public void sendEmail(String to, String subject, String text) throws Exception{
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, Constants.CHARSET_UTF8);
			helper.setFrom(fromEmail);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text, true);
			javaMailSender.send(message);
			LogUtil.setActionLog("Send Email: " + to, "subject: " + subject + " content: " + text);
		} catch (Exception e) {
			LogUtil.setErrorLog("EmailUtil.sendEmail", e);
		}
	}
	
	/**
	 * 發送Email
	 * @param to
	 * @param subject
	 * @param text
	 */
	public void sendEmail(List<String> toList, String subject, String text) throws Exception{
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, Constants.CHARSET_UTF8);
			helper.setFrom(fromEmail);
			//helper.setTo(InternetAddress.parse("email1@test.com,email2@test.com"))
			//helper.setTo(new String[]{"email1@test.com", "email2@test.com"});
			helper.setTo(toList.toArray(new String[0]));
			helper.setSubject(subject);
			helper.setText(text, true);
			javaMailSender.send(message);
			LogUtil.setActionLog("Send Email: " + toList.toArray(new String[0]), "subject: " + subject + " content: " + text);
		} catch (Exception e) {
			LogUtil.setErrorLog("EmailUtil.sendEmail", e);
		}
	}

	
	/**
	 * 使用html模板發送email
	 * @param to
	 * @param subject
	 * @param tmpFile
	 * @param variables
	 * @throws Exception
	 */
	public void sendthymeleafEmail(List<String> toList, String subject , String tmpFile , Map<String,String> variables) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true , Constants.CHARSET_UTF8);//有些地方會亂碼改成GBK
		helper.setFrom(fromEmail);
		helper.setTo(toList.toArray(new String[0]));
		helper.setSubject(subject);
		Context context = new Context();
		//給模板傳入引數，key要與模板中變數名一致
		for (Map.Entry<String, String> entry : variables.entrySet()) {
			context.setVariable(entry.getKey(), entry.getValue());
		}
		//thymeleaf模板預設會從src/main/resources/tempaltes目錄下尋找檔案，填入我們定義的模板名，不需要寫字尾。
		String template = templateEngine.process(tmpFile, context);
		helper.setText(template, true);
		javaMailSender.send(message);
    }
}
