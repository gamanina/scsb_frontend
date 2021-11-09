package com.scsb.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 
 * 建立日期：2021/04/15
 * 程式摘要：com.scsb.model
 * 類別名稱：ManagerTask.java 
 * 程式內容說明：角色物件
 * @author Cindy
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "SCSB_MANAGER_TASK")
public class ManagerTask {
	
	@Id
	@GeneratedValue
	private String id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="PARENT_ID")
	private String parentId;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="SORT")
	private int sort;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="IMAGES")
	private String images;

//	@Transient
//	private List<ManagerTask> childTask;
	
	@Transient
	private Map<String,List<ManagerTask>> childTaskMap;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

//	public List<ManagerTask> getChildTask() {
//		return childTask;
//	}
//
//	public void setChildTask(List<ManagerTask> childTask) {
//		this.childTask = childTask;
//	}

	public Map<String, List<ManagerTask>> getChildTaskMap() {
		return childTaskMap;
	}

	public void setChildTaskMap(Map<String, List<ManagerTask>> childTaskMap) {
		this.childTaskMap = childTaskMap;
	}
	
	
	
}
