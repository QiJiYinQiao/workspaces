package com.bpms.listener.loan;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.LoanOrder;
import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanOrderWorkFlowService;
import com.bpms.service.RoleService;
import com.bpms.service.UserService;
import com.bpms.util.Collections;
import com.bpms.util.SendMessageUtil;

/**
 * 贷款业务的基类监听器
 * 
 * @author liuhh
 *
 */
public abstract class BaseLoanTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	// 贷款订单的业务操作方法
	protected LoanOrderService loanOrderService;

	// 上下文
	protected WebApplicationContext webContext;

	// 用户的UserService
	protected UserService userService;

	// 角色的service
	protected RoleService roleService;

	// activiti历史任务查看服务
	protected HistoryService historyService;

	// 贷款业务的工作流的业务操作
	protected LoanOrderWorkFlowService loanOrderWorkFlowService;

	// 初始化
	{
		webContext = ContextLoader.getCurrentWebApplicationContext();
		userService = (UserService) webContext.getBean("userService");
		roleService = (RoleService) webContext.getBean("roleService");
		loanOrderService = (LoanOrderService) webContext
				.getBean("loanOrderService");
		loanOrderWorkFlowService = (LoanOrderWorkFlowService) webContext
				.getBean("loanOrderWorkFlowService");
		historyService = (HistoryService) webContext.getBean("historyService");
	}

	// 参与该活动的活动节点角色组
	protected abstract String getRoleCode();

	// 获取该活动节点参与的组织机构信息
	protected abstract String[] getOrganizationIds();

	// 根据当前的活动节点获取申请订单的信息
	protected LoanOrder getLoanOrderByTaskId(DelegateTask task) {
		if (StringUtils.isNotBlank(task.getExecution().getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值
			String id = task.getExecution().getProcessBusinessKey()
					.split("\\.")[1];
			return loanOrderService.findLoanOrderById(id);
			// 根据订单的id获取订单的信息
		}
		return null;
	}

	// 设置总部受理组并发送消息
	protected void setRoleCodeHQ(DelegateTask task) {
		String[] organizationIds = getOrganizationIds();
		if (null != organizationIds && organizationIds.length != 0) {
			for (String id : organizationIds) {
				task.addCandidateGroup(getRoleCode() + "." + id);
				List<Users> hqUsers = userService
						.findUsersByRoleCodeAndOrganizationId(getRoleCode(),
								Integer.valueOf(id));
				if (Collections.listIsNotEmpty(hqUsers)) {
					sendMessageByUsers(task, hqUsers);
				}
			}
		}
	}

	// 设置地方候选组及发送通知消息
	protected void setRoleCodeLoacl(DelegateTask task) {
		LoanOrder order = getLoanOrderByTaskId(task);
		if (null != order) {
			task.addCandidateGroup(getRoleCode() + "."
					+ order.getOrganizationId());
			List<Users> localUsers = userService
					.findUsersByRoleCodeAndOrganizationId(getRoleCode(),
							order.getOrganizationId());
			if (Collections.listIsNotEmpty(localUsers)) {
				sendMessageByUsers(task, localUsers);
			}
		}
	}

	// 设置当前任务的处理角色
	protected void setRoleCodeVariableLocal(DelegateTask task) {
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);
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
