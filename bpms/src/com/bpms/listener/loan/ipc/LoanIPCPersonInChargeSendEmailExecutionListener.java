package com.bpms.listener.loan.ipc;

import java.text.MessageFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.bpms.model.LoanContract;
import com.bpms.model.LoanOrder;
import com.bpms.model.LoanOrderHis;
import com.bpms.model.Organization;
import com.bpms.model.Users;
import com.bpms.service.LoanContractService;
import com.bpms.service.LoanOrderHisService;
import com.bpms.service.LoanOrderService;
import com.bpms.service.OrganizationService;
import com.bpms.service.UserService;
import com.bpms.util.Constants;
import com.bpms.util.DateUtils;
import com.bpms.util.SendEmailUtil;
/**
 * IPC负责人申请通过之后发送的邮件提示信息
 * @author liuhh
 *
 */
@SuppressWarnings("serial")
public class LoanIPCPersonInChargeSendEmailExecutionListener implements
		ExecutionListener {

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		// 得到上下文环境
		WebApplicationContext cx = ContextLoader.getCurrentWebApplicationContext();
		LoanOrderService loanOrderService = (LoanOrderService) cx.getBean("loanOrderService");
		UserService userService = (UserService) cx.getBean("userService");
		OrganizationService organizationService = (OrganizationService) cx.getBean("organizationService");
		LoanContractService  loanContractService = (LoanContractService) cx.getBean("loanContractService");
		LoanOrderHisService loanOrderHisService = (LoanOrderHisService) cx.getBean("loanOrderHisService");

		// 对应的申请订单的ID
		String id = Constants.EMPTY;
		if (StringUtils.isNotBlank(execution.getProcessBusinessKey())) {
			id = execution.getProcessBusinessKey().split("\\.")[1];
		}
		
		// 获取当前业务订单的备注信息,获取当前IPCShuJuGang角色受理人,设置受理人信息
		LoanOrderHis loanOrderHis = loanOrderHisService.findLoanOrderHis(Constants.LOAN_ROLE_CODE_IPCSHUJUGANG, id);

		// 根据订单的id获取订单的信息
		LoanOrder order = loanOrderService.findLoanOrderById(id);
		order.setFinishDate(new Date());// 设置业务完成时间
		loanOrderService.persistenceLoanOrder(order);// 保存业务完成时间
		// 获取申请订单的所属分公司
		Organization organization = organizationService.findOrganizationById(order.getOrganizationId());
		
		// 获取用户的邮箱
		Users user = userService.findUserById(Integer.valueOf(loanOrderHis.getAssignee()));
		
		// 获取合同信息
		LoanContract loanContract = loanContractService.calculateContract(id);

		// 然后拒绝的通知：所在分公司，时间，客户姓名，客户经理，团队经理。拒绝理由：简版
		StringBuffer message = new StringBuffer();
		message.append("<h1>{0}发放通知</h1>");
		message.append("<strong>时间：{1}</strong>");
		message.append("<content>");
		message.append("	<p>客户姓名{2}，批复金额{3}元，{4}期，实放金额{5}元，月还{6}元，合同金额{7}元。客户经理{8}，请尽快通知客户签约。</p>");
		message.append("	<p>注：{9}</p>");
		message.append("</content>");
		
		// 给用户发送发放通知信息
		if (null != user) {
			String messageFormat = MessageFormat.format(
					message.toString(),
					organization.getFullName(),
					DateUtils.getNowTime(DateUtils.DATE_SMALL_STR),
					order.getName(), 
					loanContract.getLoanEdu(), 
					loanContract.getLoanPeriods(),
					loanContract.getActualLoanAmount(),
					loanContract.getMonthlyRepayment(),
					loanContract.getLoanEdu(),
					order.getSalesMan(),
					execution.getVariables().get("mailMessage"));
			MimeMessage mimeMessage = SendEmailUtil.createTextMail(organization.getFullName(), messageFormat, user.getEmail());
			SendEmailUtil.sendEmail(mimeMessage);
		}

	}
}
