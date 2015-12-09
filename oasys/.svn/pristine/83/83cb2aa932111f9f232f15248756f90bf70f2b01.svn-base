package com.oasys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.AuditProcHis;
import com.oasys.model.OvertimeApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.CardAppVO;
import com.oasys.viewModel.OvertimeAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;

public interface OvertimeAppService {
	//展示数据
	List<OvertimeAppModel> getList(Map<String, Object> map,PageUtil pageUtil,Integer id);
	//统计总数
	Long getCount(Map<String, Object> map,Integer id);
	//添加数据
	boolean saveOvertime(OvertimeApp overtimeApp);
	//删除数据
	boolean delOvertime(Integer oveId);
	//更改流程状态
	void updateProcStatus(Integer id,String status);
	//开启流程
	String submitCardApply(Integer id);
	//查看待办任务
	List<OvertimeAppModel> getTaskByGroup(int firstResult,int maxResults,WorkFlowTasksModel workFlowTasksModel1);
	//查看流程图
	void getDiagramResourceByCaId(HttpServletResponse response,Integer oveId);
	//更新申请状态
	void updateApplyStatus(Integer id,String result);
	//办理任务
	String saveSubmitTask(WorkFlowTasksModel taskModel);
	//确认加班申请
	boolean saveSubmitConfirmOvertimeApp(String taskId,String result, AuditProcHis auditProcHis,String oveId);
}
