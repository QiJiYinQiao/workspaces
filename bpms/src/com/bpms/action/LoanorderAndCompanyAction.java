package com.bpms.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.LoanorderAndCompany;
import com.bpms.service.LoanorderAndCompanyService;
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
			//先解除该订单之前绑定的工作单位关系
			loanorderAndCompanyServiceImpl.deleteByLoanOrderId(loanorderAndCompany.getLoanOrderId());
			//在新建新的关系
			if(StringUtils.isNotBlank(comIds)){
				String[] ids = comIds.split(",");
				for(String id:ids){
					LoanorderAndCompany lac = new LoanorderAndCompany();
					lac.setComId(id);
					lac.setLoanOrderId(loanorderAndCompany.getLoanOrderId());
					loanorderAndCompanyServiceImpl.saveLoanorderAndCompany(lac);
				}
				OutputJson("{\"msg\":\"保存成功!\",\"state\":"+true+"}");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		OutputJson("{\"msg\":\"保存失败!\",\"state\":"+false+"}");
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
