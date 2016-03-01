package com.bpms.listener.loan.ipc;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Constants;

/**
 * 订单重提活动节点的监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCOrderResubmitTaskListener extends BaseLoanTaskListener  implements TaskListener{

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ORDER_CODE_FINANCIALMANAGER;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前节点的受理角色的信息
		setLocalVariableRole(task);
		// 设置受理人
		task.setAssignee((String) task.getVariable("curUserId"));
		// 发送消息提醒
		sendMessageByAssignee(task);
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}

}
