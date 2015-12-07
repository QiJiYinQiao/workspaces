package com.bpms.listener.loan.settlement;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 贷款结算子流程中签约人对应节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanSettlementQianYueRenTaskListener extends BaseLoanTaskListener  implements TaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_SIGNATORY;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置该活动节点的受理人的角色信息
		setLocalVariableRole(task);
		// 设置地方候选组
		addCandidateGroupLocal(task);
		// 发送提示消息
		sendMessageByCandidateGroup(task);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}
}
