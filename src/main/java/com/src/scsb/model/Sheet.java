package com.src.scsb.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;


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
@Data
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
	@Column(name="CATEGORY")
	private String category;
	
	@Transient
	private String categoryName;
	@Transient
	private String imageName;
	@Transient
	private String fileName;
	@Transient
	private String file1Name;
	@Transient
	private String file2Name;
	@Transient
	private String file3Name;
	@Transient
	private String file4Name;
	@Transient
	private String file5Name;
	
	
}
