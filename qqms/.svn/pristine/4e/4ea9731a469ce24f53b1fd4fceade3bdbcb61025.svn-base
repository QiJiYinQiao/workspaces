package com.qqms.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqms.dao.PublicDao;
import com.qqms.model.OrderStatus;
import com.qqms.service.OrderStatusService;
import com.qqms.viewModel.ComboBoxModel;

/**
 * 贷款订单状态Service实现类
 * @author zhangjian
 * @date 2015/06/30
 */
@Service("orderStatusService")
public class OrderStatusServiceImpl extends BaseServiceImpl implements
		OrderStatusService {
	
	/** 自动注入实体共同dao */
	@Autowired
	private PublicDao<OrderStatus> publicDao;

	@Override
	public List<ComboBoxModel> findAllOrderStatus() {
		
		List<ComboBoxModel> allOrderStatus = new ArrayList<ComboBoxModel>();
		
		String sql = "select t1.status_id as STATUS_ID, t1.status_name as STATUS_NAME from t_bp_order_status t1";		
		List<Object[]> resultsArrayList = publicDao.findBySQL(sql);

		for(Object[] objArray : resultsArrayList){
			ComboBoxModel oneOrderStatus = new ComboBoxModel((String)objArray[0], (String)objArray[1]);
			allOrderStatus.add(oneOrderStatus);			
		}
		
		return allOrderStatus;
	}
		
}
