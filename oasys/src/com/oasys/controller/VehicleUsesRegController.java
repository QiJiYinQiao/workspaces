package com.oasys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oasys.model.VehicleUsesReg;
import com.oasys.service.OrganizationService;
import com.oasys.service.UserService;
import com.oasys.service.VehicleUsesRegService;
import com.oasys.util.Constants;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.GridModel;

@Controller
public class VehicleUsesRegController extends BaseController{
	@Autowired
	private VehicleUsesRegService vehicleUsesRegService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrganizationService organizationService;
	/**
	 * 展示数据
	 * @Title: getList 
	 * @Description: 展示数据
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月13日 下午2:58:44
	 * @throws
	 */
	@RequestMapping(value="vehicleUsesReg/index")
	public String getList(HttpServletRequest request,HttpServletResponse response,Integer page,Integer rows){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("beginTime", request.getParameter("beginTime"));
		map.put("endTime", request.getParameter("endTime"));
		map.put("userName", request.getParameter("userName"));
		map.put("countAmount", request.getParameter("countAmount"));
		map.put("dept", request.getParameter("dept"));
		GridModel gridModel = new GridModel();
		PageUtil pageUtil = new PageUtil(page,rows);
		map.put("status", request.getParameter("procStatus"));
		map.put("dateBegin", request.getParameter("appDateBefore"));
		map.put("dateEnd", request.getParameter("appDateAfter"));
		map.put("daetApplyBegin", request.getParameter("appApplyDateBefore"));
		map.put("daetApplyEnd", request.getParameter("appApplyDateAfter"));
		gridModel.setRows( vehicleUsesRegService.getList(map,pageUtil));
		gridModel.setTotal(vehicleUsesRegService.getCount(map));
		OutputJson(response,gridModel);
		return null;
	}
	
	/**
	 * 添加记录
	 * @Title: addCarInfo 
	 * @Description: 添加记录
	 * @param @param response
	 * @param @param vrm
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月13日 下午4:32:14
	 * @throws
	 */
	@RequestMapping(value="vehicleUsesReg/addCarInfo")
	public String addCarInfo(HttpServletResponse response,HttpServletRequest request){
//		java.text.DecimalFormat df = new java.text.DecimalFormat("####0.00");  
		VehicleUsesReg vrm = new VehicleUsesReg();
		System.out.println(request.getParameter("vurId"));
		if(request.getParameter("vurId")!=null && !"".equals(request.getParameter("vurId"))){
			vrm.setVurId(Integer.parseInt(request.getParameter("vurId")));
		}
		vrm.setRegistrantNo(Constants.getCurrendUser().getUserId());//登记人
		vrm.setRegDate(DateUtils.parse(DateUtils.getNowTime()));//登记时间
		vrm.setUserNo(Integer.parseInt(request.getParameter("user")));//使用人
//		vrm.setDeptNo(Integer.parseInt(organizationService.getIdByName(request.getParameter("dept")).get(0)[0]+""));//部门
		vrm.setDeptNo(Integer.parseInt(request.getParameter("dept")));//部门
		vrm.setCarNo(request.getParameter("carNo"));//车牌号
		vrm.setUserReson(request.getParameter("usesReson"));//使用事由
		vrm.setUserDateTime(DateUtils.parse(request.getParameter("usesDateTime")));//用车时间
		vrm.setOrigin(request.getParameter("origin"));//始发地
		vrm.setDestination(request.getParameter("destination"));//目的地
		vrm.setBgKilometer(Double.parseDouble(request.getParameter("bgKilometer")));//启程公里数
		vrm.setEdKilometer(Double.parseDouble(request.getParameter("edKilometer")));//结束公里数
		vrm.setGvDateTime(DateUtils.parse(request.getParameter("gvDateTime")));//归还时间
		vrm.setFuelCharge(Double.parseDouble(request.getParameter("fuelCharge")));//加油费
		vrm.setRoadToll(Double.parseDouble(request.getParameter("roadToll")));//路桥费
		vrm.setParkingFee(Double.parseDouble(request.getParameter("parkingFee")));//停车费
		System.out.println(request.getParameter("totalAMT")+"--");
		vrm.setTotalAMT(Double.parseDouble(request.getParameter("totalAMT")));//合计金额
		vrm.setRemark(request.getParameter("remark"));//备注信息
		boolean flag=vehicleUsesRegService.addCarInfo(vrm);
		OutputJson(response,getMessage(flag));
		return null;
	}
	
	/**
	 * 删除记录
	 * @Title: removeCarInfo 
	 * @Description: 删除记录
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @author Guo
	 * @return String
	 * @date 2015年10月14日 上午9:54:02
	 * @throws
	 */
	@RequestMapping(value="vehicleUsesReg/delCarInfo")
	public String removeCarInfo(HttpServletRequest request,HttpServletResponse response){
		boolean flag=vehicleUsesRegService.delCarInfo(request.getParameter("id"));
		OutputJson(response,getMessage(flag));
		return null;
	}
}
