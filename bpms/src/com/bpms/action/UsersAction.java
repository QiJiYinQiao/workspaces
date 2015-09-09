package com.bpms.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.Users;
import com.bpms.service.UserService;
import com.bpms.util.Constants;
import com.bpms.view.model.ComboBoxModel;

/**
 * 用户控制器
 * @author Sun
 *
 */
@Namespace("/users")
@Action(value = "usersAction")
public class UsersAction extends BaseAction{

	private static final long serialVersionUID = 7009161158346977305L;
	
	@Autowired
	private UserService userService;
	
	private Constants contacts;
	
	private String userid;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * 根据用户ID查询与该用户同角色的所有用户
	 */
	public void findUsers(){
		Integer userid = contacts.getCurrendUser().getUserId();
		List<Users> list = userService.findUsersByUserId(userid);
		List<ComboBoxModel> lists = castToListComBoxModel(list);
		OutputJson(lists);
	}
	
	/**
	 * 将返回的结果转换为List<ComboBoxModel>
	 * 
	 * @param list
	 *            需要转化的list
	 */
	private List<ComboBoxModel> castToListComBoxModel(List<Users> list) {
		List<ComboBoxModel> comboxList = new ArrayList<ComboBoxModel>();
		for(Users us : list){
			comboxList.add(new ComboBoxModel(String.valueOf(us.getUserId()),
					(String) us.getName()));
		}
		return comboxList;
	}
	
	public void findUserById(){
		Integer userId = Integer.parseInt(userid);
		Users u = userService.findUserById(userId);
		OutputJson(u);
	}
}
