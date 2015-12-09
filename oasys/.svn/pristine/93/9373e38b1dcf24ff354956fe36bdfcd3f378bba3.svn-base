package com.oasys.listener.ad.PpeTurnoverApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.StatusNameRef;
import com.oasys.service.PPEScrapAppService;
import com.oasys.service.PpeTurnoverAppService;
import com.oasys.util.Constants;

/**
 * @Creater chenfl
 * @File_Name LoanProcessStopTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 流程关闭的监听器
 */
@SuppressWarnings("serial")
public class PpeTurnoverAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		PpeTurnoverAppService ppeTurnoverAppService = (PpeTurnoverAppService) ctx
				.getBean("ppeTurnoverAppService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		
		PpeTurnoverApp ppeTurnoverApp = ppeTurnoverAppService.findPpeTurnoverAppByID(id.toString());
		StatusNameRef statusNameRef = ppeTurnoverAppService.findAppStatusByID(ppeTurnoverApp.getAppStatus());
		// 修改订单的状态
		if (statusNameRef.getAppStatusCode().split("_")[1].equals(Constants.PPESCRAPAPP_APPLY_KEY)) {
			ppeTurnoverAppService.updatePpeTurnoverappProceStatus(id, "5");
		} else {
			ppeTurnoverAppService.updatePpeTurnoverappProceStatus(id, "3");
		}
	}

}
