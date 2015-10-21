package com.bpms.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanOrderWorkFlowService;
import com.bpms.util.Collections;
import com.bpms.util.SendMessageUtil;

/**
 * 贷款业务的基类监听器
 * 
 * @author liuhh
 *
 */
public abstract class BaseLoanTaskListener extends BaseTaskListener {

	private static final long serialVersionUID = 1L;

	// 贷款订单的业务操作方法
	protected LoanOrderService loanOrderService;

	// 贷款业务的工作流的业务操作
	protected LoanOrderWorkFlowService loanOrderWorkFlowService;

	{
		loanOrderService = (LoanOrderService) webContext
				.getBean("loanOrderService");
		loanOrderWorkFlowService = (LoanOrderWorkFlowService) webContext
				.getBean("loanOrderWorkFlowService");
	}

	/**
	 * 向用户发送消息
	 * 
	 * @param users
	 *            用户的信息
	 */
	protected void sendMessageByUsers(DelegateTask task, List<Users> users) {
		// 遍历获取每个人的任务总个数
		for (Users user : users) {
			String userId = String.valueOf(user.getUserId());

			Long claimCount = loanOrderWorkFlowService
					.findAllClaimTaskCount(userId);
			Long unClaimCount = loanOrderWorkFlowService
					.findAllUnClaimTaskCount(userId) + 1;

			SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
					SendMessageUtil.DATATYPE_UNCLAIMLOANORDER,
					SendMessageUtil.CALCULATETYPE_ADD, task.getId(),
					claimCount, unClaimCount), userId);
		}
	}

	/**
	 * 向用户发送消息
	 * 
	 * @param userId
	 *            制定的用户
	 */
	protected void sendMessageByUserId(DelegateTask task, String userId) {

		Long claimCount = loanOrderWorkFlowService
				.findAllClaimTaskCount(userId) + 1;
		Long unClaimCount = loanOrderWorkFlowService
				.findAllUnClaimTaskCount(userId);

		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_CLAIMLOANORDER,
				SendMessageUtil.CALCULATETYPE_ADD, task.getId(), claimCount,
				unClaimCount), userId);
	}

	@Override
	public void notify(DelegateTask task) {

		List<Users> users = userService.findUsersByRoleCode(getRoleCode());
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);

		if (Collections.listIsNotEmpty(users)) {
			for (Users user : users) {
				task.addCandidateUser(user.getUserId().toString());
			}
			sendMessageByUsers(task, users);
		}

	}
}
