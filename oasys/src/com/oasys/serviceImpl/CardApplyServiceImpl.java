package com.oasys.serviceImpl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.oasys.dao.PublicDao;
import com.oasys.model.AuditProcHis;
import com.oasys.model.CardApp;
import com.oasys.model.CardAppAttach;
import com.oasys.model.Organization;
import com.oasys.model.Parameter;
import com.oasys.model.Role;
import com.oasys.model.StatusNameRef;
import com.oasys.model.Users;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.CardApplyService;
import com.oasys.service.CommonService;
import com.oasys.service.OrganizationService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Collections;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.CardAppVO;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.TreeModel;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 
 * @author Guo
 *
 */
@Service("cardApplyService")
public class CardApplyServiceImpl extends WorkFlowBaseServiceImpl implements CardApplyService {
	@Autowired
	private PublicDao<CardApp> publicDao;
	@Autowired
	private PublicDao<CardAppVO> publicDaoVO;
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	@Autowired
	private StatusNameRefService statusNameRefService;
	@Autowired
	private AuditProcHisService auditProcHisService;
	@Autowired
	private PublicDao<CardAppAttach> publicDaoAtta;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private CommonService commonService;
	
	/**
	 * 展示数据
	 */
	@Override
	public List<CardAppVO> getList(Map<String, Object> map,PageUtil pageUtil,Integer id) {
		StringBuffer stringBuffer = new StringBuffer("SELECT u.USER_NAME as '姓名',o.FULL_NAME as '部门名称',r.NAME as '职位',c.personal_tel as '个人电话',c.office_tel as '办公电话', ");
		stringBuffer.append(" c.email as '邮箱',c.BRANCH_ADDR as '分公司地址',c.APP_QTY as '申请数量',c.COM_URL as '公司网址',card.REMARK as '备注',card.APP_NO as '申请编号',card.PROC_STATUS AS '流程状态',card.REG_DATETIME AS '登记日期',card.APP_DATE AS '申请日期',card.APP_QTY AS '总数',card.CA_ID '编号',card.REGISTRANT_NO '登记人编号',c.position '职务',card.TOTAL_AMT '合计金额(总)',c.PRICE '单价',c.SUBTOTAL_AMT '合计金额'");
		stringBuffer.append(" FROM t_oa_ad_card_app card LEFT JOIN t_oa_ad_card_app_attach c ON card.APP_NO=c.APP_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_users u ON u.USER_ID = c.APPLICANT_NO ");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = c.DEPT_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_role r ON r.ROLE_ID=POSITION");
		stringBuffer.append(" WHERE o.STATUS = 'A'");
		if(StringUtils.hasText(map.get("status")+"") && map.get("status")!=null){
			stringBuffer.append(" and card.PROC_STATUS="+map.get("status"));
		}
		if(StringUtils.hasText(map.get("dateBegin")+"") && map.get("dateBegin")!=null){
			stringBuffer.append(" and card.REG_DATETIME>='"+map.get("dateBegin")+"'");
		}
		if(StringUtils.hasText(map.get("dateEnd")+"") && map.get("dateEnd")!=null){
			stringBuffer.append(" and card.REG_DATETIME<='"+map.get("dateEnd")+"'");
		}
		if(StringUtils.hasText(map.get("daetApplyBegin")+"") && map.get("daetApplyBegin")!=null){
			stringBuffer.append(" and card.APP_DATE>='"+map.get("daetApplyBegin")+"'");
		}
		if(StringUtils.hasText(map.get("daetApplyEnd")+"") && map.get("daetApplyEnd")!=null){
			stringBuffer.append(" and card.APP_DATE<='"+map.get("daetApplyEnd")+"'");
		}
		if(id==1){
			stringBuffer.append(" and REGISTRANT_NO ="+Constants.getCurrendUser().getUserId());
		}
		stringBuffer.append(" ORDER BY card.REG_DATETIME DESC,card.APP_NO DESC");
		List<Object> list=publicDaoVO.findBySql(stringBuffer.toString(),pageUtil);
		List<CardAppVO> list2 = new ArrayList<CardAppVO>();
		for (int i = 0; i < list.size(); i++) {
			Object[] item=(Object[]) list.get(i);
			CardAppVO cardAppVO = new CardAppVO();
			cardAppVO.setUserName(item[0]+"");
			cardAppVO.setDeptName(item[1]==null?"":item[1]+"");
			cardAppVO.setPositionName(item[2]==null?"":item[2]+"");
			cardAppVO.setPersonalTel(item[3]==null?"":item[3]+"");
			cardAppVO.setOfficeTel(item[4]==null?"":item[4]+"");
			cardAppVO.setEmail(item[5]==null?"":item[5]+"");
			cardAppVO.setBranchAddr(item[6]==null?"":item[6]+"");
			cardAppVO.setAppQty(Integer.parseInt(item[7]+""));
			cardAppVO.setComUrl(item[8]==null?"":item[8]+"");
			cardAppVO.setRemark(item[9]==null?"":item[9]+"");
			cardAppVO.setAppNO(item[10]==null?"":item[10]+"");
			cardAppVO.setProcStatusInfo(Integer.parseInt(item[11]+"")==1?"初始状态":Integer.parseInt(item[11]+"")==2?"审批中":Integer.parseInt(item[11]+"")==3?"已完成":Integer.parseInt(item[11]+"")==4?"已失效":Integer.parseInt(item[11]+"")==5?"已撤销":"已拒绝");
			cardAppVO.setRegDeteTime(DateUtils.parse(item[12]==null?"0000-00-00 00:00:00":item[12]+""));
			cardAppVO.setAppDate(DateUtils.parse(item[13]==null?"0000-00-00":item[13]+"",DateUtils.DATE_SMALL_STR));
			cardAppVO.setSumAppQty(Long.parseLong(item[14]+""));
			cardAppVO.setCaID(Integer.parseInt(item[15]+""));
			cardAppVO.setRegistrantNO(Integer.parseInt(item[16]+""));
			cardAppVO.setPositionName(item[17]+"");
			cardAppVO.setTotalAMT(new BigDecimal(item[18]+""));
			cardAppVO.setIsHeadOrBranch("0".equals(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getDeptLevel())?0:1);
			cardAppVO.setPrice(new BigDecimal(item[19]+""));
			cardAppVO.setSubTotalAMT(new BigDecimal(item[20]+""));
			list2.add(cardAppVO);
		}
		return list2;
	}
	

	/**
	 * 删除数据
	 */
	@Override
	public boolean delCard(Integer id) {
		CardApp cardApp=publicDao.get(CardApp.class, id);
		int count=publicDao.executeHql("delete CardApp where caID="+id+"");
		if(count>=1){
			publicDao.executeHql("delete CardAppAttach where appNo='"+cardApp.getAppNo()+"'");
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 添加申请
	 */
	@Override
	public boolean addCard(CardApp cardApp) {
		boolean flag=false;
		try {
			publicDao.saveOrUpdate(cardApp);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 开启流程
	 */
	@Override
	public String submitCardApply(Integer id) {
		ShiroUser shiroUser = Constants.getCurrendUser();
		CardApp ca = publicDao.get(CardApp.class, id);
		ca.setRegistrantNO(shiroUser.getUserId());
		publicDao.update(ca);
		String proDefKey=null;
		if(getStatus(id)){//总部流程
			// 获取启动实例的key
			proDefKey = Constants.CALLINGCARD_HO;
		}else {
			proDefKey = Constants.CALLINGCARD_BO;
		}
		WorkFlowTasksModel taskModel = new WorkFlowTasksModel();
		taskModel.setBusinessID(id.toString());//业务ID
		taskModel.setBusinessDefineKey(proDefKey);// 获取启动实例的key
//		taskModel.setApplyStr(Constants.APPLY_FOR_ADJUSTMENT);//调整申请节点的标识
		taskModel.setAppNo(ca.getAppNo());
		Map<String,Object> resultMap = workFlowTaskService.startProcessInstance(taskModel);
		if(null != resultMap.get(Constants.STATUS_REF_ID) && org.apache.commons.lang3.StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			this.updateApplyStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}

	/**
	 * 获取总部分部
	 * @Title: getStatus 
	 * @Description: 获取总部分部
	 * @param @param id
	 * @param @return
	 * @author Guo
	 * @return boolean
	 * @date 2015年10月13日 上午10:49:45
	 * @throws
	 */
	private boolean getStatus(Integer id){
		int status=Integer.parseInt(publicDao.findBySQL("SELECT DEPT_LEVEL FROM qqms.t_organization WHERE ORGANIZATION_ID = (SELECT ORGANIZATION_ID FROM qqms.t_users WHERE USER_ID = (SELECT REGISTRANT_NO FROM t_oa_ad_card_app WHERE CA_ID = "+id+"))").get(0)+"");
		if(status==0){
			return true;//总部
		}else{
			return false;//分部
		}
	}

	/**
	 * 更新流程状态
	 */
	@Override
	public void updateStatus(Integer id, String status) {
		publicDao.executeHql("update CardApp set procStatus="+status+" where caID="+id+"");
	}

	/**
	 * 更新申请状态
	 */
	@Override
	public void updateApplyStatus(Integer id, String status) {
//		taskService.setVariable("1005", "RESULT", "rea");
		System.out.println("------"+CardApp.class.getSimpleName());
		publicDao.executeHql("update CardApp set appStatus='"+CardApp.class.getSimpleName()+"_"+status+"' where caID="+id+"");
	}
	
	/**
	 * 获取待办任务
	 */
	@Override
	public List<CardAppVO> getTaskByGroup(int firstResult,int maxResults,WorkFlowTasksModel workFlowTasksModel1) {
		//登录角色所要办理的的任务集合
				List<WorkFlowTasksModel> taskModelList = workFlowTaskService.findAcceptTaskByGroup(workFlowTasksModel1);
				List<CardAppVO> purchaseAppModelList = new ArrayList<CardAppVO>();//返回的结果集
				String ids = "";
				if (Collections.listIsNotEmpty(taskModelList)) {
					for (WorkFlowTasksModel workFlowTasksModel : taskModelList) {
						ids += workFlowTasksModel.getBusinessID()+",";
					}
					//id字符串
					ids = ids.substring(0, ids.length()-1);
//					//根据id字符串查出的集合
					List<CardAppVO> pamList = getList(new HashMap<String, Object>(), new PageUtil(firstResult, maxResults),0);
					for (WorkFlowTasksModel wl : taskModelList) {
						for (CardAppVO purchaseAppModel : pamList) {
							if(Integer.valueOf(wl.getBusinessID()) == purchaseAppModel.getCaID()){
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
	/**
	 * 获取待办任务信息
	 * @Title: findPurchaseAppByTask 
	 * @Description: 获取待办任务信息
	 * @param @param task
	 * @param @return
	 * @author Guo
	 * @return CardAppVO
	 * @date 2015年10月13日 上午10:52:01
	 * @throws
	 */
	public CardAppVO findPurchaseAppByTask(Task task){
		//根据task,获取流程实例
		ProcessInstance processInstance = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
		String businessKey = processInstance.getBusinessKey();
		if (org.apache.commons.lang3.StringUtils.isNotBlank(businessKey)) {
			String paId = businessKey.split("\\.")[1];
			System.err.println(paId+"----------------------------");
			StringBuffer stringBuffer = new StringBuffer("SELECT u.USER_NAME as '姓名',o.FULL_NAME as '部门名称',r.NAME as '职位',c.personal_tel as '个人电话',c.office_tel as '办公电话', ");
			stringBuffer.append(" c.email as '邮箱',c.BRANCH_ADDR as '分公司地址',c.APP_QTY as '申请数量',c.COM_URL as '公司网址',card.REMARK as '备注',card.APP_NO as '申请编号',card.PROC_STATUS AS '流程状态',card.REG_DATETIME AS '登记日期',card.APP_DATE AS '申请日期',card.APP_QTY AS '总数',card.CA_ID '编号'");
			stringBuffer.append(" FROM t_oa_ad_card_app card LEFT JOIN t_oa_ad_card_app_attach c ON card.APP_NO=c.APP_NO");
			stringBuffer.append(" LEFT JOIN qqms.t_users u ON u.USER_ID = c.APPLICANT_NO ");
			stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = c.DEPT_NO");
			stringBuffer.append(" LEFT JOIN qqms.t_role r ON r.ROLE_ID=POSITION");
			stringBuffer.append(" WHERE o.STATUS = 'A'");
			List list = publicDao.findBySQL(stringBuffer.toString());
			CardAppVO model = new CardAppVO();
			if (Collections.listIsNotEmpty(list)) {
				Object[] obj = (Object[])list.get(0);
				model.setAppNO(obj[1]+"");
				model.setAppStatus(obj[2]+"");
				model.setAppQty(Integer.parseInt(obj[3]+""));
				model.setTaskId(task.getId());
				return model;
			}
		}
		return null;
	}

	/**
	 * 签收任务
	 */
	@Override
	public boolean signTask(String taskId) {
		boolean flag = false;
		try {
			taskService.claim(taskId, Constants.getCurrendUser().getUserId()+"");
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 办理任务
	 */
	@Override
	public boolean handleTask(String taskId,String result,CardAppVO cardAppVO, AuditProcHis auditProcHis,String paId) {
		boolean flag=false;
		try {
			Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();// 获取当前执行任务
			Integer userId = Constants.getCurrendUser().getUserId();//获取登录人的id
			updateApplyStatus(Integer.parseInt(paId),result);// 修改订单的状态
			/***保存审批流程履历表 start****/
			String roleCode = (String) taskService.getVariableLocal(task.getId(), "role");
			StatusNameRef statusNameRef=null;
			if(getStatus(Integer.parseInt(paId))){//总部流程
				statusNameRef = statusNameRefService.getstatusNameRefByStatusCode(Constants.CALLINGCARD_HO+"_"+result);
			}else {
				statusNameRef = statusNameRefService.getstatusNameRefByStatusCode(Constants.CALLINGCARD_BO+"_"+result);
			}
			auditProcHis.setAppStatus(statusNameRef.getRefId());
			auditProcHis.setHandleDate(new Date());
			auditProcHis.setHandler(userId);
			auditProcHis.setHandlerRole(roleCode);
			auditProcHisService.saveAuditProcHis(auditProcHis);
			Map<String, Object> variables = new HashMap<String, Object>();
			if("zongcaizhulitongguo".equals(result)){
				Organization org = userService.findOrgByuserId(userId);
				if (org!=null) {
					String myid = org.getMyid();
					if ("JK".equals(myid)) {
						result = "jiekuanzongjianchayue";
					}
				}
			}
			variables.put("result", result);
			taskService.complete(task.getId(), variables);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取一条申请的总数
	 */
	@Override
	public Long getCount(Map<String, Object> map,Integer id) {
		StringBuffer stringBuffer = new StringBuffer("SELECT COUNT(1) ");
		stringBuffer.append(" FROM t_oa_ad_card_app card LEFT JOIN t_oa_ad_card_app_attach c ON card.APP_NO=c.APP_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_users u ON u.USER_ID = c.APPLICANT_NO ");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = c.DEPT_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_role r ON r.ROLE_ID=POSITION");
		stringBuffer.append(" WHERE o.STATUS = 'A'");
		if(StringUtils.hasText(map.get("status")+"") && map.get("status")!=null){
			stringBuffer.append(" and card.PROC_STATUS="+map.get("status"));
		}
		if(StringUtils.hasText(map.get("dateBegin")+"") && map.get("dateBegin")!=null){
			stringBuffer.append(" and card.REG_DATETIME>='"+map.get("dateBegin")+"'");
		}
		if(StringUtils.hasText(map.get("dateEnd")+"") && map.get("dateEnd")!=null){
			stringBuffer.append(" and card.REG_DATETIME<='"+map.get("dateEnd")+"'");
		}
		if(StringUtils.hasText(map.get("daetApplyBegin")+"") && map.get("daetApplyBegin")!=null){
			stringBuffer.append(" and card.APP_DATE>='"+map.get("daetApplyBegin")+"'");
		}
		if(StringUtils.hasText(map.get("daetApplyEnd")+"") && map.get("daetApplyEnd")!=null){
			stringBuffer.append(" and card.APP_DATE<='"+map.get("daetApplyEnd")+"'");
		}
		if(id==1){
			stringBuffer.append(" and REGISTRANT_NO ="+Constants.getCurrendUser().getUserId());
		}
		return publicDao.findTotalCount(stringBuffer.toString());
	}

	/**
	 * 根据部门名称获取Id
	 */
	@Override
	public Map<String, Object> getDeptNo(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptNo",publicDao.findBySQL("SELECT ORGANIZATION_ID FROM qqms.t_organization where full_name = '"+name+"'").get(0));
		return map;
	}

	/**
	 * 获取办理任务
	 */
	@Override
	public List<CardAppVO> getAcceptTask() {
		// 获取用户的id
		Integer userId = Constants.getCurrendUser().getUserId();
		// 查到该用户的所有角色list
		List<Role> roleList = userService.findRoleListByUserId(userId);
		// 角色roleCode集合
		List<String> roleIdList = new ArrayList<String>();
		if (Collections.listIsNotEmpty(roleList)) {
			for (int i = 0; i < roleList.size(); i++) {
				Role role = roleList.get(i);
				roleIdList.add(String.valueOf(role.getRoleCode()));
			}
		}
		// 流程key
		String proDefKey = Constants.CALLINGCARD_HO;
		// 查询任务List集合
		List<Task> tasks =taskService.createTaskQuery().taskAssignee(userId+"").list();
		// 返回的结果集
		List<CardAppVO> purchaseAppList = new ArrayList<CardAppVO>();
		if (Collections.listIsNotEmpty(tasks)) {
			for (int i = 0; i < tasks.size(); i++) {
				Task task = tasks.get(i);
				CardAppVO CardAppTask = findPurchaseAppByTask(task);
				if (CardAppTask != null) {
					CardAppVO CardApp = (CardAppVO) CardAppTask.clone();
					CardApp.setTaskId(task.getId());
					purchaseAppList.add(CardApp);
				}
			}
		}
		return purchaseAppList;
	}

	/**
	 * 删除未保存的名片附件
	 */
	@Override
	public boolean removeCardAccessory() {
		boolean flag=false;
		publicDao.executeHql("delete CardAppAttach c where c.appNo is null");
		flag=true;
		return flag;
	}


	@Override
	public void getDiagramResourceByCaId(HttpServletResponse response,Integer caId) {
		// 图片的文件的流
		InputStream in = null;
		try {
			String proDefKey = "";
			String imgName = "";
			if(getStatus(caId)){//总部流程
				proDefKey = Constants.CALLINGCARD_HO;//总部
				imgName = "OA_AD_CallingCard_HO";
			}else{
				proDefKey = Constants.CALLINGCARD_BO;//分部
				imgName = "OA_AD_callingCard_BO";
			}
			String businessKey = proDefKey + "." + caId;
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
	public BigDecimal updateCountByAppQty(String appNo) {
		BigDecimal amount=null;
		try {
			amount=(BigDecimal) publicDao.findBySQL("SELECT SUM(APP_QTY) FROM t_oa_ad_card_app_attach WHERE APP_NO = '"+appNo+"'").get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amount;
	}


	@Override
	public CardApp saveCardAttach(CardAppAttach cardAppAttach,String price) {
		try {
			CardApp cardApp=new CardApp();	
			CardApp cardApp2=new CardApp();
			if(cardAppAttach.getAppNo()==""){
				//保存主表信息
				//编码
				
				String  appNo=UniqueIdUtil.generate("MP");
				cardApp.setAppNo(appNo);
				//登记人
				cardApp.setRegistrantNO(Constants.getCurrendUser().getUserId());
				//登记日期
				cardApp.setRegDeteTime(new Date());
				cardApp.setAppDate(DateUtils.parse(DateUtils.getNowTime(),DateUtils.DATE_SMALL_STR));
				//流程状态
				cardApp.setProcStatus(1);
				//保存备注信息
				cardApp.setRemark(cardAppAttach.getRemark());
				//保存主表
				publicDao.save(cardApp);
				Organization organization=organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId());
				//保存附加表
				if("0".equals(organization.getDeptLevel())){//总部
					CardAppAttach cardAppAttach2=new CardAppAttach();
					//部门编号
					cardAppAttach2.setDeptNo(organization.getOrganizationId());
					//申请编号
					cardAppAttach2.setAppNo(appNo);
					//设置申请人id
					cardAppAttach2.setApplicantNo(Constants.getCurrendUser().getUserId());
					//申请人职位信息
					Role role = userService.findRoleListByUserId(Constants.getCurrendUser().getUserId()).get(0);
					cardAppAttach2.setPosition(role.getName());//职位
					Users users=userService.findUserById(Constants.getCurrendUser().getUserId());
					cardAppAttach2.setPersonalTel(users.getMobile());
					cardAppAttach2.setOfficeTel(users.getTel());
					cardAppAttach2.setEmail(users.getEmail());
					cardAppAttach2.setUnit("盒");
					cardAppAttach2.setRemark(cardAppAttach.getAppQty()+"");
					cardAppAttach2.setBranchAddr(cardAppAttach.getBranchAddr());//分公司地址
					cardAppAttach2.setComUrl(cardAppAttach.getComUrl());
					cardAppAttach2.setAppQty(cardAppAttach.getAppQty());
					cardAppAttach2.setPrice(new BigDecimal(price));
					cardAppAttach2.setSubTotalAMT(new BigDecimal(Double.parseDouble(price)*cardAppAttach.getAppQty()));
					//保存附加表
					publicDaoAtta.saveOrUpdate(cardAppAttach2);
//					if(cardAppAttach.getAppNo()==null || "".equals(cardAppAttach.getAppNo())){
//						cardApp2 = publicDao.get(CardApp.class, findCardAppNo().getCaID());
//					}else{
						cardApp2 = publicDao.get(CardApp.class, findCardAppNo(cardApp.getAppNo()).getCaID());
//					}
				}else {//分部
					String userIds=cardAppAttach.getName();
					String[] userList = userIds.split(",");
					for (String userId : userList) {
						CardAppAttach cardAppAttach2=new CardAppAttach();
						//部门编号
						cardAppAttach2.setDeptNo(cardAppAttach.getDeptNo());
						//申请编号
						cardAppAttach2.setAppNo(appNo);
						//设置申请人id
						cardAppAttach2.setApplicantNo(Integer.parseInt(userId));
						//申请人职位信息
						Role role = userService.findRoleListByUserId(Integer.parseInt(userId)).get(0);
						cardAppAttach2.setPosition(role.getName());
						Users users=userService.findUserById(Integer.parseInt(userId));
						cardAppAttach2.setPersonalTel(users.getMobile());
						cardAppAttach2.setOfficeTel(users.getTel());
						cardAppAttach2.setEmail(users.getEmail());
						cardAppAttach2.setUnit("盒");
						cardAppAttach2.setRemark(cardAppAttach.getAppQty()+"");
						cardAppAttach2.setBranchAddr(cardAppAttach.getBranchAddr());
						cardAppAttach2.setComUrl(cardAppAttach.getComUrl());
						cardAppAttach2.setAppQty(cardAppAttach.getAppQty());
						cardAppAttach2.setPrice(new BigDecimal(price));
						cardAppAttach2.setSubTotalAMT(new BigDecimal(Double.parseDouble(price)*cardAppAttach.getAppQty()));
						//保存附加表
						publicDaoAtta.saveOrUpdate(cardAppAttach2);
						cardApp2 = publicDao.get(CardApp.class, findCardAppNo(cardAppAttach2.getAppNo()).getCaID());
					}
				}
			}else{//在添加完数据的基础上继续添加数据
				String userIds=cardAppAttach.getName();
				
				if(org.apache.commons.lang3.StringUtils.isNotBlank(userIds)){//分布
					String[] userList = userIds.split(",");
					for (String userId : userList) {
						CardAppAttach cardAppAttach2=new CardAppAttach();
						if(cardAppAttach.getCaId()!=null && !"".equals(cardAppAttach.getCaId())){
							cardAppAttach2.setCaId(cardAppAttach.getCaId());
						}
						//部门编号
						cardAppAttach2.setDeptNo(cardAppAttach.getDeptNo());
						//申请编号
						cardAppAttach2.setAppNo(cardAppAttach.getAppNo());
						//设置申请人id
						cardAppAttach2.setApplicantNo(Integer.parseInt(userId));
						//申请人职位信息
						Role role = userService.findRoleListByUserId(Integer.parseInt(userId)).get(0);
						cardAppAttach2.setPosition(role.getName());
						Users users2=userService.findUserById(Integer.parseInt(userId));
						cardAppAttach2.setPersonalTel(users2.getMobile());
						cardAppAttach2.setOfficeTel(users2.getTel());
						cardAppAttach2.setEmail(users2.getEmail());
						cardAppAttach2.setUnit("盒");
						cardAppAttach2.setRemark(cardAppAttach.getAppQty()+"");
						cardAppAttach2.setBranchAddr(cardAppAttach.getBranchAddr());
						cardAppAttach2.setComUrl(cardAppAttach.getComUrl());
						cardAppAttach2.setAppQty(cardAppAttach.getAppQty());
						cardAppAttach2.setPrice(new BigDecimal(price));
						cardAppAttach2.setSubTotalAMT(new BigDecimal(Double.parseDouble(price)*cardAppAttach.getAppQty()));
						//保存附加表
						publicDaoAtta.saveOrUpdate(cardAppAttach2);
						cardApp2 = publicDao.get(CardApp.class, findCardAppNo(cardAppAttach2.getAppNo()).getCaID());
					}
				}else{//总部
					CardAppAttach cardAppAttach2=new CardAppAttach();
					if(cardAppAttach.getCaId()!=null && !"".equals(cardAppAttach.getCaId())){
						cardAppAttach2.setCaId(cardAppAttach.getCaId());
					}
					//部门编号
					cardAppAttach2.setDeptNo(organizationService.findOrganizationByUserId(Constants.getCurrendUser().getUserId()).getOrganizationId());
					//申请编号
					cardAppAttach2.setAppNo(cardAppAttach.getAppNo());
					//设置申请人id
					cardAppAttach2.setApplicantNo(Constants.getCurrendUser().getUserId());
					//申请人职位信息
					Role role = userService.findRoleListByUserId(Constants.getCurrendUser().getUserId()).get(0);
					cardAppAttach2.setPosition(role.getName());
					Users users2=userService.findUserById(Constants.getCurrendUser().getUserId());
					cardAppAttach2.setPersonalTel(users2.getMobile());
					cardAppAttach2.setOfficeTel(users2.getTel());
					cardAppAttach2.setEmail(users2.getEmail());
					cardAppAttach2.setUnit("盒");
					cardAppAttach2.setRemark(cardAppAttach.getAppQty()+"");
					cardAppAttach2.setBranchAddr(cardAppAttach.getBranchAddr());
					cardAppAttach2.setComUrl(cardAppAttach.getComUrl());
					cardAppAttach2.setAppQty(cardAppAttach.getAppQty());
					cardAppAttach2.setPrice(new BigDecimal(price));
					cardAppAttach2.setSubTotalAMT(new BigDecimal(Double.parseDouble(price)*cardAppAttach.getAppQty()));
					//保存附加表
					publicDaoAtta.saveOrUpdate(cardAppAttach2);
					cardApp2 = publicDao.get(CardApp.class, findCardAppNo(cardAppAttach2.getAppNo()).getCaID());
				}
				/*//当备注信息改变时，保存备注信息
				if(org.apache.commons.lang3.StringUtils.isNotBlank(cardAppAttach.getRemark())){
					cardApp = this.findCardAppNo(cardAppAttach.getAppNo());
					cardApp.setRemark(cardAppAttach.getRemark());
					cardApp2 = publicDao.get(CardApp.class, findCardAppNo(cardAppAttach.getAppNo()).getCaID());
					publicDao.saveOrUpdate(cardApp);
				}*/
			}
			
			List<Object> listObj = publicDaoAtta.findBySQL("select SUM(SUBTOTAL_AMT)_AMT from t_oa_ad_card_app_attach where APP_NO = '"+cardApp2.getAppNo()+"'");
			cardApp2.setTotalAMT(new BigDecimal(listObj.get(0)+""));
			BigDecimal amount = updateCountByAppQty(cardApp2.getAppNo());
			cardApp2.setAppQty(Integer.parseInt(amount+""));
			cardApp2.setRemark(amount+"");
			publicDao.saveOrUpdate(cardApp2);

			return cardApp;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public CardApp findCardAppNo(String appNo) {
		String hql="from CardApp where appNo='"+appNo+"'";
		List<CardApp> list = publicDao.find(hql);
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}


	@Override
	public Long findCardAtttotal(String appNo, String deptNo) {
		try {
			String sql="SELECT COUNT(*) FROM t_oa_ad_card_app_attach  a where a.APP_NO='"+appNo+"'  ";
			if(org.apache.commons.lang3.StringUtils.isNotBlank(deptNo)){
				sql+=" and a.DEPT_NO='"+deptNo+"' ";
			}
			sql+=" ORDER BY a.APP_NO ";
			Long totalCount = publicDaoAtta.findTotalCount(sql);
			return totalCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0L;
	}


	@Override
	public List<CardAppAttach> findCardAttList(String appNo, String deptNo,
			PageUtil pageUtil) {
		try {
			if(org.apache.commons.lang3.StringUtils.isNotBlank(appNo)){
				StringBuffer stringBuffer = new StringBuffer("SELECT a.APP_QTY '申请数量',u.USER_NAME as '姓名',o.FULL_NAME as '部门名称',a.POSITION '职位',a.CA_ID '编号',a.APP_NO '申请编号',a.PRICE '金额',a.APPLICANT_NO '登记人编号',a.BRANCH_ADDR '分公司地址',a.COM_URL '公司网址',a.remark '备注'");
				stringBuffer.append(" FROM t_oa_ad_card_app_attach a ");
				stringBuffer.append(" LEFT JOIN t_oa_ad_card_app card ON a.APP_NO = card.APP_NO");
				stringBuffer.append(" LEFT JOIN qqms.t_users u ON u.USER_ID = a.APPLICANT_NO ");
				stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = a.DEPT_NO");
				stringBuffer.append(" WHERE o.STATUS = 'A' ");
//				if(StringUtils.isNotBlank(caID) && !"null".equals(caID)){
//					String appNo=publicDao.findBySQL("SELECT APP_NO FROM t_oa_ad_card_app WHERE CA_ID="+caID).get(0)+"";
//					stringBuffer.append(" AND a.APP_NO = '"+appNo+"'");
//				}
				/*else{
					stringBuffer.append(" AND a.APP_NO IS NULL OR a.APP_NO = 'null'");
				}*/
				
				if(org.apache.commons.lang3.StringUtils.isNotBlank(appNo)){
					stringBuffer.append(" AND a.APP_NO = '"+appNo+"' ");
				}
				if(org.apache.commons.lang3.StringUtils.isNotBlank(deptNo)){
					stringBuffer.append(" AND a.DEPT_NO = "+deptNo);
				}
				stringBuffer.append("  ORDER BY a.APP_NO ");
				List<Object> list = publicDaoAtta.findBySql(stringBuffer.toString(), pageUtil);
				List<CardAppAttach> attList=new ArrayList<CardAppAttach>();
				if(Collections.listIsNotEmpty(list)){
					for (int i = 0; i < list.size(); i++) {
						Object[] obj = (Object[]) list.get(i);
						CardAppAttach cardAppAttach = new CardAppAttach();
						cardAppAttach.setName(obj[1]+"");//姓名
						cardAppAttach.setCaId(Integer.parseInt(obj[4]+""));
						cardAppAttach.setAppNo(obj[5]==null?"":String.valueOf(obj[5]));//申请编号
						cardAppAttach.setPositionName(obj[3]==null?"":String.valueOf(obj[3]));//职位
						cardAppAttach.setFullName(obj[2]==null?"":String.valueOf(obj[2]));//部门名称
						cardAppAttach.setAppQty(Integer.parseInt(obj[0]+""));//申请数量
						cardAppAttach.setPrice(new BigDecimal(obj[6]+""));
						cardAppAttach.setApplicantNo(Integer.parseInt(obj[7]+""));
						cardAppAttach.setBranchAddr(obj[8]+"");
						cardAppAttach.setComUrl(obj[9]+"");
						cardAppAttach.setRemark(obj[10]+"");
						attList.add(cardAppAttach);
					}
				}
				return attList;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean delCardApply(String ids) {
		//删除附表数据后重新计算主表的总数量
		String appNo=null;
		if(ids.contains(",")){
			appNo=publicDaoAtta.get(CardAppAttach.class,Integer.parseInt(ids.split(",")[0])).getAppNo();
		}else{
			appNo=publicDaoAtta.get(CardAppAttach.class, Integer.parseInt(ids)).getAppNo();
		}
		try {
			publicDao.executeHql("delete CardAppAttach where caId in ("+ids+")");
			String amount = publicDao.findBySQL("SELECT SUM(APP_QTY) FROM t_oa_ad_card_app_attach WHERE APP_NO ='"+appNo+"'").get(0)+"";
			publicDao.executeHql("update CardApp set appQty = "+Integer.parseInt(amount)+" where appNo='"+appNo+"'");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public String saveSubmitTask(WorkFlowTasksModel taskModel) {
		List<BigDecimal> list = publicDao.findBySQL("select TOTAL_AMT from t_oa_ad_card_app where app_No = '"+taskModel.getAppNo()+"'");
		Parameter parameter = commonService.findParameterByParmCode("amount");
		Map<String, Object> map = new HashMap<String, Object>();
		if (new BigDecimal(parameter.getParmValue()).compareTo(list.get(0))==1) {//小于500
			map.put(Constants.CONDITIONS_KEY, "XYDY");//小于等于500进财务总监
		}else{
			map.put(Constants.CONDITIONS_KEY, "DY");//大于500进总经理
		}
		taskModel.setVariables(map);;
		Map<String,Object> resultMap = workFlowTaskService.saveSubmitTask(taskModel);
		if(null != resultMap.get(Constants.STATUS_REF_ID) && org.apache.commons.lang3.StringUtils.isNotBlank(resultMap.get(Constants.STATUS_REF_ID).toString())){
			this.updateApplyStatus(Integer.valueOf(taskModel.getBusinessID()),resultMap.get(Constants.STATUS_REF_ID).toString());// 更新流程状态
		}
		return resultMap.get(Constants.RESULT_STR).toString();
	}


	@Override
	public List<ComboBoxModel> getUserByDeptNo(Integer deptNo) {
		List<ComboBoxModel> list=new ArrayList<ComboBoxModel>();
		StringBuffer stringBuffer = new StringBuffer();
		try {
			stringBuffer.append("SELECT u.USER_ID,u.USER_NAME FROM t_oa_hr_emp_roster_reg r ");
			stringBuffer.append("LEFT JOIN qqms.t_users u ");
			stringBuffer.append("ON r.USER_ID = u.USER_ID ");
			if(org.apache.commons.lang3.StringUtils.isNotBlank(deptNo+"")){
				stringBuffer.append("WHERE u.ORGANIZATION_ID = "+deptNo);
			}
			stringBuffer.append(" AND TIMESTAMPDIFF(MONTH,r.ENTRY_DATE,NOW())>=1 ")  ;//满足入职时间满一个月的用户
			List<Object[]> list2  = publicDao.findBySQL(stringBuffer.toString());
			for (Object[] item : list2) {
				//key编码,申请人id
				 String code=String.valueOf(item[0]);
				 //value，申请人名字
				 String text=String.valueOf(item[1]);
				
				ComboBoxModel boxModel=new ComboBoxModel(code, text);
				 list.add(boxModel);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}