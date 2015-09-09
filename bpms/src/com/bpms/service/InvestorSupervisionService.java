package com.bpms.service;

import java.util.List;

import com.bpms.model.vo.InvestorAndInvestProductModel;
import com.bpms.util.PageUtil;

/**
 * @ClassName: InvestorSupervisionService
 * @Description: 投资客户监管Service 
 * @author ZHANGJIAN
 * @date 2015年8月06日 下午13:21:00
 */
public interface InvestorSupervisionService {

	/**
	 * 获取 “到期客户明细” 信息。
	 * @param model 
	 * @return
	 */
	public Object[] findDueInvestorInfoDetails(PageUtil pageUtil, InvestorAndInvestProductModel model);

	
	/**
	 * 获取 “返息客户明细” 信息。
	 * @param model 
	 * @return
	 */	
	public Object[] findReturnInterestInvestorInfoDetails(PageUtil pageUtil, InvestorAndInvestProductModel model);
	
	/**
	 * @Title: toExportExcelFindAllList 
	 * @Description: TODO 到期客户明细表，导出excel
	 * @param @return
	 * @return List<InvestorAndInvestProductModel>
	 * @throws
	 */
	public List<InvestorAndInvestProductModel> toExportExcelFindAllList(InvestorAndInvestProductModel model);
	/**
	 * @Title: toExportExcelFindAllData 
	 * @Description: TODO 返息客户明细表，导出excel
	 * @param @param model
	 * @param @return
	 * @return List<InvestorAndInvestProductModel>
	 * @throws
	 */
	public List<InvestorAndInvestProductModel> toExportExcelFindAllData(InvestorAndInvestProductModel model);
}
