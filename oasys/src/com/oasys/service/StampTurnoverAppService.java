package com.oasys.service;

import java.util.List;

import com.oasys.model.StampTurnoverApp;
import com.oasys.model.StampTurnoverAppAttach;
import com.oasys.model.StatusNameRef;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;



/**   
 * @Title: PPEScrapAppService
 * @Package com.oasys.service
 * @Description: 印章移交Service
 * @author lida  
 * @date 2015/9/16
 * @version V1.0   
 */
public interface StampTurnoverAppService {
	
	/** 
	 * @Title: findStampList
	 * @Description: 查询所有印章移交申请记录集合
	 * @param pageUtil 分页对象
	 * @param StampTurnover 页面绑定实体对象参数
	 * @author lida
	 * @return List<StampTurnoverAppAttach> 固定资产申请记录List
	 * @date 2015/9/16
	 */
	List<StampTurnoverAppAttach> findStampList(PageUtil pageUtil,StampTurnoverApp stampTurnover);
	
	/** 
	 * @Title: findStampListCount
	 * @Description: 	查询所有印章移交申请记录数量
	 * @param StampTurnover 页面绑定实体对象参数
	 * @author lida
	 * @return Long 固定资产申请记录总记录数
	 * @date 2015/9/16
	 */
	Long findStampListCount(StampTurnoverApp StampTurnover);
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 	新增印章移交申请单
	 * @param ppe 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/16
	 */
	void saveOrUpdatePpeEntity(StampTurnoverApp ppe);
	
	/**
	 * @Title: delStampTurnover
	 * @Description: 	根据申请编号删除印章移交记录
	 * @param appNo 申请编号
	 * 	@author lida
	 * @date 2015/9/16
	 */
	void delStampTurnover(String appNo);
	
	/**
	 * @Title: getStampTurnoverByID
	 * @Description: 	根据ID加载印章移交记录
	 * @param id id标识
	 * 	@author lida
	 * @return StampTurnoverApp 固定资产实体对象
	 * @date 2015/9/22
	 */
	StampTurnoverApp getStampTurnoverByID(Integer id);
	
	/**
	 * @Title: startProcessInstance
	 * @Description: 	开启流程
	 * @param id id标识
	 * 	@author lida
	 * @return boolean 消息提醒字符
	 * @date 2015/9/23
	 */
	String startProcessInstance(Integer id);
	
	/**
	 * @Title: updStampTurnoverProcessStatus
	 * @Description: 	更新流程状态字段
	 * @param id id标识
	 * @param status 更新的状态Code
	 * 	@author lida
	 * @date 2015/9/23
	 */
	void updStampTurnoverProcessStatus(Integer id,String status);
	
	/**
	 * @Title: updStampTurnoverAppStatus
	 * @Description: 更新申请状态字段
	 * @param id id标识
	 * @param status 更新的状态Code
	 * 	@author lida
	 * @date 2015/9/23 
	 */
	void updStampTurnoverAppStatus(Integer id,String status);
	
	/**
	 * @Title: findPPEListTask
	 * @Description: 查询印章移交任务列表
	 * @param id id标识
	 * @param status 更新的状态Code
	 * @return List<StampTurnoverAppAttach> 印章移交申请附加表List
	 * 	@author lida
	 * @date 2015/9/24 
	 */
	List<StampTurnoverAppAttach> findStampListTask(PageUtil pageUtil,StampTurnoverApp stampTurnover);
	
	/**
	 * @Title: findPPEListTaskCount
	 * @Description: 查询印章移交任务列表记录数量
     * @param StampTurnover 实体对象
	 * @return Long 记录数
	 * 	@author lida
	 * @date 2015/9/24 
	 */
	Long findStampListTaskCount(StampTurnoverApp stampTurnover);
	
	/**
	 * @Title: findPPEListTaskCount
	 * @Description: 查询印章移交任务列表记录数量
	 * @param StampTurnover 实体对象
	 * 	@author lida
	 * @date 2015/9/24
	 */
	public String saveSubmitTask(WorkFlowTasksModel taskModel);
	
	/**
	 * @Title: findAppStatusByID
	 * @Description: 根据状态ID查询印章移交状态
	 * @param appStatusID 状态ID
	 * 	@author lida
	 * @date 2015/10/9
	 */
	public StatusNameRef findAppStatusByID(String appStatusID);
}
