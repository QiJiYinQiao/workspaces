package com.oasys.service;

import java.util.List;

import com.oasys.model.SystemCode;
import com.oasys.viewModel.TreeModel;

public interface SystemCodeService
{

	List<SystemCode> findSystemCodeList(Integer id);

	List<TreeModel> findSystemCodeList();

	boolean persistenceSystemCodeDig(SystemCode systemCode,String permissionName,Integer codePid);

	boolean delSystemCode(Integer codeId );

	List<SystemCode> findSystemCodeByType(String codeMyid );
	
	public boolean isExistsCode(SystemCode systemCode);
	/**
	 * 根据系统字典的dictCode获取子名称
	 * @Title: findSystemName 
	 * @Description: TODO
	 * @param @param parDictCode 父类型
	 * @param @param dictCode 子编码
	 * @param @return
	 * @author WANGXINCHENG
	 * @return String
	 * @date 2015年10月19日 下午3:18:10
	 * @throws
	 */
	String findSystemName(String parDictCode,String dictCode);

}
