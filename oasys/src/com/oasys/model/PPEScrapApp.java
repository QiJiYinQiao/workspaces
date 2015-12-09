package com.oasys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * PPEScrapApp entity. 
 * @author lida
 * @Package com.oasys.model
 * @Description: 固定资产报废实体对象
 * @date 2015/9/16
 * @version 1.0
 */
@Entity
@Table(name = "t_oa_ad_ppe_scrap_app")
public class PPEScrapApp implements java.io.Serializable
{
	private static final long serialVersionUID = 3995767024135212110L;
	private Integer psaId;//ID
	private String appNo;//申请编号，记录当前申请的唯一编号，建议编号生成规则为：BF+动态生成唯一编号
	private Integer appDept;//申请部门
	private Integer applicantNo;//申请人ID
	private String appDate;//申请时间
	private String appStatus;//申请状态，记录流程中各分支线路名称，例如：AdminDirectorReject
	private String procStatus;//流程状态(大状态)
	private String remark;//备注信息
	private String appDateBefore;//查询申请时间开始绑定字段
	private String appDateAfter;//查询申请时间结束绑定字段
	private String orgName;//部门名称
	private String userName;//用户名称
	
	private String isHaveAttach;//是否存在附加项

	/** WorkFlow propertites */
	private WorkFlowTasksModel taskModel;//task对象
	private String result;//审批通过或驳回标识
	private String taskID;//任务taskID
	private String formKey;//跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	private String assistant;//指定的候选人
	private String isSuccess;//是否通过标识
	private String resourcesName;//资源文件名称
	private String definitionKey;//流程标识Key
	

	


	public PPEScrapApp() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public PPEScrapApp(Integer psaId, String appNo, Integer appDept,
			Integer applicantNo, String appDate, String appStatus,
			String procStatus, String remark, String appDateBefore,
			String appDateAfter, String orgName, String userName,
			WorkFlowTasksModel taskModel, String result, String taskID,
			String formKey, String assistant, String isSuccess,
			String resourcesName) {
		super();
		this.psaId = psaId;
		this.appNo = appNo;
		this.appDept = appDept;
		this.applicantNo = applicantNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.procStatus = procStatus;
		this.remark = remark;
		this.appDateBefore = appDateBefore;
		this.appDateAfter = appDateAfter;
		this.orgName = orgName;
		this.userName = userName;
		this.taskModel = taskModel;
		this.result = result;
		this.taskID = taskID;
		this.formKey = formKey;
		this.assistant = assistant;
		this.isSuccess = isSuccess;
		this.resourcesName = resourcesName;
	}






	@Transient
	public String getResourcesName() {
		return resourcesName;
	}
	
	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	@Transient
	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
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
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	@Transient
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Transient
	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}
	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}
	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	@Transient
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "PROC_STATUS")
	public String getProcStatus() {
		return procStatus;
	}
	
	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PSA_ID", unique = true, nullable = false,length=10)
	public Integer getPsaId() {
		return psaId;
	}

	public void setPsaId(Integer psaId) {
		this.psaId = psaId;
	}

	@Column(name = "APP_NO", length = 25)
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
	@Column(name = "APPLICANT_NO", length = 11)
	public Integer getApplicantNo() {
		return applicantNo;
	}
	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}
	
	@Column(name = "APP_DEPT", length = 11)
	public Integer getAppDept() {
		return appDept;
	}
	public void setAppDept(Integer appDept) {
		this.appDept = appDept;
	}
	
	@Column(name = "APP_DATE")
	public String getAppDate() {
		return appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	@Column(name = "APP_STATUS", length = 100)
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	@Column(name = "REMARK", length = 200)
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	public String getAppDateBefore() {
		return appDateBefore;
	}

	public void setAppDateBefore(String appDateBefore) {
		this.appDateBefore = appDateBefore;
	}
	@Transient
	public String getAppDateAfter() {
		return appDateAfter;
	}

	public void setAppDateAfter(String appDateAfter) {
		this.appDateAfter = appDateAfter;
	}

	
	@Transient
	public String getIsHaveAttach() {
		return isHaveAttach;
	}


	public void setIsHaveAttach(String isHaveAttach) {
		this.isHaveAttach = isHaveAttach;
	}
	
	@Transient
	public String getDefinitionKey() {
		return definitionKey;
	}


	public void setDefinitionKey(String definitionKey) {
		this.definitionKey = definitionKey;
	}

}