package com.oasys.model.VO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmpRosterRegModel implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	private static final long serialVersionUID = 1L;
	private String deptName;// 部门
	private String userId;// 用户id
	private String userName;// 用户名
	private String mobile;// 联系方式
	private String idCard;// 身份证号
	private String idCardAddr;// 身份证地址
	private String salCardNo;// 工资卡号
	private String duty;// 职务
	private String trialBaseSal;// 试用期基本工资
	private String trialPostSal;// 试用期岗位工资
	private String trialPerfSal;// 试用期绩效工资
	private String regularBaseSal;// 转正基本工资
	private String regularPostSal;// 转正岗位工资
	private String regularPerfSal;// 转正绩效工资
	private String curMonthSalCnt;// 本月最终薪资总计
	private String contractNo;// 劳动合同编号
	private String contractPeriod;// 劳动合同期限
	private String contractBgDate;// 劳动合同开始时间
	private String contractEdDate;// 劳动合同结束时间
	private String endowmentIns;// 养老保险
	private String medicalIns;// 医疗保险
	private String unemploymentIns;// 失业保险
	private String workInjuryIns;// 工伤保险
	private String maternityIns;// 生育保险
	private String housingFund;// 住房公积金
	private String isChg;// 是否异动
	private String isRegular;// 是否转正
	private String isSignContract;// 是否签订劳动合同
	private String deptId;// 部门id

	private String errId;// 花名册登记主键
	private String dutyBgDate;// 当前职务开始日期
	private String commendInfo;// 表彰情况
	private String entryDate;// 入职日期
	private String trialTlimit;// 试用期期限
	private String trialEdDate;// 试用期结束时间
	private String regularDate;// 转正日期
	private String isMakeOverall;// 是否制作工服
	private String overallsMakeDate;// 工服制作日期
	private String resignDate;// 离职时间
	private String resignReason;// 离职原因
	private String workedDays;// 在职天数
	private String creator;// 创建人
	private String crtDtime;// 创建时间
	private String remark;// 备注

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getIdCardAddr() {
		return idCardAddr;
	}

	public void setIdCardAddr(String idCardAddr) {
		this.idCardAddr = idCardAddr;
	}

	public String getSalCardNo() {
		return salCardNo;
	}

	public void setSalCardNo(String salCardNo) {
		this.salCardNo = salCardNo;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTrialBaseSal() {
		return trialBaseSal;
	}

	public void setTrialBaseSal(String trialBaseSal) {
		this.trialBaseSal = trialBaseSal;
	}

	public String getTrialPostSal() {
		return trialPostSal;
	}

	public void setTrialPostSal(String trialPostSal) {
		this.trialPostSal = trialPostSal;
	}

	public String getTrialPerfSal() {
		return trialPerfSal;
	}

	public void setTrialPerfSal(String trialPerfSal) {
		this.trialPerfSal = trialPerfSal;
	}

	public String getRegularBaseSal() {
		return regularBaseSal;
	}

	public void setRegularBaseSal(String regularBaseSal) {
		this.regularBaseSal = regularBaseSal;
	}

	public String getRegularPostSal() {
		return regularPostSal;
	}

	public void setRegularPostSal(String regularPostSal) {
		this.regularPostSal = regularPostSal;
	}

	public String getRegularPerfSal() {
		return regularPerfSal;
	}

	public void setRegularPerfSal(String regularPerfSal) {
		this.regularPerfSal = regularPerfSal;
	}

	public String getCurMonthSalCnt() {
		return curMonthSalCnt;
	}

	public void setCurMonthSalCnt(String curMonthSalCnt) {
		this.curMonthSalCnt = curMonthSalCnt;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractPeriod() {
		return contractPeriod;
	}

	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}

	public String getContractBgDate() {
		return contractBgDate;
	}

	public void setContractBgDate(String contractBgDate) {
		this.contractBgDate = contractBgDate;
	}

	public String getContractEdDate() {
		return contractEdDate;
	}

	public void setContractEdDate(String contractEdDate) {
		this.contractEdDate = contractEdDate;
	}

	public String getEndowmentIns() {
		return endowmentIns;
	}

	public void setEndowmentIns(String endowmentIns) {
		this.endowmentIns = endowmentIns;
	}

	public String getMedicalIns() {
		return medicalIns;
	}

	public void setMedicalIns(String medicalIns) {
		this.medicalIns = medicalIns;
	}

	public String getUnemploymentIns() {
		return unemploymentIns;
	}

	public void setUnemploymentIns(String unemploymentIns) {
		this.unemploymentIns = unemploymentIns;
	}

	public String getWorkInjuryIns() {
		return workInjuryIns;
	}

	public void setWorkInjuryIns(String workInjuryIns) {
		this.workInjuryIns = workInjuryIns;
	}

	public String getMaternityIns() {
		return maternityIns;
	}

	public void setMaternityIns(String maternityIns) {
		this.maternityIns = maternityIns;
	}

	public String getHousingFund() {
		return housingFund;
	}

	public void setHousingFund(String housingFund) {
		this.housingFund = housingFund;
	}

	public String getIsChg() {
		return isChg;
	}

	public void setIsChg(String isChg) {
		this.isChg = isChg;
	}

	public String getIsRegular() {
		return isRegular;
	}

	public void setIsRegular(String isRegular) {
		this.isRegular = isRegular;
	}

	public String getIsSignContract() {
		return isSignContract;
	}

	public void setIsSignContract(String isSignContract) {
		this.isSignContract = isSignContract;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getErrId() {
		return errId;
	}

	public void setErrId(String errId) {
		this.errId = errId;
	}

	public String getDutyBgDate() {
		return dutyBgDate;
	}

	public void setDutyBgDate(String dutyBgDate) {
		this.dutyBgDate = dutyBgDate;
	}

	public String getCommendInfo() {
		return commendInfo;
	}

	public void setCommendInfo(String commendInfo) {
		this.commendInfo = commendInfo;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getTrialTlimit() {
		return trialTlimit;
	}

	public void setTrialTlimit(String trialTlimit) {
		this.trialTlimit = trialTlimit;
	}

	public String getTrialEdDate() {
		return trialEdDate;
	}

	public void setTrialEdDate(String trialEdDate) {
		this.trialEdDate = trialEdDate;
	}

	public String getRegularDate() {
		return regularDate;
	}

	public void setRegularDate(String regularDate) {
		this.regularDate = regularDate;
	}

	public String getIsMakeOverall() {
		return isMakeOverall;
	}

	public void setIsMakeOverall(String isMakeOverall) {
		this.isMakeOverall = isMakeOverall;
	}

	public String getOverallsMakeDate() {
		return overallsMakeDate;
	}

	public void setOverallsMakeDate(String overallsMakeDate) {
		this.overallsMakeDate = overallsMakeDate;
	}

	public String getResignDate() {
		return resignDate;
	}

	public void setResignDate(String resignDate) {
		this.resignDate = resignDate;
	}

	public String getResignReason() {
		return resignReason;
	}

	public void setResignReason(String resignReason) {
		this.resignReason = resignReason;
	}

	public String getWorkedDays() {
		return workedDays;
	}

	public void setWorkedDays(String workedDays) {
		this.workedDays = workedDays;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCrtDtime() {
		return crtDtime;
	}

	public void setCrtDtime(String crtDtime) {
		this.crtDtime = crtDtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
