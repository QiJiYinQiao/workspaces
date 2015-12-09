package com.oasys.service;

import java.util.List;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.LoanApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;


/**
 * 工牌申请流程
 * @author Administrator
 *
 */
public interface LoanTaskService 
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
	String addLoanStartProcessInstance(Integer btaId);
	/**
	 * 查询借款申请任务列表
	 * @Title: findNotTaskClimList 
	 * @Description: TODO
	 * @param @param loanApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<LoanApp>
	 * @date 2015年11月27日 下午1:59:54
	 * @throws
	 */
	List<LoanApp> findNotTaskClimList(LoanApp loanApp,PageUtil pageUtil);
	/**
	 * 查询借款申请的任务数量
	 * @Title: findTotal 
	 * @Description: TODO
	 * @param @param loanApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年11月27日 下午2:05:39
	 * @throws
	 */
	Long findTotal(LoanApp loanApp);
	/**
	 * 个人领取任务
	 * @Title: getTaskUserClaim 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @throws ActivitiTaskAlreadyClaimedException
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月27日 下午2:11:15
	 * @throws
	 */
	void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException;
	/**
	 * 办理任务
	 * @Title: saveSubmitTask 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @return
	 * @param @throws Exception
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月27日 下午2:14:59
	 * @throws
	 */
	String saveSubmitTask(WorkFlowTasksModel taskModel) throws Exception;
	

	
}
