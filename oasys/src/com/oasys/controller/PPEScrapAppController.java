package com.oasys.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

import com.oasys.model.PPEScrapApp;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.Users;
import com.oasys.service.OrganizationService;
import com.oasys.service.PPEScrapAppAttachService;
import com.oasys.service.PPEScrapAppService;
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
 * @Title: PPEScrapAppController
 * @Package com.oasys.controller
 * @Description: 固定资产报废申请Controller
 * @author lida  
 * @date 2015/9/7
 * @version V1.0   
 */
@Controller
@RequestMapping("/ppeScrapAppController")
public class PPEScrapAppController extends BaseController{
	/** 注入service. */
	@Autowired
	private PPEScrapAppService ppeScrapAppService;
	
	@Autowired
	private PPEScrapAppAttachService ppeAttachService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkFlowTaskService workTaskService;
	
	
	/** 
	 * @Title: findAllppeScrap
	 * @Description: 查询固定资产报废申请列表
	 * @param page 分页参数
	 * @param rows 分页参数
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @throws ParseException 
	 * @date 2015/9/16
	 */
	@ResponseBody
	@RequestMapping(value="/findAllppeScrap",method=RequestMethod.POST)
	public String findAllppeScrap(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, Integer page,Integer rows,@ModelAttribute("ppeScrap") PPEScrapApp ppeScrap,  BindingResult bindingResult) throws ParseException{
    	GridModel gridModel = new GridModel();
		gridModel.setRows(ppeScrapAppService.findPpeList(new PageUtil(page,rows),ppeScrap));//获取固定资产报废申请List
		gridModel.setTotal( ppeScrapAppService.findPpeListCount(ppeScrap));//获取总记录数
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	
	/** 
	 * @Title: addPPEScrapApp
	 * @Description: 新增固定资产报废申请
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author LIDA
	 * @return String
	 * @date 2015/9/16
	 */
	@ResponseBody
	@RequestMapping(value="/addOrUpdatePPEScrapApp",method=RequestMethod.POST)
	public String addPPEScrapApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("ppeScrap") PPEScrapApp ppeScrap,  BindingResult bindingResult) {
		//新增获取当前登录用户中的信息
		Users user = userService.getUserByID( Constants.getCurrendUser().getUserId());
		List<String> dataList = new ArrayList<String>();
		try {
			if(null == ppeScrap.getPsaId()){
				ppeScrap.setAppNo(UniqueIdUtil.generate("BF"));
				ppeScrap.setAppDept(user.getOrganizeId());//部门编号
				ppeScrap.setApplicantNo(user.getUserId());//申请人
				ppeScrap.setAppDate(DateUtils.getNowTime(DateUtils.DATE_FULL_STR));//申请时间
				ppeScrap.setProcStatus(Constants.PROC_INIT);//新增默认初始状态
				ppeScrapAppService.saveOrUpdatePpeEntity(ppeScrap);
			}else{
				ppeScrapAppService.saveOrUpdatePpeEntity(ppeScrap);
			}
			dataList.add(ppeScrap.getPsaId().toString());
			dataList.add(ppeScrap.getAppNo());
			OutputJson(httpServletResponse,new Json("提示","保存成功",true,dataList));
		} catch (Exception e) {
			OutputJson(httpServletResponse,new Json("提示","保存失败",false,dataList));
		}
		return null;
	}
	
	/** 
	 * @Title: delPPEScrap
	 * @Description:  删除固定资产报废申请
	 * @param appNo 申请编号
	 * @author lida 
	 * @return String
	 * @date 2015/9/18
	 */
	@ResponseBody
	@RequestMapping(value="/delPPEScrap",method=RequestMethod.POST)
	public String delPPEScrap(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="appNo",required = true) String appNo){
		boolean isSuccess = true;
		try {
			ppeScrapAppService.delPpeScrap(appNo);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}
	
	/** 
	 * @Title: toAddPPEScrapApp
	 * @Description:  跳转到固定资产报废申请新增页面
	 * @param psaID ID标识
	 * @author lida 
	 * @return String
	 * @date 2015/9/18
	 */
    @RequestMapping(value="/toAddPPEScrapApp")
	public ModelAndView toAddPPEScrapApp(HttpServletRequest httpServletRequest,@RequestParam(value="psaId",required = true) String psaID){
    	PPEScrapApp ppe = new PPEScrapApp();
    	//判断是否为新增
    	if(null == psaID || "".equals(psaID)){
    		ppe.setUserName(Constants.getCurrendUser().getAccount());
    		ppe.setOrgName(orgService.getOrgNameByID(userService.getUserByID(Constants.getCurrendUser().getUserId()).getOrganizeId()));
    		ppe.setAppDate(DateUtils.getNowTime());
    		ppe.setProcStatus("1");//设置默认流程状态为1
    	}else{
    	//修改
    		ppe = ppeScrapAppService.getPpeScrapByID(Integer.valueOf(psaID));
    		ppe.setUserName(userService.getUserByID(ppe.getApplicantNo()).getName());//翻译用户名
    		ppe.setOrgName(orgService.getOrgNameByID(userService.getUserByID(ppe.getApplicantNo()).getOrganizeId()));//翻译部门名称
    	}
    	ModelAndView mv = new ModelAndView("ad/ppeScrap/ppeScrapAdd");
    	mv.addObject("ppeScrap", ppe);
		return mv;
	}
    
    @RequestMapping(value="/toAddPPEScrapAppCwzg")
  	public ModelAndView toAddPPEScrapAppCwzg(HttpServletRequest httpServletRequest,@RequestParam(value="psaId",required = true) String psaID){
      	PPEScrapApp ppe = new PPEScrapApp();
  		ppe = ppeScrapAppService.getPpeScrapByID(Integer.valueOf(psaID));
  		ppe.setUserName(userService.getUserByID(ppe.getApplicantNo()).getName());//翻译用户名
  		ppe.setOrgName(orgService.getOrgNameByID(userService.getUserByID(ppe.getApplicantNo()).getOrganizeId()));//翻译部门名称
      	ModelAndView mv = new ModelAndView("ad/ppeScrap/ppeScrapAddCwzg");
      	mv.addObject("ppeScrap", ppe);
  		return mv;
  	}
    
	
	/**---------------------------------------流程相关---------------------------------------*/
	
	/** 
	 * @Title: startProcessPpeScrap
	 * @Description:  提交开启固定资产报废申请流程
	 * @param psaID ID标识
	 * @author lida 
	 * @return String
	 * @date 2015/9/20
	 */
	@ResponseBody
	@RequestMapping(value="/startProcessPpeScrap",method=RequestMethod.POST)
	public String startProcessPpeScrap(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestParam(value="psaId",required = true) Integer psaId){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr = ppeScrapAppService.startProcessInstance(psaId);
		} catch (Exception e) {
			System.out.println(e.toString());
			isSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse,json);
		return null;
	}

	
	/** 
	 * @Title: findPPEScrapTask
	 * @Description:  查询固定资产报废申请任务列表
	 * @param ppeScrap 页面绑定实体对象
	 * @author lida 
	 * @return String
	 * @throws ParseException 
	 * @date 2015/9/20
	 */
	@ResponseBody
	@RequestMapping(value="/findPPEScrapTask",method=RequestMethod.POST)
	public String findPPEScrapTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,  Integer page,Integer rows,@ModelAttribute("ppeScrap") PPEScrapApp ppeScrap,  BindingResult bindingResult) throws ParseException{
    	GridModel gridModel = new GridModel();
		gridModel.setRows(ppeScrapAppService.findPPEListTask(new PageUtil(page,rows),ppeScrap));//获取申请任务列表
		gridModel.setTotal(ppeScrapAppService.findPPEListTaskCount(ppeScrap));
		OutputJson2(httpServletResponse,gridModel);
		return null;
	}
	/** 
	 * @Title: signForTask
	 * @Description:  签收任务
	 * @param taskID 任务流程ID
	 * @author lida 
	 * @return String
	 * @date 2015/9/24
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
	 * @Title: saveTaskPPEScrapApp
	 * @Description:  受理申请
	 * @param ppeScrap 页面绑定实体对象
	 * @author lida 
	 * @return String
	 * @date 2015/9/24
	 */
	@ResponseBody
	@RequestMapping(value="/saveTaskPPEScrapApp",method=RequestMethod.POST)
	public String saveTaskPPEScrapApp(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel,PpeScrapAppAttach ppeAttach) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = ppeScrapAppService.saveSubmitTask(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	

	
	@ResponseBody
	@RequestMapping(value="/saveTaskPPEScrapAppBatch",method=RequestMethod.POST)
	public String saveTaskPPEScrapAppBatch(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = ppeScrapAppService.saveSubmitTaskBatch(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	/** 
	 * @Title: findAttachCount
	 * @Description:  判断该申请是否添加子项 
	 * @param String appNo 
	 * @author lida 
	 * @return String
	 * @date 2015/9/24
	 */
	@ResponseBody
	@RequestMapping(value="/findAttachCount",method=RequestMethod.POST)
	public String findAttachCount(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @RequestParam(value="appNo",required = true) String appNo) {
		List<PpeScrapAppAttach> listAttach = ppeAttachService.findPpeAttachList(appNo);
		OutputJson(httpServletResponse,listAttach==null?0:listAttach.size());
		return null;
	}
	
}
