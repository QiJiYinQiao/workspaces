package com.oasys.service;

import java.util.List;

import com.oasys.model.Parameter;
import com.oasys.viewModel.ComboBoxModel;

public interface CommonService {

	/**
	 * 根据字典codeMyid获取下拉框选项集合
	 * @Title: findDicArray 
	 * @Description: TODO
	 * @param @param codeMyid
	 * @param @return
	 * @author PANCHUANHE
	 * @return List<ComboBoxModel>
	 * @date 2015年9月18日 下午5:21:52
	 * @throws
	 */
	List<ComboBoxModel> findDicList(String codeMyid);
	/**
	 *  根据codeMyid和parentId查询字典
	 * @Title: findDicByParentId 
	 * @Description: TODO
	 * @param @param ParentId
	 * @param @return
	 * @author PANCHUANHE
	 * @return ComboBoxModel
	 * @date 2015年11月25日 下午3:54:09
	 * @throws
	 */
	List<ComboBoxModel> findDicByParentId(String codeMyid,String parentId);
	/**
	 * 根据parmCode获取系统参数
	 * @Title: findParameterByParmCode 
	 * @Description: TODO
	 * @param @param parmCode
	 * @author PANCHUANHE
	 * @return void
	 * @date 2015年11月30日 下午3:19:11
	 * @throws
	 */
	Parameter findParameterByParmCode(String parmCode);
}
