package com.oasys.listener.ad.UseStampApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.BadgeAppService;
import com.oasys.service.PPEScrapAppService;
import com.oasys.service.UseStampAppService;
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
public class UseStampAppStartExecutionListener implements ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
			UseStampAppService useStampAppService =  (UseStampAppService) ctx
					.getBean("useStampAppService"); 
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			useStampAppService.updateProcStatus(id,"2");
		}
	}

}
