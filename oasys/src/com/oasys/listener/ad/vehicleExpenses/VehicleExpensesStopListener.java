package com.oasys.listener.ad.vehicleExpenses;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.VehicleUsesRegService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.util.Constants;

public class VehicleExpensesStopListener implements ExecutionListener{

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			VehicleUsesRegService vehicleUsesRegService = (com.oasys.service.VehicleUsesRegService) ctx.getBean("vehicleUsesRegService");
			WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
			// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
			if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && "1".equals(execution.getVariable("result"))){
				vehicleUsesRegService.updateProcStatus(id, '5');
			}else if( "2".equals(execution.getVariable("result")) || 2==Integer.parseInt(execution.getVariable("result")+"")){
				vehicleUsesRegService.updateProcStatus(id, '6');//已拒绝
			}else{
				vehicleUsesRegService.updateProcStatus(id, '3');
			}
		}
	}
}