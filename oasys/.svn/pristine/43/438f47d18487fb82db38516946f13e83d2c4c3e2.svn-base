package com.oasys.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.Parameter;
import com.oasys.service.CommonService;
import com.oasys.util.Collections;
import com.oasys.viewModel.ComboBoxModel;

/**
 * 公用Controller
 * @ClassName: CommonController 
 * @Description: TODO
 * @author PANCHUANHE
 * @date 2015年9月18日 下午5:15:08
 */
@Controller
@RequestMapping("/commonController")
public class CommonController extends BaseController {

	@Autowired
	private CommonService commonService;
	
	
	/**
	 * 根据字典codeMyid获取下拉框选项，渲染下拉框
	 * @Title: findDicList 
	 * @Description: TODO
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月18日 下午5:28:54
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="findDicList",method=RequestMethod.POST)
	public String findDicList(HttpServletResponse httpServletResponse,String codeMyid){
		OutputJson(httpServletResponse,this.commonService.findDicList(codeMyid));
		return null;
	}
	/**
	 * 根据codeMyid和parentId查询字典(字典有三级的情况下使用)
	 * @Title: findDicByParentId 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param codeMyid
	 * @param @param parentId
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年11月25日 下午4:14:26
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="findDicByParentId",method=RequestMethod.POST)
	public String findDicByParentId(HttpServletResponse httpServletResponse,String codeMyid,String parentId){
		List<ComboBoxModel> list = this.commonService.findDicByParentId(codeMyid, parentId);
		if (Collections.listIsNotEmpty(list)) {
			OutputJson(httpServletResponse,list.get(0));
		}
		return null;
	}
	/**
	 * 根据parmCode获取系统参数
	 * @Title: findParameterByParmCode 
	 * @Description: TODO
	 * @param @param parmCode
	 * @param @return
	 * @author PANCHUANHE
	 * @return Parameter
	 * @date 2015年11月30日 下午3:16:56
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="findParameterByParmCode",method=RequestMethod.POST)
	public Parameter findParameterByParmCode(HttpServletResponse httpServletResponse,String parmCode){
		OutputJson(httpServletResponse, this.commonService.findParameterByParmCode(parmCode));
		return null;
	};
}
