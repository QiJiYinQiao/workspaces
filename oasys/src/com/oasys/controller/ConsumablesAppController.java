package com.oasys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.ConsumablesApp;
import com.oasys.model.ConsumablesAppAttach;
import com.oasys.model.PPEScrapApp;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.Users;
import com.oasys.service.ConsumablesAppService;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.util.UniqueIdUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;

@Controller
@RequestMapping("/consumablesApp")
public class ConsumablesAppController extends BaseController {
	private static Logger logger = Logger.getLogger(ConsumablesAppController.class); 
	
	@Autowired
	private ConsumablesAppService consumablesAppService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService orgService;
	@Autowired
	private WorkFlowTaskService workTaskService;
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年9月30日 下午2:14:56
	 * @Title:findConsumablesAppList
	 * @Description:TODO（这里描述这个方法的作用） 查询低值易耗品申请列表
	 * @param httpServletResponse
	 * @param consumablesApp
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/findConsumablesAppList",method=RequestMethod.POST)
	public String findConsumablesAppList(HttpServletResponse httpServletResponse,ConsumablesApp consumablesApp, Integer page,Integer rows){
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
    	List<ConsumablesAppAttach> attachList = consumablesAppService.findConsumablesAppAttachList(pageUtil,consumablesApp);
    	Long rowsCount = consumablesAppService.findConsumablesAppCount(consumablesApp);//获取总记录数
		gridModel.setRows(attachList);
		gridModel.setTotal(rowsCount);
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	
	
    @RequestMapping(value="/toAddWindow")
	public String toAddWindow(HttpServletRequest httpServletRequest){
    	String account =  Constants.getCurrendUser().getAccount();
    	httpServletRequest.setAttribute("account", account);
		return "ad/consumablesApp/consumablesAppForm";
	}
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年9月30日 下午2:15:45
	 * @Title:saveConsumablesApp
	 * @Description:TODO（这里描述这个方法的作用）数据持久化（addOrUpdate）
	 * @param httpServletResponse
	 * @param consumablesApp
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value="/saveConsumablesApp",method=RequestMethod.POST)
	public String saveConsumablesApp(HttpServletResponse httpServletResponse,ConsumablesApp consumablesApp){
    	OutputJson2(httpServletResponse, getMessage(consumablesAppService.addOrUpdateConsumablesEntity(consumablesApp)));
		return null;
	}
	/**
	 * 
	 * @Title: toAddConsumablesApp 
	 * @Description: 跳转到添加页面
	 * @param @param httpServletRequest
	 * @param @param caId
	 * @param @return
	 * @author yuanzhongqiu
	 * @return ModelAndView
	 * @date 2015年10月12日 上午10:27:16
	 * @throws
	 */
	@RequestMapping(value="/toAddConsumablesApp")
	public ModelAndView toAddConsumablesApp(HttpServletRequest httpServletRequest,@RequestParam(value="caId",required = true) Integer caId){
		ConsumablesApp consumablesApp = new ConsumablesApp();
    	//判断是否为新增
    	if(null == caId){
    		consumablesApp.setAccount(Constants.getCurrendUser().getAccount());
    		consumablesApp.setAppDeptName(orgService.getOrgNameByID(userService.getUserByID(Constants.getCurrendUser().getUserId()).getOrganizeId()));
    		consumablesApp.setAppDate(DateUtils.getNowTime());
    		consumablesApp.setProcStatus("1");//设置默认流程状态为1
    	}else{
    	//修改
    		consumablesApp = consumablesAppService.getConsumablesAppByID(caId);
    		consumablesApp.setAccount(userService.getUserByID(consumablesApp.getApplicantNo()).getName());//翻译用户名
    		consumablesApp.setAppDeptName(orgService.getOrgNameByID(userService.getUserByID(consumablesApp.getApplicantNo()).getOrganizeId()));//翻译部门名称
    	}
    	ModelAndView mv = new ModelAndView("ad/consumablesApp/consumablesAppForm");
    	mv.addObject("consumablesApp", consumablesApp);
		return mv;
	}
	
	/**
	 * 
	 * @Title: addConsumablesApp 
	 * @Description: 添加或者更新ConsumablesApp
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param consumablesApp
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月12日 上午10:28:29
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpdateConsumablesApp",method=RequestMethod.POST)
	public String addConsumablesApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("consumablesApp") ConsumablesApp consumablesApp,  BindingResult bindingResult) {
		//新增获取当前登录用户中的信息
		ShiroUser shiroUser = Constants.getCurrendUser();
		Integer userID = shiroUser.getUserId();
		Users user = userService.getUserByID(userID);
		List<String> dataList = new ArrayList<String>();
		try {
			if(null == consumablesApp.getCaId()){
				consumablesApp.setAppNo(UniqueIdUtil.generate("YH"));
				consumablesApp.setAppDept(user.getOrganizeId());//部门编号
				consumablesApp.setApplicantNo(userID);//申请人
				consumablesApp.setAppDate(DateUtils.getNowTime(DateUtils.DATE_FULL_STR));//申请时间
				consumablesApp.setProcStatus("1");//新增默认初始状态
				consumablesAppService.addOrUpdateConsumablesEntity(consumablesApp);
			}else{
				consumablesAppService.addOrUpdateConsumablesEntity(consumablesApp);
			}
			dataList.add(consumablesApp.getCaId().toString());
			dataList.add(consumablesApp.getAppNo());
			OutputJson(httpServletResponse,new Json("提示","保存成功",true,dataList));
		} catch (Exception e) {
			OutputJson(httpServletResponse,new Json("提示","保存失败",false,dataList));
		}
		return null;
	}
	/**
	 * 
	 * @Title: delConsumablesApp 
	 * @Description: TODO
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月12日 下午3:25:59
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/delConsumablesApp",method=RequestMethod.POST)
	public String delConsumablesApp(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="appNo",required = true) String appNo){
		boolean isSuccess = true;
		try {
			consumablesAppService.delConsumablesApp(appNo);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}
	/**
	 * 
	 * @Title: startProcessConsumablesApp 
	 * @Description: 提交申请启动流程
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param consumablesApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月13日 下午2:05:17
	 * @throws
	 */
	/*@ResponseBody
	@RequestMapping(value="/startProcessConsumablesApp",method=RequestMethod.POST)
	public String startProcessConsumablesApp(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, ConsumablesApp consumablesApp){
			boolean bl= consumablesAppService.consumablesAppStartProcessInstance(consumablesApp);
			if(bl){
	    		OutputJson(httpServletResponse,new Json("提示","提交申请成功!",bl));
	    	}else{
	    		OutputJson(httpServletResponse,new Json("提示","出错了,提交申请成功失败!",bl));
	    	}
	    	return null;
	}*/
	
	@ResponseBody
	@RequestMapping(value="/saveStartProcess",method=RequestMethod.POST)
	public String saveStartProcess(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, ConsumablesApp consumablesApp){
			boolean isSuccess = true;
			String resultStr = "";
			try {
				resultStr = consumablesAppService.saveStartProcess(consumablesApp);
			} catch (Exception e) {
				System.out.println(e.toString());
				isSuccess = false;
			}
			Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
			OutputJson(httpServletResponse,json);
			return null;
	}
	
	
	/**
	 * 
	 * @Title: showProcessImg 
	 * @Description: 查看流程图
	 * @param @param httpServletResponse
	 * @param @param caId
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月13日 下午3:33:11
	 * @throws
	 */
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer caId){
		consumablesAppService.getDiagramResourceByPaId(httpServletResponse,caId);
    	return null;
    }
	/**
	 * 
	 * @Title: findConsumablesAppTask 
	 * @Description: 查询待办任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param consumablesApp
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月13日 下午4:05:53
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findConsumablesAppTask",method=RequestMethod.POST)
	public String findConsumablesAppTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,  Integer page,Integer rows,ConsumablesApp consumablesApp,  BindingResult bindingResult){
    	PageUtil pageUtil = new PageUtil(page,rows);
    	List<ConsumablesAppAttach> list = consumablesAppService.findConsumablesListTask(pageUtil,consumablesApp);//获取申请任务列表
    	Long listCount = consumablesAppService.findConsumablesListTaskCount(consumablesApp);//获取总记录数
    	GridModel gridModel = new GridModel();
		gridModel.setRows(list);
		gridModel.setTotal(listCount);
		OutputJson2(httpServletResponse,gridModel);
		return null;
	}
	/**
	 * 
	 * @Title: signForTask 
	 * @Description: 签收任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param taskID
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月13日 下午4:08:30
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/signForTask",method=RequestMethod.POST)
	public String signForTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="taskID",required = true) String taskID){
		boolean isSuccess = true;
		try{
			workTaskService.signForTask(taskID);//执行任务签收
		}catch (ActivitiTaskAlreadyClaimedException e) {
			// TODO: handle exception
			isSuccess = false;
			System.out.println("该任务已经被接收");
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}
	/**
	 * 
	 * @Title: saveTaskConsumablesApp 
	 * @Description: 办理任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param consumablesApp
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月13日 下午4:37:28
	 * @throws
	 */
	/*@ResponseBody
	@RequestMapping(value="/saveTaskConsumablesApp",method=RequestMethod.POST)
	public String saveTaskConsumablesApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse,ConsumablesApp consumablesApp,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		try {
			consumablesAppService.saveSubmitTask(consumablesApp);	//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		OutputJson(httpServletResponse, getMessage(saveSuccess));
		return null;
	}*/
	
	@ResponseBody
	@RequestMapping(value="/saveTask",method=RequestMethod.POST)
	public String saveTask(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = consumablesAppService.saveTask(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
}
