package com.bpms.listener.invest;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.util.Constants;

/**
 * 数据专员办理任务监听器
 * @Creater ZHANGJIAN
 * @File_Name InvestBuMenZhuLiTaskListener
 * @Creation_Date 2015年12月08日 
 * @Description 财富业务流程，销客专员办理任务监听器
 */
@SuppressWarnings("serial")
public class InvestShuJuZhuanYuanTaskListener extends BaseInvestTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_SHUJUZHUANYUAN; //数据专员角色常量。
	}	

	@Override
	public void notify(DelegateTask task) {
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);			
		task.addCandidateGroup(getRoleCode());
	}
}
