package com.bpms.listener2;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.BaseLoanTaskListener;
import com.bpms.model.Role;
import com.bpms.util.Constants;

/**
 * IPC贷审委2活动节点的监听
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCLRC02TaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDAISHENWEI2;
	}
	
	@Override
	public void notify(DelegateTask task) {
		String userId = (String) task.getVariable("ipclrcUser02");
		task.setAssignee(userId);
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);
		// 发送用户受理任务提醒
		sendMessageByUserId(task, userId);
	}
}
