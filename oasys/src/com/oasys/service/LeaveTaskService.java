package com.oasys.service;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.BadgeApp;
import com.oasys.model.LeaveApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.BadgeAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 工牌申请流程
 * @author Administrator
 *
 */
public interface LeaveTaskService 
{

	
	/**
	 * 开启休假申请流程
	 * @Title: addLeaveStartProcessInstance 
	 * @Description: TODO
	 * @param @param leaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月27日 上午11:04:15
	 * @throws
	 */
	String addLeaveStartProcessInstance(Integer leaId);
	/**
	 * 查询待办任务列表
	 * @Title: findNotTaskClimList 
	 * @Description: TODO
	 * @param @param leaveApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<LeaveApp>
	 * @date 2015年10月27日 上午11:04:32
	 * @throws
	 */
	List<LeaveApp> findNotTaskClimList(LeaveApp leaveApp,PageUtil pageUtil);
	/**
	 * 查看休假申请代办任务数量
	 * @Title: findTotal 
	 * @Description: TODO
	 * @param @param leaveApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年10月27日 上午11:04:54
	 * @throws
	 */
	Long findTotal(LeaveApp leaveApp);
	/**
	 * 领取任务
	 * @Title: getTaskUserClaim 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年9月24日 上午9:55:27
	 * @throws
	 */
	void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException;
	
	/**
	 * 通用方法办理任务
	 * @Title: saveSubmitTask 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @return
	 * @param @throws Exception
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月4日 上午9:53:03
	 * @throws
	 */
	String saveSubmitTask(WorkFlowTasksModel taskModel,String realBgDtime,String realEdDtime) throws Exception;
	
	/**
	 * 批量处理任务
	 * @Title: addMangeTaksList 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月17日 下午4:07:16
	 * @throws
	 */
	String addMangeTaksList(WorkFlowTasksModel taskModel);
	
	

	
}
