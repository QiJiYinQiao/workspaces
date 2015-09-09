package com.bpms.model.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 共同借款人信息
 * 
 * @author liuhh
 *
 */
public class LoanerJointModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String ljId;
	private String loanOrderId;
	private String name;
	private String gender;
	private String idNo;
	private Integer age;
	private String residence;
	private String curAddr;
	private String mobileTel;
	private String familyTel;
	private String fixedTel;
	private String corpName;
	private String corpAddr;
	private String corpTel;
	private Date foundedTime;
	private BigDecimal areaSize;
	private String corpNature;
	private BigDecimal regCapital;
	private Integer ljEmpAmt;
	private String marriageType;
	private String hasChild;
	private String email;
	private String qqNo;

	// 户籍地址
	private String residenceAddressId;
	private String residenceAddressType;;
	private Integer residenceProvinceId;
	private Integer residenceCityId;
	private Integer residenceAreaId;
	private String residenceAddrDetails;

	// 现住地址
	private String curAddressId;
	private String curAddressType;
	private Integer curProvinceId;
	private Integer curCityId;
	private Integer curAreaId;
	private String curAddrDetails;

	// 单位地址
	private String corpAddressId;
	private String corpAddressType;
	private Integer corpProvinceId;
	private Integer corpCityId;
	private Integer corpAreaId;
	private String corpAddrDetails;

	public LoanerJointModel() {
	}

	public LoanerJointModel(String ljId) {
		this.ljId = ljId;
	}

	public String getLjId() {
		return ljId;
	}

	public void setLjId(String ljId) {
		this.ljId = ljId;
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getCurAddr() {
		return curAddr;
	}

	public void setCurAddr(String curAddr) {
		this.curAddr = curAddr;
	}

	public String getMobileTel() {
		return mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	public String getFamilyTel() {
		return familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}

	public String getFixedTel() {
		return fixedTel;
	}

	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getCorpAddr() {
		return corpAddr;
	}

	public void setCorpAddr(String corpAddr) {
		this.corpAddr = corpAddr;
	}

	public String getCorpTel() {
		return corpTel;
	}

	public void setCorpTel(String corpTel) {
		this.corpTel = corpTel;
	}

	public Date getFoundedTime() {
		return foundedTime;
	}

	public void setFoundedTime(Date foundedTime) {
		this.foundedTime = foundedTime;
	}

	public BigDecimal getAreaSize() {
		return areaSize;
	}

	public void setAreaSize(BigDecimal areaSize) {
		this.areaSize = areaSize;
	}

	public String getCorpNature() {
		return corpNature;
	}

	public void setCorpNature(String corpNature) {
		this.corpNature = corpNature;
	}

	public BigDecimal getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}

	public Integer getLjEmpAmt() {
		return ljEmpAmt;
	}

	public void setLjEmpAmt(Integer ljEmpAmt) {
		this.ljEmpAmt = ljEmpAmt;
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

	public Integer getResidenceProvinceId() {
		return residenceProvinceId;
	}

	public void setResidenceProvinceId(Integer residenceProvinceId) {
		this.residenceProvinceId = residenceProvinceId;
	}

	public Integer getResidenceCityId() {
		return residenceCityId;
	}

	public void setResidenceCityId(Integer residenceCityId) {
		this.residenceCityId = residenceCityId;
	}

	public Integer getResidenceAreaId() {
		return residenceAreaId;
	}

	public void setResidenceAreaId(Integer residenceAreaId) {
		this.residenceAreaId = residenceAreaId;
	}

	public String getResidenceAddrDetails() {
		return residenceAddrDetails;
	}

	public void setResidenceAddrDetails(String residenceAddrDetails) {
		this.residenceAddrDetails = residenceAddrDetails;
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

	public Integer getCorpProvinceId() {
		return corpProvinceId;
	}

	public void setCorpProvinceId(Integer corpProvinceId) {
		this.corpProvinceId = corpProvinceId;
	}

	public Integer getCorpCityId() {
		return corpCityId;
	}

	public void setCorpCityId(Integer corpCityId) {
		this.corpCityId = corpCityId;
	}

	public Integer getCorpAreaId() {
		return corpAreaId;
	}

	public void setCorpAreaId(Integer corpAreaId) {
		this.corpAreaId = corpAreaId;
	}

	public String getCorpAddrDetails() {
		return corpAddrDetails;
	}

	public void setCorpAddrDetails(String corpAddrDetails) {
		this.corpAddrDetails = corpAddrDetails;
	}

	public String getResidenceAddressId() {
		return residenceAddressId;
	}

	public void setResidenceAddressId(String residenceAddressId) {
		this.residenceAddressId = residenceAddressId;
	}

	public String getResidenceAddressType() {
		return residenceAddressType;
	}

	public void setResidenceAddressType(String residenceAddressType) {
		this.residenceAddressType = residenceAddressType;
	}

	public String getCurAddressId() {
		return curAddressId;
	}

	public void setCurAddressId(String curAddressId) {
		this.curAddressId = curAddressId;
	}

	public String getCurAddressType() {
		return curAddressType;
	}

	public void setCurAddressType(String curAddressType) {
		this.curAddressType = curAddressType;
	}

	public String getCorpAddressId() {
		return corpAddressId;
	}

	public void setCorpAddressId(String corpAddressId) {
		this.corpAddressId = corpAddressId;
	}

	public String getCorpAddressType() {
		return corpAddressType;
	}

	public void setCorpAddressType(String corpAddressType) {
		this.corpAddressType = corpAddressType;
	}

	public LoanerJointModel(String ljId, String loanOrderId, String name,
			String gender, String idNo, Integer age, String residence,
			String curAddr, String mobileTel, String familyTel,
			String fixedTel, String corpName, String corpAddr, String corpTel,
			Date foundedTime, BigDecimal areaSize, String corpNature,
			BigDecimal regCapital, Integer ljEmpAmt, String marriageType,
			String hasChild, String email, String qqNo,
			String residenceAddressId, String residenceAddressType,
			Integer residenceProvinceId, Integer residenceCityId,
			Integer residenceAreaId, String residenceAddrDetails,
			String curAddressId, String curAddressType, Integer curProvinceId,
			Integer curCityId, Integer curAreaId, String curAddrDetails,
			String corpAddressId, String corpAddressType,
			Integer corpProvinceId, Integer corpCityId, Integer corpAreaId,
			String corpAddrDetails) {
		super();
		this.ljId = ljId;
		this.loanOrderId = loanOrderId;
		this.name = name;
		this.gender = gender;
		this.idNo = idNo;
		this.age = age;
		this.residence = residence;
		this.curAddr = curAddr;
		this.mobileTel = mobileTel;
		this.familyTel = familyTel;
		this.fixedTel = fixedTel;
		this.corpName = corpName;
		this.corpAddr = corpAddr;
		this.corpTel = corpTel;
		this.foundedTime = foundedTime;
		this.areaSize = areaSize;
		this.corpNature = corpNature;
		this.regCapital = regCapital;
		this.ljEmpAmt = ljEmpAmt;
		this.marriageType = marriageType;
		this.hasChild = hasChild;
		this.email = email;
		this.qqNo = qqNo;
		this.residenceAddressId = residenceAddressId;
		this.residenceAddressType = residenceAddressType;
		this.residenceProvinceId = residenceProvinceId;
		this.residenceCityId = residenceCityId;
		this.residenceAreaId = residenceAreaId;
		this.residenceAddrDetails = residenceAddrDetails;
		this.curAddressId = curAddressId;
		this.curAddressType = curAddressType;
		this.curProvinceId = curProvinceId;
		this.curCityId = curCityId;
		this.curAreaId = curAreaId;
		this.curAddrDetails = curAddrDetails;
		this.corpAddressId = corpAddressId;
		this.corpAddressType = corpAddressType;
		this.corpProvinceId = corpProvinceId;
		this.corpCityId = corpCityId;
		this.corpAreaId = corpAreaId;
		this.corpAddrDetails = corpAddrDetails;
	}

}
