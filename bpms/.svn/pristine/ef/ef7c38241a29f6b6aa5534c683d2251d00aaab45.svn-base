package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.OrderStatus;
import com.bpms.service.OrderStatusService;
import com.bpms.util.Collections;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

/**
 * 
 * 订单状态的serviceImp
 * 
 * @author 孙一般 2015/05/25.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/25 孙一般 创建.
 */
@Service("orderStatusService")
public class OrderStatusServiceImpl implements OrderStatusService {
	@Autowired
	private BaseDAO<OrderStatus> baseDAO;

	@Override
	public List<OrderStatus> findOrderStatusAllList(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "from OrderStatus t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from OrderStatus t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

	@Override
	public boolean persistenceOrderStatus(OrderStatus orderStatus) {
		if (null == orderStatus.getStatusId()) {
			baseDAO.save(orderStatus);
		} else {
			baseDAO.update(orderStatus);
		}
		return true;
	}

	@Override
	public boolean deleteOrderStatus(String id) {
		baseDAO.delete(baseDAO.get(OrderStatus.class, id));
		return true;
	}

	@Override
	public OrderStatus getOrderStatusByStatusCode(String statusCode) {
		List<OrderStatus> list = baseDAO
				.find("from OrderStatus o where o.statusCode ='" + statusCode
						+ "'");
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
