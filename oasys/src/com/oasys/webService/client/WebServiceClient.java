package com.oasys.webService.client;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.oasys.model.Department;
import com.oasys.model.User;
import com.oasys.service.webService.EmWebService;
import com.oasys.webService.wsCollection.WebServiceCollection;


public class WebServiceClient{

	public static void main(String[] args) throws Exception{
		WebServiceCollection webColl = new WebServiceCollection();
		//加载spring配置文件
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();  
		context.setValidating(false);  
		context.load("classpath*:*.xml");
		context.refresh();
		//获取Em中用户 角色 部门 列表 进行配置与入库
		EmWebService emService = context.getBean(EmWebService.class);
		List<Department> departList = webColl.getDepartmentList();//部门列表
		for (Department department : departList) {
			emService.saveEmDepartment(department);
		}
		List<User> userList = webColl.getUserList();//用户列表
		//将em中的user存入数据库中
		for (User u : userList) {
			emService.saveEmUser(u);
		}
		context.close();
		System.exit(0);
	}
}
