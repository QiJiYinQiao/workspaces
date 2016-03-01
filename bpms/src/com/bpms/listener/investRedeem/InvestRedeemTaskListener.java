package com.bpms.listener.investRedeem;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.util.Constants;

public class InvestRedeemTaskListener extends BaseInvestRedeemTaskListener{

	private static final long serialVersionUID = 9054407863573951214L;

	@Override
	public String getRoleCode() {
//		Constants.getCurrendUser().get
		return null;
	}

	@Override
	public void notify(DelegateTask task) {
		//获得当前登录用户的角色常量，
		//特点：用户角色的常量 与 每个Task节点的ID相同，
		//反映在数据库act_ru_task表上，则意味着：前登录用户的角色常量 = TASK_DEF_KEY
		String roleCode = task.getTaskDefinitionKey();		
		
				
		/////////////////////////////////////////
		//发送办理任务的消息给同角色的不同用户，或者给流程启动者
		/////////////////////////////////////////		
		//1.若当前Task节点是“调整申请”的Task节点，则将办理任务的消息发送给流程启动者。
		if(roleCode.equalsIgnoreCase("InvestRedeemAdjustment")){
			//此处写死了。
			Role roleObj = this.roleService.findRoleByCode(Constants.INVEST_ROLE_CODE_LICAIJINGLI);
			task.createVariableLocal("role", roleObj);
			//获取提交投资赎回申请的理财经理的ID，即启动当前流程实例的用户ID
			String userId = (String) task.getVariable(Constants.CURRENT_USER_KEY);						
			task.setAssignee(userId);			
			sendMessageByUserId(task, userId);		
		}		
		//2.若当前Task节点是“普通”的Task节点，则将办理任务的消息发送给同角色的不同用户。
		else{
			Role roleObj = this.roleService.findRoleByCode(roleCode);
			task.createVariableLocal("role", roleObj);
			task.addCandidateGroup(roleCode);			
			//(1)获得当前处理Task节点的角色常量：即 TASK_DEF_KEY			
			List<Users> userList = userService.findUsersByRoleCode(roleCode);  //利用了BaseTaskListener中自动注入的userService.
			sendMessageByUsers(task, userList);					
		}		
	}
}
