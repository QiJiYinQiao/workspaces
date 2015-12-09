package com.oasys.serviceImpl;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.oasys.model.LeaveApp;
import com.oasys.model.OvertimeApp;
import com.oasys.model.Role;
import com.oasys.model.StatusNameRef;
import com.oasys.service.AuditProcHisService;
import com.oasys.service.LeaveAppService;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.service.StatusNameRefService;
import com.oasys.service.SystemCodeService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
import com.oasys.util.Constants;
import com.oasys.util.DayUtils;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.UserRoleModel;

@Service("leaveAppService")
@SuppressWarnings("unchecked")
public class LeaveServiceImpl  extends WorkFlowBaseServiceImpl  implements LeaveAppService
{

	
	@Autowired
	private WorkFlowTaskService workFlowTaskService;
	
	@Autowired
	private PublicDao<LeaveApp> leaveAppDao;
	
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private UserService userService;
	@Autowired
	private  StatusNameRefService  statusNameRefService;
	@Autowired
	private AuditProcHisService auditProcHisService;
	@Autowired
	private RoleService roleService;
	/**
	 * 加班申请
	 */
	@Autowired
	private PublicDao<OvertimeApp> overTimeDao;
	
	/**
	 * 系统字典
	 */
	@Autowired
	private SystemCodeService systemCodeService;
	
	@Override
	public List<LeaveApp> findLeaveAppList(LeaveApp leaveApp,PageUtil pageUtil) {
		
		try {
			Integer userId = Constants.getCurrendUser().getUserId();
			
			String hql="from LeaveApp where 1=1 ";
			if(userId!=1){
				hql+=" and applicantNo = "+userId;
			}
			
			if(StringUtils.isNotBlank(leaveApp.getAppNo())){
				hql+=" and appNo='"+leaveApp.getAppNo()+"' ";
			}else{
				if(StringUtils.isNotBlank(leaveApp.getAppDateS())){
					hql+=" and appDate >='" + leaveApp.getAppDateS()+"' ";  //申请开始日期
				}
				if(StringUtils.isNotBlank(leaveApp.getAppDateE())){
					hql+=" and appDate <='" + leaveApp.getAppDateE()+"' ";  //申请结束日期
				}
				if(StringUtils.isNotBlank(leaveApp.getProcStatus())){
					hql+=" and procStatus='"+leaveApp.getProcStatus()+"' ";
				}
			}
			hql+=" order by leaId desc ";
			//执行sql语句
			List<LeaveApp> leaveAppList = leaveAppDao.find(hql, pageUtil);
			for (LeaveApp leave : leaveAppList) {
				//申请人姓名
				leave.setApplicantName(userService.findUserById(leave.getApplicantNo()).getName());
				//申请人部门
				leave.setFullName(organizationService.getOrgNameByID(leave.getDeptNo()));
				//代理人姓名
				leave.setAgentName(userService.findUserById(leave.getAgentNo()).getName());
				
				//判断请假类型，当类型为其他时填写其他类型、
				if(StringUtils.equals(leave.getLeType(), "9")){
					leave.setLeName(leave.getLeTypeOther());
				}else{
					leave.setLeName(systemCodeService.findSystemName(Constants.LEAVEAPP_TYPE, leave.getLeType()));
				}
			}
			return leaveAppList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Long getTotal(LeaveApp leaveApp) {
		try {
			Integer userId = Constants.getCurrendUser().getUserId();
			
			String hql="SELECT COUNT(*) FROM  t_oa_pd_leave_app WHERE 1=1 ";
			if(userId!=1){
				hql+=" AND APPLICANT_NO = "+userId;
			}
			
			if(StringUtils.isNotBlank(leaveApp.getAppNo())){
				hql+=" AND APP_NO='"+leaveApp.getAppNo()+"' ";
			}else{
				if(StringUtils.isNotBlank(leaveApp.getAppDateS())){
					hql+=" AND APP_DATE >='" + leaveApp.getAppDateS()+"' ";  //申请开始日期
				}
				if(StringUtils.isNotBlank(leaveApp.getAppDateE())){
					hql+=" AND APP_DATE <='" + leaveApp.getAppDateE()+"' ";  //申请结束日期
				}
				if(StringUtils.isNotBlank(leaveApp.getProcStatus())){
					hql+=" AND PROC_STATUS='"+leaveApp.getProcStatus()+"' ";
				}
			}
			Long count = leaveAppDao.findTotalCount(hql);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0L;
	}
	@Override
	public LeaveApp saveOrUpdLeaveApp(LeaveApp leaveApp) {
		try {
			//校验时间是否相等，结束时间是否小于开始时间
			if(leaveApp.getPlanBgDtime()==null || leaveApp.getPlanEdDtime()==null){
				return null;
			}else if(leaveApp.getPlanBgDtime().getTime()>=leaveApp.getPlanEdDtime().getTime()){
				return null;
			}
			if(leaveApp.getRealBgDtime()!=null && leaveApp.getRealEdDtime()!=null){
				if(leaveApp.getRealBgDtime().getTime()>=leaveApp.getRealEdDtime().getTime()){
					return null;
				}
			}
			
			
			
			//判断申请时新增还是修改
			if(leaveApp.getLeaId()==null){
				//新增
				//申请编号
				String appNo=UniqueIdUtil.generate("QJ");
				leaveApp.setAppNo(appNo);
				//申请人
				Integer userId=Constants.getCurrendUser().getUserId();
				leaveApp.setApplicantNo(userId);
				//部门
				leaveApp.setDeptNo(userService.getUserByID(userId).getOrganizeId());
				//流程状态
				leaveApp.setProcStatus("1");
				//实际请假开始时间
				if(leaveApp.getRealBgDtime()==null){
					leaveApp.setRealBgDtime(leaveApp.getPlanBgDtime());
				}
				//实际请假结束时间
				if(leaveApp.getRealEdDtime()==null){
					leaveApp.setRealEdDtime(leaveApp.getPlanEdDtime());
				}
				leaveApp.setPlanLeDays(getLeaveDate(leaveApp.getPlanBgDtime(),leaveApp.getPlanEdDtime()));
				//实际请假时间
				leaveApp.setRealLeDays(getLeaveDate(leaveApp.getRealBgDtime(), leaveApp.getRealEdDtime()));
				
				
				
				
				//保存申请
				leaveAppDao.save(leaveApp);
			}else{
				LeaveApp leave = leaveAppDao.get(LeaveApp.class, leaveApp.getLeaId());
				leave.setPosition(leaveApp.getPosition());//职位
				leave.setAgentNo(leaveApp.getAgentNo());//代理人
				leave.setLeType(leaveApp.getLeType());//休假类型
				if(leaveApp.getLeType().equals("9")){
					leave.setLeTypeOther(leaveApp.getLeTypeOther());//其他休假类型
				}else{
					leave.setLeTypeOther("");
				}
				leave.setLeReason(leaveApp.getLeReason());//事由
				leave.setPlanBgDtime(leaveApp.getPlanBgDtime());//请假开始i时间
				leave.setPlanEdDtime(leaveApp.getPlanEdDtime());//请假结束时间
				leave.setPlanLeDays(getLeaveDate(leaveApp.getPlanBgDtime(),leaveApp.getPlanEdDtime()));
				leave.setRealBgDtime(leaveApp.getRealBgDtime());//时间请假开始时间
				leave.setRealEdDtime(leaveApp.getRealEdDtime());//实际请假结束时间
				leave.setRealLeDays(getLeaveDate(leaveApp.getRealBgDtime(), leaveApp.getRealEdDtime()));
				leave.setRemark(leaveApp.getRemark());//备注
				leaveAppDao.saveOrUpdate(leave);
				return leave;
			}
			return leaveApp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteLeaveApp(Integer leaId) {
		try {
			String hql="delete from LeaveApp where leaId="+leaId;
			leaveAppDao.executeHql(hql);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void getDiagramResourceByPaId(HttpServletResponse response,
			Integer leaId) {
		// 图片的文件的流
				InputStream in = null;
				try {
					String proDefKey = this.findProDefKey(leaId);
					String imgName =this.findimgName(leaId);
					
					String businessKey = proDefKey + "." + leaId;
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


	@Override
	public LeaveApp findLeaveAppbyId(Integer leaId) {
		LeaveApp leaveApp = leaveAppDao.get(LeaveApp.class, leaId);
		return leaveApp;
	}


	@Override
	public void upLeaveProcStatus(Integer leaId,String procKey) {
		LeaveApp leaveApp = leaveAppDao.get(LeaveApp.class, leaId);
		leaveApp.setProcStatus(procKey);
		this.saveOrUpdLeaveApp(leaveApp);
	}


	@Override
	public void upLeaveAppStatus(Integer leaId, String appStatus) {
		LeaveApp leaveApp = leaveAppDao.get(LeaveApp.class, leaId);
		leaveApp.setAppStatus(appStatus);
		this.saveOrUpdLeaveApp(leaveApp);
	}


	@Override
	public String findProDefKey(Integer leaId) {
		LeaveApp leaveApp = leaveAppDao.get(LeaveApp.class, leaId);
		List<UserRoleModel> rolesList = userService.findUsersRolesList(leaveApp.getApplicantNo());
		String proDefKey="";
		if(rolesList!=null && rolesList.size()>0){
			Integer roleId = rolesList.get(0).getRoleId();
			Role role = roleService.findRoleByRoleId(roleId);
			//查询普通职员的
			List<Role> list = roleService.findRoleListsByRoleCode(roleService.findRoleByCode("BuMenZhuGuan").getRoleId());
			for (Role role2 : list) {
				if(role2.getRoleCode().equals(role.getRoleCode())){
					return Constants.LEAVEAPP_Staff;//职员
				}
			}
			
			if(role.getRoleCode().equals("KeHuJingLi")){
				proDefKey= Constants.LEAVEAPP_Cli_TEAM;//客户经理
			}else if(role.getRoleCode().equals("TuanDuiJingLi")){
				proDefKey= Constants.LEAVEAPP_Cli_TEAM;//团队经理
			}else if(role.getRoleCode().equals("YingYeBuJingLi")){
				proDefKey= Constants.LEAVEAPP_Sales;//营业部经理
			}else if(role.getRoleCode().equals("ChengShiJingLi")){
				proDefKey= Constants.LEAVEAPP_City_Area;//城市经理
			}else if(role.getRoleCode().equals("QuYuJingLi")){
				proDefKey= Constants.LEAVEAPP_City_Area;//区域经理
			}else if(role.getRoleCode().equals("BuMenZhuGuan")){
				proDefKey= Constants.LEAVEAPP_Dep;//部门主管
			}else {
				proDefKey=Constants.LEAVEAPP_Woman;//部门经理及以上职位的请假申请，除婚产假
			}
		}
		return proDefKey;
	}


	@Override
	public String findimgName(Integer leaId) {
		LeaveApp leaveApp = leaveAppDao.get(LeaveApp.class, leaId);
		List<UserRoleModel> rolesList = userService.findUsersRolesList(leaveApp.getApplicantNo());
		String image="";
		if(rolesList!=null && rolesList.size()>0){
			Integer roleId = rolesList.get(0).getRoleId();
			Role role = roleService.findRoleByRoleId(roleId);
			//查询普通员
			List<Role> list = roleService.findRoleListsByRoleCode(roleService.findRoleByCode("BuMenZhuGuan").getRoleId());
			for (Role role2 : list) {
				if(role2.getRoleCode().equals(role.getRoleCode())){
					return "OA_PD_LeaveApp_Staff";//职员
				}
			}
			
			if(role.getRoleCode().equals("KeHuJingLi")){
				image= "OA_PD_LeaveApp_Cli";
			}else if(role.getRoleCode().equals("TuanDuiJingLi")){
				image= "OA_PD_LeaveApp_Cli";
			}else if(role.getRoleCode().equals("YingYeBuJingLi")){
				image= "OA_PD_LeaveApp_Sales";
			}else if(role.getRoleCode().equals("ChengShiJingLi")){
				image= "OA_PD_LeaveApp_City_Area";
			}else if(role.getRoleCode().equals("QuYuJingLi")){
				image= "OA_PD_LeaveApp_City_Area";
			}else if(role.getRoleCode().equals("BuMenZhuGuan")){
				image= "OA_PD_LeaveApp_Dep";//部门主管
			}else{
				image= "OA_PD_LeaveApp_Woman";//部门经理以上职位的休假申请除婚产假
			}
		}
		return image;
	}


	public BigDecimal  getLeaveDate(Date beginDate,Date endDate ){
		try {
			double leaveDay=0;
			Long dateL=beginDate.getTime()-endDate.getTime();
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String beDate=format.format(beginDate);
			String edDate=format.format(endDate);
			DayUtils dayUtils=new DayUtils();
			
			//判断开始时间和结束时间是否是同一天
			if(beDate.equals(edDate)){
				//判断是否是上午半天
				if(endDate.getTime()<dateFormat.parse(edDate+" 12:00:00").getTime() || beginDate.getTime()>dateFormat.parse(edDate+" 12:00:00").getTime()){
					leaveDay=0.5;
				}else{
					//判断开始时间是上午，结束时间是下午
					if((endDate.getTime()-beginDate.getTime()-1000*60*60)>1000*60*60*5){
						//当请假时间大于5小时时
						leaveDay=1.0;
					}else{
						//请假时间小于等于5小时
						leaveDay=0.5;
					};
				}
			}else{
				//开始时间和结束时间不是同一天
				List<Date> dateList = dayUtils.getDatesBetweenTwoDate(beginDate, endDate);
				for (Date date : dateList) {
					//判断改时间是否是工作日
					if(dayUtils.getDate(date)){
						leaveDay++;
					}
				}
				//单独判断开始日期和结束日期，这两个时间段内的按天数计算
				if(beginDate.getTime()<dateFormat.parse(beDate+" 12:00:00").getTime()){
					//判断开始日期是上午
					leaveDay+=1;
				}else if(beginDate.getTime()!=dateFormat.parse(beDate+" 18:00:00").getTime()){
					//判断开始日期是下午，小于等于5小时，算半天
					leaveDay+=0.5;
				}
				//单独判断结束日期
				if(endDate.getTime()<=dateFormat.parse(edDate+" 15:00:00").getTime() && endDate.getTime()!=dateFormat.parse(edDate+" 9:00:00").getTime()){
					//当结束时间小于等于5小时，算半天
					leaveDay+=0.5;
				}else if(endDate.getTime()>dateFormat.parse(edDate+" 15:00:00").getTime()){
					//结束时间大于5小时
					leaveDay+=1;
				}
				//对开式日期和结束日期进行了单独处理，因此时间减去2
				long time = beginDate.getTime()-format.parse(beDate).getTime();
				long time2=endDate.getTime()-format.parse(edDate).getTime();
				if(time >=time2){
					leaveDay=leaveDay-2;
				}else{
					leaveDay=leaveDay-3;
				}
			}
			return new BigDecimal(leaveDay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new BigDecimal(0);
	}


	@Override
	public boolean getValDate(String date) {
		try {
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			return DayUtils.getDate(dateFormat.parse(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}


	@Override
	public StatusNameRef findAppStatusByID(String appStatusID) {
		return statusNameRefService.getStatusNameRefByStatusID(appStatusID);
	}


	@Override
	public List<ComboBoxModel> findOrgUserList() {
		try {
			Integer userId=Constants.getCurrendUser().getUserId();
			Integer iorganizeId=userService.findOrgByuserId(userId).getOrganizationId();
			String organizeId=String.valueOf(iorganizeId);
			List<ComboBoxModel> userList = userService.findOrgUserList(organizeId);
			return userList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//0：通过，1.1：表示没有可调配天数或出现异常，
	@Override
	public double getDatemistiming(String beDate, String edDate) {
		try {
			//格式化开始时间和结束时间
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance(); //得到日历
			Date bDate = format.parse(beDate);
			Date eDate = format.parse(edDate);
			
			//前三个月从开始日期算起
			calendar.setTime(sdf.parse(beDate));//把当前时间赋给日历
			calendar.add(calendar.MONTH, -3); //设置为前3月
			Date date=calendar.getTime(); //得到前3月的时间
			String EndDate = sdf.format(sdf.parse(beDate))+" 24:00:00"; //格式化当天
			String StartDate = sdf.format(date)+" 00:00:00"; //格式化前3月的时间
			
			//判断3个月之前是和当天是否是同一年
			if(!EndDate.split("-")[0].equals(StartDate.split("-")[0])){
				StartDate=EndDate.split("-")[0]+"-1-1 00:00:00";//不在同一年时，开始时间是1月1日
			}
			
			//查询加班申请中可调休天数
			String sql="SELECT SUM(REAL_ALLOCATE_DAYS) FROM t_oa_pd_overtime_app WHERE REAL_BG_DTIME>='"+StartDate+"' AND REAL_BG_DTIME<='"+EndDate+"' AND PROC_STATUS='3' AND APPLICANT_NO="+Constants.getCurrendUser().getUserId();
			BigDecimal allDay = overTimeDao.getNumber(sql);//前三个月的可调配天数
			if(allDay==null || allDay.doubleValue()==0){
				return 1.1;
			}
			BigDecimal leaveDate = this.getLeaveDate(bDate, eDate);//请假天数
			if(allDay.doubleValue()<leaveDate.doubleValue()){
				return allDay.doubleValue();
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1.1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 1.1;
		}
		return 0;
	}

	/**
	 * 6:正常保存，7：工作时间 不满一年，8：不能连续请年假，9：系统错误
	 */
	@Override
	public double getAnnualLeaDays(String beDate, String edDate) {
		try {
			//满一年者一年最多请假5天
			double leaDay=5;
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			
			//获取当前时间
			Integer userId=Constants.getCurrendUser().getUserId();
			Date takejobDate = userService.findUserById(userId).getTakejobDate();
			
			//判断是否满一年
			Long dateTime=dateFormat.parse(beDate).getTime()-takejobDate.getTime();
			Long years=dateTime/1000/60/60/24/365;
			if(years==0L){
				return 7;
			}
			
			//获取当前年
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(dateFormat.parse(beDate));
			int year = calendar.get(Calendar.YEAR);
			String beYear=year+":1:1";//每年的1月1日
			String edYear=year+":12:31";//每年的12月31日

			//判断同一年中是否请过假
			String hql="from LeaveApp where realBgDtime>='"+beYear+"' and realBgDtime<='"+edYear+"' and leType='7' and procStatus in('2','3') and applicantNo="+userId+" order by leaId desc" ;
			List<LeaveApp> leaveList = leaveAppDao.find(hql);
			if(leaveList!=null && leaveList.size()>0){
				//判断年假中间是否相隔一天
				LeaveApp leave = leaveList.get(0);
				calendar.setTime(leave.getRealEdDtime());
				calendar.add(calendar.DATE, 1);
				Date date = calendar.getTime();
				String tomDate = dateFormat.format(date);//申请的明天
				String reDate = dateFormat.format(leave.getRealEdDtime());//请假结束当天
				if(tomDate.equals(beDate.split(" ")[0]) || reDate.equals(beDate.split(" ")[0])){
					//判断年假不属于当年最后一次年假的结束时间，同时也不属于当天时间
					return 8;
				}
				
				//获取剩余年假天数
				for (LeaveApp leaveApp : leaveList) {
					leaDay-=leaveApp.getRealLeDays().doubleValue();
				}
			}
			
			//请假天数,年假最多一次性请3天
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date bDate = format.parse(beDate);
			Date eDate = format.parse(edDate);
			BigDecimal days = this.getLeaveDate(bDate, eDate);
			
			if(leaDay==0){
				//当年的年假累计完时，则不能请年假
				return 0;
			}else if(days.doubleValue()>3 && leaDay>=3){
				//当年请假天数大于3天时，年假最多一次性请3天假
				return 3;
			}else if(days.doubleValue()>leaDay && leaDay<=3){
				//当年假不足时，
				return leaDay;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 9;
		}
		//请假通过
		return 6;
	}

	
	@Override
	public void updateFallsTime(Integer leaId) {
		try {
			Double days=new Double(0);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
			Calendar calendar = Calendar.getInstance(); //得到日历
			//获取请假时间
			LeaveApp leaveApp = leaveAppDao.get(LeaveApp.class, leaId);
			Double realLeDays = leaveApp.getRealLeDays().doubleValue();

			//判断调休申请跟确认销假时间是否在同一年
			calendar.setTime(new Date());
			int newYear = calendar.get(Calendar.YEAR);//获取当前年份
			calendar.setTime(leaveApp.getRealBgDtime());
			int appYear = calendar.get(Calendar.YEAR);//获取实际开始休假时间
			
			//判断申请完成后时间和申请时间是否在同一年内，在同一年内需要减去对应的调休时间，不在同一年则不需要操作
			if(newYear==appYear){
				calendar.setTime(leaveApp.getRealBgDtime());//把当前时间赋给日历
				calendar.add(calendar.MONTH, -3); //设置为前3月
				Date date=calendar.getTime(); //得到前3月的时间
				String EndDate = sdf.format(leaveApp.getRealBgDtime())+" 24:00:00"; //格式化前3月的时间
				String StartDate = sdf.format(date)+" 00:00:00"; //格式化当天
				String hql="from OvertimeApp where realBgDtime>='"+StartDate+"' and realBgDtime<='"+EndDate+"' and procStatus='3' and applicantNo="+leaveApp.getApplicantNo()+" order by oveId asc";
				List<OvertimeApp> list = overTimeDao.find(hql);
				for (OvertimeApp overtimeApp : list) {
					days+=overtimeApp.getRealAllocateDays();
					if(realLeDays.doubleValue()>days.doubleValue()){
						//加班申请可调配天数清零
						overtimeApp.setRealAllocateDays(0.0);
						overTimeDao.saveOrUpdate(overtimeApp);
					}else if(days.doubleValue()>realLeDays.doubleValue()){
						//如果加班的时间稍微大于休假的时间
						Double dr= days-realLeDays;
						overtimeApp.setRealAllocateDays(dr);
						overTimeDao.saveOrUpdate(overtimeApp);
						break;
					}else if(days.doubleValue()==realLeDays.doubleValue()){
						//修加时间正好够加班时间
						overtimeApp.setRealAllocateDays(0.0);
						overTimeDao.saveOrUpdate(overtimeApp);
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	

	


		



	
	
	
	
}
