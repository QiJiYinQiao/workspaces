package com.bpms.listener.invest;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.util.Constants;

/**
 * 投资申请调整TaskListener
 * @Creater ZHANGJIAN
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 理财经理重整订单的监听器
 */
@SuppressWarnings("serial")
public class InvestApplyAdjustmentTaskListener extends BaseInvestTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_LICAIJINGLI; //理财经理角色常量
	}

	@Override
	public void notify(DelegateTask task) {
		
		Role role = roleService.findRoleByCode(getRoleCode());
		String userId = (String) task.getVariable("curUserId");
		task.createVariableLocal("role", role);
		task.setAssignee(userId);
		
		sendMessageByUserId(task, userId);
	}

}
