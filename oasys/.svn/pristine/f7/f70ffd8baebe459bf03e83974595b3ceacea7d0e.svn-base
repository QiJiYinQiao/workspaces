package com.oasys.service;

import java.util.List;
import java.util.Map;

import com.oasys.model.PenaltyNoticeReg;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.PenaltyNoticeRegModel;

public interface PenaltyNoticeRegService {
	//展示数据
	List<PenaltyNoticeRegModel> getList(Map<String, Object> map,PageUtil pageUtil);
	//统计总数
	Long getCount(Map<String, Object> map);
	//删除记录
	boolean delFineInfo(String id);
	//添加信息
	boolean saveFine(PenaltyNoticeReg penaltyNoticeReg);
}
