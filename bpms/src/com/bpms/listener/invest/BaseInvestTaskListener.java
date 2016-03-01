package com.bpms.listener.invest;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.BaseTaskListener;
import com.bpms.model.Users;
import com.bpms.service.InvestOrderWorkFlowService;
import com.bpms.util.SendMessageUtil;

/**
 * 财富的任务监听基类
 * 
 * @author liuhh
 *
 */
public abstract class BaseInvestTaskListener extends BaseTaskListener {

	private static final long serialVersionUID = 1L;

	// 投资订单的业务操作方法
	protected InvestOrderWorkFlowService investOrderWorkFlowService;

	{
		investOrderWorkFlowService = (InvestOrderWorkFlowService) webContext
				.getBean("investOrderWorkFlowService");
	}

	/**
	 * 向用户发送消息
	 * 
	 * @param users
	 *            用户的信息
	 */
	protected void sendMessageByUsers(DelegateTask task, List<Users> users) {
		for (Users user : users) {
			String userId = String.valueOf(user.getUserId());

			Long claimCount = investOrderWorkFlowService
					.findAllClaimedTaskCount(userId);
			Long unClaimCount = investOrderWorkFlowService
					.findAllUnclaimedTaskCount() + 1;

			SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
					SendMessageUtil.DATATYPE_UNCLAIMINVESTORDER,
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
		
		Long claimCount = investOrderWorkFlowService
				.findAllClaimedTaskCount(userId) + 1;
		Long unClaimCount = investOrderWorkFlowService
				.findAllUnclaimedTaskCount() + 1;

		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_CLAIMINVESTORDER,
				SendMessageUtil.CALCULATETYPE_ADD, task.getId(), claimCount,
				unClaimCount), userId);
	}
}
