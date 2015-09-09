package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 紧急联系人的vo
 * @author panchuanhe
 * 2015/6/30
 */
public class ContactsModel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String contactId;
	private String cusId;
	private String cusType;
	private String contactStatus;
	private String chName;
	private String enName;
	private String genderType;
	private Integer age;
	private String jobTitle;
	private BigDecimal annualSalary;
	private Integer yearsOfWork;
	private String compTel;
	private String compAddr;
	private String currAddr;
	private Date birthday;
	private String idType;
	private String idNo;
	private String tel;
	private String fixedTel;
	private String email;
	private String relationship;
	private String zip;
	private String workplace;
	private String creator;
	private Date createDate;
	private String relationshipName;

	/*** 单位地址 ***/
	private Integer compProvince;// 省
	private Integer compCity;// 市
	private Integer compArea;// 县
	private String compAddrDetails;// 详细

	/*** 家庭地址 ***/
	private Integer familyProvince;// 省
	private Integer familyCity;// 市
	private Integer familyArea;// 县
	private String familyAddrDetails;// 详细
	
	//该联系人是否与订单绑定
	private String checkedLinkMan;
	
	//稽核信息
	private String auditId;//稽核信息记录ID
	private String auditItem;//核查项目(对象)
	private String phoneAuditRecord;// 电核内容记录
	private String webAuditRecord;//网核内容记录
	private String query114;//114查询
	
	
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getCusId() {
		return cusId;
	}
	public void setCusId(String cusId) {
		this.cusId = cusId;
	}
	public String getCusType() {
		return cusType;
	}
	public void setCusType(String cusType) {
		this.cusType = cusType;
	}
	public String getContactStatus() {
		return contactStatus;
	}
	public void setContactStatus(String contactStatus) {
		this.contactStatus = contactStatus;
	}
	public String getChName() {
		return chName;
	}
	public void setChName(String chName) {
		this.chName = chName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getGenderType() {
		return genderType;
	}
	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}
	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}
	public Integer getYearsOfWork() {
		return yearsOfWork;
	}
	public void setYearsOfWork(Integer yearsOfWork) {
		this.yearsOfWork = yearsOfWork;
	}
	public String getCompTel() {
		return compTel;
	}
	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}
	public String getCompAddr() {
		return compAddr;
	}
	public void setCompAddr(String compAddr) {
		this.compAddr = compAddr;
	}
	public String getCurrAddr() {
		return currAddr;
	}
	public void setCurrAddr(String currAddr) {
		this.currAddr = currAddr;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFixedTel() {
		return fixedTel;
	}
	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getCompProvince() {
		return compProvince;
	}
	public void setCompProvince(Integer compProvince) {
		this.compProvince = compProvince;
	}
	public Integer getCompCity() {
		return compCity;
	}
	public void setCompCity(Integer compCity) {
		this.compCity = compCity;
	}
	public Integer getCompArea() {
		return compArea;
	}
	public void setCompArea(Integer compArea) {
		this.compArea = compArea;
	}
	public String getCompAddrDetails() {
		return compAddrDetails;
	}
	public void setCompAddrDetails(String compAddrDetails) {
		this.compAddrDetails = compAddrDetails;
	}
	public Integer getFamilyProvince() {
		return familyProvince;
	}
	public void setFamilyProvince(Integer familyProvince) {
		this.familyProvince = familyProvince;
	}
	public Integer getFamilyCity() {
		return familyCity;
	}
	public void setFamilyCity(Integer familyCity) {
		this.familyCity = familyCity;
	}
	public Integer getFamilyArea() {
		return familyArea;
	}
	public void setFamilyArea(Integer familyArea) {
		this.familyArea = familyArea;
	}
	public String getFamilyAddrDetails() {
		return familyAddrDetails;
	}
	public void setFamilyAddrDetails(String familyAddrDetails) {
		this.familyAddrDetails = familyAddrDetails;
	}
	public String getCheckedLinkMan() {
		return checkedLinkMan;
	}
	public void setCheckedLinkMan(String checkedLinkMan) {
		this.checkedLinkMan = checkedLinkMan;
	}
	public String getRelationshipName() {
		return relationshipName;
	}
	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}
	public String getAuditId() {
		return auditId;
	}
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}
	public String getAuditItem() {
		return auditItem;
	}
	public void setAuditItem(String auditItem) {
		this.auditItem = auditItem;
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
	public String getQuery114() {
		return query114;
	}
	public void setQuery114(String query114) {
		this.query114 = query114;
	}
	
}
