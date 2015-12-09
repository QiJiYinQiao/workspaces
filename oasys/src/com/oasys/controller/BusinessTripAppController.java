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

import com.oasys.model.BusinessTripApp;
import com.oasys.service.BusinessTripAppService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;

@Controller
@RequestMapping("/businessTripApp")
public class BusinessTripAppController extends BaseController{
	@Autowired
	private BusinessTripAppService businessTripAppService;
	@Autowired
	private WorkFlowTaskService workTaskService;
	/**
	 * 
	 * @Title: findBusinessTripAppList 
	 * @Description:查询出差申请列表
	 * @param @param response
	 * @param @param businessTripApp
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月11日 下午4:56:12
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findBusinessTripAppList",method=RequestMethod.POST)
	public String findBusinessTripAppList(HttpServletResponse response,BusinessTripApp businessTripApp, Integer page,Integer rows){
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
    	List<BusinessTripApp> businessList = businessTripAppService.findBusinessTripAppList(pageUtil,businessTripApp);
    	Long rowsCount = businessTripAppService.findBusinessTripAppCount(businessTripApp);//获取总记录数
		gridModel.setRows(businessList);
		gridModel.setTotal(rowsCount);
		OutputJson(response, gridModel);
		return null;
	}
	/**
	 * 
	 * @Title: saveBusinessTripApp 
	 * @Description: 添加/编辑
	 * @param @param response
	 * @param @param request
	 * @param @param businessTripApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月12日 下午5:17:12
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveBusinessTripApp",method=RequestMethod.POST)
	public String saveBusinessTripApp(HttpServletResponse response,HttpServletRequest request,BusinessTripApp businessTripApp){
		boolean bl = businessTripAppService.saveBusinessTripApp(businessTripApp);
    	if(bl){
			OutputJson2(response,new Json("提示","保存成功!",bl,businessTripApp.getAppNo()));
		}else{
			OutputJson2(response,new Json("提示","出错了,保存失败!",bl));
		}
    	return null;
	}
	/**
	 * 
	 * @Title: delBusinessTripApp 
	 * @Description: 删除
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月12日 下午5:18:17
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/delBusinessTripApp",method=RequestMethod.POST)
	public String delBusinessTripApp(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,String appNo){
		boolean isSuccess = true;
		try {
			businessTripAppService.delBusinessTripApp(appNo);
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
	 * @Description: 提交申请启动流程
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param businessTripApp
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月13日 下午5:30:26
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveStartProcess",method=RequestMethod.POST)
	public String saveStartProcess(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, BusinessTripApp businessTripApp){
			boolean isSuccess = true;
			String resultStr = "";
			try {
				resultStr = businessTripAppService.saveStartProcess(businessTripApp);
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
	 * @Title: findBusinessTripAppTask 
	 * @Description: 查询待办任务
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param businessTripApp
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月13日 下午5:31:02
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findBusinessTripAppTask",method=RequestMethod.POST)
	public String findBusinessTripAppTask(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,  Integer page,Integer rows,BusinessTripApp businessTripApp,  BindingResult bindingResult){
    	PageUtil pageUtil = new PageUtil(page,rows);
    	List<BusinessTripApp> list = businessTripAppService.findBusinessTripAppTask(pageUtil,businessTripApp);//获取申请任务列表
    	Long listCount = businessTripAppService.findBusinessTripAppTaskCount(businessTripApp);//获取总记录数
    	GridModel gridModel = new GridModel();
		gridModel.setRows(list);
		gridModel.setTotal(listCount);
		OutputJson(httpServletResponse,gridModel);
		return null;
	}
	/**
	 * 
	 * @Title: showProcessImg 
	 * @Description: 查看流程图
	 * @param @param httpServletResponse
	 * @param @param btaId
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年11月13日 下午5:31:24
	 * @throws
	 */
	@RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer btaId){
		businessTripAppService.getDiagramResourceByPaId(httpServletResponse,btaId);
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
	 * @date 2015年11月13日 下午5:31:46
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
	 * @date 2015年11月13日 下午5:32:04
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveTask",method=RequestMethod.POST)
	public String saveTask(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("taskModel") WorkFlowTasksModel taskModel,  BindingResult bindingResult) {
		boolean saveSuccess = true;
		String resultStr = "";
		try {
			resultStr = businessTripAppService.saveTask(taskModel);//执行受理申请
		} catch (Exception e) {
			saveSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
		return null;
	}
	
	/*@ResponseBody
	@RequestMapping(value="/sumDays",method=RequestMethod.POST)
	public String sumDays(HttpServletRequest request,HttpServletResponse response,String beginTime,String endTime){
		BigInteger days=businessTripAppService.sumDays(endTime,beginTime);
		OutputJson(response, new Json(null,null,true,days));
		return null;
	}*/
}
