package com.bpms.listener.invest;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.util.Constants;

import common.Logger;

@SuppressWarnings("serial")
public class InvestTuanDuiJingLiTaskListener extends BaseInvestTaskListener {
	
	private static Logger logger = Logger.getLogger(InvestTuanDuiJingLiTaskListener.class);
	
	@Override
	public String getRoleCode() {		
		return Constants.INVEST_ROLE_CODE_TUANDUIJINGLI;   //团队经理角色常量
	}	
	
	@Override
//	public void notify(DelegateTask task) {
//		String taskDefinitionKey = task.getTaskDefinitionKey();		
//		logger.info("------------------->>  在类"+this.getClass().getSimpleName()+"中，taskDefinitionKey = \""+taskDefinitionKey+"\"");						
//		Role roleObj = this.roleService.findRoleByCode(getRoleCode());
//		String processVariables = (String)task.getVariable(Constants.INVEST_SUBMITTER_YINGYEBU_ID);
//		task.addCandidateGroup(getRoleCode());
//	}
	
	public void notify(DelegateTask task) {

		//1.设置 与当前任务节点相关的本地变量
		Role roleObj = this.roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", roleObj);							
		
		//2.获取 投资订单提交人所在的营业部ID		
		Map<String, Object> processVariables = task.getVariables();
		String orgId4Submitter = (String) processVariables.get(Constants.INVEST_SUBMITTER_YINGYEBU_ID);

		//3.指定办理任务的候选组
		/**
		 * 说明：
		 * 判断 基础数据库中，是否具存在当前角色的用户（在此为与任务提交者同营业部中，角色为团队经理的用户）
		 * 若有，则将任务交给相应的用户组
		 * 若无，则自动跳转到下一个角色节点（即，大团经理）
		 * **/		
		//(1)若存在具有团队经理角色的用户
		if(userService.isUserExistByRoleCodeAndOrgId4InvestBusiness(getRoleCode(), orgId4Submitter)){
			logger.info("------------>部门ID为"+orgId4Submitter+"的部门中， 角色"+getRoleCode()+"的用户存在。");
			// 设置办理当前Task的候选人组
			String candidateGroup = getRoleCode()+"."+orgId4Submitter;
			task.addCandidateGroup(candidateGroup);						
		}
		//(2)若不存在具有团队经理角色的用户，跳转到流程图的下一个节点
		else{
			logger.info("------------>部门ID为"+orgId4Submitter+"的部门中， 没有角色"+getRoleCode()+"的用户。");
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("result", "TuanDuiJingLiTongGuo");
			taskService.complete(task.getId(), variables);
		}				
	}
	
}
