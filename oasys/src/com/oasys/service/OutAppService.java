package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.AuditProcHis;
import com.oasys.model.OutApp;
import com.oasys.model.VO.OutAppModel;
import com.oasys.model.VO.ProposerModel;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * 外出申请Service
 * @ClassName: OutAppService 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年11月10日 下午7:47:22
 */
public interface OutAppService {
	/**
	 * 申请人下拉列表
	 * @Title: createCombogrid 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<ProposerModel>
	 * @date 2015年10月26日 下午3:17:08
	 * @throws
	 */
	List<ProposerModel> findCreateCombogrid(String q);
	/**
	 * 新增保存OutApp
	 * @Title: saveOutApp 
	 * @Description: TODO
	 * @param @param axpensesApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 上午11:57:48
	 * @throws
	 */
	DataModel saveOutApp(OutApp outApp);
	/**
	 * 查询list列表
	 * @Title: findOutAppList 
	 * @Description: TODO
	 * @param @param pageUtil
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<OutApp>
	 * @date 2015年10月12日 下午2:49:17
	 * @throws
	 */
	List<OutAppModel> findOutAppList(PageUtil pageUtil,OutAppModel outAppModel);
	/**
	 * 查询总条数
	 * @Title: countFindOutAppList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return Long
	 * @date 2015年10月12日 下午3:21:26
	 * @throws
	 */
	Long countFindOutAppList(OutAppModel outAppModel);
	/**
	 * 根据id删除
	 * @Title: delOutAppbyBtaId 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 下午4:41:00
	 * @throws
	 */
    boolean delOutAppByOutId(Integer outId);
    /**
     * 提交申请，开启流程
     * @Title: sumitApply 
     * @Description: TODO
     * @param @param outApp
     * @param @return
     * @author PANCHUANHE
     * @return boolean
     * @date 2015年10月13日 上午10:54:21
     * @throws
     */
    String sumitApply(OutApp outApp);
    /**
     * 判断该用户是总部还是分部
     * @Title: getProcessImgName 
     * @Description: TODO
     * @param @param outApp
     * @param @return
     * @author PANCHUANHE
     * @return boolean
     * @date 2015年10月13日 上午10:58:39
     * @throws
     */
    String getProcessImgName(OutApp outApp);
    /**
     * 更新流程状态
     * @Title: updateOutAppProcessStatus 
     * @Description: TODO
     * @param @param btaId
     * @param @param status
     * @author PANCHUANHE
     * @return void
     * @date 2015年10月13日 上午11:16:56
     * @throws
     */
    void updateOutAppProcessStatus(Integer btaId, String status);
    /**
     * 查询所有任务
     * @Title: findAllOutAppTaskList 
     * @Description: TODO
     * @param @return
     * @author PANCHUANHE
     * @return List<OutAppModel>
     * @date 2015年10月13日 下午1:22:21
     * @throws
     */
    List<OutAppModel> findAllOutAppTaskList(PageUtil pageUtil,WorkFlowTasksModel workFlowTasksModel);
    /**
     * 根据ids查询实体对象list
     * @Title: findOutAppByIds 
     * @Description: TODO
     * @param @param ids
     * @param @param pageUtil
     * @param @return
     * @author PANCHUANHE
     * @return List<OutAppModel>
     * @date 2015年10月13日 下午1:32:58
     * @throws
     */
	List<OutAppModel> findOutAppByIds(String ids, PageUtil pageUtil);
	/**
	 * 查询所有任务的总数
	 * @Title: countFindAllOutAppTaskList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return Long
	 * @date 2015年10月13日 下午1:42:01
	 * @throws
	 */
	Long countFindAllOutAppTaskList();
	/**
	 * 签收任务
	 * @Title: saveHoldWorkTask 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月13日 下午1:55:18
	 * @throws
	 */
	boolean saveHoldWorkTask(String taskId);
	/**
	 * 更新订单状态
	 * @Title: updateOutAppStatus 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @param state
	 * @author PANCHUANHE
	 * @return void
	 * @date 2015年10月13日 下午2:37:32
	 * @throws
	 */
	void updateOutAppStatus(Integer btaId, String state);
	/**
	 * 办理任务(分部)
	 * @Title: saveSubmitTaskBo 
	 * @Description: TODO
	 * @param @param outAppModel
	 * @param @param result
	 * @param @param auditProcHis
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月13日 下午2:40:52
	 * @throws
	 */
	String saveSubmitTaskBo(WorkFlowTasksModel workFlowTaskModel);
	/**
	 * 查看流程图
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param btaId
	 * @author PANCHUANHE
	 * @return void
	 * @date 2015年10月13日 下午4:40:56
	 * @throws
	 */
	void getDiagramResourceByPaId(HttpServletResponse response, Integer btaId);
	/**
	 * 办理任务（总部）
	 * @Title: saveSubmitTaskHo 
	 * @Description: TODO
	 * @param @param outAppModel
	 * @param @param result
	 * @param @param auditProcHis
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月13日 下午8:03:17
	 * @throws
	 */
	boolean saveSubmitTaskHo(OutAppModel outAppModel, String result,
			AuditProcHis auditProcHis);
	/**
	 * 获取查询列表的sql字符串
	 * @Title: getListSql 
	 * @Description: TODO
	 * @param @param outAppModel
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年11月18日 上午10:34:38
	 * @throws
	 */
	String getListSql(OutAppModel outAppModel);
}
