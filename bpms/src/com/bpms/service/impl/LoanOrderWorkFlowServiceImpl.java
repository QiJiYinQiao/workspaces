package com.bpms.service.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.model.LoanOrder;
import com.bpms.model.LoanOrderHis;
import com.bpms.model.Role;
import com.bpms.service.LoanOrderHisService;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanOrderWorkFlowService;
import com.bpms.service.OrderStatusService;
import com.bpms.service.WorkFlowService;
import com.bpms.util.Collections;
import com.bpms.util.Constants;
import com.bpms.util.SendMessageUtil;

/**
 * 
 * 个人贷款业务申请单的workFlowServiceImp
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Service("loanOrderWorkFlowService")
public class LoanOrderWorkFlowServiceImpl extends WorkFlowBaseServiceImpl
		implements LoanOrderWorkFlowService {

	/** 贷款申请的业务service. */
	@Autowired
	private LoanOrderService loanOrderService;

	@Autowired
	private LoanOrderHisService loanOrderHisService;

	@Autowired
	private OrderStatusService orderStatusService;

	@Autowired
	private WorkFlowService workFlowService;

	@Override
	public boolean saveStartProcessInstance(LoanOrder loanOrder) {
		// 获取启动实例的key
		String proDefKey = loanOrder.getClass().getSimpleName();
		// 定义businessKey
		String businessKey = proDefKey + "." + loanOrder.getLoanOrderId();

		// 指定流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());
		variables.put(Constants.CURRENT_USER_KEY, userId);

		// 启动流程实例
		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(
				proDefKey, businessKey, variables);

		return pi != null ? true : false;
	}

	@Override
	public List<LoanOrder> findAllClaimTask(Integer firstResult,
			Integer maxResults) {
		// 定义返回值
		List<LoanOrder> list = new ArrayList<LoanOrder>();
		// 获取当前用户的角色
		String userID = String.valueOf(Constants.getCurrendUser().getUserId());
		List<Task> tasks = this.taskService.createTaskQuery()
				.processDefinitionKey(LoanOrder.class.getSimpleName())
				.taskAssignee(String.valueOf(userID))
				.listPage(firstResult, maxResults);
		// 判断查询的结果集是否为空,如果不为空的,则根据流程实例的id查询订单的信息,并放入list返回结果集中
		if (Collections.listIsNotEmpty(tasks)) {
			for (Task task : tasks) {
				// 根据订单的id获取订单的信息
				LoanOrder loanOrder = this.findLoanOrderByTaskId(task.getId());
				LoanOrder order = (LoanOrder) loanOrder.clone();
				order.setTaskId(task.getId());
				list.add(order);
			}
		}
		return list;
	}

	@Override
	public Long findAllClaimTaskCount() {
		return this.taskService
				.createTaskQuery()
				.processDefinitionKey(LoanOrder.class.getSimpleName())
				.taskAssignee(
						String.valueOf(Constants.getCurrendUser().getUserId()))
				.count();
	}

	@Override
	public List<LoanOrder> findAllUnClaimTask(Integer firstResult,
			Integer maxResults) {
		// 定义返回值
		List<LoanOrder> list = new ArrayList<LoanOrder>();
		// 执行查询
		List<Task> tasks = this.taskService
				.createTaskQuery()
				.processDefinitionKey(LoanOrder.class.getSimpleName())
				.taskCandidateUser(
						String.valueOf(Constants.getCurrendUser().getUserId()))
				.listPage(firstResult, maxResults);

		// 判断查询的结果集是否为空,如果不为空的,则根据流程实例的id查询订单的信息,并放入list返回结果集中
		if (Collections.listIsNotEmpty(tasks)) {
			for (Task task : tasks) {
				// 根据订单的id获取订单的信息
				LoanOrder loanOrder = this.findLoanOrderByTaskId(task.getId());
				if (null != loanOrder) {
					LoanOrder order = (LoanOrder) loanOrder.clone();
					order.setTaskId(task.getId());
					list.add(order);
				}
			}
		}
		return list;
	}

	@Override
	public Long findAllUnClaimTaskCount() {
		return this.taskService
				.createTaskQuery()
				.processDefinitionKey(LoanOrder.class.getSimpleName())
				.taskCandidateUser(
						String.valueOf(Constants.getCurrendUser().getUserId()))
				.count();
	}

	@Override
	public LoanOrder findLoanOrderByTaskId(String taskId) {
		// 使用任务ID，查询任务对象Task
		Task task = taskService.createTaskQuery()//
				.taskId(taskId)// 使用任务ID查询
				.singleResult();
		// 使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();
		// 获取BusinessKey对应的申请订单的ID
		String id = "";
		if (StringUtils.isNotBlank(pi.getBusinessKey())) {
			// 截取字符串，取BusinessKey小数点的第2个值
			id = pi.getBusinessKey().split("\\.")[1];
		}
		// 查询贷款申请单的信息
		LoanOrder loanOrder = this.loanOrderService.findLoanOrderById(id);
		return loanOrder;
	}

	@Override
	public void saveClaimTask(String taskId)
			throws ActivitiTaskAlreadyClaimedException {
		// 签收任务
		this.taskService.claim(taskId,
				String.valueOf(Constants.getCurrendUser().getUserId()));
		// 给受理人发送任务增加消息
		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_CLAIMLOANORDER,
				SendMessageUtil.CALCULATETYPE_ADD, taskId), String
				.valueOf(Constants.getCurrendUser().getUserId()));

		// 给代办人发送任务减少消息
		SendMessageUtil.sendMessageByUserIds(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_UNCLAIMLOANORDER,
				SendMessageUtil.CALCULATETYPE_SUBTRACT, taskId),
				workFlowService.findRunTaskAllHandlePersons(taskId));
	}

	@Override
	public void saveUnCliamTask(String taskId) {
		this.taskService.unclaim(taskId);
	}

	@Override
	public void updateCliamTaskUser(String taskId, String userId) {
		this.taskService.setAssignee(taskId, userId);
		// 发送受理任务增加提示
		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_CLAIMLOANORDER,
				SendMessageUtil.CALCULATETYPE_ADD, taskId), userId);
	}

	@Override
	public void saveSubmitTask(LoanOrder loanOrder, String comment,
			String result, String processingResult, String isInvolvedLoanCar) {
		// 获取当前执行任务
		Task task = this.taskService.createTaskQuery()
				.taskId(loanOrder.getTaskId()).singleResult();

		// 获取当前登陆用户的信息
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());

		// 获取当前处理人的角色
		Role role = taskService.getVariableLocal(task.getId(), "role",
				Role.class);
		// 根据订单状态的code,获取对应的订单id
		String orderStatusId = orderStatusService.getOrderStatusByStatusCode(
				LoanOrder.class.getSimpleName() + "_" + result).getStatusId();

		// 记录订单履历
		LoanOrderHis his = new LoanOrderHis();
		his.setComment(comment);
		his.setAgentTime(new Date());
		his.setAssignee(userId);
		his.setLoanOrderId(loanOrder.getLoanOrderId());
		his.setOrderStatus(orderStatusId);
		his.setRoleId(String.valueOf(role.getRoleId()));
		his.setProcessingResult(processingResult);
		his.setIsInvolvedLoanCar(isInvolvedLoanCar);
		loanOrderHisService.saveLoanOrderHis(his);

		// 设置流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(result) && !result.equals("默认提交")) {
			variables.put("result", result);
		}

		// 修改订单的状态
		if (!result.equals("LRC01Through") && !result.equals("LRC02Through")
				&& !result.equals("LRC03Through")) {
			this.loanOrderService.updateLoanOrderStauts(
					loanOrder.getLoanOrderId(), result);
		}
		// 使用任务ID，完成当前人的个人任务，同时设置流程变量
		taskService.complete(task.getId(), variables);

		// 流程结束了
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
				.processInstanceId(task.getProcessInstanceId())// 使用流程实例ID查询
				.singleResult();

		// 判断流程是否结束,如果结束修改流程的状态
		if (pi == null) {
			// 更新请假单表的状态从1变成2（审核中-->审核完成）
			LoanOrder loanorder = this.loanOrderService
					.findLoanOrderById(loanOrder.getLoanOrderId());
			loanorder.setProcessStatus("2");
		}
	}

	@Override
	public void getDiagramResourceByLoanOrderId(String loanOrderId)
			throws IOException {
		// 图片的文件的流
		InputStream in = null;
		try {
			// 根据申请订单的业务的ID组织执行流程实例的busniessKey
			String businessKey = LoanOrder.class.getSimpleName() + "."
					+ loanOrderId;

			// 获取当前执行的流程实例
			ProcessInstance pi = this.runtimeService
					.createProcessInstanceQuery()
					.processInstanceBusinessKey(businessKey).singleResult();

			// 获取流程定义的实体对象（对应.bpmn文件中的数据）
			ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService
					.getProcessDefinition(pi.getProcessDefinitionId());

			// 获取当前执行任务流程
			List<Task> tasks = this.taskService.createTaskQuery()
					.processInstanceBusinessKey(businessKey).list();

			// 获取图片的流程图片名称
			String resourceName = LoanOrder.class.getSimpleName() + ".png";

			// 获取图片的文件的流
			in = this.repositoryService.getResourceAsStream(
					pd.getDeploymentId(), resourceName);

			// 获取图片对象
			BufferedImage bimg;

			bimg = ImageIO.read(in);

			// 得到Graphics2D 对象
			Graphics2D g2d = (Graphics2D) bimg.getGraphics();
			// 设置颜色和画笔粗细
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(3));

			// 遍历执行的任务,画图
			for (Task task : tasks) {
				// 根据任务的获取当前执行对象的id,根据执行对象的id获取执行对象的信息
				Execution execution = runtimeService.createExecutionQuery()
						.executionId(task.getExecutionId()).singleResult();

				// 根据当前的执行对象的id获取正在执行的活动信息
				ActivityImpl activityImpl = pd.findActivity(execution
						.getActivityId());

				// 绘制矩形
				Rectangle2D rectangle = new Rectangle2D.Float(
						activityImpl.getX(), activityImpl.getY(),
						activityImpl.getWidth(), activityImpl.getHeight());
				g2d.draw(rectangle);
			}

			// 写入response输出流中
			ImageIO.write(bimg, "png", ServletActionContext.getResponse()
					.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
		}
	}

}
