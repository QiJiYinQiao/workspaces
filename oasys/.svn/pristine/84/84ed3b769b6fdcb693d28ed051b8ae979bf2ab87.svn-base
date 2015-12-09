package com.oasys.listener.ad.badgeApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.BadgeApp;
import com.oasys.model.StatusNameRef;
import com.oasys.service.BadgeAppService;
import com.oasys.service.UseStampAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;

/**
 * @Creater wangxincheng
 * @File_Name PPEScrapAppProcessStartExecutionListener.java
 * @Version v1.0
 * @Creation_Date 2015年9月18日 
 * @Modifier
 * @Modified_Date
 * @Description 申请提交的监听器
 */
@SuppressWarnings("serial")
public class BadgeAppStopExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		/*// 得到上下文环境
		WebApplicationContext ctx = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		BadgeAppService badgeAppService =  (BadgeAppService) ctx
				.getBean("badgeAppService"); 
		// 根据流程实例获取流程的BusinessKey,并获取当前的业务订单的id
		Integer id = null;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值,第二个值为业务订单的id
			id = Integer.valueOf(execution.getProcessBusinessKey().split("\\.")[1]);
		}
		BadgeApp badgeApp = badgeAppService.findBadgeByPnrId(id);
		String statusKey= Constants.BADGEAPP_RE_KEY;
		//撤销时的流程和通过时的流程申请状态
		if(badgeApp.getAppStatus().equals(statusKey)){
			badgeAppService.upBadgeProcStatus(id, "5");
		}else{
			badgeAppService.upBadgeProcStatus(id, "3");
		}*/
		
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			BadgeAppService badgeAppService =  (BadgeAppService) ctx
					.getBean("badgeAppService"); 
			WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
			// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
			if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && Constants.TASK_REJECT_RESULT.equals(execution.getVariable("result"))){
				badgeAppService.upBadgeProcStatus(id, Constants.PROC_REVOKE);
			}else if( Constants.TASK_REFUSE_RESULT.equals(execution.getVariable("result"))){
				badgeAppService.upBadgeProcStatus(id, Constants.PROC_REFUSE);//已拒绝
			}else{
				badgeAppService.upBadgeProcStatus(id, Constants.PROC_COMPLETED);
			}
		}
	}

}
