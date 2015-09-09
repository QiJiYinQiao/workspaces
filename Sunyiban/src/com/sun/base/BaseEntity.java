package com.sun.base;
/**
 * 模型类的基类,提供所有表中共有的属性
 * @FileName: BaseEntity.java
 * @Author: Sunyiban
 * @Version: 1.0
 * @Date: 2015-09-01 10:48am
 * @Description: 
 * @Others: 
 * @FuntionList:
 * @History:
 * @Category:
 */
public class BaseEntity {
	private String id;				//ID
	private String creater;			//创建人
	private String createTime;		//创建时间
	private String modifier;		//修改人
	private String modifyTime;		//修改时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
