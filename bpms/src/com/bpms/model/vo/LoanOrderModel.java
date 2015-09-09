package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.bpms.model.OrderStatus;

/**
 * @ClassName: LoanOrderModel 
 * @Description: TODO 贷款订单vo,它包括贷款订单所有字段，贷款合同表部分字段，系统参数表部分字段
 * @author PANCHUANHE
 * @date 2015年8月19日 上午11:01:48
 */
public class LoanOrderModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**贷款订单字段**/
	private String loanOrderId;
	private String loanerId;
	private OrderStatus orderStatus;
	private String orderDesc;
	private String subjectType;
	private String loanType;
	private BigDecimal loanAmount;
	private BigDecimal loanMin;
	private Integer loanPeriod;
	private String repayMethod;
	private String purpose;
	private Integer creator;// 登记人
	private Integer organizationId;// 登记人所属的机构的信息
	private String salesMan;// 业务员
	private Date createDate;
	private Date finishDate;
	private Date applyDate;
	private String name;
	private String genderType;
	private String idNo;
	private Integer age;
	private String hukouAddr;
	private String curAddr;
	private String mobileTel;
	private String fixedTel;
	private String marriageType;
	private String hasChild;
	private String email;
	private String qqNo;
	private BigDecimal annualSalary;
	private String incomeSrc;
	private String mortgageStatus;
	private BigDecimal houseInstallPay;
	private BigDecimal rent;
	private String processStatus;
	private String taskId;
	/**系统参数表字段**/
	private String rate;//利率
	/**贷款合同全部字段**/
	private String lcId;			//贷款合同ID
	private String contractNo;		//贷款合同编号
	private String contractSignSite;//记录合同的签署地
	private Date contractSignDate;	//合同签署日期
	private String loaner;			//贷款人姓名
	private String loanerIdno;		//贷款人身份证号
	private String loanerPostalAddr;//贷款人通讯地址
	private String loanerTel;		//贷款人联系电话
	private Date loanerSignDate;	//贷款人签字日期
	private String loanerJoint;		//共同贷款人姓名
	private String ljIdno;			//共同贷款人身份证号
	private String ljPostalAddr;	//共同贷款人通讯地址
	private String ljTel;			//共同贷款人联系电话
	private Date ljSignDate;		//共同贷款人签字日期
	private String lender;			//出借人，目前固定为于德建
	private String lenderIdno;		//出借人身份证号，目前固定为131126197912150054
	private Date lenderSignDate;	//出借人签字日期
	private String agent;			//委托代理人姓名，有则填写，否则无
	private String agentIdno;		//委托代理人身份证号
	private Date agentSignDate;		//委托代理人签字日期
	private BigDecimal loanEdu;		//贷款额度，合同金额
	private Map<String, String> digitLoanEdu;
	private String characterLoanEdu;//贷款额度的中文大写 例如：壹仟叁百元
	private BigDecimal monthlyRate;	//月利率，目前固定为1%（年利率12%）
	private BigDecimal loanInterest;//贷款利息，贷款利息＝合同金额*月利率*期限
	private Map<String, String> digitLoanInterest; 
	private String characterLoanInterest;//贷款利息的中文大写 例如：壹仟叁百元
	private BigDecimal monthlyRepayment;//月还款额度，每月还本金和利息总和。计息公式如下(合同金额/贷款期数)+合同金额*月利率
	private String characterMonthlyRepayment;//月还款额大写 例如：壹仟叁百元
	private Map<String,String> digitMonthlyRepayment;
	private Integer loanPeriods;	//贷款期数，目前可选贷款期数为6期和12期
	private Integer monthlyRepaymentDate;//月还款日，如果批贷日期在当月15号之前的，还款日为30号，否则为15号
	private Date loanBgDate;		//贷款开始日期，为合同签约日期
	private Date loanEdDate;		//贷款结束日期，合同履行终止日期
	private Date repaymentBgDate;	//还款开始日期
	private Date repaymentEdDate;	//还款结束日期
	private String loanerBankName;	//贷款人开户银行全称
	private String loanerActName;	//贷款人开户行户名，账户名称
	private String loanerActNum;	//贷款人开户行账号
	private String contractStatus;	//合同状态，查询系统字典表（t_sys_dict）中所有parent_id等于dict_code=contract_status的记录的code_id，取值范围为0－初始状态，1－已签约，2－客户拒签，3－作废
	private String remark;			//备注信息
	private String obliMatchStatus; //债权匹配状态，0－未匹配，1－已匹配
	public String getLoanOrderId() {
		return loanOrderId;
	}
	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}
	public String getLoanerId() {
		return loanerId;
	}
	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderDesc() {
		return orderDesc;
	}
	public void setOrderDesc(String orderDesc) {
		this.orderDesc = orderDesc;
	}
	public String getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}
	public BigDecimal getLoanMin() {
		return loanMin;
	}
	public void setLoanMin(BigDecimal loanMin) {
		this.loanMin = loanMin;
	}
	public Integer getLoanPeriod() {
		return loanPeriod;
	}
	public void setLoanPeriod(Integer loanPeriod) {
		this.loanPeriod = loanPeriod;
	}
	public String getRepayMethod() {
		return repayMethod;
	}
	public void setRepayMethod(String repayMethod) {
		this.repayMethod = repayMethod;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public String getSalesMan() {
		return salesMan;
	}
	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGenderType() {
		return genderType;
	}
	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getHukouAddr() {
		return hukouAddr;
	}
	public void setHukouAddr(String hukouAddr) {
		this.hukouAddr = hukouAddr;
	}
	public String getCurAddr() {
		return curAddr;
	}
	public void setCurAddr(String curAddr) {
		this.curAddr = curAddr;
	}
	public String getMobileTel() {
		return mobileTel;
	}
	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}
	public String getFixedTel() {
		return fixedTel;
	}
	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}
	public String getMarriageType() {
		return marriageType;
	}
	public void setMarriageType(String marriageType) {
		this.marriageType = marriageType;
	}
	public String getHasChild() {
		return hasChild;
	}
	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQqNo() {
		return qqNo;
	}
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}
	public String getIncomeSrc() {
		return incomeSrc;
	}
	public void setIncomeSrc(String incomeSrc) {
		this.incomeSrc = incomeSrc;
	}
	public String getMortgageStatus() {
		return mortgageStatus;
	}
	public void setMortgageStatus(String mortgageStatus) {
		this.mortgageStatus = mortgageStatus;
	}
	public BigDecimal getHouseInstallPay() {
		return houseInstallPay;
	}
	public void setHouseInstallPay(BigDecimal houseInstallPay) {
		this.houseInstallPay = houseInstallPay;
	}
	public BigDecimal getRent() {
		return rent;
	}
	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getLcId() {
		return lcId;
	}
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getContractSignSite() {
		return contractSignSite;
	}
	public void setContractSignSite(String contractSignSite) {
		this.contractSignSite = contractSignSite;
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
	public String getLoanerPostalAddr() {
		return loanerPostalAddr;
	}
	public void setLoanerPostalAddr(String loanerPostalAddr) {
		this.loanerPostalAddr = loanerPostalAddr;
	}
	public String getLoanerTel() {
		return loanerTel;
	}
	public void setLoanerTel(String loanerTel) {
		this.loanerTel = loanerTel;
	}
	public Date getLoanerSignDate() {
		return loanerSignDate;
	}
	public void setLoanerSignDate(Date loanerSignDate) {
		this.loanerSignDate = loanerSignDate;
	}
	public String getLoanerJoint() {
		return loanerJoint;
	}
	public void setLoanerJoint(String loanerJoint) {
		this.loanerJoint = loanerJoint;
	}
	public String getLjIdno() {
		return ljIdno;
	}
	public void setLjIdno(String ljIdno) {
		this.ljIdno = ljIdno;
	}
	public String getLjPostalAddr() {
		return ljPostalAddr;
	}
	public void setLjPostalAddr(String ljPostalAddr) {
		this.ljPostalAddr = ljPostalAddr;
	}
	public String getLjTel() {
		return ljTel;
	}
	public void setLjTel(String ljTel) {
		this.ljTel = ljTel;
	}
	public Date getLjSignDate() {
		return ljSignDate;
	}
	public void setLjSignDate(Date ljSignDate) {
		this.ljSignDate = ljSignDate;
	}
	public String getLender() {
		return lender;
	}
	public void setLender(String lender) {
		this.lender = lender;
	}
	public String getLenderIdno() {
		return lenderIdno;
	}
	public void setLenderIdno(String lenderIdno) {
		this.lenderIdno = lenderIdno;
	}
	public Date getLenderSignDate() {
		return lenderSignDate;
	}
	public void setLenderSignDate(Date lenderSignDate) {
		this.lenderSignDate = lenderSignDate;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getAgentIdno() {
		return agentIdno;
	}
	public void setAgentIdno(String agentIdno) {
		this.agentIdno = agentIdno;
	}
	public Date getAgentSignDate() {
		return agentSignDate;
	}
	public void setAgentSignDate(Date agentSignDate) {
		this.agentSignDate = agentSignDate;
	}
	public BigDecimal getLoanEdu() {
		return loanEdu;
	}
	public void setLoanEdu(BigDecimal loanEdu) {
		this.loanEdu = loanEdu;
	}
	public Map<String, String> getDigitLoanEdu() {
		return digitLoanEdu;
	}
	public void setDigitLoanEdu(Map<String, String> digitLoanEdu) {
		this.digitLoanEdu = digitLoanEdu;
	}
	public String getCharacterLoanEdu() {
		return characterLoanEdu;
	}
	public void setCharacterLoanEdu(String characterLoanEdu) {
		this.characterLoanEdu = characterLoanEdu;
	}
	public BigDecimal getMonthlyRate() {
		return monthlyRate;
	}
	public void setMonthlyRate(BigDecimal monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	public BigDecimal getLoanInterest() {
		return loanInterest;
	}
	public void setLoanInterest(BigDecimal loanInterest) {
		this.loanInterest = loanInterest;
	}
	public Map<String, String> getDigitLoanInterest() {
		return digitLoanInterest;
	}
	public void setDigitLoanInterest(Map<String, String> digitLoanInterest) {
		this.digitLoanInterest = digitLoanInterest;
	}
	public String getCharacterLoanInterest() {
		return characterLoanInterest;
	}
	public void setCharacterLoanInterest(String characterLoanInterest) {
		this.characterLoanInterest = characterLoanInterest;
	}
	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}
	public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}
	public String getCharacterMonthlyRepayment() {
		return characterMonthlyRepayment;
	}
	public void setCharacterMonthlyRepayment(String characterMonthlyRepayment) {
		this.characterMonthlyRepayment = characterMonthlyRepayment;
	}
	public Map<String, String> getDigitMonthlyRepayment() {
		return digitMonthlyRepayment;
	}
	public void setDigitMonthlyRepayment(Map<String, String> digitMonthlyRepayment) {
		this.digitMonthlyRepayment = digitMonthlyRepayment;
	}
	public Integer getLoanPeriods() {
		return loanPeriods;
	}
	public void setLoanPeriods(Integer loanPeriods) {
		this.loanPeriods = loanPeriods;
	}
	public Integer getMonthlyRepaymentDate() {
		return monthlyRepaymentDate;
	}
	public void setMonthlyRepaymentDate(Integer monthlyRepaymentDate) {
		this.monthlyRepaymentDate = monthlyRepaymentDate;
	}
	public Date getLoanBgDate() {
		return loanBgDate;
	}
	public void setLoanBgDate(Date loanBgDate) {
		this.loanBgDate = loanBgDate;
	}
	public Date getLoanEdDate() {
		return loanEdDate;
	}
	public void setLoanEdDate(Date loanEdDate) {
		this.loanEdDate = loanEdDate;
	}
	public Date getRepaymentBgDate() {
		return repaymentBgDate;
	}
	public void setRepaymentBgDate(Date repaymentBgDate) {
		this.repaymentBgDate = repaymentBgDate;
	}
	public Date getRepaymentEdDate() {
		return repaymentEdDate;
	}
	public void setRepaymentEdDate(Date repaymentEdDate) {
		this.repaymentEdDate = repaymentEdDate;
	}
	public String getLoanerBankName() {
		return loanerBankName;
	}
	public void setLoanerBankName(String loanerBankName) {
		this.loanerBankName = loanerBankName;
	}
	public String getLoanerActName() {
		return loanerActName;
	}
	public void setLoanerActName(String loanerActName) {
		this.loanerActName = loanerActName;
	}
	public String getLoanerActNum() {
		return loanerActNum;
	}
	public void setLoanerActNum(String loanerActNum) {
		this.loanerActNum = loanerActNum;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getObliMatchStatus() {
		return obliMatchStatus;
	}
	public void setObliMatchStatus(String obliMatchStatus) {
		this.obliMatchStatus = obliMatchStatus;
	}
}
