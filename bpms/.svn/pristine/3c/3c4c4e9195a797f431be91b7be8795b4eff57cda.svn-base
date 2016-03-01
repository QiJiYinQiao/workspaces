package com.bpms.listener.investRedeem;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;

import com.bpms.listener.BaseTaskListener;
import com.bpms.model.Users;
import com.bpms.service.InvestRedeemWorkFlowService;
import com.bpms.util.SendMessageUtil;

public abstract class BaseInvestRedeemTaskListener extends BaseTaskListener {

	private static final long serialVersionUID = -1470096173804070260L;
	
	// 投资赎回的业务操作方法
	protected InvestRedeemWorkFlowService investRedeemWorkFlowService;

	{
		investRedeemWorkFlowService = (InvestRedeemWorkFlowService) webContext
				.getBean("investRedeemWorkFlowService");
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

			Long claimCount = investRedeemWorkFlowService
					.findAllClaimedTaskCount(userId);
			Long unClaimCount = investRedeemWorkFlowService
					.findAllUnclaimedTaskCount() + 1;

			SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
					SendMessageUtil.DATATYPE_UNCLAIM_INVEST_REDEEM_ORDER,
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
		
		Long claimCount = investRedeemWorkFlowService
				.findAllClaimedTaskCount(userId) + 1;
		Long unClaimCount = investRedeemWorkFlowService
				.findAllUnclaimedTaskCount() + 1;

		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_CLAIM_INVEST_REDEEM_ORDER,
				SendMessageUtil.CALCULATETYPE_ADD, task.getId(), claimCount,
				unClaimCount), userId);
	}

}
