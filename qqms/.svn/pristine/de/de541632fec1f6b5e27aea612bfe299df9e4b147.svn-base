package com.qqms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.qqms.model.Role;
import com.qqms.service.PermissionAssignmentService;
import com.qqms.util.Constants;
import com.qqms.viewModel.GridModel;
import com.qqms.viewModel.Json;

@Namespace("/permission")
@Action(value = "permissionAssignmentAction")
public class PermissionAssignmentAction extends BaseAction implements ModelDriven<Role>
{
	private static final long serialVersionUID = -7653440308109010857L;
	private PermissionAssignmentService permissionAssignmentService;
	private Integer id;
	private String checkedIds;
	private Role role;
	

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role )
	{
		this.role = role;
	}


	public String getCheckedIds()
	{
		return checkedIds;
	}

	public void setCheckedIds(String checkedIds )
	{
		this.checkedIds = checkedIds;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id )
	{
		this.id = id;
	}

	@Autowired
	public void setPermissionAssignmentService(PermissionAssignmentService permissionAssignmentService )
	{
		this.permissionAssignmentService = permissionAssignmentService;
	}
	
	/**  
	* 函数功能说明 TODO:按节点查询所有程式
	* Administrator修改者名字
	* 2013-5-10修改日期
	* 修改内容
	* @Title: findAllFunctionList 
	* @Description:
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String findAllFunctionList() throws Exception
	{
		OutputJson(permissionAssignmentService.findAllFunctionsList(id));
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:查询所有角色
	* Administrator修改者名字
	* 2013-5-20修改日期
	* 修改内容
	* @Title: findAllRoleList 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String findAllRoleList() throws Exception
	{
		Map<String, Object> map = searchRole();
		GridModel gridModel=new GridModel();
		gridModel.setRows(permissionAssignmentService.findAllRoleList(map, page, rows,true));
		gridModel.setTotal(permissionAssignmentService.getCount(map));
		OutputJson(gridModel);
		return null;
	}

	private Map<String, Object> searchRole()
	{
		Map<String, Object> map=new HashMap<String, Object>();
		if (null!=searchValue&&!"".equals(searchValue))
		{
			map.put(searchName, Constants.GET_SQL_LIKE+searchValue+Constants.GET_SQL_LIKE);
		}
		return map;
	}
	
	/**  
	* 函数功能说明 TODO:查询所有角色不分页
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: findAllRoleListNotPage 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String findAllRoleListNotPage() throws Exception
	{
		Map<String, Object> map = searchRole();
		GridModel gridModel=new GridModel();
		gridModel.setRows(permissionAssignmentService.findAllRoleList(map, null, null,false));
		OutputJson(gridModel);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:查询所有角色不分页
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: findAllRoleListNotPage1 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String findAllRoleListNotPage1() throws Exception
	{
		Map<String, Object> map = searchRole();
		OutputJson(permissionAssignmentService.findAllRoleList(map, null, null,false));
		return null;
	}
	/**  
	* 函数功能说明 TODO:有roleId获取角色权限
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: getRolePermission 
	* @Description: TODO:
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getRolePermission() throws Exception
	{
		OutputJson(permissionAssignmentService.getRolePermission(getModel().getRoleId()));
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:保存角色权限
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: savePermission 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String savePermission() throws Exception
	{
		Json json=new Json();
		if (permissionAssignmentService.savePermission(getModel().getRoleId(), checkedIds))
		{
			json.setStatus(true);
			json.setMessage("分配成功！查看已分配权限请重新登录！");
		}else {
			json.setMessage("分配失败！");
		}
		OutputJson(json);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:持久化角色
	* Administrator修改者名字
	* 2013-5-29修改日期
	* 修改内容
	* @Title: persistenceRole 
	* @Description:
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String persistenceRole() throws Exception
	{	
		Map<String, List<Role>> map=new HashMap<String, List<Role>>();
		map.put("addList", JSON.parseArray(inserted, Role.class));
		map.put("updList", JSON.parseArray(updated, Role.class));
		map.put("delList", JSON.parseArray(deleted, Role.class));
		Json json=new Json();
		if (permissionAssignmentService.persistenceRole(map)) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		}else {
			json.setMessage("提交失败了！");
		}
		OutputJson(json);
		return null;
	}
	
	/**  
	* 函数功能说明 TODO:弹窗持久化角色
	* Administrator修改者名字
	* 2013-6-14修改日期
	* 修改内容
	* @Title: persistenceRoleDlg 
	* @Description: 
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String persistenceRoleDlg() throws Exception
	{
/*		boolean flag = permissionAssignmentService.isExistsCode(getModel());
		Json json = new Json();
		if (flag) {
			json.setMessage("该角色编码已存在，请更换!");
		} else{
			json=getMessage(permissionAssignmentService.persistenceRole(getModel()));
		}*/
		OutputJson(getMessage(permissionAssignmentService.persistenceRole(getModel())),Constants.TEXT_TYPE_PLAIN);
		return null;
	}
	
	public String delRole() throws Exception{
		boolean flag = permissionAssignmentService.persistenceRole(getModel().getRoleId());
		Json json = new Json();
		json.setStatus(flag);//传入状态
		if(flag){//更新成功
			json.setMessage("数据更新成功!");
		}else{
			json.setMessage("该角色已被赋予用户,请解除关系后,在删除!");
		}
		OutputJson(json);
		return null;
	}
	public Role getModel()
	{
		if (null==role)
		{
			role =new Role();
		}
		return role;
	}
}
