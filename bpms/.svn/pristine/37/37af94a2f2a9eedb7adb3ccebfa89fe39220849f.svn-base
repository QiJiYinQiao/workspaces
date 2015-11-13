package com.bpms.listener.loan.ipc;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.commons.lang3.StringUtils;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.util.Collections;
import com.bpms.util.Constants;

/**
 * IPC电核专员活动节点监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCInitialAuditTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCDIANHEZHUANYUAN;
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

		// 判断当前任务是否指派,如果是指派,则直接设定候选人
		String userId = (String) task.getVariable("commissioner");
		if(StringUtils.isNotEmpty(userId)){
			task.setAssignee(userId);
			// 发送消息提醒
			sendMessageByUserId(task, userId);
			return;
		}

		// 电核专员是否办理过,如果办理过,则直接进行制定上次受理人,如果没有制定过,则重新查询设置
		List<HistoricTaskInstance> list = historyService
				.createHistoricTaskInstanceQuery()
				.processInstanceId(task.getProcessInstanceId())
				.taskDefinitionKeyLike("IPCInitialAudit")
				.orderByTaskCreateTime().desc().list();

		// 判断该节点是否已经办理过一次,如果没有办理则重新设定
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
		/*
		 * // 获取当前任务的候选人 List<Users> users =
		 * userService.findUsersByRoleCode(getRoleCode()); Role role =
		 * roleService.findRoleByCode(getRoleCode());
		 * task.createVariableLocal("role", role); if
		 * (Collections.listIsNotEmpty(users)) { for (Users user : users) {
		 * task.addCandidateUser(user.getUserId().toString()); } }
		 * 
		 * // 获取当前任务执行节点的定义的ID String taskDefinitionKey =
		 * task.getTaskDefinitionKey();
		 * 
		 * // 判断当前节点是IPC数据岗之后的IPC初审节点，还是执行外访完毕的IPC初审节点 // 如果是IPC数据岗之后的IPC初审节点 if
		 * ("IPCInitialAudit".equals(taskDefinitionKey)) { String userId =
		 * (String) task.getVariable("commissioner"); task.setAssignee(userId);
		 * // 发送用户办理任务提醒 sendMessageByUserId(task, userId); }
		 * 
		 * // 如果是执行外访完毕的IPC初审节点 if
		 * ("IPCInitialAudit2".equals(taskDefinitionKey)) { String
		 * ipcInitAuditUser = (String) task .getVariable("ipcInitAuditUser");
		 * task.setAssignee(ipcInitAuditUser); sendMessageByUserId(task,
		 * ipcInitAuditUser); }
		 */

	}

}
