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
 * 信用卡信息 实体
 */
@Entity
@Table(name = "t_bp_credit_cards_details")
public class CreditCardsDetails implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String cardInfoId;
	private Integer cardCount;
	private Integer cardInUse;
	private BigDecimal creditTotalAmount;
	private BigDecimal creditLimit;
	private Integer yearOverdue;
	private Integer validateYear;
	private BigDecimal useRate;
	private Integer overdueCardCount;
	private BigDecimal overdueRatio;
	private BigDecimal maxLimit;
	private BigDecimal monthRepay;
	private Integer cumulativeOverdue;
	private Integer maxQishu;
	private BigDecimal overdueRate;
	private String detail;
	private Set<CreditAuditReport> CreditAuditReports = new HashSet<CreditAuditReport>(
			0);

	public CreditCardsDetails() {
	}

	public CreditCardsDetails(String cardInfoId) {
		this.cardInfoId = cardInfoId;
	}

	public CreditCardsDetails(String cardInfoId, Integer cardCount,
			Integer cardInUse, BigDecimal creditTotalAmount,
			BigDecimal creditLimit, Integer yearOverdue, Integer validateYear,
			BigDecimal useRate, Integer overdueCardCount,
			BigDecimal overdueRatio, BigDecimal maxLimit,
			BigDecimal monthRepay, Integer cumulativeOverdue, Integer maxQishu,
			BigDecimal overdueRate, String detail,
			Set<CreditAuditReport> creditAuditReports) {
		this.cardInfoId = cardInfoId;
		this.cardCount = cardCount;
		this.cardInUse = cardInUse;
		this.creditTotalAmount = creditTotalAmount;
		this.creditLimit = creditLimit;
		this.yearOverdue = yearOverdue;
		this.validateYear = validateYear;
		this.useRate = useRate;
		this.overdueCardCount = overdueCardCount;
		this.overdueRatio = overdueRatio;
		this.maxLimit = maxLimit;
		this.monthRepay = monthRepay;
		this.cumulativeOverdue = cumulativeOverdue;
		this.maxQishu = maxQishu;
		this.overdueRate = overdueRate;
		this.detail = detail;
		this.CreditAuditReports = creditAuditReports;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "CARD_INFO_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getCardInfoId() {
		return this.cardInfoId;
	}

	public void setCardInfoId(String cardInfoId) {
		this.cardInfoId = cardInfoId;
	}

	@Column(name = "CARD_COUNT")
	public Integer getCardCount() {
		return this.cardCount;
	}

	public void setCardCount(Integer cardCount) {
		this.cardCount = cardCount;
	}

	@Column(name = "CARD_IN_USE")
	public Integer getCardInUse() {
		return this.cardInUse;
	}

	public void setCardInUse(Integer cardInUse) {
		this.cardInUse = cardInUse;
	}

	@Column(name = "CREDIT_TOTAL_AMOUNT", precision = 20, scale = 5)
	public BigDecimal getCreditTotalAmount() {
		return this.creditTotalAmount;
	}

	public void setCreditTotalAmount(BigDecimal creditTotalAmount) {
		this.creditTotalAmount = creditTotalAmount;
	}

	@Column(name = "CREDIT_LIMIT", precision = 20, scale = 5)
	public BigDecimal getCreditLimit() {
		return this.creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Column(name = "YEAR_OVERDUE")
	public Integer getYearOverdue() {
		return this.yearOverdue;
	}

	public void setYearOverdue(Integer yearOverdue) {
		this.yearOverdue = yearOverdue;
	}

	@Column(name = "VALIDATE_YEAR")
	public Integer getValidateYear() {
		return this.validateYear;
	}

	public void setValidateYear(Integer validateYear) {
		this.validateYear = validateYear;
	}

	@Column(name = "USE_RATE", precision = 10, scale = 5)
	public BigDecimal getUseRate() {
		return this.useRate;
	}

	public void setUseRate(BigDecimal useRate) {
		this.useRate = useRate;
	}

	@Column(name = "OVERDUE_CARD_COUNT")
	public Integer getOverdueCardCount() {
		return this.overdueCardCount;
	}

	public void setOverdueCardCount(Integer overdueCardCount) {
		this.overdueCardCount = overdueCardCount;
	}

	@Column(name = "OVERDUE_RATIO", precision = 10, scale = 5)
	public BigDecimal getOverdueRatio() {
		return this.overdueRatio;
	}

	public void setOverdueRatio(BigDecimal overdueRatio) {
		this.overdueRatio = overdueRatio;
	}

	@Column(name = "MAX_LIMIT", precision = 20, scale = 5)
	public BigDecimal getMaxLimit() {
		return this.maxLimit;
	}

	public void setMaxLimit(BigDecimal maxLimit) {
		this.maxLimit = maxLimit;
	}

	@Column(name = "MONTH_REPAY", precision = 20, scale = 5)
	public BigDecimal getMonthRepay() {
		return this.monthRepay;
	}

	public void setMonthRepay(BigDecimal monthRepay) {
		this.monthRepay = monthRepay;
	}

	@Column(name = "CUMULATIVE_OVERDUE")
	public Integer getCumulativeOverdue() {
		return this.cumulativeOverdue;
	}

	public void setCumulativeOverdue(Integer cumulativeOverdue) {
		this.cumulativeOverdue = cumulativeOverdue;
	}

	@Column(name = "MAX_QISHU")
	public Integer getMaxQishu() {
		return this.maxQishu;
	}

	public void setMaxQishu(Integer maxQishu) {
		this.maxQishu = maxQishu;
	}

	@Column(name = "OVERDUE_RATE", precision = 10, scale = 5)
	public BigDecimal getOverdueRate() {
		return this.overdueRate;
	}

	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

	@Column(name = "DETAIL", length = 512)
	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "creditCardsDetails")
	@JSONField(serialize = false)
	public Set<CreditAuditReport> getCreditAuditReports() {
		return this.CreditAuditReports;
	}

	public void setCreditAuditReports(Set<CreditAuditReport> creditAuditReports) {
		this.CreditAuditReports = creditAuditReports;
	}

}
