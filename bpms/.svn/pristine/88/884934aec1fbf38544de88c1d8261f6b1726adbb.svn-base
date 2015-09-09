package com.bpms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.bpms.model.LoanCustRepaymentDetail;
import com.bpms.model.vo.CustomerRepaymentInfoModel;
import com.bpms.model.vo.LoanCustRepaymentDetailModel;
import com.bpms.util.PageUtil;

/**
 * 贷款客户款款明细处理
 * 
 * @author liuhh
 *
 */
public interface LoanCustRepaymentDetailService {
	/**
	 * 持久化信息
	 */
	public boolean persistenceLoanCustRepaymentDetail(
			LoanCustRepaymentDetail loanCustRepaymentDetail);

	/**
	 * 客户还款详情
	 * 
	 * @param map
	 * @param pageUtil
	 * @return
	 */
	public List<CustomerRepaymentInfoModel> findLoanCustRepaymentDetail(
			Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 客户还款详情记录总条数
	 * 
	 * @param map
	 * @return
	 */
	public Long getCountoFLoanCustRepaymentDetail(Map<String, Object> map);

	/**
	 * 根据合同号查询客户还款详情
	 * 
	 * @param map
	 * @param pageUtil
	 * @return
	 */
	public List<LoanCustRepaymentDetail> findRepaymentDetailByCno(
			Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 根据合同号查询客户还款详情记录总条数
	 * 
	 * @param map
	 * @return
	 */
	public Long getCountRepaymentDetail(Map<String, Object> map);

	/**
	 * 导出客户还款明细
	 */
	public void exportLoanCustRepaymentDetail(Map<String, Object> map) throws IOException, Exception;

	/**
	 * 根据ID查询客户还款详情
	 * 
	 * @param id
	 * @return
	 */
	public LoanCustRepaymentDetail findCustRepaymentDeatilById(String id);

	/**
	 * 定时任务转催收
	 */
	public void updateOverdueReceivablesDetail();

	/**
	 * 查询还款详情
	 * 
	 * @param param
	 *            查询的参数
	 * @param page
	 *            分页工具
	 * @return 还款详情的列表
	 */
	public List<LoanCustRepaymentDetailModel> findCustRepaymentDetails(
			Map<String, Object> param, PageUtil page);

	/**
	 * 查询还款详情的个数
	 * 
	 * @param param
	 *            查询的参数
	 * @return 还款详情的个数
	 */
	public Long findCustRepaymentDetialsCount(Map<String, Object> param);
}
