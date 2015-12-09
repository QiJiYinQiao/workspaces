package com.oasys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * ExpressInfoReg entity. 
 * @author lida
 * @Package com.oasys.model
 * @Description:快递信息登记信息
 * @date 2015/10/13
 * @version 1.0
 */
@Entity
@Table(name = "t_oa_ad_express_info_reg")
public class ExpressInfoReg implements java.io.Serializable
{
	private static final long serialVersionUID = 3995767024135212110L;
	private Integer eirId;//ID
	private Integer registrantNo;//登记人
	private Integer addresseeNo;//收件人
	private Integer deptNo;//收件人所属部门
	
	private String regDatetime;//登记日期
	private String regDatetimeBegin;
	private String regDatetimeEnd;
	private String nhwrDate;//收件日期
	private String nhwrDateBegin;
	private String nhwrDateEnd;
	private String nhwrReson;//事由
	private String expNo;//快递单号
	private String expAmt;//金额
	private String remark;//备注
	
	private String userName;
	private String addUserName;
	private String addOrgName;
	
	
	
	public ExpressInfoReg() {
		super();
	}

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "EIR_ID", unique = true, nullable = false,length=10)
	public Integer getEirId() {
		return eirId;
	}
	public void setEirId(Integer eirId) {
		this.eirId = eirId;
	}
	
	
	
	@Column(name = "REG_DATETIME")
	public String getRegDatetime() {
		return regDatetime;
	}


	public void setRegDatetime(String regDatetime) {
		this.regDatetime = regDatetime;
	}

	@Transient
	public String getNhwrDateBegin() {
		return nhwrDateBegin;
	}


	public void setNhwrDateBegin(String nhwrDateBegin) {
		this.nhwrDateBegin = nhwrDateBegin;
	}

	@Transient
	public String getNhwrDateEnd() {
		return nhwrDateEnd;
	}


	public void setNhwrDateEnd(String nhwrDateEnd) {
		this.nhwrDateEnd = nhwrDateEnd;
	}

	
	
	@Transient
	public String getRegDatetimeBegin() {
		return regDatetimeBegin;
	}


	public void setRegDatetimeBegin(String regDatetimeBegin) {
		this.regDatetimeBegin = regDatetimeBegin;
	}

	@Transient
	public String getRegDatetimeEnd() {
		return regDatetimeEnd;
	}


	public void setRegDatetimeEnd(String regDatetimeEnd) {
		this.regDatetimeEnd = regDatetimeEnd;
	}


	@Column(name = "REGISTRANT_NO")
	public Integer getRegistrantNo() {
		return registrantNo;
	}
	public void setRegistrantNo(Integer registrantNo) {
		this.registrantNo = registrantNo;
	}
	
	@Column(name = "ADDRESSEE_NO")
	public Integer getAddresseeNo() {
		return addresseeNo;
	}
	public void setAddresseeNo(Integer addresseeNo) {
		this.addresseeNo = addresseeNo;
	}
	
	@Column(name = "DEPT_NO")
	public Integer getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}
	
	@Column(name = "NHWR_DATE")
	public String getNhwrDate() {
		return nhwrDate;
	}
	public void setNhwrDate(String nhwrDate) {
		this.nhwrDate = nhwrDate;
	}
	
	@Column(name = "NHWR_RESON")
	public String getNhwrReson() {
		return nhwrReson;
	}
	public void setNhwrReson(String nhwrReson) {
		this.nhwrReson = nhwrReson;
	}
	
	@Column(name = "EXP_NO")
	public String getExpNo() {
		return expNo;
	}
	public void setExpNo(String expNo) {
		this.expNo = expNo;
	}
	
	@Column(name = "EXP_AMT")
	public String getExpAmt() {
		return expAmt;
	}
	public void setExpAmt(String expAmt) {
		this.expAmt = expAmt;
	}
	
	@Column(name = "REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Transient
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Transient
	public String getAddUserName() {
		return addUserName;
	}


	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	@Transient
	public String getAddOrgName() {
		return addOrgName;
	}


	public void setAddOrgName(String addOrgName) {
		this.addOrgName = addOrgName;
	}
	
	
}