package com.bpms.service;

import java.util.List;

import com.bpms.model.LoanorderAndCompany;

/**
 * bpms 公司信息与订单servic
 * 
 * @author panchuanhe
 * @date 2015/6/16
 */
public interface LoanorderAndCompanyService {
	/**
	 * 新增
	 * 
	 * @param la
	 *            公司信息与订单关系类实例
	 * @return
	 */
	boolean saveLoanorderAndCompany(LoanorderAndCompany la);

	/**
	 * 删除
	 */
	boolean deleteByComIdAndLoanOrderId(String ComId, String LoanOrderId);

	/**
	 * 根据工作单位id查询是否该工作单位被订单绑定
	 * 
	 * @param ComId
	 *            工作单位id
	 * @return
	 */
	List<LoanorderAndCompany> findListByComId(String ComId);

	/**
	 * 根据订单id查询
	 * 
	 * @param loanOrderId
	 * @return
	 */
	List<LoanorderAndCompany> findListByLoanOrderId(String loanOrderId);

	/**
	 * 根据订单id删除，删除该订单的所有绑定的公司
	 * 
	 * @param loanOrderId
	 */
	void deleteByLoanOrderId(String loanOrderId);

	/**
	 * 根据订单的id获取单位的个数
	 * 
	 * @param loanOrderId
	 * @return
	 */
	Long findCountByLoanOrderId(String loanOrderId);
}
