package com.bpms.model;

import java.math.BigDecimal;
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
 * 共同借款人信息
 * 
 * @author liuhh
 *
 */
@Entity
@Table(name = "t_bp_loaner_joint")
public class LoanerJoint implements java.io.Serializable {

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

	public LoanerJoint() {
	}

	public LoanerJoint(String ljId) {
		this.ljId = ljId;
	}

	public LoanerJoint(String ljId, String loanOrderId, String name,
			String gender, String idNo, Integer age, String residence,
			String curAddr, String mobileTel, String familyTel,
			String fixedTel, String corpName, String corpAddr, String corpTel,
			Date foundedTime, BigDecimal areaSize, String corpNature,
			BigDecimal regCapital, Integer ljEmpAmt, String marriageType,
			String hasChild, String email, String qqNo) {
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
	}

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(name = "LJ_ID", insertable = true, updatable = true, nullable = false, length = 40, unique = true)
	public String getLjId() {
		return this.ljId;
	}

	public void setLjId(String ljId) {
		this.ljId = ljId;
	}

	@Column(name = "LOAN_ORDER_ID", length = 40)
	public String getLoanOrderId() {
		return this.loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GENDER", length = 1)
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	@Column(name = "RESIDENCE", length = 40)
	public String getResidence() {
		return this.residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	@Column(name = "CUR_ADDR", length = 40)
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

	@Column(name = "FAMILY_TEL", length = 20)
	public String getFamilyTel() {
		return this.familyTel;
	}

	public void setFamilyTel(String familyTel) {
		this.familyTel = familyTel;
	}

	@Column(name = "FIXED_TEL", length = 20)
	public String getFixedTel() {
		return this.fixedTel;
	}

	public void setFixedTel(String fixedTel) {
		this.fixedTel = fixedTel;
	}

	@Column(name = "CORP_NAME", length = 80)
	public String getCorpName() {
		return this.corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	@Column(name = "CORP_ADDR", length = 40)
	public String getCorpAddr() {
		return this.corpAddr;
	}

	public void setCorpAddr(String corpAddr) {
		this.corpAddr = corpAddr;
	}

	@Column(name = "CORP_TEL", length = 20)
	public String getCorpTel() {
		return this.corpTel;
	}

	public void setCorpTel(String corpTel) {
		this.corpTel = corpTel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FOUNDED_TIME", length = 10)
	public Date getFoundedTime() {
		return this.foundedTime;
	}

	public void setFoundedTime(Date foundedTime) {
		this.foundedTime = foundedTime;
	}

	@Column(name = "AREA_SIZE", precision = 8)
	public BigDecimal getAreaSize() {
		return this.areaSize;
	}

	public void setAreaSize(BigDecimal areaSize) {
		this.areaSize = areaSize;
	}

	@Column(name = "CORP_NATURE", length = 1)
	public String getCorpNature() {
		return this.corpNature;
	}

	public void setCorpNature(String corpNature) {
		this.corpNature = corpNature;
	}

	@Column(name = "REG_CAPITAL", precision = 15)
	public BigDecimal getRegCapital() {
		return this.regCapital;
	}

	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}

	@Column(name = "LJ_EMP_AMT")
	public Integer getLjEmpAmt() {
		return this.ljEmpAmt;
	}

	public void setLjEmpAmt(Integer ljEmpAmt) {
		this.ljEmpAmt = ljEmpAmt;
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

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "QQ_NO", length = 20)
	public String getQqNo() {
		return this.qqNo;
	}

	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}

}
