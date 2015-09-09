package com.bpms.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.service.InvestOrderService;
/**
 * @ClassName: InvestOrderStatusExecutionListener 
 * @Description: TODO 【提交订单】的监听
 * @author PANCHUANHE
 * @date 2015年7月24日 上午10:32:12
 */
public class InvestOrderStatusExecutionListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		//获取当前上下文
		WebApplicationContext wx = ContextLoader.getCurrentWebApplicationContext();
		//获取investOrderService实现类
		InvestOrderService investOrderServiceImpl = (InvestOrderService)wx.getBean("investOrderService");
		ExecutionEntity executionEntity = (ExecutionEntity)execution;
		if(executionEntity.getTransition()!=null){
			String investOrderId = "";
			if(StringUtils.isNotBlank(execution.getProcessBusinessKey())){
				investOrderId = execution.getProcessBusinessKey().split("\\.")[1];
			}
			investOrderServiceImpl.updateInvestOrderStatus(investOrderId, executionEntity.getTransition().getId());
		}
	}

}
