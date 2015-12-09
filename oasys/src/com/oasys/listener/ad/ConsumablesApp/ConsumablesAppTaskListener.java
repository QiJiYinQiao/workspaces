package com.oasys.listener.ad.ConsumablesApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;
/**
 * 
 * @Title: ConsumablesAppTaskListener.java 
 * @Package com.oasys.listener.ad.ConsumablesApp 
 * @Description: 各任务节点监听器
 * @author yuanzhongqiu  
 * @date 2015年10月12日 下午5:29:43 
 * @version V1.0
 */
public class ConsumablesAppTaskListener extends BaseTaskListener implements TaskListener{
	
	private static final long serialVersionUID = -344720324571393495L;

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
