package com.oasys.model;

// Generated 2015-10-10 14:43:11 by Hibernate Tools 4.0.0

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
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 费用申请实体类
 * @ClassName: ExpensesApp 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月10日 下午4:41:33
 */
@Entity
@Table(name = "t_oa_ad_expenses_app", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class ExpensesApp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer btaId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请人
	 */
	private Integer applicantNo;
	/**
	 * 所属部门
	 */
	private Integer deptNo;
	/**
	 * 申请日期
	 */
	private Date appDate;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 费用用途
	 */
	private String expReson;
	/**
	 * 申请金额
	 */
	private BigDecimal appAmt;
	/**
	 * 支付方式
	 */
	private String payMode;
	/**
	 * 转入账号
	 */
	private Integer intoAct;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 账户名称
	 */
	private String actName;
	/**
	 * 票据类型
	 */
	private String billType;
	/**
	 * 其他票据类型
	 */
	private String billTypeOther;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 备注信息
	 */
	private String remark;

	public ExpensesApp() {
	}

	public ExpensesApp(String appNo, Integer applicantNo, Integer deptNo,
			Date appDate, String appStatus, String expReson, BigDecimal appAmt,
			String payMode, Integer intoAct, String bankName,
			String actName, String billType, String billTypeOther,
			String procStatus, String remark) {
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.expReson = expReson;
		this.appAmt = appAmt;
		this.payMode = payMode;
		this.intoAct = intoAct;
		this.bankName = bankName;
		this.actName = actName;
		this.billType = billType;
		this.billTypeOther = billTypeOther;
		this.procStatus = procStatus;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BTA_ID", unique = true, nullable = false)
	public Integer getBtaId() {
		return this.btaId;
	}

	public void setBtaId(Integer btaId) {
		this.btaId = btaId;
	}

	@Column(name = "APP_NO", unique = true, length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	@Column(name = "APPLICANT_NO")
	public Integer getApplicantNo() {
		return this.applicantNo;
	}

	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}

	@Column(name = "DEPT_NO")
	public Integer getDeptNo() {
		return this.deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APP_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getAppDate() {
		return this.appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return this.appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	@Column(name = "EXP_RESON", length = 500)
	public String getExpReson() {
		return this.expReson;
	}

	public void setExpReson(String expReson) {
		this.expReson = expReson;
	}

	@Column(name = "APP_AMT", precision = 10)
	public BigDecimal getAppAmt() {
		return this.appAmt;
	}

	public void setAppAmt(BigDecimal appAmt) {
		this.appAmt = appAmt;
	}

	@Column(name = "PAY_MODE", length = 1)
	public String getPayMode() {
		return this.payMode;
	}

	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}

	@Column(name = "INTO_ACT")
	public Integer getIntoAct() {
		return this.intoAct;
	}

	public void setIntoAct(Integer intoAct) {
		this.intoAct = intoAct;
	}

	@Column(name = "BANK_NAME", length = 80)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "ACT_NAME", length = 15)
	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	@Column(name = "BILL_TYPE", length = 1)
	public String getBillType() {
		return this.billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	@Column(name = "BILL_TYPE_OTHER", length = 20)
	public String getBillTypeOther() {
		return this.billTypeOther;
	}

	public void setBillTypeOther(String billTypeOther) {
		this.billTypeOther = billTypeOther;
	}

	@Column(name = "PROC_STATUS", length = 1)
	public String getProcStatus() {
		return this.procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
