package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.Assets;
import com.bpms.util.PageUtil;

public interface AssetsService {

	/**
	 * 持久化资产
	 * 
	 * @return
	 */
	public boolean persistenceAssets(Assets assets);

	/**
	 * 根据财产的id获取财产的信息
	 * 
	 * @param id
	 *            财产的id
	 * @return 财产的信息
	 */
	public Assets findAssetsById(String id);

	/**
	 * 分页查询资产
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
	public List<Assets> findAssets(Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 查询资产的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countAssets(Map<String, Object> map, PageUtil pageUtil);

}
