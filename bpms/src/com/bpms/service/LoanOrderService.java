package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.LoanOrder;
import com.bpms.model.Organization;
import com.bpms.util.PageUtil;

/**
 * 
 * 个人业务请假单的业务处理的service接口
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public interface LoanOrderService {

	/**
	 * 分页获取个人业务贷款单
	 * 
	 * @param map
	 *            获取信息的参数
	 * @param pageUtil
	 *            获取信息的分页工具
	 * @return 当前页的个人贷款业务单
	 */
	List<LoanOrder> findAllLoanOrder(Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 获取个人业务贷款单的条数
	 * 
	 * @param map
	 *            获取信息的参数
	 * @param pageUtil
	 *            获取分页信息的工具
	 * @return 个人业务贷款的总条数
	 */
	Long getCount(Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 增加或修改个人业务贷款信息
	 * 
	 * @param loanOrder
	 *            个人业务贷款单
	 * @return 增加是否成功
	 */
	boolean persistenceLoanOrder(LoanOrder loanOrder);

	/**
	 * 删除贷款业务申请单
	 * 
	 * @param id
	 *            删除贷款业务申请单的id
	 * @return 删除是否成功
	 */
	boolean deleteLoanOrder(String id);

	/**
	 * 根据订单的id获取订单的信息
	 * 
	 * @param id
	 *            订单的id
	 * @return 订单的信息
	 */
	LoanOrder findLoanOrderById(String id);

	/**
	 * 根据订单的信息修改订单
	 * 
	 * @param id
	 *            订单的id
	 * @param status
	 *            修改的状态
	 */
	void updateLoanOrderStauts(String id, String status);

	/**
	 * 修改流程的状态的信息
	 * 
	 * @param processStatus
	 *            流程状态信息
	 */
	void updateLoanOrderProcessStatus(String id, String processStatus);

	/**
	 * 根据订单的ID获取订单的进件城市
	 * @param loanOrderId
	 */
	Organization findLoanCityByOrderId(String loanOrderId);
	/**
	 * @Title: findLoanOrderListByOrderStatus 
	 * @Description: TODO 根据订单状态查询列表，债权匹配
	 * @param @return
	 * @return List<LoanOrder>
	 * @throws
	 */
	List<LoanOrder> findLoanOrderListByOrderStatus(PageUtil pageUtil);
	/**
	 * @Title: countLoanOrderListByOrderStatus 
	 * @Description: TODO 根据订单状态查询列表总条数，债权匹配
	 * @param @return
	 * @return Long
	 * @throws
	 */
	Long countLoanOrderListByOrderStatus();
}
