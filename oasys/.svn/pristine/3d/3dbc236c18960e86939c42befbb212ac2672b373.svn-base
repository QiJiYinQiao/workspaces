package com.oasys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.ConsumablesAppAttach;
import com.oasys.service.ConsumablesAppAttachService;

/**
 * 
 * @Title: ConsumablesAppAttachController.java 
 * @Package com.oasys.controller 
 * @Description: TODO
 * @author yuanzhongqiu  
 * @date 2015年10月12日 上午11:22:12 
 * @version V1.0
 */
@Controller
@RequestMapping("/consumablesAppAttach")
public class ConsumablesAppAttachController extends BaseController{
	@Autowired
	private ConsumablesAppAttachService consumablesAppAttachService;
	/**
	 * 
	 * @Title: findAllConsumablesAppAttach 
	 * @Description: 查询所有低值易耗品
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param consumablesAppAttach
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月12日 下午1:40:05
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findAllConsumablesAppAttach",method=RequestMethod.POST)
	public String findAllConsumablesAppAttach(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,ConsumablesAppAttach consumablesAppAttach,  BindingResult bindingResult){
		if(StringUtils.isNotBlank(consumablesAppAttach.getAppNo()) && consumablesAppAttach.getAppNo().split(",").length > 1){
			consumablesAppAttach.setAppNo(consumablesAppAttach.getAppNo().split(",")[1]);
		}
		if(StringUtils.isNotBlank(consumablesAppAttach.getAppNo())){
			List<ConsumablesAppAttach> attachList = consumablesAppAttachService.findConsumablesAppAttachList(consumablesAppAttach.getAppNo());//根据appNo申请编号 或者附加表列表
			OutputJson(httpServletResponse, attachList);
		}else{
			OutputJson(httpServletResponse, null);
		}
		return null;
	}
	/**
	 * 
	 * @Title: addConsumablesAppAttach 
	 * @Description: 低值易耗品添加
	 * @param @param httpRequest
	 * @param @param httpServletResponse
	 * @param @param consumablesAppAttach
	 * @param @param bindingResult
	 * @param @return
	 * @author yuanzhongqiu
	 * @return String
	 * @date 2015年10月12日 下午1:57:42
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/addConsumablesAppAttach",method=RequestMethod.POST)
	public String addConsumablesAppAttach(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse,ConsumablesAppAttach consumablesAppAttach,BindingResult bindingResult) {
		try {
			consumablesAppAttachService.saveOrUpdateConsumablesAppAttach(consumablesAppAttach);//新增或更新附加表信息
			OutputJson(httpServletResponse, getMessage(true));
		} catch (Exception e) {
			OutputJson(httpServletResponse, getMessage(false));
		}
		return null;
	}
	/**
	 * 
	 * @Title: toAddConsumablesAppAttach 
	 * @Description: 跳转到低值易耗品编辑页面
	 * @param @param httpServletRequest
	 * @param @param caaID
	 * @param @return
	 * @author yuanzhongqiu
	 * @return ModelAndView
	 * @date 2015年10月12日 下午2:13:26
	 * @throws
	 */
	@RequestMapping(value="/toAddConsumablesAppAttach")
	public ModelAndView toAddConsumablesAppAttach(HttpServletRequest httpServletRequest,@RequestParam(value="caaId",required = true) String caaID){
		ConsumablesAppAttach consumablesAppAttach = consumablesAppAttachService.getConsumablesAppAttachByID(Integer.valueOf(caaID));
    	ModelAndView mv = new ModelAndView("ad/consumablesApp/consumablesAppAttachAddForm");
    	mv.addObject("consumablesAppAttach", consumablesAppAttach);
		return mv;
	}
	/**
	 * 
	 * @Title: delPurchaseAppAttach 
	 * @Description: TODO
	 * @param @param httpServletRequest
	 * @param @param httpServletResponse
	 * @param @param ids
	 * @param @return
	 * @author yuanzhongqiu
	 * @return ModelAndView
	 * @date 2015年10月12日 下午2:32:05
	 * @throws
	 */
	@RequestMapping(value="/delConsumablesAppAttach")
	public ModelAndView delPurchaseAppAttach(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestParam(value="ids",required = true) String ids,String appNo){
    	Integer idCounts = consumablesAppAttachService.delConsumablesAppAttach(ids,appNo);
    	if(idCounts > 0){
    		OutputJson(httpServletResponse, getMessage(true));
    	}else{
    		OutputJson(httpServletResponse, getMessage(false));
    	}
		return null;
	}
}
