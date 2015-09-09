package com.qqms.service;

import com.qqms.model.VO.LoanOrderVO;
import com.qqms.util.PageUtil;

public interface LoanerService
{
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月23日 下午1:06:19
	 * @Title:findLoanerList
	 * @Description:TODO查询贷款客户列表（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public Object[] findLoanerList(String name,String idNo,String genderType,PageUtil pageUtil);	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月24日 下午1:23:10
	 * @Title:findLoanerDetailById
	 * @Description:TODO根据贷款人id查询贷款人详情（这里描述这个方法的作用）
	 * @param loanerId
	 * @return最近一次的订单id
	 * @throws:
	 */
	public String findLoanerDetailById(String loanerId);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月24日 下午1:23:40
	 * @Title:findLoanerOrderListById
	 * @Description:TODO根据贷款人id查询客户贷款订单列表（这里描述这个方法的作用）
	 * @param loanerId
	 * @return
	 * @throws:
	 */
	public Object[] findLoanerOrderListById(String loanerId,PageUtil pageUtil);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月25日 上午11:48:30
	 * @Title:findLoanerDetailById
	 * @Description:TODO 根据贷款订单id查询贷款人个人信息详情（这里描述这个方法的作用）
	 * @param loanOrderId
	 * @return
	 * @throws:
	 */
	public Object[] findLoanerDetailByOrderId(String loanOrderId);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月25日 上午11:55:23
	 * @Title:findLoanOrderDetailById
	 * @Description:TODO 根据订单id查询订单详情（这里描述这个方法的作用）
	 * @param loanOrderId
	 * @return
	 * @throws:
	 */
	public LoanOrderVO findLoanOrderDetailById(String loanOrderId);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月25日 下午8:23:28
	 * @Title:findCreditAuditDetailByOrderId
	 * @Description:TODO 根据订单id查询针对该条订单的信审报告详情（这里描述这个方法的作用）
	 * @param loanOrderId
	 * @return
	 * @throws:
	 */
	public Object[] findCreditAuditDetailByOrderId(String loanOrderId) throws CloneNotSupportedException;
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月3日 上午10:49:14
	 * @Title:findLoanerHisListByLoanerId
	 * @Description:TODO 根据贷款人id查看履历变更列表（这里描述这个方法的作用）
	 * @param loanerId
	 * @return
	 * @throws:
	 */
	public Object[] findLoanerHisListByLoanerId(String loanerId,PageUtil pageUtil);
}
