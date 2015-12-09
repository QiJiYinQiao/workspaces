package com.oasys.serviceImpl;

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
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.AuditProcHis;
import com.oasys.model.Organization;
import com.oasys.model.OvertimeApp;
import com.oasys.model.Role;
import com.oasys.model.StatusNameRef;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.OvertimeAppService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.OvertimeAppModel;
import com.oasys.viewModel.WorkFlowTasksModel;

@Service("overtimeAppService")
public class OvertimeServiceImpl extends WorkFlowBaseServiceImpl implements OvertimeAppService {
	@Autowired
	private PublicDao<OvertimeAppModel> publicDaoModel;
	@Autowired
	private PublicDao<OvertimeApp> publicDao;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private StatusNameRefService statusNameRefService;
	@Autowired
	private AuditProcHisService auditProcHisService;
	@Autowired
	private OrganizationService organizationService;
	
	@Override
	public List<OvertimeAppModel> getList(Map<String, Object> map,
			PageUtil pageUtil,Integer id) {
		StringBuffer stringBuffer = new StringBuffer("SELECT u.USER_NAME '姓名',ovt.POSITION '职位',o.FULL_NAME as '部门名称',ovt.APP_DATE '申请时间',ovt.PLAN_BG_DTIME '计划开始日期时间',");
		stringBuffer.append("ovt.PLAN_ED_DTIME '计划结束日期时间',ovt.PLAN_OT_CNT '计划加班合计',ovt.REAL_BG_DTIME '实际开始日期时间',ovt.REAL_ED_DTIME '实际结束日期时间',");
		stringBuffer.append("ovt.REAL_OT_CNT '实际加班合计',ovt.PROC_STATUS '流程状态',ovt.REMARK '备注信息',ovt.APP_NO '申请编号',ovt.OVE_ID '编号',u.USER_ID '用户编号',o.ORGANIZATION_ID '部门编号'");
		stringBuffer.append(" FROM t_oa_pd_overtime_app ovt");
		stringBuffer.append(" LEFT JOIN qqms.t_users u ON u.USER_ID = ovt.APPLICANT_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = ovt.DEPT_NO WHERE 1=1");
		if(StringUtils.isNotBlank(map.get("status")+"") && map.get("status")!=null){
			stringBuffer.append(" AND ovt.PROC_STATUS='"+map.get("status")+"'");
		}
		if(StringUtils.isNotBlank(map.get("dateBegin")+"") && map.get("dateBegin")!=null){
			stringBuffer.append(" AND ovt.REAL_BG_DTIME>='"+map.get("dateBegin")+"'");
		}
		if(StringUtils.isNotBlank(map.get("dateEnd")+"") && map.get("dateEnd")!=null){
			stringBuffer.append(" AND ovt.REAL_ED_DTIME<='"+map.get("dateEnd")+"'");
		}
		if(1==id){
			stringBuffer.append(" AND ovt.APPLICANT_NO="+Constants.getCurrendUser().getUserId());
		}
		stringBuffer.append(" ORDER BY ovt.APP_DATE DESC");
		List<Object> list=publicDao.findBySql(stringBuffer.toString(),pageUtil);
		List<OvertimeAppModel> list2 = new ArrayList<OvertimeAppModel>();
		for (int i=0;i<list.size();i++) {
			Object[] item=(Object[]) list.get(i);
			OvertimeAppModel ovt = new OvertimeAppModel();
			ovt.setApplicantNo(item[0]+"");//用户姓名
			ovt.setApplicantName(item[0]+"");
			ovt.setPosition(item[1]+"");
			ovt.setDeptName(item[2]+"");//部门名称
			ovt.setAppDate(DateUtils.parse(item[3]+"",DateUtils.DATE_SMALL_STR));
			ovt.setPlanBgDtime(DateUtils.parse(item[4]+""));
			ovt.setPlanEdDtime(DateUtils.parse(item[5]+""));
			ovt.setPlanOtCnt(Double.parseDouble(item[6]+""));
			ovt.setRealBgDtime(DateUtils.parse(item[7]+""));
			ovt.setRealEdDtime(DateUtils.parse(item[8]+""));
			ovt.setRealOtCnt(Double.parseDouble(item[9]+""));
			ovt.setProcStatusName(Integer.parseInt(item[10]+"")==1?"初始状态":Integer.parseInt(item[10]+"")==2?"审批中":Integer.parseInt(item[10]+"")==3?"已完成":Integer.parseInt(item[10]+"")==4?"已失效":Integer.parseInt(item[10]+"")==5?"已撤销":"已拒绝");
			ovt.setRemark(item[11]+"");
			ovt.setAppNo(item[12]+"");
			ovt.setOveId(Integer.parseInt(item[13]+""));
			ovt.setUserId(Integer.parseInt(item[14]+""));//用户编号
			ovt.setOrganizationId(Integer.parseInt(item[15]+""));//部门编号
			list2.add(ovt);
		}
		return list2;
	}
	
	@Override
	public Long getCount(Map<String, Object> map,Integer id) {
		StringBuffer stringBuffer = new StringBuffer("SELECT COUNT(1)");
		stringBuffer.append(" FROM t_oa_pd_overtime_app ovt");
		stringBuffer.append(" LEFT JOIN qqms.t_users u ON u.USER_ID = ovt.APPLICANT_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = ovt.DEPT_NO WHERE 1=1");
		if(StringUtils.isNotBlank(map.get("status")+"") && map.get("status")!=null){
			stringBuffer.append(" AND ovt.PROC_STATUS='"+map.get("status")+"'");
		}
		if(StringUtils.isNotBlank(map.get("dateBegin")+"") && map.get("dateBegin")!=null){
			stringBuffer.append(" AND ovt.REAL_BG_DTIME>='"+map.get("dateBegin")+"'");
		}
		if(StringUtils.isNotBlank(map.get("dateEnd")+"") && map.get("dateEnd")!=null){
			stringBuffer.append(" AND ovt.REAL_ED_DTIME<='"+map.get("dateEnd")+"'");
		}
		if(1==id){
			stringBuffer.append(" AND ovt.APPLICANT_NO="+Constants.getCurrendUser().getUserId());
		}
		Long count=publicDaoModel.findTotalCount(stringBuffer.toString());
		return count;
	}
	
	@Override
	public boolean saveOvertime(OvertimeApp overtimeApp) {
		boolean flag=false;
		try {
			publicDao.saveOrUpdate(overtimeApp);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delOvertime(Integer oveId) {
		boolean flag = false;
		try {
			publicDao.executeHql("delete OvertimeApp where oveId ="+oveId);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public void updateProcStatus(Integer id, String status) {
		publicDao.executeBySql("UPDATE t_oa_pd_overtime_app SET PROC_STATUS = '"+status+"' WHERE OVE_ID= "+id);
	}

	@Override
	public String submitCardApply(Integer id) {
		OvertimeApp overtime = publicDao.get(OvertimeApp.class, id);
		// 获取启动实例的key
		String proDefKey = getOvertimeAppImage(id);
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setBusinessID(id.toString());//业务ID
		taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
//		taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
		taskModel.setAppNo(overtime.getAppNo());
		Map<String,Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
		if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			this.updateApplyStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
		}
////		 获取启动实例的key
////		String proDefKey = Constants.OVERTIMEAPP;
//		// 定义businessKey
//		String businessKey = proDefKey + "." + id;
//		// 指定流程变量
//		Map<String, Object> variables = new HashMap<String, Object>();
//		String userId = String.valueOf(Constants.getCurrendUser().getUserId());
//		variables.put(Constants.CURRENT_USER_KEY, userId);
//		// 启动流程实例
//		ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(proDefKey, businessKey, variables);
//		return pi != null ? true : false;
		return resultMap.get(Constants.RESULT_STR).toString();
	}

	@Override
	public List<OvertimeAppModel> getTaskByGroup(int firstResult, int maxResults,WorkFlowTasksModel workFlowTasksModel1) {
//		WorkFlowTasksModel flowTasksModel = new  WorkFlowTasksModel();
//		List<WorkFlowTasksModel> workList = workFlowTaskService
//				.findAcceptTaskByGroup(taskModel);
		//登录角色所要办理的的任务集合
		List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(workFlowTasksModel1);
		List<OvertimeAppModel> purchaseAppModelList = new ArrayList<OvertimeAppModel>();//返回的结果集
		String ids = "";
		if (Collections.listIsNotEmpty(taskModelList)) {
			for (WorkFlowTasksModel workFlowTasksModel : taskModelList) {
				ids += workFlowTasksModel.getBusinessID()+",";
			}
			//id字符串
			ids = ids.substring(0, ids.length()-1);
//			//根据id字符串查出的集合
			List<OvertimeAppModel> pamList = getList(new HashMap<String, Object>(), new PageUtil(firstResult, 50000),0);//查询所有人任务
			for (WorkFlowTasksModel wl : taskModelList) {
				for (OvertimeAppModel purchaseAppModel : pamList) {
					if(Integer.valueOf(wl.getBusinessID()) == purchaseAppModel.getOveId()){
						purchaseAppModel.setTaskModel(wl);
						purchaseAppModel.setTaskId(wl.getTaskID());
						purchaseAppModel.setAssistant(wl.getAssistant());
						purchaseAppModel.setFormKey(wl.getFormKey());
						purchaseAppModelList.add(purchaseAppModel);
					}
				}
			}
		}
		return purchaseAppModelList;
	}

	@Override
	public void getDiagramResourceByCaId(HttpServletResponse response,
			Integer oveId) {
		// 图片的文件的流
				InputStream in = null;
				try {
					String proDefKey = "";
					String imgName = "";
					proDefKey = getOvertimeAppImage(oveId);
					if("OvertimeApp".equals(proDefKey)){
						imgName="OA_PD_OvertimeApp_FU";
					}else{
						imgName="OA_PD_OvertimeApp_BO_BU";
					}
					String businessKey = proDefKey + "." + oveId;
					// 获取当前执行的流程实例
					ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
					if(pi!=null){
						// 获取流程定义的实体对象（对应.bpmn文件中的数据）
						ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(pi.getProcessDefinitionId());
						// 获取当前执行任务流程
						List<Task> tasks = this.taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
						// 获取图片的流程图片名称
						String resourceName = imgName + ".png";
						// 获取图片的文件的流
						in = this.repositoryService.getResourceAsStream(pd.getDeploymentId(),resourceName);
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
							Execution execution = runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
							// 根据当前的执行对象的id获取正在执行的活动信息
							ActivityImpl activityImpl = pd.findActivity(execution.getActivityId());
							// 绘制矩形
							Rectangle2D rectangle = new Rectangle2D.Float(
									activityImpl.getX(), activityImpl.getY(),
									activityImpl.getWidth(), activityImpl.getHeight());
								g2d.draw(rectangle);
							}
							// 写入response输出流中
							ImageIO.write(bimg, "png", response.getOutputStream());
							}
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (in != null)
							try {
								in.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
					}	
	}
	
	@Override
	public void updateApplyStatus(Integer id,String result){
//		publicDao.executeBySql("UPDATE t_oa_pd_overtime_app SET APP_STATUS = '"+OvertimeApp.class.getSimpleName()+"_"+result+"' WHERE OVE_ID = "+id);
		OvertimeApp overtimeApp = publicDao.get(OvertimeApp.class, id);
		overtimeApp.setAppStatus(result);
		publicDao.saveOrUpdate(overtimeApp);
	}

	@Override
	public String saveSubmitTask(WorkFlowTasksModel taskModel) {
		Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
		if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			this.updateApplyStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}
	
	private String getOvertimeAppImage(Integer id){
		String proDefKey=null;
		Organization org = organizationService.findOrganizationByOrganizationId(userService.findOrgByuserId(Constants.getCurrendUser().getUserId()).getOrganizationId());//获取当前提交用户的职位Id
//		//0为总部 1为分部
//		proDefKey=Constants.OVERTIMEAPPBOBU;
		if(null != org.getDeptLevel() && "0".equals(org.getDeptLevel())){
			List<Role> roleList = userService.findRoleListByUserId(Constants.getCurrendUser().getUserId());
			if (Collections.listIsNotEmpty(roleList)) {
				//目前只考虑一个用户只有一个角色
				Role role = roleList.get(0);
				//1－业务，2－职能，3－其他
				if ("1".equals(role.getRoleType())) {
				}else if("2".equals(role.getRoleType())){
					proDefKey = Constants.OVERTIMEAPP;//总部/分公司职能端员工加班审批
				}else{
				}
			}
		}else{
			List<Role> roleList = userService.findRoleListByUserId(Constants.getCurrendUser().getUserId());
			if (Collections.listIsNotEmpty(roleList)) {
				//目前只考虑一个用户只有一个角色
				Role role = roleList.get(0);
				//1－业务，2－职能，3－其他
				if ("1".equals(role.getRoleType())) {
					proDefKey = Constants.OVERTIMEAPPBOBU;//分部业务端 
				}else if("2".equals(role.getRoleType())){
					proDefKey = Constants.OVERTIMEAPP; //总部/分公司职能端员工加班审批
				}else{
				}
			}
		}
		return proDefKey;
	}
	
	@Override
	public boolean saveSubmitConfirmOvertimeApp(String taskId,String result, AuditProcHis auditProcHis,String oveId) {
		boolean flag=false;
		try {
			Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();// 获取当前执行任务
			Integer userId = Constants.getCurrendUser().getUserId();//获取登录人的id
			/***保存审批流程履历表 start****/
			String roleCode = (String) taskService.getVariableLocal(task.getId(), "role");
			StatusNameRef statusNameRef=null;
			String proDefKey=getOvertimeAppImage(Integer.parseInt(oveId));
			if(Constants.OVERTIMEAPPBOBU.equals(proDefKey)){
				//分部业务端
				statusNameRef=statusNameRefService.getstatusNameRefByStatusCode(Constants.OVERTIMEAPPBOBU+"_"+result);
			}else{
				//总部/分公司职能端
				statusNameRef=statusNameRefService.getstatusNameRefByStatusCode(Constants.OVERTIMEAPP+"_"+result);
			}
			auditProcHis.setAppStatus(statusNameRef.getRefId());
			auditProcHis.setHandleDate(new Date());
			auditProcHis.setHandler(userId);
			auditProcHis.setHandlerRole(roleCode);
			auditProcHisService.saveAuditProcHis(auditProcHis);
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("result", result);//只有一个确认按钮
			taskService.complete(task.getId(), variables);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
