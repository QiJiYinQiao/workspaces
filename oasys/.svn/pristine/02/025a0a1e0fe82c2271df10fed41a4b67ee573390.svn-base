package com.oasys.listener.pd.outApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.OutAppService;
import com.oasys.util.Constants;
/**
 * 更新订单状态监听
 * @ClassName: PurchaseAppBoUpdateStateListener 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月22日 下午7:37:38
 */
public class OutAppProcessStartListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		if(execution.getVariable(Constants.CURRENT_BUSINESS_ID) != null){
			WebApplicationContext wx = ContextLoader.getCurrentWebApplicationContext();
			OutAppService outAppServiceImpl = (OutAppService)wx.getBean("outAppService");
			Integer id = Integer.valueOf(execution.getVariable(Constants.CURRENT_BUSINESS_ID).toString());
			outAppServiceImpl.updateOutAppProcessStatus(id,Constants.PROC_APPROVAL);
		}
	}

}
