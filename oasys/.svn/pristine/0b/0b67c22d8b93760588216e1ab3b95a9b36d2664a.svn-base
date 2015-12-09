package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.AuditProcHis;
import com.oasys.model.EmpForgetPluginApp;
import com.oasys.model.VO.EmpForgetPluginAppModel;
import com.oasys.service.EmpForgetPluginAppService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 费用申请Controller
 * @ClassName: EmpForgetPluginAppController 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:18:55
 */
@Controller
@RequestMapping("/empForgetPluginAppController")
public class EmpForgetPluginAppController extends BaseController{

	@Autowired
	private EmpForgetPluginAppService empForgetPluginAppService;
	
	/**
	 * 新增
	 * @Title: saveEmpForgetPluginApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param empForgetPluginApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午1:24:27
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveEmpForgetPluginApp",method=RequestMethod.POST)
	public String saveEmpForgetPluginApp(HttpServletResponse httpServletResponse,EmpForgetPluginApp empForgetPluginApp){
		boolean bl = empForgetPluginAppService.saveEmpForgetPluginApp(empForgetPluginApp);
		if (bl) {
			OutputJson(httpServletResponse, new DataModel("提示","更新数据成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,保存失败!",bl));
		}
		return null;
	}
	/**
	 * 查询列表
	 * @Title: findEmpForgetPluginAppList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午3:19:01
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findEmpForgetPluginAppList",method=RequestMethod.POST)
	public String findEmpForgetPluginAppList(HttpServletResponse httpServletResponse,Integer page,Integer rows,EmpForgetPluginAppModel empForgetPluginAppModel){
		PageUtil pageUtil = new PageUtil(page, rows);
		GridModel gridModel = new GridModel();
		gridModel.setRows(empForgetPluginAppService.findEmpForgetPluginAppList(pageUtil,empForgetPluginAppModel));
		gridModel.setTotal(empForgetPluginAppService.countFindEmpForgetPluginAppList(empForgetPluginAppModel));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	/**
	 * 删除
	 * @Title: delEmpForgetPluginAppbyBtaId 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param btaId
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午4:39:55
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/delEmpForgetPluginAppbyFpaId",method=RequestMethod.POST)
	public String delEmpForgetPluginAppbyFpaId(HttpServletResponse httpServletResponse,Integer fpaId){
		boolean bl = empForgetPluginAppService.delEmpForgetPluginAppbyFpaId(fpaId);
		if (bl) {
			OutputJson(httpServletResponse, new DataModel("提示","删除成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,删除失败!",bl));
		}
		return null;
	}
	/**
	 * 提交申请（开启流程）
	 * @Title: sumitApply 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param empForgetPluginApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 上午10:53:10
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/sumitApply",method=RequestMethod.POST)
	public String sumitApply(HttpServletResponse httpServletResponse,EmpForgetPluginApp empForgetPluginApp){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr = empForgetPluginAppService.sumitApply(empForgetPluginApp);
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
		}
		Json json = StringUtils.isBlank(resultStr)?getMessage(isSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse,json);
		return null;
	}
	/**
	 * 查询所有任务list
	 * @Title: findAllEmpForgetPluginAppTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 下午1:21:00
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findAllEmpForgetPluginAppTaskList",method=RequestMethod.POST)
	public String findAllEmpForgetPluginAppTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,WorkFlowTasksModel workFlowTasksModel){
		GridModel gridModel = new GridModel();
		gridModel.setRows(empForgetPluginAppService.findAllEmpForgetPluginAppTaskList(new PageUtil(page, rows),workFlowTasksModel));
		gridModel.setTotal(empForgetPluginAppService.countFindAllEmpForgetPluginAppTaskList(workFlowTasksModel));
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	/**
     * 签收任务
     * @Title: holdWorkTask 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param taskId
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月13日 下午1:21:00
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/holdWorkTask",method=RequestMethod.POST)
    public String holdWorkTask(HttpServletResponse httpServletResponse,String taskId){
    	boolean bl = empForgetPluginAppService.saveHoldWorkTask(taskId);
    	if(bl){
    		OutputJson(httpServletResponse, new Json("提示","签收任务成功!",bl));
    	}else{
    		OutputJson(httpServletResponse, new Json("提示","出错了,签收任务失败!",bl));
    	}
    	return null;
    }
    /**
     * 办理任务（分部）
     * @Title: submitTask 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param purchaseAppModel
     * @param @param result
     * @param @param auditProcHis
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月13日 下午2:32:36
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/submitTask",method=RequestMethod.POST)
    public String submitTask(HttpServletResponse httpServletResponse,@ModelAttribute("taskModel") WorkFlowTasksModel taskModel,BindingResult bindingResult){
    	boolean saveSuccess = true;
    	String resultStr = "";
    	try {
    		resultStr = empForgetPluginAppService.saveSubmitTask(taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			saveSuccess = false;
		}
    	Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
    	return null;
    }
    /**
     * 查看流程图
     * @Title: showProcessImg 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param paId
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月9日 下午3:35:52
     * @throws
     */
    @RequestMapping(value="/showProcessImg",method=RequestMethod.GET)
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer fpaId){
    	empForgetPluginAppService.getDiagramResourceByPaId(httpServletResponse,fpaId);
    	return null;
    }
}
