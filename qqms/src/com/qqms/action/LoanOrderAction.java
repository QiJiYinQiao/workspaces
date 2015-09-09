package com.qqms.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.qqms.model.LoanOrder;
import com.qqms.service.LoanOrderService;
import com.qqms.util.PageUtil;
import com.qqms.viewModel.GridModel;
/**
 * 贷款订单信息Action
 * @author zhangjian
 * @date 2015/06/23
 */
@Namespace("/loanOrder")
@Action(value = "loanOrderAction")
public class LoanOrderAction extends BaseAction{

	private static final long serialVersionUID = -1677449778207937084L;	
	
	//用于存储高级查询条件
	private String loanOrderId;  //贷款订单编号
	private String loanerName;   //贷款人姓名
	private String idNo;         //贷款人身份证编号
	private String loanType;     //贷款类型：工薪贷 or 事业贷
	private String orderStatus;  //贷款订单状态。
	private Date applyDate;      //贷款申请日期，开始日期范围
	private Date applyDate02;    //贷款申请日期，结束日期范围
	private String loanAmount;   //贷款申请额度
	private String loanMin;      //最低接受额度		

	@Autowired
	private LoanOrderService loanOrderService;
	
	/**
	 * 将客户端输入的查询参数放入Map结构中。
	 * @param map
	 */
	private Map<String, Object> encapsulteQueryParamIntoMap(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanOrderId", loanOrderId);
		map.put("loanerName", loanerName);
		map.put("idNo", idNo);
		map.put("loanType", loanType);
		map.put("orderStatus", orderStatus);
		map.put("applyDate", applyDate);
		map.put("applyDate02", applyDate02);
		map.put("loanAmount", loanAmount);
		map.put("loanMin", loanMin);
		return map;
	}
	
	public String findAllLoanOrderList(){
		//封装用户的查询条件。		
		Map<String, Object> queryParamMap = encapsulteQueryParamIntoMap();
		PageUtil pageUtil = new PageUtil(page,rows);		
		Object[] objs = loanOrderService.findAllLoanOrderList(queryParamMap, pageUtil);
		List<LoanOrder> loanOrderList = (List<LoanOrder>) objs[0];
		Long totoalRowNum = (Long) objs[1];					
		GridModel gridModel = new GridModel();				
		gridModel.setRows(loanOrderList);
		gridModel.setTotal(totoalRowNum);
		OutputJson(gridModel);	
		return null;
		
	}

	public String getLoanOrderId() {
		return loanOrderId;
	}

	public void setLoanOrderId(String loanOrderId) {
		this.loanOrderId = loanOrderId;
	}

	public String getLoanerName() {
		return loanerName;
	}

	public void setLoanerName(String loanerName) {
		if(loanerName != null){			
		}
		this.loanerName = loanerName;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanMin() {
		return loanMin;
	}

	public void setLoanMin(String loanMin) {
		this.loanMin = loanMin;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getApplyDate02() {
		return applyDate02;
	}

	public void setApplyDate02(Date applyDate02) {
		this.applyDate02 = applyDate02;
	}


}
