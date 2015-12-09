package com.oasys.serviceImpl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
import com.oasys.model.ExpensesApp;
import com.oasys.model.Organization;
import com.oasys.model.StatusNameRef;
import com.oasys.model.Users;
import com.oasys.model.VO.ExpensesAppModel;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.ExpensesAppService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.MoneyUtil;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 费用申请serviceImpl
 * @ClassName: ExpensesAppServiceImpl 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:21:59
 */
@Service(value="expensesAppService")
public class ExpensesAppServiceImpl extends WorkFlowBaseServiceImpl implements ExpensesAppService {

	@Autowired
	private PublicDao<ExpensesApp> publicDao;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private StatusNameRefService statusNameRefService;
	@Autowired
	private AuditProcHisService auditProcHisService;
	
	@Override
	public boolean saveExpensesApp(ExpensesApp expensesApp) {
		try {
			String appNum = UniqueIdUtil.generate("FY");
			Users user = userService.findUserById(Constants.getCurrendUser().getUserId());
			expensesApp.setDeptNo(user.getOrganizeId());;//申请单位
			expensesApp.setAppStatus("1");//申请状态，初始状态
			expensesApp.setApplicantNo(Constants.getCurrendUser().getUserId());//申请人
			expensesApp.setAppNo(appNum);//申请编号
			expensesApp.setProcStatus("1");//流程状态
			if (StringUtils.isNotBlank(expensesApp.getPayMode()) && "1".equals(expensesApp.getPayMode())) {//现金
				expensesApp.setActName(null);//账户名称
				expensesApp.setBankName(null);//银行名称
				expensesApp.setIntoAct(null);//转入账号
			}
			if (StringUtils.isNotBlank(expensesApp.getBillType()) && !"3".equals(expensesApp.getBillType())) {//其他
				expensesApp.setBillTypeOther(null);//其他票据类型名称
			}
			publicDao.saveOrUpdate(expensesApp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ExpensesAppModel> findExpensesAppList(PageUtil pageUtil,ExpensesAppModel expensesAppModel) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Integer userId = Constants.getCurrendUser().getUserId();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("o.FULL_NAME as '所属部门名称',");
			sql.append("u.USER_NAME as '申请人账号',");
			sql.append("e.BTA_ID AS '主键',");
			sql.append("e.APP_NO AS '申请编号',");
			sql.append("e.APPLICANT_NO AS '申请人id',");
			sql.append("e.DEPT_NO AS '部门id',");
			sql.append("e.APP_DATE AS '申请日期',");
			sql.append("e.APP_STATUS AS '申请状态',");
			sql.append("e.EXP_RESON AS '费用用途',");
			sql.append("e.APP_AMT AS '申请金额',");
			sql.append("IFNULL(e.PAY_MODE,'') AS '支付方式',");
			sql.append("e.INTO_ACT AS '转入账号',");
			sql.append("e.BANK_NAME AS '银行名称',");
			sql.append("e.ACT_NAME AS '账户名称',");
			sql.append("IFNULL(e.BILL_TYPE,'') AS '票据类型',");
			sql.append("e.BILL_TYPE_OTHER AS '其他票据类型',");
			sql.append("e.PROC_STATUS AS '流程状态',");
			sql.append("e.REMARK AS '备注信息',");
			sql.append("s.DICT_NAME AS '支付方式名称',");
			sql.append("ss.DICT_NAME AS '票据类型名称',");
			sql.append("ref.APP_STATUS_NAME AS '申请状态名称' ");
			sql.append("FROM t_oa_ad_expenses_app e ");
			sql.append("LEFT JOIN qqms.t_organization o ON e.DEPT_NO = o.ORGANIZATION_ID ");
			sql.append("LEFT JOIN t_oa_app_status_name_ref ref ON e.APP_STATUS = ref.APP_STATUS_CODE ");
			sql.append("LEFT JOIN qqms.t_users u ON e.APPLICANT_NO = u.USER_ID ");
			sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'pay_mode')) s ON s.DICT_CODE = e.PAY_MODE ");
			sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'bill_type')) ss ON ss.DICT_CODE = e.BILL_TYPE ");
			sql.append("WHERE e.APPLICANT_NO = '"+userId+"' ");
			if (StringUtils.isNotBlank(expensesAppModel.getAppNo())) {
				sql.append("AND e.APP_NO like '%"+expensesAppModel.getAppNo()+"%' ");
			}
			if (expensesAppModel.getAppAmtMini()!=null) {
				sql.append("AND e.APP_AMT >= '"+expensesAppModel.getAppAmtMini()+"' ");
			}
			if (expensesAppModel.getAppAmtMax()!=null) {
				sql.append("AND e.APP_AMT <= '"+expensesAppModel.getAppAmtMax()+"' ");
			}
			if (StringUtils.isNotBlank(expensesAppModel.getPayMode())) {
				sql.append("AND e.PAY_MODE = '"+expensesAppModel.getPayMode()+"' ");
			}
			if (StringUtils.isNotBlank(expensesAppModel.getAppDateMini())) {
				sql.append("AND e.APP_DATE >= '"+expensesAppModel.getAppDateMini()+"' ");
			}
			if (StringUtils.isNotBlank(expensesAppModel.getAppDateMax())) {
				sql.append("AND e.APP_DATE <= '"+expensesAppModel.getAppDateMax()+"' ");
			}
			sql.append("ORDER BY e.BTA_ID DESC");
			List<Object> list = publicDao.findBySql(sql.toString(), pageUtil);
			List<ExpensesAppModel> expensesAppList = new ArrayList<ExpensesAppModel>();
			if (Collections.listIsNotEmpty(list)) {
				ExpensesAppModel model = new ExpensesAppModel();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[])list.get(i);
					ExpensesAppModel cmodel = (ExpensesAppModel)model.clone();
					cmodel.setFullName(obj[0]==null?"":String.valueOf(obj[0]));
					cmodel.setAccount(obj[1]==null?"":String.valueOf(obj[1]));
					cmodel.setBtaId(obj[2]==null?null:(Integer)obj[2]);
					cmodel.setAppNo(obj[3]==null?"":String.valueOf(obj[3]));
					cmodel.setApplicantNo(obj[4]==null?null:(Integer)obj[4]);
					cmodel.setDeptNo(obj[5]==null?0:(Integer)obj[5]);
					cmodel.setAppDate(obj[6]==null?null:sdf.parse(String.valueOf(obj[6])));
					cmodel.setAppStatus(obj[7]==null?"":String.valueOf(obj[7]));
					cmodel.setExpReson(obj[8]==null?"":String.valueOf(obj[8]));
					cmodel.setAppAmt(obj[9]==null?null:MoneyUtil.numberWithDelimiter(String.valueOf(obj[9])));
					cmodel.setPayMode(obj[10]==null?"":String.valueOf(obj[10]));
					cmodel.setIntoAct(obj[11]==null?null:(Integer)obj[11]);
					cmodel.setBankName(obj[12]==null?"":String.valueOf(obj[12]));
					cmodel.setActName(obj[13]==null?"":String.valueOf(obj[13]));
					cmodel.setBillType(obj[14]==null?"":String.valueOf(obj[14]));
					cmodel.setBillTypeOther(obj[15]==null?"":String.valueOf(obj[15]));
					cmodel.setProcStatus(obj[16]==null?"":String.valueOf(obj[16]));
					cmodel.setRemark(obj[17]==null?"":String.valueOf(obj[17]));
					cmodel.setPayModeName(obj[18]==null?"":String.valueOf(obj[18]));
					cmodel.setBillTypeName(obj[19]==null?"":String.valueOf(obj[19]));
					cmodel.setAppStatus(obj[20]==null?"":String.valueOf(obj[20]));
					expensesAppList.add(cmodel);
				}
			}
			return expensesAppList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long countFindExpensesAppList() {
		Integer userId = Constants.getCurrendUser().getUserId();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM t_oa_ad_expenses_app e ");
		sql.append("LEFT JOIN qqms.t_organization o ON e.DEPT_NO = o.ORGANIZATION_ID ");
		sql.append("LEFT JOIN qqms.t_users u ON e.APPLICANT_NO = u.USER_ID ");
		sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'pay_mode')) s ON s.DICT_CODE = e.PAY_MODE ");
		sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'bill_type')) ss ON ss.DICT_CODE = e.BILL_TYPE ");
		sql.append("WHERE e.APPLICANT_NO = '"+userId+"' ");
		sql.append("ORDER BY e.BTA_ID DESC");
		return publicDao.findTotalCount(sql.toString());
	}

	@Override
	public boolean delExpensesAppbyBtaId(Integer btaId) {
		try {
			ExpensesApp expensesApp = new ExpensesApp();
			expensesApp.setBtaId(btaId);
			publicDao.delete(expensesApp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean sumitApply(ExpensesApp expensesApp) {
		try {
			String proDefKey = "";
			boolean bl = findZongBuOrFenBu(expensesApp);
			//true是总部，false是分部
			if (bl) {
				proDefKey = Constants.EXPENSESAPP_HO;
			}else{
				proDefKey = Constants.EXPENSESAPP_BO;
			}
			String businessKey = proDefKey+"."+expensesApp.getBtaId();
			Map<String, Object> variables = new HashMap<String, Object>();
			String userId = String.valueOf(Constants.getCurrendUser().getUserId());
			variables.put(Constants.CURRENT_USER_KEY,userId);
			ProcessInstance pi = this.runtimeService.startProcessInstanceByKey(proDefKey,businessKey,variables);
			//更新流程状态
			updateExpensesAppProcessStatus(expensesApp.getBtaId(),"2");
			
			return pi != null ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean findZongBuOrFenBu(ExpensesApp expensesApp) {
		try {
			ExpensesApp expensesApp2 = publicDao.get(ExpensesApp.class,expensesApp.getBtaId());
			if (expensesApp2!=null) {
				Integer appDeptId = expensesApp2.getDeptNo();
				Organization organization = organizationService.findOrganizationByOrganizationId(appDeptId);
				if(organization!=null){
					//0是总部，1是分部
					if("0".equals(organization.getDeptLevel())){
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void updateExpensesAppProcessStatus(Integer btaId, String status) {
		ExpensesApp expensesApp = publicDao.get(ExpensesApp.class, btaId);
		if (expensesApp!=null) {
			expensesApp.setProcStatus(status);
			if("2".equals(status)){
				expensesApp.setAppDate(new Date());
			}
		}
		//跟新订单状态
		publicDao.saveOrUpdate(expensesApp);
	}

	@Override
	public List<ExpensesAppModel> findAllExpensesAppTaskList(PageUtil pageUtil,WorkFlowTasksModel wf) {
		try {
			//登录角色所要办理的的任务集合
			List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(wf);
			//返回的结果集
			List<ExpensesAppModel> expensesAppModelList = new ArrayList<ExpensesAppModel>();
			String ids = "";
			if (Collections.listIsNotEmpty(taskModelList)) {
				for (WorkFlowTasksModel workFlowTasksModel : taskModelList) {
					ids += workFlowTasksModel.getBusinessID()+",";
				}
				//id字符串
				ids = ids.substring(0, ids.length()-1);
				//根据id字符串查出的集合
				List<ExpensesAppModel> pamList = findExpensesAppByIds(ids,pageUtil);
				for (WorkFlowTasksModel wl : taskModelList) {
					for (ExpensesAppModel expensesAppModel : pamList) {
						if(Integer.valueOf(wl.getBusinessID()) == expensesAppModel.getBtaId()){
							expensesAppModel.setTaskModel(wl);
							expensesAppModel.setTaskId(wl.getTaskID());
							expensesAppModelList.add(expensesAppModel);
						}
					}
				}
			}
			return expensesAppModelList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<ExpensesAppModel> findExpensesAppByIds(String ids,PageUtil pageUtil){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("o.FULL_NAME as '所属部门名称',");
			sql.append("u.USER_NAME as '申请人账号',");
			sql.append("e.BTA_ID AS '主键',");
			sql.append("e.APP_NO AS '申请编号',");
			sql.append("e.APPLICANT_NO AS '申请人id',");
			sql.append("e.DEPT_NO AS '部门id',");
			sql.append("e.APP_DATE AS '申请日期',");
			sql.append("e.APP_STATUS AS '申请状态',");
			sql.append("e.EXP_RESON AS '费用用途',");
			sql.append("e.APP_AMT AS '申请金额',");
			sql.append("e.PAY_MODE AS '支付方式',");
			sql.append("e.INTO_ACT AS '转入账号',");
			sql.append("e.BANK_NAME AS '银行名称',");
			sql.append("e.ACT_NAME AS '账户名称',");
			sql.append("e.BILL_TYPE AS '票据类型',");
			sql.append("e.BILL_TYPE_OTHER AS '其他票据类型',");
			sql.append("e.PROC_STATUS AS '流程状态',");
			sql.append("e.REMARK AS '备注信息',");
			sql.append("s.DICT_NAME AS '支付方式名称',");
			sql.append("ss.DICT_NAME AS '票据类型名称',");
			sql.append("ref.APP_STATUS_NAME AS '申请状态名称' ");
			sql.append("FROM t_oa_ad_expenses_app e ");
			sql.append("LEFT JOIN qqms.t_organization o ON e.DEPT_NO = o.ORGANIZATION_ID ");
			sql.append("LEFT JOIN t_oa_app_status_name_ref ref ON e.APP_STATUS = ref.APP_STATUS_CODE ");
			sql.append("LEFT JOIN qqms.t_users u ON e.APPLICANT_NO = u.USER_ID ");
			sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'pay_mode')) s ON s.DICT_CODE = e.PAY_MODE ");
			sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'bill_type')) ss ON ss.DICT_CODE = e.BILL_TYPE ");
			sql.append("WHERE 1=1 ");
			sql.append("AND e.BTA_ID in ("+ids+") ");
			sql.append("ORDER BY e.BTA_ID DESC");
			List<Object> list = publicDao.findBySql(sql.toString(), pageUtil);
			List<ExpensesAppModel> expensesAppList = new ArrayList<ExpensesAppModel>();
			if (Collections.listIsNotEmpty(list)) {
				ExpensesAppModel model = new ExpensesAppModel();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[])list.get(i);
					ExpensesAppModel cmodel = (ExpensesAppModel)model.clone();
					cmodel.setFullName(obj[0]==null?"":String.valueOf(obj[0]));
					cmodel.setAccount(obj[1]==null?"":String.valueOf(obj[1]));
					cmodel.setBtaId(obj[2]==null?null:(Integer)obj[2]);
					cmodel.setAppNo(obj[3]==null?"":String.valueOf(obj[3]));
					cmodel.setApplicantNo(obj[4]==null?null:(Integer)obj[4]);
					cmodel.setDeptNo(obj[5]==null?null:(Integer)obj[5]);
					cmodel.setAppDate(obj[6]==null?null:sdf.parse(String.valueOf(obj[6])));
					cmodel.setAppStatus(obj[7]==null?"":String.valueOf(obj[7]));
					cmodel.setExpReson(obj[8]==null?"":String.valueOf(obj[8]));
					cmodel.setAppAmt(obj[9]==null?null:MoneyUtil.numberWithDelimiter(String.valueOf(obj[9])));
					cmodel.setPayMode(obj[10]==null?"":String.valueOf(obj[10]));
					cmodel.setIntoAct(obj[11]==null?null:(Integer)obj[11]);
					cmodel.setBankName(obj[12]==null?"":String.valueOf(obj[12]));
					cmodel.setActName(obj[13]==null?"":String.valueOf(obj[13]));
					cmodel.setBillType(obj[14]==null?"":String.valueOf(obj[14]));
					cmodel.setBillTypeOther(obj[15]==null?"":String.valueOf(obj[15]));
					cmodel.setProcStatus(obj[16]==null?"":String.valueOf(obj[16]));
					cmodel.setRemark(obj[17]==null?"":String.valueOf(obj[17]));
					cmodel.setPayModeName(obj[18]==null?"":String.valueOf(obj[18]));
					cmodel.setBillTypeName(obj[19]==null?"":String.valueOf(obj[19]));
					cmodel.setAppStatus(obj[20]==null?"":String.valueOf(obj[20]));
					expensesAppList.add(cmodel);
				}
			}
			return expensesAppList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Long countFindAllExpensesAppTaskList(WorkFlowTasksModel wf) {
		try {
			//登录角色所要办理的的任务集合
			List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(wf);
			if (Collections.listIsNotEmpty(taskModelList)) {
				return Long.valueOf(taskModelList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}
	
	@Override
	public boolean saveHoldWorkTask(String taskId) {
		try {
			//获取用户的id
			Integer userId = Constants.getCurrendUser().getUserId();
			//签收任务
			this.taskService.claim(taskId, String.valueOf(userId));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean saveSubmitTaskBo(ExpensesAppModel expensesAppModel,String result,AuditProcHis auditProcHis) {
		try {
			Task task = this.taskService.createTaskQuery().taskId(expensesAppModel.getTaskId()).singleResult();// 获取当前执行任务
			Integer userId = Constants.getCurrendUser().getUserId();//获取登录人的id
			updateExpensesAppStatus(expensesAppModel.getBtaId(),Constants.EXPENSESAPP_BO +"_"+result);// 修改订单的状态
			
			/***保存审批流程履历表 start****/
			String roleCode = (String) taskService.getVariableLocal(task.getId(), "role");
			StatusNameRef statusNameRef = statusNameRefService.getstatusNameRefByStatusCode(Constants.EXPENSESAPP_BO+"_"+result);
			auditProcHis.setAppNo(expensesAppModel.getAppNo());
			auditProcHis.setAppStatus(statusNameRef.getRefId());
			auditProcHis.setHandleDate(new Date());
			auditProcHis.setHandler(userId);
			auditProcHis.setHandlerRole(roleCode);
			auditProcHisService.saveAuditProcHis(auditProcHis);
			/***保存审批流程履历表 start****/
			
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("result", result);
			taskService.complete(task.getId(), variables);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean saveSubmitTaskHo(ExpensesAppModel expensesAppModel,String result,AuditProcHis auditProcHis) {
		try {
			Task task = this.taskService.createTaskQuery().taskId(expensesAppModel.getTaskId()).singleResult();// 获取当前执行任务
			Integer userId = Constants.getCurrendUser().getUserId();//获取登录人的id
			updateExpensesAppStatus(expensesAppModel.getBtaId(),Constants.EXPENSESAPP_HO +"_"+result);// 修改订单的状态
			
			/***保存审批流程履历表 start****/
			String roleCode = (String) taskService.getVariableLocal(task.getId(), "role");
			StatusNameRef statusNameRef = statusNameRefService.getstatusNameRefByStatusCode(Constants.EXPENSESAPP_HO+"_"+result);
			auditProcHis.setAppNo(expensesAppModel.getAppNo());
			auditProcHis.setAppStatus(statusNameRef.getRefId());
			auditProcHis.setHandleDate(new Date());
			auditProcHis.setHandler(userId);
			auditProcHis.setHandlerRole(roleCode);
			auditProcHisService.saveAuditProcHis(auditProcHis);
			/***保存审批流程履历表 start****/
			
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("result", result);
			taskService.complete(task.getId(), variables);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void updateExpensesAppStatus(Integer btaId, String state) {
		//跟新订单状态
		ExpensesApp expensesApp = publicDao.get(ExpensesApp.class,btaId);
		expensesApp.setAppStatus(state);
		publicDao.saveOrUpdate(expensesApp);
	}
	@Override
	public void getDiagramResourceByPaId(HttpServletResponse response,Integer btaId){
		// 图片的文件的流
		InputStream in = null;
		try {
			String proDefKey = "";
			String imgName = "";
			ExpensesApp expensesApp = new ExpensesApp();
			expensesApp.setBtaId(btaId);
			boolean bl = findZongBuOrFenBu(expensesApp);
			if(bl){
				proDefKey = Constants.EXPENSESAPP_HO;//总部
				imgName = "OA_AD_ExpensesApp_HO";
			}else{
				proDefKey = Constants.EXPENSESAPP_BO;//分部
				imgName = "OA_AD_ExpensesApp_BO";
			}
			String businessKey = proDefKey + "." + btaId;
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
