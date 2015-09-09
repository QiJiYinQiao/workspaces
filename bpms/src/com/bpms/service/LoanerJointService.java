package com.bpms.service;

import com.bpms.model.vo.LoanerJointModel;

/**
 * 
 * 共同贷款人service
 * 
 * @author liuhh 2015/06/16.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 liuhh 2015/08/03 创建.
 */
public interface LoanerJointService {

	/**
	 * 持久化共同贷款人的信息
	 */
	void persistenceLoanerJoint(LoanerJointModel loanerJoint);

	/**
	 * 根据订单的id查询共同贷款人的信息
	 */
	LoanerJointModel findLoanerJointByOrderId(String orderId);

}
