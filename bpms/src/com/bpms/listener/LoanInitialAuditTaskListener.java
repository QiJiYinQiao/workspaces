package com.bpms.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.service.RoleService;
import com.bpms.service.UserService;
import com.bpms.util.Constants;
import com.bpms.util.SendMessageUtil;

/**
 * @Creater chenfl
 * @File_Name InitialAuditTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 借款业务流程初审部门节点任务监听器
 */
@SuppressWarnings("serial")
public class LoanInitialAuditTaskListener extends BaseTaskListener {

	@Override
	public String getRoleCode() {
		return Constants.LOAN_ROLE_CODE_INITIALAUDIT;
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
		List<Users> users = userService.findUsersByRoleCode(getRoleCode());
		Role role = roleService.findRoleByCode(getRoleCode());
		for (Users user : users) {
			task.addCandidateUser(user.getUserId().toString());
		}
		task.createVariableLocal("role", role);
		// 发送消息
		SendMessageUtil.sendMessageByUsers(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_UNCLAIMLOANORDER,
				SendMessageUtil.CALCULATETYPE_ADD, task.getId()), users);

	}
}
