package com.oasys.listener.pd.leaveAppCli.copy;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;


/**
 * @Creater chenfl
 * @File_Name FinancingDeptAssistantTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年5月25日 下午1:23:14
 * @Modifier
 * @Modified_Date
 * @Description 财富业务流程部门助理节点任务监听器
 */
@SuppressWarnings("serial")
public class LeaveAppAssistantTaskListener extends BaseTaskListener implements TaskListener {
	
	private static final long serialVersionUID = -344720324571393495L;
	
	private FixedValue roleCodeKey;

	public FixedValue getRoleCodeKey() {
		return roleCodeKey;
	}
	         
	public void setRoleCodeKey(FixedValue roleCodeKey) {
		this.roleCodeKey = roleCodeKey;
	}

	@Override
	public void notify(DelegateTask task) {
		
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT)){
			if(null != task.getVariable(Constants.CURRENT_USER_KEY))
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY).toString());
		}else{
			setTaskRoleCodeByTask(task);
		}
	}
}
