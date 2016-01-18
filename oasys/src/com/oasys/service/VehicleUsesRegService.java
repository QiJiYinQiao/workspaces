package com.oasys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.oasys.util.PageUtil;
import com.oasys.viewModel.VehicleExpensesAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;

public interface VehicleUsesRegService {
//	//展示数据
//	List<VehicleUsesRegModel> getList(Map<String, Object> map,PageUtil pageUtil);
//	//添加记录
//	boolean addCarInfo(VehicleUsesReg vrm);
//	//删除记录
//	boolean delCarInfo(String id);
//	//获取总条数
//	Long getCount(Map<String, Object> map);
	
	
	//展示数据
	List<VehicleExpensesAppModel> getList(Map<String, Object> map,PageUtil pageUtil,Integer id,String sql);
	//总数
	Long getCount(Map<String, Object> map);
	//新增记录,返回申请编号,用于上传附件
	String addVehicleExpenses(VehicleExpensesAppModel veam);
	//删除申请
	boolean delVehicleExpenses(Integer id);
	//提交申请
	String submitVehicleExpensesApply(Integer id);
	//修改流程状态
	void updateStatus(Integer id,String status);
	//修改申请状态
	void updateProcStatus(Integer id,String status);
	//查看流程图
	void getDiagramResourceByCaId(HttpServletResponse response,Integer veaId);
	//获取任务列表
	List<VehicleExpensesAppModel> getTaskByGroup(int firstResult,int maxResults,WorkFlowTasksModel workFlowTasksModel,Map<String, Object> map);
	//待办任务统计
	Long getTaskCountByGroup(WorkFlowTasksModel workFlowTasksModel,Map<String, Object> map);
	//受理任务
	String saveSubmitTask(WorkFlowTasksModel taskModel);
	//批量受理任务
	String saveSubmitTaskBatch(WorkFlowTasksModel taskModel);
}
