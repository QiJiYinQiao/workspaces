package com.oasys.listener.pd.empForgetPluginApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.service.EmpForgetPluginAppService;
/**
 * 更新订单状态监听
 * @ClassName: PurchaseAppBoUpdateStateListener 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月22日 下午7:37:38
 */
public class EmpForgetPluginAppProcessStartListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		WebApplicationContext wx = ContextLoader.getCurrentWebApplicationContext();
		EmpForgetPluginAppService empForgetPluginAppServiceImpl = (EmpForgetPluginAppService)wx.getBean("empForgetPluginAppService");
		String btaId = "";
		if(StringUtils.isNotBlank(execution.getProcessBusinessKey())){
			btaId = execution.getProcessBusinessKey().split("\\.")[1];
		}
		empForgetPluginAppServiceImpl.updateEmpForgetPluginAppProcessStatus(Integer.valueOf(btaId),"2");
	}

}
