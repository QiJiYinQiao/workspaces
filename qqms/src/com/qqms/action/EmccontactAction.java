package com.qqms.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.qqms.model.Emccontact;
import com.qqms.service.EmccontactService;
import com.qqms.viewModel.GridModel;
/**
 * 财富客户紧急联系人Action
 * @author panchuanhe
 * @dateTime 2015/6/2
 */
@Namespace("/emccontact")
@Action(value="emccontactAction")
public class EmccontactAction extends BaseAction implements ModelDriven<Emccontact>{
	//实体类对象
	private Emccontact emccontact;
	//注入service层
	@Autowired
	private EmccontactService emccontactService;
	/**
	 * 查询紧急联系人列表
	 */
	public String findAllList(){
		GridModel gridModel = new GridModel();
		gridModel.setRows(emccontactService.findAllList(emccontact));//获取List
		gridModel.setTotal(emccontactService.getCount(emccontact));//总条数
		OutputJson(gridModel);
		return null;
	}

	@Override
	public Emccontact getModel() {
		if (null == emccontact) {
			emccontact = new Emccontact();
		}
		return emccontact;
	}
	public Emccontact getEmccontact() {
		return emccontact;
	}
	public void setEmccontact(Emccontact emccontact) {
		this.emccontact = emccontact;
	}
}
