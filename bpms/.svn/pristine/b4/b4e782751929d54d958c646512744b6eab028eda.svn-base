package com.bpms.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.InvestorAndContacts;
import com.bpms.service.InvestorAndContactsService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 紧急联系人与投资人Action
 * @author panchuanhe
 * 2015/7/15
 */
@Namespace("/InvestorAndContacts")
@Action("InvestorAndContactsAction")
public class InvestorAndContactsAction  extends BaseAction implements ModelDriven<InvestorAndContacts> {
	private static final long serialVersionUID = 1L;
	@Autowired
    private InvestorAndContactsService investorAndContactsServiceImpl;
    //实体类对象
    private InvestorAndContacts investorAndContacts;
    //紧急联系人ids字符串
    private String contactIds;
    //投资id
    private String investorId;
    
    /**
     * 保存或是更新
     * @return
     */
    public String saveInvestorAndContacts(){
    	//判断紧急联系人id和投资人id都存在
    	if(StringUtils.isNotBlank(contactIds) && StringUtils.isNotBlank(investorAndContacts.getInvestorId())){
    		//先删除该投资人之前和紧急联系人建立的关系
    		investorAndContactsServiceImpl.deleteByInvestorId(investorAndContacts.getInvestorId());
    		String[] ids = contactIds.split(",");
    		for(String contactId:ids){
    			InvestorAndContacts iac = new InvestorAndContacts();
    			iac.setContactId(contactId);//紧急联系人id
    			iac.setInvestorId(investorAndContacts.getInvestorId());//投资人id
    			//调用逻辑方法保存
    			investorAndContactsServiceImpl.persistenceInvestorAndContacts(iac);
    		}
    		DataModel json = new DataModel("提示","恭喜你,保存成功!",true);
    		OutputJson(json);
    		return null;
    	}
    	DataModel json = new DataModel("提示","出错了,保存失败!",false);
    	OutputJson(json);
    	return null;
    }
	@Override
	public InvestorAndContacts getModel() {
		if(null == investorAndContacts){
			investorAndContacts = new InvestorAndContacts();
		}
		return investorAndContacts;
	}
	public InvestorAndContacts getInvestorAndContacts() {
		return investorAndContacts;
	}
	public void setInvestorAndContacts(InvestorAndContacts investorAndContacts) {
		this.investorAndContacts = investorAndContacts;
	}
	public String getContactIds() {
		return contactIds;
	}
	public void setContactIds(String contactIds) {
		this.contactIds = contactIds;
	}
}
