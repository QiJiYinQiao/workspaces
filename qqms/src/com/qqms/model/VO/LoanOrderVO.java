package com.qqms.model.VO;

/**
 * 
 * @ClassName: LoanOrderVO
 * @Description: TODO 客户贷款订单VO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年6月24日 下午5:55:11
 *
 */
public class LoanOrderVO implements Cloneable {
	private String loanerId;// 贷款人id
	private String loanerName;// 贷款人姓名
	private String idNo;// 贷款人身份证号
	private String loanOrderId;// 订单id
	private String loanType;// 贷款类型
	private String loanAmount;// 贷款申请额度
	private String loanMin;// 最低接受额度
	private String createDate;// 贷款申请日期
	private String creator;// 订单处理员工
	private String statusName;// 贷款订单状态名称
	private String loanPeriod;//贷款申请期限
	private String repayMethod;//还款方式
	private String purpose;//贷款用途

	public String getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

	public String getLoanerName() {
		return loanerName;
	}

	public void setLoanerName(String loanerName) {
		this.loanerName = loanerName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanMin() {
		return loanMin;
	}

	public void setLoanMin(String loanMin) {
		this.loanMin = loanMin;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	

	public String getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(String loanPeriod) {
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

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}
