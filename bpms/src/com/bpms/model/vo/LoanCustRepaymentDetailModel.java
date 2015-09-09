package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Date;

public class LoanCustRepaymentDetailModel implements java.io.Serializable {
	private static final long serialVersionUID = 3880503794020413157L;
	private String lcId; // 贷款合同ID
	private String loanOrderId; // 贷款订单ID
	private String contractNo; // 贷款合同编号
	private Date contractSignDate; // 合同签署日期
	private String contractSignSite;// 签署地
	private String loaner; // 贷款人姓名
	private String loanerIdno; // 贷款人身份证号
	private BigDecimal loanEdu; // 贷款额度，合同金额
	private Integer loanPeriods; // 贷款期数，目前可选贷款期数为6期和12期
	private BigDecimal monthlyRepayment;// 月还款额度，每月还本金和利息总和。计息公式如下(合同金额/贷款期数)+合同金额*月利率
	private String loanType;// 贷款类型，工薪贷1.3%,事业贷1.4%
	private String rdId;
	private int periods;
	private Date planRepmtDate;
	private BigDecimal planRepmtAmt;
	private Date realRepmtDate;
	private BigDecimal realRepmtAmt;
	private String repmtAct;
	private Integer overdueDays;
	private BigDecimal lateFee;
	private BigDecimal defaultInterest;
	private BigDecimal freeInterestFee;
	private String rpmtStatus;
	private String operator;
	private String remark;
	private BigDecimal needPay;// 待还金额
	private BigDecimal needLateFee;// 待还滞纳金
	private BigDecimal needDefaultInterest;// 待还罚息
	private BigDecimal needMonthFee;// 待还月还金额
	private String loanCity;
	private String loanerId;//贷款人ID
	private String rpmtStatusName;

	public String getLoanCity() {
		return loanCity;
	}

	public void setLoanCity(String loanCity) {
		this.loanCity = loanCity;
	}

	public String getLcId() {
		return lcId;
	}

	public void setLcId(String lcId) {
		this.lcId = lcId;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getContractSignDate() {
		return contractSignDate;
	}

	public void setContractSignDate(Date contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	public String getLoaner() {
		return loaner;
	}

	public void setLoaner(String loaner) {
		this.loaner = loaner;
	}

	public String getLoanerIdno() {
		return loanerIdno;
	}

	public void setLoanerIdno(String loanerIdno) {
		this.loanerIdno = loanerIdno;
	}

	public BigDecimal getLoanEdu() {
		return loanEdu;
	}

	public void setLoanEdu(BigDecimal loanEdu) {
		this.loanEdu = loanEdu;
	}

	public Integer getLoanPeriods() {
		return loanPeriods;
	}

	public void setLoanPeriods(Integer loanPeriods) {
		this.loanPeriods = loanPeriods;
	}

	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getRdId() {
		return rdId;
	}

	public void setRdId(String rdId) {
		this.rdId = rdId;
	}

	public int getPeriods() {
		return periods;
	}

	public void setPeriods(int periods) {
		this.periods = periods;
	}

	public Date getPlanRepmtDate() {
		return planRepmtDate;
	}

	public void setPlanRepmtDate(Date planRepmtDate) {
		this.planRepmtDate = planRepmtDate;
	}

	public BigDecimal getPlanRepmtAmt() {
		return planRepmtAmt;
	}

	public void setPlanRepmtAmt(BigDecimal planRepmtAmt) {
		this.planRepmtAmt = planRepmtAmt;
	}

	public Date getRealRepmtDate() {
		return realRepmtDate;
	}

	public void setRealRepmtDate(Date realRepmtDate) {
		this.realRepmtDate = realRepmtDate;
	}

	public BigDecimal getRealRepmtAmt() {
		return realRepmtAmt;
	}

	public void setRealRepmtAmt(BigDecimal realRepmtAmt) {
		this.realRepmtAmt = realRepmtAmt;
	}

	public String getRepmtAct() {
		return repmtAct;
	}

	public void setRepmtAct(String repmtAct) {
		this.repmtAct = repmtAct;
	}

	public Integer getOverdueDays() {
		return overdueDays;
	}

	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	public BigDecimal getDefaultInterest() {
		return defaultInterest;
	}

	public void setDefaultInterest(BigDecimal defaultInterest) {
		this.defaultInterest = defaultInterest;
	}

	public BigDecimal getFreeInterestFee() {
		return freeInterestFee;
	}

	public void setFreeInterestFee(BigDecimal freeInterestFee) {
		this.freeInterestFee = freeInterestFee;
	}

	public String getRpmtStatus() {
		return rpmtStatus;
	}

	public void setRpmtStatus(String rpmtStatus) {
		this.rpmtStatus = rpmtStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getNeedPay() {
		return needPay;
	}

	public void setNeedPay(BigDecimal needPay) {
		this.needPay = needPay;
	}

	public BigDecimal getNeedLateFee() {
		return needLateFee;
	}

	public void setNeedLateFee(BigDecimal needLateFee) {
		this.needLateFee = needLateFee;
	}

	public BigDecimal getNeedDefaultInterest() {
		return needDefaultInterest;
	}

	public void setNeedDefaultInterest(BigDecimal needDefaultInterest) {
		this.needDefaultInterest = needDefaultInterest;
	}

	public String getContractSignSite() {
		return contractSignSite;
	}

	public void setContractSignSite(String contractSignSite) {
		this.contractSignSite = contractSignSite;
	}

	public BigDecimal getNeedMonthFee() {
		return needMonthFee;
	}

	public void setNeedMonthFee(BigDecimal needMonthFee) {
		this.needMonthFee = needMonthFee;
	}

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public String getRpmtStatusName() {
		return rpmtStatusName;
	}

	public void setRpmtStatusName(String rpmtStatusName) {
		this.rpmtStatusName = rpmtStatusName;
	}

}
