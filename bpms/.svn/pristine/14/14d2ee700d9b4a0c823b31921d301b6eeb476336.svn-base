package com.bpms.service;

import java.util.List;

import com.bpms.view.model.ComboBoxModel;

/**
 * 
 * 获取地区和字典表的共同方法的接口<br>
 * 描述:主要是为前台页面的下拉列表框使用的
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public interface CommonService {
	/**
	 * 查询字典,根据主数据的code_myid,查出子数据
	 * 
	 * @param codeMyid
	 *            查询参数 ---所属类型
	 */
	List<ComboBoxModel> findTextArr(String codeMyid);

	/**
	 * 查询区域字典表
	 * @param parentId
	 *            上级地区的id
	 */
	List<ComboBoxModel> findAreaDic(String parentId);
	
	/**
	 * 查询区域字典表,返回所有省或是所有市或是所有县
	 */
	public String findAreaDicByCode(String code);
	
	/**
	 * 查询字典表，返回一个字典的json串
	 */
	public String findDicText(String codeMyid);
	
	/**
	 * 根据父 码 子码 查询子 名
	 * @param dictPcode 父码
	 * @param dictCode  子码
	 * @return
	 */
	public String findDictName(String dictPcode,String dictCode);
}
