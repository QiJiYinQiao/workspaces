package com.oasys.model.VO;

import java.util.Date;

import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 忘打卡申请vo
 * @ClassName: EmpForgetPluginAppModel 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年11月6日 下午2:02:56
 */
public class EmpForgetPluginAppModel implements java.io.Serializable,Cloneable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private Integer fpaId;
	/**
	 * 申请编号
	 */
	private String appNo;
	/**
	 * 申请人id
	 */
	private Integer applicantNo;
	/**
	 * 部门id
	 */
	private Integer deptNo;
	/**
	 * 申请时间
	 */
	private Date appDtime;
	/**
	 * 申请状态
	 */
	private String appStatus;
	/**
	 * 忘打卡事由
	 */
	private String forgetPluginReason;
	/**
	 * 流程状态
	 */
	private String procStatus;
	/**
	 * 备注信息
	 */
	private String remark;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 申请状态名称
	 */
	private String appStatusName;
	/**
	 * 最小日期
	 */
	private String appDateMini;
	/**
	 * 最大日期
	 */
	private String appDateMax;
	/**
	 * 
	 */
	private WorkFlowTasksModel taskModel;
	/**
	 * 任务id
	 */
	private String taskId;
	/**
	 * id字符串
	 */
	private String ids;
	
	public EmpForgetPluginAppModel() {
		super();
	}
	
	public EmpForgetPluginAppModel(String ids) {
		super();
		this.ids = ids;
	}
	
	public Integer getFpaId() {
		return fpaId;
	}
	public void setFpaId(Integer fpaId) {
		this.fpaId = fpaId;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
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
	public Date getAppDtime() {
		return appDtime;
	}
	public void setAppDtime(Date appDtime) {
		this.appDtime = appDtime;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public String getForgetPluginReason() {
		return forgetPluginReason;
	}
	public void setForgetPluginReason(String forgetPluginReason) {
		this.forgetPluginReason = forgetPluginReason;
	}
	public String getProcStatus() {
		return procStatus;
	}
	public void setProcStatus(String procStatus) {
		this.procStatus = procStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAppStatusName() {
		return appStatusName;
	}
	public void setAppStatusName(String appStatusName) {
		this.appStatusName = appStatusName;
	}
	public String getAppDateMini() {
		return appDateMini;
	}
	public void setAppDateMini(String appDateMini) {
		this.appDateMini = appDateMini;
	}
	public String getAppDateMax() {
		return appDateMax;
	}
	public void setAppDateMax(String appDateMax) {
		this.appDateMax = appDateMax;
	}
	public WorkFlowTasksModel getTaskModel() {
		return taskModel;
	}
	public void setTaskModel(WorkFlowTasksModel taskModel) {
		this.taskModel = taskModel;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
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
