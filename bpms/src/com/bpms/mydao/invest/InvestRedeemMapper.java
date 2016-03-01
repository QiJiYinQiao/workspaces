package com.bpms.mydao.invest;

import java.util.List;
import java.util.Map;

public interface InvestRedeemMapper {
	
	/**
	 * 获得所有已经完成审批的投资申请订单。
	 * @Title: findAlreadyApprovedInvestApplyList 
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月16日 上午11:32:04
	 */
	public List<Map<String, Object>> findAlreadyApprovedInvestApplyList(Map<String, Object> param);
	
	/**
	 * 统计所有有待进行投资赎回申请的Invest信息的条数。
	 * @Title: countApprovedInvestList4Redeem 
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月16日 上午11:33:08
	 */
	public Long countAlreadyApprovedInvestApplyList(Map<String, Object> param);
		
	/**
	 * 查询投资赎回申请的数据
	 * @Title: findInvestRedeemList 
	 * @param @param param
	 * @author ZHANGJIAN
	 * @return List<Map<String,Object>>
	 * @date 2015年12月17日 上午11:04:50
	 * @throws
	 */
	public List<Map<String, Object>> findInvestRedeemList(Map<String, Object> param);
	
	/**
	 * 统计投资赎回信息的条数。
	 * @Title: countInvestRedeemList 
	 * @param @param param
	 * @author ZHANGJIAN
	 * @return Long
	 * @date 2015年12月17日 上午11:05:39
	 */
	public Long countInvestRedeemList(Map<String, Object> param);	
	
	/**
	 * 修改投资赎回的申请日期
	 * @Title: updateInvestRedeemBeginDate 
	 * @param @param param
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2015年12月18日 上午11:44:01
	 */
	public int updateInvestRedeemBeginDate(Map<String, Object> param);
	
	/**
	 * 获取办理投资赎回任务时的数据。
	 * @Title: findInvestRedeemInfo4HandleTask 
	 * @param @param param
	 * @author ZHANGJIAN
	 * @return Map<String,Object>
	 * @date 2015年12月18日 下午2:35:13
	 * @throws
	 */
	public Map<String, Object> findInvestRedeemInfo4HandleTask(Map<String, Object> param);
				
	
	/**
	 * 获取投资赎回客户明细信息(包括理财金额，违约金，服务费，已付利息，应回款)
	 * @Title: findRedeemInvestorFinancialDetails 
	 * @Description: TODO
	 * @param @param param
	 * @param @return
	 * @author ZHANGJIAN
	 * @return List<Map<String,Object>>
	 * @date 2015年12月31日 上午11:23:56
	 * @throws
	 */
	public List<Map<String, Object>> findRedeemInvestorFinancialDetails(Map<String, Object> param);
	
	
	/**
	 * 统计投资赎回客户明细信息(包括理财金额，违约金，服务费，已付利息，应回款)
	 * @Title: countRedeemInvestorFinancialInfo4ExportReport 
	 * @Description: TODO
	 * @param @param param
	 * @param @return
	 * @author ZHANGJIAN
	 * @return Long
	 * @date 2015年12月31日 上午11:26:10
	 * @throws
	 */
	public Long countRedeemInvestorFinancialDetails(Map<String, Object> param);
	
	/**
	 * 根据investOrderId，获取投资赎回时，团队经理和大团经理的名字。
	 * @Title: findTuanDuiJingLiAndDaTuanJingLiByOrderId 
	 * @param param
	 * @author ZHANGJIAN
	 * @return Map<String,Object>
	 * @date 2015年12月31日 上午10:50:04
	 * @throws
	 */
	public Map<String, Object> findTuanDuiJingLiAndDaTuanJingLiByOrderId(Map<String, Object> param);
}
