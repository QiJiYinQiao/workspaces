package com.bpms.listener.loan.letter;


import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.model.LoanOrder;
import com.bpms.model.LoanOrderHis;
import com.bpms.util.Constants;

/**
 * 初审部门
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanInitialAuditTaskListener extends BaseLoanTaskListener  implements TaskListener{

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_INITIALAUDIT;
	}

	@Override
	protected String[] getOrganizationIds() {
		String[] organizationIds = { Constants.LOAN_LETTER_ID };
		return organizationIds;
	}

	@Override
	public void notify(DelegateTask task) {

		// 设置当前任务的处理角色
		setLocalVariableRole(task);
		// 设置总部候选组
		addCandidateGroupHQ(task);
		
		// 获取当前执行的业务订单
		LoanOrder loanOrder = getLoanOrderByTaskId(task);
		// 获取当前业务订单的备注信息,获取当前ChuShen角色受理人,设置受理人信息
		LoanOrderHis loanOrderHis = loanOrderHisService.findLoanOrderHis(getRoleCode(), loanOrder.getLoanOrderId());

		// 判断是否之前有初审人员办理过,如果办理过则设置上一次初审人员,如果不是则使用候选组
		if (null != loanOrderHis) {
			// 获取上一次任务办理的人
			task.setAssignee(loanOrderHis.getAssignee());
			// 发送消息提醒
			sendMessageByAssignee(task);
		} else {
			// 发送消息提醒
			sendMessageByCandidateGroup(task);
		}
	}

}
