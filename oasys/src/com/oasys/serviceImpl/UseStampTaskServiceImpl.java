package com.oasys.serviceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.StampStatisticsReg;
import com.oasys.model.UseStampApp;
import com.oasys.model.UseStampAppAttach;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UseStampAppService;
import com.oasys.service.UseStampTaskService;
import com.oasys.service.workFlow.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;
@Service("useStampTaskService")
@SuppressWarnings("unchecked")
public class UseStampTaskServiceImpl  extends WorkFlowBaseServiceImpl  implements UseStampTaskService
{

	
	//申请状态
	@Autowired
	private StatusNameRefService  statusNameRefService;
	@Autowired
	private AuditProcHisService auditProcHisService;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private UseStampAppService useStampAppService; 
	@Autowired
	private PublicDao<UseStampApp> stampDao;
	@Autowired
	private PublicDao<UseStampAppAttach> stampAttDao;
	@Autowired
	private PublicDao<StampStatisticsReg> stampRegDao;
	@Autowired
	private OrganizationService organizationService;
	
	
	/**
	 * 开启流程
	 */
	@Override
	public String addStartProcessInstance(Integer usaId) {
		try {
			
			//判断该申请是否有印章
			UseStampApp useStampApp = stampDao.get(UseStampApp.class, usaId); 
			//申请的印章集合
			List<UseStampAppAttach> useStampAttList = useStampAppService.findStampNamesByAppNo(useStampApp.getAppNo());
			if(useStampAttList==null || useStampAttList.size()==0){
				return null;
			}
			//判断申请下的印章是否有剩余数量
			for (UseStampAppAttach useStampAppAttach : useStampAttList) {
				if(useStampAppAttach.getStampType().equals("F")){
					
					//其他印章，判断该申请是否呗其他人借走，根据印章名字
					boolean flag = useStampAppService.getStampByName(useStampAppAttach.getStampTypeOther(), useStampAppAttach.getAppNo());
					if(!flag){
						return null;
					}
				}else{
					//正常印章
					StampStatisticsReg stampStatisticsReg = stampRegDao.get(StampStatisticsReg.class, Integer.valueOf(useStampAppAttach.getStampName()));
					if(stampStatisticsReg.getSurplusQty()-1<0){
						return null;
					}
				}
			}
			//----------任务开启流程--------------
			//流程变量
			Map<String,Object> var=new HashMap<String, Object>();
			//判断key值
			String deptLoeave = useStampAppService.getUserDeptLoeave(usaId);
			String proDefKey ="";
			if(deptLoeave.equals("0")){
				//总部
				proDefKey=Constants.USESTAMPAPP_HO;
				if(useStampApp.getUseNature().equals("1")){
					//本地使用
					var.put(Constants.CONDITIONS_KEY, "XingZhengJingLi");
				}else{
					//外接
					var.put(Constants.CONDITIONS_KEY, "ZongJingLi");
				}
			}else{
				//分部
				proDefKey=Constants.USESTAMPAPP_BO;
				if(useStampApp.getUseNature().equals("1")){
					//本地使用
					var.put(Constants.CONDITIONS_KEY, "XingZhengZhuanYuan");
				}else{
					//外接
					var.put(Constants.CONDITIONS_KEY, "ZongJingLi");
				}
			}
			
			// 定义businessKey
			String businessKey = proDefKey + "." + usaId;
			
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setBusinessID(usaId.toString());//业务ID
			taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
			taskModel.setAppNo(useStampApp.getAppNo());//编号
			
			
			//字表中的id集合
			List<String> assigneeList=new ArrayList<String>();
			if(useStampApp.getUseNature().equals("2")){
				for (UseStampAppAttach useStampAppAttach : useStampAttList) {
					assigneeList.add(useStampAppAttach.getSaaId().toString());
				}
				var.put("assigneeList", assigneeList);
				
				//数量
				var.put("count", assigneeList.size());
			}else{
				var.put("count", 0);
			}
			taskModel.setVariables(var);
			
			Map<String, Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
			
			//判断任务未结束
			if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
				useStampAppService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新申请状态
				
				
			}
			
			//更新印章汇总表中的数据
			for (UseStampAppAttach useStampAppAttach : useStampAttList) {
				if(!useStampAppAttach.getStampType().equals("F")){
					//更改剩余数量
					StampStatisticsReg stampStatisticsReg = stampRegDao.get(StampStatisticsReg.class, Integer.valueOf(useStampAppAttach.getStampName()));
					stampStatisticsReg.setSurplusQty(stampStatisticsReg.getSurplusQty()-1);
					//更新最后修改人
					stampStatisticsReg.setLastBorrower(useStampApp.getApplicantNo());
					stampRegDao.saveOrUpdate(stampStatisticsReg);
				}
			}
			
			//更新申请中的申请时间
			useStampApp.setAppDate(new Date());
			stampDao.saveOrUpdate(useStampApp);
			
			
			return resultMap.get(Constants.RESULT_STR).toString();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 查询待办任务
	 */
	@Override
	public List<UseStampApp> findNotClimTask(UseStampApp useStampApp,
			PageUtil pageUtil) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(useStampApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return new ArrayList<UseStampApp>();
			
			
			StringBuffer sql = useStampAppService.getUseStampListSQL();
			if(StringUtils.isNotBlank(useStampApp.getAppNo())){
				sql.append(" AND sta.APP_NO='"+useStampApp.getAppNo()+"' ");
			}else {
				if(StringUtils.isNotBlank(useStampApp.getMyId())){
					sql.append(" AND r.MYID='"+useStampApp.getMyId()+"' ");
				}
				if(StringUtils.isNotBlank(useStampApp.getAppDateS())){
					sql.append( " AND sta.APP_DATE >='" + useStampApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(useStampApp.getAppDateE())){
					sql.append( " AND sta.APP_DATE <='" + useStampApp.getAppDateE()+"' ");  //申请结束日期
				}
			}
			sql.append(" AND sta.USA_ID IN("+getTaskPPEids(workList)+") ");
			sql.append(" ORDER BY sta.USA_ID DESC ");
			
			List<Object> list = stampDao.findBySql(sql.toString(), pageUtil);
			List<UseStampApp> stampList=new ArrayList<UseStampApp>();
			
			if(Collections.listIsNotEmpty(list)){
				UseStampApp stampApp=new UseStampApp();
				for (int i = 0; i < list.size(); i++) {
					UseStampApp app=(UseStampApp) stampApp.clone();
					Object[] obj=(Object[]) list.get(i);
					app.setUsaId(obj[0]==null?0:(Integer)obj[0]);//主表的id
					app.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));//申请编号
					app.setApplicantNo(obj[2]==null?0:(Integer)obj[2]);//申请人id
					app.setApplicantName(obj[3]==null?"":String.valueOf(obj[3]));//申请人姓名
					app.setAppDept(obj[4]==null?0:(Integer)obj[4]);//申请人部门id
					app.setFullName(obj[5]==null?"":String.valueOf(obj[5]));//申请人部门名字
					app.setMyId(obj[6]==null?"":String.valueOf(obj[6]));//判断财富端借款端
					app.setUseNature(obj[7]==null?"":String.valueOf(obj[7]));//使用性质
					app.setAppDate(obj[8]==null?null:sdf.parse(String.valueOf(obj[8])));//申请日期
					app.setAppStatus(obj[9]==null?"":String.valueOf(obj[9]));//申请状态
					app.setTurnoverDtime(obj[10]==null?null:sim.parse(String.valueOf(obj[10])));//移交时间
					app.setUseBgDate(obj[11]==null?null:sdf.parse(String.valueOf(obj[11])));//开始用章时间
					app.setUseEdDate(obj[12]==null?null:sdf.parse(String.valueOf(obj[12])));//结束用章时间
					app.setProcStatus(obj[13]==null?"":String.valueOf(obj[13]));//流程状态
					app.setRemark(obj[14]==null?"":String.valueOf(obj[14]));//主编备注信息
					app.setSaaId(obj[15]==null?0:(Integer)obj[15]);//附加表id
					if(obj[16]!=null && String.valueOf(obj[16]).equals("F")){
						//其他张
						app.setStampName(obj[18]==null?"":String.valueOf(obj[18]));//其他张名字
						app.setStampNameId(obj[18]==null?"":String.valueOf(obj[18]));
					}else{
						app.setStampName(obj[17]==null?"":String.valueOf(obj[17]));//印章名字
						app.setStampNameId(obj[20]==null?"":String.valueOf(obj[20]));
					}
					app.setStampType(obj[19]==null?"":String.valueOf(obj[19]));//印章类型
					
					//-----自定义查询-------------
					/*String executionId = this.runtimeService.createExecutionQuery().processInstanceBusinessKey(proDefKey+"."+app.getUsaId()).singleResult().getId();
					Task task = this.taskService.createTaskQuery().executionId(executionId).singleResult();
					if(task==null){
						//开始并行网管
						task = this.taskService.createTaskQuery().taskDescription(proDefKey+"."+app.getStampNameId()).singleResult();
					}
					
					if(task!=null){
						app.setTaskID(task.getId());
						app.setAssistant(task.getAssignee());
						app.setFormKey(task.getFormKey());
						stampList.add(app);
					}*/
					
					
					for (WorkFlowTasksModel wl : workList) {
						Object obg = taskService.getVariable(wl.getTaskID().toString(),"${assignee}");
						String auqaId = (obg == null ? "" : obg.toString());
						String buID = StringUtils.isNotBlank(auqaId) ? auqaId : wl.getBusinessID();;
						String uqaId = StringUtils.isNotBlank(auqaId) ?app.getSaaId().toString():app.getUsaId().toString();
						if(buID.equals(uqaId)){
								app.setTaskModel(wl);
								app.setTaskID(wl.getTaskID());
								app.setAssistant(wl.getAssistant());
								app.setFormKey(wl.getFormKey());
								stampList.add(app);
						}
					}
					
				}
			}
			return stampList;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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

	/**
	 * 查询待办任务数量
	 */
	@Override
	public Long findNotClimTaakCount(UseStampApp useStampApp) {
		try {
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(useStampApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return 0L;
			
			Long total=0L;
			
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT COUNT(*) ");
			sql.append("FROM t_oa_ad_use_stamp_app usa ");
			sql.append("LEFT JOIN t_oa_ad_use_stamp_app_attach saa ON usa.APP_NO=saa.APP_NO ");
			sql.append("LEFT JOIN qqms.t_organization tr ON usa.APP_DEPT = tr.ORGANIZATION_ID ");
			sql.append("WHERE 1=1 AND saa.RECEIVER_DTIME is NULL ");
			sql.append(" AND usa.USA_ID IN("+getTaskPPEids(workList)+") ");
			
			if(StringUtils.isNotBlank(useStampApp.getAppNo())){
				sql.append(" AND usa.APP_NO='"+useStampApp.getAppNo()+"' ");
			}else if(StringUtils.isNotBlank(useStampApp.getMyId())){
				sql.append(" AND tr.MYID='"+useStampApp.getMyId()+"' ");
			}else{
				if(StringUtils.isNotBlank(useStampApp.getAppDateS())){
					sql.append( " AND usa.APP_DATE >='" + useStampApp.getAppDateS()+"' ");  //申请开始日期
				}
				if(StringUtils.isNotBlank(useStampApp.getAppDateE())){
					sql.append( " AND usa.APP_DATE <='" + useStampApp.getAppDateE()+"' ");  //申请结束日期
				}
			}
			
			total=stampDao.findTotalCount(sql.toString());
			return total;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 个人领取任务
	 */
	@Override
	public void getTaskUserClaim(String taskID) throws ActivitiTaskAlreadyClaimedException{
		//领取人id
		Integer userId=Constants.getCurrendUser().getUserId();
		this.taskService.claim(taskID, String.valueOf(userId));
	}

	/**
	 * 任务办理
	 */
	@Override
	public String addUserTask(WorkFlowTasksModel taskModel, String turnoverDtime,UseStampAppAttach appAttach) throws Exception {
		try {
			//防止申请调整时删除印章
			if(taskModel.getIsSuccess().equals("1")){
				List<UseStampAppAttach> list = useStampAppService.findStampNamesByAppNo(taskModel.getAppNo());
				if(Collections.listIsEmpty(list) || list.size()==0){
					return null;
				}
			}
			
			Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
			if(StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
				useStampAppService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
			}
			//印章移交
			if(appAttach.getUseNature().equals("2") && StringUtils.isNotBlank(appAttach.getUseNature())){
				//移交时间
				if(StringUtils.isNotBlank(turnoverDtime)){
					UseStampApp useStampApp = stampDao.get(UseStampApp.class, Integer.valueOf(taskModel.getBusinessID()));
					useStampApp.setTurnoverDtime(new Date());
					stampDao.saveOrUpdate(useStampApp);
				}
				
				//归还信息
				if(appAttach.getReceiverDtime()!=null){
					UseStampAppAttach stampAppAttach = stampAttDao.get(UseStampAppAttach.class, appAttach.getSaaId());
					stampAppAttach.setReceiverDtime(appAttach.getReceiverDtime());//接受时间
					stampAppAttach.setGivebackDtime(new Date());//归还时间
					stampAppAttach.setEmsNo(appAttach.getEmsNo());//快递单号
					stampAppAttach.setPostAddr(appAttach.getPostAddr());//邮寄地址
					stampAppAttach.setContactWay(appAttach.getContactWay());//联系方式
					stampAttDao.saveOrUpdate(stampAppAttach);
				}
			}
			
			
			return resultMap.get(Constants.RESULT_STR).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}


	/**
	 * 批量办理任务
	 */
	@Override
	public String addMangeTaksList(WorkFlowTasksModel taskModel) {
		try {
			List<Map<String,Object>> resultMapList = workFlowTaskService.saveSubmitTaskBatch(taskModel);//调用通用受理任务方法
			String resultStr = "";
			if(Collections.listIsNotEmpty(resultMapList)){
				for (Map<String, Object> map : resultMapList) {
					if(map.containsKey(Constants.STATUS_REF_ID) && map.containsKey(Constants.BUSINESS_ID)){
						useStampAppService.updateAppStatus(Integer.valueOf(map.get(Constants.BUSINESS_ID).toString()),map.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
						resultStr = map.get(Constants.RESULT_STR).toString();
					}
				}
			}
			return resultStr;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	
	
	
	
	
	
	
	
}
