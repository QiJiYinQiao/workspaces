package com.bpms.listener.loan.letter;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 订单重提活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanOrderResubmitTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ORDER_CODE_FINANCIALMANAGER;
	}

	@Override
	public void notify(DelegateTask task) {
		setRoleCodeVariableLocal(task);
		String userId = (String) task.getVariable("curUserId");
		task.setAssignee(userId);
		sendMessageByUserId(task, userId);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}

}
