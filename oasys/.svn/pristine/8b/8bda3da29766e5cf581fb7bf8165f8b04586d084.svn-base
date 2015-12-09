package com.oasys.viewModel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class BadgeAppModel implements Serializable,Cloneable{

	private static final long serialVersionUID = 1L;
	/**
	 * 工牌申请id
	 */
	private Integer pnrId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 登记日期
	 */
	private Date regDatetime;
	/**
	 * 申请日期
	 */
	private String appDate;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 申请人id
	 */
	private Integer applicantNo;
	/**
	 * 申请人英文名字
	 */
	private String namePinyin;
	//部门名称
	private String fullName;
	//申请人名字
	private String name;
	//职位名字
	private String positionName;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 登记人id
	 */
	private Integer registrantNo;
	/**
	 * 登记人姓名
	 */
	private String registrantNanme;
	/**
	 * 部门id
	 */
	private Integer deptNo;
	/**
	 * 判断财富端，借款端
	 */
	private String myId;
	
	/** WorkFlow propertites */
	private WorkFlowTasksModel taskModel;//task对象
	private String result;//审批通过或驳回标识
	private String taskId;//任务taskId
	private String formKey;//跳转对应的受理页面JSP配置(在流程图的formKey中设置)
	private String assistant;//指定的候选人
	private String isSuccess;//是否通过标识
	private String ideaRemark;//审批意见
	
	public BadgeAppModel() {
		super();
	}



	public BadgeAppModel(Integer pnrId, String appNo, Date regDatetime,
			String appDate, String appStatus, String procStatus,
			Integer applicantNo, String namePinyin, String fullName,
			String name, String positionName, String remark,
			Integer registrantNo, Integer deptNo, WorkFlowTasksModel taskModel,
			String result, String taskId, String formKey, String assistant,String myId,
			String isSuccess, String ideaRemark,String registrantNanme) {
		super();
		this.pnrId = pnrId;
		this.appNo = appNo;
		this.regDatetime = regDatetime;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.procStatus = procStatus;
		this.applicantNo = applicantNo;
		this.namePinyin = namePinyin;
		this.fullName = fullName;
		this.name = name;
		this.positionName = positionName;
		this.remark = remark;
		this.registrantNo = registrantNo;
		this.deptNo = deptNo;
		this.taskModel = taskModel;
		this.result = result;
		this.taskId = taskId;
		this.formKey = formKey;
		this.assistant = assistant;
		this.myId=myId;
		this.isSuccess = isSuccess;
		this.ideaRemark = ideaRemark;
		this.registrantNanme=registrantNanme;
	}





	public String getRegistrantNanme() {
		return registrantNanme;
	}



	public void setRegistrantNanme(String registrantNanme) {
		this.registrantNanme = registrantNanme;
	}



	public String getMyId() {
		return myId;
	}



	public void setMyId(String myId) {
		this.myId = myId;
	}



	public Integer getDeptNo() {
		return deptNo;
	}



	public void setDeptNo(Integer deptNo) {
		this.deptNo = deptNo;
	}



	public Integer getRegistrantNo() {
		return registrantNo;
	}





	public void setRegistrantNo(Integer registrantNo) {
		this.registrantNo = registrantNo;
	}





	public String getIdeaRemark() {
		return ideaRemark;
	}



	public void setIdeaRemark(String ideaRemark) {
		this.ideaRemark = ideaRemark;
	}



	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}



	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}



	public String getResult() {
		return result;
	}



	public void setResult(String result) {
		this.result = result;
	}



	public String getTaskId() {
		return taskId;
	}



	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}



	public String getFormKey() {
		return formKey;
	}



	public void setFormKey(String formKey) {
		this.formKey = formKey;
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



	public Date getRegDatetime() {
		return regDatetime;
	}


	public void setRegDatetime(Date regDatetime) {
		this.regDatetime = regDatetime;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getProcStatus() {
		return procStatus;
	}

	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}

	public Integer getPnrId() {
		return pnrId;
	}
	public void setPnrId(Integer pnrId) {
		this.pnrId = pnrId;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	
	public Integer getApplicantNo() {
		return applicantNo;
	}
	public void setApplicantNo(Integer applicantNo) {
		this.applicantNo = applicantNo;
	}
	public String getNamePinyin() {
		return namePinyin;
	}
	public void setNamePinyin(String namePinyin) {
		this.namePinyin = namePinyin;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
