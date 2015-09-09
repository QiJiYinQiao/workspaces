package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Date;

public class CompanyModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String comId;
	private String loanerId;
	private String name;
	private String address;
	private String corpNature;
	private String tele;
	private Date foundedTime;
	private Integer areaSize;
	private String businessScope;
	private BigDecimal regCapital;
	private Integer empAmount;
	private String creator;
	private Date createDate;
	private String regId;
	
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	private Integer dwProvince;//省
	private Integer dwCity;//市
	private Integer dwArea;//县
	private String  dwAddrDetails;//详细
	//是否被订单绑定标示，驳回修改的时候用
	private String stateChecked;
	
	//稽核信息
	private String auditId;//稽核信息记录ID
	private String auditItem;//核查项目(对象)
	private String phoneAuditRecord;// 电核内容记录
	private String webAuditRecord;//网核内容记录
	
	public String getComId() {
		return comId;
	}
	public void setComId(String comId) {
		this.comId = comId;
	}
	public String getLoanerId() {
		return loanerId;
	}
	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCorpNature() {
		return corpNature;
	}
	public void setCorpNature(String corpNature) {
		this.corpNature = corpNature;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public Date getFoundedTime() {
		return foundedTime;
	}
	public void setFoundedTime(Date foundedTime) {
		this.foundedTime = foundedTime;
	}
	public Integer getAreaSize() {
		return areaSize;
	}
	public void setAreaSize(Integer areaSize) {
		this.areaSize = areaSize;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public BigDecimal getRegCapital() {
		return regCapital;
	}
	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}
	public Integer getEmpAmount() {
		return empAmount;
	}
	public void setEmpAmount(Integer empAmount) {
		this.empAmount = empAmount;
	}
	public Integer getDwProvince() {
		return dwProvince;
	}
	public void setDwProvince(Integer dwProvince) {
		this.dwProvince = dwProvince;
	}
	public Integer getDwCity() {
		return dwCity;
	}
	public void setDwCity(Integer dwCity) {
		this.dwCity = dwCity;
	}
	public Integer getDwArea() {
		return dwArea;
	}
	public void setDwArea(Integer dwArea) {
		this.dwArea = dwArea;
	}
	public String getDwAddrDetails() {
		return dwAddrDetails;
	}
	public void setDwAddrDetails(String dwAddrDetails) {
		this.dwAddrDetails = dwAddrDetails;
	}
	public String getStateChecked() {
		return stateChecked;
	}
	public void setStateChecked(String stateChecked) {
		this.stateChecked = stateChecked;
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
	
	

}
