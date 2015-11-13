package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.model.Role;
import com.bpms.util.Constants;

/**
 * IPC贷审委1活动节点的监听
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCLRC01TaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDAISHENWEI1;
	}

	@Override
	public void notify(DelegateTask task) {
		String userId = (String) task.getVariable("ipclrcUser01");
		task.setAssignee(userId);
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);
		// 发送用户办理任务提醒
		sendMessageByUserId(task, userId);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}
}
