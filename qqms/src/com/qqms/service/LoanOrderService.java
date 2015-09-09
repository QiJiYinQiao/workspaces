package com.qqms.service;

import java.util.List;
import java.util.Map;

import com.qqms.model.LoanOrder;
import com.qqms.util.PageUtil;

public interface LoanOrderService extends BaseService {

	/**
	 * 分页获取所有的业务贷款订单
	 * 
	 * @param map
	 *            获取信息的参数，封装用户的查询条件。
	 * @param pageUtil 
	 *            分页的工具
	 * @return 当前页的个人贷款业务单
	 */	
	public Object[] findAllLoanOrderList(Map<String, Object> map, PageUtil pageUtil);
	
}
