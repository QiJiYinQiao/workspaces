package com.oasys.controller;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.PpeUsedConfirmApp;
import com.oasys.model.PpeUsedConfirmAppAttach;
import com.oasys.service.OrganizationService;
import com.oasys.service.PpeStockInfoService;
import com.oasys.service.PpeUsedConfirmService;
import com.oasys.service.RoleService;
import com.oasys.service.UserService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author wangxincheng
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *固定资产使用申请表
 */

@Controller
@RequestMapping("/PpeUsedConfirm")
public class PpeUsedConfirmController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PpeUsedConfirmService ppeUsedConfirmService; 
	
	
	/**
	 * 查询固定资产使用列表
	 * @Title: findPpeUsedConfirmList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param ppeUsedConfirmApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月4日 下午1:57:11
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findPpeUsedConfirmList",method=RequestMethod.POST)
	public String findPpeUsedConfirmList(HttpServletResponse httpServletResponse,Integer page,Integer rows,@ModelAttribute("ppeUsedConfirmApp") PpeUsedConfirmApp ppeUsedConfirmApp,  BindingResult bindingResult){
		PageUtil pageUtil=new PageUtil(page, rows);
		GridModel gridModel = ppeUsedConfirmService.findPpeUsedConfirmList(ppeUsedConfirmApp, pageUtil);
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	
	/**
	 * 根据部门id查询部门下的人员
	 * @Title: findPpeUsedConfirmList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param organizeId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月4日 下午3:35:35
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findUserList",method=RequestMethod.POST)
	public String findUserList(HttpServletResponse httpServletResponse,String organizeId){
		List<ComboBoxModel> list = userService.findOrgUserList(organizeId);
		OutputJson(httpServletResponse, list);
		return null;
	}
	
	/**
	 * 保存固定资产主表信息
	 * @Title: saveOrUpdPpeusedConfirm 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param ppeUsedConfirmApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月4日 下午4:14:49
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdPpeusedConfirm",method=RequestMethod.POST)
	public String saveOrUpdPpeusedConfirm(HttpServletResponse httpServletResponse,String userName,String password,@ModelAttribute("ppeUsedConfirmApp") PpeUsedConfirmApp ppeUsedConfirmApp,  BindingResult bindingResult){
		PpeUsedConfirmApp ppeUsedConfirm = ppeUsedConfirmService.saveOrUpdPpeUsedConfirm(ppeUsedConfirmApp);
		if(ppeUsedConfirm!=null){
			OutputJson2(httpServletResponse,new Json("提示", "固定资产使用申请保存成功！", true, ppeUsedConfirm));
		}else{
			OutputJson2(httpServletResponse,new Json("提示", "固定资产使用申请保存失败！", true));
		}
		return null;
	}
	
	/**
	 * 删除固定资产使用申请
	 * @Title: deletePpeUsedConfirm 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 下午1:30:56
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/deletePpeUsedConfirm",method=RequestMethod.POST)
	public String deletePpeUsedConfirm(HttpServletResponse httpServletResponse,String appNo){
		boolean flag = ppeUsedConfirmService.deletePpeusedConfirm(appNo);
		if(flag){
			OutputJson2(httpServletResponse, new Json("提示", "固定资产使用申请删除成功！", true));
		}else{
			OutputJson2(httpServletResponse, new Json("提示", "固定资产使用申请删除失败！", false));
		}
		
		return null;
	}
	
	//-0--------------------------------------固定资产使用附加表----------------------------------------------------
	
	/**
	 * 保存或更新申请的固定资产附加表
	 * @Title: saveOrUpdPpeusedConAtt 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param userName
	 * @param @param password
	 * @param @param ppeUsedConfirmAppAttach
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 上午11:17:16
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdPpeusedConAtt",method=RequestMethod.POST)
	public String saveOrUpdPpeusedConAtt(HttpServletResponse httpServletResponse,@ModelAttribute("ppeUsedConfirmAppAttach") PpeUsedConfirmAppAttach ppeUsedConfirmAppAttach,  BindingResult bindingResult){
		String appNo = ppeUsedConfirmService.saveOrUpdPpeUsedConAtt(ppeUsedConfirmAppAttach);
		if(appNo!=null){
			OutputJson2(httpServletResponse,new Json("提示", "申请固定资产信息保存成功！", true, appNo));
		}else{
			OutputJson2(httpServletResponse,new Json("提示", "申请固定资产信息保存失败！", true));
		}
		return null;
	}
	
	/**
	 * 申请的固定资产列表
	 * @Title: findPpeUsedConfirmList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param page
	 * @param @param rows
	 * @param @param ppeUsedConfirmApp
	 * @param @param bindingResult
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 上午11:17:44
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findPpeUsedConAtt",method=RequestMethod.POST)
	public String findPpeUsedConAtt(HttpServletResponse httpServletResponse,Integer page,Integer rows,String appNo){
		PageUtil pageUtil=new PageUtil(page, rows);
		GridModel gridModel = ppeUsedConfirmService.findPpeUsedConAttList(appNo, pageUtil);
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}
	
	/**
	 * 删除该申请下的固定资产项目，删除附加表信息
	 * @Title: deletePpeUsedConfirmAtt 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param ids
	 * @param @return	
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 下午1:42:14
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/deletePpeUsedConfirmAtt",method=RequestMethod.POST)
	public String deletePpeUsedConfirmAtt(HttpServletResponse httpServletResponse,String ids,String appNo){
		boolean flag = ppeUsedConfirmService.deletePpeUsedconAtt(ids, appNo);
		if(flag){
			OutputJson2(httpServletResponse, new Json("提示", "申请使用的固定资产删除成功！", true));
		}else{
			OutputJson2(httpServletResponse, new Json("提示", "申请使用的固定资产删除失败！", false));
		}
		return null;
	}
	
	/**
	 * 根据固定资产编号查询固定资产名称
	 * @Title: deletePpeUsedConfirmAtt 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param ppeNo
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 下午2:35:17
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/findPpeStock",method=RequestMethod.POST)
	public String deletePpeUsedConfirmAtt(HttpServletResponse httpServletResponse,String ppeNo){
		String ppeName = ppeUsedConfirmService.findPpeStockPpeNo(ppeNo);
		OutputJson2(httpServletResponse,ppeName);
		return null;
	}
	
	/**
	 * 查看流程图
	 * @Title: showPpeUsedProcess 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param pnrId
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年12月7日 下午5:05:41
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="/showPpeUsedProcess",method=RequestMethod.GET)
	public String showPpeUsedProcess(HttpServletResponse httpServletResponse,Integer psaId){
		ppeUsedConfirmService.getDiagramResourceByPaId(httpServletResponse, psaId);
		return null;
	}
	
}


