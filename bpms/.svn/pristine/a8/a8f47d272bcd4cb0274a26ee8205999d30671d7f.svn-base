package com.bpms.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.service.RoleService;
import com.bpms.service.UserService;

public abstract class BaseTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;

	public abstract String getRoleCode();

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

	}
}
