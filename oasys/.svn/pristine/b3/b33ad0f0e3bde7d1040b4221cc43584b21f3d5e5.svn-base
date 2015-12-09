package com.oasys.listener.ad.ConsumablesApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.ConsumablesApp;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.StatusNameRef;
import com.oasys.service.ConsumablesAppService;
import com.oasys.service.PPEScrapAppService;
import com.oasys.service.PpeTurnoverAppService;
import com.oasys.util.Constants;
/**
 * 
 * @Title: ConsumablesAppStopExecutionListener.java 
 * @Package com.oasys.listener.ad.ConsumablesApp 
 * @Description: 流程结束监听器
 * @author yuanzhongqiu  
 * @date 2015年10月12日 下午5:29:01 
 * @version V1.0
 */
@SuppressWarnings("serial")
public class ConsumablesAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		ConsumablesAppService consumablesAppService = (ConsumablesAppService) ctx
				.getBean("consumablesAppService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		

		ConsumablesApp consumablesApp = consumablesAppService.getConsumablesAppByID(id);
		StatusNameRef statusNameRef = consumablesAppService.findAppStatusByID(consumablesApp.getAppStatus());
		// 修改订单的状态
		if (statusNameRef.getAppStatusCode().split("_")[1].equals(Constants.PPESCRAPAPP_APPLY_KEY)) {
			consumablesAppService.updateConsumablesAppProceStatus(id, "5");
		} else {
			consumablesAppService.updateConsumablesAppProceStatus(id, "3");
		}
		// 修改订单的状态
		
	}

}
