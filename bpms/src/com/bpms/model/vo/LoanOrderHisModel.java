package com.bpms.model.vo;

import java.util.Date;

/**
 * 贷款订单履历表
 * 
 * @author PANCHUANHE
 * @date 2015/6/16
 */
public class LoanOrderHisModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 贷款订单履历ID号
	 */
	private String loanOrderHiId;

	/**
	 * 订单的ID
	 */
	private String loanOrderId;

	/**
	 * 订单的申请日期
	 */
	private Date createDate;

	/**
	 * 贷款订单状态
	 */
	private String orderStatus;
	/**
	 * 贷款订单状态的名称
	 */
	private String orderStatusName;
	/**
	 * 订单受理人角色ID
	 */
	private String roleId;

	/**
	 * 订单受理人名称
	 */
	private String roleName;
	/**
	 * 贷款订单受理人
	 */
	private String assignee;
	/**
	 * 受理人姓名
	 */
	private String assigneeName;
	/**
	 * 备注信息
	 */
	private String comment;
	/**
	 * 订单受理时间
	 */
	private Date agentTime;
	/**
	 * 返回结果
	 */
	private String processingResult;

	/**
	 * 是否涉及车贷
	 */
	private String isInvolvedLoanCar;

	public String getProcessingResult() {
		return processingResult;
	}

	public void setProcessingResult(String processingResult) {
		this.processingResult = processingResult;
	}

	public String getIsInvolvedLoanCar() {
		return isInvolvedLoanCar;
	}

	public void setIsInvolvedLoanCar(String isInvolvedLoanCar) {
		this.isInvolvedLoanCar = isInvolvedLoanCar;
	}

	public LoanOrderHisModel(String loanOrderHiId, String loanOrderId,
			Date createDate, String orderStatus, String orderStatusName,
			String roleId, String roleName, String assignee,
			String assigneeName, String comment, Date agentTime) {
		super();
		this.loanOrderHiId = loanOrderHiId;
		this.loanOrderId = loanOrderId;
		this.createDate = createDate;
		this.orderStatus = orderStatus;
		this.orderStatusName = orderStatusName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.assignee = assignee;
		this.assigneeName = assigneeName;
		this.comment = comment;
		this.agentTime = agentTime;
	}

	public LoanOrderHisModel() {
		super();
	}

	public String getLoanOrderHiId() {
		return loanOrderHiId;
	}

	public void setLoanOrderHiId(String loanOrderHiId) {
		this.loanOrderHiId = loanOrderHiId;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getAgentTime() {
		return agentTime;
	}

	public void setAgentTime(Date agentTime) {
		this.agentTime = agentTime;
	}

}
