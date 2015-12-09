package com.oasys.listener.ad.StampTurnoverApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.StampTurnoverAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;

/**
 * @Creater LiDa
 * @File_Name StampTurnoverAppStopExecutionListener.java
 * @Version v1.0
 * @Creation_Date 2015年10月7日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 流程关闭的监听器
 */
@SuppressWarnings("serial")
public class StampTurnoverAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			StampTurnoverAppService stampTurnoverAppService = (StampTurnoverAppService) ctx.getBean("stampTurnoverAppService");
			WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
			//判断是否为拒绝 拒绝时 更新流程状态为已拒绝
			if(Constants.TASK_REFUSE_RESULT.equals(execution.getVariable("result"))){
				stampTurnoverAppService.updStampTurnoverProcessStatus(id, Constants.PROC_REFUSE);
			// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
			}else if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && Constants.TASK_REJECT_RESULT.equals(execution.getVariable("result"))){
				stampTurnoverAppService.updStampTurnoverProcessStatus(id,Constants.PROC_REVOKE);
			}else{
				stampTurnoverAppService.updStampTurnoverProcessStatus(id,Constants.PROC_COMPLETED);
			}
		}
	}
}
