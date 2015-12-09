package com.oasys.listener.CredentialsApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.CardApplyService;
import com.oasys.util.Constants;

public class CredentialsAppStartListener implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			com.oasys.service.CredentialsAppService credentialsAppService = (com.oasys.service.CredentialsAppService) ctx.getBean("credentialsAppService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			credentialsAppService.updateProcStatus(id,"2");
		}
	}
}