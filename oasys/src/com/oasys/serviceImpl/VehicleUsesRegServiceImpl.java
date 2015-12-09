package com.oasys.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.VehicleUsesReg;
import com.oasys.service.VehicleUsesRegService;
import com.oasys.util.DateUtils;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.VehicleUsesRegModel;

@Service("vehicleUsesRegService")
public class VehicleUsesRegServiceImpl implements VehicleUsesRegService {
	@Autowired
	private PublicDao<VehicleUsesRegModel> publicDao;
	@Autowired
	private PublicDao<VehicleUsesReg> publicDaoV;
	//展示数据
	@Override
	public List<VehicleUsesRegModel> getList(Map<String, Object> map,PageUtil pageUtil) {
		StringBuffer stringBuffer = new StringBuffer("SELECT v.VUR_ID '编号',v.REG_DATE '登记日期',u.USER_NAME '使用人',o.FULL_NAME as '部门名称',v.CAR_NO '车牌号',v.USES_RESON '使用事由',v.USES_DATETIME '用车时间',v.ORIGIN '始发地',v.DESTINATION '目的地',v.BG_KILOMETER '启程公里数',v.ED_KILOMETER '结束公里数',v.GV_DATETIME '归还时间',");
		stringBuffer.append(" v.FUEL_CHARGE '加油费',v.ROAD_TOLL '路桥费',v.PARKING_FEE '停车费',v.TOTAL_AMT '合计金额',v.remark '备注',u.USER_ID '用户编号',o.ORGANIZATION_ID ");
		stringBuffer.append(" FROM t_oa_ad_vehicle_uses_reg v LEFT JOIN qqms.t_users u ON u.USER_ID = v.USER_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = v.DEPT_NO WHERE 1=1");
		if(StringUtils.isNotBlank(map.get("beginTime")+"") && map.get("beginTime")!=null){
			stringBuffer.append(" AND v.USES_DATETIME >='"+map.get("beginTime")+"'");
		}
		if(StringUtils.isNotBlank(map.get("endTime")+"") && map.get("endTime")!=null){
			stringBuffer.append(" AND v.USES_DATETIME <='"+map.get("endTime")+"'");
		}
		if(StringUtils.isNotBlank(map.get("userName")+"") && map.get("userName")!=null){
			stringBuffer.append(" AND u.USER_ID = "+map.get("userName")+"");
		}
		if(StringUtils.isNotBlank(map.get("countAmount")+"") && map.get("countAmount")!=null){
			stringBuffer.append(" AND v.TOTAL_AMT ='"+map.get("countAmount")+"'");
		}
		if(StringUtils.isNotBlank(map.get("dept")+"") && map.get("dept")!=null){
			stringBuffer.append(" AND o.ORGANIZATION_ID ="+map.get("dept")+"");
		}
		stringBuffer.append(" ORDER BY v.REG_DATE DESC");
		List<Object[]> list = publicDao.findBySQL(stringBuffer.toString());
		List<VehicleUsesRegModel> list2 = new ArrayList<VehicleUsesRegModel>();
		for (int i = 0; i < list.size(); i++) {
			Object[] object = list.get(i);
			VehicleUsesRegModel vrm = new VehicleUsesRegModel();
			vrm.setVurId(Integer.parseInt(object[0]+""));
			vrm.setRegDate(DateUtils.parse(object[1]+"",DateUtils.DATE_SMALL_STR));
			vrm.setUser(object[2]+"");
			vrm.setDeptNo(object[3]+"");
			vrm.setCarNo(object[4]+"");
			vrm.setUserReson(object[5]+"");
			vrm.setUserDateTime(DateUtils.parse(object[6]+""));
			vrm.setOrigin(object[7]+"");
			vrm.setDestination(object[8]+"");
			vrm.setBgKilometer(Double.parseDouble(object[9]+""));
			vrm.setEdKilometer(Double.parseDouble(object[10]+""));
			vrm.setGvDateTime(DateUtils.parse(object[11]+""));
			vrm.setFuelCharge(Double.parseDouble(object[12]+""));
			vrm.setRoadToll(Double.parseDouble(object[13]+""));
			vrm.setParkingFee(Double.parseDouble(object[14]+""));
			vrm.setTotalAMT(Double.parseDouble(object[15]+""));
			vrm.setRemark(object[16]+"");
			vrm.setUserId(Integer.parseInt(object[17]+""));
			vrm.setOrganizationId(Integer.parseInt(object[18]+""));
			list2.add(vrm);
		}
		return list2;
	}
	//添加记录
	@Override
	public boolean addCarInfo(VehicleUsesReg vrm) {
		boolean flag=false;
		try {
			publicDaoV.saveOrUpdate(vrm);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	//删除记录
	@Override
	public boolean delCarInfo(String id) {
		int count=publicDao.executeHql("delete VehicleUsesReg where vurId in ("+id+")");
		if(count>=1){
			return true;
		}else{
			return false;
		}
	}
	//统计总条数
	@Override
	public Long getCount(Map<String, Object> map) {StringBuffer stringBuffer = new StringBuffer("SELECT COUNT(1)");
		stringBuffer.append(" FROM t_oa_ad_vehicle_uses_reg v LEFT JOIN qqms.t_users u ON u.USER_ID = v.USER_NO");
		stringBuffer.append(" LEFT JOIN qqms.t_organization o ON o.ORGANIZATION_ID = v.DEPT_NO");
		if(StringUtils.isNotBlank(map.get("beginTime")+"") && map.get("beginTime")!=null){
			stringBuffer.append(" AND v.USES_DATETIME >='"+map.get("beginTime")+"'");
		}
		if(StringUtils.isNotBlank(map.get("endTime")+"") && map.get("endTime")!=null){
			stringBuffer.append(" AND v.USES_DATETIME <='"+map.get("endTime")+"'");
		}
		if(StringUtils.isNotBlank(map.get("userName")+"") && map.get("userName")!=null){
			stringBuffer.append(" AND u.USER_NAME ='"+map.get("userName")+"'");
		}
		if(StringUtils.isNotBlank(map.get("countAmount")+"") && map.get("countAmount")!=null){
			stringBuffer.append(" AND v.TOTAL_AMT ='"+map.get("countAmount")+"'");
		}
		if(StringUtils.isNotBlank(map.get("dept")+"") && map.get("dept")!=null){
			stringBuffer.append(" AND o.FULL_NAME ='"+map.get("dept")+"'");
		}
		Long count=publicDao.findTotalCount(stringBuffer.toString());
		return count;
	}

}
