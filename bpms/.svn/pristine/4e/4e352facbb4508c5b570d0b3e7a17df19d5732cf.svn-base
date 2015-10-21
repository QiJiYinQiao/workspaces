package com.bpms.listener2;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.commons.lang.StringUtils;

import com.bpms.listener.BaseLoanTaskListener;
import com.bpms.model.LoanOrder;
import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.util.Collections;
import com.bpms.util.Constants;
/**
 * IPC负责人活动节点的监听器
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCPersonInChargeTaskListener extends BaseLoanTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_IPCFUZEREN;
	}
	
	@Override
	public void notify(DelegateTask task) {
		// 获取BusinessKey对应的申请订单的ID
		String id = "";
		if (StringUtils.isNotBlank(task.getExecution().getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值
			id = task.getExecution().getProcessBusinessKey().split("\\.")[1];
		}

		// 根据订单的id获取订单的信息
		LoanOrder order = loanOrderService.findLoanOrderById(id);
		// 根据组织机构的id和roleCode获取用户信息
		List<Users> users = userService.findUsersByRoleCodeAndOrganizationId(
				getRoleCode(), order.getOrganizationId());

		// 获取当前用户执行的角色的信息
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);

		// 用户信息部位则进行发送消息
		if (Collections.listIsNotEmpty(users)) {
			for (Users user : users) {
				task.addCandidateUser(user.getUserId().toString());
			}
			sendMessageByUsers(task, users);
		}
	}
}
