package com.oasys.test;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oasys.controller.BaseController;
import com.oasys.model.Organization;
import com.oasys.service.UserService;
import com.oasys.viewModel.DataModel;

/**
 *author:yuanzhongqiu
 *Time:2015年9月17日  上午9:39:28
 */
@Controller
@RequestMapping("/testController")
public class TestController extends BaseController{
	@Autowired
	UserService userService;
	
	
    @RequestMapping(value="/uploadFile",method=RequestMethod.GET)  
    public String uploadFile(HttpServletResponse response,HttpServletRequest request){  
		try {
			Integer userId = 1;
			Organization org = userService.findOrgByuserId(userId);
			System.out.println(org.getOrganizationId());
		} catch (IOException e) {
			e.printStackTrace();
		}  
        return null;
    }  
}


