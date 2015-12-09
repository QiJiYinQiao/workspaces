package com.oasys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.oasys.model.StampTurnoverAppAttach;
import com.oasys.service.OrganizationService;
import com.oasys.service.StampTurnoverAppAttachService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;


/**   
 * @Title: PPEScrapAppAttachController
 * @Package com.oasys.controller
 * @Description: 印章移交附加表Controller
 * @author lida  
 * @date 2015/10/12
 * @version V1.0   
 */
@Controller
@RequestMapping("/StampTurnoverAppAttachController")
public class StampTurnoverAppAttachController extends BaseController{
	
	/** 注入service. */
	@Autowired
	private StampTurnoverAppAttachService StampTurnoverAttachService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkFlowTaskService workTaskService;
	
	
	/** 
	 * @Title: addStampTurnoverAppAttach
	 * @Description: 新增印章移交附加表申请
	 * @param StampTurnover 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/10/13
	 */
	@ResponseBody
	@RequestMapping(value="/addStampTurnoverAppAttach",method=RequestMethod.POST)
	public String addStampTurnoverAppAttach(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("StampTurnover") StampTurnoverAppAttach StampTurnoverAppAttach,BindingResult bindingResult) {
		try {
			StampTurnoverAttachService.saveOrUpdatePpeEntity(StampTurnoverAppAttach);//新增或更新附加表信息
			OutputJson(httpServletResponse, getMessage(true));
		} catch (Exception e) {
			OutputJson(httpServletResponse, getMessage(false));
		}
		return null;
	}
	

	/** 
	 * @Title: findAllStampTurnoverAttach
	 * @Description: 查询印章移交申请附加表列表
	 * @param StampTurnoverAttach 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/9/25
	 */
	@ResponseBody
	@RequestMapping(value="/findAllStampTurnoverAttach",method=RequestMethod.POST)
	public String findAllStampTurnoverAttach(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,@ModelAttribute("StampTurnoverAttach") StampTurnoverAppAttach StampTurnoverAttach,  BindingResult bindingResult){
		if(StringUtils.isNotBlank(StampTurnoverAttach.getAppNo()) && StampTurnoverAttach.getAppNo().split(",").length > 1){
			StampTurnoverAttach.setAppNo(StampTurnoverAttach.getAppNo().split(",")[1]);
		}
		List<StampTurnoverAppAttach> attachList = new ArrayList<StampTurnoverAppAttach>();
		if(StringUtils.isNotBlank(StampTurnoverAttach.getAppNo())){
			attachList = StampTurnoverAttachService.findPpeAttachList(StampTurnoverAttach.getAppNo());//根据appNo申请编号 或者附加表列表
			OutputJson(httpServletResponse, attachList);
		}else{
			OutputJson(httpServletResponse, attachList);
		}
		return null;
	}
	
	/** 
	 * @Title: toAddStampTurnoverAppAttach
	 * @Description: 跳转到固定资产项编辑页面
	 * @param psaID ID标识
	 * @author lida
	 * @return String
	 * @date 2015/9/28
	 */
    @RequestMapping(value="/toAddStampTurnoverAppAttach")
	public ModelAndView toAddStampTurnoverAppAttach(HttpServletRequest httpServletRequest,@RequestParam(value="taaId",required = true) String taaId){
    	StampTurnoverAppAttach stampTurnoverAttach = StampTurnoverAttachService.getStampTurnoverAttachByID(Integer.valueOf(taaId));
    	ModelAndView mv = new ModelAndView("ad/stampTurnover/stampTurnoverAttachAdd");
    	mv.addObject("stampTurnoverAttach", stampTurnoverAttach);
		return mv;
	}
    
	/** 
	 * @Title: delPurchaseAppAttach
	 * @Description: 删除固定资产项
	 * @param ids ID标识 支持多选删除(用<，>分割)
	 * @author lida
	 * @return String
	 * @date 2015/9/28
	 */
    @RequestMapping(value="/delPurchaseAppAttach")
	public ModelAndView delPurchaseAppAttach(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestParam(value="ids",required = true) String ids){
    	Integer idCounts = StampTurnoverAttachService.delStampTurnoverAttach(ids);
    	if(idCounts > 0){
    		OutputJson(httpServletResponse, getMessage(true));
    	}else{
    		OutputJson(httpServletResponse, getMessage(false));
    	}
		return null;
	}
}
