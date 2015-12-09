package com.oasys.model;

// Generated 2015-9-23 15:26:54 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 审批流程履历实体类
 * @ClassName: AuditProcHis 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月24日 下午1:12:52
 */
@Entity
@Table(name = "t_oa_audit_proc_his")
public class AuditProcHis implements java.io.Serializable,Cloneable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer aphId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请状态
	 */
	private Integer appStatus;
	/**
	 * 受理人
	 */
	private Integer handler;
	/**
	 * 受理人角色
	 */
	private String handlerRole;
	/**
	 * 受理时间
	 */
	private Date handleDate;
	/**
	 * 处理结果
	 */
	private String handleResult;
	/**
	 * 备注信息
	 */
	private String remark;

	
	public AuditProcHis() {
	}

	public AuditProcHis(String appNo, Integer appStatus, Integer handler,
			String handlerRole, Date handleDate, String handleResult,
			String remark) {
		this.appNo = appNo;
		this.appStatus = appStatus;
		this.handler = handler;
		this.handlerRole = handlerRole;
		this.handleDate = handleDate;
		this.handleResult = handleResult;
		this.remark = remark;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "APH_ID", unique = true, nullable = false)
	public Integer getAphId() {
		return this.aphId;
	}

	public void setAphId(Integer aphId) {
		this.aphId = aphId;
	}

	@Column(name = "APP_NO", length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "APP_STATUS")
	public Integer getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "HANDLER")
	public Integer getHandler() {
		return this.handler;
	}

	public void setHandler(Integer handler) {
		this.handler = handler;
	}

	@Column(name = "HANDLER_ROLE")
	public String getHandlerRole() {
		return this.handlerRole;
	}

	public void setHandlerRole(String handlerRole) {
		this.handlerRole = handlerRole;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HANDLE_DATE", length = 19)
	public Date getHandleDate() {
		return this.handleDate;
	}

	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}

	@Column(name = "HANDLE_RESULT", length = 1)
	public String getHandleResult() {
		return this.handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
