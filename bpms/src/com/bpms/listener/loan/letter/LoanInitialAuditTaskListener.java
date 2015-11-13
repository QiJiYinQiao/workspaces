package com.bpms.listener.loan.letter;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.history.HistoricTaskInstance;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Collections;
import com.bpms.util.Constants;

/**
 * 初审部门
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanInitialAuditTaskListener extends BaseLoanTaskListener {

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
		setRoleCodeVariableLocal(task);

		// 获取初审部门节点或者是初审核实节点的任务办理人履历,如果存在则直接设置受理人,如果不存在则进行查询.
		// 此时使用了一个小技巧,使用的是初审部门节点id是初审核实节点id的前缀.
		List<HistoricTaskInstance> list = historyService
				.createHistoricTaskInstanceQuery()
				.processInstanceId(task.getProcessInstanceId())
				.taskDefinitionKeyLike("InitialAudit%")
				.orderByTaskCreateTime().desc().list();

		// 判断没有人办理过此条任务,则说明
		if (Collections.listIsEmpty(list)) {
			// 设置总部候选组并发送提示消息
			setRoleCodeHQ(task);
		} else {
			// 获取上一次任务办理的人
			String assignee = list.get(0).getAssignee();
			task.setAssignee(assignee);
			// 发送消息提醒
			sendMessageByUserId(task, assignee);
		}
	}

}
