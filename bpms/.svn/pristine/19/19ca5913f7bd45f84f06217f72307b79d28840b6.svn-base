package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.Loaner;
import com.bpms.model.vo.LoanerModel;
import com.bpms.util.PageUtil;

/**
 * 
 * 贷款人service
 * 
 * @author 孙一般 2015/06/16.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 孙一般 2015/06/16 创建.
 */
public interface LoanerService {
	/**
	 * 持久化贷款人信息
	 */
	LoanerModel persistenceLoaner(LoanerModel loanerModel);

	/**
	 * 更新贷款人信息
	 * 
	 * @param loaner
	 */
	public void updateLoaner(Loaner loaner);

	/**
	 * 分页查询所有贷款人信息
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
	public List<Loaner> findLoaner(Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 查询所有贷款人的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countAllLoaner(Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 根据参数获取下拉表格的值
	 * 
	 * @param q
	 *            模糊查询的参数
	 * @return 检索去的用户信息的值
	 */
	public List<LoanerModel> findCombogridLoaner(String q);
	/**
	 * 根据id查询一个实体
	 * @param id 贷款人id
	 * @return
	 */
	public Loaner findById(String id);
}
