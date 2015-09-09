package com.bpms.model.vo;

// Generated 2015-6-16 10:13:19 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

public class LoanerModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String loanerId;
	private String name;
	private String genderType;
	private String idNo;
	private Integer age;
	private String familyTel;
	private String loanOrderId;
	private String hukouAddr;
	private String curAddr;
	private boolean state;
	private String salesMan;

	// 户籍地址
	private String hukouAddrId;
	private Integer hukouProvinceId;
	private Integer hukouCityId;
	private Integer hukouAreaId;
	private String hukouAddrDetails;
	private String hukouAddrType;
	private String hukouHostType;
	private String hukouHostId;

	// 现住地址
	private String curAddrId;
	private Integer curProvinceId;
	private Integer curCityId;
	private Integer curAreaId;
	private String curAddrDetails;
	private String curAddrType;
	private String curHostType;
	private String curHostId;

	private String mobileTel;
	private String fixedTel;
	private String marriageType;
	private String hasChild;
	private String email;
	private String qqNo;
	private BigDecimal annualSalary;
	private String incomeSrc;
	private String mortgageStatus;
	private BigDecimal houseInstallPay;
	private BigDecimal rent;
	private Date createDate;
	
	private String curAddress;//完整现居地址
	private String hukouAddress;//完整户口地址
	private String genderTypeName;//性别
	private String hasChildName;//是否有子女
	private String marriageTypeName;//是否已婚
	private String mortgageStatusName;//居住状态
	
	public LoanerModel() {

	}

	public LoanerModel(String loanerId, String name, String genderType,
			String idNo, Integer age, String hukouAddr, String hukouAddrId,
			Integer hukouProvinceId, Integer hukouCityId, Integer hukouAreaId,
			String hukouAddrDetails, String hukouAddrType,
			String hukouHostType, String hukouHostId, String curAddr,
			String curAddrId, Integer curProvinceId, Integer curCityId,
			Integer curAreaId, String curAddrDetails, String curAddrType,
			String curHostType, String curHostId, String mobileTel,
			String fixedTel, String marriageType, String hasChild,
			String email, String qqNo, BigDecimal annualSalary,
			String incomeSrc, String mortgageStatus,
			BigDecimal houseInstallPay, BigDecimal rent, Date createDate) {
		super();
		this.loanerId = loanerId;
		this.name = name;
		this.genderType = genderType;
		this.idNo = idNo;
		this.age = age;
		this.hukouAddr = hukouAddr;
		this.hukouAddrId = hukouAddrId;
		this.hukouProvinceId = hukouProvinceId;
		this.hukouCityId = hukouCityId;
		this.hukouAreaId = hukouAreaId;
		this.hukouAddrDetails = hukouAddrDetails;
		this.hukouAddrType = hukouAddrType;
		this.hukouHostType = hukouHostType;
		this.hukouHostId = hukouHostId;
		this.curAddr = curAddr;
		this.curAddrId = curAddrId;
		this.curProvinceId = curProvinceId;
		this.curCityId = curCityId;
		this.curAreaId = curAreaId;
		this.curAddrDetails = curAddrDetails;
		this.curAddrType = curAddrType;
		this.curHostType = curHostType;
		this.curHostId = curHostId;
		this.mobileTel = mobileTel;
		this.fixedTel = fixedTel;
		this.marriageType = marriageType;
		this.hasChild = hasChild;
		this.email = email;
		this.qqNo = qqNo;
		this.annualSalary = annualSalary;
		this.incomeSrc = incomeSrc;
		this.mortgageStatus = mortgageStatus;
		this.houseInstallPay = houseInstallPay;
		this.rent = rent;
		this.createDate = createDate;
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

	public String getGenderType() {
		return genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getHukouAddr() {
		return hukouAddr;
	}

	public void setHukouAddr(String hukouAddr) {
		this.hukouAddr = hukouAddr;
	}

	public String getHukouAddrId() {
		return hukouAddrId;
	}

	public void setHukouAddrId(String hukouAddrId) {
		this.hukouAddrId = hukouAddrId;
	}

	public Integer getHukouProvinceId() {
		return hukouProvinceId;
	}

	public void setHukouProvinceId(Integer hukouProvinceId) {
		this.hukouProvinceId = hukouProvinceId;
	}

	public Integer getHukouCityId() {
		return hukouCityId;
	}

	public void setHukouCityId(Integer hukouCityId) {
		this.hukouCityId = hukouCityId;
	}

	public Integer getHukouAreaId() {
		return hukouAreaId;
	}

	public void setHukouAreaId(Integer hukouAreaId) {
		this.hukouAreaId = hukouAreaId;
	}

	public String getHukouAddrDetails() {
		return hukouAddrDetails;
	}

	public void setHukouAddrDetails(String hukouAddrDetails) {
		this.hukouAddrDetails = hukouAddrDetails;
	}

	public String getHukouAddrType() {
		return hukouAddrType;
	}

	public void setHukouAddrType(String hukouAddrType) {
		this.hukouAddrType = hukouAddrType;
	}

	public String getHukouHostType() {
		return hukouHostType;
	}

	public void setHukouHostType(String hukouHostType) {
		this.hukouHostType = hukouHostType;
	}

	public String getHukouHostId() {
		return hukouHostId;
	}

	public void setHukouHostId(String hukouHostId) {
		this.hukouHostId = hukouHostId;
	}

	public String getCurAddr() {
		return curAddr;
	}

	public void setCurAddr(String curAddr) {
		this.curAddr = curAddr;
	}

	public String getCurAddrId() {
		return curAddrId;
	}

	public void setCurAddrId(String curAddrId) {
		this.curAddrId = curAddrId;
	}

	public Integer getCurProvinceId() {
		return curProvinceId;
	}

	public void setCurProvinceId(Integer curProvinceId) {
		this.curProvinceId = curProvinceId;
	}

	public Integer getCurCityId() {
		return curCityId;
	}

	public void setCurCityId(Integer curCityId) {
		this.curCityId = curCityId;
	}

	public Integer getCurAreaId() {
		return curAreaId;
	}

	public void setCurAreaId(Integer curAreaId) {
		this.curAreaId = curAreaId;
	}

	public String getCurAddrDetails() {
		return curAddrDetails;
	}

	public void setCurAddrDetails(String curAddrDetails) {
		this.curAddrDetails = curAddrDetails;
	}

	public String getCurAddrType() {
		return curAddrType;
	}

	public void setCurAddrType(String curAddrType) {
		this.curAddrType = curAddrType;
	}

	public String getCurHostType() {
		return curHostType;
	}

	public void setCurHostType(String curHostType) {
		this.curHostType = curHostType;
	}

	public String getCurHostId() {
		return curHostId;
	}

	public void setCurHostId(String curHostId) {
		this.curHostId = curHostId;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getFixedTel() {
		return fixedTel;
	}

	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}

	public String getMarriageType() {
		return marriageType;
	}

	public void setMarriageType(String marriageType) {
		this.marriageType = marriageType;
	}

	public String getHasChild() {
		return hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQqNo() {
		return qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	public BigDecimal getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}

	public String getIncomeSrc() {
		return incomeSrc;
	}

	public void setIncomeSrc(String incomeSrc) {
		this.incomeSrc = incomeSrc;
	}

	public String getMortgageStatus() {
		return mortgageStatus;
	}

	public void setMortgageStatus(String mortgageStatus) {
		this.mortgageStatus = mortgageStatus;
	}

	public BigDecimal getHouseInstallPay() {
		return houseInstallPay;
	}

	public void setHouseInstallPay(BigDecimal houseInstallPay) {
		this.houseInstallPay = houseInstallPay;
	}

	public BigDecimal getRent() {
		return rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFamilyTel() {
		return familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}
	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public String getCurAddress() {
		return curAddress;
	}

	public void setCurAddress(String curAddress) {
		this.curAddress = curAddress;
	}

	public String getHukouAddress() {
		return hukouAddress;
	}

	public void setHukouAddress(String hukouAddress) {
		this.hukouAddress = hukouAddress;
	}

	public String getGenderTypeName() {
		return genderTypeName;
	}

	public void setGenderTypeName(String genderTypeName) {
		this.genderTypeName = genderTypeName;
	}

	public String getHasChildName() {
		return hasChildName;
	}

	public void setHasChildName(String hasChildName) {
		this.hasChildName = hasChildName;
	}

	public String getMarriageTypeName() {
		return marriageTypeName;
	}

	public void setMarriageTypeName(String marriageTypeName) {
		this.marriageTypeName = marriageTypeName;
	}

	public String getMortgageStatusName() {
		return mortgageStatusName;
	}

	public void setMortgageStatusName(String mortgageStatusName) {
		this.mortgageStatusName = mortgageStatusName;
	}
	
}
