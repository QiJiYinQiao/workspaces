package com.bpms.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.Role;
import com.bpms.service.RoleService;
import com.bpms.util.Constants;

/**
 * @Creater chenfl
 * @File_Name LoanOrderResubmitTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 理财经理重整订单的监听器
 */
@SuppressWarnings("serial")
public class InvestAdjustOrderTaskListener extends BaseTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ORDER_CODE_FINANCIALMANAGER;
	}

	@Override
	public void notify(DelegateTask task) {
		// 得到上下文环境
		WebApplicationContext webContext = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		RoleService roleService = (RoleService) webContext
				.getBean("roleService");
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);
	}

}