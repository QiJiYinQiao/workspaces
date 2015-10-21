package com.bpms.listener2;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.BaseLoanTaskListener;
import com.bpms.model.Role;
import com.bpms.util.Constants;

/**
 * IPC贷审委3活动节点的监听
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCLRC03TaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDAISHENWEI3;
	}

	@Override
	public void notify(DelegateTask task) {
		String userId = (String) task.getVariable("ipclrcUser03");
		task.setAssignee(userId);
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);
		// 发送办理任务提示信息
		sendMessageByUserId(task, userId);
	}
}
