package com.qqms.model;

// Generated 2015-6-16 10:13:19 by Hibernate Tools 4.3.1

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
 * 征信查询信息 实体
 */
@Entity
@Table(name = "t_bp_credit_investigation")
public class CreditInvestigation implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private CreditAuditReport creditAuditReport;
	private String creditRefId;
	private Integer selfQuery;
	private Integer loanApproval;
	private Integer creditCardApproval;
	private Integer internetQuery;
	private String queryInterval;
	private String queryNotes;

	public CreditInvestigation() {
	}

	public CreditInvestigation(String creditRefId) {
		this.creditRefId = creditRefId;
	}

	public CreditInvestigation(String creditRefId, Integer selfQuery,
			Integer loanApproval, Integer creditCardApproval,
			Integer internetQuery, String queryInterval, String queryNotes) {
		this.creditRefId = creditRefId;
		this.selfQuery = selfQuery;
		this.loanApproval = loanApproval;
		this.creditCardApproval = creditCardApproval;
		this.internetQuery = internetQuery;
		this.queryInterval = queryInterval;
		this.queryNotes = queryNotes;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "CREDIT_REF_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getCreditRefId() {
		return this.creditRefId;
	}

	public void setCreditRefId(String creditRefId) {
		this.creditRefId = creditRefId;
	}

	@Column(name = "SELF_QUERY")
	public Integer getSelfQuery() {
		return this.selfQuery;
	}

	public void setSelfQuery(Integer selfQuery) {
		this.selfQuery = selfQuery;
	}

	@Column(name = "LOAN_APPROVAL")
	public Integer getLoanApproval() {
		return this.loanApproval;
	}

	public void setLoanApproval(Integer loanApproval) {
		this.loanApproval = loanApproval;
	}

	@Column(name = "CREDIT_CARD_APPROVAL")
	public Integer getCreditCardApproval() {
		return this.creditCardApproval;
	}

	public void setCreditCardApproval(Integer creditCardApproval) {
		this.creditCardApproval = creditCardApproval;
	}

	@Column(name = "INTERNET_QUERY")
	public Integer getInternetQuery() {
		return this.internetQuery;
	}

	public void setInternetQuery(Integer internetQuery) {
		this.internetQuery = internetQuery;
	}

	@Column(name = "QUERY_INTERVAL", length = 1)
	public String getQueryInterval() {
		return this.queryInterval;
	}

	public void setQueryInterval(String queryInterval) {
		this.queryInterval = queryInterval;
	}

	@Column(name = "QUERY_NOTES", length = 512)
	public String getQueryNotes() {
		return this.queryNotes;
	}

	public void setQueryNotes(String queryNotes) {
		this.queryNotes = queryNotes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_ID")
	public CreditAuditReport getCreditAuditReport() {
		return this.creditAuditReport;
	}

	public void setCreditAuditReport(CreditAuditReport creditAuditReport) {
		this.creditAuditReport = creditAuditReport;
	}

}
