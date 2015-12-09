package com.oasys.listener.ad.StampTurnoverApp;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import com.oasys.listener.BaseTaskListener;
import com.oasys.util.Constants;



/**
 * @Creater lida
 * @File_Name StampTurnoverAppTaskListener.java
 * @Version v1.0
 * @Creation_Date 2015年10月8日 
 * @Modifier
 * @Modified_Date
 * @Description 流程运行时的监听器
 */
public class StampTurnoverAppTaskListener extends BaseTaskListener implements TaskListener{
	
	private static final long serialVersionUID = -344720324571393495L;

	@Override
	public void notify(DelegateTask task) {
		//申请调整时 将任务指定回提交申请人
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT)){
			if(null != task.getVariable(Constants.CURRENT_USER_KEY))
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY).toString());//申请调整时 将任务指定回提交申请人
		}else if(task.getTaskDefinitionKey().contains(Constants.AD_ROLE_CODE_RECIPIENT)){//如果是接收人
			//设置候选人为接收人
			task.setAssignee(task.getVariable(Constants.CURRENT_RECIPIENT_KEY)==null?"":task.getVariable(Constants.CURRENT_RECIPIENT_KEY).toString());
		}else{
			setTaskRoleCodeByTask(task);
		}
		
	}
}
