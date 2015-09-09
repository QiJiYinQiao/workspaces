package com.qqms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.qqms.model.SystemCode;
import com.qqms.service.BaseService;
import com.qqms.service.SystemCodeService;
import com.qqms.viewModel.Json;

@ParentPackage("default-package")
@Namespace("/")
@Action(value = "baseAction")
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 7493364888065600947L;
	@Autowired
	private BaseService baseService;
	@Autowired
	public SystemCodeService systemCodeService;
	public String searchName;
	public String searchValue;
	public String inserted;
	public String updated;
	public String deleted;
	public Integer page; //当前页为第几页，即为pageNum
	public Integer rows; //当前页共有多少行，即为pageSize
	public String searchAnds;
	public String searchColumnNames;
	public String searchConditions;
	public String searchVals;
	public String IDS;
	private String code;//数据字典
	
	public String getIDS() {
		return IDS;
	}
	public void setIDS(String iDS) {
		IDS = iDS;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getInserted() {
		return inserted;
	}

	public void setInserted(String inserted) {
		this.inserted = inserted;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public String getSearchAnds() {
		return searchAnds;
	}

	public void setSearchAnds(String searchAnds) {
		this.searchAnds = searchAnds;
	}

	public String getSearchColumnNames() {
		return searchColumnNames;
	}

	public void setSearchColumnNames(String searchColumnNames) {
		this.searchColumnNames = searchColumnNames;
	}

	public String getSearchConditions() {
		return searchConditions;
	}

	public void setSearchConditions(String searchConditions) {
		this.searchConditions = searchConditions;
	}

	public String getSearchVals() {
		return searchVals;
	}

	public void setSearchVals(String searchVals) {
		this.searchVals = searchVals;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void OutputJson(Object object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext
				.getResponse();
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
	
	public void OutputJson2(String object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext
				.getResponse();
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		try {
			out = httpServletResponse.getWriter();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(object);
		out.close();
	}

	public void OutputJson(Object object, String type) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext
				.getResponse();
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
	/**
	 * 获取字典值，拼成json串
	 */
	public String getDicText(){
//		String msg = baseService.findTextArr(code);
//		OutputJson(msg);
		List<SystemCode> codeList=systemCodeService.findSystemCodeByType(code);
		OutputJson(codeList);
		return null;
	}
	/**
	 * 获取区域数据字典
	 */
	public String getAreaDicText(){
		String msg = baseService.findAreaDic(code);
		OutputJson(msg);
		return null;
	}
}
