package com.oasys.serviceImpl;


import java.math.BigDecimal;
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
import com.oasys.model.Role;
import com.oasys.model.TravelExpensesApp;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.SystemCodeService;
import com.oasys.service.TravelExpensesAppService;
import com.oasys.service.TravelTaskService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;
@Service("travelTaskService")
@SuppressWarnings("unchecked")
public class TravelTaskServiceImpl  extends WorkFlowBaseServiceImpl  implements TravelTaskService
{

	
	//申请状态
	@Autowired
	private StatusNameRefService  statusNameRefService;
	@Autowired
	private AuditProcHisService auditProcHisService;
	@Autowired
	private TravelExpensesAppService travelExpensesAppService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private SystemCodeService systemCodeService;
	@Autowired
	private PublicDao<TravelExpensesApp> travelDao;

	/**
	 * 提交申请
	 */
	@Override
	public String addTravelTask(Integer teaId) {
		//提交申请时，查看是否有未完成任务，有责不能提交申请
		TravelExpensesApp expensesApp = travelExpensesAppService.findTravelNotTaskByTeaId(teaId);
		if(expensesApp!=null){
			return null;
		}
		
		
		TravelExpensesApp travelExpensesApp = travelDao.get(TravelExpensesApp.class, teaId);
		String proDefKey =Constants.TRAVELEXPENSESAPP;
		// 定义businessKey
		String businessKey = proDefKey + "." + teaId;
		
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setBusinessID(teaId.toString());//业务ID
		taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
//		taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
		taskModel.setAppNo(travelExpensesApp.getAppNo());//编号
		
		Map<String,Object> var=new HashMap<String, Object>();
		var.put(Constants.APPUSER, travelExpensesApp.getApplicantNo());
		taskModel.setVariables(var);//提交人可能不是申请人
		
		Map<String, Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
		
		//更新申请时间
		travelExpensesApp.setAppDate(new Date());
		travelDao.saveOrUpdate(travelExpensesApp);
		
		//判断任务未结束
		if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			travelExpensesAppService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新申请状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}

	/**
	 * 查询任务列表信息
	 */
	@Override
	public List<TravelExpensesApp> findTravelTaskList(
			TravelExpensesApp travelExpensesApp, PageUtil pageUtil) {
		//基本信息
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Integer userId = Constants.getCurrendUser().getUserId();//登录人id
			Role role = userService.findRoleListByUserId(userId).get(0);//获得角色
			String deptLeave = organizationService.findOrgDeptLevelByUserID(userId);//获取总部，分部
			
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(travelExpensesApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return new ArrayList<TravelExpensesApp>();
			
			StringBuffer sql = travelExpensesAppService.getUseStampSql();
			sql.append(" AND tea.TEA_ID IN ("+getTaskPPEids(workList)+")");
			if(StringUtils.isNotBlank(travelExpensesApp.getAppNo())){
				sql.append(" AND tea.APP_NO='"+travelExpensesApp.getAppNo()+"' ");
			}else{
				if(StringUtils.isNotBlank(travelExpensesApp.getAppDateS())){
					sql.append(" AND tea.APP_DATE >='" + travelExpensesApp.getAppDateS()+"' ") ;  //申请开始日期
				}
				if(StringUtils.isNotBlank(travelExpensesApp.getAppDateE())){
					sql.append(" AND tea.APP_DATE <='" + travelExpensesApp.getAppDateE()+"' ") ;  //申请结束日期
				}
			}
			//排序
			sql.append(" ORDER BY tea.TEA_ID DESC ");
			
			List<Object> list = travelDao.findBySql(sql.toString(), pageUtil);
			List<TravelExpensesApp> travelList=new ArrayList<TravelExpensesApp>();
			for (WorkFlowTasksModel wl : workList) {
				TravelExpensesApp expensesApp=new TravelExpensesApp();
				
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					TravelExpensesApp app=(TravelExpensesApp) expensesApp.clone();
					app.setTeaId(obj[0] == null?0:(Integer)obj[0]);//申请id
					app.setAppNo(obj[1] == null?"":String.valueOf(obj[1]));//申请编号
					app.setApplicantNo(obj[2] == null?0:(Integer)obj[2]);//申请人id
					app.setApplicantName(obj[3] == null?"":String.valueOf(obj[3]));//申请人名字
					app.setDeptNo(obj[4] == null?0:(Integer)obj[4]);
					app.setFullName(obj[5] == null?"":String.valueOf(obj[5]));//部门名字
					app.setDeptLevel(obj[6] == null?"":String.valueOf(obj[6]));//总部级别
					app.setAppDate(obj[7] == null?null:sdf.parse(String.valueOf(obj[7])));//申请日期
					app.setBtDays((BigDecimal) (obj[8] == null?BigDecimal.valueOf(0):BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[8])).doubleValue())));//出差天数
					app.setSubsidyAmt((BigDecimal) (obj[9] == null?BigDecimal.valueOf(0):BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[9])).doubleValue())));//出差补助
					app.setExpAmt((BigDecimal) (obj[10] == null?BigDecimal.valueOf(0):BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[10])).doubleValue())));//报销总额
					app.setGrantExp((BigDecimal) (obj[11] == null?BigDecimal.valueOf(0):BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[11])).doubleValue())));//预借旅费
					app.setSupplyAmt((BigDecimal) (obj[12] == null?BigDecimal.valueOf(0):BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[12])).doubleValue())));//补领费用
					app.setGivebackAmt((BigDecimal) (obj[13] == null?BigDecimal.valueOf(0):BigDecimal.valueOf(Double.valueOf(String.valueOf(obj[13])).doubleValue())));//退还金额
					app.setBtReason(obj[14] == null?"":String.valueOf(obj[14]));//出差事由
					app.setAppStatus(obj[15] == null?"":String.valueOf(obj[15]));//申请状态
					app.setProcStatus(obj[16] == null?"":String.valueOf(obj[16]));//流程状态
					app.setRemark(obj[17] == null?"":String.valueOf(obj[17]));//备注信息
					app.setJkAppNo(obj[18] == null?"":String.valueOf(obj[18]));//借款编号
					app.setRoleCode(role.getRoleCode());//角色编码
					app.setDeptLevel(deptLeave);//部门级别
					
					//添加任务信息
					if( Integer.valueOf(wl.getBusinessID()).intValue() == app.getTeaId().intValue() ){
						app.setTaskModel(wl);
						app.setTaskID(wl.getTaskID());
						app.setAssistant(wl.getAssistant());
						app.setFormKey(wl.getFormKey());
					}
					
					travelList.add(app);
				}
			}
			return travelList;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<TravelExpensesApp>();
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
	 * 查询任务列表数量
	 * 
	 */
	@Override
	public Long findTravelTaskCount(TravelExpensesApp travelExpensesApp) {
		try {
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(travelExpensesApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return 0L;
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) FROM t_oa_fd_travel_expenses_app tea  ");
			sql.append("WHERE 1=1 ");
			sql.append(" AND tea.TEA_ID IN ("+getTaskPPEids(workList)+")");
			if(StringUtils.isNotBlank(travelExpensesApp.getAppNo())){
				sql.append(" AND tea.APP_NO='"+travelExpensesApp.getAppNo()+"' ");
			}else{
				if(StringUtils.isNotBlank(travelExpensesApp.getAppDateS())){
					sql.append(" AND tea.APP_DATE >='" + travelExpensesApp.getAppDateS()+"' ") ;  //申请开始日期
				}
				if(StringUtils.isNotBlank(travelExpensesApp.getAppDateE())){
					sql.append(" AND tea.APP_DATE <='" + travelExpensesApp.getAppDateE()+"' ") ;  //申请结束日期
				}
			}
			//排序
			sql.append(" ORDER BY tea.TEA_ID DESC ");
			Long count = travelDao.findTotalCount(sql.toString());
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}

	/**
	 * 领取个人任务
	 */
	@Override
	public void getUserTravelTask(String taskId)
			throws ActivitiTaskAlreadyClaimedException {
		//领取人id
		Integer userId=Constants.getCurrendUser().getUserId();
		this.taskService.claim(taskId, String.valueOf(userId));
	}

	/**
	 * 办理个人任务，公共的方法
	 */
	@Override
	public String saveSubmitTask(WorkFlowTasksModel taskModel) {
		try {
			Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
			if(StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
				travelExpensesAppService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
			}
			return resultMap.get(Constants.RESULT_STR).toString();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	
	
	
}
