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
@Table(name = "t_oa_pd_overtime_app")
@DynamicUpdate(true)
@DynamicInsert(true)
public class OvertimeApp {
	private Integer oveId;//编号
	private String  appNo;//申请编号
	private Integer applicantNo;//申请人编号
	private String position;//申请人职位
	private Integer deptNo;//所属部门
	private String appStatus;//申请状态
	private Date appDate;//申请日期
	private String otReason;//出差事由
	private Date planBgDtime;//计划开始日期时间
	private Date planEdDtime;//计划结束日期时间
	private Double planOtCnt;//计划加班合计
	private Date realBgDtime;//实际开始日期时间
	private Date realEdDtime;//实际结束日期时间
	private Double realOtCnt;//实际加班合计
	private Double realOtDays;
	private Double realAllocateDays;
//	private Integer otCount;//职位代理人编号
//	private Date otBeginDate;//加班开始日期
//	private Date otEndDate;//加班结束日期
	private Integer procStatus;//流程状态
	private String remark;//备注
	
	public OvertimeApp() {
	}

	public OvertimeApp(Integer oveId, String appNo, Integer applicantNo,
			String position, Integer deptNo, String appStatus, Date appDate,
			String otReason, Date planBgDtime, Date planEdDtime,
			Double planOtCnt, Date realBgDtime, Date realEdDtime,
			Double realOtCnt, Integer procStatus, String remark) {
		this.oveId = oveId;
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.position = position;
		this.deptNo = deptNo;
		this.appStatus = appStatus;
		this.appDate = appDate;
		this.otReason = otReason;
		this.planBgDtime = planBgDtime;
		this.planEdDtime = planEdDtime;
		this.planOtCnt = planOtCnt;
		this.realBgDtime = realBgDtime;
		this.realEdDtime = realEdDtime;
		this.realOtCnt = realOtCnt;
		this.procStatus = procStatus;
		this.remark = remark;
	}

	@Column(name = "OVE_ID", length = 10,unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getOveId() {
		return oveId;
	}

	public void setOveId(Integer oveId) {
		this.oveId = oveId;
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

	@Column(name = "POSITION", length = 50)
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "DEPT_NO", length = 11)
	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "APP_DATE")
	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	@Column(name = "OT_REASON", length = 200)
	public String getOtReason() {
		return otReason;
	}

	public void setOtReason(String otReason) {
		this.otReason = otReason;
	}

	@Column(name = "PLAN_BG_DTIME")
	public Date getPlanBgDtime() {
		return planBgDtime;
	}

	public void setPlanBgDtime(Date planBgDtime) {
		this.planBgDtime = planBgDtime;
	}

	@Column(name = "PLAN_ED_DTIME")
	public Date getPlanEdDtime() {
		return planEdDtime;
	}

	public void setPlanEdDtime(Date planEdDtime) {
		this.planEdDtime = planEdDtime;
	}

	@Column(name = "PLAN_OT_CNT", length = 4)
	public Double getPlanOtCnt() {
		return planOtCnt;
	}

	public void setPlanOtCnt(Double planOtCnt) {
		this.planOtCnt = planOtCnt;
	}

	@Column(name = "REAL_BG_DTIME")
	public Date getRealBgDtime() {
		return realBgDtime;
	}

	public void setRealBgDtime(Date realBgDtime) {
		this.realBgDtime = realBgDtime;
	}

	@Column(name = "REAL_ED_DTIME")
	public Date getRealEdDtime() {
		return realEdDtime;
	}

	public void setRealEdDtime(Date realEdDtime) {
		this.realEdDtime = realEdDtime;
	}

	@Column(name = "REAL_OT_CNT", length = 4)
	public Double getRealOtCnt() {
		return realOtCnt;
	}

	public void setRealOtCnt(Double realOtCnt) {
		this.realOtCnt = realOtCnt;
	}

	@Column(name = "PROC_STATUS", length = 1)
	public Integer getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(Integer procStatus) {
		this.procStatus = procStatus;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REAL_OT_DAYS", length = 4)
	public Double getRealOtDays() {
		return realOtDays;
	}

	public void setRealOtDays(Double realOtDays) {
		this.realOtDays = realOtDays;
	}

	@Column(name = "REAL_ALLOCATE_DAYS", length = 4)
	public Double getRealAllocateDays() {
		return realAllocateDays;
	}

	public void setRealAllocateDays(Double realAllocateDays) {
		this.realAllocateDays = realAllocateDays;
	}
}
