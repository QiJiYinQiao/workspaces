package com.bpms.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("default-package")
@Namespace("/")
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 7493364888065600947L;
	public String searchName;
	public String searchValue;
	public String inserted;
	public String updated;
	public String deleted;
	public Integer page;
	public Integer rows;
	public String searchAnds;
	public String searchColumnNames;
	public String searchConditions;
	public String searchVals;
	public String IDS;

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

	public Integer getFirstResult() {
		return (getPage() - 1) * getRows();
	}

	public Integer getMaxResults() {
		return getRows();
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
					"yyyy-MM-dd HH:mm:ss",
					SerializerFeature.DisableCircularReferenceDetect);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
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
					"yyyy-MM-dd HH:mm:ss",
					SerializerFeature.DisableCircularReferenceDetect);
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}
	//不带时分秒
	public void OutputJson2(Object object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext
				.getResponse();
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

	//需求描述：fastjson生成json时，Value为Null的<key-value>键值对会默认不显示，该键值对在序列化为JSON字符串时，默认会被过滤掉，不发送到前端。
	//此方法作用：
	public void OutputJsonKeepNullValue(Object object) {
		PrintWriter out = null;
		HttpServletResponse httpServletResponse = ServletActionContext
				.getResponse();
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setCharacterEncoding("utf-8");
		String json = null;
		try {
			out = httpServletResponse.getWriter();
			json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss",
					SerializerFeature.DisableCircularReferenceDetect, 
					SerializerFeature.WriteMapNullValue);  //WriteMapNullValue——–是否输出值为null的字段,默认为false 
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(json);
		out.close();
	}	
	
	
	public DataModel getMessage(boolean flag) {
		DataModel json = new DataModel();
		if (flag) {
			json.setStatus(true);
			json.setMessage("数据更新成功！");
		} else {
			json.setMessage("提交失败了！");
		}
		return json;
	}

}
