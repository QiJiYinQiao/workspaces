package com.bpms.model.vo;

public class AuditInfoRecordModel implements java.io.Serializable{
	private static final long serialVersionUID = -8665350958235903994L;
	/**
	 * 稽核信息记录ID
	 */
	private String auditId;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 核查项目(对象)
	 */
	private String auditItem;
	/**
	 * 核查项目名称
	 */
	private String auditItemName;
	/**
	 * 电核内容记录
	 */
	private String phoneAuditRecord;
	/**
	 * 网核内容记录
	 */
	private String webAuditRecord;
	/**
	 * 联系方式
	 */
	private String contactMethod;
	/**
	 * 114查询
	 */
	private String query114;
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuditItem() {
		return auditItem;
	}
	public void setAuditItem(String auditItem) {
		this.auditItem = auditItem;
	}
	public String getAuditItemName() {
		return auditItemName;
	}
	public void setAuditItemName(String auditItemName) {
		this.auditItemName = auditItemName;
	}
	public String getPhoneAuditRecord() {
		return phoneAuditRecord;
	}
	public void setPhoneAuditRecord(String phoneAuditRecord) {
		this.phoneAuditRecord = phoneAuditRecord;
	}
	public String getWebAuditRecord() {
		return webAuditRecord;
	}
	public void setWebAuditRecord(String webAuditRecord) {
		this.webAuditRecord = webAuditRecord;
	}
	public String getContactMethod() {
		return contactMethod;
	}
	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}
	public String getQuery114() {
		return query114;
	}
	public void setQuery114(String query114) {
		this.query114 = query114;
	}
	
	
}
