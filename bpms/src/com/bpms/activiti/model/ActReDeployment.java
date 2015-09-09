package com.bpms.activiti.model;

import java.util.Date;

/**
 * @author chenfl
 * @fileName ActReDeployment.java
 * @createTime 2015年5月13日 下午11:29:55
 * @description 流程部署对象
 */
public class ActReDeployment {
	private String id;
	private String name;
	private String category;
	private String tenantId;
	private Date deploymentTime;

	public ActReDeployment() {
		super();
	}

	public ActReDeployment(String id, String name, String category,
			String tenantId, Date deploymentTime) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.tenantId = tenantId;
		this.deploymentTime = deploymentTime;
	}

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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public Date getDeploymentTime() {
		return deploymentTime;
	}

	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}
}