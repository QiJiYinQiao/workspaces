package com.bpms.model;

// Generated 2015-6-16 10:13:19 by Hibernate Tools 4.3.1

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 信审报告实体
 */
@Entity
@Table(name = "t_bp_credit_audit_report")
public class CreditAuditReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String carId;
	private Assets assets;
	private CreditCardsDetails creditCardsDetails;
	private FinalAuditReport finalAuditReport;
	private FirstAuditReport firstAuditReport;
	private String loanOrderId;
	private LoansDetails loansDetails;
	private String lawQueryStatus;
	private String comCreditStatus;
	private String loanCity;
	private List<AccountsJournal> accountsJournals = new ArrayList<AccountsJournal>();
	private List<CreditInvestigation> creditInvestigations = new ArrayList<CreditInvestigation>();

	public CreditAuditReport() {
	}

	public CreditAuditReport(String carId) {
		this.carId = carId;
	}

	public CreditAuditReport(String carId, Assets assets,
			CreditCardsDetails creditCardsDetails,
			FinalAuditReport finalAuditReport,
			FirstAuditReport firstAuditReport, String loanOrderId,
			LoansDetails loansDetails, String lawQueryStatus,
			String comCreditStatus, List<AccountsJournal> accountsJournals,
			List<CreditInvestigation> creditInvestigations) {
		super();
		this.carId = carId;
		this.assets = assets;
		this.creditCardsDetails = creditCardsDetails;
		this.finalAuditReport = finalAuditReport;
		this.firstAuditReport = firstAuditReport;
		this.loanOrderId = loanOrderId;
		this.loansDetails = loansDetails;
		this.lawQueryStatus = lawQueryStatus;
		this.comCreditStatus = comCreditStatus;
		this.accountsJournals = accountsJournals;
		this.creditInvestigations = creditInvestigations;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "CAR_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getCarId() {
		return this.carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	@Column(name = "LOAN_ORDER_ID")
	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSET_ID")
	public Assets getAssets() {
		return this.assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "CARD_INFO_ID")
	public CreditCardsDetails getCreditCardsDetails() {
		return this.creditCardsDetails;
	}

	public void setCreditCardsDetails(CreditCardsDetails creditCardsDetails) {
		this.creditCardsDetails = creditCardsDetails;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "FINA_ID")
	public FinalAuditReport getFinalAuditReport() {
		return this.finalAuditReport;
	}

	public void setFinalAuditReport(FinalAuditReport finalAuditReport) {
		this.finalAuditReport = finalAuditReport;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "FIRS_ID")
	public FirstAuditReport getFirstAuditReport() {
		return this.firstAuditReport;
	}

	public void setFirstAuditReport(FirstAuditReport firstAuditReport) {
		this.firstAuditReport = firstAuditReport;
	}

	@ManyToOne(cascade = { CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "EXIST_LOAN_ID")
	public LoansDetails getLoansDetails() {
		return this.loansDetails;
	}

	public void setLoansDetails(LoansDetails loansDetails) {
		this.loansDetails = loansDetails;
	}

	@Column(name = "LAW_QUERY_STATUS", length = 1)
	public String getLawQueryStatus() {
		return this.lawQueryStatus;
	}

	public void setLawQueryStatus(String lawQueryStatus) {
		this.lawQueryStatus = lawQueryStatus;
	}

	@Column(name = "COM_CREDIT_STATUS", length = 1)
	public String getComCreditStatus() {
		return this.comCreditStatus;
	}

	public void setComCreditStatus(String comCreditStatus) {
		this.comCreditStatus = comCreditStatus;
	}

	@Column(name = "LOAN_CITY", length = 50)
	public String getLoanCity() {
		return this.loanCity;
	}

	public void setLoanCity(String loanCity) {
		this.loanCity = loanCity;
	}

	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = AccountsJournal.class)
	@JoinColumn(name = "CAR_ID", referencedColumnName = "CAR_ID")
	public List<AccountsJournal> getAccountsJournals() {
		return this.accountsJournals;
	}

	public void setAccountsJournals(List<AccountsJournal> accountsJournals) {
		this.accountsJournals = accountsJournals;
	}

	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.REFRESH }, fetch = FetchType.LAZY, targetEntity = CreditInvestigation.class)
	@JoinColumn(name = "CAR_ID", referencedColumnName = "CAR_ID")
	public List<CreditInvestigation> getCreditInvestigations() {
		return creditInvestigations;
	}

	public void setCreditInvestigations(
			List<CreditInvestigation> creditInvestigations) {
		this.creditInvestigations = creditInvestigations;
	}

}
