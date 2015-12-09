package com.oasys.viewModel;

import java.util.Date;

public class OvertimeAppModel {
	private Integer oveId;//编号
	private String  appNo;//申请编号
	private String applicantNo;//申请人编号
	private String position;//申请人职位
	private Integer deptNo;//所属部门
	private String appStatus;//申请状态
	private Date appDate;//申请日期
	private String otReason;//出差事由
	private Date planBgDtime;//计划开始日期时间
	private Date planEdDtime;//计划结束日期时间
	private Double planOtCnt;//计划加班合计
	private Date realBgDtime;//实际开始日期时间
	private Date realEdDtime;//实际结束日期时间
	private Double realOtCnt;//实际加班合计
//	private Integer otCount;//职位代理人编号
//	private Date otBeginDate;//加班开始日期
//	private Date otEndDate;//加班结束日期
	private Integer procStatus;//流程状态
	private String remark;//备注
	private String applicantName;//申请人姓名
	private String deptName;//部门
	private String procStatusName;//流程状态名称
	private Integer userId;
	private Integer organizationId;
	
	private String taskId;
	private String formKey;// 跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	/** 各个节点配置的判断与跳转控制json 
	 *   如果formKey中不设置Json跳转控制 那么默认有 通过 拒绝 驳回操作 并且 通过操作会默认传递 "1" 来进行跳转下一流程
	 *   格式为：formKey?{"type":0,"JK":"ChengShiJingLi","CF":"YingYeBuJingLi"} -----该格式跳转借款端或财富端格式
	 *   			  formKey?{"type":1,"result1":"YX1","result2":"YS1","result3":"TuanDuiJingLi"} -----该格式为判断团队经理以上或团队经理以下格式
	 *   			  formKey?{"type":2} ----该格式为只有通过和驳回操作
	 *   			  formKey?{"type":3} ----该格式为只有通过操作*/ 
	private String formKeyJson;
	
	private String assistant;// 指定的候选人
	private String isSuccess;// 是否通过标识
	private String resourcesName;// 资源文件名称
	private String definitionKey;// 流程标识Key
	/**
	 * task对象
	 */
	private WorkFlowTasksModel taskModel;
	
	public OvertimeAppModel() {
	}

	public OvertimeAppModel(Integer oveId, String appNo, String applicantNo,
			String position, Integer deptNo, String appStatus, Date appDate,
			String otReason, Date planBgDtime, Date planEdDtime,
			Double planOtCnt, Date realBgDtime, Date realEdDtime,
			Double realOtCnt, Integer procStatus, String remark) {
		this.oveId = oveId;
		this.appNo = appNo;
		this.applicantNo = applicantNo;
		this.position = position;
		this.deptNo = deptNo;
		this.appStatus = appStatus;
		this.appDate = appDate;
		this.otReason = otReason;
		this.planBgDtime = planBgDtime;
		this.planEdDtime = planEdDtime;
		this.planOtCnt = planOtCnt;
		this.realBgDtime = realBgDtime;
		this.realEdDtime = realEdDtime;
		this.realOtCnt = realOtCnt;
		this.procStatus = procStatus;
		this.remark = remark;
	}

	public Integer getOveId() {
		return oveId;
	}

	public void setOveId(Integer oveId) {
		this.oveId = oveId;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getApplicantNo() {
		return applicantNo;
	}

	public void setApplicantNo(String applicantNo) {
		this.applicantNo = applicantNo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getOtReason() {
		return otReason;
	}

	public void setOtReason(String otReason) {
		this.otReason = otReason;
	}

	public Date getPlanBgDtime() {
		return planBgDtime;
	}

	public void setPlanBgDtime(Date planBgDtime) {
		this.planBgDtime = planBgDtime;
	}

	public Date getPlanEdDtime() {
		return planEdDtime;
	}

	public void setPlanEdDtime(Date planEdDtime) {
		this.planEdDtime = planEdDtime;
	}

	public Double getPlanOtCnt() {
		return planOtCnt;
	}

	public void setPlanOtCnt(Double planOtCnt) {
		this.planOtCnt = planOtCnt;
	}

	public Date getRealBgDtime() {
		return realBgDtime;
	}

	public void setRealBgDtime(Date realBgDtime) {
		this.realBgDtime = realBgDtime;
	}

	public Date getRealEdDtime() {
		return realEdDtime;
	}

	public void setRealEdDtime(Date realEdDtime) {
		this.realEdDtime = realEdDtime;
	}

	public Double getRealOtCnt() {
		return realOtCnt;
	}

	public void setRealOtCnt(Double realOtCnt) {
		this.realOtCnt = realOtCnt;
	}

	public Integer getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(Integer procStatus) {
		this.procStatus = procStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getProcStatusName() {
		return procStatusName;
	}

	public void setProcStatusName(String procStatusName) {
		this.procStatusName = procStatusName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}

	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getFormKeyJson() {
		return formKeyJson;
	}

	public void setFormKeyJson(String formKeyJson) {
		this.formKeyJson = formKeyJson;
	}

	public String getAssistant() {
		return assistant;
	}

	public void setAssistant(String assistant) {
		this.assistant = assistant;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public String getDefinitionKey() {
		return definitionKey;
	}

	public void setDefinitionKey(String definitionKey) {
		this.definitionKey = definitionKey;
	}
}
