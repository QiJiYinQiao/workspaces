package com.oasys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oasys.model.GoodsTackoverStacfg;
import com.oasys.service.GoodsTackoverStacfgService;
@Controller
@RequestMapping("/goodsStacfg")
public class GoodsTackoverStacfgController extends BaseController{
	
	@Autowired
	private GoodsTackoverStacfgService goodsTackoverStacfgService;
	@ResponseBody
	@RequestMapping(value="/findAllGoodsStacfg",method=RequestMethod.POST)
	public String findAllGoodsStacfg(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse){
		List<GoodsTackoverStacfg> list = goodsTackoverStacfgService.findAllGoodsStacfg();
		OutputJson2(httpServletResponse, list);
		return null;
	}
}
