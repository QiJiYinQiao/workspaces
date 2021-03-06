package com.oasys.service;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;

import com.oasys.model.UseStampApp;
import com.oasys.model.UseStampAppAttach;
import com.oasys.service.workFlow.WorkFlowBaseService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
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
	 * 查询用章申请列表sql语句
	 * @Title: getUseStampListSQL 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return StringBuffer
	 * @date 2015年12月11日 下午3:56:11
	 * @throws
	 */
	StringBuffer getUseStampListSQL();
	
	/**
	 * 查询用章申请列表
	 * @Title: findUseStampList 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<UseStampApp>
	 * @date 2015年12月11日 下午4:05:23
	 * @throws
	 */
	List<UseStampApp> findUseStampList(UseStampApp useStampApp,PageUtil pageUtil);
	/**
	 * 查询用章申请总条数
	 * @Title: findUseStampCount 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年12月11日 下午5:10:59
	 * @throws
	 */
	Long findUseStampCount(UseStampApp useStampApp);
	/**
	 * 保存或更新用章申请主表
	 * @Title: saveOrUpdStampApp 
	 * @Description: TODO
	 * @param @param useStampApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return UseStampApp
	 * @date 2015年12月14日 上午10:20:18
	 * @throws
	 */
	UseStampApp saveOrUpdStampApp(UseStampApp useStampApp);
	/**
	 * 根据申请编号删除申请
	 * @Title: deleteuseStampApp 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年12月14日 上午11:14:06
	 * @throws
	 */
	boolean deleteuseStampApp(String appNo);
	/**
	 * 更改申请状态
	 * @Title: updateAppStatus 
	 * @Description: TODO
	 * @param @param usaId
	 * @param @param appStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月15日 上午9:57:14
	 * @throws
	 */
	void updateAppStatus(Integer usaId,String appStatus);
	/**
	 * 更改流程状态
	 * @Title: updateProcStatus 
	 * @Description: TODO
	 * @param @param usaId
	 * @param @param procStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月15日 上午9:57:53
	 * @throws
	 */
	void updateProcStatus(Integer usaId,String procStatus);
	/**
	 * 根据申请id判断申请人总部分部
	 * @Title: getUserDeptLoeave 
	 * @Description: TODO
	 * @param @param usaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月15日 上午10:02:32
	 * @throws
	 */
	String getUserDeptLoeave(Integer usaId);
	/**
	 * 查看流程图
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param usaId 
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月15日 上午10:17:49
	 * @throws
	 */
	void getDiagramResourceByPaId(HttpServletResponse response,
			Integer usaId);
	/**
	 * 任务完成之后更新印章汇总表中的数据，并更新每个字表中的数据
	 * @Title: updateStampStatiscsReg 
	 * @Description: TODO
	 * @param @param usaId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年12月15日 下午7:50:35
	 * @throws
	 */
	void updateStampStatiscsReg(Integer usaId);
	
	//---------------------------用章附加表--------------------------------------------------------
	
	/**
	 * 保存用章详情
	 * @Title: saveOrUpdUseStamp 
	 * @Description: TODO
	 * @param @param useStampAppAttach
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月14日 下午3:29:42
	 * @throws
	 */
	String saveOrUpdUseStamp(UseStampAppAttach useStampAppAttach);
	/**
	 * 查询用章申请下的印章信息
	 * @Title: findUseStampAttList 
	 * @Description: TODO
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<UseStampAppAttach>
	 * @date 2015年12月14日 下午4:01:18
	 * @throws
	 */
	List<UseStampAppAttach> findUseStampAttList(String appNo,PageUtil pageUtil);
	/**
	 * 查询用章申请下的申请印章数量
	 * @Title: findUseStampCount 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年12月14日 下午4:01:51
	 * @throws
	 */
	Long findUseStampCount(String appNo);
	/**
	 * 删除申请的印章信息
	 * @Title: deleteStampList 
	 * @Description: TODO
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年12月14日 下午4:12:10
	 * @throws
	 */
	boolean deleteStampList(String ids,String procStatus);
	/**
	 * 根据申请编号查询申请的印章名称，只限定为有类型的
	 * @Title: findStampNamesByAppNo 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<String>
	 * @date 2015年12月15日 上午11:51:07
	 * @throws
	 */
	List<UseStampAppAttach> findStampNamesByAppNo(String appNo);
	/**
	 * 根据印章名字判断是否有人借用盖印章
	 * @Title: getStampByName 
	 * @Description: TODO
	 * @param @param stampName
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年12月15日 下午2:27:02
	 * @throws
	 */
	boolean getStampByName(String stampName,String appNo);
	/**
	 * 查询能够申请的印章
	 * @Title: findStampList 
	 * @Description: TODO
	 * @param @param organizeId
	 * @param @param stampType
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<ComboBoxModel>
	 * @date 2015年12月16日 上午10:03:44
	 * @throws
	 */
	List<ComboBoxModel> findStampList(String stampType,String appNo);
	
}
