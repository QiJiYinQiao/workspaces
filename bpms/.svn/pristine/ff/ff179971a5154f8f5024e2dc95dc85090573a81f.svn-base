package com.bpms.listener2;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.LoanOrder;
import com.bpms.model.Loaner;
import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanerService;
import com.bpms.service.UserService;
import com.bpms.util.Collections;
import com.bpms.util.Constants;
import com.bpms.util.SendEmailUtil;

@SuppressWarnings("serial")
public class LoanIPCPersonInChargeSendEmailExecutionListener implements ExecutionListener {
	
	private Expression defId;

	public Expression getDefId() {
		return defId;
	}

	public void setDefId(Expression defId) {
		this.defId = defId;
	}

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		String pid =execution.getProcessInstanceId();
		// 得到上下文环境
		WebApplicationContext cx = ContextLoader.getCurrentWebApplicationContext();
		LoanOrderService loanOrderService = (LoanOrderService) cx.getBean("loanOrderService");
		LoanerService loanerService = (LoanerService)cx.getBean("loanerService");
		UserService userService = (UserService) cx.getBean("userService");
		HistoryService historyService = (HistoryService) cx.getBean("historyService");
		HistoricTaskInstance hTask = historyService.createHistoricTaskInstanceQuery().processInstanceId(pid).taskDefinitionKey("IPCData").singleResult();
		HistoricTaskInstance hTask2 = historyService.createHistoricTaskInstanceQuery().processInstanceId(pid).taskDefinitionKey((String)getDefId().getValue(execution)).singleResult();
		HistoricVariableInstance hVariable = historyService.createHistoricVariableInstanceQuery().processInstanceId(pid).variableName(Constants.CURRENT_USER_KEY).singleResult();
		HistoricVariableInstance hVariable2 = historyService.createHistoricVariableInstanceQuery().processInstanceId(pid).taskId(hTask2.getId()).variableName("role").singleResult();
		String ipcDataUser = hTask.getAssignee();
		String salesman = (String) hVariable.getValue();
		
		List<String> userIds = new ArrayList<String>();
		userIds.add(salesman);
		userIds.add(ipcDataUser);
		// 获取用户的邮箱
		List<Users> users = userService.findUsersByUserIds(userIds);
		

		// 获取BusinessKey对应的申请订单的ID
		String id = "";
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值
			id = execution.getProcessBusinessKey().split("\\.")[1];
		}
		
		// 根据订单的id获取订单的信息
		LoanOrder order = loanOrderService.findLoanOrderById(id);
		// 贷款申请拒掉的角色
		Role role = (Role) hVariable2.getValue();
		
		// 获取贷款人
		Loaner loaner = loanerService.findById(order.getLoanerId());
		if(Collections.listIsNotEmpty(users)){
			for (Users user : users) {
				MimeMessage message = SendEmailUtil.createTextMail("贷款申请已通过IPC负责人申请!", "订单号:"+order.getLoanerId()+"\n客户名:"+loaner.getName()+"\n已经被"+role.getName()+":"+Constants.getCurrendUser().getUser().getName()+"通过申请!!!", user.getEmail());
				SendEmailUtil.sendEmail(message);
			}
		}
		
	}

	
}
