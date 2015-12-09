package com.oasys.model;

// Generated 2015-10-14 10:29:36 by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * TOaAdGoodsTackoverReg generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_goods_tackover_reg")
@SuppressWarnings("serial")
public class GoodsTackoverReg implements java.io.Serializable {

	private Integer gtrId;
	private Integer registrantNo;
	private String registerName;
	private Date regDatetime;
	private String regDatetimeBefore;
	private String regDatetimeAfter;
	private Integer turnoverUser;
	private String turnoverUserName;
	private Integer turnoverDept;
	private String turnoverDeptName;
	private Date turnoverDate;
	private String turnoverDateBefore;
	private String turnoverDateAfter;
	private Integer tackoverUser;
	private String tackoverUserName;
	private Integer tackoverDept;
	private String tackoverDeptName;
	private Date tackoverDate;
	private String tackoverDateBefore;
	private String tackoverDateAfter;
	private String remark;
	private List<GoodsTackoverStacfg> goodsTackoverStacfgList = new ArrayList<GoodsTackoverStacfg>();

	public GoodsTackoverReg() {
	}

	public GoodsTackoverReg(Integer registrantNo, Date regDatetime,
			Integer turnoverUser, Integer turnoverDept, Date turnoverDate,
			Integer tackoverUser, Integer tackoverDept, Date tackoverDate,
			String remark, List<GoodsTackoverStacfg> goodsTackoverStacfgList,
			String turnoverUserName,String turnoverDeptName,String tackoverUserName,
			String tackoverDeptName) {
		this.registrantNo = registrantNo;
		this.regDatetime = regDatetime;
		this.turnoverUser = turnoverUser;
		this.turnoverDept = turnoverDept;
		this.turnoverDate = turnoverDate;
		this.tackoverUser = tackoverUser;
		this.tackoverDept = tackoverDept;
		this.tackoverDate = tackoverDate;
		this.remark = remark;
		this.goodsTackoverStacfgList = goodsTackoverStacfgList;
		this.turnoverUserName=turnoverUserName;
		this.turnoverDeptName=turnoverDeptName;
		this.tackoverUserName=tackoverUserName;
		this.tackoverDeptName=tackoverDeptName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "GTR_ID", unique = true, nullable = false)
	public Integer getGtrId() {
		return this.gtrId;
	}

	public void setGtrId(Integer gtrId) {
		this.gtrId = gtrId;
	}

	@Column(name = "REGISTRANT_NO")
	public Integer getRegistrantNo() {
		return this.registrantNo;
	}

	public void setRegistrantNo(Integer registrantNo) {
		this.registrantNo = registrantNo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REG_DATETIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	public Date getRegDatetime() {
		return this.regDatetime;
	}

	public void setRegDatetime(Date regDatetime) {
		this.regDatetime = regDatetime;
	}

	@Column(name = "TURNOVER_USER")
	public Integer getTurnoverUser() {
		return this.turnoverUser;
	}

	public void setTurnoverUser(Integer turnoverUser) {
		this.turnoverUser = turnoverUser;
	}

	@Column(name = "TURNOVER_DEPT")
	public Integer getTurnoverDept() {
		return this.turnoverDept;
	}

	public void setTurnoverDept(Integer turnoverDept) {
		this.turnoverDept = turnoverDept;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TURNOVER_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getTurnoverDate() {
		return this.turnoverDate;
	}

	public void setTurnoverDate(Date turnoverDate) {
		this.turnoverDate = turnoverDate;
	}

	@Column(name = "TACKOVER_USER")
	public Integer getTackoverUser() {
		return this.tackoverUser;
	}

	public void setTackoverUser(Integer tackoverUser) {
		this.tackoverUser = tackoverUser;
	}

	@Column(name = "TACKOVER_DEPT")
	public Integer getTackoverDept() {
		return this.tackoverDept;
	}

	public void setTackoverDept(Integer tackoverDept) {
		this.tackoverDept = tackoverDept;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TACKOVER_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getTackoverDate() {
		return this.tackoverDate;
	}

	public void setTackoverDate(Date tackoverDate) {
		this.tackoverDate = tackoverDate;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_oa_ad_goodstackover_and_stacfg", joinColumns = { @JoinColumn(name = "GTR_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "GTS_ID", nullable = false, updatable = false) })
	@JSONField(serialize=false)
	public List<GoodsTackoverStacfg> getGoodsTackoverStacfgList() {
		return goodsTackoverStacfgList;
	}

	public void setGoodsTackoverStacfgList(
			List<GoodsTackoverStacfg> goodsTackoverStacfgList) {
		this.goodsTackoverStacfgList = goodsTackoverStacfgList;
	}
	@Transient
	public String getTurnoverUserName() {
		return turnoverUserName;
	}

	public void setTurnoverUserName(String turnoverUserName) {
		this.turnoverUserName = turnoverUserName;
	}
	@Transient
	public String getTurnoverDeptName() {
		return turnoverDeptName;
	}

	public void setTurnoverDeptName(String turnoverDeptName) {
		this.turnoverDeptName = turnoverDeptName;
	}
	@Transient
	public String getTackoverUserName() {
		return tackoverUserName;
	}

	public void setTackoverUserName(String tackoverUserName) {
		this.tackoverUserName = tackoverUserName;
	}
	@Transient
	public String getTackoverDeptName() {
		return tackoverDeptName;
	}

	public void setTackoverDeptName(String tackoverDeptName) {
		this.tackoverDeptName = tackoverDeptName;
	}
	@Transient
	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	@Transient
	public String getRegDatetimeBefore() {
		return regDatetimeBefore;
	}

	public void setRegDatetimeBefore(String regDatetimeBefore) {
		this.regDatetimeBefore = regDatetimeBefore;
	}
	@Transient
	public String getRegDatetimeAfter() {
		return regDatetimeAfter;
	}

	public void setRegDatetimeAfter(String regDatetimeAfter) {
		this.regDatetimeAfter = regDatetimeAfter;
	}
	@Transient
	public String getTurnoverDateBefore() {
		return turnoverDateBefore;
	}

	public void setTurnoverDateBefore(String turnoverDateBefore) {
		this.turnoverDateBefore = turnoverDateBefore;
	}
	@Transient
	public String getTurnoverDateAfter() {
		return turnoverDateAfter;
	}

	public void setTurnoverDateAfter(String turnoverDateAfter) {
		this.turnoverDateAfter = turnoverDateAfter;
	}
	@Transient
	public String getTackoverDateBefore() {
		return tackoverDateBefore;
	}

	public void setTackoverDateBefore(String tackoverDateBefore) {
		this.tackoverDateBefore = tackoverDateBefore;
	}
	@Transient
	public String getTackoverDateAfter() {
		return tackoverDateAfter;
	}

	public void setTackoverDateAfter(String tackoverDateAfter) {
		this.tackoverDateAfter = tackoverDateAfter;
	}
	
	
}
