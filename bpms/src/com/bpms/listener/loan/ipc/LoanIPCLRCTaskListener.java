package com.bpms.listener.loan.ipc;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * IPC贷审委1活动节点的监听
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCLRCTaskListener extends BaseLoanTaskListener  implements TaskListener{

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDAISHENWEI;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前受理角色信息
		setLocalVariableRole(task);
		// 发送用户办理任务提醒
		sendMessageByAssignee(task);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}
}
