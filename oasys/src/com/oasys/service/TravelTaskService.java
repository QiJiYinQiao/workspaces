package com.oasys.service;

import java.util.List;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.TravelExpensesApp;
import com.oasys.model.TravelExpensesOther;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;


/**
 * 差旅报销申请流程
 * @author Administrator
 *
 */
public interface TravelTaskService 
{

	/**
	 * 提交申请
	 * @Title: addTravelTask 
	 * @Description: TODO
	 * @param @param teaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 下午7:08:29
	 * @throws
	 */
	String addTravelTask(Integer teaId);
	
	/**
	 * 查询待办任务列表
	 * @Title: findTravelTaskList 
	 * @Description: TODO
	 * @param @param travelExpensesApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<TravelExpensesOther>
	 * @date 2015年11月17日 下午7:09:56
	 * @throws
	 */
	List<TravelExpensesApp> findTravelTaskList(TravelExpensesApp travelExpensesApp ,PageUtil pageUtil);
	
	/**
	 *查询任务列表数据
	 * @Title: findTravelTaskCount 
	 * @Description: TODO
	 * @param @param travelExpensesApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年11月17日 下午7:39:21
	 * @throws
	 */
	Long findTravelTaskCount(TravelExpensesApp travelExpensesApp);
	
	/**
	 * 个人领取任务
	 * @Title: getUserTravelTask 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @throws ActivitiTaskAlreadyClaimedException
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月17日 下午7:45:35
	 * @throws
	 */
	void getUserTravelTask(String taskId) throws ActivitiTaskAlreadyClaimedException;
	
	/**
	 * 办理个人任务
	 * @Title: saveSubmitTask 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月17日 下午7:48:48
	 * @throws
	 */
	String saveSubmitTask(WorkFlowTasksModel taskModel);

	
	
}
