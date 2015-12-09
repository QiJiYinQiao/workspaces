package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.ConsumablesApp;
import com.oasys.model.ConsumablesAppAttach;
import com.oasys.model.StatusNameRef;
import com.oasys.util.PageUtil;

/**
 * 
 * @ClassName: ConsumablesAppService
 * @Description: TODO 低值易耗品申请service
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年9月28日 下午1:49:14
 *
 */
public interface ConsumablesAppService {

	/**
	 * 
	 * @time:2015年9月28日 下午1:55:16
	 * @Title:findConsumablesList
	 * @Description:TODO（这里描述这个方法的作用）查询所有申请记录
	 * @param consumablesApp
	 * @return
	 * @throws:
	 */
	List<ConsumablesApp> findConsumablesList(ConsumablesApp consumablesApp);
	
	/**
	 * 
	 * @time:2015年9月28日 下午4:42:00
	 * @Title:countConsumablesList
	 * @Description:TODO（这里描述这个方法的作用）申请总条数
	 * @param consumablesApp
	 * @return
	 * @throws:
	 */
	Long countConsumablesList(ConsumablesApp consumablesApp); 
	/**
	 * 
	 * @time:2015年9月28日 下午1:55:29
	 * @Title:addOrUpdatePpeEntity
	 * @Description:TODO（这里描述这个方法的作用）新增或修改申请
	 * @param consumablesApp
	 * @throws:
	 */
	boolean addOrUpdateConsumablesEntity(ConsumablesApp consumablesApp);

	/**
	 * 
	 * @time:2015年9月28日 下午1:55:38
	 * @Title:delConsumablesAppScrap
	 * @Description:TODO（这里描述这个方法的作用）根据ID删除申请
	 * @param id
	 * @throws:
	 */
	void delConsumablesAppScrap(Integer id);

	/**
	 * 
	 * @time:2015年9月28日 下午1:55:47
	 * @Title:getConsumablesAppByID
	 * @Description:TODO（这里描述这个方法的作用）根据ID查询
	 * @param id
	 * @return
	 * @throws:
	 */
	ConsumablesApp getConsumablesAppByID(Integer id);

	/**
	 * 
	 * @time:2015年9月28日 下午1:56:00
	 * @Title:consumablesAppStartProcessInstance
	 * @Description:TODO（这里描述这个方法的作用）开启流程
	 * @param consumablesApp
	 * @return
	 * @throws:
	 */
	boolean consumablesAppStartProcessInstance(ConsumablesApp consumablesApp);

	/**
	 * 
	 * @time:2015年9月28日 下午1:56:10
	 * @Title:updateProcessStatus
	 * @Description:TODO（这里描述这个方法的作用）更新流程状态字段
	 * @param id
	 * @param status
	 * @throws:
	 */
	void updProcessStatus(Integer id, String status);

	/**
	 * 
	 * @time:2015年9月28日 下午1:56:31
	 * @Title:updateStatus
	 * @Description:TODO（这里描述这个方法的作用）更新申请状态字段
	 * @param id
	 * @param status
	 * @throws:
	 */
	void updStatus(Integer id, String status);

	/**
	 * 
	 * @time:2015年9月28日 下午1:56:39
	 * @Title:findPPEListTask
	 * @Description:TODO（这里描述这个方法的作用）查询低值易耗品申请列表
	 * @param consumablesApp
	 * @return
	 * @throws:
	 */
	List<ConsumablesApp> findConsumablesListTask(ConsumablesApp consumablesApp);

	/**
	 * 
	 * @time:2015年9月28日 下午1:56:48
	 * @Title:saveSubmitTask
	 * @Description:TODO（这里描述这个方法的作用）受理任务
	 * @param consumablesApp
	 * @throws:
	 */
	public void saveSubmitTask(ConsumablesApp consumablesApp);
	/**
	 * 
	 * @Title: findConsumablesAppAttachList 
	 * @Description: 查询低值易耗品记录
	 * @param @param pageUtil
	 * @param @param consumablesApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return List<ConsumablesAppAttach>
	 * @date 2015年10月12日 下午4:54:51
	 * @throws
	 */
	List<ConsumablesAppAttach> findConsumablesAppAttachList(PageUtil pageUtil,
			ConsumablesApp consumablesApp);
	/**
	 * 
	 * @Title: findConsumablesAppCount 
	 * @Description: 统计申请总数
	 * @param @param consumablesApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return Long
	 * @date 2015年10月12日 下午4:55:10
	 * @throws
	 */
	Long findConsumablesAppCount(ConsumablesApp consumablesApp);
	/**
	 * 
	 * @Title: delConsumablesApp 
	 * @Description: 删除申请
	 * @param @param appNo
	 * @author yuanzhongqiu
	 * @return void
	 * @date 2015年10月12日 下午4:55:19
	 * @throws
	 */
	void delConsumablesApp(String appNo);
	/**
	 * 
	 * @Title: updateConsumablesAppProceStatus 
	 * @Description: 更新流程状态
	 * @param @param id
	 * @param @param string
	 * @author yuanzhongqiu
	 * @return void
	 * @date 2015年10月12日 下午5:35:58
	 * @throws
	 */
	void updateConsumablesAppProceStatus(Integer id, String status);
	/**
	 *
	 * @Title: updateConsumablesAppStatus 
	 * @Description: 更新申请状态
	 * @param @param valueOf
	 * @param @param id
	 * @author yuanzhongqiu
	 * @return void
	 * @date 2015年10月12日 下午5:42:32
	 * @throws
	 */
	void updateConsumablesAppStatus(Integer id, String status);
	/**
	 * 
	 * @Title: findZongBuOrFenBu 
	 * @Description: 区分总部分部
	 * @param @param consumablesApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return boolean
	 * @date 2015年10月12日 下午5:50:00
	 * @throws
	 */
	boolean findZongBuOrFenBu(ConsumablesApp consumablesApp);

	void getDiagramResourceByPaId(HttpServletResponse httpServletResponse,
			Integer caId);

	List<ConsumablesAppAttach> findConsumablesListTask(PageUtil pageUtil,
			ConsumablesApp consumablesApp);

	Long findConsumablesListTaskCount(ConsumablesApp consumablesApp);

	StatusNameRef findAppStatusByID(String appStatus);

	ConsumablesApp getConsumablesAppByAppNo(String appNo);
}
