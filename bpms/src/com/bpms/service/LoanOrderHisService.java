package com.bpms.service;

import java.util.List;

import com.bpms.model.LoanOrderHis;
import com.bpms.model.vo.LoanOrderHisModel;

/**
 * 
 * 增加订单履历的service
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public interface LoanOrderHisService {

	/**
	 * 增加订单的履历的信息
	 * 
	 * @param loanOrder
	 *            订单履历
	 */
	void saveLoanOrderHis(LoanOrderHis loanOrderHis);

	/**
	 * 根据订单的id获取订单的履历批注信息
	 * 
	 * @param loanOrderId
	 *            订单的id
	 * @return 订单履历的批注的信息
	 */
	List<LoanOrderHisModel> findAllLoanOrderHist(String loanOrderId);

	/**
	 * 根据角色的code和订单ID获取履历的信息
	 * 
	 * @param roleCode
	 *            角色code
	 * @param loanOrderId
	 *            订单ID
	 * @return
	 */
	LoanOrderHis findLoanOrderHis(String roleCode, String loanOrderId);

	/**
	 * 获取贷审委的评注的信息
	 * 
	 * @param loanOrderId
	 *            订单id
	 * @return
	 */
	List<LoanOrderHis> findLoanOrderHis(String loanOrderId);
}
