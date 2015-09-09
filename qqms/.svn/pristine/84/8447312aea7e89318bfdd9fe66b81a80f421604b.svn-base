package com.qqms.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 贷款订单履历表
 * @author PANCHUANHE
 * @date 2015/6/16
 */
@Entity
@Table(name = "t_bp_loan_order_his")
public class LoanOrderHis implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 贷款订单履历ID号
	 */
	private String loanOrderHiId;
	/**
	 * 订单
	 */
	private LoanOrder loanOrder;
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

	public LoanOrderHis() {
	}

	public LoanOrderHis(String loanOrderHiId, LoanOrder loanOrder) {
		this.loanOrderHiId = loanOrderHiId;
		this.loanOrder = loanOrder;
	}

	public LoanOrderHis(String loanOrderHiId, LoanOrder loanOrder,
			String orderStatus, String roleId, String assignee, String comment,
			Date agentTime) {
		this.loanOrderHiId = loanOrderHiId;
		this.loanOrder = loanOrder;
		this.orderStatus = orderStatus;
		this.roleId = roleId;
		this.assignee = assignee;
		this.comment = comment;
		this.agentTime = agentTime;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "LOAN_ORDER_HI_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getLoanOrderHiId() {
		return this.loanOrderHiId;
	}

	public void setLoanOrderHiId(String loanOrderHiId) {
		this.loanOrderHiId = loanOrderHiId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_ORDER_ID", nullable = false)
	public LoanOrder getLoanOrder() {
		return this.loanOrder;
	}

	public void setLoanOrder(LoanOrder loanOrder) {
		this.loanOrder = loanOrder;
	}

	@Column(name = "ORDER_STATUS", length = 100)
	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Column(name = "ROLE_ID", length = 40)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ASSIGNEE", length = 40)
	public String getAssignee() {
		return this.assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	@Column(name = "COMMENT")
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AGENT_TIME", length = 19)
	public Date getAgentTime() {
		return this.agentTime;
	}

	public void setAgentTime(Date agentTime) {
		this.agentTime = agentTime;
	}

}
