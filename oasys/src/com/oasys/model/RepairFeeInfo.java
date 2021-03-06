package com.oasys.model;

// Generated 2015-12-10 13:43:04 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TOaAdRepairFeeInfo generated by hbm2java
 */
@Entity
@Table(name = "t_oa_ad_repair_fee_info")
public class RepairFeeInfo implements java.io.Serializable {

	private Integer rfiId;
	private BigDecimal appAmt;
	private String repairItem;
	private String repairReson;
	private String remark;

	public RepairFeeInfo() {
	}

	public RepairFeeInfo(BigDecimal appAmt, String repairItem,
			String repairReson, String remark) {
		this.appAmt = appAmt;
		this.repairItem = repairItem;
		this.repairReson = repairReson;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RFI_ID", unique = true, nullable = false)
	public Integer getRfiId() {
		return this.rfiId;
	}

	public void setRfiId(Integer rfiId) {
		this.rfiId = rfiId;
	}

	@Column(name = "APP_AMT", precision = 10)
	public BigDecimal getAppAmt() {
		return this.appAmt;
	}

	public void setAppAmt(BigDecimal appAmt) {
		this.appAmt = appAmt;
	}

	@Column(name = "REPAIR_ITEM", length = 256)
	public String getRepairItem() {
		return this.repairItem;
	}

	public void setRepairItem(String repairItem) {
		this.repairItem = repairItem;
	}

	@Column(name = "REPAIR_RESON", length = 512)
	public String getRepairReson() {
		return this.repairReson;
	}

	public void setRepairReson(String repairReson) {
		this.repairReson = repairReson;
	}

	@Column(name = "REMARK", length = 256)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
