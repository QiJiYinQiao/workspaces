package com.oasys.listener.ad.PpeTurnoverApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.PPEScrapAppService;
import com.oasys.service.PpeTurnoverAppService;
import com.oasys.util.Constants;

/**
 * @Creater lida
 * @File_Name PPEScrapAppStartExecutionListener.java
 * @Version v1.0
 * @Creation_Date 2015年9月18日 
 * @Modifier
 * @Modified_Date
 * @Description 流程启动的监听器
 */
@SuppressWarnings("serial")
public class PpeTurnoverAppStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			PpeTurnoverAppService ppeTurnoverAppService = (PpeTurnoverAppService) ctx.getBean("ppeTurnoverAppService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			ppeTurnoverAppService.updatePpeTurnoverappProceStatus(id, Constants.PROC_APPROVAL);
		}
	}
}
