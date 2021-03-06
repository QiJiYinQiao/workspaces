package com.oasys.model;

// Generated 2015-12-11 13:36:55 by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TOaAdStampStatisticsReg generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_stamp_statistics_reg", catalog = "oasys")
public class StampStatisticsReg implements java.io.Serializable {

	private Integer ssrId;
	private Integer registrantNo;
	private Date regDate;
	private Integer belongDept;
	private String stampName;
	private Character stampNature;
	private Integer stampQty;
	private Integer surplusQty;
	private Integer lastBorrower;
	private Integer updator;
	private Date updDate;
	private String remark;

	public StampStatisticsReg() {
	}

	public StampStatisticsReg(Integer registrantNo, Date regDate,
			Integer belongDept, String stampName, Character stampNature,
			Integer stampQty, Integer surplusQty, Integer lastBorrower,
			Integer updator, Date updDate, String remark) {
		this.registrantNo = registrantNo;
		this.regDate = regDate;
		this.belongDept = belongDept;
		this.stampName = stampName;
		this.stampNature = stampNature;
		this.stampQty = stampQty;
		this.surplusQty = surplusQty;
		this.lastBorrower = lastBorrower;
		this.updator = updator;
		this.updDate = updDate;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SSR_ID", unique = true, nullable = false)
	public Integer getssrId() {
		return this.ssrId;
	}

	public void setssrId(Integer ssrId) {
		this.ssrId = ssrId;
	}

	@Column(name = "REGISTRANT_NO")
	public Integer getRegistrantNo() {
		return this.registrantNo;
	}

	public void setRegistrantNo(Integer registrantNo) {
		this.registrantNo = registrantNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REG_DATE", length = 10)
	public Date getRegDate() {
		return this.regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column(name = "BELONG_DEPT")
	public Integer getBelongDept() {
		return this.belongDept;
	}

	public void setBelongDept(Integer belongDept) {
		this.belongDept = belongDept;
	}

	@Column(name = "STAMP_NAME", length = 128)
	public String getStampName() {
		return this.stampName;
	}

	public void setStampName(String stampName) {
		this.stampName = stampName;
	}

	@Column(name = "STAMP_NATURE", length = 1)
	public Character getStampNature() {
		return this.stampNature;
	}

	public void setStampNature(Character stampNature) {
		this.stampNature = stampNature;
	}

	@Column(name = "STAMP_QTY")
	public Integer getStampQty() {
		return this.stampQty;
	}

	public void setStampQty(Integer stampQty) {
		this.stampQty = stampQty;
	}

	@Column(name = "SURPLUS_QTY")
	public Integer getSurplusQty() {
		return this.surplusQty;
	}

	public void setSurplusQty(Integer surplusQty) {
		this.surplusQty = surplusQty;
	}

	@Column(name = "LAST_BORROWER")
	public Integer getLastBorrower() {
		return this.lastBorrower;
	}

	public void setLastBorrower(Integer lastBorrower) {
		this.lastBorrower = lastBorrower;
	}

	@Column(name = "UPDATOR")
	public Integer getUpdator() {
		return this.updator;
	}

	public void setUpdator(Integer updator) {
		this.updator = updator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPD_DATE", length = 10)
	public Date getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
