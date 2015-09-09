package com.bpms.activiti.model;

import java.util.Date;
import java.util.Map;

public class ActRuTask implements IActInterface {

	/** DB id of the task. */
	private String id;

	/**
	 * Name or title of the task.
	 */
	private String name;

	/**
	 * Free text description of the task.
	 */
	private String description;

	/**
	 * Indication of how important/urgent this task is
	 */
	private int priority;

	/**
	 * task.
	 */
	private String owner;

	/**
	 */
	private String assignee;

	private String userName;

	/**
	 * Reference to the process instance or null if it is not related to a
	 * process instance.
	 */
	private String processInstanceId;

	/**
	 * Reference to the path of execution or null if it is not related to a
	 * process instance.
	 */
	private String executionId;

	/**
	 * Reference to the process definition or null if it is not related to a
	 * process.
	 */
	private String processDefinitionId;

	/** The date/time when this task was created */
	private Date createTime;

	/**
	 * The id of the activity in the process defining this task or null if this
	 * is not related to a process
	 */
	private String taskDefinitionKey;

	/**
	 * Due date of the task.
	 */
	private Date dueDate;

	/**
	 * The category of the task. This is an optional field and allows to 'tag'
	 * tasks as belonging to a certain category.
	 */
	private String category;

	/**
	 * The parent task for which this task is a subtask
	 */
	private String parentTaskId;

	/**
	 * The tenant identifier of this task
	 */
	private String tenantId;

	/**
	 * The form key for the user task
	 */
	private String formKey;

	/**
	 * Returns the local task variables if requested in the task query
	 */
	private Map<String, Object> taskLocalVariables;

	/**
	 * Returns the process variables if requested in the task query
	 */
	private Map<String, Object> processVariables;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public Map<String, Object> getTaskLocalVariables() {
		return taskLocalVariables;
	}

	public void setTaskLocalVariables(Map<String, Object> taskLocalVariables) {
		this.taskLocalVariables = taskLocalVariables;
	}

	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getUserId() {
		return this.getAssignee();
	}
}
