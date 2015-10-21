package com.bpms.model;

import java.math.BigDecimal;
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
 * 
 * @author liuhh 外访调查报告总结对应的实体类
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bp_outsurvey_report_summary")
public class OutsurveyReportSummary implements java.io.Serializable {

	private String outsurveyReportSummaryId;
	private String loanOrderId;
	private String doubtfulPoint;//调查中存在的疑问点
	private String customerAnaly;//客户优劣势分析 (包括但不限于企业的经营情况、企业背景、软信息、财务信息等)
	private BigDecimal suggestAmt;//建议放款额度 (包括风控措施并给出相应理由)
	private String suggestAmtReason;//风控措施并给出相应理由
	private String reason;//初审问题及答疑
	private String surveyer;//调查人员
	private Date reportingTime;//报告时间

	public OutsurveyReportSummary() {
	}

	public OutsurveyReportSummary(String outsurveyReportSummaryId,
			Date reportingTime) {
		this.outsurveyReportSummaryId = outsurveyReportSummaryId;
		this.reportingTime = reportingTime;
	}

	public OutsurveyReportSummary(String outsurveyReportSummaryId,
			String loanOrderId, String doubtfulPoint, String customerAnaly,
			BigDecimal suggestAmt, String suggestAmtReason, String reason,
			String surveyer, Date reportingTime) {
		super();
		this.outsurveyReportSummaryId = outsurveyReportSummaryId;
		this.loanOrderId = loanOrderId;
		this.doubtfulPoint = doubtfulPoint;
		this.customerAnaly = customerAnaly;
		this.suggestAmt = suggestAmt;
		this.suggestAmtReason = suggestAmtReason;
		this.reason = reason;
		this.surveyer = surveyer;
		this.reportingTime = reportingTime;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "OUTSURVEY_REPORT_SUMMARY_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getOutsurveyReportSummaryId() {
		return this.outsurveyReportSummaryId;
	}

	public void setOutsurveyReportSummaryId(String outsurveyReportSummaryId) {
		this.outsurveyReportSummaryId = outsurveyReportSummaryId;
	}

	@Column(name = "LOAN_ORDER_ID", length = 40)
	public String getLoanOrderId() {
		return this.loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	@Column(name = "DOUBTFUL_POINT", length = 2500)
	public String getDoubtfulPoint() {
		return this.doubtfulPoint;
	}

	public void setDoubtfulPoint(String doubtfulPoint) {
		this.doubtfulPoint = doubtfulPoint;
	}

	@Column(name = "CUSTOMER_ANALY", length = 2500)
	public String getCustomerAnaly() {
		return this.customerAnaly;
	}

	public void setCustomerAnaly(String customerAnaly) {
		this.customerAnaly = customerAnaly;
	}

	@Column(name = "SUGGEST_AMT", precision = 20, scale = 5)
	public BigDecimal getSuggestAmt() {
		return this.suggestAmt;
	}

	public void setSuggestAmt(BigDecimal suggestAmt) {
		this.suggestAmt = suggestAmt;
	}

	@Column(name = "REASON", length = 2500)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "SURVEYER", length = 40)
	public String getSurveyer() {
		return this.surveyer;
	}

	public void setSurveyer(String surveyer) {
		this.surveyer = surveyer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REPORTING_TIME", nullable = false, length = 19)
	public Date getReportingTime() {
		return this.reportingTime;
	}

	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
	}

	@Column(name = "SUGGESTAMT_REASON", length = 2500)
	public String getSuggestAmtReason() {
		return suggestAmtReason;
	}

	public void setSuggestAmtReason(String suggestAmtReason) {
		this.suggestAmtReason = suggestAmtReason;
	}

	
}
