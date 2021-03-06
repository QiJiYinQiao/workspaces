package com.oasys.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.CardApp;
import com.oasys.model.CardAppAttach;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.CardAppVO;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.WorkFlowTasksModel;

public interface CardApplyService {
	//展示数据
	List<CardAppVO> getList(Map<String, Object> map,PageUtil pageUtil,Integer id,String sql);
	//删除数据
	boolean delCard(Integer id);
	//提交申请
	String submitCardApply(Integer id);
	//修改流程状态
	void updateStatus(Integer id,String status);
	//修改申请状态
	void updateApplyStatus(Integer id,String status);
	//获取任务列表
	List<CardAppVO> getTaskByGroup(Map<String, Object> map,int firstResult,int maxResults,WorkFlowTasksModel workFlowTasksModel);
	//获取任务总条数
	Long getTaskTotalByGroup(Map<String, Object> map,WorkFlowTasksModel workFlowTasksModel);
	//获取总条数
	Long getCount(Map<String, Object> map,Integer id);
	//获取部门Id及职位Id
	Map<String, Object> getDeptNo(String name);
	//查看流程图
	void getDiagramResourceByCaId(HttpServletResponse response,Integer caId);
	//更新名片申请数量
	BigDecimal updateCountByAppQty(String appNo);
	//保存名片申请信息
	CardApp saveCardAttach(CardAppAttach cardAppAttach,String price);
	//根据申请编号获取Id
	CardApp findCardAppNo(String appNo);
	//花去名片申请条数
	Long findCardAtttotal(String appNo, String deptNo);
	//获取申请的名片信息
	List<CardAppAttach>findCardAttList(String appNo,String deptNo,PageUtil pageUtil);
	//删除选中的名片申请信息
	boolean delCardApply(String ids);
	//办理任务
	String saveSubmitTask(WorkFlowTasksModel taskModel);
	//根据部门编号查询该部门满足入职满一个月的用户
	List<ComboBoxModel> getUserByDeptNo(Integer deptNo);
	//批量受理任务
	String saveSubmitTaskBatch(WorkFlowTasksModel taskModel);
	CardApp getCardAppById(Integer caId);
}
