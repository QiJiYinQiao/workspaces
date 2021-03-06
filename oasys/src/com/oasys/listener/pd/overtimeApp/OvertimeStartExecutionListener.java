package com.oasys.listener.pd.overtimeApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.EmpSalPositionChgAppService;
import com.oasys.service.OvertimeAppService;
import com.oasys.util.Constants;

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
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			OvertimeAppService overtieService = (OvertimeAppService) ctx.getBean("overtimeAppService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			overtieService.updateProcStatus(id,"2");
		}
	}

}
