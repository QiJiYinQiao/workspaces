package com.oasys.model.VO;

/**
 * 
 * @ClassName: AuditProcHisModel
 * @Description: TODO 申请履历表VO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年9月29日 下午7:34:30
 *
 */
public class AuditProcHisModel implements java.io.Serializable {
	// 申请编号
	private String appNO;
	// 申请状态
	private String appStatus;
	// 受理人姓名
	private String handlerName;
	// 受理人角色
	private String roleName;
	// 受理时间
	private String handleDate;
	// 处理结果
	private String handleResult;
	// 备注信息
	private String remark;
    //处理人
	private Integer handler;
	
	public String getAppNO() {
		return appNO;
	}

	public void setAppNO(String appNO) {
		this.appNO = appNO;
	}

	public String getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}

	public Integer getHandler() {
		return handler;
	}

	public void setHandler(Integer handler) {
		this.handler = handler;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getHandleDate() {
		return handleDate;
	}

	public void setHandleDate(String handleDate) {
		this.handleDate = handleDate;
	}

	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}
}
