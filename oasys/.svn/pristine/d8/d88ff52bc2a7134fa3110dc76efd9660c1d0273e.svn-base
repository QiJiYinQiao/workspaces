package com.oasys.service;

import java.util.List;
import java.util.Map;

import com.oasys.model.VehicleUsesReg;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.VehicleUsesRegModel;

public interface VehicleUsesRegService {
	//展示数据
	List<VehicleUsesRegModel> getList(Map<String, Object> map,PageUtil pageUtil);
	//添加记录
	boolean addCarInfo(VehicleUsesReg vrm);
	//删除记录
	boolean delCarInfo(String id);
	//获取总条数
	Long getCount(Map<String, Object> map);
}
