package com.bpms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 稽核信息与附件关系实体类
 * 
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
	 * 附件ID
	 */
	private String attId;

	/**
	 * 稽核信息记录ID
	 */
	private String auditId;

	public AuditinforecordAndAttachment() {
	}

	public AuditinforecordAndAttachment(String auditAttId) {
		this.auditAttId = auditAttId;
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

	@Column(name = "ATT_ID", length = 40)
	public String getAttId() {
		return attId;
	}

	public void setAttId(String attId) {
		this.attId = attId;
	}

	@Column(name = "AUDIT_ID", length = 40)
	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

}
