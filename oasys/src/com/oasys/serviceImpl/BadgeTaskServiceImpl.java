package com.oasys.serviceImpl;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.BadgeApp;
import com.oasys.model.Role;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.BadgeAppService;
import com.oasys.service.BadgeTaskService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.BadgeAppModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.WorkFlowTasksModel;
@Service("badgeTaskService")
@SuppressWarnings("unchecked")
public class BadgeTaskServiceImpl  extends WorkFlowBaseServiceImpl  implements BadgeTaskService
{

	
	//申请状态
	@Autowired
	private StatusNameRefService  statusNameRefService;

	@Autowired
	private AuditProcHisService auditProcHisService;
	
	@Autowired
	private BadgeAppService badgeAppService;
	
	@Autowired
	private PublicDao<BadgeApp> publicDao;
	
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	
	
	@Override
	public String addBadgeAppStartProcessInstance(Integer pnrId) {
		BadgeApp badgeApp = publicDao.get(BadgeApp.class, pnrId);
		//判断是否有工牌信息
		Long count = badgeAppService.findbadgeAtttotal(badgeApp.getAppNo(),"");
		if(count==0L){
			return null;
		}
		
		//申请时间
		badgeApp.setAppDate(new Date());
		publicDao.saveOrUpdate(badgeApp);
		
		// TODO Auto-generated method stub
		// 获取启动实例的key
		String proDefKey = badgeAppService.getBusinessKey(pnrId);
		// 定义businessKey
		String businessKey = proDefKey + "." + pnrId;
			
		
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setBusinessID(pnrId.toString());//业务ID
		taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
//		taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
		taskModel.setAppNo(badgeApp.getAppNo());//编号
		Map<String, Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
		
		//判断任务未结束
		if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			badgeAppService.upBadgeAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新申请状态
			
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}

	@Override
	public List<BadgeAppModel> findNotTaskClimList(BadgeApp badgeApp,PageUtil pageUtil) {
		
		List<BadgeAppModel> modelList=new ArrayList<BadgeAppModel>();
		try {
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(badgeApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return new ArrayList<BadgeAppModel>();
			StringBuffer sql = new StringBuffer();
			SimpleDateFormat djr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sql.append("SELECT ");
			sql.append("ba.PNR_ID '申请id',");
			sql.append("ba.APP_NO '申请编号',");
			sql.append("rg.FULL_NAME '部门',");
			sql.append("u.USER_NAME '姓名',");
			sql.append("u.USER_ENAME '英文名',");
			sql.append("r.`NAME` '职位',");
			sql.append("ba.REMARK '备注',");
			sql.append("ba.APP_STATUS '申请状态',");
			sql.append("ba.PROC_STATUS '流程状态',");
			sql.append("ba.REG_DATETIME '登记日期',");
			sql.append("ba.APP_DATE '申请日期',");
			sql.append("baa.APPLICANT_NO '申请人id', ");
			sql.append("ba.REGISTRANT_NO '登记人id', ");
			sql.append(" baa.DEPT_NO '部门id', ");
			sql.append(" rg.MYID '区分财富端借款端', ");
			sql.append(" ur.USER_NAME '登记人姓名' ");
			sql.append(" FROM t_oa_ad_badge_app ba ");
			sql.append("LEFT JOIN t_oa_ad_badge_app_attach baa ON ba.APP_NO = baa.APP_NO ");
			sql.append(" LEFT JOIN qqms.t_users ur ON ba.REGISTRANT_NO = ur.USER_ID ");
			sql.append("LEFT JOIN qqms.t_organization  tr ON ur.ORGANIZATION_ID= tr.ORGANIZATION_ID ");
			sql.append("LEFT JOIN qqms.t_users u ON baa.APPLICANT_NO = u.USER_ID ");
			sql.append("LEFT JOIN qqms.t_organization  rg ON baa.DEPT_NO = rg.ORGANIZATION_ID ");
			sql.append("LEFT JOIN qqms.t_role r ON baa.POSITION = r.ROLE_CODE WHERE 1=1 ");
			sql.append(" AND ba.PNR_ID IN ("+getTaskPPEids(workList)+") ");
			
			if(StringUtils.isNotBlank(badgeApp.getAppNo())){
				sql.append(" AND ba.APP_NO='"+badgeApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(badgeApp.getMyId())){
				sql.append(" AND tr.MYID='"+badgeApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(badgeApp.getAppDateS())){
					sql.append( " AND ba.APP_DATE >='" + badgeApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(badgeApp.getAppDateE())){
					sql.append( " AND ba.APP_DATE <='" + badgeApp.getAppDateE()+"' ");  //申请结束日期
				}
			}
			sql.append(" ORDER BY ba.PNR_ID DESC,baa.DEPT_NO DESC ");
			
			List<Object> badgeList =publicDao.findBySql(sql.toString(), pageUtil);
			
			
			if(Collections.listIsNotEmpty(badgeList)){
				BadgeAppModel badgeAppModel=new BadgeAppModel();
				for (int i = 0; i < badgeList.size(); i++) {
					BadgeAppModel cmodel=(BadgeAppModel) badgeAppModel.clone();
					Object[] obj = (Object[]) badgeList.get(i);
					cmodel.setPnrId(obj[0] == null?0:(Integer)obj[0]);
					cmodel.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));
					cmodel.setFullName(obj[2]==null?"":String.valueOf(obj[2]));
					cmodel.setName(obj[3]==null?"":String.valueOf(obj[3]));
					cmodel.setNamePinyin(obj[4]==null?"":String.valueOf(obj[4]));
					cmodel.setPositionName(obj[5]==null?"":String.valueOf(obj[5]));
					cmodel.setRemark(obj[6]==null?"":String.valueOf(obj[6]));
					cmodel.setAppStatus(obj[7]==null?"":String.valueOf(obj[7]));
					cmodel.setProcStatus(obj[8]==null?"":String.valueOf(obj[8]));
					cmodel.setRegDatetime(obj[9]==null?null:djr.parse(String.valueOf(obj[9])));
					cmodel.setAppDate(obj[10]==null?null:String.valueOf(obj[10]));
					cmodel.setApplicantNo(obj[11] == null?0:(Integer)obj[11]);
					cmodel.setRegistrantNo(obj[12] == null?0:(Integer)obj[12]);
					cmodel.setDeptNo(obj[13] == null?0:(Integer)obj[13]);
					cmodel.setMyId(obj[14]==null?"":String.valueOf(obj[14]));//区分借款端，财富端
					cmodel.setRegistrantNanme(obj[15]==null?"":String.valueOf(obj[15]));//登记人姓名
					modelList.add(cmodel);
				}
			}
			
			for (WorkFlowTasksModel wl : workList) {
				for (BadgeAppModel badgeAppModel : modelList) {
					
					if( Integer.valueOf(wl.getBusinessID()).intValue() == badgeAppModel.getPnrId().intValue() ){
						badgeAppModel.setTaskModel(wl);
						badgeAppModel.setTaskId(wl.getTaskID());
						badgeAppModel.setAssistant(wl.getAssistant());
						badgeAppModel.setFormKey(wl.getFormKey());
					}
				}
			}
			return modelList;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<BadgeAppModel>();
		
	}

	@Override
	public Long findTotal(BadgeApp badgeApp) {
		try {
		
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(badgeApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return 0L;
		
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT count(*) FROM t_oa_ad_badge_app ba LEFT JOIN t_oa_ad_badge_app_attach baa ON ba.APP_NO = baa.APP_NO  ");
			sql.append(" LEFT JOIN qqms.t_users ur ON ba.REGISTRANT_NO = ur.USER_ID ");
			sql.append("LEFT JOIN qqms.t_organization  tr ON ur.ORGANIZATION_ID= tr.ORGANIZATION_ID   WHERE 1=1  and ba.PNR_ID  in  ("+getTaskPPEids(workList)+") ");
			if(StringUtils.isNotBlank(badgeApp.getAppNo())){
				sql.append(" AND ba.APP_NO='"+badgeApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(badgeApp.getMyId())){
				sql.append(" AND tr.MYID='"+badgeApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(badgeApp.getAppDateS())){
					sql.append( " AND ba.APP_DATE >='" + badgeApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(badgeApp.getAppDateE())){
					sql.append(" AND ba.APP_DATE <='" + badgeApp.getAppDateE()+"' ");  //申请结束日期
				}
				
			}
			sql.append(" ORDER BY ba.PNR_ID DESC ");
			Long totalCount = publicDao.findTotalCount(sql.toString());
			
			return totalCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}

	//获取task中工牌申请表的id集合
	private String getTaskPPEids(List<WorkFlowTasksModel> workList){
		String ids = "";
		for (WorkFlowTasksModel workFlowTasksModel : workList) {
			ids+=workFlowTasksModel.getBusinessID()+",";
		}
		if(ids.length()>0){
			ids = ids.substring(0, ids.length()-1);
		}
		return ids;
	}
	
	@Override
	public void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException{
		//领取人id
		Integer userId=Constants.getCurrendUser().getUserId();
		this.taskService.claim(taskId, String.valueOf(userId));
	}

	@Override
	public String addBadgeTaskMgr(WorkFlowTasksModel taskModel) throws Exception{
		try {
			String taskId=taskModel.getTaskID();
			Integer pnrId=Integer.valueOf(taskModel.getBusinessID());
			BadgeApp badgeApp = publicDao.get(BadgeApp.class, pnrId);
			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
			if(Integer.valueOf(task.getAssignee())==badgeApp.getRegistrantNo()){
				//如果受理人是申请人的话，进行判断是否有附件信息
				Long count = badgeAppService.findbadgeAtttotal(badgeApp.getAppNo(),"");
				if(count==0L){
					return null;
				}
			}else{
				Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
				if(StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
					badgeAppService.upBadgeAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
				}
				return resultMap.get(Constants.RESULT_STR).toString();
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void getDiagramResourceByPaId(HttpServletResponse response,
			Integer pnrId) {
		// 图片的文件的流
				InputStream in = null;
				try {
					String proDefKey = badgeAppService.getBusinessKey(pnrId);
					String imgName =badgeAppService.getTaskImage(pnrId);
					
					String businessKey = proDefKey + "." + pnrId;
					// 获取当前执行的流程实例
					ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
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
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (in != null)
						try {
							in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
		
		
	}

	/**
	 * 获取当前登录人的角色
	 */
	@Override
	public boolean getUserRole() {
		List<Role> list = userService.findRoleListByUserId(Constants.getCurrendUser().getUserId());
		if(list!=null && list.size()>0){
			if(list.get(0).getRoleCode().equals(Constants.AD_ROLE_CODE_MGR)){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public GridModel findBadgeList(BadgeApp badgeApp, PageUtil pageUtil) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String ids="";
			
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			//分部
			taskModel.setProcessKey(Constants.BADGEAPP_BO);
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			ids += this.getTaskPPEids(workList);
			//总部
			taskModel.setProcessKey(Constants.BADGEAPP_HO);
			List<WorkFlowTasksModel> workListHo = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(workListHo!=null && workListHo.size()>0){
				ids=ids+","+this.getTaskPPEids(workListHo);
			}
			
			if(StringUtils.isBlank(ids)){
				return new GridModel();
			}
			
			
			StringBuffer sql=new StringBuffer();
			sql.append(" SELECT	b.*, COUNT(*) total");
			sql.append(" FROM ( ");
			sql.append(" SELECT");
			sql.append(" ba.PNR_ID pnrId,");
			sql.append(" ba.APP_NO appno,");
			sql.append(" ba.REGISTRANT_NO '登记人',");
			sql.append(" u.USER_NAME '登记人名字',");
			sql.append(" baa.DEPT_NO deno,");
			sql.append(" ba.APP_DATE '申请日期',");
			sql.append(" rg.FULL_NAME dename,");
			sql.append(" ba.REMARK '备注'");
			sql.append(" FROM t_oa_ad_badge_app ba");
			sql.append(" LEFT JOIN t_oa_ad_badge_app_attach baa ON ba.APP_NO = baa.APP_NO");
			sql.append(" LEFT JOIN qqms.t_organization rg ON baa.DEPT_NO = rg.ORGANIZATION_ID");
			sql.append(" LEFT JOIN qqms.t_users u ON ba.REGISTRANT_NO = u.USER_ID");
			sql.append(" LEFT JOIN qqms.t_organization tg ON u.ORGANIZATION_ID=tg.ORGANIZATION_ID ");
			sql.append(" WHERE 1=1 AND ba.PROC_STATUS='2' ");
			//条件  
			sql.append(" AND ba.PNR_ID IN ("+ids+") ");
			
			if(StringUtils.isNotBlank(badgeApp.getAppNo())){
				sql.append(" AND ba.APP_NO='"+badgeApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(badgeApp.getMyId())){
				sql.append(" AND tg.MYID='"+badgeApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(badgeApp.getAppDateS())){
					sql.append( " AND ba.APP_DATE >='" + badgeApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(badgeApp.getAppDateE())){
					sql.append( " AND ba.APP_DATE <='" + badgeApp.getAppDateE()+"' ");  //申请结束日期
				}
			}
			
			sql.append(" ORDER BY ba.PNR_ID DESC");
			sql.append("	) b ");
			sql.append("GROUP BY b.appno,b.deno  ");
			
			//每个分部的工牌详情
			List<Object> list = publicDao.findBySql(sql.toString(), pageUtil);
			
			int size = publicDao.findBySQL(sql.toString()).size();
			
			//工牌色总素
			Long total = this.findBadgeListTotal(badgeApp);
			
			List<BadgeApp> badgeList=new ArrayList<BadgeApp>();
			if(Collections.listIsNotEmpty(list)){
				for (int i = 0; i < list.size(); i++) {
					BadgeApp app=new BadgeApp();
					Object[] obj=(Object[]) list.get(i);
					app.setPnrId(obj[0]==null?0:(Integer)obj[0]);//工牌申请id
					app.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));//申请编号
					app.setRegistrantNo(obj[2]==null?0:(Integer)obj[2]);//登记人id
					app.setRegName(obj[3]==null?"":String.valueOf(obj[3]));//登记人名字
					app.setDeptNo(obj[4]==null?0:(Integer)obj[4]);//部门id
					app.setAppDate(obj[5]==null?null:sdf.parse(String.valueOf(obj[5])));//申请日期
					app.setFullName(obj[6]==null?"":String.valueOf(obj[6]));//部门名字
					app.setRemark(obj[7]==null?"":String.valueOf(obj[7]));//备注
					app.setNumber(obj[8]==null?0L:Long.valueOf(String.valueOf(obj[8])));//每个部门的工牌数
					app.setTotal(total);//工牌总数
					
					badgeList.add(app);
				}
				return new GridModel(badgeList, (long) size);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new GridModel();
	}

	/**
	 * 查询汇总表总数据
	 */
	@Override
	public Long findBadgeListTotal(BadgeApp badgeApp) {
		try {
			String ids="";
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			//分部
			taskModel.setProcessKey(Constants.BADGEAPP_BO);
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			ids += this.getTaskPPEids(workList);
			//总部
			taskModel.setProcessKey(Constants.BADGEAPP_HO);
			List<WorkFlowTasksModel> workListHo = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(workListHo!=null && workListHo.size()>0){
				ids=ids+","+this.getTaskPPEids(workListHo);
			}
			
			if(StringUtils.isBlank(ids)){
				return 0L;
			}
			
			StringBuffer sql=new StringBuffer();
			sql.append(" SELECT COUNT(*) FROM t_oa_ad_badge_app ba ");
			sql.append(" LEFT JOIN t_oa_ad_badge_app_attach baa ON ba.APP_NO = baa.APP_NO");
			sql.append(" LEFT JOIN qqms.t_users u ON ba.REGISTRANT_NO = u.USER_ID");
			sql.append(" LEFT JOIN qqms.t_organization tg ON u.ORGANIZATION_ID=tg.ORGANIZATION_ID ");
			sql.append(" WHERE 1=1 AND ba.PROC_STATUS='2' ");
			
			sql.append(" AND ba.PNR_ID IN ("+ids+") ");
			
			if(StringUtils.isNotBlank(badgeApp.getAppNo())){
				sql.append(" AND ba.APP_NO='"+badgeApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(badgeApp.getMyId())){
				sql.append(" AND tg.MYID='"+badgeApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(badgeApp.getAppDateS())){
					sql.append( " AND ba.APP_DATE >='" + badgeApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(badgeApp.getAppDateE())){
					sql.append( " AND ba.APP_DATE <='" + badgeApp.getAppDateE()+"' ");  //申请结束日期
				}
			}
			
			Long total = publicDao.findTotalCount(sql.toString());
			return total;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0L;
	}

	

	
	
	
	
	
	
	
	
}
