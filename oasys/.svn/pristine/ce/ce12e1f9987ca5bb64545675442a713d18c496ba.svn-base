package com.oasys.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

import com.oasys.model.PpeReg;
import com.oasys.service.OrganizationService;
import com.oasys.service.PpeRegService;
import com.oasys.service.UserService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;


/**   
 * @Title: PpeRegController
 * @Package com.oasys.controller
 * @Description: 固定资产登记表Controller
 * @author lida  
 * @date 2015/9/25
 * @version V1.0   
 */
@Controller
@RequestMapping("/ppeRegController")
public class PpeRegController extends BaseController{
	
	/** 注入service. */
	@Autowired
	private PpeRegService ppeRegService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private UserService userService;
	
	
	
	/** 
	 * @Title: addPpeReg
	 * @Description: 新增固定资产登记表申请
	 * @param ppeReg 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/10/13
	 */
	@ResponseBody
	@RequestMapping(value="/addPpeReg",method=RequestMethod.POST)
	public String addPpeReg(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("ppeReg") PpeReg ppeReg,BindingResult bindingResult) {
		//新增获取当前登录用户中的信息
			ShiroUser shiroUser = Constants.getCurrendUser();
			Integer userID = shiroUser.getUserId();
			List<String> dataList = new ArrayList<String>();
			try {
				if(null == ppeReg.getPrId()){
					ppeReg.setRegistrantNo(userID);//登记人
					ppeReg.setRegDatetime(DateUtils.getNowTime(DateUtils.DATE_FULL_STR));//登记时间
				}
				ppeRegService.saveOrUpdatePpeRegEntity(ppeReg);
				OutputJson(httpServletResponse,new Json("提示","保存成功",true,dataList));
			} catch (Exception e) {
				OutputJson(httpServletResponse,new Json("提示","保存失败",false,dataList));
			}
			return null;
	}
	

	/** 
	 * @Title: findAllPpeReg
	 * @Description: 查询固定资产登记列表
	 * @param ppeScrapAttach 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @throws ParseException 
	 * @date 2015/9/25
	 */
	@ResponseBody
	@RequestMapping(value="/findAllPpeReg",method=RequestMethod.POST)
	public String findAllPpeReg(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, Integer page,Integer rows,@ModelAttribute("ppeReg") PpeReg ppeReg,  BindingResult bindingResult) throws ParseException{
    	GridModel gridModel = new GridModel();
		gridModel.setRows(ppeRegService.findPpeRegList(ppeReg,new PageUtil(page,rows)));//获取固定资产报废申请List
		gridModel.setTotal(ppeRegService.findPpeRegCount(ppeReg));//获取总记录数
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	
    
	/** 
	 * @Title: PpeReg
	 * @Description: 删除固定资产登记
	 * @param ids ID标识 支持多选删除(用<，>分割)
	 * @author lida
	 * @return String
	 * @date 2015/9/28
	 */
    @RequestMapping(value="/delPpeReg")
	public ModelAndView delPpeReg(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestParam(value="ids",required = true) String ids){
    	Integer idCounts = ppeRegService.delPpeReg(ids);
    	if(idCounts > 0){
    		OutputJson(httpServletResponse, getMessage(true));
    	}else{
    		OutputJson(httpServletResponse, getMessage(false));
    	}
		return null;
	}
    
    /** 
	 * @Title: toAddPpeReg
	 * @Description:  跳转到快递信息登记表新增页面
	 * @param psaID ID标识
	 * @author lida 
	 * @return String
	 * @date 2015/10/13
	 */
    @RequestMapping(value="/toAddPpeReg")
	public ModelAndView toAddPpeReg(HttpServletRequest httpServletRequest,@RequestParam(value="prId",required = true) String prId){
    	PpeReg ppe = new PpeReg();
    	//判断是否为新增
    	if(null == prId || "".equals(prId)){
    		ppe.setUserName(Constants.getCurrendUser().getAccount());//用户帐号
    		ppe.setRegDatetime(DateUtils.getNowTime());//创建时间
    		ppe.setRegistrantNo(Constants.getCurrendUser().getUserId());//userID
    	}else{
    	//修改
    		ppe = ppeRegService.getPpeRegByID(Integer.valueOf(prId));
    		ppe.setUserName(userService.getUserByID(ppe.getRegistrantNo()).getName());//翻译用户名
    		ppe.setPpeUserName(userService.getUserByID(ppe.getUser()).getName());//翻译使用人用户名
    		ppe.setPpeOrgName(orgService.getOrgNameByID(ppe.getUseDept()));//翻译使用部门名称
    	}
    	ModelAndView mv = new ModelAndView("ad/ppeReg/ppeRegAdd");
    	mv.addObject("ppeReg", ppe);
		return mv;
	}
    
}
