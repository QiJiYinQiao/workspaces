package com.bpms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 稽核信息记录实体
 * 
 * @author PANCHUANHE
 * @date 2015/6/16
 */
@Entity
@Table(name = "t_bp_audit_info_record")
public class AuditInfoRecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 稽核信息记录ID
	 */
	private String auditId;
	/**
	 * 订单
	 */
	private LoanOrder loanOrder;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 核查项目(对象)
	 */
	private String auditItem;
	/**
	 * 电核内容记录
	 */
	private String phoneAuditRecord;
	/**
	 * 网核内容记录
	 */
	private String webAuditRecord;
	/**
	 * 联系方式
	 */
	private String contactMethod;
	/**
	 * 114查询
	 */
	private String query114;

	public AuditInfoRecord() {
	}

	public AuditInfoRecord(String auditId) {
		this.auditId = auditId;
	}

	public AuditInfoRecord(String auditId, LoanOrder LoanOrder, String name,
			String auditItem, String phoneAuditRecord, String webAuditRecord,
			String contactMethod, String query114) {
		this.auditId = auditId;
		this.loanOrder = LoanOrder;
		this.name = name;
		this.auditItem = auditItem;
		this.phoneAuditRecord = phoneAuditRecord;
		this.webAuditRecord = webAuditRecord;
		this.contactMethod = contactMethod;
		this.query114 = query114;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "AUDIT_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getAuditId() {
		return this.auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOAN_ORDER_ID")
	public LoanOrder getLoanOrder() {
		return this.loanOrder;
	}

	public void setLoanOrder(LoanOrder LoanOrder) {
		this.loanOrder = LoanOrder;
	}

	@Column(name = "TARGET_NAME", length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "AUDIT_TARGET", length = 30)
	public String getAuditItem() {
		return this.auditItem;
	}

	public void setAuditItem(String auditItem) {
		this.auditItem = auditItem;
	}

	@Column(name = "PHONE_AUDIT_RECORD", length = 1024)
	public String getPhoneAuditRecord() {
		return this.phoneAuditRecord;
	}

	public void setPhoneAuditRecord(String phoneAuditRecord) {
		this.phoneAuditRecord = phoneAuditRecord;
	}

	@Column(name = "WEB_AUDIT_RECORD", length = 1024)
	public String getWebAuditRecord() {
		return this.webAuditRecord;
	}

	public void setWebAuditRecord(String webAuditRecord) {
		this.webAuditRecord = webAuditRecord;
	}

	@Column(name = "CONTACT_METHOD", length = 30)
	public String getContactMethod() {
		return this.contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	@Column(name = "QUERY_114", length = 1024)
	public String getQuery114() {
		return this.query114;
	}

	public void setQuery114(String query114) {
		this.query114 = query114;
	}

}
