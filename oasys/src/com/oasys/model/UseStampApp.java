package com.oasys.model;

// Generated 2015-10-10 15:27:56 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * TOaPdUseStampApp generated by hbm2java
 */
@Entity
@Table(name = "t_oa_pd_use_stamp_app", catalog = "oasys", uniqueConstraints = @UniqueConstraint(columnNames = "APP_NO"))
public class UseStampApp implements java.io.Serializable,Cloneable {

	private Integer usaId;
	private String appNo;
	private Integer applicantNo;
	private Integer deptNo;
	
	private Date appDate;
	
	private String appStatus;
	private String usReason;
	private Integer usQuantity;
	private String stampType;
	private String stampTypeOther;
	
	private Date usBeginDate;
	private Date usEndDate;
	
	private String procStatus;
	private String remark;

	//申请人名字
	private String applicantName;
	//部门名称
	private String fullName;
	//开始申请时间
	private String appDateS;
	//结束时间
	private String appDateE;
	//申请状态名称
	private String appStateName;
	//印章名字
	private String  dictName;
	
	/** WorkFlow propertites */
	private WorkFlowTasksModel taskModel;//task对象
	private String result;//审批通过或驳回标识
	private String taskId;//任务taskId
	private String formKey;//跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	private String assistant;//指定的候选人
	private String isSuccess;//是否通过标识
	private String ideaRemark;//审批意见
	
	public UseStampApp() {
	}

	
	
	public UseStampApp(Integer usaId, String appNo, Integer applicantNo,
			Integer deptNo, Date appDate, String appStatus, String usReason,
			Integer usQuantity, String stampType, String stampTypeOther,
			Date usBeginDate, Date usEndDate, String procStatus,
			String remark, String applicantName, String fullName,
			String appDateS, String appDateE, String appStateName,
			String dictName, WorkFlowTasksModel taskModel, String result,
			String taskId, String formKey, String assistant, String isSuccess,
			String ideaRemark) {
		super();
		this.usaId = usaId;
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.deptNo = deptNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.usReason = usReason;
		this.usQuantity = usQuantity;
		this.stampType = stampType;
		this.stampTypeOther = stampTypeOther;
		this.usBeginDate = usBeginDate;
		this.usEndDate = usEndDate;
		this.procStatus = procStatus;
		this.remark = remark;
		this.applicantName = applicantName;
		this.fullName = fullName;
		this.appDateS = appDateS;
		this.appDateE = appDateE;
		this.appStateName = appStateName;
		this.dictName = dictName;
		this.taskModel = taskModel;
		this.result = result;
		this.taskId = taskId;
		this.formKey = formKey;
		this.assistant = assistant;
		this.isSuccess = isSuccess;
		this.ideaRemark = ideaRemark;
	}


	@Transient
	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}
	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}
	@Transient
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}


	@Transient
	public String getTaskId() {
		return taskId;
	}



	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	@Transient
	public String getFormKey() {
		return formKey;
	}



	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}


	@Transient
	public String getAssistant() {
		return assistant;
	}



	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}


	@Transient
	public String getIsSuccess() {
		return isSuccess;
	}



	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}


	@Transient
	public String getIdeaRemark() {
		return ideaRemark;
	}



	public void setIdeaRemark(String ideaRemark) {
		this.ideaRemark = ideaRemark;
	}



	@Transient
	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	@Transient
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	@Transient
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Transient
	public String getAppDateS() {
		return appDateS;
	}

	public void setAppDateS(String appDateS) {
		this.appDateS = appDateS;
	}
	@Transient
	public String getAppDateE() {
		return appDateE;
	}

	public void setAppDateE(String appDateE) {
		this.appDateE = appDateE;
	}
	@Transient
	public String getAppStateName() {
		return appStateName;
	}

	public void setAppStateName(String appStateName) {
		this.appStateName = appStateName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "USA_ID", unique = true, nullable = false)
	public Integer getUsaId() {
		return this.usaId;
	}

	public void setUsaId(Integer usaId) {
		this.usaId = usaId;
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

	@Column(name = "US_REASON", length = 200)
	public String getUsReason() {
		return this.usReason;
	}

	public void setUsReason(String usReason) {
		this.usReason = usReason;
	}

	@Column(name = "US_QUANTITY")
	public Integer getUsQuantity() {
		return this.usQuantity;
	}

	public void setUsQuantity(Integer usQuantity) {
		this.usQuantity = usQuantity;
	}

	@Column(name = "STAMP_TYPE", length = 1)
	public String getStampType() {
		return this.stampType;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	@Column(name = "STAMP_TYPE_OTHER", length = 30)
	public String getStampTypeOther() {
		return this.stampTypeOther;
	}

	public void setStampTypeOther(String stampTypeOther) {
		this.stampTypeOther = stampTypeOther;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "US_BEGIN_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getUsBeginDate() {
		return this.usBeginDate;
	}

	public void setUsBeginDate(Date usBeginDate) {
		this.usBeginDate = usBeginDate;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "US_END_DATE", length = 10)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getUsEndDate() {
		return this.usEndDate;
	}

	public void setUsEndDate(Date usEndDate) {
		this.usEndDate = usEndDate;
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
