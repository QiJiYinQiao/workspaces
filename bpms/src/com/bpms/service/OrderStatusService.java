package com.bpms.service;

import java.util.List;
import java.util.Map;

import com.bpms.model.OrderStatus;
import com.bpms.util.PageUtil;

/**
 * 
 * 订单状态的service接口
 * 
 * @author 孙一般 2015/05/25.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/25 孙一般 创建.
 */
public interface OrderStatusService {

	/**
	 * 分页获取订单状态
	 * 
	 * @param map
	 *            获取信息的参数
	 * @param pageUtil
	 *            获取信息的分页工具
	 * @return 当前页的订单状态
	 */
	List<OrderStatus> findOrderStatusAllList(Map<String, Object> map,
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
	 * @param orderStatus
	 * 
	 * @return 增加是否成功
	 */
	boolean persistenceOrderStatus(OrderStatus orderStatus);

	/**
	 * 删除订单状态
	 * 
	 * @param id
	 *            订单状态的id
	 * @return 删除是否成功
	 */
	boolean deleteOrderStatus(String id);

	/**
	 * 根据状态的code获取对应的状态的信息
	 * 
	 * @param statusCode
	 *            状态的code
	 * @return 状态的信息
	 */
	OrderStatus getOrderStatusByStatusCode(String statusCode);

}
