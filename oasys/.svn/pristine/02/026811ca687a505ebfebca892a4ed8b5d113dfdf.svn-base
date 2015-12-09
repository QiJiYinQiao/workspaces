package com.oasys.service;

import java.text.ParseException;
import java.util.List;

import com.oasys.model.PPEScrapApp;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.StatusNameRef;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;



/**   
 * @Title: PPEScrapAppService
 * @Package com.oasys.service
 * @Description: 固定资产报废Service
 * @author lida  
 * @date 2015/9/16
 * @version V1.0   
 */
public interface PPEScrapAppService {
	
	/** 
	 * @Title: findPpeList
	 * @Description: 查询所有固定资产报废申请记录集合
	 * @param pageUtil 分页对象
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author lida
	 * @return List<PpeScrapAppAttach> 固定资产申请记录List
	 * @date 2015/9/16
	 */
	List<PpeScrapAppAttach> findPpeList(PageUtil pageUtil,PPEScrapApp ppeScrap) throws ParseException;
	
	/** 
	 * @Title: findPpeListCount
	 * @Description: 	查询所有固定资产报废申请记录数量
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author lida
	 * @return Long 固定资产申请记录总记录数
	 * @date 2015/9/16
	 */
	Long findPpeListCount(PPEScrapApp ppeScrap);
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 	新增固定资产报废申请单
	 * @param ppe 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/16
	 */
	void saveOrUpdatePpeEntity(PPEScrapApp ppe);
	
	/**
	 * @Title: delPpeScrap
	 * @Description: 	根据申请编号删除固定资产报废记录
	 * @param appNo 申请编号
	 * 	@author lida
	 * @date 2015/9/16
	 */
	void delPpeScrap(String appNo);
	
	/**
	 * @Title: getPpeScrapByID
	 * @Description: 	根据ID加载固定资产报废记录
	 * @param id id标识
	 * 	@author lida
	 * @return PPEScrapApp 固定资产实体对象
	 * @date 2015/9/22
	 */
	PPEScrapApp getPpeScrapByID(Integer id);
	
	/**
	 * @Title: startProcessInstance
	 * @Description: 	开启流程
	 * @param id id标识
	 * 	@author lida
	 * @return 消息提醒字符串
	 * @date 2015/9/23
	 */
	String startProcessInstance(Integer id);
	
	/**
	 * @Title: updPPEScrapProcessStatus
	 * @Description: 	更新流程状态字段
	 * @param id id标识
	 * @param status 更新的状态Code
	 * 	@author lida
	 * @date 2015/9/23
	 */
	void updPPEScrapProcessStatus(Integer id,String status);
	
	/**
	 * @Title: updPPEScrapAppStatus
	 * @Description: 更新申请状态字段
	 * @param id id标识
	 * @param status 更新的状态Code
	 * 	@author lida
	 * @date 2015/9/23 
	 */
	void updPPEScrapAppStatus(Integer id,String status);
	
	/**
	 * @Title: findPPEListTask
	 * @Description: 查询固定资产报废任务列表
	 * @param id id标识
	 * @param status 更新的状态Code
	 * @return List<PpeScrapAppAttach> 固定资产报废申请附加表List
	 * 	@author lida
	 * @date 2015/9/24 
	 */
	List<PpeScrapAppAttach> findPPEListTask(PageUtil pageUtil,PPEScrapApp ppescrap) throws ParseException ;
	
	/**
	 * @Title: findPPEListTaskCount
	 * @Description: 查询固定资产报废任务列表记录数量
     * @param ppeScrap 实体对象
	 * @return Long 记录数
	 * 	@author lida
	 * @date 2015/9/24 
	 */
	Long findPPEListTaskCount(PPEScrapApp ppescrap);
	
	/**
	 * @Title: findPPEListTaskCount
	 * @Description: 查询固定资产报废任务列表记录数量
	 * @param taskModel 工作流实体对象
	 * @return 消息提醒字符
	 * 	@author lida
	 * @date 2015/9/24
	 */
	public String saveSubmitTask(WorkFlowTasksModel taskModel);
	
	/**
	 * @Title: saveSubmitTask
	 * @Description: 批量受理任务方法
	 * @param taskModel 工作流实体对象
	 * 	@author lida
	 * @return 消息提醒字符串
	 * @date 2015/12/3
	 */
	public String saveSubmitTaskBatch(WorkFlowTasksModel taskModel);
	
	/**
	 * @Title: findAppStatusByID
	 * @Description: 根据状态ID查询固定资产报废状态
	 * @param appStatusID 状态ID
	 * 	@author lida
	 * @date 2015/10/9
	 */
	public StatusNameRef findAppStatusByID(String appStatusID);
}
