package com.oasys.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.PpeStockInfo;
import com.oasys.service.OrganizationService;
import com.oasys.service.PPEScrapAppAttachService;
import com.oasys.service.PpeStockInfoService;
import com.oasys.service.UserService;
import com.oasys.service.WorkFlowTaskService;
import com.oasys.util.DateUtils;
import com.oasys.viewModel.WorkFlowTasksModel;


/**   
 * @Title: PPEScrapAppAttachController
 * @Package com.oasys.controller
 * @Description: 固定资产报废附加表Controller
 * @author lida  
 * @date 2015/9/25
 * @version V1.0   
 */
@Controller
@RequestMapping("/ppeScrapAppAttachController")
public class PPEScrapAppAttachController extends BaseController{
	
	/** 注入service. */
	@Autowired
	private PPEScrapAppAttachService ppeScrapAttachService;
	
	@Autowired
	private OrganizationService orgService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WorkFlowTaskService workTaskService;
	
	@Autowired
	private PpeStockInfoService stockInfoService;
	
	/** 
	 * @Title: addPPEScrapAppAttach
	 * @Description: 新增固定资产报废附加表申请
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/9/25
	 */
	@ResponseBody
	@RequestMapping(value="/addPPEScrapAppAttach",method=RequestMethod.POST)
	public String addPPEScrapAppAttach(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("ppeScrap") PpeScrapAppAttach ppeScrapAppAttach,BindingResult bindingResult) {
		try {
			//处理页面绑定Date时的异常 转化为Date存入数据库中
			if(StringUtils.isNotBlank(ppeScrapAppAttach.getBuyDateStr())){
				ppeScrapAppAttach.setBuyDate(DateUtils.parse(ppeScrapAppAttach.getBuyDateStr(),"YYYY-MM-DD"));
			}
			ppeScrapAttachService.saveOrUpdatePpeEntity(ppeScrapAppAttach);//新增或更新附加表信息
			OutputJson(httpServletResponse, getMessage(true));
		} catch (Exception e) {
			OutputJson(httpServletResponse, getMessage(false));
		}
		return null;
	}
	

	/** 
	 * @Title: findAllppeScrapAttach
	 * @Description: 查询固定资产报废申请附加表列表
	 * @param ppeScrapAttach 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/9/25
	 */
	@ResponseBody
	@RequestMapping(value="/findAllppeScrapAttach",method=RequestMethod.POST)
	public String findAllppeScrapAttach(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse,@ModelAttribute("ppeScrapAttach") PpeScrapAppAttach ppeScrapAttach,  BindingResult bindingResult){
		if(StringUtils.isNotBlank(ppeScrapAttach.getAppNo()) && ppeScrapAttach.getAppNo().split(",").length > 1){
			ppeScrapAttach.setAppNo(ppeScrapAttach.getAppNo().split(",")[1]);
		}
		if(StringUtils.isNotBlank(ppeScrapAttach.getAppNo())){
			OutputJson(httpServletResponse, ppeScrapAttachService.findPpeAttachList(ppeScrapAttach.getAppNo()));//根据APPNO申请编号 或者附加表列表
		}else{
			OutputJson(httpServletResponse, null);
		}
		return null;
	}
	
	/** 
	 * @Title: toAddPPEScrapAppAttach
	 * @Description: 跳转到固定资产项编辑页面
	 * @param psaID ID标识
	 * @author lida
	 * @return String
	 * @date 2015/9/28
	 */
    @RequestMapping(value="/toAddPPEScrapAppAttach")
	public ModelAndView toAddPPEScrapAppAttach(HttpServletRequest httpServletRequest,@RequestParam(value="psaId",required = true) String psaID){
    	ModelAndView mv = new ModelAndView("ad/ppeScrap/ppeScrapAttachAdd");
    	mv.addObject("ppeAttach", ppeScrapAttachService.getPpeScrapAttachByID(Integer.valueOf(psaID)));
		return mv;
	}
    
	//财务主管更改资产净值与资产残值
	@ResponseBody
	@RequestMapping(value="/savePpeNetAndSal",method=RequestMethod.POST)
	public void savePpeNetAndSal(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, WorkFlowTasksModel taskModel,PpeScrapAppAttach ppeAttach) {
		//判断是否更新资产净值和资产原值
		try {
			if(null != ppeAttach.getPpeNet() && null  !=ppeAttach.getPpeSalvageVal()){
				PpeScrapAppAttach pa = ppeScrapAttachService.getPpeScrapAttachByID(ppeAttach.getPsaId());
				pa.setPpeNet(ppeAttach.getPpeNet());	
				pa.setPpeSalvageVal(ppeAttach.getPpeSalvageVal());
				ppeScrapAttachService.saveOrUpdatePpeEntity(pa);
			}
			OutputJson(httpServletResponse, getMessage(true));
		} catch (Exception e) {
			OutputJson(httpServletResponse, getMessage(false));
		}

	}
    
	/** 
	 * @Title: toAddPPEScrapAppAttach
	 * @Description: 跳转到固定资产项编辑页面
	 * @param psaID ID标识
	 * @author lida
	 * @return String
	 * @date 2015/9/28
	 */
    @RequestMapping(value="/toAddPPEScrapAppAttachCwzg")
	public ModelAndView toAddPPEScrapAppAttachCwzg(HttpServletRequest httpServletRequest,@RequestParam(value="psaId",required = true) String psaID){
    	ModelAndView mv = new ModelAndView("ad/ppeScrap/ppeScrapAttachAddCwzg");
    	mv.addObject("ppeAttach", ppeScrapAttachService.getPpeScrapAttachByID(Integer.valueOf(psaID)));
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
    	Integer idCounts = ppeScrapAttachService.delPpeScrapAttach(ids);
    	if(idCounts > 0){
    		OutputJson(httpServletResponse, getMessage(true));
    	}else{
    		OutputJson(httpServletResponse, getMessage(false));
    	}
		return null;
	}
    
    /** 
	 * @Title: addPPEScrapAppAttach
	 * @Description: 新增固定资产报废附加表申请
	 * @param ppeScrap 页面绑定实体对象参数
	 * @author lida
	 * @return String
	 * @date 2015/9/25
	 */
	@ResponseBody
	@RequestMapping(value="/getPpeStockInfo",method=RequestMethod.POST)
	public void getPpeStockInfo(HttpServletRequest httpRequest,HttpServletResponse httpServletResponse, @ModelAttribute("stockInfo") PpeStockInfo stockInfo,BindingResult bindingResult) {
		JSONObject jo = new JSONObject();
		if(stockInfoService.isExistsPpeCode(stockInfo.getPpeCode())){
			if(null == ppeScrapAttachService.findPpeAttachByPpeNo(stockInfo.getPpeCode())){
				PpeStockInfo ppeStockInfo =stockInfoService.getPpeStockInfo(stockInfo);
				jo.put("ppeStock", ppeStockInfo);
			}else{
				jo.put("resultStr", "编号 "+stockInfo.getPpeCode()+" 已经申请报废");
			}
		}else{
			jo.put("resultStr", "编号 "+stockInfo.getPpeCode()+" 在库存中不存在");
		}
		OutputJson(httpServletResponse, jo);
	}
}
