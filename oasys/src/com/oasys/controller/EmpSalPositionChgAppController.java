package com.oasys.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.oasys.model.EmpSalPositionChgApp;
import com.oasys.model.UserRole;
import com.oasys.model.Users;
import com.oasys.service.EmpSalPositionChgAppService;
import com.oasys.service.OrganizationService;
import com.oasys.service.RoleService;
import com.oasys.service.SystemCodeService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;

/**   
 * @Title: EmpSalPositionChgAppController
 * @Package com.oasys.controller
 * @Description: 员工薪资岗位变动申请Controller
 * @author lida  
 * @date 2015/9/7
 * @version V1.0   
 */
@Controller
@RequestMapping("/empSalPositionChgAppController")
public class EmpSalPositionChgAppController extends BaseController{
	/** 注入service. */
	@Autowired
	private EmpSalPositionChgAppService empSalAppService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkFlowTaskService workTaskService;
	
	@Autowired
	private SystemCodeService sysCodeService;
	
	@Autowired
	private RoleService roleService;
	
	/** 
	 * @Title: findAllEmpSalApp
	 * @Description: 查询员工薪资岗位变动申请列表
	 * @param page 分页参数
	 * @param rows 分页参数
	 * @param empSal 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @throws ParseException 
	 * @date 2015/10/26
	 */
	@ResponseBody
	@RequestMapping(value="/findAllEmpSalApp",method=RequestMethod.POST)
	public String findAllEmpSalApp(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, Integer page,Integer rows,@ModelAttribute("empSal") EmpSalPositionChgApp empSal,  BindingResult bindingResult) throws ParseException{
    	GridModel gridModel = new GridModel();
		gridModel.setRows(empSalAppService.findEmpSalAppList(new PageUtil(page,rows),empSal));//获取员工薪资岗位变动申请List
		gridModel.setTotal( empSalAppService.findEmpSalAppListCount(empSal));//获取总记录数
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	
	/** 
	 * @Title: addEmpSalPositionChgApp
	 * @Description: 新增员工薪资岗位变动申请
	 * @param empSal 页面绑定实体对象参数
	 * @author LIDA
	 * @return String
	 * @date 2015/10/26
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpdateEmpSalPositionChgApp",method=RequestMethod.POST)
	public String addEmpSalPositionChgApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("empSal") EmpSalPositionChgApp empSal,  BindingResult bindingResult) {
		//新增获取当前登录用户中的信息
		Users user = userService.getUserByID( Constants.getCurrendUser().getUserId());
		List<String> dataList = new ArrayList<String>();
		try {
			if(null == empSal.getEfaId()){
				empSal.setAppNo(UniqueIdUtil.generate("TG"));
				empSal.setApplicantNo(user.getUserId());//申请人
				empSal.setAppDate(DateUtils.getNowTime(DateUtils.DATE_FULL_STR));//申请时间
				empSal.setProcStatus("1");//新增默认初始状态
			}
			empSalAppService.saveOrUpdateEmpSalAppEntity(empSal);
			OutputJson(httpServletResponse,new Json("提示","保存成功",true,dataList));
		} catch (Exception e) {
			OutputJson(httpServletResponse,new Json("提示","保存失败",false,dataList));
		}
		return null;
	}
	
	/** 
	 * @Title: delEmpSal
	 * @Description:  删除员工薪资岗位变动申请
	 * @param appNo 申请编号
	 * @author lida 
	 * @return String
	 * @date 2015/10/26
	 */
	@ResponseBody
	@RequestMapping(value="/delEmpSal",method=RequestMethod.POST)
	public String delEmpSal(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="appNo",required = true) String appNo){
		boolean isSuccess = true;
		try {
			empSalAppService.delEmpSalAppScrap(appNo);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}
	
	/** 
	 * @Title: toAddEmpSalPositionChgApp
	 * @Description:  跳转到员工薪资岗位变动申请新增页面
	 * @param efaId ID标识
	 * @author lida 
	 * @return String
	 * @date 2015/10/26
	 */
    @RequestMapping(value="/toAddEmpSalPositionChgApp")
	public ModelAndView toAddEmpSalPositionChgApp(HttpServletRequest httpServletRequest,@RequestParam(value="efaId",required = true) String efaId){
    	EmpSalPositionChgApp empSal = new EmpSalPositionChgApp();
    	Users user;
    	Map<String,String> roleMap = new HashMap<String, String>();
    	//判断是否为新增
    	if(null == efaId || "".equals(efaId)){
    		user = userService.getUserByID(Constants.getCurrendUser().getUserId());
    		roleMap = getRoleListStr(user);
//    		empSal.setAppNo(user.getUserId().toString());
    		empSal.setUserName(Constants.getCurrendUser().getAccount());
    		empSal.setAppDate(DateUtils.getNowTime());
    		empSal.setProcStatus("1");//设置默认流程状态为1
    		empSal.setCurDeptNo(orgService.findOrganizationByUserId(user.getUserId()).getOrganizationId());
    		empSal.setCurDeptName(orgService.findOrganizationByUserId(user.getUserId()).getFullName());//当前部门
    		empSal.setCurPosition(roleMap.get("roleID"));//当前职位ID
    		empSal.setCurPositionStr(roleMap.get("roleName"));//当前职位名称
    	}else{
    	//修改
    		empSal = empSalAppService.getEmpSalPositionByID(Integer.valueOf(efaId));
//    		user = userService.getUserByID(Integer.valueOf(efaId));
//    		roleMap = getRoleListStr(user);
//    		empSal.setCurPosition(roleMap.get("roleID"));
//    		empSal.setCurPositionStr(roleMap.get("roleName"));
    		empSal.setCurPositionStr(roleService.getRoleStrByIDs(empSal.getCurPosition(), ","));
    		empSal.setAftPositionStr(roleService.getRoleStrByIDs(empSal.getAftPosition(), ","));
    		empSal.setChgTypeName(sysCodeService.findSystemName(Constants.CHG_TYPE, empSal.getChgType()));
    		empSal.setSalChgTypeName(sysCodeService.findSystemName(Constants.SAL_CHG_TYPE, empSal.getSalChgMode()));
			empSal.setChgResonName(sysCodeService.findSystemName(Constants.CHG_RESON, empSal.getChgReson()));
    	}
    	ModelAndView mv = new ModelAndView("pd/empSalPositionChgApp/empSalAppAdd");
    	mv.addObject("empSal", empSal);
		return mv;
	}
    
    protected Map<String,String> getRoleListStr(Users user) {
		String roleName = "";
		String roleID="";
		Map<String,String> map = new HashMap<String, String>();
		for (UserRole userRole : user.getUserRoles()) {
			roleID +=userRole.getRole().getRoleId()+",";
			roleName +=userRole.getRole().getName()+"   ";
		}
		roleID = roleID !="" ? roleID.substring(0, roleID.length()-1) : roleID;
		roleName = roleName !="" ? roleName.substring(0, roleName.length()-1) : roleName;
		map.put("roleID", roleID);
		map.put("roleName", roleName);
		return map;
	}
	
	/**---------------------------------------流程相关---------------------------------------*/
	
	/** 
	 * @Title: startProcessPpeScrap
	 * @Description:  提交开启员工薪资岗位变动申请流程
	 * @param efaId ID标识
	 * @author lida 
	 * @return String
	 * @date 2015/10/26
	 */
	@ResponseBody
	@RequestMapping(value="/startProcessEmpSal",method=RequestMethod.POST)
	public String startProcessEmpSal(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="efaId",required = true) Integer efaId){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr = empSalAppService.empSalPositionStartProcessInstance(efaId);
		} catch (Exception e) {
			System.out.println(e.toString());
			isSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse,json);
		return null;
	}

	
	/** 
	 * @Title: findEmpSalTask
	 * @Description:  查询员工薪资岗位变动申请任务列表
	 * @param empSal 页面绑定实体对象
	 * @author lida 
	 * @return String
	 * @throws ParseException 
	 * @date 2015/10/26
	 */
	@ResponseBody
	@RequestMapping(value="/findEmpSalTask",method=RequestMethod.POST)
	public String findEmpSalTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,  Integer page,Integer rows,@ModelAttribute("empSal") EmpSalPositionChgApp empSal,  BindingResult bindingResult) throws ParseException{
    	GridModel gridModel = new GridModel();
		gridModel.setRows(empSalAppService.findEmpSalPositionTask(new PageUtil(page,rows),empSal));//获取申请任务列表
		gridModel.setTotal(empSalAppService.findEmpSalPositionTaskCount(empSal));
		OutputJson2(httpServletResponse,gridModel);
		return null;
	}
	
	/** 
	 * @Title: signForTask
	 * @Description:  签收任务
	 * @param taskID 任务流程ID
	 * @author lida 
	 * @return String
	 * @date 2015/10/26
	 */
	@ResponseBody
	@RequestMapping(value="/signForTask",method=RequestMethod.POST)
	public String signForTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="taskID",required = true) String taskID){
		boolean isSuccess = true;
		try{
			workTaskService.signForTask(taskID);//执行任务签收
		}catch (ActivitiTaskAlreadyClaimedException e) {
			isSuccess = false;
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}

	
	/** 
	 * @Title: saveTaskEmpSalPositionChgApp
	 * @Description:  受理申请
	 * @param empSal 页面绑定实体对象
	 * @author lida 
	 * @return String
	 * @date 2015/10/26
	 */
	@ResponseBody
	@RequestMapping(value="/saveTaskEmpSalPositionChgApp",method=RequestMethod.POST)
	public String saveTaskEmpSalPositionChgApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = empSalAppService.saveSubmitTask(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/getProcDefKey",method=RequestMethod.POST)
	public String getProcDefKey(HttpServletResponse httpServletResponse,Integer busID) {
		EmpSalPositionChgApp empSal = empSalAppService.getEmpSalPositionByID(busID);
		Map<String,String> map = empSalAppService.getProcDefKey(empSal);
		String taskID = workTaskService.getTaskIDByBusinessKey(busID.toString(),map.get("procDefKey").toString());
		JSONObject jo = new JSONObject();
		jo.put("taskID",taskID);
		jo.put("procDefKeyDes", map.get("procDefKeyRes").toString());
		OutputJson(httpServletResponse,jo);
		return null;
	}
	
}
