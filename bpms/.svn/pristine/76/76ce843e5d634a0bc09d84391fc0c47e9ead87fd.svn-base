package com.bpms.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.InvestOrder;
import com.bpms.model.InvestorderAndProducts;
import com.bpms.model.LoanContract;
import com.bpms.model.ObligationMatch;
import com.bpms.service.InvestOrderService;
import com.bpms.service.ObligationMatchService;
import com.bpms.util.Collections;

/**
 * @ClassName: ObligationMatchServiceImpl 
 * @Description: TODO 债权匹配impl
 * @author PANCHUANHE
 * @date 2015年8月20日 下午3:45:39
 */
@Service("obligationMatchService")
public class ObligationMatchServiceImpl implements ObligationMatchService{
	
    @Autowired
	private BaseDAO<ObligationMatch> baseDAO;
    @Autowired
    private BaseDAO<InvestorderAndProducts> investorderAndProductsDAO;
    @Autowired
    private BaseDAO<LoanContract> loanContractDAO;
    @Autowired
    private InvestOrderService investOrderService;
    
	@Override	
	public boolean persistenceObligationMatch(String investOrderIds,String usableEdus,String matchingEdus,String contractNo) {
		try {
			//投资订单id
			String[] ids = investOrderIds.split(",");
			//可用余额
			String[] keyongyue = usableEdus.split(",");
			//投资金额
			String[] touzijine = matchingEdus.split(",");
			for (int i = 0; i < ids.length; i++) {
				StringBuffer hql = new StringBuffer("from InvestorderAndProducts t where 1=1 AND t.investOrderId = '"+ids[i]+"'");
				//查询订单与产品关系表
				List<InvestorderAndProducts> InvestorderAndProductsList = investorderAndProductsDAO.find(hql.toString());
				//根据id查询订单
				InvestOrder investOrder = investOrderService.findByInvestOrderId(ids[i]);
				if(Collections.listIsNotEmpty(InvestorderAndProductsList) && investOrder!=null){
					//债权匹配信息表实体类
					ObligationMatch obligationMatch = new ObligationMatch();
					InvestorderAndProducts investorderAndProducts = InvestorderAndProductsList.get(0);
					investorderAndProducts.setUsableEdu(new BigDecimal(keyongyue[i]));
					//更新订单与产品关系表可用余额字段
					investorderAndProductsDAO.update(investorderAndProducts);
					//新增债权匹配信息表数据
					obligationMatch.setLcontractNo(contractNo);
					obligationMatch.setIcontractNo(investOrder.getContractNo());
					obligationMatch.setMatchEdu(new BigDecimal(touzijine[i]));
					obligationMatch.setUsableEdu(new BigDecimal(keyongyue[i]));
					obligationMatch.setInvestEdu(investorderAndProducts.getInvestEdu());
					obligationMatch.setDueDate(investorderAndProducts.getEndDate());
					baseDAO.save(obligationMatch);
					//更新贷款合同状态
					StringBuffer hql2 = new StringBuffer("update LoanContract t set t.obliMatchStatus = '1' where t.contractNo = '"+contractNo+"'");
					loanContractDAO.executeHql(hql2.toString());
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
