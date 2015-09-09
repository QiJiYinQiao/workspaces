package com.qqms.model;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 附件表
 * 
 * @author liuhh
 * @date 2015/6/16
 */
@Entity
@Table(name = "t_bp_attachment")
public class Attachment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private File file;
	private String fileFileName;
	private String fileFileContentType;
	private String fileName;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileFileContentType() {
		return fileFileContentType;
	}

	public void setFileFileContentType(String fileFileContentType) {
		this.fileFileContentType = fileFileContentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 附件ID
	 */
	private String attId;
	/**
	 * 订单ID
	 */
	private String orderId;
	/**
	 * 订单类型
	 */
	private String orderType;
	/**
	 * 附件名称
	 */
	private String attName;
	/**
	 * 附件类型
	 */
	private String attType;
	/**
	 * 附件数据
	 */
	private byte[] attData;
	/**
	 * 附件描述
	 */
	private String attDesc;
	/**
	 * 附件创建人
	 */
	private String creator;
	/**
	 * 附件创建时间
	 */
	private Date createDate;

	private Set<AuditinforecordAndAttachment> auditinforecordAndAttachments = new HashSet<AuditinforecordAndAttachment>(
			0);

	public Attachment() {
	}

	public Attachment(String attId) {
		this.attId = attId;
	}

	public Attachment(String attId, String orderId, String orderType,
			String attName, String attType, byte[] attData, String attDesc,
			String creator, Date createDate,
			Set<AuditinforecordAndAttachment> auditinforecordAndAttachments) {
		this.attId = attId;
		this.orderId = orderId;
		this.orderType = orderType;
		this.attName = attName;
		this.attType = attType;
		this.attData = attData;
		this.attDesc = attDesc;
		this.creator = creator;
		this.createDate = createDate;
		this.auditinforecordAndAttachments = auditinforecordAndAttachments;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "ATT_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getAttId() {
		return this.attId;
	}

	public void setAttId(String attId) {
		this.attId = attId;
	}

	@Column(name = "ORDER_ID", length = 40)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "ORDER_TYPE", length = 30)
	public String getOrderType() {
		return this.orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Column(name = "ATT_NAME", length = 200)
	public String getAttName() {
		return this.attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	@Column(name = "ATT_TYPE", length = 200)
	public String getAttType() {
		return this.attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	@Column(name = "ATT_DATA", length = 65535)
	public byte[] getAttData() {
		return this.attData;
	}

	public void setAttData(byte[] attData) {
		this.attData = attData;
	}

	@Column(name = "ATT_DESC", length = 300)
	public String getAttDesc() {
		return this.attDesc;
	}

	public void setAttDesc(String attDesc) {
		this.attDesc = attDesc;
	}

	@Column(name = "CREATOR", length = 40)
	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attachment")
	@JSONField(serialize = false)
	public Set<AuditinforecordAndAttachment> getAuditinforecordAndAttachments() {
		return this.auditinforecordAndAttachments;
	}

	public void setAuditinforecordAndAttachments(
			Set<AuditinforecordAndAttachment> auditinforecordAndAttachments) {
		this.auditinforecordAndAttachments = auditinforecordAndAttachments;
	}

}
