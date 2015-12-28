package com.oasys.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.oasys.model.VO.MyAppModel;
import com.oasys.service.MyAppService;
import com.oasys.viewModel.GridModel;

@Controller
@RequestMapping("/myAppController")
public class MyAppController extends BaseController {
	@Autowired
	private MyAppService myAppService;

	@ResponseBody
	@RequestMapping(value = "/findMyAppList")
	private String findMyAppList(HttpServletResponse httpServletResponse) {
		List<MyAppModel> myAppList = myAppService.findMyAppList();
		OutputJson(httpServletResponse, myAppList);
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/findMyAppSortList")
	private String findMyAppSortList(HttpServletResponse httpServletResponse,String businesskeyPre) {
		GridModel gridModel = new GridModel();
		List<MyAppModel> myAppList = myAppService.findMyAppListSort(businesskeyPre);
		gridModel.setRows(myAppList);
		gridModel.setTotal(Long.parseLong(String.valueOf(myAppList.size())));
		OutputJson(httpServletResponse, gridModel);
		return null;
	}
	@ResponseBody
	@RequestMapping(value = "/myAppSort")
	private ModelAndView myAppSort(HttpServletResponse httpServletResponse,String appName) {
		Map<String,String> map =new HashMap<String,String>();
		//行政相关申请,AD
				map.put("工牌申请","jsp/ad/badgeApply/badgeAppAttMain.jsp");
				map.put("车辆费用申请","jsp/ad/carRegister/vehicleExpensesAppMain.jsp");
				map.put("罚款通知提交申请","jsp/ad/penaltyNoticeSubmitApp/penaltyNoticeSubmitMain.jsp");
				map.put("名片申请","jsp/ad/callingCard/callingCardMain.jsp");
				map.put("物料采购申请","jsp/ad/purchaseApp/purchaseAppMain.jsp");
				map.put("固定资产采购申请","jsp/ad/purchaseApp/purchaseApp2Main.jsp");
				map.put("固定资产使用申请","jsp/ad/ppeUsedConfirm/ppeUsedConfirmMain.jsp");
				map.put("固定资产报废申请","jsp/ad/ppeScrap/ppeScrapList.jsp");
				map.put("固定资产移交申请","jsp/ad/ppeTurnover/ppeTurnoverMain.jsp");
				map.put("低值易耗品申请","jsp/ad/consumablesApp/consumablesAppMain.jsp");
				map.put("用章申请","jsp/ad/UseStampApp/useStampAppMain.jsp");
				map.put("费用申请","jsp/ad/expensesApp/expensesAppMain.jsp");
				//人力相关申请,PD
				map.put("人员需求申请","jsp/pd/staffRecruitApp/staffRecruitAppMain.jsp");
				map.put("员工转正申请","jsp/pd/empFullmemberApp/empFullmemberAppMain.jsp");
				map.put("员工调岗调薪申请","jsp/pd/empSalPositionChgApp/empSalAppList.jsp");
				map.put("员工离职申请","jsp/hr/empDimissionApp/empDimissionAppMain.jsp");
				map.put("外出申请","jsp/pd/outApp/outAppMain.jsp");
				map.put("出差申请","jsp/pd/BusinessTripApp/businessTripAppMain.jsp");
				map.put("休假申请","jsp/pd/leaveApp/leaveAppMain.jsp");
				map.put("加班申请","jsp/pd/overtime/overtimeMain.jsp");
				map.put("忘打卡申请","jsp/pd/empForgetPluginApp/empForgetPluginAppMain.jsp");
				//财务相关申请,FD
				map.put("借款申请","jsp/fd/loanApp/loanAppMain.jsp");
				map.put("差旅费报销申请","jsp/fd/travelExpensesApp/travelExpensesAppMain.jsp");
				map.put("支出凭单报销申请","jsp/ad/badgeApply/badgeAppAttMain.jsp");
				map.put("证章申请","jsp/fd/credentialsApp/credentialsAppMain.jsp");
				map.put("特批申请","jsp/fd/specialRatifyApp/specialRatifyAppMain.jsp");
				String name = appName.substring(0, appName.indexOf("申请")+2);
				Calendar cl = Calendar.getInstance();
				cl.add(Calendar.WEEK_OF_YEAR, -1);
				Date dateFrom = cl.getTime();
				SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(Map.Entry entry:map.entrySet()){
					if(String.valueOf(entry.getKey()).equals(name)){
						OutputJson(httpServletResponse, entry.getValue()+"@"+sdf.format(dateFrom));
					}
				}
				
		return null;
	}
	
	

}
