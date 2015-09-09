package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.LoansDetails;
import com.bpms.util.PageUtil;

public interface LoansDetailsService {

	/**
	 * 持久化贷款详情
	 * 
	 * @return
	 */
	public boolean persistenceLoansDetails(LoansDetails loansDetails);

	/**
	 * 根据贷款详情的id获取贷款详情
	 * 
	 * @param id
	 *            贷款详情的id
	 * @return 贷款详情
	 */
	public LoansDetails findLoansDetailsById(String id);

	/**
	 * 分页查询贷款详情
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
	public List<LoansDetails> findLoansDetails(Map<String, Object> map,
			PageUtil pageUtil);

	/**
	 * 查询贷款详情的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countLoansDetails(Map<String, Object> map, PageUtil pageUtil);
}
