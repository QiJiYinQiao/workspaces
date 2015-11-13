package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 电核部主管活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCTrialSupervisorTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDIANHEZHUGUAN;
	}

	@Override
	protected String[] getOrganizationIds() {
		String[] organizationIds = { Constants.LOAN_ORGANIZATION_ID };
		return organizationIds;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前用户执行的角色信息
		setRoleCodeVariableLocal(task);
		// 设置当前执行候选人并发送消息提醒
		setRoleCodeHQ(task);
	}
}
