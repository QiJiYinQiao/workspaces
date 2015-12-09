package com.oasys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.oasys.model.GoodsTackoverReg;
import com.oasys.model.GoodsTackoverStacfg;
import com.oasys.model.Users;
import com.oasys.service.GoodsTackoverRegService;
import com.oasys.service.GoodsTackoverStacfgService;
import com.oasys.service.UserService;
import com.oasys.shiro.ShiroUser;
import com.oasys.util.Constants;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;

@Controller
@RequestMapping("/goodsReg")
public class GoodsTackoverRegController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsTackoverRegService goodsTackoverRegService;
	@Autowired
	private GoodsTackoverStacfgService goodsTackoverStacfgService;
	@ResponseBody
	@RequestMapping(value="/addGoodsTackoverReg",method=RequestMethod.POST)
	public String addGoodsTackoverReg(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse,GoodsTackoverReg goodsTackoverReg,  BindingResult bindingResult,String ids) {
		//新增获取当前登录用户中的信息
		ShiroUser shiroUser = Constants.getCurrendUser();
		Integer userID = shiroUser.getUserId();
		Users user = userService.getUserByID(userID);
		try {
			List<GoodsTackoverStacfg> arrayList = new ArrayList<GoodsTackoverStacfg>();
			String[] idList=ids.split(",");
			for(int i=0;i<idList.length;i++){
				GoodsTackoverStacfg goodsTackoverStacfg=goodsTackoverStacfgService.getGoodsTackoverStacfgById(Integer.parseInt(idList[i]));
				arrayList.add(goodsTackoverStacfg);
			}
			goodsTackoverReg.setGoodsTackoverStacfgList(arrayList);
			if(null == goodsTackoverReg.getGtrId()){
				goodsTackoverReg.setRegistrantNo(user.getUserId());
				goodsTackoverReg.setRegDatetime(new Date());
				
				goodsTackoverRegService.addOrUpdateGoodsTackoverEntity(goodsTackoverReg);
			}else{
				goodsTackoverRegService.addOrUpdateGoodsTackoverEntity(goodsTackoverReg);
			}
			OutputJson(httpServletResponse,new Json("提示", "保存成功", true));
		} catch (Exception e) {
			OutputJson(httpServletResponse,new Json("提示","保存失败",false));
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/findAllGoods",method=RequestMethod.POST)
	public String findAllGoods(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse,GoodsTackoverReg goodsTackoverReg,Integer page,Integer rows) {
		PageUtil pageUtil = new PageUtil(page,rows);
    	GridModel gridModel = new GridModel();
    	List<GoodsTackoverReg> goodsList = goodsTackoverRegService.findGoodsList(pageUtil,goodsTackoverReg);
    	Long rowsCount = goodsTackoverRegService.findGoodsCount(goodsTackoverReg);//获取总记录数
		gridModel.setRows(goodsList);
		gridModel.setTotal(rowsCount);
		OutputJson(httpServletResponse, gridModel,"text/json");
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/createCombogrid",method=RequestMethod.POST)
	public String createCombogrid(HttpServletResponse httpServletResponse,String q){
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findUserList(q));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteGoods",method=RequestMethod.POST)
	public String deleteGoods(HttpServletResponse httpServletResponse,String ids){
		boolean bl=goodsTackoverRegService.deleteGoods(ids);
		if(bl){
			OutputJson2(httpServletResponse,new Json("提示","删除成功!",bl));
		}else{
			OutputJson2(httpServletResponse,new Json("提示","出错了,删除失败!",bl));
		}
    	return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/findGoodsStacfgIds",method=RequestMethod.POST)
	public String findGoodsStacfgIds(HttpServletResponse httpServletResponse,Integer id){
		String ids=goodsTackoverRegService.findGoodsStacfgIds(id);
		OutputJson2(httpServletResponse, ids);
		return null;
	}
}
