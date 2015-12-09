package com.oasys.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_oa_ad_penalty_notice_reg")
public class PenaltyNoticeReg {
	private Integer pnrId;//编号
	private Integer registrantNo;//登记人
	private Date regDateTime;//登记日期
	private Integer ptNo;//受罚对象
	private String ptType;//受罚对象类型
	private Date penaltyDate;//受罚日期
	private String penaltyReson;//受罚原因
	private String penaltyResonOther;//其他受罚原因
	private Double penaltyAMT;//罚款金额
	private Integer signDeptNo;//罚款单签发部门编号
	private String remark;//备注信息
	
	public PenaltyNoticeReg() {
	}
	
	public PenaltyNoticeReg(Integer pnrId, Integer registrantNo,
			Date regDateTime, Integer ptNo, String ptType, Date penaltyDate,
			String penaltyReson, String penaltyResonOther, Double penaltyAMT,
			Integer signDeptNo, String remark) {
		this.pnrId = pnrId;
		this.registrantNo = registrantNo;
		this.regDateTime = regDateTime;
		this.ptNo = ptNo;
		this.ptType = ptType;
		this.penaltyDate = penaltyDate;
		this.penaltyReson = penaltyReson;
		this.penaltyResonOther = penaltyResonOther;
		this.penaltyAMT = penaltyAMT;
		this.signDeptNo = signDeptNo;
		this.remark = remark;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PNR_ID", unique = true, nullable = false)
	public Integer getPnrId() {
		return pnrId;
	}

	public void setPnrId(Integer pnrId) {
		this.pnrId = pnrId;
	}

	@Column(name = "REGISTRANT_NO", length = 11)
	public Integer getRegistrantNo() {
		return registrantNo;
	}

	public void setRegistrantNo(Integer registrantNo) {
		this.registrantNo = registrantNo;
	}


	@Column(name = "REG_DATETIME")
	public Date getRegDateTime() {
		return regDateTime;
	}

	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}

	@Column(name = "PT_NO", length = 11)
	public Integer getPtNo() {
		return ptNo;
	}

	public void setPtNo(Integer ptNo) {
		this.ptNo = ptNo;
	}

	@Column(name = "PT_TYPE", length = 1)
	public String getPtType() {
		return ptType;
	}

	public void setPtType(String ptType) {
		this.ptType = ptType;
	}

	@Column(name = "PENALTY_DATE")
	public Date getPenaltyDate() {
		return penaltyDate;
	}

	public void setPenaltyDate(Date penaltyDate) {
		this.penaltyDate = penaltyDate;
	}

	@Column(name = "PENALTY_RESON", length = 1)
	public String getPenaltyReson() {
		return penaltyReson;
	}

	public void setPenaltyReson(String penaltyReson) {
		this.penaltyReson = penaltyReson;
	}

	@Column(name = "PENALTY_RESON_OTHER", length = 200)
	public String getPenaltyResonOther() {
		return penaltyResonOther;
	}

	public void setPenaltyResonOther(String penaltyResonOther) {
		this.penaltyResonOther = penaltyResonOther;
	}

	@Column(name = "PENALTY_AMT", length = 10)
	public Double getPenaltyAMT() {
		return penaltyAMT;
	}

	public void setPenaltyAMT(Double penaltyAMT) {
		this.penaltyAMT = penaltyAMT;
	}

	@Column(name = "SIGN_DEPT_NO", length = 11)
	public Integer getSignDeptNo() {
		return signDeptNo;
	}

	public void setSignDeptNo(Integer signDeptNo) {
		this.signDeptNo = signDeptNo;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
