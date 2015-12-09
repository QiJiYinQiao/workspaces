package com.oasys.model;

// Generated 2015-11-6 13:51:12 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 忘打卡申请
 * @ClassName: EmpForgetPluginApp 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年11月6日 下午1:54:18
 */
@Entity
@Table(name = "t_oa_pd_emp_forget_plugin_app", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class EmpForgetPluginApp implements java.io.Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer fpaId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请人id
	 */
	private Integer applicantNo;
	/**
	 * 部门id
	 */
	private Integer deptNo;
	/**
	 * 申请时间
	 */
	private Date appDtime;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 忘打卡事由
	 */
	private String forgetPluginReason;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 备注信息
	 */
	private String remark;

	public EmpForgetPluginApp() {
	}

	public EmpForgetPluginApp(String appNo, Integer applicantNo,
			Integer deptNo, Date appDtime, String appStatus,
			String forgetPluginReason, String procStatus, String remark) {
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.appDtime = appDtime;
		this.appStatus = appStatus;
		this.forgetPluginReason = forgetPluginReason;
		this.procStatus = procStatus;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FPA_ID", unique = true, nullable = false)
	public Integer getFpaId() {
		return this.fpaId;
	}

	public void setFpaId(Integer fpaId) {
		this.fpaId = fpaId;
	}

	@Column(name = "APP_NO", unique = true, length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "APPLICANT_NO")
	public Integer getApplicantNo() {
		return this.applicantNo;
	}

	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}

	@Column(name = "DEPT_NO")
	public Integer getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APP_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getAppDtime() {
		return this.appDtime;
	}

	public void setAppDtime(Date appDtime) {
		this.appDtime = appDtime;
	}

	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "FORGET_PLUGIN_REASON", length = 200)
	public String getForgetPluginReason() {
		return this.forgetPluginReason;
	}

	public void setForgetPluginReason(String forgetPluginReason) {
		this.forgetPluginReason = forgetPluginReason;
	}

	@Column(name = "PROC_STATUS", length = 1)
	public String getProcStatus() {
		return this.procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	protected Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
