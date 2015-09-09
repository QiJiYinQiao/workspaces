package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.CreditInvestigation;
import com.bpms.util.PageUtil;

public interface CreditInvestigationService {

	/**
	 * 持久化信审报告
	 * 
	 * @return
	 */
	public boolean persistenceCreditInvestigation(
			CreditInvestigation creditInvestigation);

	/**
	 * 
	 * 根据id获取信息报告的信息
	 * @return
	 */
	public CreditInvestigation findCreditInvestigationById(String id);

	/**
	 * 分页查询信审报告
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
	public List<CreditInvestigation> findCreditInvestigation(
			Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 查询信审报告的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countCreditInvestigation(Map<String, Object> map,
			PageUtil pageUtil);

}
