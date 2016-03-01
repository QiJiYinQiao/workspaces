package com.bpms.listener.investRedeem;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.service.InvestOrderService;

public class InvestRedeemStartExecutionListener implements ExecutionListener {

	private static final long serialVersionUID = -7374162927147458598L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		//1.获取Spring容器对象
		WebApplicationContext curWebAppContext = ContextLoader.getCurrentWebApplicationContext();
		//2.获取InvestOrderService对象。
		InvestOrderService investOrderService = (InvestOrderService) curWebAppContext.getBean("investOrderService");
		//3.获得与本流程相关的InvestOrderId
		String investOrderId = "";
		if(StringUtils.isNotBlank(execution.getProcessBusinessKey())){
			investOrderId = execution.getProcessBusinessKey().split("\\.")[1];
		}			
		//4.修改“InvestOrderId所属”的投资订单对象(InvestOrder)的流程状态。
		//备注：记录流程整体状态，0－未开启，1－已开启，2－已结束
		investOrderService.updateInvestOrderProcessStatus(investOrderId, "1");
	}
}
