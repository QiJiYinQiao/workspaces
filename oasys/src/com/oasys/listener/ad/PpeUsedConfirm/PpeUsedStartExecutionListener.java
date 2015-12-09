package com.oasys.listener.ad.PpeUsedConfirm;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.BadgeAppService;
import com.oasys.service.LeaveAppService;
import com.oasys.service.PpeUsedConfirmService;
import com.oasys.service.TravelExpensesAppService;

/**
 * 固定资产使用申请开启流程监听器
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class PpeUsedStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		PpeUsedConfirmService ppeUsedConfirmService= (PpeUsedConfirmService) ctx.getBean("ppeUsedConfirmService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		// 修改订单的状态的信息
		ppeUsedConfirmService.updateProcStatus(id,"2");
		
		
	}

}
