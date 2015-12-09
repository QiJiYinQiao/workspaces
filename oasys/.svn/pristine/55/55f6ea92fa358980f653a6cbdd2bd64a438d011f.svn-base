package com.oasys.listener.ad.badgeApp;

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
public class BadgeAppAssistantTaskListener extends BaseTaskListener implements TaskListener {
	
	@Override
	public void notify(DelegateTask task) {
		
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT)){
			if(null != task.getVariable(Constants.CURRENT_USER_KEY))
				task.setAssignee(task.getVariable(Constants.CURRENT_USER_KEY).toString());//申请调整时 将任务指定回提交申请人
		}else{
			setTaskRoleCodeByTask(task);
			//当流程走到行政对接主管和对接专员是，添加行政经理能查看，但不能操作
			if(task.getTaskDefinitionKey().startsWith(Constants.AD_ROLE_CODE_ASSISTANTCHARGE) || task.getTaskDefinitionKey().startsWith(Constants.AD_ROLE_CODE_SPECIALIST)){
				task.addCandidateGroup(Constants.AD_ROLE_CODE_MGR);
			}
			
		}
	}
}
