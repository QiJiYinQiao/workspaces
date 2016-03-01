package com.bpms.mydao.invest;

import java.util.List;
import java.util.Map;

/**
 * 投资报表数据查询
 * 
 * @author sunyiban
 *
 */
public interface InvestReportExportMapper {
	/**
	 * 查询理财客户汇总
	 * @param param
	 * @return
	 */
	List<Map<String,Object>> queryInvestorSummaryStatement(Map<String,Object> param);
	Integer queryInvestorSummaryStatementCount(Map<String,Object> param);
	Map<String,Object> queryTeamManager(String investOrderId);
	Map<String,Object> queryGroupManager(String investOrderId);
}
