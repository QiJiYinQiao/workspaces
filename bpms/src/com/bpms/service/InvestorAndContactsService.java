package com.bpms.service;

import com.bpms.model.InvestorAndContacts;

/**
 * 投资人和联系人的关联关系维护Service
 * 
 * @author liuhh
 *
 */
public interface InvestorAndContactsService {
	/**
	 * 持久化投资订单的信息
	 */
	public boolean persistenceInvestorAndContacts(
			InvestorAndContacts investorAndContacts);
	/**
	 * 根据紧急联系人的id删除
	 * @return
	 */
	public boolean deleteByContactId(String ContactId);
	/**
	 * 根据投资人id，删除
	 * @param InvestorId
	 * @return
	 */
	public boolean deleteByInvestorId(String InvestorId);
}
