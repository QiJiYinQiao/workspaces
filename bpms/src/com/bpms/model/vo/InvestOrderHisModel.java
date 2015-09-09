package com.bpms.model.vo;

import java.util.Date;
/**
 * @ClassName: InvestOrderHisModel 
 * @Description: TODO 投资订单履历表vo
 * @author PANCHUANHE
 * @date 2015年7月28日 下午1:19:35
 */
public class InvestOrderHisModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 贷款订单履历ID号
	 */
	private String invOrdHisId;
	/**
	 * 订单
	 */
	private String investOrderId;
	/**
	 * 贷款订单状态
	 */
	private String orderStatus;
	/**
	 * 订单受理人角色ID
	 */
	private String roleId;
	/**
	 * 贷款订单受理人
	 */
	private String assignee;
	/**
	 * 备注信息
	 */
	private String comment;
	/**
	 * 订单受理时间
	 */
	private Date agentTime;
	/**
	 * 处理结果
	 */
	private String handleResult;
	/**
	 * 订单创建日期
	 */
	private Date createDate;
	/**
	 * 订单受理人角色
	 */
	private String roleName;
	/**
	 * 订单受理人
	 */
	private String assigneeName;
	/**
	 * 贷款订单状态的名称
	 */
	private String orderStatusName;
	
	
	public String getInvOrdHisId() {
		return invOrdHisId;
	}
	public void setInvOrdHisId(String invOrdHisId) {
		this.invOrdHisId = invOrdHisId;
	}
	public String getInvestOrderId() {
		return investOrderId;
	}
	public void setInvestOrderId(String investOrderId) {
		this.investOrderId = investOrderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
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
	public String getHandleResult() {
		return handleResult;
	}
	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public String getOrderStatusName() {
		return orderStatusName;
	}
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	
}
