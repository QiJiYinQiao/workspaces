package com.bpms.service;

import java.util.List;
import java.util.Map;

public interface InvestReportExportService {
	/**
	 * 查询理财客户汇总表(分页)
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> queryInvestorSummaryStatement(Map<String,Object> param);
	/**
	 * 查询理财客户汇总表(分页)总条数
	 * @param param
	 * @return
	 */
	Integer queryInvestorSummaryStatementCount(Map<String,Object> param);
	/**
	 * 查询团队经理
	 * @param investOrderId
	 * @return
	 */
	Map<String,Object> queryTeamManager(String investOrderId);
	/**
	 * 查询大团经理
	 * @param investOrderId
	 * @return
	 */
	Map<String,Object> queryGroupManager(String investOrderId);
	
}
