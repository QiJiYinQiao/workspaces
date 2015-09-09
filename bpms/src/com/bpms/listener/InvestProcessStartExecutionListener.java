package com.bpms.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.service.InvestOrderService;

/**
 * @ClassName: InvestProcessStartExecutionListener 
 * @Description: TODO 开启流程的监听
 * @author PANCHUANHE
 * @date 2015年7月22日 下午8:41:33
 */
public class InvestProcessStartExecutionListener implements ExecutionListener{
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		//得到上下文
		WebApplicationContext  ctx = ContextLoader.getCurrentWebApplicationContext();
		//得到投资订单service
		InvestOrderService investOrderService = (InvestOrderService)ctx.getBean("investOrderService");
		//订单id
		String investOrderId = "";
		//根据流程实例得到BusinessKey
		if(StringUtils.isNotBlank(execution.getProcessBusinessKey())){
			investOrderId = execution.getProcessBusinessKey().split("\\.")[1];
		}
		//改变订单流程大状态
		investOrderService.updateInvestOrderProcessStatus(investOrderId, "1");
	}

}
