package com.oasys.model.VO;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.oasys.model.StaffRecruitAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;


public class StaffRecruitAppVOModel extends StaffRecruitAppModel implements java.io.Serializable, Cloneable {

	private static final long serialVersionUID = 8441968158213202811L;
		
	private Integer deptNo2;      //所属部门，记录当前申请人的所属部门编号
	private Date appDateStart;    //申请日期01(日期比较的开始日期)	
	private Date appDateEnd;      //申请日期02(日期比较的结束日期)
	
	//==================================================	
	//===============封装当前登录用户相关的信息==================
	private String deptName;     //存放当前登陆用户所属的部门名称
	private String curLoginUserName;     //存放当前登陆用户的名称
	//==================================================	
	
	//==================================================
	//================= 封装流程的相关信息 ====================
	//==================================================
	private String appStatus2;       //申请状态编号，比如ref_id=155,对应“营业部经理通过”。
	private String appStatusName;    //申请状态名称，比如营业部经理通过
	
	private WorkFlowTasksModel taskModel;    //taskModel
	private String taskId;                   //taskId
	private String formKey;                  //保存流程图中的JSP路径。		
	
	public Integer getDeptNo2() {
		return deptNo2;
	}

	public void setDeptNo2(Integer deptNo2) {
		this.deptNo2 = deptNo2;
	}

	public String getAppStatus2() {
		return appStatus2;
	}

	public void setAppStatus2(String appStatus2) {
		this.appStatus2 = appStatus2;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getAppDateStart() {
		return appDateStart;
	}


	public void setAppDateStart(Date appDateStart) {
		this.appDateStart = appDateStart;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date getAppDateEnd() {
		return appDateEnd;
	}


	public void setAppDateEnd(Date appDateEnd) {
		this.appDateEnd = appDateEnd;
	}

	public String getDeptName() {
		return deptName;
	}


	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getCurLoginUserName() {
		return curLoginUserName;
	}


	public void setCurLoginUserName(String curLoginUserName) {
		this.curLoginUserName = curLoginUserName;
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


	public String getFormKey() {
		return formKey;
	}


	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}


	public String getAppStatusName() {
		return appStatusName;
	}

	public void setAppStatusName(String appStatusName) {
		this.appStatusName = appStatusName;
	}

	@Override
	public Object clone(){
		try {
			return super.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
		
}
