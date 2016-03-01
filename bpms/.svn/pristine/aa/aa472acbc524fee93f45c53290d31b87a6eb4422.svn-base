package com.bpms.mydao.invest;

import java.util.List;
import java.util.Map;

/**
 * 投资申请列表的查询
 * @author ZHANGJIAN
 */
public interface InvestApplyMapper {
	
	/**
	 * 获取投资申请的DataGrid 
	 * @Title: findInvestApplyList 
	 * @param Map<String, Object>
	 * @return List<Map<String, Object>>
	 * @author ZHANGJIAN
	 * @date 2015年12月2日 下午6:13:33
	 */
	public List<Map<String, Object>> findInvestApplyList(Map<String, Object> param);
	
	/**
	 * 统计符合条件的投资申请个数
	 * @Title: countInvestApplyList 
	 * @param Map<String, Object>
	 * @return Long
	 * @author ZHANGJIAN
	 * @date 2015年12月2日 下午6:24:38
	 * @throws
	 */
	public Long countInvestApplyList(Map<String, Object> param);
	
	
	/**
	 * 获取有待调整理财收益率的客户的相关投资信息。
	 * @Title: findInvestorAndFinancingMgrInfo4AdjustArs 
	 * @param @param param
	 * @param @return
	 * @author ZHANGJIAN
	 * @return List<Map<String,Object>>
	 * @date 2015年12月11日 上午10:56:35
	 */
	public Map<String, Object> findInvestApplyInfo4AdjustArs(Map<String, Object> param);
	
	Map<String,Object> queryPositionName(String taskId);
	
}
