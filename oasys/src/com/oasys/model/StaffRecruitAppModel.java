package com.oasys.model;

// Generated 2015-11-9 16:00:21 by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "t_oa_hr_manpower_requisition_app", catalog = "oasys", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class StaffRecruitAppModel implements java.io.Serializable, Cloneable {

	private Integer mraId;                 //主键
	private String appNo;                  //申请编号，记录当前申请的唯一编号，建议编号生成规则为：MR+动态生成唯一编号
	private Integer applicantNo;           //申请人编号，记录当前提交申请的用户编号
	private Integer deptNo;                //所属部门，记录当前申请人的所属部门编号
	private Date appDate;                  //申请日期
	private String appStatus;              //申请状态
	private Integer existingStaff;         //现有人员
	private String jobTitle;               //职位名称
	private Integer recruitQty;            //招聘数量
	private Character isTargetInside;      
	private Character recruitPurpose;
	private Character recruitType;
	private Character recruitPlace;
	private Date jobDtime;                  //上岗时间
	private BigDecimal serviceLife;         //服务器年限
	private BigDecimal adviceSalLower;      //建议薪资下限
	private BigDecimal adviceSalUpper;      //建议薪资上限
	private String jobDescription;          //招聘岗位职责，工作职责
	private String qualification;           //任职资格，教育、经验和技能信息
	private Character procStatus;
	private String remark;

	public StaffRecruitAppModel() {
	}

	public StaffRecruitAppModel(String appNo, Integer applicantNo,
			Integer deptNo, Date appDate, String appStatus,
			Integer existingStaff, String jobTitle, Integer recruitQty,
			Character isTargetInside, Character recruitPurpose,
			Character recruitType, Character recruitPlace, Date jobDtime,
			BigDecimal serviceLife, BigDecimal adviceSalLower,
			BigDecimal adviceSalUpper, String jobDescription,
			String qualification, Character procStatus, String remark) {
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.existingStaff = existingStaff;
		this.jobTitle = jobTitle;
		this.recruitQty = recruitQty;
		this.isTargetInside = isTargetInside;
		this.recruitPurpose = recruitPurpose;
		this.recruitType = recruitType;
		this.recruitPlace = recruitPlace;
		this.jobDtime = jobDtime;
		this.serviceLife = serviceLife;
		this.adviceSalLower = adviceSalLower;
		this.adviceSalUpper = adviceSalUpper;
		this.jobDescription = jobDescription;
		this.qualification = qualification;
		this.procStatus = procStatus;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MRA_ID", unique = true, nullable = false)
	public Integer getMraId() {
		return this.mraId;
	}

	public void setMraId(Integer mraId) {
		this.mraId = mraId;
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

	@Column(name = "EXISTING_STAFF")
	public Integer getExistingStaff() {
		return this.existingStaff;
	}

	public void setExistingStaff(Integer existingStaff) {
		this.existingStaff = existingStaff;
	}

	@Column(name = "JOB_TITLE", length = 50)
	public String getJobTitle() {
		return this.jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name = "RECRUIT_QTY")
	public Integer getRecruitQty() {
		return this.recruitQty;
	}

	public void setRecruitQty(Integer recruitQty) {
		this.recruitQty = recruitQty;
	}

	@Column(name = "IS_TARGET_INSIDE", length = 1)
	public Character getIsTargetInside() {
		return this.isTargetInside;
	}

	public void setIsTargetInside(Character isTargetInside) {
		this.isTargetInside = isTargetInside;
	}

	@Column(name = "RECRUIT_PURPOSE", length = 1)
	public Character getRecruitPurpose() {
		return this.recruitPurpose;
	}

	public void setRecruitPurpose(Character recruitPurpose) {
		this.recruitPurpose = recruitPurpose;
	}

	@Column(name = "RECRUIT_TYPE", length = 1)
	public Character getRecruitType() {
		return this.recruitType;
	}

	public void setRecruitType(Character recruitType) {
		this.recruitType = recruitType;
	}

	@Column(name = "RECRUIT_PLACE", length = 1)
	public Character getRecruitPlace() {
		return this.recruitPlace;
	}

	public void setRecruitPlace(Character recruitPlace) {
		this.recruitPlace = recruitPlace;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "JOB_DTIME", length = 19)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getJobDtime() {
		return this.jobDtime;
	}

	public void setJobDtime(Date jobDtime) {
		this.jobDtime = jobDtime;
	}

	@Column(name = "SERVICE_LIFE", precision = 3, scale = 1)
	public BigDecimal getServiceLife() {
		return this.serviceLife;
	}

	public void setServiceLife(BigDecimal serviceLife) {
		this.serviceLife = serviceLife;
	}

	@Column(name = "ADVICE_SAL_LOWER", precision = 10)
	public BigDecimal getAdviceSalLower() {
		return this.adviceSalLower;
	}

	public void setAdviceSalLower(BigDecimal adviceSalLower) {
		this.adviceSalLower = adviceSalLower;
	}

	@Column(name = "ADVICE_SAL_UPPER", precision = 10)
	public BigDecimal getAdviceSalUpper() {
		return this.adviceSalUpper;
	}

	public void setAdviceSalUpper(BigDecimal adviceSalUpper) {
		this.adviceSalUpper = adviceSalUpper;
	}

	@Column(name = "JOB_DESCRIPTION", length = 256)
	public String getJobDescription() {
		return this.jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@Column(name = "QUALIFICATION", length = 1024)
	public String getQualification() {
		return this.qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	@Column(name = "PROC_STATUS", length = 1)
	public Character getProcStatus() {
		return this.procStatus;
	}

	public void setProcStatus(Character procStatus) {
		this.procStatus = procStatus;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}	

}
