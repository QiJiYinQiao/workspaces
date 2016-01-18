package com.oasys.service;


import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.LoanApp;
import com.oasys.service.workFlow.WorkFlowBaseService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
/**
 * 请假申请
 * @author Administrator
 *
 */
public interface LoanAppService extends WorkFlowBaseService
{	
	
	
	/**
	 * 查询借款申请列表
	 * @Title: findLoanAppList 
	 * @Description: TODO
	 * @param @param loanApp
	 * @param @param pageUtil
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<LoanApp>
	 * @date 2015年11月24日 下午7:35:20
	 * @throws
	 */
	List<LoanApp> findLoanAppList(LoanApp loanApp,PageUtil pageUtil);
	
	/**
	 * 查询借款申请列表sql语句
	 * @Title: getLoanListSQL 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return StringBuffer
	 * @date 2015年11月24日 下午7:37:36
	 * @throws
	 */
	StringBuffer getLoanListSQL();
	
	/**
	 * 根据条件查询借款申请数量
	 * @Title: findLoanCount 
	 * @Description: TODO
	 * @param @param loanApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年11月26日 下午3:07:29
	 * @throws
	 */
	Long findLoanCount(LoanApp loanApp);
	/**
	 * 根据申请人所在的部门查询该部门下的所有人
	 * @Title: findUserListByOrgId 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<ComboBoxModel>
	 * @date 2015年11月26日 下午5:08:04
	 * @throws
	 */
	List<ComboBoxModel> findUserListByOrgId();
	
	/**
	 * 保存或更新借款申请
	 * @Title: saveOrUpdLoanApp 
	 * @Description: TODO
	 * @param @param loanApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月26日 下午5:27:18
	 * @throws
	 */
	LoanApp saveOrUpdLoanApp(LoanApp loanApp);
	/**
	 * 查询同一个付款人申请同一种借款用途最多2个
	 * @Title: getLoanPurposeCount 
	 * @Description: TODO
	 * @param @param loanApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年11月26日 下午8:21:32
	 * @throws
	 */
	boolean getLoanPurposeCount(LoanApp loanApp);
	/**
	 * 根据申请编号删除借款申请
	 * @Title: deleteLoanApp 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年11月27日 上午9:49:05
	 * @throws
	 */
	boolean deleteLoanApp(String appNo);
	
	/**
	 * 查看流程图
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param evaId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月27日 上午11:31:01
	 * @throws
	 */
	void getDiagramResourceByPaId(HttpServletResponse response,
			Integer btaId);
	
	/**
	 * 更改申请状态
	 * @Title: updateAppStatus 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @param appStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月27日 上午11:39:47
	 * @throws
	 */
	void updateAppStatus(Integer btaId,String appStatus);
	
	/**
	 * 更改流程状态
	 * @Title: updateProcStatus 
	 * @Description: TODO
	 * @param @param btaId
	 * @param @param procStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月27日 上午11:40:21
	 * @throws
	 */
	void updateProcStatus(Integer btaId,String procStatus);
	/**
	 * 更新报销状态
	 * @Title: updateLoanBalance 
	 * @Description: TODO
	 * @param @param appNo
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月27日 下午5:10:28
	 * @throws
	 */
	void updateLoanBalance(String appNo);
	
	
	
	/**
	 * 根据申请人id判断业务端，借款端
	 * @Title: getUserRoleType 
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月23日 下午2:45:48
	 * @throws
	 */
	String getUserRoleType(Integer userId);
	
	
	
	
	/**
	 * 根据申请人id查询该申请人所申请的借款
	 * 借款用途
	 * @Title: findLoanApp 
	 * @Description: TODO
	 * @param @param userId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<ComboBoxModel>
	 * @date 2015年11月25日 上午11:20:17
	 * @throws
	 */
	List<ComboBoxModel> findLoanApp(Integer userId,String  loanPurpose,String appNos);
	/**
	 * 根据申请编号查询借款申请
	 * @Title: findLoanByAppNo 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return LoanApp
	 * @date 2015年11月25日 下午4:14:32
	 * @throws
	 */
	LoanApp findLoanByAppNo(String appNo);
}
