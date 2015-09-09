package com.qqms.model;

// Generated 2015-6-16 10:13:19 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 贷款详情 实体
 */
@Entity
@Table(name = "t_bp_loans_details")
public class LoansDetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String existLoanId;
	private Integer totalLoanCount;
	private Integer outstandingCount;
	private BigDecimal outstandingSum;
	private BigDecimal outstandingBalance;
	private Integer yearOverdue;
	private Integer cumulativeOverdue;
	private BigDecimal overdueRate;
	private BigDecimal monthRepay;
	private String lastLoanSpace;
	private String loanDetail;
	private Set<CreditAuditReport> creditAuditReports = new HashSet<CreditAuditReport>(
			0);

	public LoansDetails() {
	}

	public LoansDetails(String existLoanId) {
		this.existLoanId = existLoanId;
	}

	public LoansDetails(String existLoanId, Integer totalLoanCount,
			Integer outstandingCount, BigDecimal outstandingSum,
			BigDecimal outstandingBalance, Integer yearOverdue,
			Integer cumulativeOverdue, BigDecimal overdueRate,
			BigDecimal monthRepay, String lastLoanSpace, String loanDetail,
			Set<CreditAuditReport> creditAuditReports) {
		this.existLoanId = existLoanId;
		this.totalLoanCount = totalLoanCount;
		this.outstandingCount = outstandingCount;
		this.outstandingSum = outstandingSum;
		this.outstandingBalance = outstandingBalance;
		this.yearOverdue = yearOverdue;
		this.cumulativeOverdue = cumulativeOverdue;
		this.overdueRate = overdueRate;
		this.monthRepay = monthRepay;
		this.lastLoanSpace = lastLoanSpace;
		this.loanDetail = loanDetail;
		this.creditAuditReports = creditAuditReports;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "EXIST_LOAN_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getExistLoanId() {
		return this.existLoanId;
	}

	public void setExistLoanId(String existLoanId) {
		this.existLoanId = existLoanId;
	}

	@Column(name = "TOTAL_LOAN_COUNT")
	public Integer getTotalLoanCount() {
		return this.totalLoanCount;
	}

	public void setTotalLoanCount(Integer totalLoanCount) {
		this.totalLoanCount = totalLoanCount;
	}

	@Column(name = "OUTSTANDING_COUNT")
	public Integer getOutstandingCount() {
		return this.outstandingCount;
	}

	public void setOutstandingCount(Integer outstandingCount) {
		this.outstandingCount = outstandingCount;
	}

	@Column(name = "OUTSTANDING_SUM", precision = 20, scale = 5)
	public BigDecimal getOutstandingSum() {
		return this.outstandingSum;
	}

	public void setOutstandingSum(BigDecimal outstandingSum) {
		this.outstandingSum = outstandingSum;
	}

	@Column(name = "OUTSTANDING_BALANCE", precision = 20, scale = 5)
	public BigDecimal getOutstandingBalance() {
		return this.outstandingBalance;
	}

	public void setOutstandingBalance(BigDecimal outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}

	@Column(name = "YEAR_OVERDUE")
	public Integer getYearOverdue() {
		return this.yearOverdue;
	}

	public void setYearOverdue(Integer yearOverdue) {
		this.yearOverdue = yearOverdue;
	}

	@Column(name = "CUMULATIVE_OVERDUE")
	public Integer getCumulativeOverdue() {
		return this.cumulativeOverdue;
	}

	public void setCumulativeOverdue(Integer cumulativeOverdue) {
		this.cumulativeOverdue = cumulativeOverdue;
	}

	@Column(name = "OVERDUE_RATE", precision = 10, scale = 5)
	public BigDecimal getOverdueRate() {
		return this.overdueRate;
	}

	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

	@Column(name = "MONTH_REPAY", precision = 20, scale = 5)
	public BigDecimal getMonthRepay() {
		return this.monthRepay;
	}

	public void setMonthRepay(BigDecimal monthRepay) {
		this.monthRepay = monthRepay;
	}

	@Column(name = "LAST_LOAN_SPACE", length = 512)
	public String getLastLoanSpace() {
		return this.lastLoanSpace;
	}

	public void setLastLoanSpace(String lastLoanSpace) {
		this.lastLoanSpace = lastLoanSpace;
	}

	@Column(name = "LOAN_DETAIL", length = 512)
	public String getLoanDetail() {
		return this.loanDetail;
	}

	public void setLoanDetail(String loanDetail) {
		this.loanDetail = loanDetail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "loansDetails")
	@JSONField(serialize = false)
	public Set<CreditAuditReport> getCreditAuditReports() {
		return this.creditAuditReports;
	}

	public void setCreditAuditReports(Set<CreditAuditReport> creditAuditReports) {
		this.creditAuditReports = creditAuditReports;
	}

}
