package com.oasys.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.sun.star.util.DateTime;

@Entity
@Table(name = "t_oa_ad_card_app")
@DynamicUpdate(true)
@DynamicInsert(true)
public class CardApp implements Cloneable{
	private Integer caID;//主键ID
	private String appNo;//申请编号
	private Integer registrantNO;//登记人
	private Date regDeteTime;//登记日期
	private Date appDate;//申请日期
	private String appStatus;//申请状态
	private Integer appQty;//申请数量
	private BigDecimal totalAMT;//合计金额
	private Integer procStatus;//流程状态
	private String unit;//单位 盒
	private String remark;//备注信息
//	private Set<CardAppAttach> cardAppAttachs=new HashSet<CardAppAttach>();
	
	public CardApp() {
	}
	
	
	public CardApp(Integer caID, String appNo, Integer registrantNO,
			Date regDeteTime, Date appDate, String appStatus, Integer appQty,
			Integer procStatus, String unit, String remark) {
		this.caID = caID;
		this.appNo = appNo;
		this.registrantNO = registrantNO;
		this.regDeteTime = regDeteTime;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.appQty = appQty;
		this.procStatus = procStatus;
		this.unit = unit;
		this.remark = remark;
	}


	@Column(name = "CA_ID", length = 10,unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	public Integer getCaID() {
		return caID;
	}
	
	public void setCaID(Integer caID) {
		this.caID = caID;
	}

	@Column(name = "APP_NO", length = 25)
	public String getAppNo() {
		return appNo;
	}
	
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "REGISTRANT_NO", length = 11)
	public Integer getRegistrantNO() {
		return registrantNO;
	}
	
	public void setRegistrantNO(Integer registrantNO) {
		this.registrantNO = registrantNO;
	}

	@Column(name = "REG_DATETIME")
	public Date getRegDeteTime() {
		return regDeteTime;
	}
	
	public void setRegDeteTime(Date regDeteTime) {
		this.regDeteTime = regDeteTime;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "APP_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getAppDate() {
		return appDate;
	}
	
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}
	
	@Column(name = "APP_STATUS",length=1)
	public String getAppStatus() {
		return appStatus;
	}
	
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "APP_QTY",length=11)
	public Integer getAppQty() {
		return appQty;
	}
	
	public void setAppQty(Integer appQty) {
		this.appQty = appQty;
	}
	
	@Column(name = "TOTAL_AMT",length=10)
	public BigDecimal getTotalAMT() {
		return totalAMT;
	}

	public void setTotalAMT(BigDecimal totalAMT) {
		this.totalAMT = totalAMT;
	}


	@Column(name = "PROC_STATUS",length=1)
	public Integer getProcStatus() {
		return procStatus;
	}
	
	public void setProcStatus(Integer procStatus) {
		this.procStatus = procStatus;
	}
	
	@Column(name="UNIT",length=10)
	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	@Column(name = "REMARK",length=200)
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

//	@OneToMany(mappedBy = "cardApp")
//	public Set<CardAppAttach> getCardAppAttachs() {
//		return cardAppAttachs;
//	}
//
//
//	public void setCardAppAttachs(Set<CardAppAttach> cardAppAttachs) {
//		this.cardAppAttachs = cardAppAttachs;
//	}
}
