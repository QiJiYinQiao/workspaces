package com.bpms.listener.invest;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.util.Constants;

/**
 * @Creater ZHANGJIAN
 * @File_Name InvestBuMenZhuLiTaskListener
 * @Version v1.0
 * @Creation_Date 2015年12月04日 
 * @Description 财富业务流程，部门助理节点任务监听器
 */
@SuppressWarnings("serial")
public class InvestBuMenZhuLiTaskListener extends BaseInvestTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_BUMENZHULI; //部门助理角色常量。
	}

	@Override
	public void notify(DelegateTask task) {
		
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);	
		
		//设置处理当前任务的角色
		task.addCandidateGroup(getRoleCode());
	}

}
