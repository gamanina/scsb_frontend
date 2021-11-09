package com.scsb.controller.indexWinners;

import org.springframework.web.multipart.MultipartFile;

/**
 * 中獎名單表單物件
 */
public class IndexWinnersForm 
{
	private String applicantId;
	private String applicant;
	private String applicantUnitId;
	private String applicantUnit;
	private String onTimeDate;
	private String onTimeHour;
	private String onTimeMin;
	private String offTimeDate;
	private String offTimeHour;
	private String offTimeMin;
	private String nextApproverId;
	private String nextApprover;
	private String agentId;
	private String agent;
	private String title;
	private MultipartFile imageFile;
	private String imageUrl;
	private MultipartFile file;
	private MultipartFile file2;
	private MultipartFile file3;
	private MultipartFile file4;
	private MultipartFile file5;
	private String content;

	public String getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	
	public String getApplicantUnitId() {
		return applicantUnitId;
	}

	public void setApplicantUnitId(String applicantUnitId) {
		this.applicantUnitId = applicantUnitId;
	}

	public String getApplicantUnit() {
		return applicantUnit;
	}

	public void setApplicantUnit(String applicantUnit) {
		this.applicantUnit = applicantUnit;
	}

	public String getOnTimeDate() {
		return onTimeDate;
	}

	public void setOnTimeDate(String onTimeDate) {
		this.onTimeDate = onTimeDate;
	}

	public String getOnTimeHour() {
		return onTimeHour;
	}

	public void setOnTimeHour(String onTimeHour) {
		this.onTimeHour = onTimeHour;
	}

	public String getOnTimeMin() {
		return onTimeMin;
	}

	public void setOnTimeMin(String onTimeMin) {
		this.onTimeMin = onTimeMin;
	}

	public String getOffTimeDate() {
		return offTimeDate;
	}

	public void setOffTimeDate(String offTimeDate) {
		this.offTimeDate = offTimeDate;
	}

	public String getOffTimeHour() {
		return offTimeHour;
	}

	public void setOffTimeHour(String offTimeHour) {
		this.offTimeHour = offTimeHour;
	}

	public String getOffTimeMin() {
		return offTimeMin;
	}

	public void setOffTimeMin(String offTimeMin) {
		this.offTimeMin = offTimeMin;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public MultipartFile getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}

	public MultipartFile getFile3() {
		return file3;
	}

	public void setFile3(MultipartFile file3) {
		this.file3 = file3;
	}

	public MultipartFile getFile4() {
		return file4;
	}

	public void setFile4(MultipartFile file4) {
		this.file4 = file4;
	}

	public MultipartFile getFile5() {
		return file5;
	}

	public void setFile5(MultipartFile file5) {
		this.file5 = file5;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
