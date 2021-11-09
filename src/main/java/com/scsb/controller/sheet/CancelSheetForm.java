package com.scsb.controller.sheet;

import org.springframework.web.multipart.MultipartFile;

/**
 * 申請下架表單物件
 */
public class CancelSheetForm 
{
	private String scsbSheetId;
	private String nextApproverId;
	private String nextApprover;
	private String agentId;
	private String agent;
	private String archiveReason;
	
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
	public String getArchiveReason() {
		return archiveReason;
	}
	public void setArchiveReason(String archiveReason) {
		this.archiveReason = archiveReason;
	}
	
	

}
