package com.qqms.service;

import java.util.List;

import com.qqms.model.Organization;
import com.qqms.viewModel.TreeModel;

/**
 * 
 * 组织机构管理Service接口
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
public interface OrganizationService {

	/**
	 * 获取组织机构的信息
	 * 
	 * @return 组织机构的信息列表
	 */
	List<TreeModel> findOrganizationList();

	/**
	 * 根据ID获取组织机构的信息的下一级
	 * 
	 * @param id
	 *            获取组织就够信息的ID
	 * @return 获取组织机构的信息的下一的列表
	 */
	List<Organization> findOrganizationList(Integer id);

	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年6月12日 下午2:38:20
	 * @Title:findOrganizationByIdList
	 * @Description:TODO 查询组织列表,以用于列表显示组织名称（这里描述这个方法的作用）
	 * @return
	 * @throws:
	 */
	public List<Organization> findOrganizationByIdList();
	/**
	 * 增加的组织机构的信息
	 * 
	 * @param o
	 *            待增加组织机构信息实例
	 * @return 增加是否成功
	 */
	boolean persistenceOrganization(Organization o);

	/**
	 * 根据信息的ID,删除组织机构的信息
	 * 
	 * @param id
	 *            根据信息的ID
	 * @return 删除信息的是否成功
	 */
	boolean delOrganization(Integer id);
	
	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月21日 上午11:02:18
	 * @Title:getRegionTypeById
	 * @Description:TODO 根据id取到区域类型（这里描述这个方法的作用）
	 * @param id
	 * @return
	 * @throws:
	 */
	public String getRegionTypeById(Integer id);
	
	/**
	 * 
	 * @author:xujianwei
	 * @time:2015年10月9日 下午5:40:47
	 * @Title:getOrganizationCode
	 * @Description:TODO 根据id判断机构编码属于财富（CF）、借款（JK）、其它（QT）（这里描述这个方法的作用）
	 * @param id
	 * @return
	 * @throws:
	 */
	public String getOrganizationCode(Integer id);
}
