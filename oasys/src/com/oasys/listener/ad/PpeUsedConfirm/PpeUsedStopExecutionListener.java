package com.oasys.listener.ad.PpeUsedConfirm;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.BadgeApp;
import com.oasys.model.LeaveApp;
import com.oasys.model.StatusNameRef;
import com.oasys.service.BadgeAppService;
import com.oasys.service.LeaveAppService;
import com.oasys.service.PpeUsedConfirmService;
import com.oasys.service.TravelExpensesAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;

/**
 * 固定资产使用申请结束监听器
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class PpeUsedStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		PpeUsedConfirmService ppeUsedConfirmService= (PpeUsedConfirmService) ctx.getBean("ppeUsedConfirmService");
		// 使用上下文环境中的getBean方法得到bean实例
		WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx
				.getBean("workFlowTaskService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		
		Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
		
		//修改流程状态
		ppeUsedConfirmService.updateProcStatus (id, "3");
		//更改固定资产保管人和状态
		ppeUsedConfirmService.updatePpeStock(id);
	}

}
