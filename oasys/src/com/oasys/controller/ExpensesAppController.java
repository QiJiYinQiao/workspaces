package com.oasys.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.AuditProcHis;
import com.oasys.model.ExpensesApp;
import com.oasys.model.VO.ExpensesAppModel;
import com.oasys.service.ExpensesAppService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.WorkFlowTasksModel;
/**
 * 费用申请Controller
 * @ClassName: ExpensesAppController 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年10月12日 上午9:18:55
 */
@Controller
@RequestMapping("/expensesAppController")
public class ExpensesAppController extends BaseController{

	@Autowired
	private ExpensesAppService expensesAppService;
	
	/**
	 * 新增
	 * @Title: saveExpensesApp 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param expensesApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月12日 下午1:24:27
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveExpensesApp",method=RequestMethod.POST)
	public String saveExpensesApp(HttpServletResponse httpServletResponse,ExpensesApp expensesApp){
		boolean bl = expensesAppService.saveExpensesApp(expensesApp);
		if (bl) {
			OutputJson(httpServletResponse, new DataModel("提示","更新数据成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,保存失败!",bl));
		}
		return null;
	}
	/**
	 * 查询列表
	 * @Title: findExpensesAppList 
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
	@RequestMapping(value="/findExpensesAppList",method=RequestMethod.POST)
	public String findExpensesAppList(HttpServletResponse httpServletResponse,Integer page,Integer rows,ExpensesAppModel expensesAppModel){
		PageUtil pageUtil = new PageUtil(page, rows);
		GridModel gridModel = new GridModel();
		gridModel.setRows(expensesAppService.findExpensesAppList(pageUtil,expensesAppModel));
		gridModel.setTotal(expensesAppService.countFindExpensesAppList());
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	/**
	 * 删除
	 * @Title: delExpensesAppbyBtaId 
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
	@RequestMapping(value="/delExpensesAppbyBtaId",method=RequestMethod.POST)
	public String delExpensesAppbyBtaId(HttpServletResponse httpServletResponse,Integer btaId){
		boolean bl = expensesAppService.delExpensesAppbyBtaId(btaId);
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
	 * @param @param expensesApp
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 上午10:53:10
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/sumitApply",method=RequestMethod.POST)
	public String sumitApply(HttpServletResponse httpServletResponse,ExpensesApp expensesApp){
		boolean bl = expensesAppService.sumitApply(expensesApp);
		if(bl){
			OutputJson(httpServletResponse, new DataModel("提示","提交申请成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,提交申请失败!",bl));
		}
		return null;
	}
	/**
	 * 查询所有任务list
	 * @Title: findAllExpensesAppTaskList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年10月13日 下午1:21:00
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findAllExpensesAppTaskList",method=RequestMethod.POST)
	public String findAllExpensesAppTaskList(HttpServletResponse httpServletResponse,Integer page,Integer rows,WorkFlowTasksModel workFlowTasksModel){
		GridModel gridModel = new GridModel();
		gridModel.setRows(expensesAppService.findAllExpensesAppTaskList(new PageUtil(page, rows),workFlowTasksModel));
		gridModel.setTotal(expensesAppService.countFindAllExpensesAppTaskList(workFlowTasksModel));
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
    	boolean bl = expensesAppService.saveHoldWorkTask(taskId);
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
    public String submitTask(HttpServletResponse httpServletResponse,ExpensesAppModel expensesAppModel,String result,AuditProcHis auditProcHis){
    	boolean bl = expensesAppService.saveSubmitTaskBo(expensesAppModel, result, auditProcHis);
    	if(bl){
    		OutputJson(httpServletResponse, new Json("提示","办理任务成功!",bl));
    	}else{
    		OutputJson(httpServletResponse, new Json("提示","出错了,办理任务失败!",bl));
    	}
    	return null;
    }
    /**
     * 办理任务（总部）
     * @Title: submitTask2 
     * @Description: TODO
     * @param @param httpServletResponse
     * @param @param expensesAppModel
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
    public String submitTask2(HttpServletResponse httpServletResponse,ExpensesAppModel expensesAppModel,String result,AuditProcHis auditProcHis){
    	boolean bl = expensesAppService.saveSubmitTaskHo(expensesAppModel, result, auditProcHis);
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
    public String showProcessImg(HttpServletResponse httpServletResponse,Integer btaId){
    	expensesAppService.getDiagramResourceByPaId(httpServletResponse,btaId);
    	return null;
    }
}
