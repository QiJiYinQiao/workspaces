package com.oasys.serviceImpl.workFlow;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oasys.dao.PublicDao;
import com.oasys.model.AuditProcHis;
import com.oasys.model.Organization;
import com.oasys.model.Role;
import com.oasys.model.StatusNameRef;
import com.oasys.model.UserRole;
import com.oasys.model.Users;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.TaskRoleService;
import com.oasys.service.WorkFlowService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ProcessNameModel;
import com.oasys.viewModel.UserRoleModel;
import com.oasys.viewModel.WorkFlowTasksModel;


/**
 * @Title WorkFlowTaskServiceImpl
 * @Package com.oasys.serviceImpl;
 * @author lida
 * @Date 2015/9/23
 * @remark 任务管理Service实现类
 * 
 * */
@Service("workFlowTaskService")
public class WorkFlowTaskServiceImpl extends WorkFlowBaseServiceImpl implements WorkFlowTaskService {

	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private PublicDao<Task> publicDao;
	
	@Autowired
	private PublicDao<AuditProcHis> publicDaoHis;
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private StatusNameRefService statusNameService;

	@Autowired
	private AuditProcHisService auditProcHisService;
	
	@Autowired 
	private TaskRoleService taskRoleService;
	
	
	
	//构建任务查询Query对象
	@Override
	public List<WorkFlowTasksModel> findAcceptTaskByGroup(
			WorkFlowTasksModel taskModelQuery) {
		return getTaskModelList(getNativeQuery(taskModelQuery));
	}
	
	
	//签收任务
	@Override
	public void signForTask(String taskID) throws ActivitiTaskAlreadyClaimedException{
			taskService.claim(taskID, Constants.getCurrendUser().getUserId().toString());
	}
	
	
	//待办任务类型下拉框数据查询
	@Override
	public List<ProcessNameModel> processNameList(){
		//获取taskList
		List<Task> taskList = getNativeQuery(new WorkFlowTasksModel()).list();
		List<ProcessNameModel> procNameList = new ArrayList<ProcessNameModel>();
		ProcessNameModel procNameModel = new ProcessNameModel();
		Map<String,Integer> taskCountMap = new HashMap<String, Integer>();//计算流程图中的task任务数量
		String keyStr = "";
		String listUrl = "";
		Integer i =0;
		for (Task task : taskList) {
			ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
			procNameModel = new ProcessNameModel();
			procNameModel.setProcessKey(pi.getProcessDefinitionKey());
			procNameModel.setProcessName(pi.getProcessDefinitionName());
			if(Constants.getTaskLocationMapBatch().get(getRoleIdList()) ==null)
				listUrl = Constants.getTaskLocationMap().get(procNameModel.getProcessKey());
			else 
				listUrl = Constants.getTaskLocationMap().get(procNameModel.getProcessKey()).replace(".jsp", "Batch.jsp");
			procNameModel.setListURL(listUrl);
			//计算流程图中的task任务数量
			if(taskCountMap.containsKey(pi.getProcessDefinitionKey())){
				i++;
			}else{
				i = 1;
			}
			taskCountMap.put(pi.getProcessDefinitionKey(), i);
			/**   排重  **/
			if(keyStr.indexOf((",").concat(pi.getProcessDefinitionKey()).concat(",")) == -1){
				procNameList.add(procNameModel);
			}
			keyStr = keyStr.concat(",".concat(pi.getProcessDefinitionKey()).concat(","));
		}
		//计算每个流程中的任务数量
		if(Collections.listIsNotEmpty(procNameList)){
			for (ProcessNameModel processNameModel : procNameList) {
				processNameModel.setTaskCount(taskCountMap.get(processNameModel.getProcessKey()));
			}
		}
        return procNameList;
	}

	//根据业务ID返回taskID方法
	@SuppressWarnings("unchecked")
	@Override
	public String getTaskIDByBusinessKey(String businessKey, String definitionKey) {
		String sql = "SELECT task.ID_ FROM ".concat(managementService.getTableName(TaskEntity.class)).concat(" task LEFT JOIN ")
							.concat(managementService.getTableName(ExecutionEntity.class)).concat(" exe ")
							.concat(" ON task.EXECUTION_ID_ = exe.ID_ WHERE exe.business_key_ = '").concat(definitionKey).concat(".").concat(businessKey).concat("' ");
		List<Object> listObj = publicDao.findBySQL(sql);
		if(Collections.listIsNotEmpty(listObj)){
			return listObj.get(0).toString();
		}
		return "";
	}
	
	
	//根据业务ID返回task对象方法
	@Override
	public Task getTaskByBusinessKey(String businessKey, String definitionKey) {
		return taskService.createTaskQuery().taskId(getTaskIDByBusinessKey(businessKey,definitionKey)).singleResult();
	}
	
	
	/**
	 * 根据userID取出 对应的财富端或借款端角色 如果为空则调用默认角色
	 * @param userID 申请人ID
	 * @param 默认角色code
	 * @return 角色code
	 * */
	@Override
	public String getStartProcRoleCode(String userID,String defaultCode){
		Organization org = orgService.findOrganizationByUserId(Integer.valueOf(userID));
		String roleCode = "";
		if(Constants.ORG_MYID_CF.equals(org.getMyid())){
			roleCode =  Constants.AD_ROLE_CODE_BUSINESSMGR;//判断如果为财富端 调用营业部经理角色
		}else if(Constants.ORG_MYID_JK.equals(org.getMyid())){
			roleCode =  Constants.AD_ROLE_CODE_CITYMANAGER;//判断如果为借款端 调用城市经理角色	
		}else{
			roleCode =  defaultCode;//默认调用
		}
		return roleCode;
	}
	
	/**
	 * 根据UserID获取当前配置的角色Code 
	 * @param 用户ID
	 * @return 角色编码
	 * */
	@Override
	public String getUserRoleCodeByID(String userID){
		Users user = userService.getUserByID(Integer.valueOf(userID));
		Set<UserRole> userRoleList = user.getUserRoles();
		for (UserRole userRole : userRoleList) {
			return userRole.getRole().getRoleCode();
		}
		return "";
	}

	/**
	 * 从当前task节点跳转到下一task节点 跳转条件为 
	 * 根据task中 formKey的type选择判断不同的通过Task条件(如果不设置type 默认将result设置为1[通过]) 例:type=0为财富端Or借款端判断 type=1为角色以上或角色以下判断)
	 * @param businessKey 业务ID
	 * @param definitionKey 流程节点ID
	 * @param curUserID 申请人ID
	 * */
	@Override
	public void JumpTaskByByFormKeyType(WorkFlowTasksModel taskModel,String curUserID) {
		Map<String,Object> variables = new HashMap<String,Object>();
		Task task = getTaskByBusinessKey(taskModel.getBusinessID(),taskModel.getBusinessDefineKey());
		if(StringUtils.isNotBlank(task.getFormKey()) && task.getFormKey().contains("?")){
			taskModel.setTaskID(task.getId());
			variables.put("result",getResultByFormKey(taskModel,curUserID));
		}else{
			variables.put("result", Constants.TASK_COMMIT_RESULT);//设置流程变量 默认通过到下一节点
		}
		taskService.complete(task.getId(), variables);// 向task发送消息跳转下一流程
	}

	/**
	 * 根据formKey 判断类型 type=0为财富端Or借款端判断 type=1为角色以上或角色以下判断 type=4为定制条件 
	 * @param formKey 流程图中节点配置的formKey
	 * @param curUserID 申请人ID
	 * @param 默认通过的表达式值
	 * @return 计算出的result表达式值
	 * */
	@Override
	public String getResultByFormKey(WorkFlowTasksModel taskModel,String curUserID){
//		String formKeyJson = taskModel.getFormKey().substring(taskModel.getFormKey().indexOf("?")+1);
		JSONObject jsonObj = JSON.parseObject(taskModel.getFormKeyJson(),JSONObject.class);//获取formKeyJson字符并进行转换
		String resultCode = "";//发送的流程变量
		if(null != jsonObj.getString("type")){
			String type = jsonObj.get("type").toString();//获取type类型 0为判断财富端或借款端 1为比对角色信息 3判断申请人是否为行政人员(分公司职能端离职判断申请人是否为行政部门)
			switch (type) {
				//判断财富端或借款端
				case "0":
					String myId = orgService.findOrganizationByUserId(Integer.valueOf(curUserID)).getMyid();//获取申请人对应的 财富端或借款端标识
					//将财富或借款端的标识作为Key 在jsonObj中查找对应的value作为跳转下一节点的条件
					resultCode = jsonObj.getString(myId);
					break;
				/**进行角色比对 比对规则 从formKey中获取要进行比对的角色对象 
				 * 然后根据该角色在数据库中查找子集 如果不存在子集或子集记录中没有要对比的角色
				 * 则说名该角色比要对比的角色大或者为同级 则按照 该角色以下职级的方式继续流程*/	
				case "1":
					List<UserRoleModel> userRoleList =  userService.findUsersRolesList(Integer.valueOf(curUserID));
					String result2 = jsonObj.getString("result2");//判断为角色以下时 设置的resultCode
					String result3 = jsonObj.getString("result3");//要进行比对的角色
					if(null != userRoleList && userRoleList.size() > 0){
						List<Role> roleList = roleService.findRoleListsByRoleCode(userRoleList.get(0).getRoleId());//获取该角色的子集
						for (Role role : roleList) {
							//如果该角色的子集中 存在要比对的角色 说明该角色比进行比对的角色权限大 所以执行该角色以上职级流程
							if(null != result3 && result3.equals(role.getRoleCode())){
								resultCode = result2;
								break;
							}
						}
					}
					//匹配不到或没有子集 则按照 该角色以上职级方式处理流程
					if(StringUtils.isBlank(resultCode)){
						resultCode = jsonObj.getString("result1");
					}
					break;
				/*判断申请人是否为行政人员(分公司职能端离职判断申请人是否为行政部门)*/
				case "3":
					List<Role> roleList = userService.findRoleListByUserId(Integer.valueOf(curUserID));
					if (Collections.listIsNotEmpty(roleList)) {
						//目前只考虑一个用户只有一个角色
						Role role = roleList.get(0);
						if (role.getRoleCode().startsWith(jsonObj.getString("result3"))) {
							resultCode = jsonObj.getString("result2");
						}else{
							resultCode = jsonObj.getString("result1");
						}
					}
					break;
				/* 定制条件  */	
				case "4":
					resultCode = (null == taskService.getVariable(
											taskModel.getTaskID(), Constants.CONDITIONS_KEY) ? Constants.TASK_COMMIT_RESULT
											: taskService.getVariable(taskModel.getTaskID(),
													Constants.CONDITIONS_KEY).toString()); 
					break;
				//默认设置result传递进来的值
				default:
					resultCode = Constants.TASK_COMMIT_RESULT;
			}
		}else{
			resultCode = Constants.TASK_COMMIT_RESULT;
		}
		return resultCode;
	}

	/**
	 * 根据业务表ID与流程示例ID 构造消息提醒字符串
	 * @param task 流程节点对象
	 * @param businessID 业务ID
	 * @param applyForAdjustment 申请调整标识
	 * @return 根据流程节点构造的消息提醒字符
	 * */
	@Override
	public String getTaskResultStr(Task task,String businessID,String applyForAdjustment){
		/* 消息提醒 获取task中最新的流程状态 告知前台界面 */
		String resultStr = "";
		if(null == task){
			resultStr = "任务已结束"; 
		}else{
			if(task.getTaskDefinitionKey().contains(applyForAdjustment)){
				String submitUserID = this.taskService.getVariable(task.getId(), Constants.CURRENT_USER_KEY) == null ? 
						"" : this.taskService.getVariable(task.getId(), Constants.CURRENT_USER_KEY).toString();//获取当前任务申请人ID
				resultStr = "已将任务提交回申请人处理 申请人登录账号为:".concat(userService.getUserByID(Integer.valueOf(submitUserID)).getAccount());
			}else{
				resultStr = "已将任务提交到【".concat(task.getName()).concat("】流程节点进行审核");
			}
		}
		return resultStr;
	}
	
	

	/**
	 * 开启流程 通用方法 需构造taskModel对象 需填充BusinessID与BusinessDefineKey属性
	 * @param taskModel 工作流需要用到的model实体
	 * @return 消息构造字符及流程图中通过的流程线的数据库记录ID StatusNameRef表中的ref_id
	 */
	@Override
	public Map<String,Object> startProcessInstance(WorkFlowTasksModel taskModel){
		Map<String, Object> variables = taskModel.getVariables();
		String curUserID = String.valueOf(Constants.getCurrendUser().getUserId());
		variables.put(Constants.CURRENT_USER_KEY, curUserID);//申请人ID
		variables.put(Constants.CURRENT_BUSINESS_ID, taskModel.getBusinessID());//业务ID
		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(
				taskModel.getBusinessDefineKey(), taskModel.getBusinessDefineKey().concat(".").concat(taskModel.getBusinessID()), variables);// 启动流程实例
		if(null != pi){
			jumpSubmitTaskFunc(pi.getId(),curUserID, taskModel);
		}
		return taskParentJumpFunc(taskModel);//调用判断当前节点是否有人员配置进行处理 没有则进行跳转操作
	}
	
	/***
	 * @title saveSubmitTaskBatch
	 * @time 2015年12月3日 09:55:39
	 * @author lid
	 * @param taskModel
	 * @return List<Map<String,Object>> 消息提示字符与流程图中流程状态的refID 
	 */
	public List<Map<String,Object>> saveSubmitTaskBatch(WorkFlowTasksModel taskModel){
		List<Map<String,Object>> listMap = new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(taskModel.getAppNo()) && 
				StringUtils.isNotBlank(taskModel.getBusinessID()) && 
				StringUtils.isNotBlank(taskModel.getFormKey()) && 
				StringUtils.isNotBlank(taskModel.getTaskID())){
			String[] appNos = taskModel.getAppNo().split(",");
			String[] businessIDs = taskModel.getBusinessID().split(",");
			String[] formKeys = taskModel.getBusinessID().split(",");
			String[] taskIDs = taskModel.getTaskID().split(",");
			Map<String,Object> varMap = new HashMap<String, Object>();
			if((appNos.length+businessIDs.length+formKeys.length+taskIDs.length) % 4 == 0){
				for(int i = 0; i < appNos.length; i++){
					taskModel = new WorkFlowTasksModel();
					varMap = new HashMap<String, Object>();
					taskModel.setAppNo(appNos[i]);
					taskModel.setBusinessID(businessIDs[i]);
					taskModel.setFormKey(formKeys[i]);
					taskModel.setTaskID(taskIDs[i]);
					taskModel.setResult(Constants.TASK_COMMIT_RESULT);
					taskModel.setIsSuccess(Constants.TASK_COMMIT_RESULT);
					try {
						varMap = saveSubmitTask(taskModel);
						varMap.put(Constants.BUSINESS_ID, businessIDs[i]);
						listMap.add(varMap);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}
		return listMap;
	}
	
	/**
	 * 受理任务 通用方法 需构造taskModel对象 详细字段见model中备注的注释
	 * @param taskModel 必须填充的字段见实体对象描述
	 * @return resultMap 
	 * */
	@Override
	public Map<String,Object> saveSubmitTask(WorkFlowTasksModel taskModel){
		Task task = this.taskService.createTaskQuery().taskId(taskModel.getTaskID()).singleResult();// 获取当前执行任务
		Map<String, Object> variables = taskModel.getVariables();
		boolean isCommitTask = true;
		//防止task空指针异常
		if(null != task){
			String submitUserID = this.taskService.getVariable(task.getId(), Constants.CURRENT_USER_KEY) == null ? 
					"" : this.taskService.getVariable(task.getId(), Constants.CURRENT_USER_KEY).toString();//获取当前任务申请人ID
			taskModel.setBusinessDefineKey(task.getProcessDefinitionId().split(":")[0]);//从task对象中获取流程实例标识 赋值到实体对象属性中
			//判断只有当审批通过时(result==1)才调用计算result表达式方法 
			if(StringUtils.isNotBlank(taskModel.getResult()) && StringUtils.isNotBlank(taskModel.getFormKeyJson()) 
					&& Constants.TASK_COMMIT_RESULT.equals(taskModel.getResult())){
				taskModel.setTaskID(task.getId());
				taskModel.setResult(getResultByFormKey(taskModel,submitUserID));//根据formKey中的json数据判断取出跳转下一流程的result表达式值
			}
			//判断是否为节点通过直接结束流程
			if(Constants.TASK_COMMIT_RESULT.equals(taskModel.getResult()) && Constants.getTaskResultMap().containsKey(taskModel.getBusinessDefineKey())){
				taskModel.setResult(Constants.getTaskResultMap().get(taskModel.getBusinessDefineKey()));
			}
			variables.put("result", taskModel.getResult());// 设置流程变量
			//申请调整重新提交时	重提到申请人对应的角色的上一级部门领导节点 和开启流程时调用同样方式
			if(StringUtils.isNotBlank(taskModel.getFormKeyJson())){
				String curUserID = String.valueOf(Constants.getCurrendUser().getUserId());
				//type==2为申请调整 并且不能为撤销申请 result==0
				if(task.getTaskDefinitionKey().startsWith(taskModel.getApplyStr()) 
						&& !taskModel.getResult().equals(Constants.TASK_REJECT_RESULT)==true && StringUtils.isNotBlank(getUserRoleCodeByID(curUserID))){
					//与开启流程跳转相同节点 获取返回值 如果跳转不成功 则返回true 继续执行流程中的通过线操作
					isCommitTask = jumpSubmitTaskFunc(task.getExecutionId(),curUserID, taskModel);
				}
			}
			//如果已经执行过跳转task 则不需要执行该语句块
			if(isCommitTask){
				taskService.complete(taskModel.getTaskID(), variables);// 向task发送消息 跳转下一流程
			}
		}
		StatusNameRef statusNameRef = saveProHis(taskModel, task.getTaskDefinitionKey());//记录历史
		//监测受理后到达的节点是否有人员配置 如果没有 则跳转下一节点 并且系统自动记录跳转历史记录
		variables = taskParentJumpFunc(taskModel);
		//如果系统执行自动跳转 则返回statusRefID为自动跳转的执行操作线的ID
		if(null == variables.get(Constants.STATUS_REF_ID)){
			variables.put(Constants.STATUS_REF_ID, statusNameRef == null ? null : statusNameRef.getRefId());
		}
		return variables;
	}
	
	
	
	/**
	 * 
	 * 根据taskModel中设置的属性 对task参数进行记录历史操作
	 * @param taskModel 工作流实体对象
	 * @param taskDefKey 角色/流程节点标识
	 * @return 历史审批意见对象
	 * */
	@Override
	public StatusNameRef saveProHis(WorkFlowTasksModel taskModel, String taskDefKey) {
		//拼接字符串取出流程图中线的名字(下方写入历史表中取值提供数据)
		StatusNameRef statusNameRef = statusNameService
				.getstatusNameRefByStatusCode(taskModel.getBusinessDefineKey().concat("_").concat(taskDefKey).concat(taskModel.getResult()));
		if(StringUtils.isNotBlank(taskModel.getIsSuccess())){
			/*** 保存审批流程履历表 start ****/
			AuditProcHis auditProcHis = new AuditProcHis();
			auditProcHis.setAppNo(taskModel.getAppNo());//appNo标识
			if(null != statusNameRef)
				auditProcHis.setAppStatus(statusNameRef.getRefId());//流程图中线对应statusNameRef表中的ref_id
			auditProcHis.setHandleDate(new Date());//系统时间
			auditProcHis.setHandleResult(taskModel.getIsSuccess());//操作标志 1通过 0驳回 2拒绝 3作废 4其他
			//判断如果是其他类型
			if(!"4".equals(taskModel.getIsSuccess())){
				auditProcHis.setHandler(Constants.getCurrendUser().getUserId());//操作人
				//根据流程图中节点ID到Map中取对应的角色code
				auditProcHis.setHandlerRole(getRoleIdList().split(",")[0]);
			}
			auditProcHis.setRemark(taskModel.getTaskComment());//备注
			auditProcHisService.saveAuditProcHis(auditProcHis);
			/*** 保存审批流程履历表 end ****/
		}
		return statusNameRef;
	}
	
	
	@Override
	public Object[] getHisList(String preAppNo,PageUtil pageUtil) {
		String hql="from AuditProcHis t where 1=1 and t.handler = ".concat(Constants.getCurrendUser().getUserId().toString());
		if(StringUtils.isNotBlank(preAppNo)){
			hql = hql.concat(" and t.appNo like '%").concat(preAppNo).concat("%'");
		}
		hql+=" order by t.appNo";
		List<AuditProcHis> hisList = publicDaoHis.find(hql);
		Long count = publicDaoHis.count("select count(*) ".concat(hql));
		Object[] obj =new Object[2];
		obj[0]=hisList;
		obj[1]=count;
		return obj;
	}
	
	
	
	/**------------------以下方法为方法内部调用方法 不对外开放 protected**/
	
	
	/**
	 * 获取处理该节点的角色下是否存在用户 不存在 则跳转下一流程节点 如还不存在则继续跳转 以此类推
	 * */
	protected Map<String,Object> taskParentJumpFunc(WorkFlowTasksModel taskModel){
		Task newTask = this.taskService.createTaskQuery().taskId(
				getTaskIDByBusinessKey(taskModel.getBusinessID(),taskModel.getBusinessDefineKey())).singleResult();//获取task对象
		Map<String,Object> variables = new HashMap<String, Object>();
		StatusNameRef statusNameRef = new StatusNameRef();//历史记录对象
		if(null != newTask){
			Object rcnResult = this.taskService.getVariables(newTask.getId()).get(Constants.RCN_RESULT);//获取流程跳转变量
			//流程变量为空 标识已找到受理该节点的用户 不需跳转下一节点
			while(rcnResult != null){
				String oldTaskName = newTask.getName();//跳转前节点名称
				String oldTaskDefKey = newTask.getTaskDefinitionKey();//跳转前节点ID
				variables.put("result", rcnResult.toString());//设置流程变量
				this.taskService.complete(newTask.getId(), variables);//执行跳转下一节点方法
				newTask = this.taskService.createTaskQuery().taskId(
						getTaskIDByBusinessKey(taskModel.getBusinessID(),taskModel.getBusinessDefineKey())).singleResult();//更新task对象 判断是否角色下还是没有人员配置
				taskModel.setIsSuccess("4");//通过类型 其他
				taskModel.setResult(rcnResult.toString());//通过表达式
				//判断newTask如果为空 则证明任务已结束
				if(null == newTask){
					taskModel.setTaskComment("系统自动处理 因没有找到匹配[".concat(oldTaskName).concat("]角色的用户 且已到达最后审批节点 该任务已结束"));
					statusNameRef = saveProHis(taskModel, oldTaskDefKey);//记录历史
					break;
				}else{
					taskModel.setTaskComment("系统自动处理 因没有找到匹配[".concat(oldTaskName).concat("]角色的用户 所以为您自动跳转到[").concat(newTask.getName()).concat("]节点"));
					rcnResult = this.taskService.getVariables(newTask.getId()).get(Constants.RCN_RESULT);//获取跳转后task对应的通过标识
					statusNameRef = saveProHis(taskModel, oldTaskDefKey);//记录历史
				}
			}
		}
		variables.put(Constants.STATUS_REF_ID,statusNameRef==null?"":statusNameRef.getRefId());//流程状态ID
		variables.put(Constants.RESULT_STR, getTaskResultStr(newTask,taskModel.getBusinessID(),taskModel.getApplyStr()));/* 消息提醒 获取task中最新的流程状态 告知前台界面 */
		return variables;
	}
	
	
	//获取NativeQuery对象方法
	protected NativeTaskQuery getNativeQuery(WorkFlowTasksModel taskModelQuery){
		String groupIDS = "",orgGroup = "", curUserID = Constants.getCurrendUser().getUserId().toString(), roleIdListStr = getRoleIdList();//变量定义
		String sql = "SELECT DISTINCT TASK.* FROM (SELECT T.* FROM ".concat(managementService.getTableName(TaskEntity.class)).concat(" T,")
				.concat(managementService.getTableName(IdentityLinkEntity.class)).concat(" I WHERE T.ID_ = I.TASK_ID_ ")
				.concat("AND (I.GROUP_ID_ in (#{groupID}) OR T.ASSIGNEE_=#{assignee} OR I.USER_ID_ =#{userId} )")
				.concat("UNION ALL SELECT T.* FROM ").concat(managementService.getTableName(TaskEntity.class)).concat(" T WHERE T.ASSIGNEE_=#{assignee}) TASK");
		if(StringUtils.isNotBlank(taskModelQuery.getProcessKey())){
			sql += " WHERE TASK.PROC_DEF_ID_ LIKE '".concat(taskModelQuery.getProcessKey()).concat(":%'");
		}
		sql +=" ORDER BY TASK.CREATE_TIME_ ";
		//循环登录用户所配置的所有角色code进行拼接 
		if(null != roleIdListStr && roleIdListStr.indexOf(",") != -1){
			for (String groupCode : roleIdListStr.split(",")) {
				orgGroup = taskRoleService.getOrgIdRoleByDefKeyByUser(curUserID,groupCode);
				if(StringUtils.isNotBlank(orgGroup)){
					groupIDS += orgGroup.concat(",");
				}
			}
			groupIDS = groupIDS.length() > 0 ? groupIDS.substring(0,groupIDS.length()-1) : groupIDS;//去逗号
		}else{
			groupIDS = taskRoleService.getOrgIdRoleByDefKeyByUser(curUserID,roleIdListStr);
		}
		NativeTaskQuery nativeQuery = taskService.createNativeTaskQuery().sql(sql)
						.parameter("assignee", Constants.getCurrendUser().getUserId().toString())//受理人条件
						.parameter("groupID", groupIDS)//候选组条件
						.parameter("userId", Constants.getCurrendUser().getUserId().toString());//候选人条件
		return nativeQuery;
	}

	protected String getRoleIdList(){
		//获取当前登陆用户
		Users users = userService.getUserByID(Constants.getCurrendUser().getUserId());
		String roleStr = "";
		//重新填充角色List
		Set<UserRole> userRoleSet = users.getUserRoles();
		for (UserRole userRole : userRoleSet) {
			if("A".equals(userRole.getStatus())){
				roleStr += userRole.getRole().getRoleCode().concat(",");
			}
		}
		if(roleStr.length() > 0){
			roleStr = roleStr.substring(0, roleStr.length()-1);
		}
		return roleStr;
	}
	

	
	//获取流程任务实例List方法
	protected List<WorkFlowTasksModel> getTaskModelList(NativeTaskQuery nq){
		List<Task> taskList = nq.list();
		List<WorkFlowTasksModel> taskModelList = new ArrayList<WorkFlowTasksModel>();
		WorkFlowTasksModel taskModel;
		Integer userID = null;
		for (Task task : taskList) {
			try {
				taskModel = new WorkFlowTasksModel();
				taskModel.setTaskID(task.getId());
				ProcessInstance pi = runtimeService.createProcessInstanceQuery()
						.processInstanceId(task.getProcessInstanceId()).singleResult();
				taskModel.setProcessKey(pi.getProcessDefinitionKey());//获取ProcessDefinitionKey用于区分流程    (yuanzhongqiu)
				taskModel.setProcessName(pi.getProcessDefinitionName());//流程名称
				taskModel.setCreateDate(task.getCreateTime());//任务提交时间
				taskModel.setBusinessID(pi.getBusinessKey().split("\\.")[1]);//对应的业务表ID
				taskModel.setAssistant(task.getAssignee());
				Execution ex = this.runtimeService.createExecutionQuery().processInstanceBusinessKey(pi.getBusinessKey()).singleResult();
				Object variable = this.runtimeService.getVariable(ex.getId(),Constants.CURRENT_USER_KEY);
				userID = Integer.valueOf((String) variable);
				Users user = userService.getUserByID(userID);//获取申请任务人信息
				taskModel.setUserName(user.getName());//申请任务用户名称
				taskModel.setFormKey(task.getFormKey());//受理任务jsp地址
				taskModelList.add(taskModel);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
		return taskModelList;
	}
	
	/***
	 * 开启流程与受理任务调用通用方法 实现自由流跳转及多节点相同时 智能跳转就近流程节点 
	 * @param executionID 流程实例ID
	 * @param activityID 流程节点ID
	 * @param curUserID 申请人ID
	 * @param taskModel 工作流实体对象
	 * @return 如果跳转不成功 则返回 true 在调用方法中判断 顺序执行到下一流程节点 跳转成功则返回false 控制不再执行跳转流程方法
	 */
	protected boolean jumpSubmitTaskFunc(String executionID,String curUserID,WorkFlowTasksModel taskModel) {
		String activityID = getUserRoleCodeByID(curUserID);
		boolean isCommit = false;
		try {
			workFlowService.commitProcessByExecutionId(executionID,activityID);//跳转到角色对应的Task节点
			JumpTaskByByFormKeyType(taskModel,curUserID);//系统自动提交到该节点的上一级角色进行审批
		} catch (Exception e) {
			//跳转失败后 对角色code进行拼接_1再次进行跳转(考虑同一角色在流程图内出现多次)
			try {
				workFlowService.commitProcessByExecutionId(executionID,activityID.concat(".1"));//跳转到角色对应的Task节点
				JumpTaskByByFormKeyType(taskModel,curUserID);//系统自动提交到该节点的上一级角色进行审批
			} catch (Exception e2) {
				isCommit = true;
			}
			isCommit = true;
		}
		return isCommit;
	}
}
