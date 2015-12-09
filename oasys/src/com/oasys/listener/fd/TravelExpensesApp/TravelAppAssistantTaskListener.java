package com.oasys.listener.fd.TravelExpensesApp;

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
public class TravelAppAssistantTaskListener extends BaseTaskListener implements TaskListener {
	

	@Override
	public void notify(DelegateTask task) {
		
		if(task.getTaskDefinitionKey().startsWith(Constants.APPLY_FOR_ADJUSTMENT)){
			if(null != task.getVariable(Constants.APPUSER))
				//提交审可能不是申请人
				task.setAssignee(task.getVariable(Constants.APPUSER).toString());		}else{
			setTaskRoleCodeByTask(task);
		}
	}
}
