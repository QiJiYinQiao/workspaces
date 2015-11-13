package com.bpms.listener.loan.letter;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 执行外访活动节点监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanVisitTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_VISIT;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置该活动节点受理人角色的信息
		setRoleCodeVariableLocal(task);
		// 设置地方候选组并发送提示消息
		setRoleCodeLoacl(task);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}
}
