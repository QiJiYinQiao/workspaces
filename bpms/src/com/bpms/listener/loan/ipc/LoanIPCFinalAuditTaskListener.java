package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 审核专员活动节点监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCFinalAuditTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCSHENHEZHUANYUAN;
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
		// 设置地方的审核专员
		setRoleCodeLoacl(task);
	}

}
