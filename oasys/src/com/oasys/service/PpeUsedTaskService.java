package com.oasys.service;

import java.util.List;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.PpeUsedConfirmApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.WorkFlowTasksModel;



/**
 * 固定资产使用申请任务
 * @author Administrator
 *
 */
public interface PpeUsedTaskService 
{	
	
	/**
	 * 提交申请
	 * @Title: addBadgeAppStartProcessInstance 
	 * @Description: TODO
	 * @param @param psaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 下午4:46:03
	 * @throws
	 */
	String addStartProcessInstance(Integer psaId);
	/**
	 * 查询待办任务列表
	 * @Title: findPpeUsedTaskList 
	 * @Description: TODO
	 * @param @param ppeUsedConfirmApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<PpeUsedConfirmApp>
	 * @date 2015年12月8日 上午10:25:53
	 * @throws
	 */
	List<PpeUsedConfirmApp> findPpeUsedTaskList(PpeUsedConfirmApp ppeUsedConfirmApp,PageUtil pageUtil);
	/**
	 * 查询为办理任务的总数量
	 * @Title: findPpeUsedTaskTotal 
	 * @Description: TODO
	 * @param @param ppeUsedConfirmApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年12月8日 下午7:33:59
	 * @throws
	 */
	Long findPpeUsedTaskTotal(PpeUsedConfirmApp ppeUsedConfirmApp);
	
	/**
	 * 个人领取任务
	 * @Title: getTaskUserClaim 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @throws ActivitiTaskAlreadyClaimedException
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月8日 上午10:57:05
	 * @throws
	 */
	void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException;
	
	/**
	 * 办理任务
	 * @Title: addUserTask 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @param attPsaId附加表id
	 * @param @param reverter归还人 id
	 * @param @return
	 * @param @throws Exception
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月8日 下午3:06:26
	 * @throws
	 */
	String addUserTask(WorkFlowTasksModel taskModel,Integer attPsaId,Integer reverter) throws Exception;
	
	
	
}
