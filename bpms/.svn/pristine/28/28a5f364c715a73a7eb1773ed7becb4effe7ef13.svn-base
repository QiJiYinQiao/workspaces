package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.Loaner;
import com.bpms.model.LoanerInfoHis;
import com.bpms.util.PageUtil;

/**
 * 
 * 贷款人变更履历service
 * 
 * @author 孙一般 2015/06/16.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 孙一般 2015/06/16 创建.
 */
public interface LoanerInfoHisService {
	/**
	 * 分页查询所有贷款人信息变更履历
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
	public List<LoanerInfoHis> findAllLoanerHistory(Map<String, Object> map,
			PageUtil pageUtil);

	/**
	 * 查询所有贷款人的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countAllLoanerHistory(Map<String, Object> map, PageUtil pageUtil);
	
	/**
	 * 保存联系人变更履历
	 * @param loanerHis
	 */
	public void saveLoanerHis(Loaner loaner);
}
