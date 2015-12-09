package com.oasys.serviceImpl.workFlow;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oasys.dao.PublicDao;
import com.oasys.model.ActReDeployment;
import com.oasys.model.ActReProcDef;
import com.oasys.model.StatusNameRef;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowService;
import com.oasys.serviceImpl.JumpTaskCmd;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.viewModel.WorkFlowBean;

@Service("workFlowService")
@Transactional
public class WorkFlowServiceImpl extends WorkFlowBaseServiceImpl implements
		WorkFlowService {
	@Autowired
	private PublicDao<StatusNameRef> baseDao;
	@Autowired
	private UserService userService;

	@Override
	public List<ActReDeployment> findAllProcessDeployment(WorkFlowBean bean) {
		// 定义返回值
		List<ActReDeployment> list = new ArrayList<ActReDeployment>();
		// 获取查询的对象
		DeploymentQuery query = this.repositoryService.createDeploymentQuery();
		// 执行查询
		List<Deployment> depList = findAllProcessDeploymentCondition(bean,
				query).orderByDeploymenTime().desc()
				.listPage(bean.getFirstResult(), bean.getMaxResults());

		// 默认按部署时间倒序排序
		if (Collections.listIsNotEmpty(depList)) {
			for (Deployment dep : depList) {
				ActReDeployment actReDep = new ActReDeployment();
				BeanUtils.copyProperties(dep, actReDep);
				list.add(actReDep);
			}
		}
		return list;
	}

	@Override
	public Long findAllProcessDeploymentCount(WorkFlowBean bean) {
		// 获取查询的对象
		DeploymentQuery query = this.repositoryService.createDeploymentQuery();
		return findAllProcessDeploymentCondition(bean, query).count();
	}

	@Override
	public void deleteProcessDeployment(String deploymentId) {
		this.repositoryService.deleteDeployment(deploymentId, true);
	}

	@Override
	public List<ActReProcDef> findAllProcessDefination(WorkFlowBean bean) {
		// 定义返回值
		List<ActReProcDef> list = new ArrayList<ActReProcDef>();
		// 获取查询对象
		ProcessDefinitionQuery query = this.repositoryService
				.createProcessDefinitionQuery();
		// 执行查询对象
		List<ProcessDefinition> proList = findAllProcessDefinationCondition(
				bean, query).orderByProcessDefinitionKey().asc()
				.orderByProcessDefinitionVersion().desc()
				.listPage(bean.getFirstResult(), bean.getMaxResults());

		// 默认按部署时间倒序排序
		if (Collections.listIsNotEmpty(proList)) {
			for (ProcessDefinition pro : proList) {
				ActReProcDef actRePro = new ActReProcDef();
				BeanUtils.copyProperties(pro, actRePro);
				list.add(actRePro);
			}
		}

		return list;
	}

	@Override
	public Long findAllProcessDefinationCount(WorkFlowBean bean) {
		// 获取查询对象
		ProcessDefinitionQuery query = this.repositoryService
				.createProcessDefinitionQuery();
		// 设置查询参数
		return findAllProcessDefinationCondition(bean, query).count();
	}

	public Deployment saveDeployProcess(byte[] bytes, String fileName)
			throws IOException {
		Deployment deployment = null;
		InputStream fis = null;
		ZipInputStream zis = null;
		try {
			// 将上传的文件流转化为zip输入流
			fis = new ByteArrayInputStream(bytes);
			zis = new ZipInputStream(fis);
			deployment = repositoryService.createDeployment().name(fileName)
					.addZipInputStream(zis).deploy();
			// 更新或保存对应得状态信息
			saveOrUpdateProcessStatus(deployment);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				fis.close();
			if (zis != null)
				zis.close();
		}
		return deployment;
	}

	/**
	 * 更新或修改状态
	 * 
	 * @param deployment
	 *            部署的对象
	 */
	private void saveOrUpdateProcessStatus(Deployment deployment) {
		// 根据部署id获取流程定义的id
		List<ProcessDefinition> pdList = repositoryService
				.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).list();

		// 遍历部署的流程信息
		for (ProcessDefinition pd : pdList) {
			// 根据流程的id获取流程定义的对象
			ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService
					.getProcessDefinition(pd.getId());
			// 定义所有的输出线的信息列表
			List<PvmTransition> list = new ArrayList<PvmTransition>();

			// 根据流程所有的activities获取所有的输出线
			for (ActivityImpl act : pde.getActivities()) {
				list.addAll(act.getOutgoingTransitions());
			}

			// 获取所有的本业务的状态状态列表
			String hql = "from StatusNameRef t where t.appStatusCode LIKE '"
					+ pde.getKey() + "_" + "%'";// 根据流程定义的key获取本业务的所有的状态
			List<StatusNameRef> statusList = this.baseDao.find(hql);
			// 遍历状态线的key与状态的信息的statutscode进行比对,如果存在则进行更新,不存在则进行插入.将已经存在的数据放入existList中,不存在放入notExistList中.
			for (PvmTransition pvm : list) {
				boolean isExist = false;
				// 判断名称是否为空,如果为空,则说明不是订单关注的状态,直接进行跳过.
				if (StringUtils.isBlank((String) pvm.getProperty("name"))) {
					continue;
				}
				// 获取流程对应的key,其实就是bpmn文件流程的id
				String statusCode = pde.getKey() + "_" + pvm.getId();
				for (StatusNameRef status : statusList) {
					// 存在的话进行更新,同时推出当前的循环
					if (status.getAppStatusCode().equals(statusCode)) {
						status.setAppStatusName((String) pvm
								.getProperty("name"));
						status.setRemark((String) pvm.getProperty("name"));
						this.baseDao.update(status);
						isExist = true;
						break;
					}
				}
				// 如果不存在的时候,则进行增加
				if (!isExist) {
					StatusNameRef pstatus = new StatusNameRef();
					pstatus.setAppStatusName((String) pvm.getProperty("name"));
					pstatus.setAppStatusCode(statusCode);
					pstatus.setRemark((String) pvm.getProperty("name"));
					this.baseDao.save(pstatus);
				}
			}
		}
	}

	/**
	 * 设置部署流程查询的query执行条件
	 * 
	 * @param bean
	 *            条件的参数
	 * @param query
	 *            执行的query对象
	 * @return 设置好的query对象
	 */
	private DeploymentQuery findAllProcessDeploymentCondition(
			WorkFlowBean bean, DeploymentQuery query) {
		// 判断条件,如果条件不为空,按指定条件进行查询
		if (StringUtils.isNotBlank(bean.getDeploymentId())) {
			query.deploymentId(bean.getDeploymentId());
		}
		if (StringUtils.isNotBlank(bean.getDeploymentName())) {
			query.deploymentNameLike("%" + bean.getDeploymentName() + "%");
		}
		return query;
	}

	/**
	 * 设置流程定义查询的query执行条件
	 * 
	 * @param bean
	 *            条件的参数
	 * @param query
	 *            执行的query对象
	 * @return 设置好的query对象
	 */
	private ProcessDefinitionQuery findAllProcessDefinationCondition(
			WorkFlowBean bean, ProcessDefinitionQuery query) {
		// 条件查询
		if (StringUtils.isNotBlank(bean.getName())) {
			query.processDefinitionName(bean.getName());
		}
		if (StringUtils.isNotBlank(bean.getKey())) {
			query.processDefinitionKey(bean.getKey());
		}
		if (bean.getVersion() != 0) {
			query.processDefinitionVersion(bean.getVersion());
		}
		if (StringUtils.isNotBlank(bean.getDeploymentId())) {
			query.deploymentId(bean.getDeploymentId());
		}
		return query;
	}

	@Override
	public InputStream findDefinationResourceAsStream(WorkFlowBean bean) {
		return this.repositoryService.getResourceAsStream(
				bean.getDeploymentId(), bean.getResourceName());
	}

	@Override
	public void getDiagramResourceByTaskId(HttpServletResponse response,
			String resourceName, String taskId) throws IOException {
		// 图片流程
		InputStream in = null;
		try {
			// 获取当前执行的任务
			Task task = this.taskService.createTaskQuery().taskId(taskId)
					.singleResult();

			// 获取流程定义的实体对象（对应.bpmn文件中的数据）
			ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService
					.getProcessDefinition(task.getProcessDefinitionId());

			// 根据任务的获取当前执行对象的id,根据执行对象的id获取执行对象的信息
			Execution execution = runtimeService.createExecutionQuery()
					.executionId(task.getExecutionId()).singleResult();

			// 根据当前的执行对象的id获取正在执行的活动信息
			ActivityImpl activityImpl = pd.findActivity(execution
					.getActivityId());

			// 获取图片的流程图片名称
			String resourceName1 = resourceName + ".png";

			// 获取图片的文件的流
			in = this.repositoryService.getResourceAsStream(
					pd.getDeploymentId(), resourceName1);

			// 获取图片对象
			BufferedImage bimg = ImageIO.read(in);

			// 得到Graphics2D 对象
			Graphics2D g2d = (Graphics2D) bimg.getGraphics();
			// 设置颜色和画笔粗细
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(3));

			// 绘制矩形
			Rectangle2D rectangle = new Rectangle2D.Float(activityImpl.getX(),
					activityImpl.getY(), activityImpl.getWidth(),
					activityImpl.getHeight());
			g2d.draw(rectangle);

			// 写入response输出流中

			ImageIO.write(bimg, "png", response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				in.close();
		}

	}

	@Override
	public String findTaskFormKeyByTaskId(String taskId) {
		return taskService.createTaskQuery().taskId(taskId).singleResult()
				.getFormKey();
	}

	@Override
	public List<String> findRunTaskHandlePersons(String taskId) {
		// 候选人列表的ID
		List<String> userIDs = findRunTaskAllHandlePersons(taskId);
		// 判断是否含有自身，如果含有自身，则删除自身
		String currentUserId = String.valueOf(Constants.getCurrendUser()
				.getUserId());
		if (userIDs.contains(currentUserId)) {
			userIDs.remove(currentUserId);
		}
		return userIDs;
	}

	@Override
	public List<String> findRunTaskAllHandlePersons(String taskId) {
		// 获取所有的候选人列表
		List<IdentityLink> identityLinks = taskService
				.getIdentityLinksForTask(taskId);
		// 候选人列表的ID
		Set<String> userIDs = new HashSet<String>();
		// 遍历获取所有候选人的信息列表
		for (IdentityLink identityLink : identityLinks) {
			userIDs.add(identityLink.getUserId());
		}
		return new ArrayList<String>(userIDs);
	}

	/**
	 * *************************************************************************
	 * *************************************************************************
	 * *<br>
	 * ************************************************以下为流程转向操作核心逻辑************
	 * ******************************************************************<br>
	 * *************************************************************************
	 * *************************************************************************
	 * *<br>
	 */

	/**
	 * @param taskId
	 *            当前任务ID
	 * @param variables
	 *            流程变量
	 * @param activityId
	 *            流程转向执行任务节点ID<br>
	 *            此参数为空，默认为提交操作
	 * @throws Exception
	 */
	@Override
	public void commitProcess(String taskId, Map<String, Object> variables,
			String activityId) throws Exception {
		if (variables == null) {
			variables = new HashMap<String, Object>();
		}
		// 跳转节点为空，默认提交操作
		if (StringUtils.isBlank(activityId)) {
			taskService.complete(taskId, variables);
		} else {// 流程转向操作
			turnTransition(taskId, activityId, variables);
		}
	}

	/**
	 * 清空指定活动节点流向
	 * 
	 * @param activityImpl
	 *            活动节点
	 * @return 节点流向集合
	 */
	private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
		// 存储当前节点所有流向临时变量
		List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
		// 获取当前节点所有流向，存储到临时变量，然后清空
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		for (PvmTransition pvmTransition : pvmTransitionList) {
			oriPvmTransitionList.add(pvmTransition);
		}
		pvmTransitionList.clear();

		return oriPvmTransitionList;
	}

	/**
	 * 还原指定活动节点流向
	 * 
	 * @param activityImpl
	 *            活动节点
	 * @param oriPvmTransitionList
	 *            原有节点流向集合
	 */
	private void restoreTransition(ActivityImpl activityImpl,
			List<PvmTransition> oriPvmTransitionList) {
		// 清空现有流向
		List<PvmTransition> pvmTransitionList = activityImpl
				.getOutgoingTransitions();
		pvmTransitionList.clear();
		// 还原以前流向
		for (PvmTransition pvmTransition : oriPvmTransitionList) {
			pvmTransitionList.add(pvmTransition);
		}
	}

	/**
	 * 流程转向操作
	 * 
	 * @param taskId
	 *            当前任务ID
	 * @param activityId
	 *            目标节点任务ID
	 * @param variables
	 *            流程变量
	 * @throws Exception
	 */
	private void turnTransition(String taskId, String activityId,
			Map<String, Object> variables) throws Exception {
		// 当前节点
		ActivityImpl currActivity = findActivitiImpl(taskId, null);
		// 清空当前流向
		List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

		// 创建新流向
		TransitionImpl newTransition = currActivity.createOutgoingTransition();
		// 目标节点
		ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
		// 设置新流向的目标节点
		newTransition.setDestination(pointActivity);

		// 执行转向任务
		taskService.complete(taskId, variables);
		// 删除目标节点新流入
		pointActivity.getIncomingTransitions().remove(newTransition);

		// 还原以前流向
		restoreTransition(currActivity, oriPvmTransitionList);
	}

	/**
	 * *************************************************************************
	 * *************************************************************************
	 * *<br>
	 * ************************************************以上为流程转向操作核心逻辑************
	 * ******************************************************************<br>
	 * *************************************************************************
	 * *************************************************************************
	 * *<br>
	 */

	/**
	 * 根据任务ID获得任务实例
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private TaskEntity findTaskById(String taskId) throws Exception {
		TaskEntity task = (TaskEntity) taskService.createTaskQuery()
				.taskId(taskId).singleResult();
		if (task == null) {
			throw new Exception("任务实例未找到!");
		}
		return task;
	}

	/**
	 * 根据任务ID获取流程定义
	 * 
	 * @param taskId
	 *            任务ID
	 * @return
	 * @throws Exception
	 */
	private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
			String taskId) throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(findTaskById(taskId)
						.getProcessDefinitionId());

		if (processDefinition == null) {
			throw new Exception("流程定义未找到!");
		}

		return processDefinition;
	}

	/**
	 * 根据任务ID和节点ID获取活动节点 <br>
	 * 
	 * @param taskId
	 *            任务ID
	 * @param activityId
	 *            活动节点ID <br>
	 *            如果为null或""，则默认查询当前活动节点 <br>
	 *            如果为"end"，则查询结束节点 <br>
	 * 
	 * @return
	 * @throws Exception
	 */
	private ActivityImpl findActivitiImpl(String taskId, String activityId)
			throws Exception {
		// 取得流程定义
		ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

		// 获取当前活动节点ID
		if (StringUtils.isBlank(activityId)) {
			activityId = findTaskById(taskId).getTaskDefinitionKey();
		}

		// 根据流程定义，获取该流程实例的结束节点
		if (activityId.toUpperCase().equals("END")) {
			for (ActivityImpl activityImpl : processDefinition.getActivities()) {
				List<PvmTransition> pvmTransitionList = activityImpl
						.getOutgoingTransitions();
				if (pvmTransitionList.isEmpty()) {
					return activityImpl;
				}
			}
		}

		// 根据节点ID，获取对应的活动节点
		ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
				.findActivity(activityId);

		return activityImpl;
	}
	
	@Override
	public String getTaskDefKeyByTaskID(String taskID) {
		String sql = "SELECT TASK_DEF_KEY FROM "+managementService.getTableName(TaskEntity.class)+" WHERE ID_="+taskID;
		@SuppressWarnings("unchecked")
		List<Object> list = baseDao.findBySQL(sql);
		if(null != list && list.size() > 0){
			return list.get(0).toString();
		}
		return "";
	}


	@Override
	public String getTaskIDByExecutionID(String executionID) {
		String sql = "SELECT TASK_DEF_KEY FROM "+managementService.getTableName(TaskEntity.class)+" WHERE EXECUTION_ID_="+executionID;
		@SuppressWarnings("unchecked")
		List<Object> list = baseDao.findBySQL(sql);
		if(null != list && list.size() > 0){
			return list.get(0).toString();
		}
		return "";
	}
	
	@Override
	public void commitProcessByExecutionId(String executionId, String activityId) throws Exception {
		// TODO Auto-generated method stub
		TaskServiceImpl taskServiceImpl=(TaskServiceImpl)taskService;  
		taskServiceImpl.getCommandExecutor().execute(new JumpTaskCmd(executionId, activityId));
	}
	
}
