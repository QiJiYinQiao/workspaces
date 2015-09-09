package com.bpms.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvestorAndInvestProductModel implements Serializable,Cloneable {

	private static final long serialVersionUID = 5499948588174390487L;
	
	/******************** 投资订单investOrder信息  ********************/
	private String investOrderId;   //投资订单ID
	private String investorId;      //投资者ID
	private String contractNo;      //合同编号
	private Date signDate;          //合同签署日期
	private String investorName;    //投资人姓名
	private String idCrad;          //身份证编号
	private String mobTel;          //联系方式
	private String bankName;        //开户行名称
	private String actNo;           //开户行账号	
	
	/*************** 投资业务订单investOrder与理财产品investProduct关联表信息  ***************/
	private BigDecimal investEdu;   //投资金额，即理财金额
	private BigDecimal usableEdu;   //可用额度
	private Date beginDate;         //意向投资日期
	private Date interestDate;      //计息日期
	private Date interestDate01;    //计息日期01，用于封装高级查询输入的计息日期
	private Date interestDate02;    //计息日期02，用于封装高级查询输入的计息日期
	private Date endDate;	        //到期日期
	private Date endDate01;	        //到期日期01，用于封装高级查询输入的到期日期
	private Date endDate02;	        //到期日期02，用于封装高级查询输入的到期日期
	
	/******************** 理财产品investProduct信息  ********************/
	private String prodId;          //理财产品ID
	private BigDecimal lowLendEdu;  //最低出借金额
	private BigDecimal higLendEdu;  //最高出借金额		
	private String prodName;      	//产品名称
	private Integer lendingCycle;	//出借周期，即理财天数
	private BigDecimal ars;         //年化收益率
	private BigDecimal ytm;         //到期收益率
	private String prodDesc;        //产品描述
	private String repaymentMode;   //还款方式
	private BigDecimal msf;          //年化折标系数
	
	
	/******************** 债券匹配相关字段  ********************/	
	//"realInvestEduForDebtMatch":记录在债权匹配时，本次投资的实际投资额度（注意：和理财金额investEdu是不同的）。
	//比如，贷款需求10万，在循环到当前的InvestOrder之前，已经匹配了9万了，若当前的投资为5万，则实际匹配给贷款的只有 10-9=1万元。
	//此时，realInvestEdu = 1万。
	private BigDecimal matchEdu = new BigDecimal("0");	
	
	
	/******************** 保存利息和本金相关信息  ********************/
	private BigDecimal totalInterest;   //应付利息
	private BigDecimal totalAmountDue;   //应付总额（本金、利息，单位：元）		
	
	/******************** 返息客户相关的：利息和本月计息天数  ********************/
	private BigDecimal interestPerMonth;       //月付利息
	private Integer daysToPayInterestCurMonth; //本月计息天数。
	private String rtnInterestMonSelect;     //用户选择的返息日期所在的月份。
	private String rtnInterestYearSelect;    //用户选择的返息日期所在的年份。
	
	public InvestorAndInvestProductModel(){}		
		
	public InvestorAndInvestProductModel(String investOrderId,
			String investorId, String contractNo, Date signDate,
			String investorName, String idCrad, String mobTel, String bankName,
			String actNo, BigDecimal investEdu, Date beginDate,
			Date interestDate, Date interestDate01, Date interestDate02,
			Date endDate, Date endDate01, Date endDate02, String prodId,
			BigDecimal lowLendEdu, BigDecimal higLendEdu, String prodName,
			Integer lendingCycle, BigDecimal ars, BigDecimal ytm,
			String prodDesc, String repaymentMode, BigDecimal totalInterest,
			BigDecimal totalAmountDue, BigDecimal interestPerMonth,
			Integer daysToPayInterestCurMonth, BigDecimal msf, String rtnInterestMonSelect, 
			String rtnInterestYearSelect, BigDecimal usableEdu, BigDecimal matchingEdu, BigDecimal realInvestEduForDebtMatch,
			BigDecimal debtMatchSuccessRecordNum) {
		super();
		this.investOrderId = investOrderId;
		this.investorId = investorId;
		this.contractNo = contractNo;
		this.signDate = signDate;
		this.investorName = investorName;
		this.idCrad = idCrad;
		this.mobTel = mobTel;
		this.bankName = bankName;
		this.actNo = actNo;
		this.investEdu = investEdu;
		this.beginDate = beginDate;
		this.interestDate = interestDate;
		this.interestDate01 = interestDate01;
		this.interestDate02 = interestDate02;
		this.endDate = endDate;
		this.endDate01 = endDate01;
		this.endDate02 = endDate02;
		this.prodId = prodId;
		this.lowLendEdu = lowLendEdu;
		this.higLendEdu = higLendEdu;
		this.prodName = prodName;
		this.lendingCycle = lendingCycle;
		this.ars = ars;
		this.ytm = ytm;
		this.prodDesc = prodDesc;
		this.repaymentMode = repaymentMode;
		this.totalInterest = totalInterest;
		this.totalAmountDue = totalAmountDue;
		this.interestPerMonth = interestPerMonth;
		this.daysToPayInterestCurMonth = daysToPayInterestCurMonth;
		this.msf = msf;
		this.rtnInterestMonSelect = rtnInterestMonSelect;
		this.rtnInterestYearSelect = rtnInterestYearSelect;
		this.usableEdu = usableEdu;
		this.matchEdu = matchEdu;
	}	

	public String getInvestOrderId() {
		return investOrderId;
	}

	public void setInvestOrderId(String investOrderId) {
		this.investOrderId = investOrderId;
	}

	public String getInvestorId() {
		return investorId;
	}

	public void setInvestorId(String investorId) {
		this.investorId = investorId;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getIdCrad() {
		return idCrad;
	}

	public void setIdCrad(String idCrad) {
		this.idCrad = idCrad;
	}

	public String getMobTel() {
		return mobTel;
	}

	public void setMobTel(String mobTel) {
		this.mobTel = mobTel;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getActNo() {
		return actNo;
	}

	public void setActNo(String actNo) {
		this.actNo = actNo;
	}

	public BigDecimal getInvestEdu() {
		return investEdu;
	}

	public void setInvestEdu(BigDecimal investEdu) {
		this.investEdu = investEdu;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}

	public Date getInterestDate01() {
		return interestDate01;
	}

	public void setInterestDate01(Date interestDate01) {
		this.interestDate01 = interestDate01;
	}

	public Date getInterestDate02() {
		return interestDate02;
	}

	public void setInterestDate02(Date interestDate02) {
		this.interestDate02 = interestDate02;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate01() {
		return endDate01;
	}

	public void setEndDate01(Date endDate01) {
		this.endDate01 = endDate01;
	}

	public Date getEndDate02() {
		return endDate02;
	}

	public void setEndDate02(Date endDate02) {
		this.endDate02 = endDate02;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public BigDecimal getLowLendEdu() {
		return lowLendEdu;
	}

	public void setLowLendEdu(BigDecimal lowLendEdu) {
		this.lowLendEdu = lowLendEdu;
	}

	public BigDecimal getHigLendEdu() {
		return higLendEdu;
	}

	public void setHigLendEdu(BigDecimal higLendEdu) {
		this.higLendEdu = higLendEdu;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getLendingCycle() {
		return lendingCycle;
	}

	public void setLendingCycle(Integer lendingCycle) {
		this.lendingCycle = lendingCycle;
	}

	public BigDecimal getArs() {
		return ars;
	}

	public void setArs(BigDecimal ars) {
		this.ars = ars;
	}

	public BigDecimal getYtm() {
		return ytm;
	}

	public void setYtm(BigDecimal ytm) {
		this.ytm = ytm;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

	public String getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public BigDecimal getTotalInterest() {
		return totalInterest;
	}

	public void setTotalInterest(BigDecimal totalInterest) {
		this.totalInterest = totalInterest;
	}

	public BigDecimal getTotalAmountDue() {
		return totalAmountDue;
	}

	public void setTotalAmountDue(BigDecimal totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}

	public BigDecimal getInterestPerMonth() {
		return interestPerMonth;
	}

	public void setInterestPerMonth(BigDecimal interestPerMonth) {
		this.interestPerMonth = interestPerMonth;
	}

	public Integer getDaysToPayInterestCurMonth() {
		return daysToPayInterestCurMonth;
	}

	public void setDaysToPayInterestCurMonth(Integer daysToPayInterestCurMonth) {
		this.daysToPayInterestCurMonth = daysToPayInterestCurMonth;
	}	
	
	public BigDecimal getMsf() {
		return msf;
	}

	public void setMsf(BigDecimal msf) {
		this.msf = msf;
	}

	public String getRtnInterestMonSelect() {
		return rtnInterestMonSelect;
	}

	public void setRtnInterestMonSelect(String rtnInterestMonSelect) {
		this.rtnInterestMonSelect = rtnInterestMonSelect;
	}	

	public String getRtnInterestYearSelect() {
		return rtnInterestYearSelect;
	}

	public void setRtnInterestYearSelect(String rtnInterestYearSelect) {
		this.rtnInterestYearSelect = rtnInterestYearSelect;
	}

	public BigDecimal getUsableEdu() {
		return usableEdu;
	}

	public void setUsableEdu(BigDecimal usableEdu) {
		this.usableEdu = usableEdu;
	}


	public BigDecimal getMatchEdu() {
		return matchEdu;
	}

	public void setMatchEdu(BigDecimal matchEdu) {
		this.matchEdu = matchEdu;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "InvestorAndInvestProductModel [investOrderId=" + investOrderId
				+ ", investorId=" + investorId + ", contractNo=" + contractNo
				+ ", signDate=" + signDate + ", investorName=" + investorName
				+ ", idCrad=" + idCrad + ", mobTel=" + mobTel + ", bankName="
				+ bankName + ", actNo=" + actNo + ", investEdu=" + investEdu
				+ ", usableEdu=" + usableEdu + ", beginDate=" + beginDate
				+ ", interestDate=" + interestDate + ", interestDate01="
				+ interestDate01 + ", interestDate02=" + interestDate02
				+ ", endDate=" + endDate + ", endDate01=" + endDate01
				+ ", endDate02=" + endDate02 + ", prodId=" + prodId
				+ ", lowLendEdu=" + lowLendEdu + ", higLendEdu=" + higLendEdu
				+ ", prodName=" + prodName + ", lendingCycle=" + lendingCycle
				+ ", ars=" + ars + ", ytm=" + ytm + ", prodDesc=" + prodDesc
				+ ", repaymentMode=" + repaymentMode + ", msf=" + msf
				+ ", matchEdu=" + matchEdu + ", totalInterest=" + totalInterest
				+ ", totalAmountDue=" + totalAmountDue + ", interestPerMonth="
				+ interestPerMonth + ", daysToPayInterestCurMonth="
				+ daysToPayInterestCurMonth + ", rtnInterestMonSelect="
				+ rtnInterestMonSelect + ", rtnInterestYearSelect="
				+ rtnInterestYearSelect + "]";
	}	
}
