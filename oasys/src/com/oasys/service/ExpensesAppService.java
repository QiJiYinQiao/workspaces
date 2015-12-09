package com.oasys.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.AuditProcHis;
import com.oasys.model.ExpensesApp;
import com.oasys.model.VO.ExpensesAppModel;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

/**
 * 费用申请Service
 * @ClassName: ExpensesAppService 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:20:24
 */
public interface ExpensesAppService {
	/**
	 * 新增保存ExpensesApp
	 * @Title: saveExpensesApp 
	 * @Description: TODO
	 * @param @param axpensesApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 上午11:57:48
	 * @throws
	 */
	boolean saveExpensesApp(ExpensesApp expensesApp);
	/**
	 * 查询list列表
	 * @Title: findExpensesAppList 
	 * @Description: TODO
	 * @param @param pageUtil
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<ExpensesApp>
	 * @date 2015年10月12日 下午2:49:17
	 * @throws
	 */
	List<ExpensesAppModel> findExpensesAppList(PageUtil pageUtil,ExpensesAppModel expensesAppModel);
	/**
	 * 查询总条数
	 * @Title: countFindExpensesAppList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return Long
	 * @date 2015年10月12日 下午3:21:26
	 * @throws
	 */
	Long countFindExpensesAppList();
	/**
	 * 根据id删除
	 * @Title: delExpensesAppbyBtaId 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月12日 下午4:41:00
	 * @throws
	 */
    boolean delExpensesAppbyBtaId(Integer btaId);
    /**
     * 提交申请，开启流程
     * @Title: sumitApply 
     * @Description: TODO
     * @param @param expensesApp
     * @param @return
     * @author PANCHUANHE
     * @return boolean
     * @date 2015年10月13日 上午10:54:21
     * @throws
     */
    boolean sumitApply(ExpensesApp expensesApp);
    /**
     * 判断该用户是总部还是分部
     * @Title: findZongBuOrFenBu 
     * @Description: TODO
     * @param @param expensesApp
     * @param @return
     * @author PANCHUANHE
     * @return boolean
     * @date 2015年10月13日 上午10:58:39
     * @throws
     */
    boolean findZongBuOrFenBu(ExpensesApp expensesApp);
    /**
     * 更新流程状态
     * @Title: updateExpensesAppProcessStatus 
     * @Description: TODO
     * @param @param btaId
     * @param @param status
     * @author PANCHUANHE
     * @return void
     * @date 2015年10月13日 上午11:16:56
     * @throws
     */
    void updateExpensesAppProcessStatus(Integer btaId, String status);
    /**
     * 查询所有任务
     * @Title: findAllExpensesAppTaskList 
     * @Description: TODO
     * @param @return
     * @author PANCHUANHE
     * @return List<ExpensesAppModel>
     * @date 2015年10月13日 下午1:22:21
     * @throws
     */
    List<ExpensesAppModel> findAllExpensesAppTaskList(PageUtil pageUtil,WorkFlowTasksModel workFlowTasksModel);
    /**
     * 根据ids查询实体对象list
     * @Title: findExpensesAppByIds 
     * @Description: TODO
     * @param @param ids
     * @param @param pageUtil
     * @param @return
     * @author PANCHUANHE
     * @return List<ExpensesAppModel>
     * @date 2015年10月13日 下午1:32:58
     * @throws
     */
	List<ExpensesAppModel> findExpensesAppByIds(String ids, PageUtil pageUtil);
	/**
	 * 查询所有任务的总数
	 * @Title: countFindAllExpensesAppTaskList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return Long
	 * @date 2015年10月13日 下午1:42:01
	 * @throws
	 */
	Long countFindAllExpensesAppTaskList(WorkFlowTasksModel workFlowTasksModel);
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
	 * @Title: updateExpensesAppStatus 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @param state
	 * @author PANCHUANHE
	 * @return void
	 * @date 2015年10月13日 下午2:37:32
	 * @throws
	 */
	void updateExpensesAppStatus(Integer btaId, String state);
	/**
	 * 办理任务(分部)
	 * @Title: saveSubmitTaskBo 
	 * @Description: TODO
	 * @param @param expensesAppModel
	 * @param @param result
	 * @param @param auditProcHis
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月13日 下午2:40:52
	 * @throws
	 */
	boolean saveSubmitTaskBo(ExpensesAppModel expensesAppModel, String result,
			AuditProcHis auditProcHis);
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
	 * @param @param expensesAppModel
	 * @param @param result
	 * @param @param auditProcHis
	 * @param @return
	 * @author PANCHUANHE
	 * @return boolean
	 * @date 2015年10月13日 下午8:03:17
	 * @throws
	 */
	boolean saveSubmitTaskHo(ExpensesAppModel expensesAppModel, String result,
			AuditProcHis auditProcHis);
	
}
