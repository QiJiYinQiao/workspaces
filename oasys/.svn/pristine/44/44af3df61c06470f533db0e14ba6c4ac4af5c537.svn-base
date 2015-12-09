package com.oasys.listener.ad.ConsumablesApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.ConsumablesAppService;
import com.oasys.service.PpeTurnoverAppService;
/**
 * 
 * @Title: ConsumablesAppUpdateStateListener.java 
 * @Package com.oasys.listener.ad.ConsumablesApp 
 * @Description: 初始监听
 * @author yuanzhongqiu  
 * @date 2015年10月12日 下午5:30:42 
 * @version V1.0
 */
public class ConsumablesAppUpdateStateListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		ConsumablesAppService consumablesAppService = (ConsumablesAppService) ctx
				.getBean("consumablesAppService");
		ExecutionEntity executionEntity = (ExecutionEntity)execution;
		if(executionEntity.getTransition()!=null){
			String caId = "";
			if(StringUtils.isNotBlank(execution.getProcessBusinessKey())){
				caId = execution.getProcessBusinessKey().split("\\.")[1];
			}
			consumablesAppService.updateConsumablesAppStatus(Integer.valueOf(caId), executionEntity.getTransition().getId());
			consumablesAppService.updateConsumablesAppProceStatus(Integer.valueOf(caId), "2");
		}
	}

}
