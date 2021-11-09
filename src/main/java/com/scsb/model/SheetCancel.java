package com.scsb.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * 建立日期：2020/05/24
 * 程式摘要：com.scsb.model
 * 類別名稱：scsbSheetCancel.java 
 * 程式內容說明：停刊簽核紀錄 物件
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "SCSB_SHEET_CANCEL")
public class SheetCancel
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
	@SequenceGenerator(name = "id_seq", sequenceName = "SCSB_SHEET_CANCEL_ID_SEQ", allocationSize = 1)
	private int id;
	@Column(name="SCSB_SHEET_ID")
	private int scsbSheetId;
	@Column(name="APPROVER_ID")
	private String approverId;
	@Column(name="APPROVER")
	private String approver;
	@Column(name="SORT")
	private int sort;
	@Column (name="STATUS")
	private String status;
	@Column(name="CREATOR")
	private String creator;
	@Column(name="CREATE_TIME", insertable=false, updatable=false)
	private Timestamp createTime;
	@Column(name="MODIFIER")
	private String modifier;
	@Column(name="MODIFY_TIME", insertable=false, updatable=false)
	private Timestamp modifyTime;
	@Column(name="LOOP")
	private int loop;
	
	public SheetCancel()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScsbSheetId() {
		return scsbSheetId;
	}

	public void setScsbSheetId(int scsbSheetId) {
		this.scsbSheetId = scsbSheetId;
	}

	public String getApproverId() {
		return approverId;
	}

	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
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

	public int getLoop() {
		return loop;
	}

	public void setLoop(int loop) {
		this.loop = loop;
	}
}
