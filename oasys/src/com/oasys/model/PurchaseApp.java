package com.oasys.model;

// Generated 2015-9-15 14:17:02 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassName: PurchaseApp 
 * @Description: TODO 物料申请表
 * @author PANCHUANHE
 * @date 2015年9月15日 下午3:13:45
 */
@Entity
@Table(name = "t_oa_ad_purchase_app", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class PurchaseApp implements java.io.Serializable,Cloneable{
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer paId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请类型
	 */
	private String appType;
	/**
	 * 其他申请类型
	 */
	private String appTypeOther;
	/**
	 * 申请人
	 */
	private Integer applicantNo;
	/**
	 * 申请部门
	 */
	private Integer appDept;
	/**
	 * 申请日期
	 */
	private Date appDate;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 合计金额
	 */
	private BigDecimal totalAmt;
	/**
	 * 计划到货时间
	 */
	private Date planRecDate;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 流程状态
	 */
	private String procStatus;

	public PurchaseApp() {
	}

	public PurchaseApp(Integer paId) {
		this.paId = paId;
	}

	public PurchaseApp(Integer paId, String appNo, String appType,
			String appTypeOther, Integer applicantNo, Integer appDept,
			Date appDate, String appStatus, BigDecimal totalAmt,
			Date planRecDate, String remark) {
		this.paId = paId;
		this.appNo = appNo;
		this.appType = appType;
		this.appTypeOther = appTypeOther;
		this.applicantNo = applicantNo;
		this.appDept = appDept;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.totalAmt = totalAmt;
		this.planRecDate = planRecDate;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PA_ID", unique = true, nullable = false)
	public Integer getPaId() {
		return this.paId;
	}

	public void setPaId(Integer paId) {
		this.paId = paId;
	}

	@Column(name = "APP_NO", unique = true, length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "APP_TYPE", length = 1)
	public String getAppType() {
		return this.appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	@Column(name = "APP_TYPE_OTHER", length = 20)
	public String getAppTypeOther() {
		return this.appTypeOther;
	}

	public void setAppTypeOther(String appTypeOther) {
		this.appTypeOther = appTypeOther;
	}

	@Column(name = "APPLICANT_NO")
	public Integer getApplicantNo() {
		return this.applicantNo;
	}

	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}

	@Column(name = "APP_DEPT")
	public Integer getAppDept() {
		return this.appDept;
	}

	public void setAppDept(Integer appDept) {
		this.appDept = appDept;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APP_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getAppDate() {
		return this.appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	@Column(name = "APP_STATUS", length = 1)
	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "TOTAL_AMT", precision = 10)
	public BigDecimal getTotalAmt() {
		return this.totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLAN_REC_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getPlanRecDate() {
		return this.planRecDate;
	}

	public void setPlanRecDate(Date planRecDate) {
		this.planRecDate = planRecDate;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	@Column(name = "PROC_STATUS", length = 1,columnDefinition = "char(1) default '1'")
	public String getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
