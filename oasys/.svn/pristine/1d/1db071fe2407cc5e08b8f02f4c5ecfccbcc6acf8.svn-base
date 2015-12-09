package com.oasys.model;

// Generated 2015-11-6 14:01:34 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
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
 * 外出申请实体类
 * @ClassName: OutApp 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年11月10日 下午7:29:15
 */
@Entity
@Table(name = "t_oa_pd_out_app", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class OutApp implements java.io.Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer outId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请人
	 */
	private Integer applicantNo;
	/**
	 * 所属部门
	 */
	private Integer deptNo;
	/**
	 * 申请日期
	 */
	private Date appDate;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 外出事由
	 */
	private String outReason;
	/**
	 * 计划外出开始时间
	 */
	private Date planBgDtime;
	/**
	 * 计划外出结束时间
	 */
	private Date planEdDtime;
	/**
	 * 计划外出合计时长
	 */
	private BigDecimal planOutCnt;
	/**
	 * 实际外出开始时间
	 */
	private Date realBgDtime;
	/**
	 * 实际外出结束时间
	 */
	private Date realEdDtime;
	/**
	 * 实际外出合计时长
	 */
	private BigDecimal realOutCnt;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 备注信息
	 */
	private String remark;

	public OutApp() {
	}

	public OutApp(String appNo, Integer applicantNo, Integer deptNo,
			Date appDate, String appStatus, String outReason, Date planBgDtime,
			Date planEdDtime, BigDecimal planOutCnt, Date realBgDtime,
			Date realEdDtime, BigDecimal realOutCnt, String procStatus,
			String remark) {
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.outReason = outReason;
		this.planBgDtime = planBgDtime;
		this.planEdDtime = planEdDtime;
		this.planOutCnt = planOutCnt;
		this.realBgDtime = realBgDtime;
		this.realEdDtime = realEdDtime;
		this.realOutCnt = realOutCnt;
		this.procStatus = procStatus;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "OUT_ID", unique = true, nullable = false)
	public Integer getOutId() {
		return this.outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "APP_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getAppDate() {
		return this.appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "OUT_REASON", length = 200)
	public String getOutReason() {
		return this.outReason;
	}

	public void setOutReason(String outReason) {
		this.outReason = outReason;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PLAN_BG_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getPlanBgDtime() {
		return this.planBgDtime;
	}

	public void setPlanBgDtime(Date planBgDtime) {
		this.planBgDtime = planBgDtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PLAN_ED_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getPlanEdDtime() {
		return this.planEdDtime;
	}

	public void setPlanEdDtime(Date planEdDtime) {
		this.planEdDtime = planEdDtime;
	}

	@Column(name = "PLAN_OUT_CNT", precision = 4, scale = 1)
	public BigDecimal getPlanOutCnt() {
		return this.planOutCnt;
	}

	public void setPlanOutCnt(BigDecimal planOutCnt) {
		this.planOutCnt = planOutCnt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REAL_BG_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getRealBgDtime() {
		return this.realBgDtime;
	}

	public void setRealBgDtime(Date realBgDtime) {
		this.realBgDtime = realBgDtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REAL_ED_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getRealEdDtime() {
		return this.realEdDtime;
	}

	public void setRealEdDtime(Date realEdDtime) {
		this.realEdDtime = realEdDtime;
	}

	@Column(name = "REAL_OUT_CNT", precision = 4, scale = 1)
	public BigDecimal getRealOutCnt() {
		return this.realOutCnt;
	}

	public void setRealOutCnt(BigDecimal realOutCnt) {
		this.realOutCnt = realOutCnt;
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
    public Object clone(){
    	// TODO Auto-generated method stub
    	try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
    	return null;
    }
}
