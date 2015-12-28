package com.oasys.webService.client;

import java.util.ArrayList;
import java.util.List;

import com.oasys.webService.model.Role;
import com.oasys.webService.model.User;
import com.oasys.webService.wsCollection.WebServiceCollection;

public class WebServiceClient {

	
	public static void main(String[] args) throws Exception{
		WebServiceCollection webColl = new WebServiceCollection();
		//List<Department> departList = webColl.getDepartmentList();//部门列表
		List<User> userList = webColl.getUserList();//用户列表
		List<Role> roleList = Role.parse(userList);//角色列表
//		for (Role role : roleList) {
//			System.out.println(role.getRoleName());
//		}
//		for (User user : userList) {
//			if(!roleList.contains(user.getOffice())){
//				roleList.add(user.getOffice());
//			}
//		}
		System.exit(0);
	}
}
