package com.oasys.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.oasys.viewModel.Json;
/**
 * 
 * @ClassName: LoginController
 * @Description: TODO
 * @Author xujianwei
 * @Version 1.0
 * @Date 2015年8月17日 下午2:24:06
 *
 */
@Controller
@RequestMapping("/")
public class BaseController {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(BaseController.class);
	
	public void OutputJson(HttpServletResponse httpServletResponse,Object object) {
		PrintWriter out = null;
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
//			json = JSON.toJSONStringWithDateFormat(object,
//					"yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	
	public void OutputJson2(HttpServletResponse httpServletResponse,Object object) {
		PrintWriter out = null;
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}

	public void OutputJson(HttpServletResponse httpServletResponse,Object object, String type) {
		PrintWriter out = null;
		httpServletResponse.setContentType(type);
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss");
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}

	public Json getMessage(boolean flag) {
		Json json = new Json();
		if (flag) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		} else {
			json.setMessage("提交失败了！");
		}
		return json;
	}
}


