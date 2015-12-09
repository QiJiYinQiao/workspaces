package com.oasys.listener;

import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.model.Users;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.service.TaskRoleService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;
import com.oasys.util.SendMessageUtil;
import com.oasys.viewModel.ProcessNameModel;
import com.oasys.viewModel.WorkFlowTasksModel;

public abstract class BaseTaskListener implements TaskListener {

	private static final long serialVersionUID = 1L;
	
	// 上下文
	protected WebApplicationContext webContext;
	// 用户的UserService
	protected UserService userService;
	// 角色的service
	protected RoleService roleService;
	
	protected OrganizationService orgService;
	
	protected TaskRoleService taskRoleService;
	
	//流程的service
	protected WorkFlowTaskService workFlowTaskService;
	{
		webContext = ContextLoader.getCurrentWebApplicationContext();
		userService = (UserService) webContext.getBean("userService");
		roleService = (RoleService) webContext.getBean("roleService");
		workFlowTaskService = (WorkFlowTaskService) webContext.getBean("workFlowTaskService");
		orgService = (OrganizationService) webContext.getBean("organizationService");
		taskRoleService = (TaskRoleService) webContext.getBean("taskRoleService");
		
	}
	
	/***
	 * 根据节点找到节点对象所对应的候选组 如找不到 则跳转下一流程节点进行处理
	 * @param task 节点对象
	 * @param taskGroupCode 节点对象对应的候选组code
	 * @param orgID 组织机构ID
	 */
	protected void setTaskGroupOrVariables(DelegateTask task,String taskGroupCode){
		//如果未找到候选组
		if(StringUtils.isBlank(taskGroupCode)){
			String result = Constants.TASK_COMMIT_RESULT;
			//获取task节点的formKeyJson 判断是 角色比对 或 财富端借款端比对
			if(StringUtils.isNotBlank(task.getFormKey()) && task.getFormKey().indexOf("?") > 0){
				WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
				taskModel.setFormKey(task.getFormKey());
				taskModel.setTaskID(task.getId());
				result = workFlowTaskService.getResultByFormKey(taskModel,task.getVariable(Constants.CURRENT_USER_KEY).toString());
			}
			task.setVariable(Constants.RCN_RESULT, result);//设置跳转流程变量 跳转下一节点处理 
		}else{
			task.addCandidateGroup(taskGroupCode);
		}
	}
	
	protected void setTaskRoleCodeByTask(DelegateTask task) {
		//定制区域部门角色处理 会在定制区域内查找申请人所属部门是否存在 如果存在 则将这部分用户查找出后 设置为该任务的候选人条件
		if(StringUtils.isNotBlank(Constants.getRoleCodeJumpFuction(6).get(task.getTaskDefinitionKey()))){
			taskRoleService.setTaskAssisantByTask(task);
		}else{
		//非定制区域角色 查找候选组 并设置候选组条件	
			taskRoleService.setTaskRoleCodeByTask(task);
		}
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
			List<ProcessNameModel> processNameList = workFlowTaskService.processNameList();
			for(ProcessNameModel model:processNameList){
				model.getProcessKey();
				model.getProcessName();
				model.getTaskCount();
			}
			//单个申请的个数
			Long taskOACount = workFlowTaskService
					.findAllTaskOACount(userId);

			SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
					SendMessageUtil.DATATYPE_TASKOA,
					SendMessageUtil.CALCULATETYPE_ADD, task.getId(),
					taskOACount), userId);
		}
	}

	/**
	 * 向用户发送消息
	 * 
	 * @param userId
	 *            指定的用户
	 */
	protected void sendMessageByUserId(DelegateTask task, String userId) {
		List<ProcessNameModel> processNameList = workFlowTaskService.processNameList();
		for(ProcessNameModel model:processNameList){
			model.getProcessKey();
			model.getProcessName();
			model.getTaskCount();
		}
//		Long taskOACount = workFlowTaskService.findAllTaskOACount(userId);

		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_TASKOA,
				SendMessageUtil.CALCULATETYPE_ADD, task.getId(), taskOACount), userId);
	}
	
}
