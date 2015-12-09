package com.oasys.listener.fd.SpecialRatifyApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.BusinessTripAppService;
import com.oasys.service.EmpDimissionAppService;
import com.oasys.service.EmpSalPositionChgAppService;
import com.oasys.service.SpecialRatifyAppService;

/**
 * 
 * @Title: SpecialRatifyAppStartExecutionListener.java 
 * @Package com.oasys.listener.fd.SpecialRatifyApp 
 * @Description: 流程启动监听
 * @author yuanzhongqiu  
 * @date 2015年11月24日 下午5:00:18 
 * @version V1.0
 */
@SuppressWarnings("serial")
public class SpecialRatifyAppStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		SpecialRatifyAppService specialRatifyAppService = (SpecialRatifyAppService) ctx
				.getBean("specialRatifyAppService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		// 修改订单的状态的信息
		specialRatifyAppService.updateSpecialRatifyAppProceStatus(id,"2");
	}

}
