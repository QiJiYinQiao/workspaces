package com.bpms.model;

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
 * @author liuhh 外访问题对应的实体类
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bp_firstaudit_questioncollect")
public class FirstauditQuestioncollect implements java.io.Serializable {

	private String questionId;
	private String loanOrderId;
	private String questionDesc;
	private String creater;
	private Date createTime;

	public FirstauditQuestioncollect() {
	}

	public FirstauditQuestioncollect(String questionId, Date createTime) {
		this.questionId = questionId;
		this.createTime = createTime;
	}

	public FirstauditQuestioncollect(String questionId, String loanOrderId,
			String questionDesc, String creater, Date createTime) {
		this.questionId = questionId;
		this.loanOrderId = loanOrderId;
		this.questionDesc = questionDesc;
		this.creater = creater;
		this.createTime = createTime;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "QUESTION_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Column(name = "LOAN_ORDER_ID", length = 40)
	public String getLoanOrderId() {
		return this.loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	@Column(name = "QUESTION_DESC", length = 300)
	public String getQuestionDesc() {
		return this.questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	@Column(name = "CREATER", length = 40)
	public String getCreater() {
		return this.creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
