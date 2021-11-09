package com.scsb.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
 * 建立日期：2021/04/15
 * 程式摘要：com.scsb.model
 * 類別名稱：Manager.java 
 * 程式內容說明：後台帳號物件
 * @author Cindy
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "SCSB_MANAGER")
public class Manager {
	
	@Id
	@GeneratedValue
	private int id;
	
	//@Column(name="zhho")
	@Column(name="ZHHO")
	private String zhho; //帳號
	
	//@Column(name="mima")
	@Column(name="MIMA")
	private String mima; //密碼
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	private ManagerRole role; //角色
	
	//@Column(name="status")
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="CREATE_TIME")
	private Timestamp create_time;
	
	// LADP物件區域
	@Transient
	private Ldap ldap;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZhho() {
		return zhho;
	}

	public void setZhho(String zhho) {
		this.zhho = zhho;
	}

	public String getMima() {
		return mima;
	}

	public void setMima(String mima) {
		this.mima = mima;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ManagerRole getRole() {
		return role;
	}

	public void setRole(ManagerRole role) {
		this.role = role;
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

	public Ldap getLdap() {
		return ldap;
	}

	public void setLdap(Ldap ldap) {
		this.ldap = ldap;
	}
}
