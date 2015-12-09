package com.oasys.controller;

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

import com.oasys.model.SpecialRatifyApp;
import com.oasys.service.SpecialRatifyAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;

@Controller
@RequestMapping("/specialRatifyApp")
public class SpecialRatifyAppController extends BaseController{
	@Autowired
	private SpecialRatifyAppService specialRatifyAppService;
	@Autowired
	private WorkFlowTaskService workTaskService;
	/**
	 * 
	 * @Title: findSpecialRatifyAppList 
	 * @Description: 查询申请列表
	 * @param @param response
	 * @param @param specialRatifyApp
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月23日 上午11:36:44
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findSpecialRatifyAppList",method=RequestMethod.POST)
	public String findSpecialRatifyAppList(HttpServletResponse response,SpecialRatifyApp specialRatifyApp, Integer page,Integer rows){
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
    	List<SpecialRatifyApp> businessList = specialRatifyAppService.findSpecialRatifyAppList(pageUtil,specialRatifyApp);
    	Long rowsCount = specialRatifyAppService.findSpecialRatifyAppCount(specialRatifyApp);//获取总记录数
		gridModel.setRows(businessList);
		gridModel.setTotal(rowsCount);
		OutputJson2(response, gridModel);
		return null;
	}
	/**
	 * 
	 * @Title: saveSpecialRatifyApp 
	 * @Description: 添加/修改
	 * @param @param response
	 * @param @param request
	 * @param @param specialRatifyApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月23日 上午11:59:50
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveSpecialRatifyApp",method=RequestMethod.POST)
	public String saveSpecialRatifyApp(HttpServletResponse response,HttpServletRequest request,SpecialRatifyApp specialRatifyApp){
		boolean bl = specialRatifyAppService.saveSpecialRatifyApp(specialRatifyApp);
    	if(bl){
			OutputJson2(response,new Json("提示","保存成功!",bl,specialRatifyApp.getAppNo()));
		}else{
			OutputJson2(response,new Json("提示","出错了,保存失败!",bl));
		}
    	return null;
	}
	/**
	 * 
	 * @Title: delSpecialRatifyApp 
	 * @Description: 删除申请
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月23日 下午3:41:24
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/delSpecialRatifyApp",method=RequestMethod.POST)
	public String delSpecialRatifyApp(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,String appNo){
		boolean isSuccess = true;
		try {
			specialRatifyAppService.delSpecialRatifyApp(appNo);
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		}
		OutputJson(httpServletResponse,getMessage(isSuccess));
		return null;
	}
	
	/**
	 * 
	 * @Title: saveStartProcess 
	 * @Description: 提交申请(启动流程)
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param specialRatifyApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月24日 下午8:56:44
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveStartProcess",method=RequestMethod.POST)
	public String saveStartProcess(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, SpecialRatifyApp specialRatifyApp){
			boolean isSuccess = true;
			String resultStr = "";
			try {
				resultStr = specialRatifyAppService.saveStartProcess(specialRatifyApp);
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
	 * @Title: findSpecialRatifyAppTask 
	 * @Description: 查询待办任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param specialRatifyApp
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月24日 下午8:57:21
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findSpecialRatifyAppTask",method=RequestMethod.POST)
	public String findSpecialRatifyAppTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,  Integer page,Integer rows,SpecialRatifyApp specialRatifyApp,  BindingResult bindingResult){
    	PageUtil pageUtil = new PageUtil(page,rows);
    	List<SpecialRatifyApp> list = specialRatifyAppService.findSpecialRatifyAppTask(pageUtil,specialRatifyApp);//获取申请任务列表
    	Long listCount = specialRatifyAppService.findSpecialRatifyAppTaskCount(specialRatifyApp);//获取总记录数
    	GridModel gridModel = new GridModel();
		gridModel.setRows(list);
		gridModel.setTotal(listCount);
		OutputJson2(httpServletResponse,gridModel);
		return null;
	}
	/**
	 * 
	 * @Title: showProcessImg 
	 * @Description: 查看流程图
	 * @param @param httpServletResponse
	 * @param @param sraId
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月24日 下午8:57:41
	 * @throws
	 */
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer sraId){
		specialRatifyAppService.getDiagramResourceBySraId(httpServletResponse,sraId);
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
	 * @date 2015年11月24日 下午8:57:59
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
	 * @Title: saveTask 
	 * @Description: 办理任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param taskModel
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月24日 下午8:58:18
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTask",method=RequestMethod.POST)
	public String saveTask(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = specialRatifyAppService.saveTask(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
}
