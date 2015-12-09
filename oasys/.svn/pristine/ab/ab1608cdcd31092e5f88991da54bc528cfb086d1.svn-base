package com.oasys.service;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.UseStampApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.UsersModel;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 用章申请
 * @author Administrator
 *
 */
public interface UseStampAppService extends WorkFlowBaseService
{	
	/**
	 * 查询用章申请列表
	 * @Title: findUseStampAppAttList 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<UseStampApp>
	 * @date 2015年10月10日 下午4:05:58
	 * @throws
	 */
	List<UseStampApp> findUseStampAppAttList(UseStampApp  useStampApp,PageUtil pageUtil);
	
	/**
	 * 查询用章申请数量
	 * @Title: getTotal 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年10月16日 上午9:21:12
	 * @throws
	 */
	Long getTotal(UseStampApp  useStampApp);
	
	/**
	 * 添加时申请人信息
	 * @Title: findFullAppName 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return UsersModel
	 * @date 2015年10月12日 下午3:26:15
	 * @throws
	 */
	UsersModel findFullAppName();
	
	/**
	 * 保存用章申请
	 * @Title: saveUseStamp 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月12日 下午3:03:12
	 * @throws
	 */
	boolean saveUseStamp(UseStampApp useStampApp);
	
	/**
	 * 根据用章申请id删除申请
	 * @Title: deleteUseStamp 
	 * @Description: TODO
	 * @param @param usaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月12日 下午4:34:00
	 * @throws
	 */
	boolean deleteUseStamp(Integer usaId);
	
	/**
	 * 根据申请id查询用章社情
	 * @Title: finduseStampAppByUsaId 
	 * @Description: TODO
	 * @param @param usaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return UseStampApp
	 * @date 2015年10月12日 下午5:44:11
	 * @throws
	 */
	UseStampApp finduseStampAppByUsaId(Integer usaId);
	
	/**
	 * 更改流程状态
	 * @Title: upBadgeProcStatus 
	 * @Description: TODO
	 * @param @param usaId
	 * @param @param procStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年10月12日 下午5:48:07
	 * @throws
	 */
	void updateProcStatus(Integer usaId,String procStatus);
	
	/**
	 * 更改申请状态
	 * @Title: upBadgeAppStatus 
	 * @Description: TODO
	 * @param @param usaID
	 * @param @param appStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年10月12日 下午5:51:08
	 * @throws
	 */
	void updateAppStatus(Integer usaID,String appStatus);
	
			//-----------------------------------      用章申请任务办理         ---------------------------=
	
	
	/**
	 * 提交用章申请任务
	 * @Title: addUseStampAppTask 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月13日 上午9:18:51
	 * @throws
	 */
	String addUseStampAppTask(Integer usaId);
	
	/**
	 * 查看流程图片
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param pnrId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年10月13日 上午9:26:27
	 * @throws
	 */
	void getDiagramResourceByPaId(HttpServletResponse response,
			Integer usaId);
	
	/**
	 * 查看个人任务办理
	 * @Title: findUseStampAppClimTask 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<UseStampApp>
	 * @date 2015年10月13日 上午10:03:22
	 * @throws
	 */
	List<UseStampApp> findUseStampAppClimTask(UseStampApp  useStampApp,PageUtil pageUtil);
	
	/**
	 * 个人任务办理数量
	 * @Title: getTaskTotal 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年10月16日 上午10:10:32
	 * @throws
	 */
	Long getTaskTotal(UseStampApp  useStampApp);
	
	
	/**
	 * 个人领取任务
	 * @Title: getTaskUserClaim 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @throws ActivitiTaskAlreadyClaimedException
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年10月13日 上午10:28:10
	 * @throws
	 */
	void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException;
	
	/**
	 * 办理个人任务
	 * @Title: setUseStampTask 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年10月13日 上午10:45:05
	 * @throws
	 */
	String setUseStampTask(WorkFlowTasksModel taskModel) throws Exception;
	
	
	
	
	
	
}
