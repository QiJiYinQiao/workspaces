package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.CreditCardsDetails;
import com.bpms.util.PageUtil;

public interface CreditCardsDetailsService {

	/**
	 * 持久化信用卡信息
	 * 
	 * @return
	 */
	public boolean persistenceCreditCardsDetails(
			CreditCardsDetails creditCardsDetails);

	/**
	 * 根据id获取信用卡的信息
	 * 
	 * @param id
	 * @return
	 */
	public CreditCardsDetails findCreditCradsDetailsById(String id);

	/**
	 * 分页查询信用卡信息
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
	public List<CreditCardsDetails> findCreditCardsDetails(
			Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 查询信用卡信息的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countCreditCardsDetails(Map<String, Object> map,
			PageUtil pageUtil);

}
