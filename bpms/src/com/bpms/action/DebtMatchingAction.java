package com.bpms.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.bpms.model.InvestOrder;
import com.bpms.model.vo.InvestorAndInvestProductModel;
import com.bpms.model.vo.LoanOrderModel;
import com.bpms.service.DebtMatchingService;
import com.bpms.service.ObligationMatchService;
import com.bpms.view.model.DataModel;
import com.bpms.view.model.GridModel;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @ClassName: DebtMatchingAction 
 * @Description: 债券匹配Action
 * @author ZHANGJIAN 
 * @date 2015年8月19日  
 */
@Namespace("/debtMatching")
@Action(value = "debtMatchingAction",results={@Result(name="success",location="/jsp/investOrder/debtPowerMatching.jsp")})
public class DebtMatchingAction extends BaseAction implements ModelDriven<LoanOrderModel> {
	
	private static final long serialVersionUID = 3249953202664749885L;
	@Autowired
	private DebtMatchingService debtMatchingService;   //注入债权匹配的Service实现类
	@Autowired
	private ObligationMatchService obligationMatchService;
	//实体对象
	private LoanOrderModel loanOrderModel;
	//投资订单id字符串
	private String investOrderIds;
	//可用余额字符串
	private String usableEdus;
	//投资金额字符串
	private String matchingEdus;
	//搜索条件
	private InvestorAndInvestProductModel investorAndInvestProductModel = new InvestorAndInvestProductModel();
	
	public String getMatchingEdus() {
		return matchingEdus;
	}

	public void setMatchingEdus(String matchingEdus) {
		this.matchingEdus = matchingEdus;
	}

	/**
	 * @Title: findLoanOrderByLoanOrderId 
	 * @Description: TODO 根据id查询一个对象
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年8月19日 上午11:24:40
	 * @throws
	 */
	public String findLoanOrderByLoanOrderId(){
		loanOrderModel = debtMatchingService.findLoanOrderByLoanOrderId(loanOrderModel.getLoanOrderId());
		return "success";
	}
	
	/**
	 * @Title: findListByOrderStatus 
	 * @Description: TODO 根据订单状态查询列表
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年8月19日 下午3:06:38
	 * @throws
	 */
	public String findListByOrderStatus(){
		GridModel gridModel = new GridModel();
		gridModel.setRows(debtMatchingService.findListByOrderStatus(investOrderIds,loanOrderModel.getLoanBgDate(),investorAndInvestProductModel));
		OutputJson2(gridModel);
		return null;
	}
	
	
	/**
	 * @Title: getAutoDebtMatchingData 
	 * @description 获取自动债权匹配的数据记录
	 * @author ZHANGJIAN
	 * @return String
	 * @throws
	 */
	public String getAutoDebtMatchingInvestOrders(){

		GridModel gridModel = new GridModel();
		//1.根据loarOrderid，获取“贷款总额”和“贷款截至日期”。
		if(StringUtils.isNotBlank(loanOrderModel.getLoanOrderId())){
			List<InvestorAndInvestProductModel> resultList = debtMatchingService.getAutoDebtMatchingInvestOrders(loanOrderModel.getLoanOrderId());
			gridModel.setRows(resultList);
			gridModel.setTotal(Long.parseLong(String.valueOf(resultList.size())));
		}			
		this.OutputJson2(gridModel);				
		return null;
	}	
	/**
	 * @Title: toAddObligationMatch 
	 * @Description: TODO 匹配成功并保存
	 * @param @return
	 * @author PANCHUANHE
	 * @return String
	 * @date 2015年8月20日 下午3:52:54
	 * @throws
	 */
	public String toAddObligationMatch(){
		boolean bl = obligationMatchService.persistenceObligationMatch(investOrderIds, usableEdus,matchingEdus,loanOrderModel.getContractNo());
		if(bl){
			OutputJson(new DataModel("提示","保存成功!",bl));
		}else{
			OutputJson(new DataModel("提示","保存失败!",bl));
		}
		return null;
	}
	
	@Override
	public LoanOrderModel getModel() {
		if(loanOrderModel == null){
			loanOrderModel = new LoanOrderModel();
		}
		return loanOrderModel;
	}

	public LoanOrderModel getLoanOrderModel() {
		return loanOrderModel;
	}

	public void setLoanOrderModel(LoanOrderModel loanOrderModel) {
		this.loanOrderModel = loanOrderModel;
	}

	public String getInvestOrderIds() {
		return investOrderIds;
	}

	public void setInvestOrderIds(String investOrderIds) {
		this.investOrderIds = investOrderIds;
	}

	public String getUsableEdus() {
		return usableEdus;
	}

	public void setUsableEdus(String usableEdus) {
		this.usableEdus = usableEdus;
	}

	public InvestorAndInvestProductModel getInvestorAndInvestProductModel() {
		return investorAndInvestProductModel;
	}

	public void setInvestorAndInvestProductModel(
			InvestorAndInvestProductModel investorAndInvestProductModel) {
		this.investorAndInvestProductModel = investorAndInvestProductModel;
	}

}
