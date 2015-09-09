package com.bpms.service;

import java.util.List;

import com.bpms.model.LoanorderAndContacts;

/**
 * bpms 紧急联系人与订单servic
 * 
 * @author panchuanhe
 * @date 2015/6/16
 */
public interface LoanorderAndContactsService {
	/**
	 * 新增
	 * 
	 * @param l
	 *            订单与联系人实体类是咧
	 * @return
	 * @author panchuanhe
	 * @date 2015/6/16
	 */
	public boolean saveLoanorderAndContacts(LoanorderAndContacts l);

	/**
	 * 根据紧急联系人的id查找，用来判断该紧急联系人是否已经与其他订单绑定，不能删除
	 * 
	 * @param contactId
	 *            紧急联系人的id
	 * @return
	 */
	public List<LoanorderAndContacts> findListByContactId(String contactId);

	/**
	 * 删除(即解除紧急联系人和订单的关系)
	 */
	public boolean deleteByContactIdAndLoanOrderId(String contactId,
			String loanOrderId);

	/**
	 * 根据订单id查询
	 * 
	 * @param loanOrderId
	 * @return
	 */
	public List<LoanorderAndContacts> findListByLoanOrderId(String loanOrderId);

	/**
	 * 根据订单id删除
	 */
	public Integer deleteByLoanOrderId(String loanOrderId);

	/**
	 * 根据订单的id获取联系人个数
	 * 
	 * @param loanOrderId
	 * @return
	 */
	Long findCountByLoanOrderId(String loanOrderId);
}
