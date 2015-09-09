package com.bpms.service;

import java.util.Date;
import java.util.List;

import com.bpms.model.vo.InvestorAndInvestProductModel;
import com.bpms.model.vo.LoanOrderModel;

/**
 * @ClassName: DebtMatchingService 
 * @Description: 债券匹配service
 * @author ZHANGJIAN 
 * @date 2015年8月19日  
 */
public interface DebtMatchingService {
	
	/**
	 * @Title: findLoanOrderByLoanOrderId 
	 * @Description: TODO 根据id查询一个实体对象
	 * @param @param loanOrderId
	 * @param @return
	 * @author PANCHUANHE
	 * @return LoanOrderModel
	 * @date 2015年8月19日 上午11:30:27
	 * @throws
	 */
	public LoanOrderModel findLoanOrderByLoanOrderId(String loanOrderId);
	/**
	 * @Title: findListByOrderStatus 
	 * @Description: TODO 根据订单状态查询列表
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<InvestorAndInvestProductModel>
	 * @date 2015年8月19日 下午3:08:44
	 * @throws
	 */
	public List<InvestorAndInvestProductModel> findListByOrderStatus(String investOrderIds,Date loanBgDate,InvestorAndInvestProductModel model);
	
	/**
	 * @Title: getAutoDebtMatchingData 
	 * @description 获取自动债权匹配的数据记录
	 * @author ZHANGJIAN
	 * @return String
	 * @throws
	 */
	public List<InvestorAndInvestProductModel> getAutoDebtMatchingInvestOrders(
			String loanOrderId);

}
