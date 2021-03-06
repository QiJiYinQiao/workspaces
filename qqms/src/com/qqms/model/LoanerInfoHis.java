package com.qqms.model;

// Generated 2015-6-24 10:29:40 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

/**
 * LoanerInfoHis generated by hbm2java
 */
@Entity
@Table(name = "t_bp_loaner_info_his")
public class LoanerInfoHis implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String loanerHiId;
	private Loaner loaner;
	private String loanerStatusType;
	private String name;
	private String genderType;
	private String idNo;
	private Integer age;
	private String hukouAddr;
	private String curAddr;
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
	private String bankNoFirst;
	private String bankNoSecond;
	private Date createDate;
	private String changeContents;
	private Date changeTiem;
	private String changePeople;

	public LoanerInfoHis() {
	}

	public LoanerInfoHis(String loanerHiId, String changeContents,
			Date changeTiem, String changePeople) {
		this.loanerHiId = loanerHiId;
		this.changeContents = changeContents;
		this.changeTiem = changeTiem;
		this.changePeople = changePeople;
	}

	public LoanerInfoHis(String loanerHiId, Loaner Loaner,
			String loanerStatusType, String name, String genderType,
			String idNo, Integer age, String hukouAddr, String curAddr,
			String mobileTel, String fixedTel, String marriageType,
			String hasChild, String email, String qqNo,
			BigDecimal annualSalary, String incomeSrc, String mortgageStatus,
			BigDecimal houseInstallPay, BigDecimal rent, String bankNoFirst,
			String bankNoSecond, Date createDate, String changeContents,
			Date changeTiem, String changePeople) {
		this.loanerHiId = loanerHiId;
		this.loaner = Loaner;
		this.loanerStatusType = loanerStatusType;
		this.name = name;
		this.genderType = genderType;
		this.idNo = idNo;
		this.age = age;
		this.hukouAddr = hukouAddr;
		this.curAddr = curAddr;
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
		this.bankNoFirst = bankNoFirst;
		this.bankNoSecond = bankNoSecond;
		this.createDate = createDate;
		this.changeContents = changeContents;
		this.changeTiem = changeTiem;
		this.changePeople = changePeople;
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "LOANER_HI_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getLoanerHiId() {
		return this.loanerHiId;
	}

	public void setLoanerHiId(String loanerHiId) {
		this.loanerHiId = loanerHiId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LOANER_ID")
	public Loaner getLoaner() {
		return this.loaner;
	}

	public void setLoaner(Loaner loaner) {
		this.loaner = loaner;
	}

	@Column(name = "LOANER_STATUS_TYPE", length = 1)
	public String getLoanerStatusType() {
		return this.loanerStatusType;
	}

	public void setLoanerStatusType(String loanerStatusType) {
		this.loanerStatusType = loanerStatusType;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER_TYPE", length = 1)
	public String getGenderType() {
		return this.genderType;
	}

	public void setGenderType(String genderType) {
		this.genderType = genderType;
	}

	@Column(name = "ID_NO", length = 32)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "HUKOU_ADDR", length = 500)
	public String getHukouAddr() {
		return this.hukouAddr;
	}

	public void setHukouAddr(String hukouAddr) {
		this.hukouAddr = hukouAddr;
	}

	@Column(name = "CUR_ADDR", length = 500)
	public String getCurAddr() {
		return this.curAddr;
	}

	public void setCurAddr(String curAddr) {
		this.curAddr = curAddr;
	}

	@Column(name = "MOBILE_TEL", length = 20)
	public String getMobileTel() {
		return this.mobileTel;
	}

	public void setMobileTel(String mobileTel) {
		this.mobileTel = mobileTel;
	}

	@Column(name = "FIXED_TEL", length = 20)
	public String getFixedTel() {
		return this.fixedTel;
	}

	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}

	@Column(name = "MARRIAGE_TYPE", length = 1)
	public String getMarriageType() {
		return this.marriageType;
	}

	public void setMarriageType(String marriageType) {
		this.marriageType = marriageType;
	}

	@Column(name = "HAS_CHILD", length = 1)
	public String getHasChild() {
		return this.hasChild;
	}

	public void setHasChild(String hasChild) {
		this.hasChild = hasChild;
	}

	@Column(name = "EMAIL", length = 300)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "QQ_NO", length = 30)
	public String getQqNo() {
		return this.qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

	@Column(name = "ANNUAL_SALARY", precision = 20, scale = 5)
	public BigDecimal getAnnualSalary() {
		return this.annualSalary;
	}

	public void setAnnualSalary(BigDecimal annualSalary) {
		this.annualSalary = annualSalary;
	}

	@Column(name = "INCOME_SRC", length = 200)
	public String getIncomeSrc() {
		return this.incomeSrc;
	}

	public void setIncomeSrc(String incomeSrc) {
		this.incomeSrc = incomeSrc;
	}

	@Column(name = "MORTGAGE_STATUS", length = 1)
	public String getMortgageStatus() {
		return this.mortgageStatus;
	}

	public void setMortgageStatus(String mortgageStatus) {
		this.mortgageStatus = mortgageStatus;
	}

	@Column(name = "HOUSE_INSTALL_PAY", precision = 20, scale = 5)
	public BigDecimal getHouseInstallPay() {
		return this.houseInstallPay;
	}

	public void setHouseInstallPay(BigDecimal houseInstallPay) {
		this.houseInstallPay = houseInstallPay;
	}

	@Column(name = "RENT", precision = 20, scale = 5)
	public BigDecimal getRent() {
		return this.rent;
	}

	public void setRent(BigDecimal rent) {
		this.rent = rent;
	}

	@Column(name = "BANK_NO_FIRST", length = 50)
	public String getBankNoFirst() {
		return this.bankNoFirst;
	}

	public void setBankNoFirst(String bankNoFirst) {
		this.bankNoFirst = bankNoFirst;
	}

	@Column(name = "BANK_NO_SECOND", length = 50)
	public String getBankNoSecond() {
		return this.bankNoSecond;
	}

	public void setBankNoSecond(String bankNoSecond) {
		this.bankNoSecond = bankNoSecond;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE", length = 19)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CHANGE_CONTENTS", nullable = false)
	public String getChangeContents() {
		return this.changeContents;
	}

	public void setChangeContents(String changeContents) {
		this.changeContents = changeContents;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHANGE_TIEM", nullable = false, length = 19)
	public Date getChangeTiem() {
		return this.changeTiem;
	}

	public void setChangeTiem(Date changeTiem) {
		this.changeTiem = changeTiem;
	}

	@Column(name = "CHANGE_PEOPLE", nullable = false, length = 40)
	public String getChangePeople() {
		return this.changePeople;
	}

	public void setChangePeople(String changePeople) {
		this.changePeople = changePeople;
	}

}
