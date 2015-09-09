package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户还款信息MODEL
 * 
 * @author Sun
 *
 */
public class CustomerRepaymentInfoModel implements java.io.Serializable {
	private static final long serialVersionUID = 6299454947887343600L;
	private String organizationId;// 登记者所属的机构的ID
	private String organizationName;// 所属地区名
	private String contractNo;// 合同编号
	private Date contractSignDate;// 合同签署日期
	private String loanName;// 客户姓名
	private String loanIdNo;// 贷款申请人的身份证号
	private String loanMobileTel;// 贷款申请人的手机电话
	private String loanerBankName;// 开户行名称
	private String loanerActNum;// 开户行账号
	private String loanerActName;// 开户人姓名
	private Date loanBgDate;// 放款日期
	private Integer monthlyRepaymentDate;// 月还款日
	private Date repaymentBgDate;// 还款开始日期
	private Date repaymentEndDate;// 还款截止日期
	private String loanEdu;// 贷款额度
	private String monthlyRepayment;// 月还款额
	private Date now = new Date();// 当前日期
	private Integer loanPeriods; // 贷款期数，目前可选贷款期数为6期和12期
	private Integer currentLoanPeriods; // 当前期数
	private Integer paidLoanPeriods; // 以还期数
	private Integer overdueTimes;// 逾期次数(次)
	private Integer overdueDays;// 逾期天数(天)
	private BigDecimal lateFee;// 滞纳金(元)
	private BigDecimal defaultFnterest; // 罚息
	private BigDecimal realRepmtAmt;// 实际还款
	private String salesMan;// 业务员
	private String teamManger;// 团队经理
	private String loanOrderId;// 订单的ID
	private String loanTypeName;// 贷款类型名称
	private String loanType;// 贷款类型
	private String loanCity;// 进件城市

	public String getLoanTypeName() {
		return loanTypeName;
	}

	public void setLoanTypeName(String loanTypeName) {
		this.loanTypeName = loanTypeName;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getLoanIdNo() {
		return loanIdNo;
	}

	public void setLoanIdNo(String loanIdNo) {
		this.loanIdNo = loanIdNo;
	}

	public String getLoanMobileTel() {
		return loanMobileTel;
	}

	public void setLoanMobileTel(String loanMobileTel) {
		this.loanMobileTel = loanMobileTel;
	}

	public String getLoanerBankName() {
		return loanerBankName;
	}

	public void setLoanerBankName(String loanerBankName) {
		this.loanerBankName = loanerBankName;
	}

	public String getLoanerActNum() {
		return loanerActNum;
	}

	public void setLoanerActNum(String loanerActNum) {
		this.loanerActNum = loanerActNum;
	}

	public String getLoanerActName() {
		return loanerActName;
	}

	public void setLoanerActName(String loanerActName) {
		this.loanerActName = loanerActName;
	}

	public Date getLoanBgDate() {
		return loanBgDate;
	}

	public void setLoanBgDate(Date loanBgDate) {
		this.loanBgDate = loanBgDate;
	}

	public Integer getMonthlyRepaymentDate() {
		return monthlyRepaymentDate;
	}

	public void setMonthlyRepaymentDate(Integer monthlyRepaymentDate) {
		this.monthlyRepaymentDate = monthlyRepaymentDate;
	}

	public Date getRepaymentBgDate() {
		return repaymentBgDate;
	}

	public void setRepaymentBgDate(Date repaymentBgDate) {
		this.repaymentBgDate = repaymentBgDate;
	}

	public Date getRepaymentEndDate() {
		return repaymentEndDate;
	}

	public void setRepaymentEndDate(Date repaymentEndDate) {
		this.repaymentEndDate = repaymentEndDate;
	}

	public String getLoanEdu() {
		return loanEdu;
	}

	public void setLoanEdu(String loanEdu) {
		this.loanEdu = loanEdu;
	}

	public String getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public void setMonthlyRepayment(String monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	public Integer getLoanPeriods() {
		return loanPeriods;
	}

	public void setLoanPeriods(Integer loanPeriods) {
		this.loanPeriods = loanPeriods;
	}

	public Integer getCurrentLoanPeriods() {
		return currentLoanPeriods;
	}

	public void setCurrentLoanPeriods(Integer currentLoanPeriods) {
		this.currentLoanPeriods = currentLoanPeriods;
	}

	public Integer getPaidLoanPeriods() {
		return paidLoanPeriods;
	}

	public void setPaidLoanPeriods(Integer paidLoanPeriods) {
		this.paidLoanPeriods = paidLoanPeriods;
	}

	public Integer getOverdueTimes() {
		return overdueTimes;
	}

	public void setOverdueTimes(Integer overdueTimes) {
		this.overdueTimes = overdueTimes;
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

	public BigDecimal getDefaultFnterest() {
		return defaultFnterest;
	}

	public void setDefaultFnterest(BigDecimal defaultFnterest) {
		this.defaultFnterest = defaultFnterest;
	}

	public BigDecimal getRealRepmtAmt() {
		return realRepmtAmt;
	}

	public void setRealRepmtAmt(BigDecimal realRepmtAmt) {
		this.realRepmtAmt = realRepmtAmt;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public String getTeamManger() {
		return teamManger;
	}

	public void setTeamManger(String teamManger) {
		this.teamManger = teamManger;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public String getLoanCity() {
		return loanCity;
	}

	public void setLoanCity(String loanCity) {
		this.loanCity = loanCity;
	}

}
