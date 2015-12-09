package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.AuditProcHis;
import com.oasys.model.EmpFullmemberApp;
import com.oasys.model.VO.EmpFullmemberAppModel;
import com.oasys.model.VO.ProposerModel;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * 费用申请Service
 * @ClassName: EmpFullmemberAppService 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:20:24
 */
public interface EmpFullmemberAppService {
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
	ProposerModel findCreateCombogrid(String q);
	/**
	 * 新增保存EmpFullmemberApp
	 * @Title: saveEmpFullmemberApp 
	 * @Description: TODO
	 * @param @param axpensesApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 上午11:57:48
	 * @throws
	 */
	boolean saveEmpFullmemberApp(EmpFullmemberApp empFullmemberApp);
	/**
	 * 查询list列表
	 * @Title: findEmpFullmemberAppList 
	 * @Description: TODO
	 * @param @param pageUtil
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<EmpFullmemberApp>
	 * @date 2015年10月12日 下午2:49:17
	 * @throws
	 */
	List<EmpFullmemberAppModel> findEmpFullmemberAppList(PageUtil pageUtil,EmpFullmemberAppModel empFullmemberAppModel);
	/**
	 * 查询总条数
	 * @Title: countFindEmpFullmemberAppList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return Long
	 * @date 2015年10月12日 下午3:21:26
	 * @throws
	 */
	Long countFindEmpFullmemberAppList();
	/**
	 * 根据id删除
	 * @Title: delEmpFullmemberAppbyBtaId 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 下午4:41:00
	 * @throws
	 */
    boolean delEmpFullmemberAppByEfaId(Integer efaId);
    /**
     * 提交申请，开启流程
     * @Title: sumitApply 
     * @Description: TODO
     * @param @param empFullmemberApp
     * @param @return
     * @author PANCHUANHE
     * @return boolean
     * @date 2015年10月13日 上午10:54:21
     * @throws
     */
    String sumitApply(EmpFullmemberApp empFullmemberApp);
    /**
     * 判断该用户是总部还是分部
     * @Title: getProcessImgName 
     * @Description: TODO
     * @param @param empFullmemberApp
     * @param @return
     * @author PANCHUANHE
     * @return boolean
     * @date 2015年10月13日 上午10:58:39
     * @throws
     */
    String getProcessImgName(EmpFullmemberApp empFullmemberApp);
    /**
     * 更新流程状态
     * @Title: updateEmpFullmemberAppProcessStatus 
     * @Description: TODO
     * @param @param btaId
     * @param @param status
     * @author PANCHUANHE
     * @return void
     * @date 2015年10月13日 上午11:16:56
     * @throws
     */
    void updateEmpFullmemberAppProcessStatus(Integer btaId, String status);
    /**
     * 查询所有任务
     * @Title: findAllEmpFullmemberAppTaskList 
     * @Description: TODO
     * @param @return
     * @author PANCHUANHE
     * @return List<EmpFullmemberAppModel>
     * @date 2015年10月13日 下午1:22:21
     * @throws
     */
    List<EmpFullmemberAppModel> findAllEmpFullmemberAppTaskList(PageUtil pageUtil,WorkFlowTasksModel workFlowTasksModel);
    /**
     * 根据ids查询实体对象list
     * @Title: findEmpFullmemberAppByIds 
     * @Description: TODO
     * @param @param ids
     * @param @param pageUtil
     * @param @return
     * @author PANCHUANHE
     * @return List<EmpFullmemberAppModel>
     * @date 2015年10月13日 下午1:32:58
     * @throws
     */
	List<EmpFullmemberAppModel> findEmpFullmemberAppByIds(String ids, PageUtil pageUtil);
	/**
	 * 查询所有任务的总数
	 * @Title: countFindAllEmpFullmemberAppTaskList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return Long
	 * @date 2015年10月13日 下午1:42:01
	 * @throws
	 */
	Long countFindAllEmpFullmemberAppTaskList();
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
	 * @Title: updateEmpFullmemberAppStatus 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @param state
	 * @author PANCHUANHE
	 * @return void
	 * @date 2015年10月13日 下午2:37:32
	 * @throws
	 */
	void updateEmpFullmemberAppStatus(Integer btaId, String state);
	/**
	 * 办理任务(分部)
	 * @Title: saveSubmitTaskBo 
	 * @Description: TODO
	 * @param @param empFullmemberAppModel
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
	 * @param @param empFullmemberAppModel
	 * @param @param result
	 * @param @param auditProcHis
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月13日 下午8:03:17
	 * @throws
	 */
	boolean saveSubmitTaskHo(EmpFullmemberAppModel empFullmemberAppModel, String result,
			AuditProcHis auditProcHis);
}
