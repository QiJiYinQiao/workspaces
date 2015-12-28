package com.oasys.model;

// Generated 2015-12-10 13:43:04 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
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
 * TOaAdInsuranceFeeInfo generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_insurance_fee_info")
public class InsuranceFeeInfo implements java.io.Serializable {

	private Integer ifiId;
	private BigDecimal appAmt;
	private BigDecimal bgMileage;
	private BigDecimal edMileage;
	private Date insuranceBgDtime;
	private Date insuranceEdDtime;
	private String icName;
	private String remark;

	public InsuranceFeeInfo() {
	}

	public InsuranceFeeInfo(BigDecimal appAmt, BigDecimal bgMileage,
			BigDecimal edMileage, Date insuranceBgDtime, Date insuranceEdDtime,
			String icName, String remark) {
		this.appAmt = appAmt;
		this.bgMileage = bgMileage;
		this.edMileage = edMileage;
		this.insuranceBgDtime = insuranceBgDtime;
		this.insuranceEdDtime = insuranceEdDtime;
		this.icName = icName;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IFI_ID", unique = true, nullable = false)
	public Integer getIfiId() {
		return this.ifiId;
	}

	public void setIfiId(Integer ifiId) {
		this.ifiId = ifiId;
	}

	@Column(name = "APP_AMT", precision = 10)
	public BigDecimal getAppAmt() {
		return this.appAmt;
	}

	public void setAppAmt(BigDecimal appAmt) {
		this.appAmt = appAmt;
	}

	@Column(name = "BG_MILEAGE", precision = 10)
	public BigDecimal getBgMileage() {
		return this.bgMileage;
	}

	public void setBgMileage(BigDecimal bgMileage) {
		this.bgMileage = bgMileage;
	}

	@Column(name = "ED_MILEAGE", precision = 10)
	public BigDecimal getEdMileage() {
		return this.edMileage;
	}

	public void setEdMileage(BigDecimal edMileage) {
		this.edMileage = edMileage;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCE_BG_DTIME", length = 19)
	public Date getInsuranceBgDtime() {
		return this.insuranceBgDtime;
	}

	public void setInsuranceBgDtime(Date insuranceBgDtime) {
		this.insuranceBgDtime = insuranceBgDtime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSURANCE_ED_DTIME", length = 19)
	public Date getInsuranceEdDtime() {
		return this.insuranceEdDtime;
	}

	public void setInsuranceEdDtime(Date insuranceEdDtime) {
		this.insuranceEdDtime = insuranceEdDtime;
	}

	@Column(name = "IC_NAME", length = 128)
	public String getIcName() {
		return this.icName;
	}

	public void setIcName(String icName) {
		this.icName = icName;
	}

	@Column(name = "REMARK", length = 256)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}