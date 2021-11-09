package com.scsb.controller.pendingSheet;

import org.springframework.web.multipart.MultipartFile;

/**
 * 待核表單物件
 */
public class PendingSheetForm 
{
	private String scsbSheetId;
	private String nextApproverId;
	private String nextApprover;
	private String agentId;
	private String agent;
	private MultipartFile file;
	private String content;
	private String check;

	public String getScsbSheetId() {
		return scsbSheetId;
	}

	public void setScsbSheetId(String scsbSheetId) {
		this.scsbSheetId = scsbSheetId;
	}

	public String getNextApproverId() {
		return nextApproverId;
	}

	public void setNextApproverId(String nextApproverId) {
		this.nextApproverId = nextApproverId;
	}

	public String getNextApprover() {
		return nextApprover;
	}

	public void setNextApprover(String nextApprover) {
		this.nextApprover = nextApprover;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}
}
