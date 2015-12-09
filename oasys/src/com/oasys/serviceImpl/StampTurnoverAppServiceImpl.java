package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.StampTurnoverApp;
import com.oasys.model.StampTurnoverAppAttach;
import com.oasys.model.StatusNameRef;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StampTurnoverAppAttachService;
import com.oasys.service.StampTurnoverAppService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

/**   
 * @Title: StampTurnoverAppServiceImpl
 * @Package com.oasys.serviceImpl
 * @Description: 印章移交Service实现类
 * @author lida  
 * @date 2015/10/10
 * @version V1.0   
 */

@Service("stampTurnoverAppService")
public class StampTurnoverAppServiceImpl extends WorkFlowBaseServiceImpl implements StampTurnoverAppService{


	@Autowired
	private PublicDao<StampTurnoverApp> publicDao;
	
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	
	@Autowired
	private StatusNameRefService statusNameService;
	
	@Autowired
	private AuditProcHisService auditProcHisService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StampTurnoverAppAttachService ppeAttachService;
	
	/** 
	 * @Title: findStampList
	 * @Description: 查询所有印章移交申请记录集合
	 * @param pageUtil 分页对象
	 * @param StampTurnover 页面绑定实体对象参数
	 * @author lida
	 * @return List<StampTurnoverAppAttach> 固定资产申请记录List
	 * @date 2015/10/11
	 */
	@Override
	public List<StampTurnoverAppAttach> findStampList(PageUtil pageUtil,StampTurnoverApp StampTurnover) {
		return getStampTurnoverList(getFindSQL(StampTurnover,0,""),pageUtil,null);
	}
	
	/** 
	 * @Title: findStampListCount
	 * @Description: 	查询所有印章移交申请记录数量
	 * @param StampTurnover 页面绑定实体对象参数
	 * @author lida
	 * @return Long 固定资产申请记录总记录数
	 * @date 2015/10/11
	 */
	@Override
	public Long findStampListCount(StampTurnoverApp StampTurnover) {
		return publicDao.findTotalCount(getFindSQL(StampTurnover,1,""));
	} 
	
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 	新增印章移交申请单
	 * @param ppe 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/10/11
	 */
	@Override
	public void saveOrUpdatePpeEntity(StampTurnoverApp ppe) {
		publicDao.saveOrUpdate(ppe);
	}

	/**
	 * @Title: delStampTurnover
	 * @Description: 	根据申请编号删除印章移交记录
	 * @param appNo 申请编号
	 * 	@author lida
	 * @date 2015/10/12
	 */
	@Override
	public void delStampTurnover(String appNo) {
		publicDao.executeHql("delete from StampTurnoverApp where appNo = '" + appNo+"'");
		publicDao.executeHql("delete from StampTurnoverAppAttach where appNo = '" + appNo+"'");
	}

	
	/**
	 * @Title: getStampTurnoverByID
	 * @Description: 	根据ID加载印章移交记录
	 * @param id id标识
	 * 	@author lida
	 * @return StampTurnoverApp 固定资产实体对象
	 * @date 2015/10/12
	 */
	@Override
	public StampTurnoverApp getStampTurnoverByID(Integer id) {
		// TODO Auto-generated method stub
		return publicDao.get(StampTurnoverApp.class, id);
	}
	
	/**
	 * @Title: StampTurnoverStartProcessInstance
	 * @Description: 	开启流程
	 * @param id id标识
	 * 	@author lida
	 * @return boolean 是否开启成功
	 * @date 2015/10/12
	 */
	@Override
	public String startProcessInstance(Integer id) {
		StampTurnoverApp stamp = publicDao.get(StampTurnoverApp.class, id);
		//填充taskModel对象属性
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setAppNo(stamp.getAppNo());//APPNO
		taskModel.setBusinessID(id.toString());//业务ID
		taskModel.setBusinessDefineKey(Constants.STAMPTURNOVERAPP);// 获取启动实例的key
//		taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
		Map<String,Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);//启动流程
/*		if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			updStampTurnoverAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());//更新流程状态
		}*/
		return resultMap.get(Constants.RESULT_STR).toString();
	}
	
	/**
	 * @Title: updStampTurnoverProcessStatus
	 * @Description: 	更新流程状态字段
	 * @param id id标识
	 * @param status 更新的状态Code
	 * 	@author lida
	 * @date 2015/10/14
	 */
	@Override
	public void updStampTurnoverProcessStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		StampTurnoverApp stampTurnover = getStampTurnoverByID(id);
		stampTurnover.setProcStatus(status);
		publicDao.saveOrUpdate(stampTurnover);
	}

	
	/**
	 * @Title: updStampTurnoverAppStatus
	 * @Description: 更新申请状态字段
	 * @param id id标识
	 * @param status 更新的状态Code
	 * 	@author lida
	 * @date 2015/10/14 
	 */
	@Override
	public void updStampTurnoverAppStatus(Integer id, String status) {
		StampTurnoverApp ppe = getStampTurnoverByID(id);
		ppe.setAppStatus(status);
		publicDao.saveOrUpdate(ppe);
	}
	
	/**
	 * @Title: findStampListTask
	 * @Description: 查询印章移交任务列表
	 * @param id id标识
	 * @param status 更新的状态Code
	 * @return List<StampTurnoverAppAttach> 印章移交申请附加表List
	 * 	@author lida
	 * @date 2015/10/15 
	 */
	@Override
	public List<StampTurnoverAppAttach> findStampListTask(PageUtil pageUtil,StampTurnoverApp stampTurnover) {
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setProcessKey(stampTurnover.getDefinitionKey());
		List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
		if(null == workList || workList.size() == 0)return new ArrayList<StampTurnoverAppAttach>();
		return getStampTurnoverList(getFindSQL(stampTurnover, 0, getTaskPPEids(workList)), pageUtil, workList);
	}
	
	/**
	 * @Title: findStampListTaskCount
	 * @Description: 查询印章移交任务列表记录数量
     * @param StampTurnover 实体对象
	 * @return Long 记录数
	 * 	@author lida
	 * @date 2015/10/15 
	 */
	@Override
	public Long findStampListTaskCount(StampTurnoverApp stampTurnover) {
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setProcessKey(stampTurnover.getDefinitionKey());
		List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
		if(null == workList || workList.size() == 0)return (long) 0;
		return publicDao.findTotalCount(getFindSQL(stampTurnover, 1, getTaskPPEids(workList)));
	}
	
	
	
	/**
	 * @Title: findStampListTaskCount
	 * @Description: 查询印章移交任务列表记录数量
	 * @param StampTurnover 实体对象
	 * 	@author lida
	 * @date 2015/10/17
	 */
	@Override
	public String saveSubmitTask(WorkFlowTasksModel taskModel) {
		String resultStr = "";
		if(StringUtils.isNotBlank(taskModel.getTaskID())){
			Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);//调用通用受理任务方法
			if(null != resultMap.get(Constants.STATUS_REF_ID)){
				updStampTurnoverAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
			}
			resultStr = resultMap.get(Constants.RESULT_STR).toString();
		}
		return resultStr;
	}
	
	/** --------- 自定义方法 ---------**/
	
	//获取task中印章移交表的id集合
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

	//获取印章移交SQL通用语句方法
	private String getFindSQL(StampTurnoverApp stamp,Integer isCount,String ids){
		String hql ="";
		if(isCount == 0){
			hql+="SELECT stamp.STA_ID,"
			  	+"stamp.APP_NO s_app_no,"
			  	+"stamp.APPLICANT_NO,"
			  	+"stamp.APP_DEPT,"
			  	+"stamp.APP_STATUS,"
			  	+"stamp.PROC_STATUS,"
			  	+"stamp.TURNOVER_DATE,"
			  	+"stamp.RECEIVER_NO,"
			  	+"stamp.RECEIVER_DEPT,"
			  	+"stamp.RECEIVER_DATE,"
			  	+"stamp.SUPERVISER_NO,"
			  	+"stamp.SUPERVISER_DEPT,"
			  	+"stamp.SUPERVISER_DATE,"
			  	+"stamp.REMARK s_remark,"
			  	+"org.FULL_NAME,"
			  	+"users.LOGIN_ACT,"
			  	+"Superviser_ORG.FULL_NAME s_full_name,"
			  	+"Superviser_USERS.LOGIN_ACT s_account,"
			  	+"Receiver_ORG.FULL_NAME r_full_name,"
			  	+"Receiver_USERS.LOGIN_ACT r_account,"
			  	+"stamp.APP_DATE,"
			  	+"stamp_attach.taa_id,"
			  	+"stamp_attach.app_no,"
			  	+"stamp_attach.stamp_name,"
			  	+"stamp_attach.turnover_reson,"
			  	+"stamp_attach.is_giveback,"
			  	+"stamp_attach.giveback_datetime,"
			  	+"stamp_attach.remark";
		}else{
			hql+="SELECT COUNT(*) ";
		}
		hql +=" FROM t_oa_ad_stamp_turnover_app stamp" 
			   	+" LEFT JOIN t_oa_ad_stamp_turnover_app_attach stamp_attach ON stamp.app_no = stamp_attach.app_no"
				+" LEFT JOIN QQMS.T_ORGANIZATION ORG ON stamp.APP_DEPT = ORG.ORGANIZATION_ID "
				+" LEFT JOIN QQMS.T_USERS USERS ON stamp.APPLICANT_NO = USERS.USER_ID "
				+" LEFT JOIN QQMS.T_ORGANIZATION Receiver_ORG ON stamp.RECEIVER_DEPT = Receiver_ORG.ORGANIZATION_ID" 
				+" LEFT JOIN QQMS.T_USERS Receiver_USERS ON stamp.RECEIVER_NO = Receiver_USERS.USER_ID "
				+" LEFT JOIN QQMS.T_ORGANIZATION Superviser_ORG ON stamp.SUPERVISER_DEPT = Superviser_ORG.ORGANIZATION_ID" 
				+"	 LEFT JOIN QQMS.T_USERS Superviser_USERS ON stamp.SUPERVISER_NO = Superviser_USERS.USER_ID WHERE 1=1";
		//判断办理任务页面 根据id加载列表 则不添加申请人查询条件
		if(StringUtils.isNotBlank(ids)){
			hql +=" AND stamp.STA_ID in ("+ids+")";
		}else{
			hql +=" AND APPLICANT_NO="+Constants.getCurrendUser().getUserId();
		}
		if(StringUtils.isNotBlank(stamp.getAppDateBefore())){
			hql +=" AND APP_DATE >='"+stamp.getAppDateBefore()+"'";
		}
		if(StringUtils.isNotBlank(stamp.getAppDateAfter())){
			hql +=" AND APP_DATE <='"+stamp.getAppDateAfter()+"'";
		}
		if(StringUtils.isNotBlank(stamp.getProcStatus())){
			hql +=" AND PROC_STATUS ="+stamp.getProcStatus();
		}
		hql += "  ORDER BY stamp.STA_ID DESC,stamp.APP_NO";
		return hql;
	}
	
	/**
	 * @Title: getStampTurnoverList
	 * @Description 获取印章移交List方法
	 * @param hql 要查询的hql语句
	 * @param pageUtil 分页组件
	 * @param workList 流程List
	 * @return List<StampTurnoverAppAttach> 业务表List数据
	 */
	private List<StampTurnoverAppAttach> getStampTurnoverList(String hql,PageUtil pageUtil,List<WorkFlowTasksModel> workList){
		List<Object> list = publicDao.findBySql(hql, pageUtil);
		List<StampTurnoverAppAttach> stampAttachList = new ArrayList<StampTurnoverAppAttach>();
		if(Collections.listIsNotEmpty(list)){
			StampTurnoverApp stamp = new StampTurnoverApp();
			StampTurnoverAppAttach stampAttach = new StampTurnoverAppAttach();
			for (int i = 0; i < list.size(); i++) {
				stamp = new StampTurnoverApp();
				stampAttach = new StampTurnoverAppAttach();
				Object[] obj = (Object[]) list.get(i);
				stamp.setResourcesName(Constants.STAMPTURNOVERAPP_RES);//设置查看流程资源名称
				stamp.setDefinitionKey(Constants.STAMPTURNOVERAPP);//设置流程标识key
				stamp.setStaId(obj[0] == null?0:(Integer)obj[0]);
				stamp.setAppNo(obj[1] == null?"":String.valueOf(obj[1]));
				stamp.setApplicantNo(obj[2] == null?0:(Integer)obj[2]);
				stamp.setAppDept(obj[3] == null?0:(Integer)obj[3]);
				stamp.setAppStatus(obj[4] == null?"":String.valueOf(obj[4]));
				stamp.setProcStatus(obj[5] == null?"":String.valueOf(obj[5]));
				stamp.setTurnoverDate(obj[6] == null?"":String.valueOf(obj[6]));
				stamp.setReceiverNo(obj[7] == null?0:(Integer)obj[7]);
				stamp.setReceiverDept(obj[8] == null?0:(Integer)obj[8]);
				stamp.setReceiverDate(obj[9] == null?"":String.valueOf(obj[9]));
				stamp.setSuperviserNo(obj[10] == null?0:(Integer)obj[10]);
				stamp.setSuperviserDept(obj[11] == null?0:(Integer)obj[11]);
				stamp.setSuperviserDate(obj[12] == null?"":String.valueOf(obj[12]));
				stamp.setRemark(obj[13] == null?"":String.valueOf(obj[13]));
				stamp.setOrgName(obj[14] == null?"":String.valueOf(obj[14]));
				stamp.setUserName(obj[15] == null?"":String.valueOf(obj[15]));
				stamp.setReceiverOrgName(obj[18] == null?"":String.valueOf(obj[18]));
				stamp.setReceiverUserName(obj[19] == null?"":String.valueOf(obj[19]));
//				stamp.setSuperviserOrgName(obj[18] == null?"":String.valueOf(obj[18]));
//				stamp.setSuperviserUserName(obj[19] == null?"":String.valueOf(obj[19]));
				stamp.setAppDate(obj[20] == null?"":String.valueOf(obj[20]));
				//将工作流信息字段更新到StampTurnoverApp对象中
				if(null != workList && workList.size() > 0){
					for (WorkFlowTasksModel wl : workList) {
						if(Integer.valueOf(wl.getBusinessID()) == stamp.getStaId()){
							stamp.setTaskModel(wl);
							stamp.setTaskID(wl.getTaskID());
							stamp.setAssistant(wl.getAssistant());
							stamp.setFormKey(wl.getFormKey());
							break;
						}
					}
				}
				/** --------------------------------------------------------------------- **/
				stampAttach.setTaaId(obj[21] == null?0:(Integer)obj[21]);
				stampAttach.setAppNo(obj[22] == null?"":String.valueOf(obj[22]));
				stampAttach.setStampName(obj[23] == null?"":String.valueOf(obj[23]));
				stampAttach.setTurnoverReson(obj[24] == null?"":String.valueOf(obj[24]));
				stampAttach.setIsGiveback(obj[25] == null?null:(Character)obj[25]);
				stampAttach.setGivebackDatetime(obj[26] == null?"":String.valueOf(obj[26]));
				stampAttach.setRemark(obj[27] == null?"":String.valueOf(obj[27]));
				stampAttach.setStampTurnover(stamp);
				stampAttachList.add(stampAttach);
			}
		}
		return stampAttachList;
	}
	
	
	//根据appStatusID加载对象
	@Override
	public StatusNameRef findAppStatusByID(String appStatusID) {
		// TODO Auto-generated method stub
		return statusNameService.getStatusNameRefByStatusID(appStatusID);
	}
}
