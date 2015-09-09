package com.qqms.model;

import java.math.BigDecimal;

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
 * 账目流水表实体
 * 
 * @author liuhh
 *
 */
@Entity
@Table(name = "t_bp_accounts_journal")
public class AccountsJournal implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String bankFlowId;
	private CreditAuditReport creditAuditReport;
	private String bankaccountNo;
	private String bankaccountType;
	private Integer monName01;
	private Integer monName02;
	private Integer monName03;
	private Integer monName04;
	private Integer monName05;
	private Integer monName06;
	private BigDecimal income01;
	private BigDecimal income02;
	private BigDecimal income03;
	private BigDecimal income04;
	private BigDecimal income05;
	private BigDecimal income06;
	private BigDecimal averageIncome;
	private String analysis;

	public AccountsJournal() {
	}

	public AccountsJournal(String bankFlowId) {
		this.bankFlowId = bankFlowId;
	}

	public AccountsJournal(String bankFlowId,
			CreditAuditReport creditAuditReport, String bankaccountNo,
			String bankaccountType, Integer monName01, Integer monName02,
			Integer monName03, Integer monName04, Integer monName05,
			Integer monName06, BigDecimal income01, BigDecimal income02,
			BigDecimal income03, BigDecimal income04, BigDecimal income05,
			BigDecimal income06, BigDecimal averageIncome, String analysis) {
		this.bankFlowId = bankFlowId;
		this.creditAuditReport = creditAuditReport;
		this.bankaccountNo = bankaccountNo;
		this.bankaccountType = bankaccountType;
		this.monName01 = monName01;
		this.monName02 = monName02;
		this.monName03 = monName03;
		this.monName04 = monName04;
		this.monName05 = monName05;
		this.monName06 = monName06;
		this.income01 = income01;
		this.income02 = income02;
		this.income03 = income03;
		this.income04 = income04;
		this.income05 = income05;
		this.income06 = income06;
		this.averageIncome = averageIncome;
		this.analysis = analysis;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "BANK_FLOW_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getBankFlowId() {
		return this.bankFlowId;
	}

	public void setBankFlowId(String bankFlowId) {
		this.bankFlowId = bankFlowId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR_ID")
	public CreditAuditReport getCreditAuditReport() {
		return this.creditAuditReport;
	}

	public void setCreditAuditReport(CreditAuditReport creditAuditReport) {
		this.creditAuditReport = creditAuditReport;
	}

	@Column(name = "BANKACCOUNT_NO", length = 30)
	public String getBankaccountNo() {
		return this.bankaccountNo;
	}

	public void setBankaccountNo(String bankaccountNo) {
		this.bankaccountNo = bankaccountNo;
	}

	@Column(name = "BANKACCOUNT_TYPE", length = 20)
	public String getBankaccountType() {
		return this.bankaccountType;
	}

	public void setBankaccountType(String bankaccountType) {
		this.bankaccountType = bankaccountType;
	}

	@Column(name = "MON_NAME_01", length = 10)
	public Integer getMonName01() {
		return this.monName01;
	}

	public void setMonName01(Integer monName01) {
		this.monName01 = monName01;
	}

	@Column(name = "MON_NAME_02", length = 10)
	public Integer getMonName02() {
		return this.monName02;
	}

	public void setMonName02(Integer monName02) {
		this.monName02 = monName02;
	}

	@Column(name = "MON_NAME_03", length = 10)
	public Integer getMonName03() {
		return this.monName03;
	}

	public void setMonName03(Integer monName03) {
		this.monName03 = monName03;
	}

	@Column(name = "MON_NAME_04", length = 10)
	public Integer getMonName04() {
		return this.monName04;
	}

	public void setMonName04(Integer monName04) {
		this.monName04 = monName04;
	}

	@Column(name = "MON_NAME_05", length = 10)
	public Integer getMonName05() {
		return this.monName05;
	}

	public void setMonName05(Integer monName05) {
		this.monName05 = monName05;
	}

	@Column(name = "MON_NAME_06", length = 10)
	public Integer getMonName06() {
		return this.monName06;
	}

	public void setMonName06(Integer monName06) {
		this.monName06 = monName06;
	}

	@Column(name = "INCOME01", precision = 20, scale = 5)
	public BigDecimal getIncome01() {
		return this.income01;
	}

	public void setIncome01(BigDecimal income01) {
		this.income01 = income01;
	}

	@Column(name = "INCOME02", precision = 20, scale = 5)
	public BigDecimal getIncome02() {
		return this.income02;
	}

	public void setIncome02(BigDecimal income02) {
		this.income02 = income02;
	}

	@Column(name = "INCOME03", precision = 20, scale = 5)
	public BigDecimal getIncome03() {
		return this.income03;
	}

	public void setIncome03(BigDecimal income03) {
		this.income03 = income03;
	}

	@Column(name = "INCOME04", precision = 20, scale = 5)
	public BigDecimal getIncome04() {
		return this.income04;
	}

	public void setIncome04(BigDecimal income04) {
		this.income04 = income04;
	}

	@Column(name = "INCOME05", precision = 20, scale = 5)
	public BigDecimal getIncome05() {
		return this.income05;
	}

	public void setIncome05(BigDecimal income05) {
		this.income05 = income05;
	}

	@Column(name = "INCOME06", precision = 20, scale = 5)
	public BigDecimal getIncome06() {
		return this.income06;
	}

	public void setIncome06(BigDecimal income06) {
		this.income06 = income06;
	}

	@Column(name = "AVERAGE_INCOME", precision = 20, scale = 5)
	public BigDecimal getAverageIncome() {
		return this.averageIncome;
	}

	public void setAverageIncome(BigDecimal averageIncome) {
		this.averageIncome = averageIncome;
	}

	@Column(name = "ANALYSIS", length = 512)
	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

}
