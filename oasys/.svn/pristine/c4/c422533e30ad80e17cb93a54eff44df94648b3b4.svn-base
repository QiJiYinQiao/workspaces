package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.UseStampApp;
import com.oasys.model.UseStampAppAttach;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;




/**
 * 用章申请任务
 * @Title: UseStampTaskService.java 
 * @Package com.oasys.service 
 * @Description: TODO
 * @author WANGXINCHENG  
 * @date 2015年12月15日 上午11:23:22 
 * @version V1.0
 */
public interface UseStampTaskService 
{	
	/**
	 * 开启流程
	 * @Title: addStartProcessInstance 
	 * @Description: TODO
	 * @param @param usaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 上午11:26:07
	 * @throws
	 */
	String addStartProcessInstance(Integer usaId);
	/**
	 * 根据条件查询待办任务
	 * @Title: findNotClimTask 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<UseStampApp>
	 * @date 2015年12月15日 下午3:26:39
	 * @throws
	 */
	List<UseStampApp> findNotClimTask(UseStampApp useStampApp ,PageUtil pageUtil);
	/**
	 * 根据条件查询待办任务数量
	 * @Title: findNotClimTaakCount 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年12月15日 下午4:04:49
	 * @throws
	 */
	Long findNotClimTaakCount(UseStampApp useStampApp);
	/**
	 * 领取个人任务
	 * @Title: getTaskUserClaim 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @throws ActivitiTaskAlreadyClaimedException
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月15日 下午4:22:20
	 * @throws
	 */
	void getTaskUserClaim(String taskID) throws ActivitiTaskAlreadyClaimedException;
	
	/**
	 * 任务办理
	 * @Title: addUserTask 
	 * @Description: TODO
	 * @param @param taskModel 任务办理对象
	 * @param @param turnover 判断是否移交
	 * @param @param useStampAppAttach 归还时封装的对象
	 * @param @return
	 * @param @throws Exception
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 下午4:51:05
	 * @throws
	 */
	//String addUserTask(WorkFlowTasksModel taskModel, String turnover,String receiverDtime,String emsNo,String postAddr,String contactWay,Integer saaId) throws Exception;
	String addUserTask(WorkFlowTasksModel taskModel, String turnoverDtime,UseStampAppAttach stampAppAttach) throws Exception;
	/**
	 * 批量办理任务
	 * @Title: addMangeTaksList 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月16日 下午3:00:55
	 * @throws
	 */
	String  addMangeTaksList(WorkFlowTasksModel taskModel);
	
}
