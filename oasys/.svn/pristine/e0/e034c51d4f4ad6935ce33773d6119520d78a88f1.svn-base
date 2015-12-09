package com.oasys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_oa_fd_credentials_stamp_app")
@DynamicUpdate(true)
@DynamicInsert(true)
public class CredentialsApp implements Cloneable{
	private Integer uqaId;
	private String appNo;
	private Integer applicantNo;
	private Integer deptNo;
	private Date appDate;
	private String appStatus;
	private String procStatus;
	private String remark;
	
	@Column(name = "UQA_ID", length = 10,unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getUqaId() {
		return uqaId;
	}
	
	public void setUqaId(Integer uqaId) {
		this.uqaId = uqaId;
	}
	
	@Column(name = "APP_NO", length = 25)
	public String getAppNo() {
		return appNo;
	}
	
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
	@Column(name = "APPLICANT_NO", length = 11)
	public Integer getApplicantNo() {
		return applicantNo;
	}
	
	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}
	
	@Column(name = "DEPT_NO", length = 11)
	public Integer getDeptNo() {
		return deptNo;
	}
	
	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	
	@Column(name = "APP_DATE")
	public Date getAppDate() {
		return appDate;
	}
	
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return appStatus;
	}
	
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "PROC_STATUS", length = 1)
	public String getProcStatus() {
		return procStatus;
	}
	
	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
