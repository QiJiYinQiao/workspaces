package com.oasys.serviceImpl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.AuditProcHis;
import com.oasys.model.OutApp;
import com.oasys.model.Users;
import com.oasys.model.VO.OutAppModel;
import com.oasys.model.VO.ProposerModel;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.OrganizationService;
import com.oasys.service.OutAppService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 费用申请serviceImpl
 * @ClassName: OutAppServiceImpl 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:21:59
 */
@Service(value="outAppService")
public class OutAppServiceImpl extends WorkFlowBaseServiceImpl implements OutAppService {

	@Autowired
	private PublicDao<OutApp> publicDao;
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
	//流程图片名称
	private String imgName;
	@Override
	public DataModel saveOutApp(OutApp outApp) {
	    try {
	    	BigDecimal hours = getHours(outApp.getPlanBgDtime(),outApp.getPlanEdDtime());
	    	//compareTo  -1表示小于 0表示等于 1表示大于
			if (hours!=null && hours.compareTo(new BigDecimal(24))==1) {
				return new DataModel("提示","外出总时长必须大于0小于24小时!",false);
			}
			//计划外出总时长
			outApp.setPlanOutCnt(hours);
			if (outApp.getOutId() == null) {//主键
				String appNum = UniqueIdUtil.generate("WC");
				Users user = userService.findUserById(Constants.getCurrendUser().getUserId());
				//申请单位
				outApp.setDeptNo(user.getOrganizeId());
				//申请人
				outApp.setApplicantNo(Constants.getCurrendUser().getUserId());
				//申请编号
				outApp.setAppNo(appNum);
				//流程状态
				outApp.setProcStatus("1");
				publicDao.save(outApp);
				return new DataModel("提示","添加数据成功!",true);
			}else{//更新
				publicDao.update(outApp);
				return new DataModel("提示","更新数据成功!",true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return new DataModel("提示","出错了,保存失败!",false);
	}

	@Override
	public List<OutAppModel> findOutAppList(PageUtil pageUtil,OutAppModel outAppModel) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sql = getListSql(outAppModel);
			List<Object> list = publicDao.findBySql(sql, pageUtil);
			List<OutAppModel> outAppList = new ArrayList<OutAppModel>();
			if (Collections.listIsNotEmpty(list)) {
				OutAppModel model = new OutAppModel();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[])list.get(i);
					OutAppModel cmodel = (OutAppModel)model.clone();
					cmodel.setOutId(obj[0]==null?null:(Integer)obj[0]);
					cmodel.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));
					cmodel.setApplicantNo(obj[2]==null?null:(Integer)obj[2]);
					cmodel.setDeptNo(obj[3]==null?null:(Integer)obj[3]);
					cmodel.setAppDate(obj[4]==null?null:sdf.parse(String.valueOf(obj[4])));
					cmodel.setAppStatus(obj[5]==null?"":String.valueOf(obj[5]));
					cmodel.setOutReason(obj[6]==null?"":String.valueOf(obj[6]));
					cmodel.setPlanBgDtime(obj[7]==null?null:sdf.parse(String.valueOf(obj[7])));
					cmodel.setPlanEdDtime(obj[8]==null?null:sdf.parse(String.valueOf(obj[8])));
					cmodel.setPlanOutCnt(obj[9]==null?null:new BigDecimal(String.valueOf(obj[9])));
					cmodel.setRealBgDtime(obj[10]==null?null:sdf.parse(String.valueOf(obj[10])));
					cmodel.setRealEdDtime(obj[11]==null?null:sdf.parse(String.valueOf(obj[11])));
					cmodel.setRealOutCnt(obj[12]==null?null:new BigDecimal(String.valueOf(obj[12])));
					cmodel.setProcStatus(obj[13]==null?"":String.valueOf(obj[13]));
					cmodel.setRemark(obj[14]==null?"":String.valueOf(obj[14]));
					cmodel.setUserName(obj[15]==null?"":String.valueOf(obj[15]));
					cmodel.setDeptName(obj[16]==null?"":String.valueOf(obj[16]));
					outAppList.add(cmodel);
				}
			}
			return outAppList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getListSql(OutAppModel outAppModel) {
		Integer userId = Constants.getCurrendUser().getUserId();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("o.OUT_ID AS '主键',");
		sql.append("o.APP_NO AS '申请编号',");
		sql.append("o.APPLICANT_NO AS '申请人',");
		sql.append("o.DEPT_NO AS '部门id',");
		sql.append("o.APP_DATE AS '申请时间',");
		sql.append("o.APP_STATUS AS '申请状态',");
		sql.append("o.OUT_REASON AS '外出原因',");
		sql.append("o.PLAN_BG_DTIME AS '计划开始时间',");
		sql.append("o.PLAN_ED_DTIME AS '计划结束时间',");
		sql.append("o.PLAN_OUT_CNT AS '计划总时间',");
		sql.append("o.REAL_BG_DTIME AS '实际开始时间',");
		sql.append("o.REAL_ED_DTIME AS '实际结束时间',");
		sql.append("o.REAL_OUT_CNT AS '实际总时间',");
		sql.append("o.PROC_STATUS AS '流程状态',");
		sql.append("o.REMARK AS '备注',");
		sql.append("u.USER_NAME AS '申请人姓名',");
		sql.append("g.FULL_NAME AS '部门名称' ");
		sql.append("FROM t_oa_pd_out_app o ");
		sql.append("LEFT JOIN t_oa_app_status_name_ref s ON s.REF_ID = o.APP_STATUS ");
		sql.append("LEFT JOIN qqms.t_organization g ON o.DEPT_NO = g.ORGANIZATION_ID ");
		sql.append("LEFT JOIN qqms.t_users u ON o.APPLICANT_NO = u.USER_ID WHERE 1=1 ");
		sql.append("AND o.APPLICANT_NO = '"+userId+"' ");
		if(StringUtils.isNotBlank(outAppModel.getAppNo())){
			sql.append("AND o.APP_NO LIKE '%"+outAppModel.getAppNo()+"%' ");
		}
		if (StringUtils.isNotBlank(outAppModel.getAppDateMax())) {
			sql.append("AND o.APP_DATE <= '"+outAppModel.getAppDateMax()+"' ");
		}
		if (StringUtils.isNotBlank(outAppModel.getAppDateMini())) {
			sql.append("AND o.APP_DATE >= '"+outAppModel.getAppDateMini()+"' ");
		}
		sql.append(" ORDER BY o.OUT_ID DESC");
		return sql.toString();
	}
	
	@Override
	public Long countFindOutAppList(OutAppModel outAppModel) {
		return publicDao.findTotalCount1(getListSql(outAppModel));
	}

	@Override
	public boolean delOutAppByOutId(Integer outId) {
		try {
			OutApp outApp = new OutApp();
			outApp.setOutId(outId);
			publicDao.delete(outApp);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String sumitApply(OutApp outApp) {
	/*	try {
			WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
			taskModel.setBusinessID(outApp.getEfaId().toString());//业务ID
			taskModel.setBusinessDefineKey(getProcessImgName(outApp));// 获取启动实例的key
//			taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
			String resultStr = workFlowTaskService.startProcessInstance(taskModel);
			//更新流程状态
			updateOutAppProcessStatus(outApp.getEfaId(),"2");
			return resultStr;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
	
	@Override
	public String getProcessImgName(OutApp outApp) {
		/*try {
			OutApp outApp2 = publicDao.get(OutApp.class,outApp.getEfaId());
			if (outApp2!=null) {
				Integer appDeptId = outApp2.getDeptNo();//部门id
				Organization organization = organizationService.findOrganizationByOrganizationId(appDeptId);
				if(organization!=null){
					//0是总部，1是分部
					if("0".equals(organization.getDeptLevel())){
						imgName = "OA_PD_OutApp_HO";
						return Constants.EMPFULLMEMBERAPPHO;//走总部流程图
					}else{
						Integer userId = outApp2.getApplicantNo();//申请人id
						List<Role> roleList = userService.findRoleListByUserId(userId);
						if (Collections.listIsNotEmpty(roleList)) {
							//目前只考虑一个用户只有一个角色
							Role role = roleList.get(0);
							//1－业务，2－职能，3－其他
							if ("1".equals(role.getRoleType())) {
								imgName = "OA_PD_OutApp_BO_BU";
								return Constants.EMPFULLMEMBERAPPBOBU; 
							}else if("2".equals(role.getRoleType())){
								imgName = "OA_PD_OutApp_BO_FU";
								return Constants.EMPFULLMEMBERAPPBOFU; 
							}else{
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
	@Override
	public void updateOutAppProcessStatus(Integer outId, String status) {
		OutApp outApp = publicDao.get(OutApp.class,outId);
		if (outApp!=null) {
			outApp.setProcStatus(status);
			if("2".equals(status)){
				//申请时间
				outApp.setAppDate(new Date());
			}
		}
		//跟新订单状态
		publicDao.saveOrUpdate(outApp);
	}
	
	@Override
	public List<OutAppModel> findAllOutAppTaskList(PageUtil pageUtil,WorkFlowTasksModel wf) {
		/*try {
		    //登录角色所要办理的的任务集合
			List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(wf);
			//返回的结果集
			List<OutAppModel> outAppModelList = new ArrayList<OutAppModel>();
			String ids = "";
			if (Collections.listIsNotEmpty(taskModelList)) {
				for (WorkFlowTasksModel workFlowTasksModel : taskModelList) {
					ids += workFlowTasksModel.getBusinessKey()+",";
				}
				//id字符串
				ids = ids.substring(0, ids.length()-1);
				//根据id字符串查出的集合
				List<OutAppModel> pamList = findOutAppByIds(ids,pageUtil);
				for (WorkFlowTasksModel wl : taskModelList) {
					for (OutAppModel outAppModel : pamList) {
						if(Integer.valueOf(wl.getBusinessKey()) == outAppModel.getEfaId()){
							outAppModel.setTaskModel(wl);
							outAppModel.setTaskId(wl.getTaskID());
							outAppModel.setFormKey(wl.getFormKey());
							outAppModelList.add(outAppModel);
						}
					}
				}
			}
			return outAppModelList;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
	
	@Override
	public List<OutAppModel> findOutAppByIds(String ids,PageUtil pageUtil){
		/*try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("f.EFA_ID AS '主键',");
			sql.append("f.APP_NO AS '申请编号',");
			sql.append("f.APP_DATE AS '申请日期',");
			sql.append("f.APPLICANT_NO AS '申请人',");
			sql.append("f.DEPT_NO AS '部门编号',");
			sql.append("f.DEPT_NAME AS '部门名称',");
			sql.append("f.POSITION AS '职位',");
			sql.append("f.GRADUATE_SCHOOL AS '毕业院校',");
			sql.append("f.MAJOR AS '专业',");
			sql.append("IFNULL(f.EDUCATION,'') AS '学历',");
			sql.append("f.AGE AS '年龄',");
			sql.append("f.ENTRY_DATE AS '入职日期',");
			sql.append("f.REGULAR_SAL AS '转正薪资',");
			sql.append("f.BG_PROB_DATE AS '试用期开始日期',");
			sql.append("f.ED_PROB_DATE AS '试用期结束日期',");
			sql.append("IFNULL(f.CHOOSE_MODE,'') AS '甄选方式',");
			sql.append("f.WORK_YEARS AS '工作经验年限',");
			sql.append("f.COR_WORK_YEARS AS '相关工作经验年限',");
			sql.append("f.NOCOR_WORK_YEARS AS '非相关工作经验年限',");
			sql.append("f.WORK_EXPLAIN AS '工作说明',");
			sql.append("f.REMARK AS '备注',");
			sql.append("u.USER_NAME AS '用户名称',");
			//sql.append("o.FULL_NAME AS '部门名称',");
			sql.append("s.DICT_NAME AS '学历名称',");
			sql.append("ss.DICT_NAME AS '甄选方式名称',");
			sql.append("f.APP_STATUS AS '申请状态',");
			sql.append("f.PROC_STATUS AS '流程状态',");
			sql.append("r.APP_STATUS_NAME AS '申请状态名称' ");
			sql.append("FROM t_oa_hr_emp_fullmember_app f ");
			//sql.append("LEFT JOIN qqms.t_organization o ON f.DEPT_NO = o.ORGANIZATION_ID ");
			sql.append("LEFT JOIN qqms.t_users u ON f.APPLICANT_NO = u.USER_ID ");
			sql.append("LEFT JOIN t_oa_app_status_name_ref r ON r.APP_STATUS_CODE = f.APP_STATUS ");
			sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'degree_type')) s ON s.DICT_CODE = f.EDUCATION ");
			sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'choose_mode')) ss ON ss.DICT_CODE = f.CHOOSE_MODE ");
			sql.append("WHERE 1=1 ");
			sql.append("AND f.EFA_ID in ("+ids+") ");
			sql.append("ORDER BY f.EFA_ID DESC");
			List<Object> list = publicDao.findBySql(sql.toString(), pageUtil);
			List<OutAppModel> outAppList = new ArrayList<OutAppModel>();
			if (Collections.listIsNotEmpty(list)) {
				OutAppModel model = new OutAppModel();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[])list.get(i);
					OutAppModel cmodel = (OutAppModel)model.clone();
					cmodel.setEfaId(obj[0]==null?null:(Integer)obj[0]);
					cmodel.setAppNo(obj[1]==null?"":String.valueOf(obj[1]));
					cmodel.setAppDate(obj[2]==null?null:sdf.parse(String.valueOf(obj[2])));
					cmodel.setApplicantNo(obj[3]==null?null:(Integer)obj[3]);
					cmodel.setDeptNo(obj[4]==null?null:(Integer)obj[4]);
					cmodel.setDeptName(obj[5]==null?"":String.valueOf(obj[5]));
					cmodel.setPosition(obj[6]==null?"":String.valueOf(obj[6]));
					cmodel.setGraduateSchool(obj[7]==null?"":String.valueOf(obj[7]));
					cmodel.setMajor(obj[8]==null?null:String.valueOf(obj[8]));
					cmodel.setEducation(obj[9]==null?"":String.valueOf(obj[9]));
					cmodel.setAge(obj[10]==null?null:(Integer)obj[10]);
					cmodel.setEntryDate(obj[11]==null?null:sdf.parse(String.valueOf(obj[11])));
					cmodel.setRegularSal(obj[12]==null?null:MoneyUtil.numberWithDelimiter(String.valueOf(obj[12])));
					cmodel.setBgProbDate(obj[13]==null?null:sdf.parse(String.valueOf(obj[13])));
					cmodel.setEdProbDate(obj[14]==null?null:sdf.parse(String.valueOf(obj[14])));
					cmodel.setChooseMode(obj[15]==null?"":String.valueOf(obj[15]));
					cmodel.setWorkYears(obj[16]==null?null:(Integer)obj[16]);
					cmodel.setCorWorkYears(obj[17]==null?null:(Integer)obj[17]);
					cmodel.setNocorWorkYears(obj[18]==null?null:(Integer)obj[18]);
					cmodel.setWorkExplain(obj[19]==null?"":String.valueOf(obj[19]));
					cmodel.setRemark(obj[20]==null?null:String.valueOf(obj[20]));
					cmodel.setAccount(obj[21]==null?null:String.valueOf(obj[21]));
					cmodel.setEducationName(obj[22]==null?null:String.valueOf(obj[22]));
					cmodel.setChooseModeName(obj[23]==null?null:String.valueOf(obj[23]));
					cmodel.setAppStatus(obj[24]==null?null:String.valueOf(obj[24]));
					cmodel.setProcStatus(obj[25]==null?null:String.valueOf(obj[25]));
					cmodel.setAppStatusName(obj[26]==null?null:String.valueOf(obj[26]));
					outAppList.add(cmodel);
				}
			}
			return outAppList;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
	
	@Override
	public Long countFindAllOutAppTaskList() {
		/*try {
			//登录角色所要办理的的任务集合
			List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(new WorkFlowTasksModel());
			if (Collections.listIsNotEmpty(taskModelList)) {
				return Long.valueOf(taskModelList.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return 0L;
	}
	
	@Override
	public boolean saveHoldWorkTask(String taskId) {
		/*try {
			//获取用户的id
			Integer userId = Constants.getCurrendUser().getUserId();
			//签收任务
			this.taskService.claim(taskId, String.valueOf(userId));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return false;
	}
	
	@Override
	public String saveSubmitTaskBo(WorkFlowTasksModel taskModel) {
		/*Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
		if(StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			updateOutAppStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();*/
		return null;
	}
	@Override
	public boolean saveSubmitTaskHo(OutAppModel outAppModel,String result,AuditProcHis auditProcHis) {
		try {
			/*Task task = this.taskService.createTaskQuery().taskId(outAppModel.getTaskId()).singleResult();// 获取当前执行任务
			Integer userId = Constants.getCurrendUser().getUserId();//获取登录人的id
			updateOutAppStatus(outAppModel.getBtaId(),Constants.EXPENSESAPP_HO +"_"+result);// 修改订单的状态
			
			*//***保存审批流程履历表 start****//*
			String roleCode = (String) taskService.getVariableLocal(task.getId(), "role");
			StatusNameRef statusNameRef = statusNameRefService.getstatusNameRefByStatusCode(Constants.EXPENSESAPP_HO+"_"+result);
			auditProcHis.setAppNo(outAppModel.getAppNo());
			auditProcHis.setAppStatus(statusNameRef.getRefId());
			auditProcHis.setHandleDate(new Date());
			auditProcHis.setHandler(userId);
			auditProcHis.setHandlerRole(roleCode);
			auditProcHisService.saveAuditProcHis(auditProcHis);
			*//***保存审批流程履历表 start****//*
			
			Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("result", result);
			taskService.complete(task.getId(), variables);
			return true;*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public void updateOutAppStatus(Integer outId, String state) {
		/*//跟新订单状态
		OutApp outApp = publicDao.get(OutApp.class,outId);
		outApp.setAppStatus(state);
		publicDao.saveOrUpdate(outApp);*/
	}
	@Override
	public void getDiagramResourceByPaId(HttpServletResponse response,Integer outId){
		/*// 图片的文件的流
		InputStream in = null;
		try {
			OutApp outApp = new OutApp();
			outApp.setEfaId(outId);
			String proDefKey = getProcessImgName(outApp);
			String businessKey = proDefKey + "." + outId;
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}*/
	}

	@Override
	public List<ProposerModel> findCreateCombogrid(String q) {
		/*try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("u.USER_ID AS '用户id',");
			sql.append("u.USER_NAME AS '用户名',");
			sql.append("u.ORGANIZATION_ID AS '部门id',");
			sql.append("o.FULL_NAME AS '部门名称',");
			sql.append("r.DUTY AS '职位',");
			sql.append("u.GRADUATE_SCHOOL AS '毕业院校',");
			sql.append("u.MAJOR AS '专业',");
			sql.append("IFNULL(u.EDUCATION,'') AS '学历',");
			sql.append("u.AGE AS '年龄',");
			sql.append("r.ENTRY_DATE AS '入职日期',");
			sql.append("r.REGULAR_BASE_SAL AS '转正基本工资',");
			sql.append("r.REGULAR_POST_SAL AS '转正岗位工资',");
			sql.append("r.REGULAR_PERF_SAL AS '转正绩效工资',");
			sql.append("r.TRIAL_ED_DATE AS '试用期结束日期',");
			sql.append("u.TAKEJOB_DATE AS '参加工作时间',");
			sql.append("s.DICT_NAME AS '学历名称' ");
			sql.append("FROM qqms.t_users u ");
			sql.append("LEFT JOIN t_oa_hr_emp_roster_reg r ON u.USER_ID = r.USER_ID ");
			sql.append("LEFT JOIN qqms.t_organization o ON u.ORGANIZATION_ID = o.ORGANIZATION_ID ");
			sql.append("LEFT JOIN (SELECT DICT_CODE,DICT_NAME FROM qqms.t_sys_dict WHERE PARENT_ID = (SELECT CODE_ID FROM qqms.t_sys_dict WHERE DICT_CODE = 'degree_type')) s ON s.DICT_CODE = u.EDUCATION ");
			sql.append("WHERE 1=1 ");
			if (StringUtils.isNotBlank(q)) {
				sql.append("AND u.USER_NAME LIKE '%"+q+"%'");
			}
			List<Object[]> list = publicDao.findBySQL(sql.toString());
			List<ProposerModel> listModel = new ArrayList<ProposerModel>();
			if (Collections.listIsNotEmpty(list)) {
				ProposerModel model = new ProposerModel();
				for (Object[] obj : list) {
					ProposerModel cmodel = (ProposerModel)model.clone();
					cmodel.setApplicantNo(obj[0]==null?null:(Integer)obj[0]);
					cmodel.setUserName(obj[1]==null?"":String.valueOf(obj[1]));
					cmodel.setDeptNo(obj[2]==null?null:(Integer)obj[2]);
					cmodel.setDeptName(obj[3]==null?"":String.valueOf(obj[3]));
					cmodel.setPosition(obj[4]==null?"":String.valueOf(obj[4]));
					cmodel.setGraduateSchool(obj[5]==null?"":String.valueOf(obj[5]));
					cmodel.setMajor(obj[6]==null?"":String.valueOf(obj[6]));
					cmodel.setEducation(obj[7]==null?"":String.valueOf(obj[7]));
					cmodel.setAge(obj[8]==null?"":String.valueOf(obj[8]));
					cmodel.setEntryDate(obj[9]==null?"":String.valueOf(obj[9]));
					//转正基本工资
					BigDecimal baseSal = obj[10]==null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[10]));
					//转正岗位工资
					BigDecimal postSal = obj[11]==null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[11]));
					//转正绩效工资
					BigDecimal perfSal = obj[12]==null?new BigDecimal(0):new BigDecimal(String.valueOf(obj[12]));
					BigDecimal count1 = baseSal.add(postSal);
					BigDecimal count2 = count1.add(perfSal);
					//转正后工资
					cmodel.setRegularSal(MoneyUtil.numberWithDelimiter(String.valueOf(count2)));
					//试用期开始日期也是入职日期
					cmodel.setBgProbDate(obj[9]==null?"":String.valueOf(obj[9]));
					//试用期结束日期
					cmodel.setEdProbDate(obj[14]==null?"":String.valueOf(obj[14]));
					cmodel.setEducationName(obj[15]==null?"":String.valueOf(obj[15]));
					listModel.add(cmodel);
				}
			}
			return listModel;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}
	/**
	 * 计算两个时间之间总时长
	 * @Title: getHours 
	 * @Description: TODO
	 * @param @param bgTime
	 * @param @param edTime
	 * @param @return
	 * @author PANCHUANHE
	 * @return BigDecimal
	 * @date 2015年11月18日 下午2:08:22
	 * @throws
	 */
	public BigDecimal getHours(Date bgTime,Date edTime){
		try {
			if (bgTime!=null && edTime!=null) {
				//得到两者的毫秒数
			    long between = (edTime.getTime() - bgTime.getTime());
			    long hour = between / (60 * 60 * 1000);
		        return new BigDecimal(hour);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 
	
	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
}
