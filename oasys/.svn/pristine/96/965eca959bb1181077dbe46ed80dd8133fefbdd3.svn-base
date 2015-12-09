package com.oasys.listener.pd.EmpSalPositionChgApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.EmpSalPositionChgAppService;
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
public class EmpSalPositionChgAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			EmpSalPositionChgAppService empSalAppService = (EmpSalPositionChgAppService) ctx.getBean("empSalPositionChgAppService");
			WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
			// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
			if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && "0".equals(execution.getVariable("result"))){
				empSalAppService.updEmpSalPositionProcessStatus(id, "5");
			}else{
				empSalAppService.updEmpSalPositionProcessStatus(id, "3");
			}
		}
	}

}
