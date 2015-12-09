package com.oasys.service;

import java.util.List;

import com.oasys.model.CredentialsAppAttach;
import com.oasys.util.PageUtil;
import com.oasys.viewModel.CredentialsAppModel;

public interface CredentialsAppAttachService {
	//保存附件表
	boolean saveCredentialsAppAttach(CredentialsAppAttach credentialsAppAttach);
	//展示数据
	List<CredentialsAppModel> getList(String appNo,PageUtil pageUtil);
	//统计附件总数
	Long getCount(String appNo);
	//删除附表信息
	boolean delCredentialsAppAttach(String ids);
	//根据编号删除附件信息
	boolean delCredentialsAppAttachByAppNo(Integer id);
}
