package com.bpms.mydao;

import java.util.List;
import java.util.Map;

/**
 * 订单列表的查询
 * 
 * @author liuhh
 *
 */
public interface LoanOrderMapper {
	/**
	 * 获取订单列表的信息
	 * 
	 * @param param
	 *            获取订单信息的参数
	 * @return 根据条件查询的申请订单的信息
	 */
	public List<Map<String, Object>> findLoanOrderList(Map<String, Object> param);

	/**
	 * 获取订单列表信息的个数
	 * 
	 * @param param
	 *            获取订单信息的参数
	 * @return 根据条件查询到的申请订单的条数
	 */
	public long findLoanOrderListCount(Map<String, Object> param);
	
	/**
	 * 到处IPC统计表
	 * @param 导出IPC统计报表的时候的参数
	 * @return 
	 */
	public List<Map<String, Object>> exportIPCCountExcel(Map<String, Object> param);
}
