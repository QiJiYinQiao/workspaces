package com.oasys.listener.pd.overtimeApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.EmpSalPositionChgApp;
import com.oasys.model.StatusNameRef;
import com.oasys.service.EmpSalPositionChgAppService;
import com.oasys.service.OvertimeAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;

/**
 * @Creater lida
 * @File_Name PPEScrapAppBoStopExecutionListener.java
 * @Version v1.0
 * @Creation_Date 2015年10月27日 
 * @Modifier
 * @Modified_Date
 * @Description 流程结束的监听器
 */
@SuppressWarnings("serial") 
public class OvertimeStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		OvertimeAppService overtimeAppService = (OvertimeAppService) ctx
				.getBean("overtimeAppService");
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
		// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && "0".equals(execution.getVariable("result"))){
			overtimeAppService.updateProcStatus(id, "5");//已撤销
		}else if( "2".equals(execution.getVariable("result")) || 2==Integer.parseInt(execution.getVariable("result")+"")){
			overtimeAppService.updateProcStatus(id, "6");//已拒绝
		}else{
			overtimeAppService.updateProcStatus(id, "3");//已完成
		}
		
	}

}
