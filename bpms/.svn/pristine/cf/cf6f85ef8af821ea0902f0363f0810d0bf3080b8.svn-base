package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.FinalAuditReport;
import com.bpms.util.PageUtil;

public interface FinalAuditReportService {

	/**
	 * 持久化终审资质分析
	 * 
	 * @return
	 */
	public boolean persistenceFinalAuditReport(FinalAuditReport finalAuditReport);

	/**
	 * 根据订单的Id获取终审资质分析报告的信息
	 */
	public FinalAuditReport findFinalAuditReportByLoanOrderId(String loanOrderId);

	/**
	 * 分页查询终审资质分析
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
	public List<FinalAuditReport> findFinalAuditReport(Map<String, Object> map,
			PageUtil pageUtil);

	/**
	 * 查询终审资质分析的个数
	 * 
	 * @param hql
	 *            指定的hql
	 * @return 对象的个数
	 */
	public Long countFinalAuditReport(Map<String, Object> map, PageUtil pageUtil);

}
