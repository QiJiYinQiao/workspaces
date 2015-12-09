package com.oasys.listener.ad.PpeTurnoverApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.PpeTurnoverAppService;
/**
 * 更新订单状态监听
 * @ClassName: PurchaseAppBoUpdateStateListener 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月22日 下午7:37:38
 */
public class PpeTurnoverAppBoUpdateStateListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		WebApplicationContext wx = ContextLoader.getCurrentWebApplicationContext();
		PpeTurnoverAppService ppeTurnoverAppServiceImpl = (PpeTurnoverAppService)wx.getBean("ppeTurnoverAppService");
		ExecutionEntity executionEntity = (ExecutionEntity)execution;
		if(executionEntity.getTransition()!=null){
			String ptaId = "";
			if(StringUtils.isNotBlank(execution.getProcessBusinessKey())){
				ptaId = execution.getProcessBusinessKey().split("\\.")[1];
			}
			ppeTurnoverAppServiceImpl.updatePurchaseAppStatus(Integer.valueOf(ptaId), executionEntity.getTransition().getId());
		}
	}

}
