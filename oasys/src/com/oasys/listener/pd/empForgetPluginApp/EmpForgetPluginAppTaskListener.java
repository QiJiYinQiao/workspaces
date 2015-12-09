package com.oasys.listener.pd.empForgetPluginApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;
/**
 * 忘打卡申请userTask的Listener
 * @ClassName: empForgetPluginAppTaskListener 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年11月13日 下午2:46:54
 */
public class EmpForgetPluginAppTaskListener extends BaseTaskListener implements TaskListener{
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask task) {
		// TODO Auto-generated method stub
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT)){
			if(null != task.getVariable(Constants.CURRENT_USER_KEY))
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY).toString());
		}else{
			setTaskRoleCodeByTask(task);
		}
	}
}
