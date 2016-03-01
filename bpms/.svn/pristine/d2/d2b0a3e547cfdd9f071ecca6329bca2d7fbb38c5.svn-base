package com.bpms.listener.invest;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.log4j.Logger;

import com.bpms.model.Role;
import com.bpms.util.Constants;

@SuppressWarnings("serial")
public class InvestQuYuZongJianTaskListener extends BaseInvestTaskListener {

	private static Logger logger = Logger.getLogger(InvestQuYuZongJianTaskListener.class);
	
	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_QUYUZONGJIAN; //区域总监角色常量。 
	}
	
	@Override
	public void notify(DelegateTask task) {
		//获得角色，即TaskDefinitionKey
		String taskDefinitionKey = task.getTaskDefinitionKey();		
		logger.info("------------------->>  在类"+this.getClass().getSimpleName()+"中，taskDefinitionKey = "+taskDefinitionKey);						
		Role roleObj = this.roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", roleObj);
		task.addCandidateGroup(getRoleCode());		
	}
}
