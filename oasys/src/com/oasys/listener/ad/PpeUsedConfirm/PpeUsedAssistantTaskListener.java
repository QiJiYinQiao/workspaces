package com.oasys.listener.ad.PpeUsedConfirm;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;

/**
 * 节点分配监听器
 * 
 * @Title: PpeUsedAssistantTaskListener.java
 * @Package com.oasys.listener.ad.PpeUsedConfirm
 * @Description: TODO
 * @author WANGXINCHENG
 * @date 2015年12月7日 下午5:22:09
 * @version V1.0
 */
@SuppressWarnings("serial")
public class PpeUsedAssistantTaskListener extends BaseTaskListener implements
		TaskListener {

	@Override
	public void notify(DelegateTask task) {

		if (task.getTaskDefinitionKey().startsWith(
				Constants.APPLY_FOR_ADJUSTMENT) || task.getTaskDefinitionKey().startsWith(Constants.USERAPPLYOK)) {
			if (null != task.getVariable(Constants.APPUSER)) {
				// 提交审可能不是申请人
				task.setAssignee(task.getVariable(Constants.APPUSER).toString());
				task.removeVariable(Constants.RCN_RESULT);// 申请调整时 将任务指定回提交申请人
			}
		} else {
			setTaskRoleCodeByTask(task);
		}
	}
}
