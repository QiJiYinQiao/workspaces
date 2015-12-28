package com.oasys.listener.ad.penaltyNoticeSubmitApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.ConsumablesApp;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.StatusNameRef;
import com.oasys.service.ConsumablesAppService;
import com.oasys.service.PPEScrapAppService;
import com.oasys.service.PenaltyNoticeSubmitAppService;
import com.oasys.service.PpeTurnoverAppService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.util.Constants;
/**
 * 
 * @Title: ConsumablesAppStopExecutionListener.java 
 * @Package com.oasys.listener.ad.ConsumablesApp 
 * @Description: 流程结束监听器
 * @author yuanzhongqiu  
 * @date 2015年10月12日 下午5:29:01 
 * @version V1.0
 */
@SuppressWarnings("serial")
public class PenaltyNoticeSubmitAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			PenaltyNoticeSubmitAppService penaltyNoticeSubmitAppService = (PenaltyNoticeSubmitAppService) ctx.getBean("penaltyNoticeSubmitAppService");
			WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
			//判断是否为拒绝 拒绝时 更新流程状态为已拒绝
			if(Constants.TASK_REFUSE_RESULT.equals(execution.getVariable("result"))){
				penaltyNoticeSubmitAppService.updatePenaltyNoticeSubmitAppProceStatus(id, Constants.PROC_REFUSE);
			// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
			}else if(null!=task && task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && Constants.TASK_REJECT_RESULT.equals(execution.getVariable("result"))){
				penaltyNoticeSubmitAppService.updatePenaltyNoticeSubmitAppProceStatus(id,Constants.PROC_REVOKE);
			}else{
				penaltyNoticeSubmitAppService.updatePenaltyNoticeSubmitAppProceStatus(id,Constants.PROC_COMPLETED);
			}
		}
	}
}
