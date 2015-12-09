package com.oasys.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.oasys.model.BusinessTripAttach;
import com.oasys.model.TravelExpensesApp;
import com.oasys.model.TravelExpensesOther;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;


/**
 * 差旅报销申请
 * @author Administrator
 *
 */
public interface TravelExpensesAppService extends WorkFlowBaseService
{	
	/***
	 * 查询差旅报销申请列表
	 * @Title: findTravelAppList 
	 * @Description: TODO
	 * @param @param travelExpensesApp 查询条件
	 * @param @param pageUtil  分页
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<TravelExpensesOther>
	 * @date 2015年11月13日 上午11:36:56
	 * @throws
	 */
	List<TravelExpensesApp> findTravelAppList(TravelExpensesApp travelExpensesApp,PageUtil pageUtil);
	/**
	 * 查询申请总数量
	 * @Title: findtravelAppListCount 
	 * @Description: TODO
	 * @param @param travelExpensesApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年11月13日 下午3:05:17
	 * @throws
	 */
	Long findtravelAppListCount(TravelExpensesApp travelExpensesApp);
	/**
	 * 查询该申请人下的用于差旅报销未报销的借款申请
	 * @Title: findLoanByUserId 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<ComboBoxModel>
	 * @date 2015年11月25日 下午1:10:40
	 * @throws
	 */
	List<ComboBoxModel> findLoanByUserId();
	/**
	 * 根据申请编号查询该差旅费下的借款申请
	 * @Title: findUserLoan 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<ComboBoxModel>
	 * @date 2015年11月25日 下午4:18:44
	 * @throws
	 */
	List<ComboBoxModel> findUserLoan(String appNo);
	
	/**
	 * 保存差旅报销申请主表
	 * @Title: saveTravelExpensesApp 
	 * @Description: TODO
	 * @param @param travelExpensesApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月16日 上午10:59:50
	 * @throws
	 */
	TravelExpensesApp saveTravelExpensesApp(TravelExpensesApp travelExpensesApp);
	
	/**
	 * 保存差旅申请
	 * @Title: savetravelExpensesOther 
	 * @Description: TODO
	 * @param @param travelExpensesOther
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年11月13日 下午5:46:04
	 * @throws
	 */
	String savetravelExpensesOther(TravelExpensesOther travelExpensesOther);
	
	/**
	 * 批量删除申请报销项目
	 * @Title: deleteTravelOther 
	 * @Description: TODO
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年11月16日 上午10:34:30
	 * @throws
	 */
	boolean deleteTravelOther(String ids,String appNo);
	
	/**
	 * 根据申请编号查询差旅报销申请
	 * @Title: findTravelExpenseByAppNo 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return TravelExpensesApp
	 * @date 2015年11月16日 上午11:30:24
	 * @throws
	 */
	TravelExpensesApp findTravelExpenseByAppNo(String appNo);
	
	/**
	 * 根据编号查询附加表中的合计总额，既总计
	 * @Title: findTravelOtherTotal 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return BigDecimal
	 * @date 2015年11月16日 上午11:36:19
	 * @throws
	 */
	BigDecimal findTravelOtherTotal(String appNo);
	/**
	 * 根据申请编号删除差旅报销申请信息
	 * @Title: deleteTravelExpensesApp 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年11月16日 上午11:48:31
	 * @throws
	 */
	boolean deleteTravelExpensesApp(String appNo);
	
	/**
	 * 根据申请编号查询附加表列表
	 * @Title: findTravelOtherList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<TravelExpensesOther>
	 * @date 2015年11月16日 上午11:57:30
	 * @throws
	 */
	List<TravelExpensesOther> findTravelOtherList(String appNo,PageUtil pageUtil);
	/**
	 * 根据申请编号查询申请下的附加表数量
	 * @Title: findTravelOtherCount 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年11月17日 上午9:26:02
	 * @throws
	 */
	Long findTravelOtherCount(String appNo);
	
	/**
	 * 根据申请编号计算金额
	 * @Title: setTravelMoney 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return TravelExpensesApp
	 * @date 2015年11月17日 下午3:25:28
	 * @throws
	 */
	TravelExpensesApp setTravelMoney(String appNo);
	
	
	/**
	 * 查看流程图
	 * @Title: getDiagramResourceByPaId 
	 * @Description: TODO
	 * @param @param response
	 * @param @param teaId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月17日 下午4:02:17
	 * @throws
	 */
	void getDiagramResourceByPaId(HttpServletResponse response,
			Integer teaId);
	
	/**
	 * 更新申请状态
	 * @Title: updateAppStatus 
	 * @Description: TODO
	 * @param @param teaId
	 * @param @param appStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月17日 下午5:55:51
	 * @throws
	 */
	void updateAppStatus(Integer teaId,String appStatus);
	
	/**
	 * 更新流程状态
	 * @Title: updateProcStatus 
	 * @Description: TODO
	 * @param @param teaId
	 * @param @param procStatus
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月17日 下午5:56:31
	 * @throws
	 */
	void updateProcStatus(Integer teaId,String procStatus);
	
	/**
	 * 差率报销申请sql语句
	 * @Title: getUseStampSql 
	 * @Description: TODO
	 * @param @return
	 * @author WANGXINCHENG
	 * @return StringBuffer
	 * @date 2015年11月17日 下午7:13:16
	 * @throws
	 */
	StringBuffer getUseStampSql();
	
	/**
	 * 更新预借金额
	 * @Title: updateLoanApp 
	 * @Description: TODO
	 * @param @param teaId
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月18日 上午11:05:32
	 * @throws
	 */
	void updateLoanApp(Integer teaId);
	
	/**
	 * 查看差旅报销申请未完成任务
	 * @Title: findTravelNotTaskByTeaId 
	 * @Description: TODO
	 * @param @param teaId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return TravelExpensesApp
	 * @date 2015年11月18日 上午11:49:09
	 * @throws
	 */
	TravelExpensesApp findTravelNotTaskByTeaId(Integer teaId);
	
	//-----------------------------------------------------------------交通费用------------------------------------------------
	/**
	 * 根据申请编号查询交通费用
	 * @Title: findBusinessAtt 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return List<BusinessTripAttach>
	 * @date 2015年11月18日 下午5:37:17
	 * @throws
	 */
	List<BusinessTripAttach> findBusinessAtt(String appNo);
	/**
	 * 查询交通费用数量
	 * @Title: findBusinessAttCount 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年11月18日 下午5:50:24
	 * @throws
	 */
	Long findBusinessAttCount(String appNo);
	/**
	 * 批量删除交通费用信息
	 * @Title: deleteBusinessTripAtt 
	 * @Description: TODO
	 * @param @param ids
	 * @param @return
	 * @author WANGXINCHENG
	 * @return boolean
	 * @date 2015年11月18日 下午5:54:32
	 * @throws
	 */
	boolean deleteBusinessTripAtt(String ids,String appNo);
	/**
	 * 保存交通费用
	 * @Title: savebusinessTripAtt 
	 * @Description: TODO
	 * @param @param businessTripAttach
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月18日 下午8:35:43
	 * @throws
	 */
	String savebusinessTripAtt(BusinessTripAttach businessTripAttach);
	
	/**
	 * 根据出差天数计算补贴金额
	 * @Title: setTravelSubsidyAmt 
	 * @Description: TODO
	 * @param @param subsidyAmt
	 * @param @return
	 * @author WANGXINCHENG
	 * @return double
	 * @date 2015年11月19日 上午10:14:24
	 * @throws
	 */
	double setTravelSubsidyAmt(Double btDays);
	
	
	
	
}
