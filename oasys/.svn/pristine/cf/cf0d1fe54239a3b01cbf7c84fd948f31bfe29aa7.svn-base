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
@Table(name = "t_oa_fd_credentials_stamp_app_attach")
@DynamicUpdate(true)
@DynamicInsert(true)
public class CredentialsAppAttach implements Cloneable{
	private Integer uqaId;
	private String appNo;
	private String csType;
	private String csDesc;
	private String isOriginal;
	private Date planUseDate;
	private Date realUseDate;
	private String csPurPose;
	private String isLetOut;
	private String isRestored;
	private Date planRestDate;
	private Date realRestDate;
	
	
	public CredentialsAppAttach() {
	}

	public CredentialsAppAttach(Integer uqaId, String appNo, String csType,
			String csDesc, String isOriginal, Date planUseDate,
			Date realUseDate, String csPurPose, String isLetOut,
			String isRestored, Date planRestDate, Date realRestDate) {
		this.uqaId = uqaId;
		this.appNo = appNo;
		this.csType = csType;
		this.csDesc = csDesc;
		this.isOriginal = isOriginal;
		this.planUseDate = planUseDate;
		this.realUseDate = realUseDate;
		this.csPurPose = csPurPose;
		this.isLetOut = isLetOut;
		this.isRestored = isRestored;
		this.planRestDate = planRestDate;
		this.realRestDate = realRestDate;
	}
	
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

	@Column(name = "CS_TYPE", length = 1)
	public String getCsType() {
		return csType;
	}

	public void setCsType(String csType) {
		this.csType = csType;
	}

	@Column(name = "CS_DESC", length = 256)
	public String getCsDesc() {
		return csDesc;
	}

	public void setCsDesc(String csDesc) {
		this.csDesc = csDesc;
	}

	@Column(name = "IS_ORIGINAL", length = 1)
	public String getIsOriginal() {
		return isOriginal;
	}

	public void setIsOriginal(String isOriginal) {
		this.isOriginal = isOriginal;
	}

	@Column(name = "PLAN_USE_DATE")
	public Date getPlanUseDate() {
		return planUseDate;
	}

	public void setPlanUseDate(Date planUseDate) {
		this.planUseDate = planUseDate;
	}

	@Column(name = "REAL_USE_DATE")
	public Date getRealUseDate() {
		return realUseDate;
	}

	public void setRealUseDate(Date realUseDate) {
		this.realUseDate = realUseDate;
	}

	@Column(name = "CS_PURPOSE", length = 256)
	public String getCsPurPose() {
		return csPurPose;
	}

	public void setCsPurPose(String csPurPose) {
		this.csPurPose = csPurPose;
	}

	@Column(name = "IS_LETOUT", length = 1)
	public String getIsLetOut() {
		return isLetOut;
	}

	public void setIsLetOut(String isLetOut) {
		this.isLetOut = isLetOut;
	}

	@Column(name = "IS_RESTORED", length = 1)
	public String getIsRestored() {
		return isRestored;
	}

	public void setIsRestored(String isRestored) {
		this.isRestored = isRestored;
	}

	@Column(name = "PLAN_REST_DATE")
	public Date getPlanRestDate() {
		return planRestDate;
	}

	public void setPlanRestDate(Date planRestDate) {
		this.planRestDate = planRestDate;
	}

	@Column(name = "REAL_REST_DATE")
	public Date getRealRestDate() {
		return realRestDate;
	}

	public void setRealRestDate(Date realRestDate) {
		this.realRestDate = realRestDate;
	}
}
