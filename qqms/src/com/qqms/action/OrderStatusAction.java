package com.qqms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.qqms.service.OrderStatusService;

/**
 * 贷款订单状态Action
 * 
 * @author zhangjian
 * @date 2015/06/30
 */
@Namespace("/orderStatus")
@Action(value = "orderStatusAction")
public class OrderStatusAction extends BaseAction {

	private static final long serialVersionUID = 2528391873187454591L;

	@Autowired
	private OrderStatusService orderStatusService;

	public String findAllOrderStatus() {
		OutputJson(orderStatusService.findAllOrderStatus());
		return null;
	}

}
