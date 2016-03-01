package com.bpms.service;

import java.io.IOException;
import java.util.List;

import org.activiti.engine.task.Task;

import com.bpms.model.InvestOrder;
import com.bpms.model.Users;

/**
 * @ClassName: InvestRedeemWorkFlowService
 * @Description: 投资流程ServiceImpl
 * @author ZHANGJIAN
 * @date 2015年12月16日 下午4:34:36
 */
public interface InvestRedeemWorkFlowService {
	
	/**
	 * 启动投资赎回的流程
	 * @Title: saveStartProcess4InvestRedeem 
	 * @author ZHANGJIAN
	 * @return boolean
	 * @date 2015年12月16日 下午4:34:36
	 */
	public boolean saveStartProcess4InvestRedeem(InvestOrder investOrder);

	/**
	 * 根据investOrderId，获取投资赎回的流程图
	 * @Title: checkWorkFlowImg4InvestRedeemByInvestOrderId 
	 * @param investOrderId
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2015年12月17日 下午1:24:10
	 * @throws
	 */
	public void checkWorkFlowImg4InvestRedeemByInvestOrderId(String investOrderId)  throws IOException;

	/**
	 * 获取当前登录用户所属角色中，所有未被签收的任务列表 
	 * @Title: findAllUnclaimedTaskList 
	 * @Description: TODO 查询所有的待办任务
	 * @param @param firstResult 起始
	 * @param @param maxResults 每页总条数
	 * @param @return
	 * @return List<InvestOrder>
	 * @throws
	 */
	public List<InvestOrder> findAllUnclaimedTaskList(Integer firstResult, Integer maxResults);	
	
	/**
	 * 统计当前登录用户所属角色中，所有没有被签收的任务的个数
	 * @Title: findAllUnclaimedTaskCount 
	 * @Description: TODO 总条数
	 * @param @param taskId
	 * @param @return
	 * @return Long
	 * @throws
	 */
	public Long findAllUnclaimedTaskCount();

	/**
	 * 当前角色的用户签收任务
	 * @Title: pickMyTask 
	 * @Description: TODO
	 * @param @param taskId
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2015年12月17日 下午2:56:07
	 * @throws
	 */
	public void pickMyTask(String taskId);	
	
	
	/**
	 * 获取当前角色的用户已经受理的投资订单investOrder的"总数量"
	 * 	@param firstResult
	 * 	@param maxResults
	 * @return List<InvestOrder>
	 */	
	public List<InvestOrder> findAllClaimedTask(Integer firstResult, Integer maxResults);	
	
	/**
	 * 获取当前角色的用户已经受理的投资订单investOrder的"总数量"
	 * @Title: findAllClaimedTaskCount 
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @author ZHANGJIAN
	 * @return Long
	 * @date 2015年12月17日 下午3:01:14
	 * @throws
	 */
	public Long findAllClaimedTaskCount(String userId);
	
	/**
	 * 根据任务的ID,获得当前的角色(GROUP)，最终获取该角色下，所有的任务受理人列表
	 * @Title: getIdentityLinkByTaskId 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @return
	 * @author ZHANGJIAN
	 * @return List<Users>
	 * @date 2015年12月17日 下午3:00:42
	 * @throws
	 */
	public List<Users> getIdentityLinkByTaskId(String taskId);
	
	/**
	 * @Title: findInvestOrderByTaskObj 
	 * @Description: TODO 根据Task对象查询订单
	 * @param Task Obj
	 * @return InvestOrder
	 * @throws
	 */	
	public InvestOrder findInvestOrderByTaskObj(Task task);

	/**
	 * 办理任务（完成任务）
	 * @Title: saveSubmitTask 
	 * @Description: TODO
	 * @param @param investOrder
	 * @param @param comment
	 * @param @param result
	 * @param @param processingResult
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2015年12月18日 下午2:51:11
	 * @throws
	 */
	public void saveSubmitTask(InvestOrder investOrder, String comment,
			String result, String processingResult); 
}
