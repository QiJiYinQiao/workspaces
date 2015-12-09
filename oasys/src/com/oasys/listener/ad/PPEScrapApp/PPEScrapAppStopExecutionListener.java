package com.oasys.listener.ad.PPEScrapApp;

import java.util.List;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.task.Task;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.PPEScrapApp;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.service.PPEScrapAppAttachService;
import com.oasys.service.PPEScrapAppService;
import com.oasys.service.PpeStockInfoService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;

/**
 * @Creater lida
 * @File_Name PPEScrapAppStopExecutionListener.java
 * @Version v1.0
 * @Creation_Date 2015年9月18日 
 * @Modifier
 * @Modified_Date
 * @Description 流程结束的监听器
 */
@SuppressWarnings("serial")
public class PPEScrapAppStopExecutionListener implements ExecutionListener {
	

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			PPEScrapAppService ppeScrapAppService = (PPEScrapAppService) ctx.getBean("ppeScrapAppService");
			PPEScrapAppAttachService ppeScrapAppAttachService = (PPEScrapAppAttachService) ctx.getBean("ppeScrapAppAttachServiceImpl");
			WorkFlowTaskService workFlowTaskService = (WorkFlowTaskService) ctx.getBean("workFlowTaskService");
			PpeStockInfoService ppeStockInfoService = (PpeStockInfoService) ctx.getBean("ppetockInfoService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			Task task =workFlowTaskService.getTaskByBusinessKey(id.toString(), execution.getProcessBusinessKey().split("\\.")[0]);
			//判断是否为拒绝 拒绝时 更新流程状态为已拒绝
			if(Constants.TASK_REFUSE_RESULT.equals(execution.getVariable("result"))){
				ppeScrapAppService.updPPEScrapProcessStatus(id, Constants.PROC_REFUSE);
			// 修改订单的状态 判断状态是否为申请撤销 如果的话 将流程状态更新为已撤销
			}else if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) && Constants.TASK_REJECT_RESULT.equals(execution.getVariable("result"))){
				ppeScrapAppService.updPPEScrapProcessStatus(id,Constants.PROC_REVOKE);
			}else{
				ppeScrapAppService.updPPEScrapProcessStatus(id,Constants.PROC_COMPLETED);
			}
			//更新库存状态
			PPEScrapApp ppeApp = ppeScrapAppService.getPpeScrapByID(id);
			List<PpeScrapAppAttach> attList = ppeScrapAppAttachService.findPpeAttachList(ppeApp.getAppNo());//获取该申请对应的报废项
			if(null != ppeApp && attList.size() > 0){
				for (PpeScrapAppAttach ppeScrapAppAttach : attList) {
					ppeStockInfoService.updPpeStockInfoStatus(ppeScrapAppAttach.getPpeNo(), "3");
				}
			}
		}
	}
}
