package com.bpms.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.LoanorderAndContacts;
import com.bpms.service.LoanorderAndContactsService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单与紧急联系人表Action
 * @author panchuanhe
 * 2015/6/26
 */
@Namespace("/loanorderAndContacts")
@Action(value = "loanorderAndContactsAction")
public class LoanorderAndContactsAction extends BaseAction implements ModelDriven<LoanorderAndContacts>{
	
	private static final long serialVersionUID = 1L;
	private LoanorderAndContacts loanorderAndContacts;
	@Autowired
	private LoanorderAndContactsService loanorderAndContactsServiceImpl;
	//紧急联系人ids字符串
	private String contactIds;
	/**
	 * 保存
	 */
	public String saveLoanorderAndContacts(){
		try {
			//然后给该订单赋予新的紧急联系人
			if(StringUtils.isNotBlank(contactIds)){
				String[] ids = contactIds.split(",");
				loanorderAndContactsServiceImpl.saveLoanorderAndcontacts(loanorderAndContacts.getLoanOrderId(), ids);
				OutputJson(new DataModel("提示","保存成功.",true));
				return null;
			}else{
				OutputJson(new DataModel("提示","没有选择要保存的紧急联系人的信息!",false));
			}
		} catch (Exception e) {
			OutputJson(new DataModel("提示","保存失败",false));
		}
		return null;
	}
	
	@Override
	public LoanorderAndContacts getModel() {
		if(loanorderAndContacts == null){
			loanorderAndContacts = new LoanorderAndContacts();
		}
		return loanorderAndContacts;
	}

	public String getContactIds() {
		return contactIds;
	}

	public void setContactIds(String contactIds) {
		this.contactIds = contactIds;
	}
}
