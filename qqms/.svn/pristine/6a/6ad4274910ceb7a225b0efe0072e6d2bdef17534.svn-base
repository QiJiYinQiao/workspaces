package com.qqms.model;

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
 * 稽核信息与附件关系实体类
 * @author PANCHUANHE
 * @date 2016/6/16
 */
@Entity
@Table(name = "t_bp_auditinforecord_and_attachment")
public class AuditinforecordAndAttachment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 稽核信息与附件关系ID
	 */
	private String auditAttId;
	/**
	 * 稽核信息记录ID
	 */
	private Attachment attachment;
	/**
	 * 附件ID
	 */
	private AuditInfoRecord auditInfoRecord;

	public AuditinforecordAndAttachment() {
	}

	public AuditinforecordAndAttachment(String auditAttId) {
		this.auditAttId = auditAttId;
	}

	public AuditinforecordAndAttachment(String auditAttId,
			Attachment attachment, AuditInfoRecord auditInfoRecord) {
		this.auditAttId = auditAttId;
		this.attachment = attachment;
		this.auditInfoRecord = auditInfoRecord;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "AUDIT_ATT_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getAuditAttId() {
		return this.auditAttId;
	}

	public void setAuditAttId(String auditAttId) {
		this.auditAttId = auditAttId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATT_ID")
	public Attachment getAttachment() {
		return this.attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUDIT_ID")
	public AuditInfoRecord getAuditInfoRecord() {
		return this.auditInfoRecord;
	}

	public void setAuditInfoRecord(AuditInfoRecord auditInfoRecord) {
		this.auditInfoRecord = auditInfoRecord;
	}

}
