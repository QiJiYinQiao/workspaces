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
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.model.InvestOrder;
import com.bpms.model.InvestOrderHis;
import com.bpms.model.Role;
import com.bpms.model.Users;
import com.bpms.service.InvestOrderHisService;
import com.bpms.service.InvestOrderService;
import com.bpms.service.InvestRedeemWorkFlowService;
import com.bpms.service.OrderStatusService;
import com.bpms.service.RoleService;
import com.bpms.service.UserService;
import com.bpms.service.WorkFlowService;
import com.bpms.util.Collections;
import com.bpms.util.Constants;
import com.bpms.util.SendMessageUtil;

@Service("investRedeemWorkFlowService")
public class InvestRedeemWorkFlowServiceImpl extends WorkFlowBaseServiceImpl implements InvestRedeemWorkFlowService{		

	@Autowired
	private InvestOrderService investOrderServiceImple;
	@Autowired
	private InvestOrderHisService investOrderHisService;
	@Autowired
	private OrderStatusService orderStatusService;
	@Autowired
	private UserService userService;
	@Autowired
	private WorkFlowService workFlowService;
	@Autowired
	private RoleService roleService;
	
	private static String PROCESS_DEFINITION_KEY = "InvestRedeemProcess";

	@Override
	public boolean saveStartProcess4InvestRedeem(InvestOrder investOrder) {		
		//思路：将BPMN图中的Process整体ID(ProcessDefinitionKey)与investOrderId相结合，创造BUSINESS_KEY
		//1、获取processDefinitionKey（即processDefinitionId）
		String processDefinitionKey = PROCESS_DEFINITION_KEY;
		//2、获取Business_Key
		String businessKey = processDefinitionKey + "." + investOrder.getInvestOrderId();
		//3、定义流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());
		//说明：将启动当前的用户ID（比如，理财经理01的userId）存放到“流程变量”中，
		//好处：在整个流程实例的任意节点，当我们需要知道是哪个用户启动了该流程时，仅仅通过该流程变量就可以实现。
		variables.put(Constants.CURRENT_USER_KEY, userId);
		//4、启动流程实例
		ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);		
		return (processInstance!=null) ? true : false;
	}
	
	@Override
	public void checkWorkFlowImg4InvestRedeemByInvestOrderId(String investOrderId) throws IOException{
		// 根据投资订单id组织执行流程实例的businessKey
		String businessKey = PROCESS_DEFINITION_KEY + "."
				+ investOrderId;
		// 根据businessKey获取当前执行的流程实例
		ProcessInstance pi = this.runtimeService.createProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey).singleResult();
		// 获取流程定义的实体对象(对应的.bpmn的东西)
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(pi.getProcessDefinitionId());
		// 获取当前执行任务流程数组
		List<Task> tasks = this.taskService.createTaskQuery()
				.processInstanceBusinessKey(businessKey).list();
		// 获取流程图片的名称
		String resourceName = PROCESS_DEFINITION_KEY + ".png";
		// 获取图片的文件的流
		InputStream in = this.repositoryService.getResourceAsStream(
				pd.getDeploymentId(), resourceName);
		// 获取图片对象
		BufferedImage bimg = ImageIO.read(in);
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
			Rectangle2D rectangle = new Rectangle2D.Float(activityImpl.getX(),
					activityImpl.getY(), activityImpl.getWidth(),
					activityImpl.getHeight());
			g2d.draw(rectangle);
		}
		// 写入response输出流中
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/octet-stream");
		ImageIO.write(bimg, "png", response
				.getOutputStream());
	}

	@Override
	public List<InvestOrder> findAllUnclaimedTaskList(Integer firstResult, Integer maxResults) {
		
		//1.声明有待返回的InvestOrderList
		List<InvestOrder> investApplyOrderList = new ArrayList<InvestOrder>();

		//2.查询当前用户“所属角色”的所有未签收的Task列表数据。
		List<Task> taskList = this.taskService.createTaskQuery().processDefinitionKey(PROCESS_DEFINITION_KEY)
		                .taskCandidateGroupIn(Constants.getCurrendUser().getRoleCodes())    //根据当前登陆人的拥有的“所有角色”来查找未签收的Task列表。
		                .listPage(firstResult, maxResults);  //分页

		//3.获取与TaskList相关的所有的InvestOrder的信息
		//根据单个Task对象 --> 获得investOrderId --> 获得investOrder对象
		if(Collections.listIsNotEmpty(taskList)){			
			for(Task oneTask : taskList){
				ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(oneTask.getProcessInstanceId()).singleResult();
				String businessKey = processInstance.getBusinessKey();
				
				//获取investOrderId
				String investOrderId = businessKey.split("\\.")[1];
				if(StringUtils.isNotBlank(investOrderId)){
					//获得“赎回类型的”investOrder对象
//					InvestOrder investOrderObj = investOrderServiceImple.findByInvestOrderId(investOrderId);
					InvestOrder investOrderObj = investOrderServiceImple.findInvestOrderById4RedeemType(investOrderId);
					InvestOrder investOrderCloneObj = null;					
					if(null != investOrderObj){						
						//设置单个InvestOrder中的taskId
						investOrderObj.setTaskId(oneTask.getId());
						investOrderCloneObj = (InvestOrder) investOrderObj.clone();					
					}															
					//将单个investOrder对象放入list列表中去。
					investApplyOrderList.add(investOrderCloneObj);
				}				
			}
		}
		return investApplyOrderList;		
	}

	@Override
	public Long findAllUnclaimedTaskCount() {
		try {			
			Long count = this.taskService.createTaskQuery()
			                .processDefinitionKey(PROCESS_DEFINITION_KEY)   //获取与“投资赎回”相关的Task数据
			                .taskCandidateGroupIn(Constants.getCurrendUser().getRoleCodes())  //根据当前登陆人的拥有的“所有角色”来查找未签收的Task列表。
			                .count();
			return (count == null) ? 0L : count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}
	}

	
	@Override
	public List<InvestOrder> findAllClaimedTask(Integer firstResult,
			Integer maxResults) {
		// 定义返回结果
		List<InvestOrder> investOrderList = new ArrayList<InvestOrder>();

		// 获取当前登陆用户id
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());

		// 获取当前用户所受理的任务。 目的： --> 得到 投资订单ID，investOrderId
		List<Task> tasks = this.taskService.createTaskQuery()
				.processDefinitionKey(PROCESS_DEFINITION_KEY) // 过滤“流程定义ID”为“InvestOrder”的Task
				.taskAssignee(userId) // 过滤“任务办理人”为“userId”的Task
				.listPage(firstResult, maxResults); // 对act_ru_task表执行查询，找到符合条件的Task对象，并且加分页。

		// 目的 ---> 根据Task对象，查找相应的流程实例ID，再找到“业务Key”，最终找到“投资订单的ID”
		// 原因 --->
		// Business_Key字段仅仅被储在Activiti的23张表中的“act_ru_execution表”和“act_hi_procinst表”
		// 中。
		if (Collections.listIsNotEmpty(tasks)) {
			for (Task task : tasks) {
				InvestOrder investOrder = this.findInvestOrderByTaskObj(task);
				if (StringUtils.isNotBlank(investOrder.getInvestOrderId())) {
					InvestOrder investOrderClone = (InvestOrder) investOrder
							.clone();
					investOrderClone.setTaskId(task.getId());
					investOrderList.add(investOrderClone);
				}
			}
		}
		return investOrderList;		
	}	
	
	@Override
	public Long findAllClaimedTaskCount(String userId) {
		try {
			return this.taskService.createTaskQuery()
					.processDefinitionKey(PROCESS_DEFINITION_KEY)
					.taskAssignee(userId).count();
		} catch (Exception e) {
			return 0L;
		}
	}

	@Override
	public List<Users> getIdentityLinkByTaskId(String taskId) {
		List<IdentityLink> identityLinkList = this.taskService.getIdentityLinksForTask(taskId);
		List<Users> userList = new ArrayList<Users>();		
		if(Collections.listIsNotEmpty(identityLinkList)){
			for(IdentityLink identity : identityLinkList){
				//投资业务中，GroupId与投资业务的角色编码是相同的，比如，BuMenZhuLi
				String groupId = identity.getGroupId();  
				if(StringUtils.isNotBlank(groupId)){					
					//根据角色编码，找到具有该角色的用户
					List<Users> tmpUserList = this.userService.findUsersByRoleCode(groupId);
					userList.addAll(tmpUserList);
				}
			}
		}		
		return userList;
	}

	@Override
	public InvestOrder findInvestOrderByTaskObj(Task task) {

		// 根据任务中的流程实例id，找到该实例对象
		ProcessInstance processInstance = this.runtimeService
				.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId()).singleResult();
		String InvestOrderId = "";
		if (processInstance != null) {
			// 获取订单id
			InvestOrderId = processInstance.getBusinessKey().split("\\.")[1];
		}
		return investOrderServiceImple.findByInvestOrderId(InvestOrderId);
	}	

	
	@Override
	public void pickMyTask(String taskId) {

		// 获取当前登录人的id
		String curUserId = String.valueOf(Constants.getCurrendUser()
				.getUserId());
		// 签收任务
		if (StringUtils.isNotBlank(taskId)) {
			this.taskService.claim(taskId, curUserId);
		}
		
		Long allClaimedTaskCount4Self = findAllClaimedTaskCount(curUserId);
		Long allUnClaimedTaskCount4Self = findAllUnclaimedTaskCount();
		// 给投资赎回业务的受理人发送任务增加消息
		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_CLAIM_INVEST_REDEEM_ORDER,
				SendMessageUtil.CALCULATETYPE_ADD, taskId,
				allClaimedTaskCount4Self, allUnClaimedTaskCount4Self),
				curUserId);

		
		// 所有的候选人，发送任务减少消息
		//根据当前用户的角色，查询具有相同角色的其他用户的ID。
		List<Users> userList = this.getIdentityLinkByTaskId(taskId);

		if (Collections.listIsNotEmpty(userList)) {
			for (Users oneUser : userList) {
				// 给代办人发送任务减少消息
				SendMessageUtil.sendMessageByUserId(SendMessageUtil
						.getMessageData(
								SendMessageUtil.DATATYPE_UNCLAIM_INVEST_REDEEM_ORDER,
								SendMessageUtil.CALCULATETYPE_SUBTRACT, taskId,
								findAllClaimedTaskCount(oneUser.getUserId().toString()),
								findAllUnclaimedTaskCount()), oneUser.getUserId().toString());
			}
		}				
	}

	@Override
	public void saveSubmitTask(InvestOrder investOrder, String comment,
			String result, String processingResult) {
		// 获取当前执行任务
		Task task = this.taskService.createTaskQuery()
				.taskId(investOrder.getTaskId()).singleResult();

		// 获取当前登陆用户的信息
		String userId = String.valueOf(Constants.getCurrendUser().getUserId());

		// 获取当前处理人的角色
		Role role = taskService.getVariableLocal(task.getId(), "role",
				Role.class);		
		
		// 根据订单状态的code,获取对应的订单状态id
		String orderStatusId = orderStatusService.getOrderStatusByStatusCode(
				PROCESS_DEFINITION_KEY + "_" + result).getStatusId();
		// 记录订单履历
		InvestOrderHis his = new InvestOrderHis();
		his.setComment(comment);
		his.setAgentTime(new Date());
		his.setAssignee(userId);
		his.setInvestOrderId(investOrder.getInvestOrderId());
		his.setOrderStatus(orderStatusId);
		his.setRoleId(String.valueOf(role.getRoleId()));
		his.setHandleResult(processingResult);
		investOrderHisService.persistenceInvestOrderHis(his);

		// 设置流程变量
		Map<String, Object> variables = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(result) && !result.equals("默认提交")) {
			variables.put("result", result);
		}
		// 修改订单的状态
		this.investOrderServiceImple.updateInvestOrderStauts(
				investOrder.getInvestOrderId(), result);

		// 使用任务ID，完成当前人的个人任务，同时设置流程变量
		taskService.complete(task.getId(), variables);

		// 流程结束了
		ProcessInstance pi = runtimeService.createProcessInstanceQuery()//
				.processInstanceId(task.getProcessInstanceId())// 使用流程实例ID查询
				.singleResult();

		// 判断流程是否结束,如果结束修改流程的状态
		if (pi == null) {
			// 更新请假单表的状态从1变成2（审核中-->审核完成）
			InvestOrder investorder = this.investOrderServiceImple
					.findByInvestOrderId(investOrder.getInvestOrderId());
			investorder.setProcessStatus("2");
		}

		// 给自身发送受理任务减少消息
		SendMessageUtil.sendMessageByUserId(SendMessageUtil.getMessageData(
				SendMessageUtil.DATATYPE_CLAIM_INVEST_REDEEM_ORDER,
				SendMessageUtil.CALCULATETYPE_SUBTRACT, task.getId(),
				findAllClaimedTaskCount(userId), findAllUnclaimedTaskCount()),
				userId);		
	}	
}
