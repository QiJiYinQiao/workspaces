package com.bpms.service;

import java.util.List;

import com.bpms.model.InvestOrder;
import com.bpms.model.Investor;
import com.bpms.model.vo.InvestorAndInvestOrderModel;
import com.bpms.model.vo.InvestorAndInvestProductModel;
import com.bpms.util.PageUtil;

/**
 * @ClassName: InvestOrderService 
 * @Description: 投资订单service
 * @author PANCHUANHE 
 * @date 2015年7月20日 下午1:41:40
 */
public interface InvestOrderService {
	/**
	 * 持久化投资订单的信息
	 */
	public boolean persistenceInvestOrder(InvestOrder investOrder);
	/**
	 * 查询全部数据
	 * @return list
	 */
	public List<InvestOrder> findAllInvestOrderList();
	/**
	 * @Title: findListByField 
	 * @Description: 根据条件查询
	 * @param @param hql
	 * @param @return
	 * @return List<InvestOrder>
	 * @throws
	 */
	public List<InvestOrder> findListByField(String hql);
	/**
	 * @Title: findListByInvestorAndInvestOrder 
	 * @Description: 查询投资人和订单列表
	 * @param @return
	 * @return List<InvestorAndInvestOrderModel>
	 * @throws
	 */
	public List<InvestorAndInvestOrderModel> findListByInvestorAndInvestOrder(PageUtil pageUtil);
	/**
	 * @Title: doDeleteInvestOrder 
	 * @Description: 订单删除
	 * @param @param investOrderId
	 * @param @return
	 * @return boolean
	 * @throws
	 */
	public boolean doDeleteInvestOrder(String investOrderId);
	/**
	 * @Title: countList 
	 * @Description: 查询列表总条数
	 * @param @return
	 * @return Integer
	 * @throws
	 */
	public Long count();
	/**
	 * @Title: updateInvestOrderProcessStatus 
	 * @Description: TODO 改变订单大状态
	 * @param @param investOrderId
	 * @param @param zhuangtai
	 * @return void
	 * @throws
	 */
	public void updateInvestOrderProcessStatus(String investOrderId,String zhuangtai);
	/**
	 * @Title: findByInvestOrderId 
	 * @Description: TODO 根据id查询一个对象
	 * @param @param InvestOrderId
	 * @param @return
	 * @return InvestOrder
	 * @throws
	 */
	public InvestOrder findByInvestOrderId(String InvestOrderId);
	/**
	 * @Title: updateInvestOrderStatus 
	 * @Description: TODO 更新投资订单状态状态
	 * @param @param InvestOrderId 订单id
	 * @param @param status 状态
	 * @return void
	 * @throws
	 */
	public void updateInvestOrderStatus(String InvestOrderId,String status);
	
	/**
	 * @Title: 
	 * @Description: TODO 根据投资订单ID，去查询投资人和其投资的理财产品的信息。
	 * @param InvestOrderId 订单id
	 * @return List<Object[]>
	 * @throws
	 */
	public List<InvestorAndInvestProductModel> findInvestorAndInvestProductsDetails(
			String investOrderId);
	
	/**
	 * @Title: findInvestOrderListByOrderStatus 
	 * @Description: TODO 根据订单状态查询投资订单列表,债权匹配
	 * @param @return
	 * @return List<InvestOrder>
	 * @throws
	 */
	public List<InvestOrder> findInvestOrderListByOrderStatus();
	
	/**
	 * @Title: findInvestOrderListByInvestOrderIds 
	 * @Description: TODO 根据ids查询列表
	 * @param @return
	 * @return List<InvestOrder>
	 * @throws
	 */
	public List<InvestOrder> findInvestOrderListByInvestOrderIds(String investOrderIds);
	
	/**
	 * 
	 * @time:2015年7月31日 下午3:55:31
	 * @Title:updateInvestOrderStauts
	 * @Description:TODO 更新订单状态（这里描述这个方法的作用）
	 * @param investOrderId
	 * @param result
	 * @throws:
	 */
	public void updateInvestOrderStauts(String investOrderId, String result);
	
	/**
	 * 
	 * @time:2015年8月14日 下午2:20:05
	 * @Title:persistenceInvestOrder
	 * @Description:TODO 当部门助理修改客户信息同时更新订单（这里描述这个方法的作用）
	 * @param investOrderId
	 * @param investor
	 * @return
	 * @throws:
	 */
	public boolean persistenceInvestOrder(String investOrderId,Investor investor);
	
}
