package com.oasys.service;

import java.util.List;
import java.util.Map;

import com.oasys.model.CredentialsApp;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.CredentialsAppModel;

public interface CredentialsAppService {
	//展示数据
	List<CredentialsAppModel> getList(Map<String, Object> map,PageUtil pageUtil);
	//统计总数
	Long getCount(Map<String, Object> map);
	//保存主表数据
	boolean saveCredentialsApp(CredentialsApp credentialsApp);
	//删除主表数据
	boolean delCredentialsApp(Integer id);
	//更改流程状态
	boolean updateProcStatus(Integer id,String status);
}
