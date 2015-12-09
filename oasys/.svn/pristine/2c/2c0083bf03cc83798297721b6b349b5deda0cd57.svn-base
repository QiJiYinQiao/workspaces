package com.oasys.service;

import java.util.List;

import com.oasys.model.Organization;
import com.oasys.viewModel.TreeModel;

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
	 * @author: LiDa
	 * @time:2015年9月17日 
	 * @Title:getRegionTypeById
	 * @Description:TODO 根据部门ID加载部门名称
	 * @param id
	 * @return orgName 
	 * @throws:
	 */
	public String getOrgNameByID(Integer id);
	/**
	 * 根据id获取部门对象
	 * @Title: findOrganizationByOrganizationId 
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @author PANCHUANHE
	 * @return Organization
	 * @date 2015年9月29日 下午3:14:32
	 * @throws
	 */
	public Organization findOrganizationByOrganizationId(Integer id);
	
	/**
	 * 根据UserId获取部门对象
	 * @Title: findOrganizationByUserId 
	 * @Description: TODO
	 * @param @param id
	 * @param @return
	 * @author LIDA
	 * @return Organization
	 * @date 2015年9月29日 下午3:14:32
	 * @throws
	 */
	public Organization findOrganizationByUserId(Integer id);
	
	/**
	 * 根据UserId获取部门类型 0总部 1分部
	 * @Title: findOrgDeptLevelByUserID 
	 * @Description: TODO
	 * @param userID 用户ID
	 * @author LIDA
	 * @return String
	 * @date 2015年10月16日 
	 * @throws
	 */
	public String findOrgDeptLevelByUserID(Integer userID);
	
	List<Object[]> getIdByName(String name);
	
	/***
	 * 根据treeLevel或name查询当前orgId所有的父节点中符合描述条件的结果集
	 * @param Organization 参数
	 * @return 组织机构列表List
	 */
	public List<Organization> findOrgParentListByRegionType(String orgID,String treeLevel);
	
	public Organization getOrgByParam(Organization org);
	
	//根据UserId查询出部门
	List<TreeModel> findOrganizationModelByUserId(Integer userId);
	
}
