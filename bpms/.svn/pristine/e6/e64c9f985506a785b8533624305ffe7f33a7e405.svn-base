package com.bpms.listener.loan.letter;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 数据岗活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanDataPostTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_DATAPOST;
	}

	@Override
	public String[] getOrganizationIds() {
		String[] organizationIds = { Constants.LOAN_LETTER_ID };
		return organizationIds;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前任务的处理角色
		setRoleCodeVariableLocal(task);
		// 设置总部候选组并发送提示消息
		setRoleCodeHQ(task);
		// 设置地方候选组并发送提示消息
		setRoleCodeLoacl(task);
	}

}
