package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.model.Role;
import com.bpms.util.Constants;
/**
 * IPC地方调查员活动节点监听
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCCensorTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDIFANGDIAOCHA;
	}
	
	@Override
	public void notify(DelegateTask task) {
		String userId = (String) task.getVariable("IPCCensorAssign");
		task.setAssignee(userId);
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);
		// 发送用户办理任务消息提醒
		sendMessageByUserId(task, userId);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}

}
