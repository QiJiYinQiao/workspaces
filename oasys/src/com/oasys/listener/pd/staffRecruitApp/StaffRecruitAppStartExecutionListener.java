package com.oasys.listener.pd.staffRecruitApp;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.TaskListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.oasys.listener.BaseTaskListener;
import com.oasys.service.StaffRecruitAppService;
import com.oasys.util.Constants;

public class StaffRecruitAppStartExecutionListener implements ExecutionListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		WebApplicationContext wx = ContextLoader.getCurrentWebApplicationContext();
		StaffRecruitAppService staffRecruitAppServiceImpl = (StaffRecruitAppService) wx.getBean("staffRecruitAppService");
		String tmpMraId = "";
		if(StringUtils.isNotBlank(execution.getProcessBusinessKey())){
			tmpMraId = execution.getProcessBusinessKey().split("\\.")[1];
		}
		//修改流程状态PROC_STATUS字段的值。
		staffRecruitAppServiceImpl.updateStaffRecruitProcessStatus(Integer.valueOf(tmpMraId), Constants.PROC_APPROVAL);
		System.out.println("^^^^^^^^^^^^^^^^^^在StaffRecruitAppStartExecutionListener中，修改完PROC_STATUS的状态。");
	}
}
