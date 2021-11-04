package com.src.scsb.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.src.scsb.util.LogUtil;


/**
 * 
 * 建立日期：2020/04/15
 * 程式摘要：com.scsb.model
 * 類別名稱：scsbSheet.java 
 * 程式內容說明：表單 物件
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "SCSB_SHEET")
public class Sheet
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	@SequenceGenerator(name = "id_seq", sequenceName = "SCSB_SHEET_ID_SEQ", allocationSize = 1)
	private int id;
	@Column(name="\"TYPE\"")
	private String type;
	@Column(name="APPLICANT_UNIT_ID")
	private String applicantUnitId;
	@Column(name="APPLICANT_UNIT")
	private String applicantUnit;
	@Column(name="APPLICANT_ID")
	private String applicantId;
	@Column(name="APPLICANT")
	private String applicant;
	@Column(name="ON_TIME")
	private Timestamp onTime;
	@Column(name="OFF_TIME")
	private Timestamp offTime;
	@Column(name="NEXT_APPROVER_ID")
	private String nextApproverId;
	@Column(name="NEXT_APPROVER")
	private String nextApprover;
	@Column(name="AGENT_ID")
	private String agentId;
	@Column(name="AGENT")
	private String agent;
	@Column(name="TITLE")
	private String title;
	@Column(name="IMAGE")
	private String image;
	@Column(name="IMAGE_MOBILE")
	private String imageMobile;
	@Column(name="IMAGE_URL")
	private String imageUrl;
	@Column(name="\"FILE\"")
	private String file;
	@Column(name="\"FILE2\"")
	private String file2;
	@Column(name="\"FILE3\"")
	private String file3;
	@Column(name="\"FILE4\"")
	private String file4;
	@Column(name="\"FILE5\"")
	private String file5;
	@Column(name="CONTENT")
	private String content;
	@Column(name="ARCHIVE_REASON")
	private String archiveReason;
	@Column(name="STEP")
	private int step;
	@Column(name="STATUS")
	private String status;
	@Column(name="CREATOR")
	private String creator;
	@Column(name="CREATE_TIME", insertable=false, updatable=false)
	private Timestamp createTime;
	@Column(name="MODIFIER")
	private String modifier;
	@Column(name="MODIFY_TIME", insertable=false, updatable=false)
	private Timestamp modifyTime;
	
	@Transient
	private String imageName;
	@Transient
	private String fileName;
	
	public Sheet()
	{
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Timestamp getOnTime() {
		return onTime;
	}

	public void setOnTime(Timestamp onTime) {
		this.onTime = onTime;
	}

	public Timestamp getOffTime() {
		return offTime;
	}

	public void setOffTime(Timestamp offTime) {
		this.offTime = offTime;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImageMobile() {
		return imageMobile;
	}

	public void setImageMobile(String imageMobile) {
		this.imageMobile = imageMobile;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	public String getFile3() {
		return file3;
	}

	public void setFile3(String file3) {
		this.file3 = file3;
	}

	public String getFile4() {
		return file4;
	}

	public void setFile4(String file4) {
		this.file4 = file4;
	}

	public String getFile5() {
		return file5;
	}

	public void setFile5(String file5) {
		this.file5 = file5;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getArchiveReason() {
		return archiveReason;
	}

	public void setArchiveReason(String archiveReason) {
		this.archiveReason = archiveReason;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getImageName() 
	{
		if (StringUtils.isBlank(getImage()))
		{
			return "";
		}
		return getImage().substring(getImage().lastIndexOf("/") + 1);
	}

	public String getImageMobileName() 
	{
		if (StringUtils.isBlank(getImageMobile()))
		{
			return "";
		}
		return getImageMobile().substring(getImageMobile().lastIndexOf("/") + 1);
	}
	
	public String getFileName() {
		if (StringUtils.isBlank(getFile()))
		{
			return "";
		}
		return getFile().substring(getFile().lastIndexOf("/") + 1);
	}
	
	public String getFile2Name() {
		if (StringUtils.isBlank(getFile2()))
		{
			return "";
		}
		return getFile2().substring(getFile2().lastIndexOf("/") + 1);
	}
	
	public String getFile3Name() {
		if (StringUtils.isBlank(getFile3()))
		{
			return "";
		}
		return getFile3().substring(getFile3().lastIndexOf("/") + 1);
	}
	
	public String getFile4Name() {
		if (StringUtils.isBlank(getFile4()))
		{
			return "";
		}
		return getFile4().substring(getFile4().lastIndexOf("/") + 1);
	}
	
	public String getFile5Name() {
		if (StringUtils.isBlank(getFile5()))
		{
			return "";
		}
		return getFile5().substring(getFile5().lastIndexOf("/") + 1);
	}
	
	public Long getLongTime() {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = sdf.parse(createTime.toString());
		} catch (ParseException e) {
			LogUtil.setErrorLog("CreatLongTime: ", e);
		}
		long time = date.getTime();
		
		return time;
	}
	
	public Timestamp getDateTime(){
		Timestamp date = new Timestamp(System.currentTimeMillis()); 

		return date;
	}

	@Override
	public String toString() {
		return "Sheet [id=" + id + ", type=" + type + ", applicantUnitId=" + applicantUnitId + ", applicantUnit="
				+ applicantUnit + ", applicantId=" + applicantId + ", applicant=" + applicant + ", onTime=" + onTime
				+ ", offTime=" + offTime + ", nextApproverId=" + nextApproverId + ", nextApprover=" + nextApprover
				+ ", agentId=" + agentId + ", agent=" + agent + ", title=" + title + ", image=" + image
				+ ", imageMobile=" + imageMobile + ", imageUrl=" + imageUrl + ", file=" + file + ", file2=" + file2
				+ ", file3=" + file3 + ", file4=" + file4 + ", file5=" + file5 + ", content=" + content
				+ ", archiveReason=" + archiveReason + ", step=" + step + ", status=" + status + ", creator=" + creator
				+ ", createTime=" + createTime + ", modifier=" + modifier + ", modifyTime=" + modifyTime
				+ ", imageName=" + imageName + ", fileName=" + fileName + "]";
	}
	
	
}
