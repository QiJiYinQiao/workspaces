package com.oasys.serviceImpl.workFlow;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.service.UserService;
import com.oasys.service.WorkFlowBaseService;



@Service
public class WorkFlowBaseServiceImpl implements WorkFlowBaseService {
	/** 仓库的服务service */
	@Autowired
	protected RepositoryService repositoryService;

	/** 注入流程运行服务service. */
	@Autowired
	protected RuntimeService runtimeService;

	/** 注入节点任务服务service. */
	@Autowired
	protected TaskService taskService;

	/** 注入历史服务service. */
	@Autowired
	protected HistoryService historyService;

	/** 注入输入表单服务service. */
	@Autowired
	protected FormService formService;

	/** 处理用户的service. */
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ManagementService managementService;

}
