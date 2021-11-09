package com.scsb.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
 * 建立日期：2021/04/15
 * 程式摘要：com.scsb.model
 * 類別名稱：ManagerRole.java 
 * 程式內容說明：角色物件
 * @author Cindy
 * @version 1.0
 * @since 1.0
 */

@Entity
@Table(name = "SCSB_MANAGER_ROLE")
@Cacheable
public class ManagerRole {
	
	@Id
	@GeneratedValue
	private String id;
	@Column(name="NAME")
	private String name;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CREATOR")
	private String creator;
	
	@Column(name="CREATE_TIME")
	private Timestamp create_time;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "SCSB_MANAGER_ROLE_TASK",
			joinColumns = {@JoinColumn(name = "ROLE_ID")},
			inverseJoinColumns = {@JoinColumn(name = "TASK_ID")}
	)
	private List<ManagerTask> tasks;
	@Transient
	private Map<String, List<ManagerTask>> tasksMap;
	
	public String getId() {
		return id;
	}
	public String setId(String id) {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public List<ManagerTask> getTasks() {
		return tasks;
	}
	public void setTasks(List<ManagerTask> tasks) {
		this.tasks = tasks;
	}
	public Map<String, List<ManagerTask>> getTasksMap() {
		return tasksMap;
	}
	public void setTasksMap(Map<String, List<ManagerTask>> tasksMap) {
		this.tasksMap = tasksMap;
	}
	
	

	
}
