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
import com.oasys.model.Organization;
import com.oasys.model.PenaltyNoticeSubmitApp;
import com.oasys.model.PenaltyNoticeSubmitAppAttach;
import com.oasys.model.Users;
import com.oasys.service.OrganizationService;
import com.oasys.service.PenaltyNoticeSubmitAppAttachService;
import com.oasys.service.PenaltyNoticeSubmitAppService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.WorkFlowTasksModel;
@Service(value="penaltyNoticeSubmitAppService")
public class PenaltyNoticeSubmitAppServiceImpl extends WorkFlowBaseServiceImpl
		implements PenaltyNoticeSubmitAppService {
	@Autowired
	private PublicDao<PenaltyNoticeSubmitApp> publicDao;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private PenaltyNoticeSubmitAppAttachService penaltyNoticeSubmitAppAttachService;
	@Override
	public List<PenaltyNoticeSubmitAppAttach> findPenaltyNoticeSubmitAppList(
			PageUtil pageUtil, PenaltyNoticeSubmitApp penaltyNoticeSubmitApp) {
		// TODO Auto-generated method stub
		String hql = getFindHQL(penaltyNoticeSubmitApp);
		List<PenaltyNoticeSubmitApp> list = publicDao.find(hql, pageUtil);
		List<PenaltyNoticeSubmitAppAttach> attachList = new ArrayList<PenaltyNoticeSubmitAppAttach>();
		for (PenaltyNoticeSubmitApp penaltyNoticeSubmitp : list) {
			Users user = userService.getUserByID(penaltyNoticeSubmitp.getApplicantNo());
			penaltyNoticeSubmitp.setUserName(user.getName());//翻译用户名
			List<PenaltyNoticeSubmitAppAttach> tmpList = penaltyNoticeSubmitAppAttachService.findPenaltyNoticeSubmitAppAttachList(penaltyNoticeSubmitp.getAppNo());//设置查询条件 查询固定资产列表信息
			//填充List对象
			if(tmpList.size()>0){
				for(PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttach : tmpList){
					Users users = userService.getUserByID(penaltyNoticeSubmitAppAttach.getPtNo());
					penaltyNoticeSubmitAppAttach.setPtName(users.getName());
					penaltyNoticeSubmitAppAttach.setPtDeptName(orgService.getOrgNameByID(users.getOrganizeId()));
					penaltyNoticeSubmitAppAttach.setSignDeptName(orgService.getOrgNameByID(penaltyNoticeSubmitAppAttach.getSignDeptNo()));
					penaltyNoticeSubmitAppAttach.setPenaltyNoticeSubmitApp(penaltyNoticeSubmitp);
				}
				attachList.addAll(tmpList);
			}else{
				PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttachBak = new PenaltyNoticeSubmitAppAttach();
				penaltyNoticeSubmitAppAttachBak.setAppNo(penaltyNoticeSubmitp.getAppNo());
				penaltyNoticeSubmitAppAttachBak.setPenaltyNoticeSubmitApp(penaltyNoticeSubmitp);
				attachList.add(penaltyNoticeSubmitAppAttachBak);
			}
		}
		return attachList;
	}
	
	private String getFindHQL(PenaltyNoticeSubmitApp penaltyNoticeSubmitApp){
		String hql = "from PenaltyNoticeSubmitApp where 1=1 and applicantNo = "+Constants.getCurrendUser().getUserId();
		
		if(StringUtils.isNotBlank(penaltyNoticeSubmitApp.getAppDateBefore())){
			hql += " and appDate >='" + penaltyNoticeSubmitApp.getAppDateBefore()+"'";  //申请开始日期
		}
		if(StringUtils.isNotBlank(penaltyNoticeSubmitApp.getAppDateAfter())){
			hql += " and appDate <='" + penaltyNoticeSubmitApp.getAppDateAfter()+"'";  //申请结束日期
		}
		if(StringUtils.isNotBlank(penaltyNoticeSubmitApp.getProcStatus())){
			hql+=" and procStatus='"+penaltyNoticeSubmitApp.getProcStatus()+"'";
		}
		hql += " order by pnrId desc";
		return hql;
	}

	@Override
	public Long findPenaltyNoticeSubmitAppCount(
			PenaltyNoticeSubmitApp penaltyNoticeSubmitApp) {
		// TODO Auto-generated method stub
		String hql = "select count(*) "+ getFindHQL(penaltyNoticeSubmitApp);
		return publicDao.count(hql);
	}

	@Override
	public boolean savePenaltyNoticeSubmitApp(
			PenaltyNoticeSubmitApp penaltyNoticeSubmitApp) {
		// TODO Auto-generated method stub
		try {
			if(penaltyNoticeSubmitApp.getPnrId()==null){
				//新增
				String appNo=UniqueIdUtil.generate("PK");
				penaltyNoticeSubmitApp.setAppNo(appNo);//申请编号
				penaltyNoticeSubmitApp.setApplicantNo(Constants.getCurrendUser().getUserId());//用户ID
				penaltyNoticeSubmitApp.setAppDate(new Date());//申请日期
				penaltyNoticeSubmitApp.setProcStatus("1");
				publicDao.save(penaltyNoticeSubmitApp);
			}else{
				//更新
				publicDao.update(penaltyNoticeSubmitApp);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void delPenaltyNoticeSubmitApp(String appNo) {
		// TODO Auto-generated method stub
		publicDao.executeHql("delete from PenaltyNoticeSubmitApp where appNo = '" + appNo+"'");
		publicDao.executeHql("delete from PenaltyNoticeSubmitAppAttach where appNo = '" + appNo+"'");
	}

	@Override
	public String saveStartProcess(PenaltyNoticeSubmitApp penaltyNoticeSubmitApp) {
		// TODO Auto-generated method stub
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setAppNo(penaltyNoticeSubmitApp.getAppNo());
		taskModel.setBusinessID(penaltyNoticeSubmitApp.getPnrId().toString());//业务ID
		taskModel.setBusinessDefineKey(getProcDefKey());// 获取启动实例的key
//		taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
		Map<String,Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);//启动流程
		if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			updatePenaltyNoticeSubmitAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());//更新流程状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}
	
	private String getProcDefKey() {
		// TODO Auto-generated method stub
		// 获取启动实例的key
				String proDefKey = "";
				Organization org = orgService.findOrganizationByUserId(Constants.getCurrendUser().getUserId());
				//0为总部 1为分部
				if(null != org.getDeptLevel() && "0".equals(org.getDeptLevel())){
					proDefKey = Constants.PENALTAPPLY_HO;
				}else{
					proDefKey = Constants.PENALTAPPLY_BO;
				}
				return proDefKey;
	}
	
	@Override
	public void updatePenaltyNoticeSubmitAppStatus(Integer id, String state) {
		// TODO Auto-generated method stub
		PenaltyNoticeSubmitApp penaltyNoticeSubmitApp = publicDao.get(PenaltyNoticeSubmitApp.class, id);
		penaltyNoticeSubmitApp.setAppStatus(state.toString());
		publicDao.saveOrUpdate(penaltyNoticeSubmitApp);
	}

	@Override
	public void updatePenaltyNoticeSubmitAppProceStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		PenaltyNoticeSubmitApp penaltyNoticeSubmitApp = publicDao.get(PenaltyNoticeSubmitApp.class, id);
		penaltyNoticeSubmitApp.setProcStatus(status);
		publicDao.saveOrUpdate(penaltyNoticeSubmitApp);
	}

	@Override
	public List<PenaltyNoticeSubmitAppAttach> findPenaltyNoticeSubmitAppTask(
			PageUtil pageUtil, PenaltyNoticeSubmitApp penaltyNoticeSubmitApp) {
		// TODO Auto-generated method stub
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setProcessKey(penaltyNoticeSubmitApp.getDefinitionKey());
		List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
		if(null == workList || workList.size() == 0)return new ArrayList<PenaltyNoticeSubmitAppAttach>();
		String hql ="from PenaltyNoticeSubmitApp where pnrId in ("+getTaskEMPids(workList)+") ";
		if(StringUtils.isNotBlank(penaltyNoticeSubmitApp.getAppDateBefore())){
			hql += " and appDate >='" + penaltyNoticeSubmitApp.getAppDateBefore()+"'";  //申请开始日期
		}
		if(StringUtils.isNotBlank(penaltyNoticeSubmitApp.getAppDateAfter())){
			hql += " and appDate <='" + penaltyNoticeSubmitApp.getAppDateAfter()+"'";  //申请结束日期
		}
		if(StringUtils.isNotBlank(penaltyNoticeSubmitApp.getProcStatus())){
			hql+=" and procStatus='"+penaltyNoticeSubmitApp.getProcStatus()+"'";
		}
		hql += " order by pnrId desc";
		List<PenaltyNoticeSubmitApp> enaltyNoticeSubmitAppList = publicDao.find(hql, pageUtil);
		List<PenaltyNoticeSubmitAppAttach> attachList = new ArrayList<PenaltyNoticeSubmitAppAttach>();
		//将工作流信息字段更新到PPEScrapApp对象中
		for (WorkFlowTasksModel wl : workList) {
			for (PenaltyNoticeSubmitApp penaltyNoticeSubmit : enaltyNoticeSubmitAppList) {
				if(Integer.valueOf(wl.getBusinessID()) == penaltyNoticeSubmit.getPnrId()){
					penaltyNoticeSubmit.setTaskModel(wl);
					penaltyNoticeSubmit.setTaskID(wl.getTaskID());
					penaltyNoticeSubmit.setAssistant(wl.getAssistant());
					penaltyNoticeSubmit.setFormKey(wl.getFormKey());
					break;
				}
			}
		}
		//循环处理固定资产实体与附加表实体对象 多对一关系
		for (PenaltyNoticeSubmitApp penaltyNoticeSubmit : enaltyNoticeSubmitAppList) {
			penaltyNoticeSubmit.setUserName(userService.getUserByID(penaltyNoticeSubmit.getApplicantNo()).getName());//翻译用户名
			List<PenaltyNoticeSubmitAppAttach> tmpList = penaltyNoticeSubmitAppAttachService.findPenaltyNoticeSubmitAppAttachList(penaltyNoticeSubmit.getAppNo());//设置查询条件 查询固定资产列表信息
			//填充List对象 如果有附加表信息 则将信息查询出后 循环将固定资产信息set到对应的属性中
			if(tmpList.size()>0){
				for(PenaltyNoticeSubmitAppAttach appAttach : tmpList){
					Users users = userService.getUserByID(appAttach.getPtNo());
					appAttach.setPtName(users.getName());
					appAttach.setPtDeptName(orgService.getOrgNameByID(users.getOrganizeId()));
					appAttach.setSignDeptName(orgService.getOrgNameByID(appAttach.getSignDeptNo()));
					appAttach.setPenaltyNoticeSubmitApp(penaltyNoticeSubmit);
				}
				attachList.addAll(tmpList);
			}else{
				PenaltyNoticeSubmitAppAttach penaltyNoticeSubmitAppAttach = new PenaltyNoticeSubmitAppAttach();
				penaltyNoticeSubmitAppAttach.setAppNo(penaltyNoticeSubmit.getAppNo());
				penaltyNoticeSubmitAppAttach.setPenaltyNoticeSubmitApp(penaltyNoticeSubmit);
				attachList.add(penaltyNoticeSubmitAppAttach);
			}
		}
		return attachList;
	}
	private String getTaskEMPids(List<WorkFlowTasksModel> workList){
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
	public Long findPenaltyNoticeSubmitAppTaskCount(
			PenaltyNoticeSubmitApp penaltyNoticeSubmitApp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveTask(WorkFlowTasksModel taskModel) {
		// TODO Auto-generated method stub
		Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
		if(resultMap.get(Constants.STATUS_REF_ID)!=null){
			updatePenaltyNoticeSubmitAppStatus(Integer.valueOf(taskModel.getBusinessID()), resultMap.get(Constants.STATUS_REF_ID).toString());
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}

	@Override
	public String saveSubmitTaskBatch(WorkFlowTasksModel taskModel) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> resultMapList = workFlowTaskService.saveSubmitTaskBatch(taskModel);//调用通用受理任务方法
		String resultStr = "";
		if(Collections.listIsNotEmpty(resultMapList)){
			for (Map<String, Object> map : resultMapList) {
				if(map.containsKey(Constants.STATUS_REF_ID) && map.containsKey(Constants.BUSINESS_ID)){
					updatePenaltyNoticeSubmitAppStatus(Integer.valueOf(map.get(Constants.BUSINESS_ID).toString()),map.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
					resultStr = map.get(Constants.RESULT_STR).toString();
				}
			}
		}
		return resultStr;
	}

	@Override
	public void getDiagramResourceByPaId(
			HttpServletResponse httpServletResponse, Integer pnrId) {
		// TODO Auto-generated method stub
		InputStream in = null;
		try {
			String proDefKey =getProcDefKey();
			String businessKey = proDefKey + "." + pnrId;
			// 获取当前执行的流程实例
			ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult();
			// 获取流程定义的实体对象（对应.bpmn文件中的数据）
			ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(pi.getProcessDefinitionId());
			// 获取当前执行任务流程
			List<Task> tasks = this.taskService.createTaskQuery().processInstanceBusinessKey(businessKey).list();
			// 获取图片的流程图片名称
			String resourceName = "";
			if (proDefKey.equals(Constants.PENALTAPPLY_BO)) {
				resourceName = Constants.PENALTAPPLY_BO_RES + ".png";
			} else {
				resourceName = Constants.PENALTAPPLY_HO_RES + ".png";
			}
			
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
			ImageIO.write(bimg, "png", httpServletResponse.getOutputStream());
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
}
