package com.bpms.listener.invest;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.log4j.Logger;

import com.bpms.model.Role;
import com.bpms.util.Constants;

@SuppressWarnings("serial")
public class InvestChengShiJingLiTaskListener extends BaseInvestTaskListener {

	private static Logger logger = Logger.getLogger(InvestChengShiJingLiTaskListener.class);
	
	@Override
	public String getRoleCode() {
		return Constants.INVEST_ROLE_CODE_CHENGSHIJINGLI; //城市经理角色常量。 
	}
	
//	@Override
//	public void notify(DelegateTask task) {
//		//获得角色，即TaskDefinitionKey
//		String taskDefinitionKey = task.getTaskDefinitionKey();		
//		logger.info("------------------->>  在类"+this.getClass().getSimpleName()+"中，taskDefinitionKey = "+taskDefinitionKey);						
//		Role roleObj = this.roleService.findRoleByCode(getRoleCode());
//		task.createVariableLocal("role", roleObj);
//		task.addCandidateGroup(getRoleCode());		
//	}

	@Override
	public void notify(DelegateTask task) {		
		
		//1.设置 与当前任务节点相关的本地变量
		Role roleObj = this.roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", roleObj);							
		
		//2.获取 投资订单提交人所在的营业部ID		
		Map<String, Object> processVariables = task.getVariables();
		String orgId4Submitter = (String) processVariables.get(Constants.INVEST_SUBMITTER_YINGYEBU_ID);

		
		// 若该城市下有多个营业部，则该城市经理都可以进行任务的办理。
		// 获取当前城市经理所在城市中，有多少个营业部？	
		/**
		 * 
		 * 城市经理位于“部门名称之下”的地方：
		 * 华北区，北京分公司，南银大厦营业部(pid:86)
		 * 华北区，山东省，德州分公司(pid:167)	
		 * 华东区，江苏省，常州市常州第二分公司（蔷薇家园）(pid:217)
		 * 
		 */
		//3.指定办理任务的候选组
		/**
		 * 说明：
		 * 判断 基础数据库中，是否具存在当前角色的用户（在此为与任务提交者同营业部中，角色为城市经理的用户）
		 * 若有，则将任务交给相应的用户组
		 * 若无，则自动跳转到下一个角色节点（即，区域经理）
		 * **/		
		//(1)若存在具有城市经理角色的用户
		if(userService.isUserExistByRoleCodeAndOrgId4InvestBusiness(getRoleCode(), orgId4Submitter)){
			logger.info("------------>部门ID为"+orgId4Submitter+"的部门中， 角色"+getRoleCode()+"的用户存在。");
			// 设置办理当前Task的候选人组
			String candidateGroup = getRoleCode()+"."+orgId4Submitter;
			task.addCandidateGroup(candidateGroup);						
		}
		//(2)若不存在具有城市经理角色的用户，跳转到流程图的下一个节点
		else{
			logger.info("------------>部门ID为"+orgId4Submitter+"的部门中， 没有角色"+getRoleCode()+"的用户。");
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("result", "ChengShiJingLiTongGuo");
			taskService.complete(task.getId(), variables);
		}										
	}	
	
}
