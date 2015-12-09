package com.oasys.listener.pd.empFullmemberApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.EmpFullmemberAppService;
import com.oasys.service.EmpFullmemberAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;
/**
 * 流程结束监听，更改订单流程状态
 * @ClassName: PurchaseAppBoProcessStopListener 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月10日 上午10:25:00
 */
@SuppressWarnings("serial")
public class EmpFullmemberAppProcessStopListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
		EmpFullmemberAppService empFullmemberAppService = (EmpFullmemberAppService) ctx.getBean("empFullmemberAppService");
		WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
			// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
			if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && "0".equals(execution.getVariable("result"))){
				empFullmemberAppService.updateEmpFullmemberAppProcessStatus(id, "5");
			}else{
				empFullmemberAppService.updateEmpFullmemberAppProcessStatus(id, "3");
			}
		}
	}

}
