package com.bpms.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.OrderStatus;
import com.bpms.service.OrderStatusService;
import com.bpms.util.Constants;
import com.bpms.util.PageUtil;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 
 * 流程状态请求处理器的action
 * 
 * @author 孙一般 2015/05/25.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/25 孙一般 创建.
 */
@Namespace("/orderStatus")
@Action(value = "orderStatusAction")
public class OrderStatusAction extends BaseAction implements
		ModelDriven<OrderStatus> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 3658084064057123814L;

	/** 模型驱动. */
	private OrderStatus orderStatus = new OrderStatus();

	/** 自动注入service. */
	@Autowired
	private OrderStatusService orderStatusService;

	@Override
	public OrderStatus getModel() {
		return orderStatus;
	}

	/**
	 * 分页获取流程状态列表
	 * 
	 * @return 流程状态列表的json信息
	 * @throws Exception
	 */
	public String findOrderStatusAllList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != searchValue && !"".equals(searchValue)) {
			map.put(searchName, Constants.GET_SQL_LIKE + searchValue
					+ Constants.GET_SQL_LIKE);
		}
		PageUtil pageUtil = new PageUtil(page, rows, searchAnds,
				searchColumnNames, searchConditions, searchVals);
		GridModel gridModel = new GridModel();
		gridModel.setRows(orderStatusService.findOrderStatusAllList(map,
				pageUtil));
		gridModel.setTotal(orderStatusService.getCount(map, pageUtil));
		OutputJson(gridModel);
		return null;
	}

	/**
	 * 持久化数据--保存或修改信息
	 * 
	 * @return 保存成功或保存失败
	 * @throws Exception
	 */
	public String persistenceOrderStatus() {
		OutputJson(
				getMessage(orderStatusService
						.persistenceOrderStatus(getModel())),
				Constants.TEXT_TYPE_PLAIN);
		return null;
	}

	/**
	 * 删除流程状态的信息
	 * 
	 * @return 删除流程状态是否成功
	 * @throws Exception
	 */
	public String deleteOrderStatus() {
		OutputJson(getMessage(orderStatusService.deleteOrderStatus(getModel()
				.getStatusId())));
		return null;
	}

}
