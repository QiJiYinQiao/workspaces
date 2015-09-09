package com.bpms.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.service.LoanOrderService;

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
public class LoanProcessStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		LoanOrderService loanOrderService = (LoanOrderService) ctx
				.getBean("loanOrderService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		String id = "";
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = execution.getProcessBusinessKey().split("\\.")[1];
		}
		// 修改订单的状态
		loanOrderService.updateLoanOrderProcessStatus(id, "2");
	}

}
