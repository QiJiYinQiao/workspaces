package com.oasys.serviceImpl;


import java.math.BigDecimal;
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
import com.oasys.model.LoanApp;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.LoanAppService;
import com.oasys.service.LoanTaskService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.SystemCodeService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.WorkFlowTasksModel;
@Service("loanTaskService")
@SuppressWarnings("unchecked")
public class LoanTaskServiceImpl  extends WorkFlowBaseServiceImpl  implements LoanTaskService
{

	
	//申请状态
	@Autowired
	private StatusNameRefService  statusNameRefService;

	@Autowired
	private AuditProcHisService auditProcHisService;
	
	@Autowired
	private LoanAppService loanAppService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private PublicDao<LoanApp> loanDao;
	
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private SystemCodeService systemCodeService;
	
	/**
	 * 开启流程
	 */
	@Override
	public String addLoanStartProcessInstance(Integer btaId) {
		try {
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sd=new SimpleDateFormat("yyyy-Mm-dd HH:mm:ss");
			LoanApp loanApp = loanDao.get(LoanApp.class, btaId);
			
			
			// TODO Auto-generated method stub
			// 获取启动实例的keygg
			String proDefKey = Constants.LOANAPP;
			// 定义businessKey
			String businessKey = proDefKey + "." + btaId;
			
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setBusinessID(btaId.toString());//业务ID
			taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
//			taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
			taskModel.setAppNo(loanApp.getAppNo());//编号
			//提交申请人可能不是申请人
			Map<String,Object> var=new HashMap<String, Object>();
			var.put(Constants.APPUSER, loanApp.getApplicantNo());
			taskModel.setVariables(var);
			Map<String, Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
			
			//更新申请时间
			loanApp.setAppDate(new Date());
			loanDao.saveOrUpdate(loanApp);
			
			//判断任务未结束
			if(null != resultMap.get(Constants.STATUS_REF_ID) && StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
				loanAppService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新申请状态
			}
			return resultMap.get(Constants.RESULT_STR).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 查询借款申请列表
	 */
	@Override
	public List<LoanApp> findNotTaskClimList(LoanApp loanApp,PageUtil pageUtil) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(loanApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return new ArrayList<LoanApp>();
			StringBuffer sql = loanAppService.getLoanListSQL();
			sql.append(" AND l.BTA_ID IN ("+getTaskPPEids(workList)+") ");
			if (StringUtils.isNotBlank(loanApp.getAppNo())) {
				sql.append(" AND l.APP_NO='" + loanApp.getAppNo() + "' ");
			} else {
				if (StringUtils.isNotBlank(loanApp.getAppDateS())) {
					sql.append(" AND l.APP_DATE >='" + loanApp.getAppDateS()
							+ "' "); // 申请开始日期
				}
				if (StringUtils.isNotBlank(loanApp.getAppDateE())) {
					sql.append(" AND l.APP_DATE <='" + loanApp.getAppDateE()
							+ "' "); // 申请结束日期
				}
			}
			// 排序
			sql.append(" ORDER BY l.BTA_ID DESC ");
			
			List<Object> list = loanDao.findBySql(sql.toString(), pageUtil);
			List<LoanApp> loanList = new ArrayList<LoanApp>();
			if(Collections.listIsNotEmpty(list)){
				for (WorkFlowTasksModel wl : workList) {
						LoanApp app = new LoanApp();
						for (int i = 0; i < list.size(); i++) {
							Object[] obj = (Object[]) list.get(i);
							LoanApp loan = (LoanApp) app.clone();
							loan.setBtaId(obj[0] == null ? 0 : (Integer) obj[0]);// 申请id
							loan.setAppNo(obj[1] == null ? "" : String.valueOf(obj[1]));// 申请编号
							loan.setApplicantNo(obj[2] == null ? 0 : (Integer) obj[2]);// 申请人id
							loan.setApplicantName(obj[3] == null ? "" : String
									.valueOf(obj[3]));// 申请人姓名
							loan.setAppDate(obj[4] == null ? null : sdf.parse(String
									.valueOf(obj[4])));// 申请日期
							loan.setPayerNo(obj[5] == null ? 0 : (Integer) obj[5]);// 付款人id
							loan.setPayerName(obj[6] == null ? "" : String
									.valueOf(obj[6]));// 付款人姓名
							loan.setPayDate(obj[7] == null ? null : sdf.parse(String
									.valueOf(obj[7])));// 付款日期
							loan.setPayMode(obj[8] == null ? "" : String
									.valueOf(obj[8]));// 付款方式
							loan.setCapitalNature(obj[9] == null ? "1" : String
									.valueOf(obj[9]));// 资金性质，默认就一个：银行转账
							loan.setCapitalNatureName(obj[10] == null ? "" : String
									.valueOf(obj[10]));// 资金性质名字
							loan.setLoanAmt(obj[11] == null ? BigDecimal.valueOf(0)
									: BigDecimal.valueOf(Double.valueOf(String
											.valueOf(obj[11]))));// 借款金额
							loan.setLoanReson(obj[12] == null ? "" : String
									.valueOf(obj[12]));// 借款理由
							loan.setIsSuccess(obj[13] == null ? "1" : String
									.valueOf(obj[13]));// 是否销账，0：是，1：否
							loan.setAppStatus(obj[14] == null ? "" : String
									.valueOf(obj[14]));// 申请状态
							loan.setProcStatus(obj[15] == null ? "" : String
									.valueOf(obj[15]));// 流程状态
							loan.setRemark(obj[16] == null ? "" : String
									.valueOf(obj[16]));// 备注
							loan.setLoanPurpose(obj[17] == null ? "2" : String
									.valueOf(obj[17]));// 资金用途
							loanList.add(loan);
						
						
						if( Integer.valueOf(wl.getBusinessID()).intValue() == loan.getBtaId().intValue() ){
							loan.setTaskModel(wl);
							loan.setTaskID(wl.getTaskID());
							loan.setAssistant(wl.getAssistant());
							loan.setFormKey(wl.getFormKey());
						}
					}
				}
			}
			return loanList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<LoanApp>();
		
	}

	/**
	 * 查询借款申请的任务数量
	 * @Title: findTotal 
	 * @Description: TODO
	 * @param @param leaveApp
	 * @param @return
	 * @author WANGXINCHENG
	 * @return Long
	 * @date 2015年11月27日 下午2:03:03
	 * @throws
	 */
	@Override
	public Long findTotal(LoanApp loanApp) {
		try {
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setProcessKey(loanApp.getDefinitionKey());
			List<WorkFlowTasksModel> workList = workFlowTaskService.findAcceptTaskByGroup(taskModel);
			if(null == workList || workList.size() == 0)return 0L;
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) FROM t_oa_fd_loan_app l WHERE 1=1");
			sql.append(" AND l.BTA_ID IN ("+getTaskPPEids(workList)+") ");
			
			if (StringUtils.isNotBlank(loanApp.getAppNo())) {
				sql.append(" AND l.APP_NO='" + loanApp.getAppNo() + "' ");
			} else {
				if (StringUtils.isNotBlank(loanApp.getAppDateS())) {
					sql.append(" AND l.APP_DATE >='" + loanApp.getAppDateS()
							+ "' "); // 申请开始日期
				}
				if (StringUtils.isNotBlank(loanApp.getAppDateE())) {
					sql.append(" AND l.APP_DATE <='" + loanApp.getAppDateE()
							+ "' "); // 申请结束日期
				}
			}
			Long totalCount = loanDao.findTotalCount(sql.toString());
			
			return totalCount;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}

	//获取task中借款申请表的id集合
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
	 * 个人领取任务
	 * @Title: getTaskUserClaim 
	 * @Description: TODO
	 * @param @param taskId
	 * @param @throws ActivitiTaskAlreadyClaimedException
	 * @author WANGXINCHENG
	 * @return void
	 * @date 2015年11月27日 下午2:10:48
	 * @throws
	 */
	@Override
	public void getTaskUserClaim(String taskId) throws ActivitiTaskAlreadyClaimedException{
		//领取人id
		Integer userId=Constants.getCurrendUser().getUserId();
		this.taskService.claim(taskId, String.valueOf(userId));
	}

	/**
	 * 办理任务
	 * @Title: saveSubmitTask 
	 * @Description: TODO
	 * @param @param taskModel
	 * @param @return
	 * @param @throws Exception
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年11月27日 下午2:14:42
	 * @throws
	 */
	@Override
	public String saveSubmitTask(WorkFlowTasksModel taskModel) throws Exception {
		Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
		if(StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			loanAppService.updateAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}
	

	
	
	
	
	
	
	
	
}
