package com.oasys.serviceImpl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.Organization;
import com.oasys.model.PPEScrapApp;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.StatusNameRef;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.PPEScrapAppAttachService;
import com.oasys.service.PPEScrapAppService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;

/**   
 * @Title: PPEScrapAppServiceImpl
 * @Package com.oasys.serviceImpl
 * @Description: 固定资产报废Service实现类
 * @author lida  
 * @date 2015/9/16
 * @version V1.0   
 */

@Service("ppeScrapAppService")
public class PPEScrapAppServiceImpl extends WorkFlowBaseServiceImpl implements PPEScrapAppService{


	@Autowired
	private PublicDao<PPEScrapApp> publicDao;
	
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
	private PPEScrapAppAttachService ppeAttachService;
	
	/** 
	 * @Title: findPpeList
	 * @Description: 查询所有固定资产报废申请记录集合
	 * @param pageUtil 分页对象
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author lida
	 * @return List<PpeScrapAppAttach> 固定资产申请记录List
	 * @throws ParseException 
	 * @date 2015/9/16
	 */
	@Override
	public List<PpeScrapAppAttach> findPpeList(PageUtil pageUtil,PPEScrapApp ppeScrap) throws ParseException {
		return getPpeAttachList(getFindHQL(ppeScrap,0,""),pageUtil,null);
	}
	
	/** 
	 * @Title: findPpeListCount
	 * @Description: 	查询所有固定资产报废申请记录数量
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author lida
	 * @return Long 固定资产申请记录总记录数
	 * @date 2015/9/16
	 */
	@Override
	public Long findPpeListCount(PPEScrapApp ppeScrap) {
		return publicDao.findTotalCount(getFindHQL(ppeScrap,1,""));
	} 
	
	
	/** 
	 * @Title: saveOrUpdatePpeEntity
	 * @Description: 	新增固定资产报废申请单
	 * @param ppe 页面绑定实体对象参数
	 * @author lida
	 * @date 2015/9/16
	 */
	@Override
	public void saveOrUpdatePpeEntity(PPEScrapApp ppe) {
		publicDao.saveOrUpdate(ppe);
	}

	/**
	 * @Title: delPpeScrap
	 * @Description: 	根据申请编号删除固定资产报废记录
	 * @param appNo 申请编号
	 * 	@author lida
	 * @date 2015/9/16
	 */
	@Override
	public void delPpeScrap(String appNo) {
		publicDao.executeHql("delete from PPEScrapApp where appNo = '" + appNo+"'");
		publicDao.executeHql("delete from PpeScrapAppAttach where appNo = '" + appNo+"'");
	}

	
	/**
	 * @Title: getPpeScrapByID
	 * @Description: 	根据ID加载固定资产报废记录
	 * @param id id标识
	 * 	@author lida
	 * @return PPEScrapApp 固定资产实体对象
	 * @date 2015/9/22
	 */
	@Override
	public PPEScrapApp getPpeScrapByID(Integer id) {
		// TODO Auto-generated method stub
		return publicDao.get(PPEScrapApp.class, id);
	}
	
	/**
	 * @Title: startProcessInstance
	 * @Description: 	开启流程
	 * @param id id标识
	 * 	@author lida
	 * @return boolean 是否开启成功
	 * @date 2015/9/23
	 */
	@Override
	public String startProcessInstance(Integer id) {
		PPEScrapApp ppeApp = getPpeScrapByID(id);
		//填充taskModel对象属性
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		Map<String,Object> variables = new HashMap<String, Object>();
		taskModel.setVariables(variables);
		taskModel.setAppNo(ppeApp.getAppNo());//APPNO
		taskModel.setBusinessID(id.toString());//业务ID
		taskModel.setBusinessDefineKey(getProcDefKey());// 获取启动实例的key
		Map<String,Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);//启动流程
		return resultMap.get(Constants.RESULT_STR).toString();
	}
	
	/**
	 * @Title: updPPEScrapProcessStatus
	 * @Description: 	更新流程状态字段
	 * @param id id标识
	 * @param status 更新的状态Code
	 * 	@author lida
	 * @date 2015/9/23
	 */
	@Override
	public void updPPEScrapProcessStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		PPEScrapApp ppe = getPpeScrapByID(id);
		ppe.setProcStatus(status);
		publicDao.saveOrUpdate(ppe);
	}

	
	/**
	 * @Title: updPPEScrapAppStatus
	 * @Description: 更新申请状态字段
	 * @param id id标识
	 * @param status 更新的状态ID
	 * 	@author lida
	 * @date 2015/9/23 
	 */
	@Override
	public void updPPEScrapAppStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		PPEScrapApp ppe = getPpeScrapByID(id);
		ppe.setAppStatus(status);
		publicDao.saveOrUpdate(ppe);
	}
	
	/**
	 * @Title: findPPEListTask
	 * @Description: 查询固定资产报废任务列表
	 * @param pageUtil 分页组件
	 * @param ppescrap 固定资产报废实体对象
	 * @return List<PpeScrapAppAttach> 固定资产报废申请附加表List
	 * 	@author lida
	 * @throws ParseException 
	 * @date 2015/9/24 
	 */
	@Override
	public List<PpeScrapAppAttach> findPPEListTask(PageUtil pageUtil,PPEScrapApp ppescrap) throws ParseException {
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setProcessKey(ppescrap.getDefinitionKey());
		List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
		if(null == workList || workList.size() == 0) return new ArrayList<PpeScrapAppAttach>();//判断是否存在流程
		String hql = getFindHQL(ppescrap,0,getTaskPPEids(workList));//获取sql语句
		List<PpeScrapAppAttach> attachList = getPpeAttachList(hql,pageUtil,workList);//查询list
		return attachList;
	}
	
	/**
	 * @Title: findPPEListTaskCount
	 * @Description: 查询固定资产报废任务列表记录数量
     * @param ppeScrap 实体对象
	 * @return Long 记录数
	 * 	@author lida
	 * @date 2015/9/24 
	 */
	@Override
	public Long findPPEListTaskCount(PPEScrapApp ppescrap) {
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setProcessKey(ppescrap.getDefinitionKey());
		List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
		if(null == workList || workList.size() == 0)return (long) 0;
		String hql = getFindHQL(ppescrap,1,getTaskPPEids(workList));//获取sql语句
		return publicDao.findTotalCount(hql);
	}
	
	
	
	/**
	 * @Title: saveSubmitTask
	 * @Description: 受理任务方法
	 * @param ppeScrap 实体对象
	 * 	@author lida
	 * @date 2015/9/24
	 */
	@Override
	public String saveSubmitTask(WorkFlowTasksModel taskModel) {
		String resultStr = "";
		if(StringUtils.isNotBlank(taskModel.getTaskID())){
			Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);//调用通用受理任务方法
			if(null != resultMap.get(Constants.STATUS_REF_ID)){
				updPPEScrapAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
			}
			resultStr = resultMap.get(Constants.RESULT_STR).toString();
		}
		return resultStr;
	}
	
	/**
	 * @Title: saveSubmitTask
	 * @Description: 批量受理任务方法
	 * @param taskModel 工作流实体对象
	 * 	@author lida
	 * @date 2015/12/3
	 */
	@Override
	public String saveSubmitTaskBatch(WorkFlowTasksModel taskModel) {
		List<Map<String,Object>> resultMapList = workFlowTaskService.saveSubmitTaskBatch(taskModel);//调用通用受理任务方法
		String resultStr = "";
		if(Collections.listIsNotEmpty(resultMapList)){
			for (Map<String, Object> map : resultMapList) {
				if(map.containsKey(Constants.STATUS_REF_ID) && map.containsKey(Constants.BUSINESS_ID)){
					updPPEScrapAppStatus(Integer.valueOf(map.get(Constants.BUSINESS_ID).toString()),map.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
					resultStr = map.get(Constants.RESULT_STR).toString();
				}
			}
		}
		return resultStr;
	}

	@Override
	public StatusNameRef findAppStatusByID(String appStatusID) {
		// TODO Auto-generated method stub
		return statusNameService.getStatusNameRefByStatusID(appStatusID);
	}

	
	/** --------- 自定义方法 ---------**/
	
	protected String getProcDefKey(){
		// 获取启动实例的key
		String proDefKey = "";
		Organization org = orgService.findOrganizationByUserId(Constants.getCurrendUser().getUserId());
		//0为总部 1为分部
		if(null != org.getDeptLevel() && "0".equals(org.getDeptLevel())){
			proDefKey = Constants.PPESCRAPAPP_HO;
		}else{
			proDefKey = Constants.PPESCRAPAPP_BO;
		}
		return proDefKey;
	}
	
	//获取task中固定资产报废表的id集合
	protected String getTaskPPEids(List<WorkFlowTasksModel> workList){
		String ids = "";
		for (WorkFlowTasksModel workFlowTasksModel : workList) {
			ids+=workFlowTasksModel.getBusinessID()+",";
		}
		if(ids.length()>0){
			ids = ids.substring(0, ids.length()-1);
		}
		return ids;
	}
	

	//获取固定资产报废HQL通用语句方法
	protected String getFindHQL(PPEScrapApp ppeScrap,Integer isCount,String ids){
		String hql ="";
		if(isCount == 0){
				hql+="SELECT  PPE.PSA_ID PPE_PSA_ID,"
				+ "PPE.APP_NO AS PPE_APP_NO,"
				+ "PPE.APPLICANT_NO,"
				+ "PPE.APP_DEPT,"
				+ "PPE.APP_STATUS,"
				+ "PPE.PROC_STATUS,"
				+ "PPE.REMARK AS PPE_REMARK,"
				+ "PPE_ATTACH.PSA_ID,"
				+ "PPE_ATTACH.APP_NO,"
				+ "PPE_ATTACH.BUY_DATE,"
				+ "PPE_ATTACH.DEPRECIATION,"
				+ "PPE_ATTACH.PPE_GROSS,"
				+ "PPE_ATTACH.PPE_MODEL,"
				+ "PPE_ATTACH.PPE_NAME,"
				+ "PPE_ATTACH.PPE_NET,"
				+ "PPE_ATTACH.PPE_NO,"
				+ "PPE_ATTACH.PPE_SALVAGE_VAL,"
				+ "PPE_ATTACH.REMARK,"
				+ "PPE_ATTACH.SCRAP_RESON,"
				+ "PPE_ATTACH.USED_YEAR,"
				+ "ORG.FULL_NAME,"
				+ "USERS.LOGIN_ACT, "
				+ "ORG.DEPT_LEVEL, "
				+ "PPE.APP_DATE ";
		}else{
			hql+="SELECT COUNT(*) ";
		}
		String hqlStr ="FROM OASYS.T_OA_AD_PPE_SCRAP_APP PPE LEFT JOIN OASYS.T_OA_AD_PPE_SCRAP_APP_ATTACH PPE_ATTACH "
		+ "ON PPE.APP_NO = PPE_ATTACH.APP_NO LEFT JOIN "
		+" QQMS.T_ORGANIZATION ORG ON PPE.APP_DEPT = ORG.ORGANIZATION_ID "
		+ " LEFT JOIN QQMS.T_USERS USERS ON PPE.APPLICANT_NO = USERS.USER_ID "
		+ "WHERE 1=1";
		//判断办理任务页面 根据id加载列表 则不添加申请人查询条件
		if(StringUtils.isNotBlank(ids)){
			hqlStr +=" AND PPE.PSA_ID in ("+ids+")";
		}else{
			hqlStr +=" AND APPLICANT_NO="+Constants.getCurrendUser().getUserId();
		}
		if(StringUtils.isNotBlank(ppeScrap.getAppDateBefore())){
			hqlStr +=" AND APP_DATE >='"+ppeScrap.getAppDateBefore().trim()+"'";
		}
		if(StringUtils.isNotBlank(ppeScrap.getAppDateAfter())){
			hqlStr +=" AND APP_DATE <='"+ppeScrap.getAppDateAfter().trim()+"'";
		}
		if(StringUtils.isNotBlank(ppeScrap.getProcStatus())){
			hqlStr +=" AND PROC_STATUS ="+ppeScrap.getProcStatus().trim();
		}
		hql+=hqlStr;
		hql += "  ORDER BY PPE.PSA_ID DESC,PPE.APP_NO";
		return hql;
	}
	
	//通用填充实体对象方法
	protected List<PpeScrapAppAttach> getPpeAttachList(String hql,PageUtil pageUtil,List<WorkFlowTasksModel> workList) throws ParseException{
		List<Object> list = publicDao.findBySql(hql, pageUtil);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<PpeScrapAppAttach> ppeAttachList = new ArrayList<PpeScrapAppAttach>();
		
		
		if(Collections.listIsNotEmpty(list)){
			PpeScrapAppAttach ppeAttach = new PpeScrapAppAttach();
			PPEScrapApp ppeApp = new PPEScrapApp();
			Object[] obj;
			String deptLevel="";
			for (int i = 0; i < list.size(); i++) {
				ppeApp = new PPEScrapApp();
				ppeAttach = new PpeScrapAppAttach();
				obj = (Object[]) list.get(i);
				ppeApp.setPsaId(obj[0] == null?0:(Integer)obj[0]);
				ppeApp.setAppNo(obj[1] == null?"":String.valueOf(obj[1]));
				ppeApp.setApplicantNo(obj[2]== null?0:(Integer)obj[2]);
				ppeApp.setAppDept(obj[3]== null?0:(Integer)obj[3]);
				ppeApp.setAppStatus(obj[4] == null?"":String.valueOf(obj[4]));
				ppeApp.setProcStatus(obj[5] == null?"":String.valueOf(obj[5]));
				ppeApp.setRemark(obj[6] == null?"":String.valueOf(obj[6]));
				ppeApp.setOrgName(obj[20] == null?"":String.valueOf(obj[20]));
				ppeApp.setUserName(obj[21] == null?"":String.valueOf(obj[21]));
				deptLevel = obj[22] == null?"":String.valueOf(obj[22]);
				//判断总部分部
				if(StringUtils.isNotBlank(deptLevel) && deptLevel.equals("0")){
					ppeApp.setResourcesName(Constants.PPESCRAPAPP_HO_RES);
					ppeApp.setDefinitionKey(Constants.PPESCRAPAPP_HO);
				}else{
					ppeApp.setResourcesName(Constants.PPESCRAPAPP_BO_RES);
					ppeApp.setDefinitionKey(Constants.PPESCRAPAPP_BO);
				}
				ppeApp.setAppDate(obj[23] == null?"":String.valueOf(obj[23]));
				//将工作流信息字段更新到PPEScrapApp对象中
				if(null != workList && workList.size() > 0){
					for (WorkFlowTasksModel wl : workList) {
						if(Integer.valueOf(wl.getBusinessID()) == ppeApp.getPsaId()){
							ppeApp.setTaskModel(wl);
							ppeApp.setTaskID(wl.getTaskID());
							ppeApp.setAssistant(wl.getAssistant());
							ppeApp.setFormKey(wl.getFormKey());
							break;
						}
					}
				}
				/** --------------------------------------------------------------------- **/
				ppeAttach.setPsaId(obj[7] == null?0:(Integer)obj[7]);
				ppeAttach.setAppNo(obj[8] == null?"":String.valueOf(obj[8]));
				ppeAttach.setBuyDate(obj[9] == null?null:sdf.parse(String.valueOf(obj[9])));
				ppeAttach.setDepreciation(obj[10] == null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[10])));
				ppeAttach.setPpeGross(obj[11] == null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[11])));
				ppeAttach.setPpeModel(obj[12] == null?"":String.valueOf(obj[12]));
				ppeAttach.setPpeName(obj[13] == null?"":String.valueOf(obj[13]));
				ppeAttach.setPpeNet(obj[14] == null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[14])));
				ppeAttach.setPpeNo(obj[15] == null?"":String.valueOf(obj[15]));
				ppeAttach.setPpeSalvageVal(obj[16] == null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[16])));
				ppeAttach.setRemark(obj[17] == null?"":String.valueOf(obj[17]));
				ppeAttach.setScrapReson(obj[18] == null?"":String.valueOf(obj[18]));
				ppeAttach.setUsedYear(obj[19] == null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[19])));
				ppeAttach.setPpeApp(ppeApp);
				ppeAttachList.add(ppeAttach);
			}
		}
		return ppeAttachList;
	}
	
	
}
