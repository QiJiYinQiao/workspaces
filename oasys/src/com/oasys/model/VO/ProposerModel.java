package com.oasys.model.VO;

import java.util.Date;

/**
 * 申请人vo
 * @ClassName: ProposerModel 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月26日 下午3:14:13
 */
public class ProposerModel implements java.io.Serializable,Cloneable{
	
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
	private String education;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 入职日期
	 */
	private String entryDate;
	/**
	 * 转正薪资
	 */
	private String regularSal;
	/**
	 * 试用开始日期
	 */
	private String bgProbDate;
	/**
	 * 试用结束日期
	 */
	private String edProbDate;
	/**
	 * 甄选方式
	 */
	private String chooseMode;
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
	 * 备注信息
	 */
	private String remark;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 申请名称
	 */
	private String appStatusName;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 甄选方式名称
	 */
	private String chooseModeName;
	/**
	 * 学历名称
	 */
	private String educationName;
	
	public Integer getEfaId() {
		return efaId;
	}


	public void setEfaId(Integer efaId) {
		this.efaId = efaId;
	}


	public String getAppNo() {
		return appNo;
	}


	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}


	public Date getAppDate() {
		return appDate;
	}


	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}


	public Integer getApplicantNo() {
		return applicantNo;
	}


	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}


	public Integer getDeptNo() {
		return deptNo;
	}


	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getGraduateSchool() {
		return graduateSchool;
	}


	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	public String getAge() {
		return age;
	}


	public void setAge(String age) {
		this.age = age;
	}


	public String getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}


	public String getRegularSal() {
		return regularSal;
	}


	public void setRegularSal(String regularSal) {
		this.regularSal = regularSal;
	}


	public String getBgProbDate() {
		return bgProbDate;
	}


	public void setBgProbDate(String bgProbDate) {
		this.bgProbDate = bgProbDate;
	}


	public String getEdProbDate() {
		return edProbDate;
	}


	public void setEdProbDate(String edProbDate) {
		this.edProbDate = edProbDate;
	}


	public String getChooseMode() {
		return chooseMode;
	}


	public void setChooseMode(String chooseMode) {
		this.chooseMode = chooseMode;
	}


	public Integer getWorkYears() {
		return workYears;
	}


	public void setWorkYears(Integer workYears) {
		this.workYears = workYears;
	}


	public Integer getCorWorkYears() {
		return corWorkYears;
	}


	public void setCorWorkYears(Integer corWorkYears) {
		this.corWorkYears = corWorkYears;
	}


	public Integer getNocorWorkYears() {
		return nocorWorkYears;
	}


	public void setNocorWorkYears(Integer nocorWorkYears) {
		this.nocorWorkYears = nocorWorkYears;
	}


	public String getWorkExplain() {
		return workExplain;
	}


	public void setWorkExplain(String workExplain) {
		this.workExplain = workExplain;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getAppStatus() {
		return appStatus;
	}


	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}


	public String getAppStatusName() {
		return appStatusName;
	}


	public void setAppStatusName(String appStatusName) {
		this.appStatusName = appStatusName;
	}


	public String getProcStatus() {
		return procStatus;
	}


	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getChooseModeName() {
		return chooseModeName;
	}


	public void setChooseModeName(String chooseModeName) {
		this.chooseModeName = chooseModeName;
	}


	public String getEducationName() {
		return educationName;
	}


	public void setEducationName(String educationName) {
		this.educationName = educationName;
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
