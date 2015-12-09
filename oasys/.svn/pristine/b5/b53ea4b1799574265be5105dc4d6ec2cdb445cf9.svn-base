package com.oasys.model;

// Generated 2015-10-14 10:29:36 by Hibernate Tools 4.0.0

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
 * 员工转正申请
 * @ClassName: EmpFullmemberApp 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月19日 下午1:44:24
 */
@Entity
@Table(name = "t_oa_hr_emp_fullmember_app", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class EmpFullmemberApp implements java.io.Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer efaId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请日期
	 */
	private Date appDate;
	/**
	 * 申请人
	 */
	private Integer applicantNo;
	/**
	 * 部门编号
	 */
	private Integer deptNo;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 毕业院校
	 */
	private String graduateSchool;
	/**
	 * 专业
	 */
	private String major;
	/**
	 * 学历
	 */
	private Character education;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 入职日期
	 */
	private Date entryDate;
	/**
	 * 转正薪资
	 */
	private BigDecimal regularSal;
	/**
	 * 试用开始日期
	 */
	private Date bgProbDate;
	/**
	 * 试用结束日期
	 */
	private Date edProbDate;
	/**
	 * 甄选方式
	 */
	private Character chooseMode;
	/**
	 * 工作经验年限
	 */
	private Integer workYears;
	/**
	 * 相关工作经验年限
	 */
	private Integer corWorkYears;
	/**
	 * 非相关工作经验年限
	 */
	private Integer nocorWorkYears;
	/**
	 * 工作说明
	 */
	private String workExplain;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 备注信息
	 */
	private String remark;

	public EmpFullmemberApp() {
	}

	public EmpFullmemberApp(String appNo, Date appDate,
			Integer applicantNo, Integer deptNo, String deptName,
			String position, String graduateSchool, String major,
			Character education, Integer age, Date entryDate,
			BigDecimal regularSal, Date bgProbDate, Date edProbDate,
			Character chooseMode, Integer workYears, Integer corWorkYears,
			Integer nocorWorkYears, String workExplain, String remark) {
		this.appNo = appNo;
		this.appDate = appDate;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.position = position;
		this.graduateSchool = graduateSchool;
		this.major = major;
		this.education = education;
		this.age = age;
		this.entryDate = entryDate;
		this.regularSal = regularSal;
		this.bgProbDate = bgProbDate;
		this.edProbDate = edProbDate;
		this.chooseMode = chooseMode;
		this.workYears = workYears;
		this.corWorkYears = corWorkYears;
		this.nocorWorkYears = nocorWorkYears;
		this.workExplain = workExplain;
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "EFA_ID", unique = true, nullable = false)
	public Integer getEfaId() {
		return this.efaId;
	}

	public void setEfaId(Integer efaId) {
		this.efaId = efaId;
	}

	@Column(name = "APP_NO", unique = true, length = 25)
	public String getAppNo() {
		return this.appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
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

	@Column(name = "DEPT_NAME", length = 50)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "POSITION", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "GRADUATE_SCHOOL", length = 150)
	public String getGraduateSchool() {
		return this.graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	@Column(name = "MAJOR", length = 80)
	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "EDUCATION", length = 1)
	public Character getEducation() {
		return this.education;
	}

	public void setEducation(Character education) {
		this.education = education;
	}

	@Column(name = "AGE")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENTRY_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Column(name = "REGULAR_SAL", precision = 10)
	public BigDecimal getRegularSal() {
		return this.regularSal;
	}

	public void setRegularSal(BigDecimal regularSal) {
		this.regularSal = regularSal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BG_PROB_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getBgProbDate() {
		return this.bgProbDate;
	}

	public void setBgProbDate(Date bgProbDate) {
		this.bgProbDate = bgProbDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ED_PROB_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getEdProbDate() {
		return this.edProbDate;
	}

	public void setEdProbDate(Date edProbDate) {
		this.edProbDate = edProbDate;
	}

	@Column(name = "CHOOSE_MODE", length = 1)
	public Character getChooseMode() {
		return this.chooseMode;
	}

	public void setChooseMode(Character chooseMode) {
		this.chooseMode = chooseMode;
	}

	@Column(name = "WORK_YEARS")
	public Integer getWorkYears() {
		return this.workYears;
	}

	public void setWorkYears(Integer workYears) {
		this.workYears = workYears;
	}

	@Column(name = "COR_WORK_YEARS")
	public Integer getCorWorkYears() {
		return this.corWorkYears;
	}

	public void setCorWorkYears(Integer corWorkYears) {
		this.corWorkYears = corWorkYears;
	}

	@Column(name = "NOCOR_WORK_YEARS")
	public Integer getNocorWorkYears() {
		return this.nocorWorkYears;
	}

	public void setNocorWorkYears(Integer nocorWorkYears) {
		this.nocorWorkYears = nocorWorkYears;
	}

	@Column(name = "WORK_EXPLAIN", length = 250)
	public String getWorkExplain() {
		return this.workExplain;
	}

	public void setWorkExplain(String workExplain) {
		this.workExplain = workExplain;
	}

	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "APP_STATUS", length = 100)
    public String getAppStatus() {
		return appStatus;
	}
	
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	
	@Column(name = "PROC_STATUS", length = 100)
	public String getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
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
