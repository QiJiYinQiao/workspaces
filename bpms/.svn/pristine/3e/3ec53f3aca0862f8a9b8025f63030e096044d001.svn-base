package com.bpms.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.InvestOrder;
import com.bpms.model.InvestRedeem;
import com.bpms.model.vo.InvestRedeemFinanceDetails4ClientVO;
import com.bpms.mydao.invest.InvestRedeemMapper;
import com.bpms.service.InvestOrderService;
import com.bpms.service.InvestRedeemService;
import com.bpms.service.InvestRedeemWorkFlowService;
import com.bpms.util.Collections;
import com.bpms.util.Constants;
import com.bpms.util.DateUtils;

import common.Logger;

@Service("investRedeemService")
public class InvestRedeemServiceImpl implements InvestRedeemService {

	private Logger logger = Logger.getLogger(InvestRedeemServiceImpl.class);

	@Autowired
	private BaseDAO<InvestRedeem> investRedeemDao;

	@Autowired
	private InvestRedeemMapper investRedeemMapper;

	@Autowired
	private InvestOrderService investOrderService;

	@Autowired
	private InvestRedeemWorkFlowService investRedeemWorkFlowService;

	@Override
	public List<Map<String, Object>> findAlreadyApprovedInvestApplyList(
			Map<String, Object> param) {
		return investRedeemMapper.findAlreadyApprovedInvestApplyList(param);
	}

	@Override
	public Long countAlreadyApprovedInvestApplyList(Map<String, Object> param) {
		return investRedeemMapper.countAlreadyApprovedInvestApplyList(param);
	}

	@Override
	public List<Map<String, Object>> findInvestRedeemList(
			Map<String, Object> param) {
		return investRedeemMapper.findInvestRedeemList(param);
	}

	@Override
	public Long countInvestRedeemList(Map<String, Object> param) {
		return investRedeemMapper.countInvestRedeemList(param);
	}

	@Override
	public int updateInvestRedeemBeginDate(Map<String, Object> param) {
		return investRedeemMapper.updateInvestRedeemBeginDate(param);
	}

	@Override
	public Map<String, Object> findInvestRedeemInfo4HandleTask(
			Map<String, Object> param) {
		return investRedeemMapper.findInvestRedeemInfo4HandleTask(param);
	}

	/**
	 * 修改投资订单的 “流程类型”，将其类型在“投资申请流程”与“投资赎回类型”之间变换。
	 * 
	 * @Title: updateInvestOrderProcessType
	 * @Description: TODO
	 * @param @return
	 * @author ZHANGJIAN
	 * @return String
	 * @throws
	 */
	@Override
	public boolean updateInvestOrderProcessType(InvestOrder investOrder) {
		boolean isSuccess = false;
		try {
			isSuccess = investOrderService.updateInvestOrderProcessType(
					investOrder.getInvestOrderId(),
					investOrder.getInvestProcessType());
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 更改投资订单的流程状态，将其由“2(已结束状态)”更改为“0(未开启状态)”
	 * 
	 * @Title: updateInvestOrderProcessStatusToWeiKaiQi
	 * @author ZHANGJIAN
	 * @return String
	 * @date 2015年12月17日 上午10:00:09
	 */
	@Override
	public boolean updateInvestRedeemProcessStatus(InvestOrder investOrder) {
		boolean isSuccess = false;
		try {
			isSuccess = investOrderService.updateInvestRedeemProcessStatus(
					investOrder.getInvestOrderId(),
					investOrder.getProcessStatus());
			return isSuccess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 新增一笔投资赎回申请。
	 */
	@Override
	public boolean addNewInvestRedeem(InvestOrder investOrder) {
		// 1.修改投资订单类型
		// 修改该投资业务订单表(t_bp_invest_order)表中“INVEST_PROCESS_TYPE”字段，将其由“1(代表：投资申请流程)”修改为“2(代表：投资赎回流程)”
		boolean flag01 = updateInvestOrderProcessType(investOrder);

		// 2.修改投资赎回流程的流程状态
		// 修改该投资业务订单表(t_bp_invest_order)表中“PROCESS_STATUS”字段，将其修改为“0(代表：流程未开启)”
		boolean flag02 = this.updateInvestRedeemProcessStatus(investOrder);

		// 3.向t_bp_invest_redeem表中新增一条记录
		InvestRedeem investRedeem = new InvestRedeem();
		investRedeem.setInvestOrderId(investOrder.getInvestOrderId());
		boolean flag03 = persistenceInvestRedeem(investRedeem);

		if (true == flag01 && true == flag02 && flag03 == true) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteInvestRedeemRecord(InvestOrder investOrder) {
		// 1.修改投资订单类型
		// 修改该投资业务订单表(t_bp_invest_order)表中“INVEST_PROCESS_TYPE”字段，将其由“2(代表：投资赎回流程)”修改为“1(代表：投资申请流程)”
		boolean flag01 = updateInvestOrderProcessType(investOrder);

		// 2.修改投资赎回流程的流程状态
		// 修改该投资业务订单表(t_bp_invest_order)表中“PROCESS_STATUS”字段，将其修改为“2(代表：流程已结束)”
		boolean flag02 = this.updateInvestRedeemProcessStatus(investOrder);

		// 3.删除t_bp_invest_redeem表中的指定记录
		InvestRedeem investRedeem = new InvestRedeem();
		investRedeem.setInvestOrderId(investOrder.getInvestOrderId());
		boolean flag03 = doDeleteInvestRedeemRecord(investRedeem);

		if (true == flag01 && true == flag02 && flag03 == true) {
			return true;
		} else {
			return false;
		}
	}

	public boolean persistenceInvestRedeem(InvestRedeem investRedeemObj) {
		try {

			if (StringUtils.isBlank(investRedeemObj.getInvestRedeemId())) {
				investRedeemDao.save(investRedeemObj);
			} else {
				// investOrder.setOrderStatus(orderStatusServiceImpl.getOrderStatusByStatusCode(investOrder.getOrderStatus().getStatusCode()));
				investRedeemDao.update(investRedeemObj);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 真正执行删除InvestRedeem记录的方法，根据传入的InvestOrderId来执行删除。
	 * 
	 * @Title: doDeleteInvestRedeemRecord
	 * @Description: TODO
	 * @param @param investRedeem
	 * @param @return
	 * @author ZHANGJIAN
	 * @return boolean
	 * @date 2015年12月24日 下午3:11:45
	 * @throws
	 */
	protected boolean doDeleteInvestRedeemRecord(InvestRedeem investRedeem) {
		try {
			if (null != investRedeem.getInvestOrderId()) {
				String sql = "DELETE FROM t_bp_invest_redeem WHERE invest_order_id='"
						+ investRedeem.getInvestOrderId() + "'";
				int rtnVal = investRedeemDao.getCurrentSession()
						.createSQLQuery(sql).executeUpdate();
				if (rtnVal == 1) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public InvestRedeem getInvestRedeemByInvestOrderId(String investOrderId) {
		try {
			if (null != investOrderId) {
				String hql = "FROM InvestRedeem t1 WHERE t1.investOrderId='"
						+ investOrderId + "'";
				List<InvestRedeem> rtnValList = investRedeemDao.find(hql);
				return rtnValList.size() > 0 ? rtnValList.get(0) : null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean saveSubmitTask4InvestRedeem(InvestRedeem investRedeem,
			InvestOrder investOrder, String comment, String result,
			String processingResult) {

		boolean flag01 = false;

		// 1.修改InvestRedeem数据，并且持久化（保存“回款日期”，“银行同期活期存款利率”，“实际理财天数”）
		// 说明此时是“销客专员”角色的用户 办理任务。
		if (null != investRedeem && null != investRedeem.getHuiKuanDate()) {
			// 获得已存在的InvestRedeem对象
			InvestRedeem existInvestRedeemObj = this
					.getInvestRedeemByInvestOrderId(investOrder
							.getInvestOrderId());
			existInvestRedeemObj.setCurDepositInterestRate(investRedeem
					.getCurDepositInterestRate()); // 设置银行同期活期存款利率
			existInvestRedeemObj.setHuiKuanDate(investRedeem.getHuiKuanDate()); // 设置回款日期
			existInvestRedeemObj.setActualInvestDays(investRedeem
					.getActualInvestDays()); // 设置实际理财天数
			// 更新数据库中InvestRedeem信息。
			flag01 = this.persistenceInvestRedeem(existInvestRedeemObj);
		}

		// 2.提交流程信息
		 investRedeemWorkFlowService.saveSubmitTask(investOrder, comment,
		 result, processingResult);

		if (flag01 == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据投资订单ID，计算投资赎回客户的信息
	 * 
	 * @Title: getInvestRedeemFinanceDetailsVOByOrderId
	 * @Description: TODO
	 * @param @param investOrderId
	 * @param @return
	 * @author ZHANGJIAN
	 * @return InvestRedeemFinanceDetails4ClientVO
	 * @date 2015年12月26日 下午5:28:41
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public InvestRedeemFinanceDetails4ClientVO getInvestRedeemFinanceDetailsVOByOrderId(
			String investOrderId) {

		InvestRedeemFinanceDetails4ClientVO rtnObjVo = new InvestRedeemFinanceDetails4ClientVO();

		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("t_investOrder.INVEST_ORDER_ID AS 'investOrderId', ");
			sql.append("t_investOrder.INVESTOR_ID AS 'investorId', ");
			sql.append("t_investOrder.CONTRACT_NO AS 'contractNo', "); // 合同编号
			sql.append("t_investOrder.FINANCING_MGR AS 'financingMgr', "); // 理财顾问名称
			sql.append("t_investOrder.PROCESS_STATUS AS 'processStatus', ");
			sql.append("t_investOrder.INVEST_PROCESS_TYPE AS 'investProcessType', "); // 投资流程类型
			sql.append("t_investor.CH_NAME AS 'chName', ");
			sql.append("t_investor.INVESTOR_STATUS AS 'investorStatus', ");
			sql.append("t_products.PROD_ID AS 'prodId', "); // 理财产品ID
			sql.append("t_products.PROD_NAME AS 'prodName', "); // 理财产品名称
			sql.append("t_products.REPAYMENT_MODE as 'repaymentMode', "); // 理财还款方式
			sql.append("t_ivstOrder_prod.INVEST_EDU AS 'investEdu', "); // 理财金额
			sql.append("t_ivstOrder_prod.INTEREST_DATE AS 'interestDate', "); // 计息日期
			sql.append("t_redeem.actual_invest_days, "); // 实际理财天数
			sql.append("DATE_FORMAT(t_redeem.REDEEM_BEGIN_DATE,'%Y-%m-%d') AS 'redeemBeginDate', "); // 投资赎回申请日期
			sql.append("t_redeem.HUI_KUAN_DATE, "); // 回款日期
			sql.append("t_redeem.CUR_DEPOSIT_INTEREST_RATE, "); // 银行同期活期存款利率
			sql.append("t_products.ARS "); // 理财产品年化收益率
			sql.append("FROM ");
			sql.append("T_BP_INVEST_ORDER t_investOrder ");
			sql.append("LEFT JOIN T_BP_INVESTOR t_investor ON t_investOrder.INVESTOR_ID = t_investor.INVESTOR_ID ");
			sql.append("LEFT JOIN t_bp_investOrder_and_products t_ivstOrder_prod ON t_investOrder.INVEST_ORDER_ID = t_ivstOrder_prod.INVEST_ORDER_ID ");
			sql.append("LEFT JOIN t_bp_invest_products t_products ON t_ivstOrder_prod.PROD_ID = t_products.PROD_ID ");
			sql.append("LEFT JOIN t_bp_invest_redeem t_redeem ON t_investOrder.INVEST_ORDER_ID = t_redeem.INVEST_ORDER_ID ");
			sql.append("WHERE 1=1 ");
			sql.append("AND t_investor.INVESTOR_STATUS = 'A' ");
			sql.append("AND t_investOrder.INVEST_PROCESS_TYPE = '2' ");
			sql.append("AND t_investOrder.FINANCING_MGR = 149 ");
			sql.append("AND t_investOrder.INVEST_ORDER_ID = '" + investOrderId+ "' ");
			

			List<Object> tmpList = this.investRedeemDao.findBySQL(sql
					.toString());

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

			if (Collections.listIsNotEmpty(tmpList)) {
				for (Object tmpVoObj : tmpList) {
					Object[] objArray = (Object[]) tmpVoObj;
					rtnObjVo.setInvestOrderId(objArray[0] == null ? "" : String
							.valueOf(objArray[0]));
					rtnObjVo.setInvestorId(objArray[1] == null ? "" : String
							.valueOf(objArray[1]));
					rtnObjVo.setContractNo(objArray[2] == null ? "" : String
							.valueOf(objArray[2]));
					rtnObjVo.setFinancingMgr(objArray[3] == null ? "" : String
							.valueOf(objArray[3]));
					rtnObjVo.setProcessStatus(objArray[4] == null ? "" : String
							.valueOf(objArray[4]));
					rtnObjVo.setInvestProcessType(objArray[5] == null ? ""
							: String.valueOf(objArray[5])); // 投资流程类型
					rtnObjVo.setChName(objArray[6] == null ? "" : String
							.valueOf(objArray[6])); // 投资者名字
					rtnObjVo.setInvestorStatus(objArray[7] == null ? ""
							: String.valueOf(objArray[7])); // 投资者状态
					rtnObjVo.setProdId(objArray[8] == null ? "" : String
							.valueOf(objArray[8])); // 理财产品ID
					rtnObjVo.setProdName(objArray[9] == null ? "" : String
							.valueOf(objArray[9])); // 理财产品名字
					rtnObjVo.setRepaymentMode(objArray[10] == null ? ""
							: String.valueOf(objArray[10])); // 理财还款方式
					rtnObjVo.setInvestEdu(objArray[11] == null ? null
							: BigDecimal.valueOf(Double.valueOf(String
									.valueOf(objArray[11])))); // 理财金额
					rtnObjVo.setInterestDate(objArray[12] == null ? null : df
							.parse(String.valueOf(objArray[12]))); // 计息日期
					rtnObjVo.setActualInvestDays(objArray[13] == null ? null
							: Integer.parseInt(String.valueOf(objArray[13]))); // 实际理财天数
					rtnObjVo.setRedeemBeginDate(objArray[14] == null ? null
							: df.parse(String.valueOf(objArray[14]))); // 实际投资赎回申请日期
					rtnObjVo.setHuiKuanDate(objArray[15] == null ? null : df
							.parse(String.valueOf(objArray[15]))); // 回款日期
					rtnObjVo.setCurDepositInterestRate(objArray[16] == null ? null
							: BigDecimal.valueOf(Double.valueOf(String
									.valueOf(objArray[16])))); // 银行同期活期存款利率
					rtnObjVo.setArs(objArray[17] == null ? null : BigDecimal
							.valueOf(Double.valueOf(String
									.valueOf(objArray[17])))); // 理财产品年化收益率
				}
			}
			return rtnObjVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算投资赎回客户的详细信息
	 * 
	 * @Title: calculateInvestRedeemFinanceDetail
	 * @Description: TODO
	 * @param @param investOrderId
	 * @author ZHANGJIAN
	 * @return void
	 * @date 2015年12月26日 下午7:01:35
	 * @throws
	 */
	public InvestRedeemFinanceDetails4ClientVO getRedeemClientFinancialDetail(
			InvestRedeem investRedeem, InvestOrder investOrder) {
		// 在事务一致性正确的前提下，计算赎回客户明细信息，包括如下信息：
		// (1)获得根据investOrderId，获得InvestRedeemFinanceDetails4ClientVO
		InvestRedeemFinanceDetails4ClientVO redeemDetailsObj = getInvestRedeemFinanceDetailsVOByOrderId(investOrder
				.getInvestOrderId());

		// (2)开始计算违约金、服务费、已付利息、应回款、划扣日期、赎回申请日期、回款日期、理财天数等信息
		InvestRedeemFinanceDetails4ClientVO investRedeemDetailsObj = saveCalculateRedeemClientFinancialDetail(redeemDetailsObj);

		return investRedeemDetailsObj;
	}

	/**
	 * 计算赎回客户财务明细信息，包括如下内容： 违约金、服务费、已付利息、应回款、划扣日期、赎回申请日期、回款日期、理财天数等信息。
	 * 
	 * @Title: saveCalculateRedeemClientFinancialDetail
	 * @Description: TODO
	 * @param @param redeemDetailsObj
	 * @param @return
	 * @author ZHANGJIAN
	 * @return InvestRedeemFinanceDetails4ClientVO
	 * @date 2015年12月28日 下午3:48:11
	 * @throws
	 */
	protected InvestRedeemFinanceDetails4ClientVO saveCalculateRedeemClientFinancialDetail(
			InvestRedeemFinanceDetails4ClientVO redeemDetailsObj) {

		if (redeemDetailsObj != null) {

			/********************** 逻辑计算部分 ***********************/
			// 1、计算应付利息。
			BigDecimal yingFuInterest = getYingFuInterest(redeemDetailsObj);
			redeemDetailsObj.setYingFuInterest(yingFuInterest);

			// 2、计算已付利息。
			BigDecimal interestAlreadyPaid = getInterestAlreadyPaid(redeemDetailsObj);
			redeemDetailsObj.setInterestPaid4Redeem(interestAlreadyPaid);

			// 3、计算违约金：违约金=（本金+应付利息）*1%
			BigDecimal defaultPenalty = (redeemDetailsObj.getInvestEdu()
					.add(yingFuInterest)).multiply(new BigDecimal("0.01"));
			redeemDetailsObj.setDefaultPenalty(defaultPenalty);

			// 4、计算服务费，服务费=（本金+截止到回款当日累计利息）*X%
			BigDecimal serviceCharge = getServiceCharge(redeemDetailsObj);
			redeemDetailsObj.setServiceCharge(serviceCharge);

			// 5、计算应回款(回款金额=本金+应付利息-已付利息-违约金-服务费)
			BigDecimal moneyReturned4Redeem = null;
			// 获取投资总额
			BigDecimal investEdu = redeemDetailsObj.getInvestEdu();
			moneyReturned4Redeem = investEdu.add(yingFuInterest)
					.subtract(interestAlreadyPaid).subtract(defaultPenalty)
					.subtract(serviceCharge);
			redeemDetailsObj.setMoneyReturned4Redeem(moneyReturned4Redeem);

			/********************** 更新数据库部分 ***********************/
			// 更新数据库InvestRedeem表。
			InvestRedeem investRedeemObj = this
					.getInvestRedeemByInvestOrderId(redeemDetailsObj
							.getInvestOrderId());
			investRedeemObj.setInterestAlreadyPaid(redeemDetailsObj
					.getInterestPaid4Redeem()); // 设置已付利息
			investRedeemObj.setDefaultPenalty(redeemDetailsObj
					.getDefaultPenalty()); // 设置违约金
			investRedeemObj.setServiceCharge(redeemDetailsObj
					.getServiceCharge()); // 设置服务费
			investRedeemObj.setMoneyReturned(redeemDetailsObj
					.getMoneyReturned4Redeem()); // 设置应回款

			this.persistenceInvestRedeem(investRedeemObj);// 更新investRedeem对象。
		}

		return redeemDetailsObj;
	}

	// 获取已付利息。
	private BigDecimal getInterestAlreadyPaid(
			InvestRedeemFinanceDetails4ClientVO redeemDetailsObj) {

		BigDecimal interestAlreadyPaid = null;

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		// 判断理财产品是“月回息” 还是 “非月回息”。若理财产品是“月回息”，则要计算“已付利息”
		// (1)利随本清（每个月不回息）
		if (null != redeemDetailsObj.getRepaymentMode()
				&& redeemDetailsObj.getRepaymentMode().equalsIgnoreCase("A")) {
			interestAlreadyPaid = new BigDecimal("0");
		}
		// (2) 按月回息
		else if (null != redeemDetailsObj.getRepaymentMode()
				&& redeemDetailsObj.getRepaymentMode().equalsIgnoreCase("B")) {
			
			interestAlreadyPaid = new BigDecimal("0");

			// 按照当前理财产品的年华收益率，计算每一天的利息收益。
			BigDecimal dailyInterestRevenue = this.getDailyInterest(
					redeemDetailsObj.getInvestEdu(), redeemDetailsObj.getArs()
							.multiply(new BigDecimal("0.01")));
			
			// 按照当前理财产品的年华收益率，计算每个整月的利息收益。
			BigDecimal monthlyInterestRevenue = dailyInterestRevenue.multiply(new BigDecimal("30"));  //计算每个整月要付的利息
			
			// 声明计息日期
			Date interestDate = redeemDetailsObj.getInterestDate();
			// 声明回款日期
			Date huiKuanDate = redeemDetailsObj.getHuiKuanDate(); 

			// 计息日期所在月份的“返息日期”
			Date payInterestDate4JiXiDate = DateUtils
					.getMonthlyInterestPaidDateBySpecifiedDate(interestDate);
			// 回款日期所在月份的“返息日期”
			Date payInterestDate4HuiKuaiDate = DateUtils
					.getMonthlyInterestPaidDateBySpecifiedDate(huiKuanDate);

			// 前提：保证 “回款日期” > “计息日期”
			
			// 获取“计息日期”和“回款日期”之间的“净月份差值”。
			int monthsDiff = DateUtils.getMonthDiffByTwoDates(interestDate,
					huiKuanDate);

			// 若计息日期 和 回款日期 在 同一年，同一个月。
			if (monthsDiff == -1) {
				logger.info("计息日期 和 回款日期 在同一个月。计息日期月份："
						+ df.format(interestDate) + "，  回款日期月份："
						+ df.format(huiKuanDate));
				// 1.计算“回款日期 ”所在月份当月的已付利息。
				// (1)若 "回款日期" > "当月返息日期"，返回当月的利息。
				if( huiKuanDate.compareTo(payInterestDate4HuiKuaiDate) >= 0){					
					Integer daysDiff01 = 1 + DateUtils.timeDifferenceDay(interestDate, payInterestDate4JiXiDate);
					interestAlreadyPaid = dailyInterestRevenue.multiply(new BigDecimal(daysDiff01));
				}				
				
			}
			// 若计息日期 和 回款日期 不在同一个月，是紧邻的两个月。（比如：计息日期：2015-10-29，回款日期：2015-11-14）
			else if (monthsDiff == 0) {
				logger.info("计息日期 和 回款日期 是紧邻的两个月。计息日期月份："
						+ df.format(interestDate) + "，  回款日期月份："
						+ df.format(huiKuanDate));
				// 1.计算 "计息日期"所在月份当月的已付利息。
				// (1)若 "计息日期" <= "当月返息日期"，根据两者的天数差，计算当月返息数据。
				if (interestDate.compareTo(payInterestDate4JiXiDate) <= 0) {
					Integer daysDiff = 1 + DateUtils.timeDifferenceDay(payInterestDate4JiXiDate, interestDate);					
					interestAlreadyPaid = dailyInterestRevenue.multiply(new BigDecimal(daysDiff));					
				}
				// (2)若 "计息日期" > "当月返息日期"，则当月不返息。
				
				// 2.计算“回款日期 ”所在月份当月的已付利息。
				// (1)若 "回款日期" > "当月返息日期"，返回当月整月的利息。
				if( huiKuanDate.compareTo(payInterestDate4HuiKuaiDate) >= 0){
					interestAlreadyPaid = interestAlreadyPaid.add(monthlyInterestRevenue);
				}
				// (2)若 "回款日期" < "当月返息日期"，则当月 不返息。				

			}
			// 若计息日期 和 回款日期 不在同一个月，相差1个月以上。（比如：计息日期：2015-10-29，回款日期：2015-12-14）
			else if (monthsDiff >= 1) {
				logger.info("计息日期 和 回款日期 相差大于一个月。计息日期月份："
						+ df.format(interestDate) + "，  回款日期月份："
						+ df.format(huiKuanDate));

				// 1.计算 "计息日期"所在月份当月的已付利息。
				// (1)若 "计息日期" <= "当月返息日期"，根据两者的天数差，计算当月返息数据。
				if (interestDate.compareTo(payInterestDate4JiXiDate) <= 0) {
					Integer daysDiff03 = 1 + DateUtils.timeDifferenceDay(interestDate, payInterestDate4JiXiDate);					
					interestAlreadyPaid = dailyInterestRevenue.multiply(new BigDecimal(daysDiff03));					
				}
				// (2)若 "计息日期" > "当月返息日期"，则当月不返息。
				
				// 2.计算“计息日期 和 回款日期 ”中间所有间隔月份(整月份)的已付利息。				
				BigDecimal tmpInterest = monthlyInterestRevenue.multiply(new BigDecimal(monthsDiff));
				interestAlreadyPaid = interestAlreadyPaid.add(tmpInterest);

				// 3.计算“回款日期 ”所在月份当月的已付利息。
				// (1)若 "回款日期" > "当月返息日期"，返回当月整月的利息。
				if( huiKuanDate.compareTo(payInterestDate4HuiKuaiDate) >= 0){
					interestAlreadyPaid = interestAlreadyPaid.add(monthlyInterestRevenue);
				}
				// (2)若 "回款日期" < "当月返息日期"，则当月 不返息。
			}
		}
		return interestAlreadyPaid;
	}

	// 获取两个日志之间的天数差（比如，“赎回申请创建日期” 和 “回款日期”之间的时间差）。
	private Integer getDaysDiffBetweenDates(Date earlyDate, Date lateDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(lateDate);
		long huiKuanDateMillSec = cal.getTimeInMillis();
		cal.setTime(earlyDate);
		long redeemBeginDateMillSec = cal.getTimeInMillis();
		long daysDiff = (huiKuanDateMillSec - redeemBeginDateMillSec)
				/ (1000 * 60 * 60 * 24);
		return Integer.parseInt(String.valueOf(daysDiff));
	}

	/**
	 * 计算服务费： 服务费=（本金+截止到回款当日累计利息）*X%（根据赎回申请日至回款日时间，
	 * ≤30T扣除2%违约金，30<天数≤60T，扣除1%违约金；>60T,不需扣除服务费）
	 * 
	 * @Title: getServiceCharge
	 * @Description: TODO
	 * @param @param redeemDetailsObj
	 * @param @return
	 * @author ZHANGJIAN
	 * @return BigDecimal
	 * @date 2015年12月28日 下午4:52:03
	 * @throws
	 */
	private BigDecimal getServiceCharge(
			InvestRedeemFinanceDetails4ClientVO redeemDetailsObj) {
		BigDecimal serviceCharge = null;
		// 服务费率
		BigDecimal serviceChargeRatio = null;

		int daysDiff = DateUtils.timeDifferenceDay(
				redeemDetailsObj.getRedeemBeginDate(),
				redeemDetailsObj.getHuiKuanDate());

		// 根据赎回申请日至回款日时间， ≤30T扣除2%违约金，
		if (daysDiff <= 30 && daysDiff >= 0) {
			serviceCharge = redeemDetailsObj.getInvestEdu()
					.add(redeemDetailsObj.getYingFuInterest())
					.multiply(new BigDecimal("0.02"));
		}
		// 30<天数≤60T，扣除1%违约金；
		else if (daysDiff > 30 && daysDiff <= 60) {
			serviceCharge = redeemDetailsObj.getInvestEdu()
					.add(redeemDetailsObj.getYingFuInterest())
					.multiply(new BigDecimal("0.01"));
		}
		// >60T,不需扣除服务费
		else {
			serviceCharge = new BigDecimal("0");
		}
		return serviceCharge;
	}

	/**
	 * 获取应付利息（根据实际理财天数 和 收益率，确定应付利息） 利息计算规则： (1)投资期限不满45T 按照银行同期活期存款利率计算
	 * (2)投资期限已满45T不满90T按照钱富宝45T利率计算 (3)投资期限已满90T不满180T按照钱富宝90T利率计算
	 * (4)投资期限已满180T不满365T按照钱富宝180T利率计算
	 * 
	 * @Title: getYingFuInterest
	 * @param @param model
	 * @author ZHANGJIAN
	 * @return BigDecimal
	 * @date 2015年12月28日 下午3:14:59
	 */
	private BigDecimal getYingFuInterest(
			InvestRedeemFinanceDetails4ClientVO model) {

		BigDecimal yingFuInterest = null;

		// 获取一笔投资的投资额度
		BigDecimal investEdu = model.getInvestEdu();

		// (1)投资期限不满45T，按照“银行同期活期存款利率”计算
		if (model.getActualInvestDays() < Constants.INVEST_PERIOD_4_QIANFUBAO_45T) {
			logger.info("投资期限不满45T，按照“银行同期活期存款利率”计算");
			BigDecimal dailyInterest = model.getInvestEdu().multiply(
					model.getCurDepositInterestRate().multiply(
							new BigDecimal("0.01")));
			yingFuInterest = dailyInterest.multiply(BigDecimal.valueOf(model
					.getActualInvestDays()));
		}
		// (2)投资期限已满45T不满90T，按照钱富宝45T利率计算
		else if (model.getActualInvestDays() >= Constants.INVEST_PERIOD_4_QIANFUBAO_45T
				&& model.getActualInvestDays() < Constants.INVEST_PERIOD_4_QIANFUBAO_90T) {
			logger.info("投资期限：" + model.getActualInvestDays()
					+ "天，已满45T不满90T，按照钱富宝45T年化收益率计算");
			BigDecimal dailyInterest = getDailyInterest(investEdu,
					Constants.INTEREST_RATE_4_QIANFUBAO_45T);
			yingFuInterest = dailyInterest.multiply(BigDecimal.valueOf(model
					.getActualInvestDays()));
		}
		// (3)投资期限已满90T不满180T，按照钱富宝90T利率计算
		else if (model.getActualInvestDays() >= Constants.INVEST_PERIOD_4_QIANFUBAO_90T
				&& model.getActualInvestDays() < Constants.INVEST_PERIOD_4_QIANFUBAO_180T) {
			logger.info("投资期限：" + model.getActualInvestDays()
					+ "天，已满90T不满180T，按照钱富宝90T年化收益率计算");
			BigDecimal dailyInterest = getDailyInterest(investEdu,
					Constants.INTEREST_RATE_4_QIANFUBAO_90T);
			yingFuInterest = dailyInterest.multiply(BigDecimal.valueOf(model
					.getActualInvestDays()));
		}
		// (4)投资期限已满180T不满365T，按照钱富宝180T利率计算
		else if (model.getActualInvestDays() >= Constants.INVEST_PERIOD_4_QIANFUBAO_180T
				&& model.getActualInvestDays() < Constants.INVEST_PERIOD_4_QIANFUBAO_360T) {
			logger.info("投资期限：" + model.getActualInvestDays()
					+ "天，已满180T不满365T，按照钱富宝180T利率计算");
			BigDecimal dailyInterest = getDailyInterest(investEdu,
					Constants.INTEREST_RATE_4_QIANFUBAO_180T);
			yingFuInterest = dailyInterest.multiply(BigDecimal.valueOf(model
					.getActualInvestDays()));
		}

		return yingFuInterest;
	}

	/**
	 * 根据年化收益率和投资额度，获得每一天的利息。 注意：此方法非常重要，后续的方法等方法的计算都基于本方法提供的“每一天的利息”之上而获得的。
	 * 
	 * @param model
	 * @return
	 */
	protected BigDecimal getDailyInterest(BigDecimal investEdu,
			BigDecimal actualArs) {
		BigDecimal annualizedRevenue = investEdu.multiply(actualArs);
		// 获取“年化收益”每一天的平均利息
		BigDecimal dailyInterestRevenue = annualizedRevenue.divide(
				BigDecimal.valueOf(Long.parseLong("360")), 11,
				RoundingMode.HALF_UP);
		return dailyInterestRevenue;
	}

	/**
	 * 根据investOrderId，获取投资赎回时，团队经理和大团经理的名字。
	 * @Title: findTuanDuiJingLiAndDaTuanJingLiByOrderId 
	 * @Description: TODO
	 * @param @param param
	 * @param @return
	 * @author ZHANGJIAN
	 * @return Map<String,Object>
	 * @date 2015年12月31日 上午11:00:32
	 * @throws
	 */
	public Map<String, Object> findTuanDuiJingLiAndDaTuanJingLiByOrderId(Map<String, Object> param){		
		Map<String, Object> rtnVal = investRedeemMapper.findTuanDuiJingLiAndDaTuanJingLiByOrderId(param);		
		return rtnVal;
	}

	/**
	 * 导出赎回客户明细报表
	 * @Title: exportExcel4RedeemInvestorFinancialDetails 
	 * @Description: TODO
	 * @param @param param
	 * @param @return
	 * @author ZHANGJIAN
	 * @return List<Map<String,Object>>
	 * @date 2015年12月31日 上午11:39:30
	 * @throws
	 */
	@Override
	public List<Map<String, Object>> exportExcel4RedeemInvestorFinancialDetails(
			Map<String, Object> param) {
		
		//1.获取赎回客户明细List数据
		List<Map<String, Object>> redeemInvestorInfoList = this.investRedeemMapper.findRedeemInvestorFinancialDetails(param);
		
		//2.从返回的 赎回客户明细List中，查找每一条记录的investOrderId，再利用该investOrderId获得相应的“团队经理”和“大团经理”。
		for(  Map<String, Object> oneMapObj : redeemInvestorInfoList ) {
			String investOrderId = (String) oneMapObj.get("investOrderId");
			
			// 保存根据investOrderId查询到的“团队经理”和“大团经理”的用户名。
			Map<String, Object> userNamesMap = null;   
			
			if( StringUtils.isNotBlank(investOrderId) ){			
				Map<String, Object> param02 = new HashMap<String, Object>();
				param02.put("investOrderId", investOrderId);
				userNamesMap = this.investRedeemMapper.findTuanDuiJingLiAndDaTuanJingLiByOrderId(param02);
			}
			oneMapObj.putAll(userNamesMap);
		}
				
		return redeemInvestorInfoList;
	}

	
	
}
