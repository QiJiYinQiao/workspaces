package com.bpms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.service.CommonService;

/**
 * 
 * 获取地区和字典表的共同Action<br>
 * 描述:主要是为前台页面的下拉列表框使用的
 * 
 * @author 刘洪虎 2015/05/07.
 * 
 * @version V1.00.
 * 
 *          更新履历： V1.00 2015/05/07 刘洪虎 创建.
 */
@Namespace("/common")
@Action(value = "commonAction")
public class CommonAction extends BaseAction {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 系统字典的所属类型. */
	private String codeMyid;

	/** 地区的上级的id. */
	private String parentId;

	/** 注入service. */
	@Autowired
	private CommonService commonService;
	
	//字典 父类型CODE
	private String dictPcode;
	
	//字典 子类型CODE
	private String dictCode;

	public String getDictPcode() {
		return dictPcode;
	}

	public void setDictPcode(String dictPcode) {
		this.dictPcode = dictPcode;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	/**
	 * 获取共同的字典的信息渲染combobox
	 * 
	 * @return combobox所需要的列表信息的json数据
	 */
	public String findTextArr() {
		OutputJson(this.commonService.findTextArr(codeMyid));
		return null;
	}

	/**
	 * 获取共同的地区的信息渲染combobox
	 * 
	 * @return 地区combobox所需要的列表信息的json数据
	 */
	public String findAreaDic() {
		OutputJson(this.commonService.findAreaDic(parentId));
		return null;
	}

	/**
	 * 获取区域数据字典
	 */
	public String getAreaDicText(){
		String msg = commonService.findAreaDicByCode(parentId);
		OutputJson(msg);
		return null;
	}
	
	public void findDictName(){
		String name = commonService.findDictName(dictPcode, dictCode);
		OutputJson(name);
	}
	
	/* 一下为属性的setter和getter方法 */
	public String getCodeMyid() {
		return codeMyid;
	}

	public void setCodeMyid(String codeMyid) {
		this.codeMyid = codeMyid;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



}
