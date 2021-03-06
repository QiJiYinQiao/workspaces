package com.oasys.listener.pd.overtimeApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;

@SuppressWarnings("serial")
public class OvertimeApprovelListener extends BaseTaskListener implements TaskListener {
	private FixedValue status;
	public FixedValue getStatus() {
		return status;
	}

	public void setStatus(FixedValue status) {
		this.status = status;
	}

	@Override
	public void notify(DelegateTask task) {
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT) || task.getTaskDefinitionKey().contains("JiaBanQueRen")){
			if(null != task.getVariable(Constants.CURRENT_USER_KEY))
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY).toString());
				task.removeVariable(Constants.RCN_RESULT);
		}else{
			setTaskRoleCodeByTask(task);
		}
		
//		Object curUserId=null;
//		curUserId = task.getVariable(Constants.CURRENT_USER_KEY);
//		if(task.getTaskDefinitionKey().contains("ApplyForAdjustment") || task.getTaskDefinitionKey().contains("JiaBanQueRen")){
//			curUserId = task.getVariable(Constants.CURRENT_USER_KEY);
//			if(null != curUserId){
//				task.setAssignee(curUserId.toString());
//			}
//		}else{
//			task.addCandidateGroup(Constants.getTaskDefIdByRoleCodeMap().get(task.getTaskDefinitionKey()));
////			task.setVariableLocal("role",Constants.getTaskRoleCodeMap().get(status.getValue(null)));
//		}
//		task.addCandidateGroup(getRoleCode());
	}
}
