package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.Investor;
import com.bpms.util.PageUtil;

/**
 * 投资订单的service接口
 * 
 * @author liuhh
 *
 */
public interface InvestorService {
	/**
	 * 持久化终投资人
	 */
	public boolean persistenceInvestor(Investor investor);
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月15日 上午9:22:02
	 * @Title:findInvestorList
	 * @Description:TODO 投资客户列表（这里描述这个方法的作用）
	 * @param map
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	public List<Investor> findInvestorList(Map<String, Object> map, PageUtil pageUtil);
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月15日 上午9:23:09
	 * @Title:count
	 * @Description:TODO 查询总数（这里描述这个方法的作用）
	 * @param map
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	public Long getCount(Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月15日 下午1:18:23
	 * @Title:delInvestor
	 * @Description:TODO 删除客户（这里描述这个方法的作用）
	 * @param ids
	 * @return
	 * @throws:
	 */
	boolean delInvestor(String ids);
	/**
	 * @Title: findInvestorByInvestorId 
	 * @Description: TODO 根据投资人id查询一个投资人
	 * @param @param InvestorId
	 * @param @return
	 * @return Investor
	 * @throws
	 */
	Investor findInvestorByInvestorId(String InvestorId);

}
