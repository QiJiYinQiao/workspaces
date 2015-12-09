package com.oasys.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.EmpRosterReg;
import com.oasys.model.Users;
import com.oasys.model.VO.EmpRosterRegModel;
import com.oasys.model.VO.UserModel;
import com.oasys.service.EmpRosterRegService;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.ComboBoxModel;
import com.oasys.viewModel.DataModel;
import com.oasys.viewModel.GridModel;

/**
 * 
 * @ClassName: EmpRosterRegController
 * @Description: TODO 员工花名册登记Controller
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年11月9日 下午5:38:22
 *
 */
@Controller
@RequestMapping("/empRosterRegController")
public class EmpRosterRegController extends BaseController {

	/** 注入service. */
	@Autowired
	private EmpRosterRegService empRosterRegService;

	@Autowired
	private OrganizationService orgService;

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月9日 下午5:35:30
	 * @Title:addEmpRosterReg
	 * @Description:TODO（这里描述这个方法的作用）保存员工花名册登记（新增或修改）
	 * @param httpRequest
	 * @param httpServletResponse
	 * @param empRosterReg
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value = "/saveEmpRosterReg", method = RequestMethod.POST)
	public String saveEmpRosterReg(HttpServletRequest httpRequest,
			HttpServletResponse httpServletResponse,@ModelAttribute(value="empRosterReg")EmpRosterReg empRosterReg) {
		boolean bl = empRosterRegService.saveOrUpdateEmpRosterRegEntity(empRosterReg);
		if (bl) {
			OutputJson(httpServletResponse, new DataModel("提示","更新数据成功!",bl));
		}else{
			OutputJson(httpServletResponse, new DataModel("提示","出错了,保存失败!",bl));
		}
		return null;
	}

	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月9日 下午5:42:24
	 * @Title:findExpressInfoRegList
	 * @Description:TODO（这里描述这个方法的作用）查询员工花名册列表
	 * @param httpRequest
	 * @param httpServletResponse
	 * @param page
	 * @param rows
	 * @param expressInfoReg
	 * @param bindingResult
	 * @return
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value = "/findEmpRosterRegList", method = RequestMethod.POST)
	public String findEmpRosterRegList(HttpServletRequest httpRequest,
			HttpServletResponse httpServletResponse, Integer page,
			Integer rows,EmpRosterRegModel empRosterReg) {
		PageUtil pageUtil=new PageUtil(page, rows);
		GridModel gridModel=new GridModel();
		gridModel.setRows(empRosterRegService.findEmpRosterRegList(pageUtil,empRosterReg));
		gridModel.setTotal(empRosterRegService.findEmpRosterRegCount(empRosterReg));
		OutputJson2(httpServletResponse, gridModel);
		return null;
	}

	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月9日 下午5:41:04
	 * @Title:delExpressInfoReg
	 * @Description:TODO（这里描述这个方法的作用）删除员工花名册信息
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param ids
	 *            对象id字符串
	 * @return
	 * @throws:
	 */
	@RequestMapping(value = "/delEmpRosterReg")
	public ModelAndView delEmpRosterReg(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(value = "ids", required = true) String ids) {
		Integer idCounts = empRosterRegService.delEmpRosterReg(ids);
		if(idCounts > 0){
    		OutputJson(httpServletResponse, getMessage(true));
    	}else{
    		OutputJson(httpServletResponse, getMessage(false));
    	}
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月10日 下午7:46:22
	 * @Title:getUsersByOrgId
	 * @Description:TODO（这里描述这个方法的作用）根据部门查找用户
	 * @param httpServletResponse
	 * @param organizeId
	 * @return
	 * @throws:
	 */
	@RequestMapping(value = "/getUsersByOrgId")
	public String getUsersByOrgId(HttpServletResponse httpServletResponse,String organizeId){
		List<ComboBoxModel> userList = userService.findOrgUserList(organizeId);
		OutputJson(httpServletResponse, userList);
		return null;
	}
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年11月10日 下午7:46:48
	 * @Title:getUsersByUserId
	 * @Description:TODO（这里描述这个方法的作用）根据用户id加载用户对象
	 * @param httpServletResponse
	 * @param userId
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws:
	 */
	@ResponseBody
	@RequestMapping(value = "/getUsersByUserId",method=RequestMethod.POST)
	public String getUsersByUserId(HttpServletResponse httpServletResponse,String userId) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Users user = userService.findUserById(Integer.parseInt(userId));
		UserModel um=new UserModel();
		PropertyUtils.copyProperties(um, user);
		OutputJson2(httpServletResponse, um);
		return null;
	}

}
