package com.bpms.service;

import java.util.List;

import com.bpms.model.InvestorderAndProducts;
import com.bpms.util.PageUtil;

/**
 * 投资的订单和联系人的关联关系维护Service
 * 
 * @author liuhh
 *
 */
public interface InvestorderAndProductsService {
	/**
	 * 持久化投资订单的信息
	 */
	public boolean persistenceInvestorderAndProducts(
			InvestorderAndProducts investorderAndProducts);
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月24日 下午1:56:52
	 * @Title:findInvestorderAndProductsList
	 * @Description:TODO 根据订单id查询与该订单有关的理财产品列表（这里描述这个方法的作用）
	 * @param investOrderId
	 * @return
	 * @throws:
	 */
	public List<InvestorderAndProducts> findInvestorderAndProductsList(String investOrderId);
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月24日 下午2:27:02
	 * @Title:counts
	 * @Description:TODO 根据订单id查询与该订单有关的理财产品总数（这里描述这个方法的作用）
	 * @param investOrderId
	 * @return
	 * @throws:
	 */
	public Long counts(String investOrderId);
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月27日 上午11:27:02
	 * @Title:deleteInvestorderAndProducts
	 * @Description:TODO 根据ids删除 订单里的理财产品（这里描述这个方法的作用）
	 * @param ids
	 * @return
	 * @throws:
	 */
	public boolean deleteInvestorderAndProducts(String ids);
	
	/**
	 * 
	 * @time:2015年8月6日 下午1:51:11
	 * @Title:findInvestPerformanceReportListByDate
	 * @Description:TODO 根据查询日期和机构查询理财业绩报表（这里描述这个方法的作用）
	 * @param queryDate
	 * @param orgId
	 * @param pageUtil
	 * @return
	 * @throws:
	 */
	public Object[] findInvestPerformanceReportListByDate(String queryDate,String orgId,PageUtil pageUtil);

	/**
	 * 
	 * @time:2015年8月10日 上午11:00:21
	 * @Title:findInterestDateAndEndDateByBeginDate
	 * @Description:TODO 根据划扣日期计算计息日和到期日（这里描述这个方法的作用）
	 * @param beginDate 划扣日期
	 * @param lendingCycle 出借周期
	 * @return
	 * @throws:
	 */
	public Object[] findInterestDateAndEndDateByBeginDate(String beginDate,String lendingCycle);
}
