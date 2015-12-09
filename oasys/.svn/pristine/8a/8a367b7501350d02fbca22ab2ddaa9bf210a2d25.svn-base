package com.oasys.viewModel;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate(true)
@DynamicInsert(true)
public class CardAppVO implements Cloneable{
	private Integer caID;//主键ID
	private String appNo;//申请编号
	private Integer registrantNO;//登记人
	private Date regDeteTime;//登记日期
	private Integer applicantNo;//申请人
	private String position;//申请人职位
	private Integer deptNo;//所属部门
	private Date appDate;//申请日期
	private String appStatus;//申请状态
	private Integer procStatus;//流程状态
	private String procStatusInfo;//流程状态名称
	private String taskId;//任务Id
	private String userName;//申请人姓名
	private String deptName;//部门名称
	private String positionName;//职位名称
	private String personalTel;//个人电话
	private String officeTel;//办公电话
	private String email;//邮箱
	private String branchAddr;//分公司地址
	private Integer appQty;//申请数量
	private String comUrl;//网址
	private String remark;//备注
	private Long sumAppQty;//总数
	/**
	 * task对象
	 */
	private WorkFlowTasksModel taskModel;
	
	
	public CardAppVO() {
	}
	
	public CardAppVO(Integer caID, String appNo, Integer registrantNO,
			Date regDeteTime, Integer applicantNo, String position,
			Integer deptNo, Date appDate, String appStatus, Integer procStatus,
			String procStatusInfo, String taskId, String userName,
			String deptName, String positionName, String personalTel,
			String officeTel, String email, String branchAddr, Integer appQty,
			String comUrl, String remark, Long sumAppQty) {
		this.caID = caID;
		this.appNo = appNo;
		this.registrantNO = registrantNO;
		this.regDeteTime = regDeteTime;
		this.applicantNo = applicantNo;
		this.position = position;
		this.deptNo = deptNo;
		this.appDate = appDate;
		this.appStatus = appStatus;
		this.procStatus = procStatus;
		this.procStatusInfo = procStatusInfo;
		this.taskId = taskId;
		this.userName = userName;
		this.deptName = deptName;
		this.positionName = positionName;
		this.personalTel = personalTel;
		this.officeTel = officeTel;
		this.email = email;
		this.branchAddr = branchAddr;
		this.appQty = appQty;
		this.comUrl = comUrl;
		this.remark = remark;
		this.sumAppQty = sumAppQty;
	}




	public Integer getCaID() {
		return caID;
	}


	public void setCaID(Integer caID) {
		this.caID = caID;
	}


	public String getAppNo() {
		return appNo;
	}


	public void setAppNO(String appNo) {
		this.appNo = appNo;
	}


	public Integer getRegistrantNO() {
		return registrantNO;
	}


	public void setRegistrantNO(Integer registrantNO) {
		this.registrantNO = registrantNO;
	}


	public Date getRegDeteTime() {
		return regDeteTime;
	}


	public void setRegDeteTime(Date regDeteTime) {
		this.regDeteTime = regDeteTime;
	}


	public Integer getApplicantNo() {
		return applicantNo;
	}


	public void setApplicantNo(Integer applicantNo) {
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


	public Date getAppDate() {
		return appDate;
	}


	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}


	public String getAppStatus() {
		return appStatus;
	}


	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}


	public Integer getAppQty() {
		return appQty;
	}


	public void setAppQty(Integer appQty) {
		this.appQty = appQty;
	}


	public Integer getProcStatus() {
		return procStatus;
	}


	public void setProcStatus(Integer procStatus) {
		this.procStatus = procStatus;
	}


	public String getTaskId() {
		return taskId;
	}


	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}


	public String getPositionName() {
		return positionName;
	}


	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}


	public String getPersonalTel() {
		return personalTel;
	}


	public void setPersonalTel(String personalTel) {
		this.personalTel = personalTel;
	}


	public String getOfficeTel() {
		return officeTel;
	}


	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getBranchAddr() {
		return branchAddr;
	}


	public void setBranchAddr(String branchAddr) {
		this.branchAddr = branchAddr;
	}

	public String getComUrl() {
		return comUrl;
	}


	public void setComUrl(String comUrl) {
		this.comUrl = comUrl;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSumAppQty() {
		return sumAppQty;
	}


	public void setSumAppQty(Long sumAppQty) {
		this.sumAppQty = sumAppQty;
	}

	public String getProcStatusInfo() {
		return procStatusInfo;
	}

	public void setProcStatusInfo(String procStatusInfo) {
		this.procStatusInfo = procStatusInfo;
	}

	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}

	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
