package com.oasys.listener.CallingCard;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.CardApplyService;
import com.oasys.util.Constants;

public class CallingCardHoApplyStartListener implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			CardApplyService cardApplyService = (CardApplyService) ctx.getBean("cardApplyService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			cardApplyService.updateStatus(id,"2");
		}
	}
}