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
 * @author liuhh 外访核实表对应的实体类
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bp_outbound_verify")
public class OutboundVerify implements java.io.Serializable {

	private String verifyId;
	private String questionId;
	private String verifyContent;
	private String creater;
	private Date createTime;

	public OutboundVerify() {
	}

	public OutboundVerify(String verifyId, Date createTime) {
		this.verifyId = verifyId;
		this.createTime = createTime;
	}

	public OutboundVerify(String verifyId, String questionId,
			String verifyContent, String creater, Date createTime) {
		this.verifyId = verifyId;
		this.questionId = questionId;
		this.verifyContent = verifyContent;
		this.creater = creater;
		this.createTime = createTime;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "VERIFY_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getVerifyId() {
		return this.verifyId;
	}

	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}

	@Column(name = "QUESTION_ID", length = 40)
	public String getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	@Column(name = "VERIFY_CONTENT", length = 2000)
	public String getVerifyContent() {
		return this.verifyContent;
	}

	public void setVerifyContent(String verifyContent) {
		this.verifyContent = verifyContent;
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
