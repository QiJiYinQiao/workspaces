package com.oasys.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_oa_ad_vehicle_uses_reg")
public class VehicleUsesReg {
	private Integer vurId;//id
	private Integer registrantNo;//登记人
	private Date regDate;//登记日期
	private Integer userNo;//使用人
	private Integer deptNo;//所属部门
	private String carNo;//车牌号码
	private String userReson;//使用事由
	private Date userDateTime;//用车时间
	private String origin;//始发地
	private String destination;//目的地
	private Double bgKilometer;//启程公里数
	private Double edKilometer;//结束公里数
	private Date gvDateTime;//归还时间
	private Double fuelCharge;//加油费
	private Double roadToll;//路桥费
	private Double parkingFee;//停车费
	private Double totalAMT;//合计金额
	private String remark;//备注信息
	
	public VehicleUsesReg() {
	}

	public VehicleUsesReg(Integer vurId, Integer registrantNo, Date regDate,
			Integer userNo, Integer deptNo, String carNo, String userReson,
			Date userDateTime, String origin, String destination,
			Double bgKilometer, Double edKilometer, Date gvDateTime,
			Double fuelCharge, Double roadToll, Double parkingFee,
			Double totalAMT, String remark) {
		this.vurId = vurId;
		this.registrantNo = registrantNo;
		this.regDate = regDate;
		this.userNo = userNo;
		this.deptNo = deptNo;
		this.carNo = carNo;
		this.userReson = userReson;
		this.userDateTime = userDateTime;
		this.origin = origin;
		this.destination = destination;
		this.bgKilometer = bgKilometer;
		this.edKilometer = edKilometer;
		this.gvDateTime = gvDateTime;
		this.fuelCharge = fuelCharge;
		this.roadToll = roadToll;
		this.parkingFee = parkingFee;
		this.totalAMT = totalAMT;
		this.remark = remark;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "VUR_ID", unique = true, nullable = false)
	public Integer getVurId() {
		return vurId;
	}

	public void setVurId(Integer vurId) {
		this.vurId = vurId;
	}

	@Column(name = "REGISTRANT_NO", length = 11)
	public Integer getRegistrantNo() {
		return registrantNo;
	}

	public void setRegistrantNo(Integer registrantNo) {
		this.registrantNo = registrantNo;
	}

	@Column(name = "REG_DATE")
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column(name = "USER_NO", length = 11)
	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	@Column(name = "DEPT_NO", length = 11)
	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	@Column(name = "CAR_NO", length = 10)
	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	@Column(name = "USES_RESON", length = 100)
	public String getUserReson() {
		return userReson;
	}

	public void setUserReson(String userReson) {
		this.userReson = userReson;
	}

	@Column(name = "USES_DATETIME")
	public Date getUserDateTime() {
		return userDateTime;
	}

	public void setUserDateTime(Date userDateTime) {
		this.userDateTime = userDateTime;
	}

	@Column(name = "ORIGIN", length = 50)
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "DESTINATION", length = 50)
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	@Column(name = "BG_KILOMETER", length = 10)
	public Double getBgKilometer() {
		return bgKilometer;
	}

	public void setBgKilometer(Double bgKilometer) {
		this.bgKilometer = bgKilometer;
	}

	@Column(name = "ED_KILOMETER", length = 10)
	public Double getEdKilometer() {
		return edKilometer;
	}

	public void setEdKilometer(Double edKilometer) {
		this.edKilometer = edKilometer;
	}

	@Column(name = "GV_DATETIME")
	public Date getGvDateTime() {
		return gvDateTime;
	}

	public void setGvDateTime(Date gvDateTime) {
		this.gvDateTime = gvDateTime;
	}

	@Column(name = "FUEL_CHARGE", length = 8)
	public Double getFuelCharge() {
		return fuelCharge;
	}

	public void setFuelCharge(Double fuelCharge) {
		this.fuelCharge = fuelCharge;
	}

	@Column(name = "ROAD_TOLL", length = 8)
	public Double getRoadToll() {
		return roadToll;
	}

	public void setRoadToll(Double roadToll) {
		this.roadToll = roadToll;
	}

	@Column(name = "PARKING_FEE", length = 8)
	public Double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(Double parkingFee) {
		this.parkingFee = parkingFee;
	}

	@Column(name = "TOTAL_AMT", length = 10)
	public Double getTotalAMT() {
		return totalAMT;
	}

	public void setTotalAMT(Double totalAMT) {
		this.totalAMT = totalAMT;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
