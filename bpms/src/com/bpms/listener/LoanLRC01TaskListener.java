package com.bpms.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.LoanOrder;
import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.service.LoanOrderService;
import com.bpms.service.RoleService;
import com.bpms.service.UserService;
import com.bpms.util.Constants;
import com.bpms.util.SendMessageUtil;

/**
 * @Creater chenfl
 * @File_Name IPCAuditTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 贷审委一
 */
@SuppressWarnings("serial")
public class LoanLRC01TaskListener extends BaseTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_LRC01;
	}

	@Override
	public void notify(DelegateTask task) {
		// 得到上下文环境
		WebApplicationContext webContext = ContextLoader
				.getCurrentWebApplicationContext();
		// 使用上下文环境中的getBean方法得到bean实例
		UserService userService = (UserService) webContext
				.getBean("userService");
		RoleService roleService = (RoleService) webContext
				.getBean("roleService");
		LoanOrderService loanOrderService = (LoanOrderService) webContext
				.getBean("loanOrderService");
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
		// 设置执行人
		for (Users user : users) {
			task.addCandidateUser(user.getUserId().toString());
		}

		// 获取总公司贷审委1的人信息
		List<Users> headOfficeUsers = userService
				.findUsersByRoleCodeAndOrganizationId(getRoleCode(),
						Constants.LOAN_ORGANIZATION_ID);
		// 设置执行人
		for (Users user : headOfficeUsers) {
			task.addCandidateUser(user.getUserId().toString());
		}
		Role role = roleService.findRoleByCode(getRoleCode());
		task.createVariableLocal("role", role);

		// 发送消息
		SendMessageUtil.sendMessageByUsers(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_UNCLAIMLOANORDER,
				SendMessageUtil.CALCULATETYPE_ADD, task.getId()), users);
	}
}
