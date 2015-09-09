package com.bpms.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.LoanOrder;
import com.bpms.model.vo.InvestorAndInvestProductModel;
import com.bpms.model.vo.LoanOrderModel;
import com.bpms.service.DebtMatchingService;
import com.bpms.util.Collections;


/**
 * @ClassName: DebtMatchingServiceImpl 
 * @Description: 债券匹配Service实现类
 * @author ZHANGJIAN 
 * @date 2015年8月19日  
 */
@Service("debtMatchingService")
public class DebtMatchingServiceImpl implements DebtMatchingService {
	
	private Logger log = Logger.getLogger(DebtMatchingServiceImpl.class);
	
	@Autowired
	public BaseDAO<LoanOrder> baseDAO;
	
	@Autowired	
	public BaseDAO<InvestorAndInvestProductModel> baseDAO02;		

	@SuppressWarnings("unchecked")
	@Override
	public LoanOrderModel findLoanOrderByLoanOrderId(String loanOrderId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT sp.PARM_VALUE AS '利率',");
		sql.append("lc.LOAN_ED_DATE as '到期日期',");
		sql.append("t.`NAME` as '姓名',");
		sql.append("t.ID_NO as '证件号码',");
		sql.append("lc.LOAN_EDU as '贷款金额',");
		sql.append("t.LOAN_PERIOD as '贷款期限',");
		sql.append("t.REPAY_METHOD as '还款方式',");
		sql.append("t.LOAN_ORDER_ID as '订单id',");
		sql.append("t.LOANER_ID as '贷款人id',");
		sql.append("lc.CONTRACT_NO as '合同编号',");
		sql.append("lc.LOAN_BG_DATE as '开始日期'");
		sql.append(" FROM T_BP_LOAN_ORDER t,T_SYS_PARAMETER sp,T_BP_LOAN_CONTRACT lc WHERE 1=1");
		sql.append(" AND t.LOAN_ORDER_ID = '"+loanOrderId+"'");
		sql.append(" AND sp.PARM_CODE = 'loan_rate'");
		sql.append(" AND t.LOAN_ORDER_ID = lc.LOAN_ORDER_ID");
		
		List<Object[]> list = baseDAO.findBySQL(sql.toString());
		//需要返回的实体类
		LoanOrderModel model = new LoanOrderModel();
		if(Collections.listIsNotEmpty(list)){
			Object[] obj = list.get(0);
			model.setRate(obj[0] == null?"":String.valueOf(obj[0]));
			model.setLoanEdDate(obj[1] == null?null:(Date)obj[1]);
			model.setName(obj[2] == null?"":String.valueOf(obj[2]));
			model.setIdNo(obj[3] == null?"":String.valueOf(obj[3]));
			model.setLoanEdu(obj[4] == null?new BigDecimal(0):(BigDecimal)obj[4]);
			model.setLoanPeriod(obj[5] == null?0:(Integer)obj[5]);
			model.setRepayMethod(obj[6] == null?"":String.valueOf(obj[6]));
			model.setLoanOrderId(obj[7] == null?"":String.valueOf(obj[7]));
			model.setLoanerId(obj[8] == null?"":String.valueOf(obj[8]));
			model.setContractNo(obj[9] == null?"":String.valueOf(obj[9]));
			model.setLoanBgDate(obj[10] == null?null:(Date)obj[10]);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvestorAndInvestProductModel> findListByOrderStatus(String investOrderIds,Date loanBgDate,InvestorAndInvestProductModel investorAndInvestProductModel) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("t.INVESTOR_NAME as '投资客户',");
			sql.append("t.ID_CARD as '身份证号码',");
			sql.append("ips.PROD_NAME as '理财产品',");
			sql.append("iap.INVEST_EDU as '理财金额',");
			sql.append("iap.USABLE_EDU as '可用余额',");
			sql.append("t.INVEST_ORDER_ID as '订单id',");
			sql.append("t.INVESTOR_ID as '投资人id',");
			sql.append("iap.END_DATE as '到期日期',");
			sql.append("t.CONTRACT_NO as '合同编号'");
			sql.append("FROM T_BP_INVEST_ORDER t ");
			sql.append("LEFT JOIN T_BP_ORDER_STATUS os ON t.ORDER_STATUS = os.STATUS_ID ");
			sql.append("LEFT JOIN T_BP_INVESTORDER_AND_PRODUCTS iap ON iap.INVEST_ORDER_ID = t.INVEST_ORDER_ID ");
			sql.append("LEFT JOIN T_BP_INVEST_PRODUCTS ips ON iap.PROD_ID = ips.PROD_ID ");
			sql.append("WHERE os.STATUS_CODE = 'InvestOrder_SalesCustCommAgree' ");
			sql.append("AND iap.USABLE_EDU > '0' ");
			sql.append("AND iap.END_DATE > '"+sdf.format(loanBgDate)+"'");
			if(StringUtils.isNotBlank(investOrderIds)){
				sql.append(" AND t.INVEST_ORDER_ID NOT IN (");
				String[] ids = investOrderIds.split(",");
				for (int i = 0; i < ids.length; i++) {
					if(i == ids.length-1){
						sql.append("'"+ids[i]+"')");
					}else{
						sql.append("'"+ids[i]+"',");
					}
				}
			}
			if(StringUtils.isNotBlank(investorAndInvestProductModel.getInvestorName())){
				sql.append(" AND t.INVESTOR_NAME like '%"+investorAndInvestProductModel.getInvestorName()+"%'");
			}
			if(investorAndInvestProductModel.getUsableEdu()!=null){
				sql.append(" AND iap.USABLE_EDU = '"+investorAndInvestProductModel.getUsableEdu()+"'");
			}
			if(investorAndInvestProductModel.getEndDate01()!=null){
				sql.append(" AND iap.END_DATE >= '"+sdf.format(investorAndInvestProductModel.getEndDate01())+"'");
			}
			if(investorAndInvestProductModel.getEndDate02()!=null){
				sql.append(" AND iap.END_DATE <= '"+sdf.format(investorAndInvestProductModel.getEndDate02())+"'");
			}
			sql.append(" ORDER BY iap.INVEST_EDU DESC");
			List<Object[]> list = baseDAO.findBySQL(sql.toString());
			//需要返回的
			List<InvestorAndInvestProductModel> modellist = new ArrayList<InvestorAndInvestProductModel>();
			if (Collections.listIsNotEmpty(list)) {
				InvestorAndInvestProductModel model = new InvestorAndInvestProductModel();
				for (int i = 0; i < list.size(); i++) {
					InvestorAndInvestProductModel cmodel = (InvestorAndInvestProductModel)model.clone();
					Object[] obj = list.get(i);
					cmodel.setInvestorName(obj[0] == null?"":String.valueOf(obj[0]));
					cmodel.setIdCrad(obj[1] == null?"":String.valueOf(obj[1]));
					cmodel.setProdName(obj[2] == null?"":String.valueOf(obj[2]));
					cmodel.setInvestEdu(obj[3] == null?new BigDecimal(0):(BigDecimal)obj[3]);
					cmodel.setUsableEdu(obj[4] == null?new BigDecimal(0):(BigDecimal)obj[4]);
					cmodel.setInvestOrderId(obj[5] == null?"":String.valueOf(obj[5]));
					cmodel.setInvestorId(obj[6] == null?"":String.valueOf(obj[6]));
					cmodel.setEndDate(obj[7] == null?null:(Date)obj[7]);
					cmodel.setContractNo(obj[8] == null?"":String.valueOf(obj[8]));
					modellist.add(cmodel);
				}
			}
			return modellist;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	/**
	 * 获取自动匹配后的投资订单。
	 * 
	 * 程序逻辑：
	 * 1、查询投资信息的条件说明：
	 * (1)条件说明
	 *   1)最优的 查询投资信息的时间条件： 投资截止日期 >= 贷款截止日期 ，既  投资订单的endDate > 贷款订单的EndDate。
	 *     此时，在自动匹配中，查询到的投资订单信息的截止日期 都是在贷款截止日期之后。
	 * 
	 *   2)次优的 查询投资信息的时间条件： 贷款开始日期 < 投资截止日期 < 贷款截止日期
	 *     贷款订单的beginDate < 投资订单的endDate < 贷款订单的EndDate
	 * 
	 *   3)声明boolean类型的变量：isTimeQueryOptimized
	 *     若为true，则表明  投资截止日期 >= 贷款截止日期
	 *     若为false，贷款开始日期 < 投资截止日期 < 贷款截止日期
	 * 
	 * (2)过程描述
	 *   1)当进行“自动债权匹配”时，默认先执行最优的时间条件来查询投资信息，此时 isTimeQueryOptimized = true
	 *     只有在当所有投资订单的投资总额 < 贷款合同额度 时，则执行次优的时间条件来查询投资信息，此时 isTimeQueryOptimized = false
	 * 
	 */	
	@Override
	public List<InvestorAndInvestProductModel> getAutoDebtMatchingInvestOrders(String loanOrderId) {
				
		boolean isFinalAutoMatchingSuccess = false;  //声明一个标志，表明自动匹配是否完成
		boolean isTimeQueryOptimized = true;         //声明一个标志，表明执行的是否是按照时间的最优条件查询的数据
		
		//1.根据loanOrderId获取 
		//get(0)-->获取贷款订单ID; get(1)-->获取贷款总额度，即合同金额; get(2)-->获取贷款开始日期; get(3)-->获取贷款截止日期
		List<String> loanInofList = getLoanInfoByLoanOrderId(loanOrderId);
		
		//2.查询投资订单数据。
		//当进行“自动债权匹配”时，默认先执行最优的时间条件来查询投资信息，既 投资订单的endDate > 贷款订单的EndDate。
		//此时 isTimeQueryOptimized = true
		List<InvestorAndInvestProductModel> matchInvestList = getMatchingInvestInfo(loanInofList, isTimeQueryOptimized);
		
		//3.进行计算和匹配 
		BigDecimal loanEdu = new BigDecimal(loanInofList.get(1));                      //获取贷款总额度，即合同金额 
		BigDecimal investTotalSum = calculateTotolInvestSum(matchInvestList);  //计算所有投资的总额
		log.info("贷款额度   -----> "+loanEdu.intValue());
		log.info("第一次查询后，投资总额----->"+investTotalSum.intValue());
		
		//(3.1)贷款合同金额  <= 投资总额度，则仅仅凭借“最优时间条件”查询出来的投资订单就可以完成匹配。
		if(loanEdu.compareTo(investTotalSum) <= 0){
			isTimeQueryOptimized = true;
			//开始匹配
			matchInvestList = matchingLoanAndInvestOrders(loanEdu, matchInvestList);
			isFinalAutoMatchingSuccess = true;  //完成债权匹配
			
		}
		//(3.2)贷款合同金额  > 投资总额度，则凭借“最优的 查询投资信息的时间条件”查询出来的投资订单“不能”完成匹配。
		//     需要进行第二次查询投资订单信息。
		else{
			isTimeQueryOptimized = false;	
			log.info("由于第一次查询的  所有订单的投资总额< 贷款额度，因此，需要再次查询投资订单数据");
			//查询投资订单数据
			List<InvestorAndInvestProductModel> tmpList = getMatchingInvestInfo(loanInofList, isTimeQueryOptimized);
			for(InvestorAndInvestProductModel model : tmpList){
				log.info("投资客户："+model.getInvestorName()+"\t 可用余额："+String.valueOf(model.getUsableEdu())+"\t 本笔投资匹配金额："+model.getMatchEdu()+"\t 到期日期："+new SimpleDateFormat("yyyy-MM-dd").format(model.getEndDate()));
				//把新查询出的数据   追加到 原来的数据List中。				
				matchInvestList.add(model);
			}
			//重新计算投资总额			
			investTotalSum = calculateTotolInvestSum(matchInvestList);  //计算所有投资的总额
			log.info("再次计算投资总额度为：--->"+investTotalSum);
			
			if(loanEdu.compareTo(investTotalSum) <= 0){
				log.info("第二次查询投资订单之后，可以进行匹配，开始匹配....");
				matchInvestList = matchingLoanAndInvestOrders(loanEdu, matchInvestList);	
				isFinalAutoMatchingSuccess = true;  //完成债权匹配
			}else{
				log.info("第二次查询投资订单后，由于总投资额度不足，所以债权匹配失败。");
				isFinalAutoMatchingSuccess = false;
				matchInvestList.clear();
			}						
		}
				
		return matchInvestList;				
	}

	
	/**
	 * 对贷款和投资订单进行匹配的核心方法
	 * @AUTHOR ZHANGJIAN
	 * @param matchingInvestOrderList
	 * @return List<InvestorAndInvestProductModel>
	 */
	private List<InvestorAndInvestProductModel> matchingLoanAndInvestOrders(
			BigDecimal loanEdu, 
			List<InvestorAndInvestProductModel> matchInvestList) {
		
		log.info("正在匹配中，请稍候......");				
		
		//counter在此有两个作用：
		//(1)作为index下标，获取List中的数据       (2)记录成功匹配债权的投资订单数量 = counter + 1。
		Integer counter = 0;		
		
		BigDecimal loanUnMatched = loanEdu; //设置贷款总额中，未匹配的贷款额度
		
		while(loanUnMatched.compareTo(new BigDecimal("0")) > 0){
			//在债权匹配之前，获取本笔投资订单的对象，注意
			InvestorAndInvestProductModel curInvestOrderObj = matchInvestList.get(counter.intValue());			
			
			//0.开始本次匹配之前，保留本次匹配最开始的“未匹配的贷款额度”
			BigDecimal loanUnMatchedBeforeMatching = loanUnMatched;		
			
			//A1.开始匹配，计算未匹配的贷款额度
			loanUnMatched = loanUnMatched.subtract(curInvestOrderObj.getUsableEdu());
			
			if(loanUnMatched.compareTo(new BigDecimal("0")) < 0){
				
				//B1.设置未匹配的贷款额度
				loanUnMatched = new BigDecimal("0");

				//B2.设置本笔投资的实际投资额度
				curInvestOrderObj.setMatchEdu(loanUnMatchedBeforeMatching);
				
				//B3.设置投资订单的剩余额度
				BigDecimal investUsableEdu =  (curInvestOrderObj.getUsableEdu()).subtract(loanUnMatchedBeforeMatching);
				curInvestOrderObj.setUsableEdu(investUsableEdu);
				
				log.info("正在进行第"+(counter.intValue()+1)+"次匹配....");
				
				//B4.债权匹配成功，设置匹配成功的数据条数。
				log.info("匹配成功，完成匹配，共有"+(counter.intValue()+1)+"条数据。");				
				break;				
			}else{
				//A2.设置本笔投资的实际投资额度
				curInvestOrderObj.setMatchEdu(curInvestOrderObj.getUsableEdu());
				//A3.设置投资订单的剩余额度
				matchInvestList.get(counter.intValue()).setUsableEdu(new BigDecimal("0"));
				log.info("正在进行第"+(counter+1)+"次匹配....");
				++counter;
			}						
		}	
		
		if(matchInvestList.size() >= counter+1){			
			return matchInvestList.subList(0, (counter+1));
		}else{
			return matchInvestList;
		}								
	}
	
	/**
	 * 根据传入的投资订单List，计算出总的投资额度
	 * @AUTHOR ZHANGJIAN
	 * @param  matchingInvestOrderList
	 * @return BigDecimal
	 */
	private BigDecimal calculateTotolInvestSum(
			List<InvestorAndInvestProductModel> matchingInvestOrderList) {
		BigDecimal investTotalSum = new BigDecimal("0");   		
		for(InvestorAndInvestProductModel model : matchingInvestOrderList){
			investTotalSum = investTotalSum.add(model.getUsableEdu());
		}
		return investTotalSum;
	}
	
	
	/**
	 * 根据loanOrderId， 从贷款合同表中，获取“贷款总额” 和 “贷款截止日期”
	 * @AUTHOR ZHANGJIAN
	 * @param @param loanOrderId
	 * @param @return
	 * @return List<String>
	 */
	@SuppressWarnings("unchecked")
	public List<String> getLoanInfoByLoanOrderId(String loanOrderId){
		List<String> rtnList = new ArrayList<String>(); 		
		
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("t1.LOAN_ORDER_ID, ");  //获取贷款订单ID			
			sql.append("t1.LOAN_EDU, ");       //获取贷款总额度，即合同金额
			sql.append("t1.LOAN_BG_DATE, ");   //获取贷款开始日期
			sql.append("t1.LOAN_ED_DATE ");    //获取贷款截止日期
			sql.append("FROM t_bp_loan_contract t1 ");
			sql.append("WHERE ");
			sql.append("t1.LOAN_ORDER_ID = '"+loanOrderId.toString()+"'");			
			
			List<Object[]> queryResult = this.baseDAO.findBySQL(sql.toString());
			if(Collections.listIsNotEmpty(queryResult)){
				Object[] oneRow = queryResult.get(0);
				rtnList.add( oneRow[0]==null ? "" : String.valueOf(oneRow[0]) );
				rtnList.add( oneRow[1]==null ? "" : String.valueOf(oneRow[1]) );
				rtnList.add( oneRow[2]==null ? "" : String.valueOf(oneRow[2]) );
				rtnList.add( oneRow[2]==null ? "" : String.valueOf(oneRow[3]) );
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return rtnList;
	}
	
	/**
	 * 根据贷款订单，进行自动匹配获取能够匹配债权的投资订单。
	 * @AUTHOR ZHANGJIAN
	 * @param List args，包含贷款订单ID，贷款合同金额，贷款截止日期
	 * @return List<InvestorAndInvestProductModel>
	 */	
	@SuppressWarnings("unchecked")
	public List<InvestorAndInvestProductModel> getMatchingInvestInfo(List args, boolean isTimeQueryOptimized){
		//获取贷款订单的ID
		String loanOrderId = args.get(0).toString();
		//获取贷款开始日期
		String loanBgDate = args.get(2).toString();	
		//获取贷款截止日期
		String loanEnDate = args.get(3).toString();	
		
		//初始化匹配债权的投资订单List			
		List<InvestorAndInvestProductModel> debtMatchInvestOrderList = new ArrayList<InvestorAndInvestProductModel>();		
		
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.delete(0,sql.length()); //首先清空StringBuffer
			sql.append("SELECT ");
			sql.append("t_invest.INVESTOR_NAME, ");    //投资客户名称
			sql.append("t_invest.ID_CARD, ");          //身份证编号
			sql.append("t_invest.USABLE_EDU, ");       //可用余额
			sql.append("t_invest.INVEST_EDU, ");       //账户总额
			sql.append("t_invest.END_DATE, ");         //投资到期日期
			sql.append("t_invest.CONTRACT_NO, ");     //投资合同编号
			sql.append("t_invest.INVEST_ORDER_ID, ");     //投资订单ID
			sql.append("t_loan.LOAN_ORDER_ID, ");     //贷款相关的信息：贷款订单编号并没有封装到Model类中
			sql.append("t_loan.LOAN_BG_DATE, ");	  //贷款相关的信息：贷款开始日期并没有封装到Model类中		
			sql.append("t_loan.LOAN_ED_DATE ");	      //贷款相关的信息：贷款截止日期并没有封装到Model类中		
			sql.append("FROM ");
			sql.append("t_bp_loan_contract t_loan ");
			sql.append("INNER JOIN ( ");
			sql.append("SELECT ");
			sql.append("t1.INVEST_ORDER_ID, ");
			sql.append("t2.PROD_ID, ");
			sql.append("t1.INVESTOR_ID, ");
			sql.append("t1.CONTRACT_NO, ");
			sql.append("t1.INVESTOR_NAME, ");
			sql.append("t1.ID_CARD, ");
			sql.append("t2.INVEST_EDU, ");
			sql.append("t2.USABLE_EDU, ");
			sql.append("t2.END_DATE ");
			sql.append("FROM ");
			sql.append("t_bp_invest_order t1 ");
			sql.append("INNER JOIN t_bp_investOrder_and_products t2 ON t1.INVEST_ORDER_ID = t2.INVEST_ORDER_ID ");
			sql.append("INNER JOIN T_BP_ORDER_STATUS os ON t1.ORDER_STATUS = os.STATUS_ID ");
			sql.append("WHERE 1=1 ");
			sql.append("AND os.STATUS_CODE = 'InvestOrder_SalesCustCommAgree' ");
			sql.append(") t_invest ");
			//执行时间最优的查询条件  ----> 投 资订单的endDate > 贷款订单的EndDate。
			if(isTimeQueryOptimized){
				sql.append("ON t_invest.END_DATE >= '"+loanEnDate+"' ");
			}
			//按照次优的时间条件来查询：贷款订单的beginDate < 投资订单的endDate < 贷款订单的EndDate
			else{
				sql.append("ON t_invest.END_DATE > '"+loanBgDate+"' ");
				sql.append("AND t_invest.END_DATE < '"+loanEnDate+"' ");									
			}			
			sql.append("AND t_loan.OBLI_MATCH_STATUS = '0' ");                //从t_bp_loan_contract表中，选取债权匹配状态为：0(即未匹配)的数据
			sql.append("AND t_loan.LOAN_ORDER_ID = '"+loanOrderId+"' ");     
			sql.append("AND t_invest.USABLE_EDU > 0 ");      //选取“可用余额” > 0 的投资订单的数据。
			sql.append("ORDER BY ");
			sql.append("t_invest.END_DATE DESC");			                 //按照投资订单的到期日期，倒序排列投资相关数据
			
			List<Object> list = baseDAO02.findBySQL(sql.toString());
			
			InvestorAndInvestProductModel tmpObj = new InvestorAndInvestProductModel();
			
			if(Collections.listIsNotEmpty(list)){
				for(Object oneRowObj : list){
					Object[] rowArray = (Object[]) oneRowObj;
					InvestorAndInvestProductModel vo = (InvestorAndInvestProductModel) tmpObj.clone();
					vo.setInvestorName( rowArray[0]==null ? "" : String.valueOf(rowArray[0])  );  //设置投资客户名称
					vo.setIdCrad( rowArray[1]==null ? "" : String.valueOf(rowArray[1]) );         //设置身份证编号
					vo.setUsableEdu( rowArray[2]==null ? null : new BigDecimal(String.valueOf(rowArray[2])));  //设置可用余额
					vo.setInvestEdu( rowArray[3]==null ? null : new BigDecimal(String.valueOf(rowArray[3])));  //设置帐号总额，即投资金额
					vo.setEndDate( rowArray[4]==null ? null : new SimpleDateFormat("yyyy-MM-dd").parse( String.valueOf(rowArray[4])) ); //设置投资到期日期
					vo.setContractNo( rowArray[5]==null ? "" : String.valueOf(rowArray[5]) );      //设置合同编号
					vo.setInvestOrderId( rowArray[6]==null ? "" : String.valueOf(rowArray[6]) );     //设置投资订单ID
					debtMatchInvestOrderList.add(vo);
				}
			}												
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return debtMatchInvestOrderList;
	}

}