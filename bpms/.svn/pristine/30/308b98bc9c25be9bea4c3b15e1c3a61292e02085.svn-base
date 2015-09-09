package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.AccountsJournal;
import com.bpms.util.PageUtil;

public interface AccountsJournalService {

	/**
	 * 持久化账目流水
	 * 
	 * @return
	 */
	public boolean persistenceAccountsJournal(AccountsJournal accountsJournal);

	/**
	 * 根据持久化账目流水id删除持久化流水的信息
	 */
	public boolean deleteAccountsJournal(String id);

	/**
	 * 根据id获取账目的信息
	 * 
	 * @param id
	 *            获取账目信息的id
	 * @return 返回账目信息
	 */
	public AccountsJournal findAccountsJournalById(String id);

	/**
	 * 分页查询账目流水
	 * 
	 * @param hql
	 *            指定的hql
	 * @param params
	 *            条件
	 * @param page
	 *            当前页
	 * @param rows
	 *            大小
	 * @return 根据条件查询当前页的对象列表
	 */
	public List<AccountsJournal> findAccountsJournal(Map<String, Object> map,
			PageUtil pageUtil);

	/**
	 * 查询账目流水的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countAccountsJournal(Map<String, Object> map, PageUtil pageUtil);

}
