package com.oasys.listener.CredentialsApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;

public class CredentialsAppTaskListener extends BaseTaskListener implements TaskListener{
	private static final long serialVersionUID = 3209239878729705913L;

	@Override
	public void notify(DelegateTask task) {
		//申请调整时 将任务指定回提交申请人
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT)){
			if(null != task.getVariable(Constants.CURRENT_USER_KEY))
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY).toString());//申请调整时 将任务指定回提交申请人
		}else{
			setTaskRoleCodeByTask(task);
		}
	}
}
