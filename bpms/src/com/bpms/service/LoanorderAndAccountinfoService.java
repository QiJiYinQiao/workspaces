package com.bpms.service;

import java.util.List;

import com.bpms.model.LoanorderAndAccountinfo;

/**
 * 订单与开户行信息表service
 * 
 * @author panchuanhe 2015/6/30
 */
public interface LoanorderAndAccountinfoService {
	/**
	 * 开户行信息id查找
	 * 
	 * @param LoanOrderId
	 *            订单id
	 * @param AccountId
	 *            开户行信息id
	 * @return
	 */
	List<LoanorderAndAccountinfo> findByAccountId(String AccountId);

	/**
	 * 根据开户行id和订单id删除，也就是该订单与该开户行解除关系
	 * 
	 * @param accountId
	 * @param loanOrderId
	 * @return
	 */
	boolean deleteByAccountIdAndLoanOrderId(String accountId, String loanOrderId);

	/**
	 * 保存
	 * 
	 * @param laa
	 * @return
	 */
	boolean saveLoanorderAndAccountinfo(LoanorderAndAccountinfo laa);

	/**
	 * 根据订单id查询
	 * 
	 * @param loanOrderId
	 * @return
	 */
	List<LoanorderAndAccountinfo> findByLoanOrderId(String loanOrderId);

	/**
	 * 根据订单id删除
	 * 
	 * @param loanOrderId
	 */
	void deleteByLoanOrderId(String loanOrderId);

	/**
	 * 根据订单id获取开户行信息的个数
	 * 
	 * @param loanOrderId
	 * @return
	 */
	Long findCountByLoanOrderId(String loanOrderId);
}
