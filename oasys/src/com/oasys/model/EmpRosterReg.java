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

import org.activiti.cdi.annotation.TaskId;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * TOaHrEmpRosterReg generated by hbm2java
 */
@Entity
@Table(name = "t_oa_hr_emp_roster_reg")
public class EmpRosterReg implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;
	private Integer errId;
	private Integer userId;
	private String contractNo;
	private BigDecimal contractPeriod;
	private Date contractBgDate;
	private Date contractEdDate;
	private String isSignContract;
	private String duty;
	private Date dutyBgDate;
	private String commendInfo;
	private Date entryDate;
	private BigDecimal trialTlimit;
	private Date trialEdDate;
	private String isRegular;
	private Date regularDate;
	private String isChg;
	private String isMakeOverall;
	private Date overallsMakeDate;
	private BigDecimal trialBaseSal;
	private BigDecimal trialPostSal;
	private BigDecimal trialPerfSal;
	private BigDecimal regularBaseSal;
	private BigDecimal regularPostSal;
	private BigDecimal regularPerfSal;
	private BigDecimal curMonthSalCnt;
	private Date resignDate;
	private String resignReason;
	private Integer workedDays;
	private Integer creator;
	private Date crtDtime;
	private Integer updater;
	private Date updDtime;
	private BigDecimal endowmentIns;
	private BigDecimal medicalIns;
	private BigDecimal unemploymentIns;
	private BigDecimal workInjuryIns;
	private BigDecimal maternityIns;
	private BigDecimal housingFund;
	private String remark;

	public EmpRosterReg() {
	}

	public EmpRosterReg(Integer userId) {
		this.userId = userId;
	}

	public EmpRosterReg(Integer userId, String contractNo,
			BigDecimal contractPeriod, Date contractBgDate,
			Date contractEdDate, String duty, Date dutyBgDate,
			String commendInfo, Date entryDate, BigDecimal trialTlimit,
			Date trialEdDate, String isRegular, Date regularDate,String isChg,
			String isMakeOverall, Date overallsMakeDate,
			BigDecimal trialBaseSal, BigDecimal trialPostSal,
			BigDecimal trialPerfSal, BigDecimal regularBaseSal,
			BigDecimal regularPostSal, BigDecimal regularPerfSal,
			BigDecimal curMonthSalCnt, Integer creator, Date crtDtime,
			Integer updater, Date updDtime, BigDecimal endowmentIns,
			BigDecimal medicalIns, BigDecimal unemploymentIns,
			BigDecimal workInjuryIns, BigDecimal maternityIns,
			BigDecimal housingFund, String remark) {
		this.userId = userId;
		this.contractNo = contractNo;
		this.contractPeriod = contractPeriod;
		this.contractBgDate = contractBgDate;
		this.contractEdDate = contractEdDate;
		this.duty = duty;
		this.dutyBgDate = dutyBgDate;
		this.commendInfo = commendInfo;
		this.entryDate = entryDate;
		this.trialTlimit = trialTlimit;
		this.trialEdDate = trialEdDate;
		this.isRegular = isRegular;
		this.regularDate = regularDate;
		this.isChg=isChg;
		this.isMakeOverall = isMakeOverall;
		this.overallsMakeDate = overallsMakeDate;
		this.trialBaseSal = trialBaseSal;
		this.trialPostSal = trialPostSal;
		this.trialPerfSal = trialPerfSal;
		this.regularBaseSal = regularBaseSal;
		this.regularPostSal = regularPostSal;
		this.regularPerfSal = regularPerfSal;
		this.curMonthSalCnt = curMonthSalCnt;
		this.creator = creator;
		this.crtDtime = crtDtime;
		this.updater = updater;
		this.updDtime = updDtime;
		this.endowmentIns = endowmentIns;
		this.medicalIns = medicalIns;
		this.unemploymentIns = unemploymentIns;
		this.workInjuryIns = workInjuryIns;
		this.maternityIns = maternityIns;
		this.housingFund = housingFund;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ERR_ID", unique = true, nullable = false)
	public Integer getErrId() {
		return this.errId;
	}

	public void setErrId(Integer errId) {
		this.errId = errId;
	}

	@Column(name = "USER_ID", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "CONTRACT_NO", length = 50)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "CONTRACT_PERIOD", precision = 4, scale = 1)
	public BigDecimal getContractPeriod() {
		return this.contractPeriod;
	}

	public void setContractPeriod(BigDecimal contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CONTRACT_BG_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getContractBgDate() {
		return this.contractBgDate;
	}

	public void setContractBgDate(Date contractBgDate) {
		this.contractBgDate = contractBgDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CONTRACT_ED_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getContractEdDate() {
		return this.contractEdDate;
	}

	public void setContractEdDate(Date contractEdDate) {
		this.contractEdDate = contractEdDate;
	}

	@Column(name = "DUTY", length = 80)
	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DUTY_BG_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getDutyBgDate() {
		return this.dutyBgDate;
	}

	public void setDutyBgDate(Date dutyBgDate) {
		this.dutyBgDate = dutyBgDate;
	}

	@Column(name = "COMMEND_INFO", length = 256)
	public String getCommendInfo() {
		return this.commendInfo;
	}

	public void setCommendInfo(String commendInfo) {
		this.commendInfo = commendInfo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENTRY_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "TRIAL_TLIMIT", precision = 3, scale = 1)
	public BigDecimal getTrialTlimit() {
		return this.trialTlimit;
	}

	public void setTrialTlimit(BigDecimal trialTlimit) {
		this.trialTlimit = trialTlimit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRIAL_ED_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getTrialEdDate() {
		return this.trialEdDate;
	}

	public void setTrialEdDate(Date trialEdDate) {
		this.trialEdDate = trialEdDate;
	}

	@Column(name = "IS_REGULAR", length = 1)
	public String getIsRegular() {
		return this.isRegular;
	}

	public void setIsRegular(String isRegular) {
		this.isRegular = isRegular;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REGULAR_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getRegularDate() {
		return this.regularDate;
	}

	public void setRegularDate(Date regularDate) {
		this.regularDate = regularDate;
	}
	
	@Column(name = "IS_CHG", length = 1)
	public String getIsChg() {
		return isChg;
	}

	public void setIsChg(String isChg) {
		this.isChg = isChg;
	}

	@Column(name = "IS_MAKE_OVERALL", length = 1)
	public String getIsMakeOverall() {
		return this.isMakeOverall;
	}

	public void setIsMakeOverall(String isMakeOverall) {
		this.isMakeOverall = isMakeOverall;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "OVERALLS_MAKE_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getOverallsMakeDate() {
		return this.overallsMakeDate;
	}

	public void setOverallsMakeDate(Date overallsMakeDate) {
		this.overallsMakeDate = overallsMakeDate;
	}

	@Column(name = "TRIAL_BASE_SAL", precision = 10)
	public BigDecimal getTrialBaseSal() {
		return this.trialBaseSal;
	}

	public void setTrialBaseSal(BigDecimal trialBaseSal) {
		this.trialBaseSal = trialBaseSal;
	}

	@Column(name = "TRIAL_POST_SAL", precision = 10)
	public BigDecimal getTrialPostSal() {
		return this.trialPostSal;
	}

	public void setTrialPostSal(BigDecimal trialPostSal) {
		this.trialPostSal = trialPostSal;
	}

	@Column(name = "TRIAL_PERF_SAL", precision = 10)
	public BigDecimal getTrialPerfSal() {
		return this.trialPerfSal;
	}

	public void setTrialPerfSal(BigDecimal trialPerfSal) {
		this.trialPerfSal = trialPerfSal;
	}

	@Column(name = "REGULAR_BASE_SAL", precision = 10)
	public BigDecimal getRegularBaseSal() {
		return this.regularBaseSal;
	}

	public void setRegularBaseSal(BigDecimal regularBaseSal) {
		this.regularBaseSal = regularBaseSal;
	}

	@Column(name = "REGULAR_POST_SAL", precision = 10)
	public BigDecimal getRegularPostSal() {
		return this.regularPostSal;
	}

	public void setRegularPostSal(BigDecimal regularPostSal) {
		this.regularPostSal = regularPostSal;
	}

	@Column(name = "REGULAR_PERF_SAL", precision = 10)
	public BigDecimal getRegularPerfSal() {
		return this.regularPerfSal;
	}

	public void setRegularPerfSal(BigDecimal regularPerfSal) {
		this.regularPerfSal = regularPerfSal;
	}

	@Column(name = "CUR_MONTH_SAL_CNT", precision = 10)
	public BigDecimal getCurMonthSalCnt() {
		return this.curMonthSalCnt;
	}

	public void setCurMonthSalCnt(BigDecimal curMonthSalCnt) {
		this.curMonthSalCnt = curMonthSalCnt;
	}

	@Column(name = "CREATOR")
	public Integer getCreator() {
		return this.creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getCrtDtime() {
		return this.crtDtime;
	}

	public void setCrtDtime(Date crtDtime) {
		this.crtDtime = crtDtime;
	}

	@Column(name = "UPDATER")
	public Integer getUpdater() {
		return this.updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPD_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getUpdDtime() {
		return this.updDtime;
	}

	public void setUpdDtime(Date updDtime) {
		this.updDtime = updDtime;
	}

	@Column(name = "ENDOWMENT_INS", precision = 8)
	public BigDecimal getEndowmentIns() {
		return this.endowmentIns;
	}

	public void setEndowmentIns(BigDecimal endowmentIns) {
		this.endowmentIns = endowmentIns;
	}

	@Column(name = "MEDICAL_INS", precision = 8)
	public BigDecimal getMedicalIns() {
		return this.medicalIns;
	}

	public void setMedicalIns(BigDecimal medicalIns) {
		this.medicalIns = medicalIns;
	}

	@Column(name = "UNEMPLOYMENT_INS", precision = 8)
	public BigDecimal getUnemploymentIns() {
		return this.unemploymentIns;
	}

	public void setUnemploymentIns(BigDecimal unemploymentIns) {
		this.unemploymentIns = unemploymentIns;
	}

	@Column(name = "WORK_INJURY_INS", precision = 8)
	public BigDecimal getWorkInjuryIns() {
		return this.workInjuryIns;
	}

	public void setWorkInjuryIns(BigDecimal workInjuryIns) {
		this.workInjuryIns = workInjuryIns;
	}

	@Column(name = "MATERNITY_INS", precision = 8)
	public BigDecimal getMaternityIns() {
		return this.maternityIns;
	}

	public void setMaternityIns(BigDecimal maternityIns) {
		this.maternityIns = maternityIns;
	}

	@Column(name = "HOUSING_FUND", precision = 8)
	public BigDecimal getHousingFund() {
		return this.housingFund;
	}

	public void setHousingFund(BigDecimal housingFund) {
		this.housingFund = housingFund;
	}

	@Column(name = "REMARK", length = 512)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "IS_SIGN_CONTRACT", length = 1)
	public String getIsSignContract() {
		return isSignContract;
	}

	public void setIsSignContract(String isSignContract) {
		this.isSignContract = isSignContract;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RESIGN_DATE", length = 1)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getResignDate() {
		return resignDate;
	}

	public void setResignDate(Date resignDate) {
		this.resignDate = resignDate;
	}

	@Column(name = "RESIGN_REASON", length = 256)
	public String getResignReason() {
		return resignReason;
	}

	public void setResignReason(String resignReason) {
		this.resignReason = resignReason;
	}

	@Column(name = "WORKED_DAYS")
	public Integer getWorkedDays() {
		return workedDays;
	}

	public void setWorkedDays(Integer workedDays) {
		this.workedDays = workedDays;
	}

	
}
