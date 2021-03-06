package com.bpms.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.LoanorderAndCompany;
import com.bpms.service.LoanorderAndCompanyService;
import com.bpms.view.model.DataModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * bpms 公司信息与订单action
 * @author panchuanhe
 * @date 2015/6/29
 */
@Namespace("/loanorderAndCompany")
@Action(value = "loanorderAndCompanyAction")
public class LoanorderAndCompanyAction extends BaseAction implements ModelDriven<LoanorderAndCompany>{
	private static final long serialVersionUID = 1L;
	@Autowired
	private LoanorderAndCompanyService loanorderAndCompanyServiceImpl;
	private LoanorderAndCompany loanorderAndCompany;
	//工作单位ids
	private String comIds;
	
	
	/**
	 * 新增
	 */
	public String saveLoanorderAndCompany(){
		try {
			//在新建新的关系
			if(StringUtils.isNotBlank(comIds)){
				String[] ids = comIds.split(",");
				loanorderAndCompanyServiceImpl.saveLoanorderAndCompany(loanorderAndCompany.getLoanOrderId(),ids);
				OutputJson(new DataModel("提示", "保存成功.", true));
				return null;
			}else{
				OutputJson(new DataModel("提示","没有选择要保存的工作单位信息.",true));
			}
		} catch (Exception e) {
			OutputJson(new DataModel("提示","保存失败.",true));
		}
		return null;
	}
	
	
	
	
	public LoanorderAndCompany getLoanorderAndCompany() {
		return loanorderAndCompany;
	}
	public void setLoanorderAndCompany(LoanorderAndCompany loanorderAndCompany) {
		this.loanorderAndCompany = loanorderAndCompany;
	}
	@Override
	public LoanorderAndCompany getModel() {
		if(loanorderAndCompany == null){
			loanorderAndCompany = new LoanorderAndCompany();
		}
		return loanorderAndCompany;
	}
	public String getComIds() {
		return comIds;
	}
	public void setComIds(String comIds) {
		this.comIds = comIds;
	}
}
