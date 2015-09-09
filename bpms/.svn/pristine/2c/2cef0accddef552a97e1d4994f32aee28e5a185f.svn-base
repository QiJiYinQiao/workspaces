package com.bpms.model.vo;

// Generated 2015-6-16 10:13:19 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 终审资质分析 实体
 */
public class FinalAuditReportModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String finaId;
	private String loanOrderId;
	private String description;
	private String finaPersonnel;
	private String finaPersSugg;
	private Date finaDate;
	private BigDecimal contractLoanAmount;
	private BigDecimal actualLoanAmount;
	private String loanPeriodType;
	private String monthServiceFeeRate;
	private String loanInterestRate;
	private BigDecimal visitFee;
	private BigDecimal monthRepay;

	public FinalAuditReportModel() {
	}

	public FinalAuditReportModel(String finaId) {
		this.finaId = finaId;
	}

	public FinalAuditReportModel(String finaId, String loanOrderId,
			String description, String finaPersonnel, String finaPersSugg,
			Date finaDate, BigDecimal contractLoanAmount,
			BigDecimal actualLoanAmount, String loanPeriodType,
			String monthServiceFeeRate, String loanInterestRate,
			BigDecimal visitFee, BigDecimal monthRepay) {
		this.finaId = finaId;
		this.loanOrderId = loanOrderId;
		this.description = description;
		this.finaPersonnel = finaPersonnel;
		this.finaPersSugg = finaPersSugg;
		this.finaDate = finaDate;
		this.contractLoanAmount = contractLoanAmount;
		this.actualLoanAmount = actualLoanAmount;
		this.loanPeriodType = loanPeriodType;
		this.monthServiceFeeRate = monthServiceFeeRate;
		this.loanInterestRate = loanInterestRate;
		this.visitFee = visitFee;
		this.monthRepay = monthRepay;
	}

	public String getFinaId() {
		return this.finaId;
	}

	public void setFinaId(String finaId) {
		this.finaId = finaId;
	}

	public String getLoanOrderId() {
		return this.loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinaPersonnel() {
		return this.finaPersonnel;
	}

	public void setFinaPersonnel(String finaPersonnel) {
		this.finaPersonnel = finaPersonnel;
	}

	public String getFinaPersSugg() {
		return this.finaPersSugg;
	}

	public void setFinaPersSugg(String finaPersSugg) {
		this.finaPersSugg = finaPersSugg;
	}

	public Date getFinaDate() {
		return this.finaDate;
	}

	public void setFinaDate(Date finaDate) {
		this.finaDate = finaDate;
	}

	public BigDecimal getContractLoanAmount() {
		return this.contractLoanAmount;
	}

	public void setContractLoanAmount(String contractLoanAmount) {
		if (StringUtils.isNotBlank(contractLoanAmount)) {
			this.contractLoanAmount = BigDecimal.valueOf(Double
					.valueOf(contractLoanAmount));
		}
	}

	public BigDecimal getActualLoanAmount() {
		return this.actualLoanAmount;
	}

	public void setActualLoanAmount(String actualLoanAmount) {
		if (StringUtils.isNotBlank(actualLoanAmount)) {
			this.actualLoanAmount = BigDecimal.valueOf(Double
					.valueOf(actualLoanAmount));
		}
	}

	public String getLoanPeriodType() {
		return this.loanPeriodType;
	}

	public void setLoanPeriodType(String loanPeriodType) {
		this.loanPeriodType = loanPeriodType;
	}

	public String getMonthServiceFeeRate() {
		return this.monthServiceFeeRate;
	}

	public void setMonthServiceFeeRate(String monthServiceFeeRate) {
		this.monthServiceFeeRate = monthServiceFeeRate;
	}

	public String getLoanInterestRate() {
		return this.loanInterestRate;
	}

	public void setLoanInterestRate(String loanInterestRate) {
		this.loanInterestRate = loanInterestRate;
	}

	public BigDecimal getVisitFee() {
		return this.visitFee;
	}

	public void setVisitFee(String visitFee) {
		if (StringUtils.isNotBlank(visitFee)) {
			this.visitFee = BigDecimal.valueOf(Double.parseDouble(visitFee));
		}
	}

	public BigDecimal getMonthRepay() {
		return this.monthRepay;
	}

	public void setMonthRepay(String monthRepay) {
		if (StringUtils.isNotBlank(monthRepay)) {
			this.monthRepay = BigDecimal
					.valueOf(Double.parseDouble(monthRepay));
		}
	}

}
