package com.bpms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * 投资订单履历
 * 
 * @author liuhh
 *
 */
@Entity
@Table(name = "t_bp_invest_order_his")
public class InvestOrderHis implements java.io.Serializable {

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
	

	public InvestOrderHis() {
	}

	public InvestOrderHis(String invOrdHisId) {
		super();
		this.invOrdHisId = invOrdHisId;
	}
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "INV_ORD_HIS_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getInvOrdHisId() {
		return this.invOrdHisId;
	}

	public void setInvOrdHisId(String invOrdHisId) {
		this.invOrdHisId = invOrdHisId;
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

	@Column(name = "COMMENT", length = 255)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AGENT_TIME")
	public Date getAgentTime() {
		return this.agentTime;
	}

	public void setAgentTime(Date agentTime) {
		this.agentTime = agentTime;
	}

	@Column(name = "INVEST_ORDER_ID", length = 40)
	public String getInvestOrderId() {
		return investOrderId;
	}

	public void setInvestOrderId(String investOrderId) {
		this.investOrderId = investOrderId;
	}
	@Column(name = "HANDLE_RESULT", length = 1)
	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	
	
}
