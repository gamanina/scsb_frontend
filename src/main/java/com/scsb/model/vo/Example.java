package com.scsb.model.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * 建立日期：2020/04/15
 * 程式摘要：com.scsb.model.vo
 * 類別名稱：Example.java 
 * 程式內容說明：範例 物件
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "tb_example")
public class Example
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="status", insertable=false)
	private String status;
	@Column(name="creator")
	private String creator;
	@Column(name="create_time", insertable=false, updatable=false)
	private Timestamp create_time;
	@Column(name="modifier")
	private String modifier;
	@Column(name="modify_time", insertable=false, updatable=false)
	private Timestamp modify_time;
	@Transient
	private List<Example> exampleList;
	
	public Example()
	{
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public Timestamp getModify_time() {
		return modify_time;
	}

	public void setModify_time(Timestamp modify_time) {
		this.modify_time = modify_time;
	}
}
