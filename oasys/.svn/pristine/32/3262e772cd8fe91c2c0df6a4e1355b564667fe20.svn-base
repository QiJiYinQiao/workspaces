package com.oasys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.ExpressInfoReg;
import com.oasys.model.Users;
import com.oasys.service.ExpressInfoRegService;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;


/**   
 * @Title: ExpressInfoRegController
 * @Package com.oasys.controller
 * @Description: 快递信息登记信息表Controller
 * @author lida  
 * @date 2015/9/25
 * @version V1.0   
 */
@Controller
@RequestMapping("/expressInfoRegController")
public class ExpressInfoRegController extends BaseController{
	
	/** 注入service. */
	@Autowired
	private ExpressInfoRegService expressInfoRegService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private UserService userService;
	
	
	
	/** 
	 * @Title: addExpressInfoReg
	 * @Description: 新增快递信息登记信息表申请
	 * @param expressInfoReg 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/10/13
	 */
	@ResponseBody
	@RequestMapping(value="/addExpressInfoReg",method=RequestMethod.POST)
	public String addExpressInfoReg(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("expressInfoReg") ExpressInfoReg expressInfoReg,BindingResult bindingResult) {
		//新增获取当前登录用户中的信息
			Users user = userService.getUserByID( Constants.getCurrendUser().getUserId());
			try {
				if(null == expressInfoReg.getEirId()){
					expressInfoReg.setRegistrantNo(user.getUserId());//登记人
					expressInfoReg.setDeptNo(user.getOrganizeId());//登记部门
					expressInfoReg.setRegDatetime(DateUtils.getNowTime(DateUtils.DATE_FULL_STR));//登记时间
				}
				expressInfoRegService.saveOrUpdateExpressInfoRegEntity(expressInfoReg);
				OutputJson(httpServletResponse,new Json("提示","保存成功",true));
			} catch (Exception e) {
				OutputJson(httpServletResponse,new Json("提示","保存失败",false));
			}
			return null;
	}
	

	/** 
	 * @Title: findAllExpressInfoReg
	 * @Description: 查询快递信息登记信息附加表列表
	 * @param ppeScrapAttach 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/9/25
	 */
	@ResponseBody
	@RequestMapping(value="/findAllExpressInfoReg",method=RequestMethod.POST)
	public String findAllExpressInfoReg(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("expressInfoReg") ExpressInfoReg expressInfoReg,  BindingResult bindingResult){
    	GridModel gridModel = new GridModel();
		gridModel.setRows(expressInfoRegService.findExpressInfoRegList(expressInfoReg));//获取固定资产报废申请List
		gridModel.setTotal(expressInfoRegService.findExpressInfoRegCount(expressInfoReg));//获取总记录数
		OutputJson2(httpServletResponse,gridModel);
		return null;
	}
	
    
	/** 
	 * @Title: ExpressInfoReg
	 * @Description: 删除快递信息登记信息
	 * @param ids ID标识 支持多选删除(用<，>分割)
	 * @author lida
	 * @return String
	 * @date 2015/9/28
	 */
    @RequestMapping(value="/delExpressInfoReg")
	public ModelAndView delExpressInfoReg(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestParam(value="ids",required = true) String ids){
    	Integer idCounts = expressInfoRegService.delExpressInfoReg(ids);
    	if(idCounts > 0){
    		OutputJson(httpServletResponse, getMessage(true));
    	}else{
    		OutputJson(httpServletResponse, getMessage(false));
    	}
		return null;
	}
    
    /** 
	 * @Title: toAddExpressInfoReg
	 * @Description:  跳转到快递信息登记表新增页面
	 * @param psaID ID标识
	 * @author lida 
	 * @return String
	 * @date 2015/10/13
	 */
    @RequestMapping(value="/toAddExpressInfoReg")
	public ModelAndView toAddExpressInfoReg(HttpServletRequest httpServletRequest,@RequestParam(value="eirId",required = true) String eirId){
    	ExpressInfoReg exp = new ExpressInfoReg();
    	//判断是否为新增
    	if(null == eirId || "".equals(eirId)){
    		exp.setUserName(Constants.getCurrendUser().getAccount());//用户帐号
    		exp.setRegDatetime(DateUtils.getNowTime());//创建时间
    		exp.setRegistrantNo(Constants.getCurrendUser().getUserId());//userID
    	}else{
    	//修改
    		exp = expressInfoRegService.getExpressInfoRegByID(Integer.valueOf(eirId));
    		exp.setUserName(userService.getUserByID(exp.getRegistrantNo()).getName());//翻译用户名
    		exp.setAddUserName(userService.getUserByID(exp.getAddresseeNo()).getName());//翻译收件人用户名
    		exp.setAddOrgName(orgService.getOrgNameByID(exp.getDeptNo()));//翻译收件人部门名称
    	}
    	ModelAndView mv = new ModelAndView("ad/expressInfoReg/expressInfoRegAdd");
    	mv.addObject("expressInfoReg", exp);
		return mv;
	}
    
}
