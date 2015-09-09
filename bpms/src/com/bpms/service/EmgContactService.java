package com.bpms.service;

import java.util.List;

import com.bpms.model.Contacts;

/**
 * 
 * 贷款人联系人的service
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public interface EmgContactService {
	/**
	 * 持久化贷款人联系人
	 */
	void persistenceEmgContact(Contacts EmgContact);

	/**
	 * 根据申请单的id查询相关的联系人
	 */
	List<Contacts> findEmgContactByLoanAppliOrderId(String id);

	/**
	 * 根据申请人的信息查询联系人的信息
	 */
	List<Contacts> findEmgContactByLonaId(String id);

}
