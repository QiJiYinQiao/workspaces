package com.oasys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * StatusNameRef entity. 
 * @author LiDa
 * @date 2015/9/21
 * @version 1.0
 */
@Entity
@Table(name = "t_oa_app_status_name_ref")
public class StatusNameRef implements java.io.Serializable
{

	private static final long serialVersionUID = -8206614796715970637L;
	
	private Integer refId;
	private String appStatusCode;
	private String appStatusName;
	private String remark;
	
	public StatusNameRef() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatusNameRef(Integer refId, String appStatusCode,
			String appStatusName, String remark) {
		super();
		this.refId = refId;
		this.appStatusCode = appStatusCode;
		this.appStatusName = appStatusName;
		this.remark = remark;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "REF_ID", unique = true, nullable = false,length=10)
	public Integer getRefId() {
		return refId;
	}
	public void setRefId(Integer refId) {
		this.refId = refId;
	}
	@Column(name = "APP_STATUS_CODE", length = 80)
	public String getAppStatusCode() {
		return appStatusCode;
	}
	public void setAppStatusCode(String appStatusCode) {
		this.appStatusCode = appStatusCode;
	}
	@Column(name = "APP_STATUS_NAME", length = 50)
	public String getAppStatusName() {
		return appStatusName;
	}
	public void setAppStatusName(String appStatusName) {
		this.appStatusName = appStatusName;
	}
	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
}