package com.oasys.listener.pd.leaveAppCli.copy;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;

/**
 * 人员分配监听器
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class LeaveAppAssistantTaskListener extends BaseTaskListener implements
		TaskListener {

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
		if (task.getTaskDefinitionKey().startsWith(
				Constants.APPLY_FOR_ADJUSTMENT) || task.getTaskDefinitionKey().startsWith(Constants.USERAPPLYOK)) {
			if (null != task.getVariable(Constants.CURRENT_USER_KEY)) {
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY)
						.toString());
				task.removeVariable(Constants.RCN_RESULT);
			}
		} else {
			setTaskRoleCodeByTask(task);
		}
	}
}
