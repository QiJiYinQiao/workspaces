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

import com.alibaba.fastjson.JSON;
import com.oasys.model.AuditProcHis;
import com.oasys.model.EmpFullmemberApp;
import com.oasys.model.VO.EmpFullmemberAppModel;
import com.oasys.service.EmpFullmemberAppService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 费用申请Controller
 * @ClassName: EmpFullmemberAppController 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:18:55
 */
@Controller
@RequestMapping("/empFullmemberAppController")
public class EmpFullmemberAppController extends BaseController{

	@Autowired
	private EmpFullmemberAppService empFullmemberAppService;
	/**
	 * 申请人下拉列表框
	 * @Title: createCombogrid 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月26日 下午3:10:56
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/createCombogrid",method=RequestMethod.POST)
	public String createCombogrid(HttpServletResponse httpServletResponse,String q){
		OutputJson2(httpServletResponse, empFullmemberAppService.findCreateCombogrid(q));
		return null;
	}
	/**
	 * 新增
	 * @Title: saveEmpFullmemberApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param empFullmemberApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午1:24:27
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveEmpFullmemberApp",method=RequestMethod.POST)
	public String saveEmpFullmemberApp(HttpServletResponse httpServletResponse,@ModelAttribute("empFullmemberApp") EmpFullmemberApp empFullmemberApp,BindingResult bindingResult){
		boolean bl = empFullmemberAppService.saveEmpFullmemberApp(empFullmemberApp);
		if (bl) {
			OutputJson(httpServletResponse, new DataModel("提示","更新数据成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,保存失败!",bl));
		}
		return null;
	}
	/**
	 * 查询列表
	 * @Title: findEmpFullmemberAppList 
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
	@RequestMapping(value="/findEmpFullmemberAppList",method=RequestMethod.POST)
	public String findEmpFullmemberAppList(HttpServletResponse httpServletResponse,Integer page,Integer rows,EmpFullmemberAppModel empFullmemberAppModel){
		PageUtil pageUtil = new PageUtil(page, rows);
		GridModel gridModel = new GridModel();
		gridModel.setRows(empFullmemberAppService.findEmpFullmemberAppList(pageUtil,empFullmemberAppModel));
		gridModel.setTotal(empFullmemberAppService.countFindEmpFullmemberAppList());
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	/**
	 * 删除
	 * @Title: delEmpFullmemberAppbyBtaId 
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
	@RequestMapping(value="/delEmpFullmemberAppByEfaId",method=RequestMethod.POST)
	public String delEmpFullmemberAppByEfaId(HttpServletResponse httpServletResponse,Integer efaId){
		boolean bl = empFullmemberAppService.delEmpFullmemberAppByEfaId(efaId);
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
	 * @param @param empFullmemberApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 上午10:53:10
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/sumitApply",method=RequestMethod.POST)
	public String sumitApply(HttpServletResponse httpServletResponse,EmpFullmemberApp empFullmemberApp){
		boolean isSuccess = true;
		String resultStr = "";
		try {
			resultStr = empFullmemberAppService.sumitApply(empFullmemberApp);
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
	 * @Title: findAllEmpFullmemberAppTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 下午1:21:00
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findAllEmpFullmemberAppTaskList",method=RequestMethod.POST)
	public String findAllEmpFullmemberAppTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,WorkFlowTasksModel workFlowTasksModel){
		GridModel gridModel = new GridModel();
		gridModel.setRows(empFullmemberAppService.findAllEmpFullmemberAppTaskList(new PageUtil(page, rows),workFlowTasksModel));
		gridModel.setTotal(empFullmemberAppService.countFindAllEmpFullmemberAppTaskList());
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
    	boolean bl = empFullmemberAppService.saveHoldWorkTask(taskId);
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
    		resultStr = empFullmemberAppService.saveSubmitTaskBo(taskModel);
		} catch (Exception e) {
			e.printStackTrace();
			saveSuccess = false;
		}
    	Json json = StringUtils.isBlank(resultStr)?getMessage(saveSuccess):new Json("提示", resultStr, true);
		OutputJson(httpServletResponse, json);
    	return null;
    }
    /**
     * 办理任务（总部）
     * @Title: submitTask2 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param empFullmemberAppModel
     * @param @param result
     * @param @param auditProcHis
     * @param @return
     * @author PANCHUANHE
     * @return String
     * @date 2015年10月13日 下午8:02:12
     * @throws
     */
    @ResponseBody
    @RequestMapping(value="/submitTask2",method=RequestMethod.POST)
    public String submitTask2(HttpServletResponse httpServletResponse,EmpFullmemberAppModel empFullmemberAppModel,String result,AuditProcHis auditProcHis){
    	boolean bl = empFullmemberAppService.saveSubmitTaskHo(empFullmemberAppModel, result, auditProcHis);
    	if(bl){
    		OutputJson(httpServletResponse, new Json("提示","办理任务成功!",bl));
    	}else{
    		OutputJson(httpServletResponse, new Json("提示","出错了,办理任务失败!",bl));
    	}
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
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer efaId){
    	empFullmemberAppService.getDiagramResourceByPaId(httpServletResponse,efaId);
    	return null;
    }
}
