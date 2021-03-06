package com.bpms.listener.loan.ipc;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.bpms.listener.loan.BaseLoanTaskListener;
import com.bpms.model.LoanOrder;
import com.bpms.model.Users;
import com.bpms.service.UserAndOrganizationService;
import com.bpms.util.Collections;
import com.bpms.util.Constants;

/**
 * 小额调查活动节点监听器
 * 
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCPerformOutboundTaskListener extends BaseLoanTaskListener  implements TaskListener{
	
	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCXIAOERDIAOCHA;
	}

	@Override
	protected String[] getOrganizationIds() {
		return null;
	}

	@Override
	public void notify(DelegateTask task) {
		// 设置当前用户执行的角色信息
		setLocalVariableRole(task);
		// 设置地方执行候选人组
		addCandidateGroupLocal(task);
		// 获取当前订单信息
		LoanOrder loanOrder = getLoanOrderByTaskId(task);
		// 获取组织机构的信息，获取外援人员信息，设置外援为候选人
		UserAndOrganizationService userAndOrganizationService = (UserAndOrganizationService) webContext.getBean("userAndOrganizationService");
		List<Users> users = userAndOrganizationService.findBindingUsersByOrganizationId(String.valueOf(loanOrder.getOrganizationId()));
		if(Collections.listIsNotEmpty(users)){
			// 设置外援人员的信息
			for (Users user : users) {
				task.addCandidateUser(String.valueOf(user.getUserId()));
			}
		}
		// 发送消息提醒
		sendMessageByCandidateGroup(task);
	}

}
