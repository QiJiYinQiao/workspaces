package com.oasys.listener.pd.overtimeApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.EmpSalPositionChgAppService;
import com.oasys.service.OvertimeAppService;

/**
 * @Creater lida
 * @File_Name EmpSalPositionChgAppStartExecutionListener.java
 * @Version v1.0
 * @Creation_Date 2015年10月27日 
 * @Modifier
 * @Modified_Date
 * @Description 流程启动的监听器
 */
@SuppressWarnings("serial")
public class OvertimeStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		OvertimeAppService overtieService = (OvertimeAppService) ctx
				.getBean("overtimeAppService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		// 修改订单的状态的信息
		overtieService.updateProcStatus(id,"2");
	}

}
