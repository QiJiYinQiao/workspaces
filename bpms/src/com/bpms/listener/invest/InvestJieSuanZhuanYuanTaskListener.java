package com.bpms.listener.invest;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.util.Constants;

@SuppressWarnings("serial")
public class InvestJieSuanZhuanYuanTaskListener extends BaseInvestTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_JIESUANZHUANYUAN; //结算专员角色常量。
	}	

	@Override
	public void notify(DelegateTask task) {
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);			
		task.addCandidateGroup(getRoleCode());
	}
}
