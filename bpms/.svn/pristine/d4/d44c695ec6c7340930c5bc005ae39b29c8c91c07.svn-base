package com.bpms.listener.investRedeem;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.service.InvestOrderService;

public class InvestRedeemStopExecutionListener implements ExecutionListener {

	private static final long serialVersionUID = -8090900348891490793L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		//获取Spring容器
		WebApplicationContext curWebAppContext = ContextLoader.getCurrentWebApplicationContext();
		//从Spring容器中，获得InvestOrderService这个Bean
		InvestOrderService investOrderService = (InvestOrderService) curWebAppContext.getBean("investOrderService");
		//获取investOrderId
		String investOrderId = "";
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			investOrderId = execution.getProcessBusinessKey().split("\\.")[1];
		}				
		//修改当前InvestOrder的processStatus字段。
		investOrderService.updateInvestOrderStatus(investOrderId, "2");		
	}
}
