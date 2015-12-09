package com.oasys.listener.pd.staffRecruitApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.StaffRecruitAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;

@SuppressWarnings("serial")
public class StaffRecruitAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {

			if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
				WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
				StaffRecruitAppService staffRecruitAppService = (StaffRecruitAppService) ctx.getBean("staffRecruitAppService");
				WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
				Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
				Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
				//判断是否为拒绝 拒绝时 更新流程状态为已拒绝
				if(Constants.TASK_REFUSE_RESULT.equals(execution.getVariable("result"))){
					staffRecruitAppService.updateStaffRecruitProcessStatus(id, Constants.PROC_REFUSE);
				// 修改订单的状态 判断状态是否为申请撤销, 如果是“撤销申请”的话 将流程状态更新为已撤销
				}else if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && Constants.TASK_REJECT_RESULT.equals(execution.getVariable("result"))){
					staffRecruitAppService.updateStaffRecruitProcessStatus(id, Constants.PROC_REVOKE);
				}
				// 修改订单的状态 判断状态既不是申请撤销，又不是拒绝，则将其视为“订单完成”。
				else{
					staffRecruitAppService.updateStaffRecruitProcessStatus(id, Constants.PROC_COMPLETED);
				}
			}
	}

}
