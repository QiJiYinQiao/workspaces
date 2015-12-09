package com.oasys.service;

import java.util.List;
import java.util.Map;

import com.oasys.model.StatusNameRef;
import com.oasys.util.PageUtil;

/**
 * 
 * 订单状态的service接口
 * @author LiDa
 * @date 2015/9/21
 * 
 * */

public interface StatusNameRefService {
	
	/**
	 * 分页获取订单状态
	 * 
	 * @param map
	 *            获取信息的参数
	 * @param pageUtil
	 *            获取信息的分页工具
	 * @return 当前页的订单状态
	 */
	List<StatusNameRef> findstatusNameRefAllList(Map<String, Object> map,
			PageUtil pageUtil);

	/**
	 * 获取订单状态的条数
	 * 
	 * @param map
	 *            获取信息的参数
	 * @param pageUtil
	 *            获取分页信息的工具
	 * @return 订单状态的总条数
	 */
	Long getCount(Map<String, Object> map, PageUtil pageUtil);

	/**
	 * 增加或修改订单状态信息
	 * 
	 * @param statusNameRef
	 * 
	 * @return 增加是否成功
	 */
	boolean persistenceStatusNameRef(StatusNameRef statusNameRef);

	/**
	 * 删除订单状态
	 * 
	 * @param id
	 *            订单状态的id
	 * @return 删除是否成功
	 */
	boolean deleteStatusNameRef(String id);

	/**
	 * 根据状态的code获取对应的状态的信息
	 * 
	 * @param statusCode
	 *            状态的code
	 * @return 状态的信息
	 */
	StatusNameRef getstatusNameRefByStatusCode(String statusCode);
	
	/**
	 * 根据状态的id获取对应的状态的信息
	 * 
	 * @param statusID
	 *            状态的ID
	 * @return 状态的信息
	 */
	StatusNameRef getStatusNameRefByStatusID(String statusID);
	
	/**
	 * 根据状态的code获取对应的状态的信息
	 * 
	 * @param statusCode
	 *            状态的code
	 * @return 状态的信息
	 */
	
	/**
	 * 修改流程的状态的信息
	 * 
	 * @param processStatus
	 *            流程状态信息
	 */
	void updateLoanOrderProcessStatus(String id, String processStatus);
	
}
