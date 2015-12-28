package com.oasys.listener.ad.FinancialApprovalSubProcess;

import org.activiti.engine.delegate.DelegateTask;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;

public class FinancialApprovalSubProcessTaskListener extends BaseTaskListener{
	private static final long serialVersionUID = 1L;
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
