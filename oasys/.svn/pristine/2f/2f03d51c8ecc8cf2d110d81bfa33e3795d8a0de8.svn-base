package com.oasys.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.PurchaseApp;
import com.oasys.model.PurchaseAppAttach;
import com.oasys.service.PurchaseAppAttachService;
import com.oasys.service.PurchaseAppService;
import com.oasys.service.UserService;
import com.oasys.viewModel.GridModel;
import com.oasys.viewModel.Json;
import com.oasys.viewModel.PurchaseAppAttachModel;
/**
 * @ClassName: PurchaseAppAttachController 
 * @Description: TODO 物料表Controller
 * @author PANCHUANHE
 * @date 2015年9月16日 下午3:15:12
 */
@Controller
@RequestMapping("/purchaseAppAttachController")
public class PurchaseAppAttachController extends BaseController{
	
	@Autowired
	private PurchaseAppAttachService purchaseAppAttachService;
	@Autowired
	private PurchaseAppService purchaseAppService;
	@Autowired
	private UserService userService;
	/**
	 * @Title: savePurchaseAppAttach 
	 * @Description: TODO 
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月16日 下午3:16:28
	 * @throws
	 */
	@RequestMapping(value="/savePurchaseAppAttach")
	public String savePurchaseAppAttach(HttpServletResponse httpServletResponse,PurchaseAppAttach purchaseAppAttach,BigDecimal count){
		//更新主数据的总金额
		purchaseAppService.updatePurchaseAppTotalAmt(purchaseAppAttach.getAppNo(), count);
		//新增物料明细
		boolean bl = purchaseAppAttachService.savePurchaseAppAttach(purchaseAppAttach);
		if(bl){
			OutputJson(httpServletResponse,new Json("提示","保存成功!",bl));
		}else{
			OutputJson(httpServletResponse,new Json("提示","出错了,保存失败!",bl));
		}
		return null;
	}
	/**
	 * @Title: createCombogrid 
	 * @Description: TODO 渲染使用人和保管人下拉列表
	 * @param @param httpServletResponse
	 * @param @param q
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月17日 下午7:23:59
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="createCombogrid",method=RequestMethod.POST)
	public String createCombogrid(HttpServletResponse httpServletResponse,String q){
		GridModel gridModel = new GridModel();
		gridModel.setRows(userService.findUserList(q));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	/**
	 * @Title: delPurchaseAppAttach 
	 * @Description: TODO 删除
	 * @param @param httpServletResponse
	 * @param @param purchaseAppAttach
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月18日 上午11:43:19
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="delPurchaseAppAttach",method=RequestMethod.POST)
	public String delPurchaseAppAttach(HttpServletResponse httpServletResponse,String ids,String appNo,BigDecimal count){
		PurchaseApp purchaseApp = purchaseAppService.findPurchaseAppByAppNo(appNo);
		//主数据总金额
		BigDecimal totalAmt = purchaseApp.getTotalAmt();
		//新的总金额
		BigDecimal newTotalAmt = totalAmt.subtract(count);
		//更新主数据
		purchaseAppService.updatePurchaseAppTotalAmt(appNo, newTotalAmt);
		//删除该条子数据
		boolean bl = purchaseAppAttachService.delPurchaseAppAttachByPsaId(ids);
		if(bl){
			OutputJson(httpServletResponse,new Json("提示","删除成功!",bl));
		}else{
			OutputJson(httpServletResponse,new Json("提示","出错了,删除失败!",bl));
		}
		return null;
	}
	/**
	 * 根据申请编号查询PurchaseAppAttach的集合
	 * @Title: findPurchaseAppAttachList 
	 * @Description: TODO
	 * @param @param appNo
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月21日 上午10:54:57
	 * @throws
	 */
	@RequestMapping(value="findPurchaseAppAttachList",method=RequestMethod.POST)
	public ModelAndView findPurchaseAppAttachList(HttpServletRequest httpServletRequest,String appNo){
		List<PurchaseAppAttachModel> purchaseAppAttachModelList =  purchaseAppAttachService.findPurchaseAppAttachList(appNo);
		httpServletRequest.setAttribute("PurchaseAppAttachList", purchaseAppAttachModelList);
		ModelAndView mv = new ModelAndView("ad/purchaseApp/purchaseAppAttachView");
		return mv;
	}
	/**
	 * 物料列表（新增、修改页面grid数据）
	 * @Title: findAllPurchaseAppAttachList 
	 * @Description: TODO
	 * @param @param httpServletResponse
	 * @param @param appNo
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年9月24日 下午4:43:22
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value="findAllPurchaseAppAttachList",method=RequestMethod.POST)
	public String findAllPurchaseAppAttachList(HttpServletResponse httpServletResponse,String appNo){
		GridModel gridModel = new GridModel();
		gridModel.setRows(purchaseAppAttachService.findPurchaseAppAttachList(appNo));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
}
