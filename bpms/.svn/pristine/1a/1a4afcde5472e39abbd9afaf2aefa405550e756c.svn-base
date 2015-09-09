package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.CreditAuditReport;
import com.bpms.util.PageUtil;

public interface CreditAuditReportService {

	/**
	 * 持久化信审报告
	 * 
	 * @return
	 */
	public boolean persistenceCreditAuditReport(
			CreditAuditReport creditAuditReport);

	/**
	 * 根据订单的id获取稽核信息报告
	 * 
	 * @param loanOrderId
	 *            订单的id
	 * @return 稽核信息报告
	 */
	public CreditAuditReport findCreditAuditReportByLoanOrderId(
			String loanOrderId);
	
	/**
	 * 通过id获取信审报告的信息
	 * @param id
	 * @return
	 */
	public CreditAuditReport findCreditAuditReoprtById(String id);

	/**
	 * 分页查询信审报告
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
	public List<CreditAuditReport> findCreditAuditReport(
			Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 查询信审报告的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countCreditAuditReport(Map<String, Object> map,
			PageUtil pageUtil);

}
