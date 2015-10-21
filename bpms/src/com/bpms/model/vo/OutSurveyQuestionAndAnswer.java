package com.bpms.model.vo;

import java.util.Date;

/**
 * 外访问题及回答实体模型
 * @author Sun
 *
 */
public class OutSurveyQuestionAndAnswer implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String answerId;
	private String outsurveyReportSummaryId;
	private String questionId;
	private String answer;
	private String loanOrderId;
	private String questionDesc;
	private String creater;
	private Date createTime;
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getOutsurveyReportSummaryId() {
		return outsurveyReportSummaryId;
	}
	public void setOutsurveyReportSummaryId(String outsurveyReportSummaryId) {
		this.outsurveyReportSummaryId = outsurveyReportSummaryId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getLoanOrderId() {
		return loanOrderId;
	}
	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
