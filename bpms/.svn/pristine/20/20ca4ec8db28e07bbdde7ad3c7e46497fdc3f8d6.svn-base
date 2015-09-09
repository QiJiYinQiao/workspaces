package com.bpms.service;

import java.util.List;

import com.bpms.model.Contacts;
import com.bpms.model.vo.ContactsModel;

/**
 * bpms 贷款人的紧急联系人service
 * @author panchuanhe
 * @date 2015/6/16
 */
public interface ContactsService{
    /**
     * 根据贷款人id查询紧急联系人信息
     * @param id 贷款人id
     * @author panchuanhe
     */
	public List<ContactsModel> findListById(String id);
	/**
	 * 保存紧急联系人
	 * @param c 紧急联系人,loanOrderId 订单ID
	 * @author panchuanhe
	 */
	public boolean saveContacts(ContactsModel contactsModel);
	/**
	 * 根据贷款人id查询紧急联系人信息的总条数
	 * @param id
	 * @return
	 */
	public Long count(String id);
	/**
	 * 删除
	 */
	public boolean doDeleteById(Contacts c);
	/**
	 * 根据订单ID查询配偶信息
	 * @param oid
	 * @return
	 */
	public Contacts findContactByOid(String oid);
	/**
	 * 根据订单ID查询订单所有紧急联系人
	 * @param oid
	 * @return
	 */
	public List<ContactsModel> findContactsByOid(String oid);
	/**
	 * 根据紧急联系人的id，查询一个紧急联系人
	 */
	public Contacts findByContactId(String id);
}
