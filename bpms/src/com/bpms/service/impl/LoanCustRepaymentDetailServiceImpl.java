package com.bpms.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bpms.dao.BaseDAO;
import com.bpms.model.LoanContract;
import com.bpms.model.LoanCustRepaymentDetail;
import com.bpms.model.vo.CustomerRepaymentInfoModel;
import com.bpms.model.vo.LoanCustRepaymentDetailModel;
import com.bpms.service.CommonService;
import com.bpms.service.LoanContractService;
import com.bpms.service.LoanCustRepaymentDetailService;
import com.bpms.util.Collections;
import com.bpms.util.DateUtils;
import com.bpms.util.NumberFormatUtil;
import com.bpms.util.PageUtil;
import com.bpms.util.ReadExcel;

/**
 * 贷款客户还款明细实现类
 * 
 * @author liuhh
 *
 */
@Service("loanCustRepaymentDetailService")
public class LoanCustRepaymentDetailServiceImpl implements
		LoanCustRepaymentDetailService {

	@Autowired
	private BaseDAO<LoanCustRepaymentDetail> loanCustRepaymentDetailDao;

	@Autowired
	private CommonService commonService;

	@Autowired
	private LoanContractService contractService;

	@Override
	public boolean persistenceLoanCustRepaymentDetail(
			LoanCustRepaymentDetail loanCustRepaymentDetail) {
		if (StringUtils.isBlank(loanCustRepaymentDetail.getRdId())) {
			loanCustRepaymentDetailDao.save(loanCustRepaymentDetail);
		} else {
			loanCustRepaymentDetailDao.update(loanCustRepaymentDetail);
		}
		return true;
	}

	@Override
	public List<CustomerRepaymentInfoModel> findLoanCustRepaymentDetail(
			Map<String, Object> map, PageUtil pageUtil) {
		List<Object> list = this.loanCustRepaymentDetailDao.findBySql(
				createContractRepaymentDetailSql(map), pageUtil);
		List<CustomerRepaymentInfoModel> listcri = new ArrayList<CustomerRepaymentInfoModel>();
		if (Collections.listIsNotEmpty(list)) {
			for (Object cri : list) {
				Object[] o = (Object[]) cri;
				CustomerRepaymentInfoModel cusrepayInfo = new CustomerRepaymentInfoModel();
				cusrepayInfo.setContractNo((String) o[0]);
				cusrepayInfo.setContractSignDate((Date) o[1]);
				cusrepayInfo.setLoanBgDate((Date) o[1]);
				cusrepayInfo.setLoanerActName((String) o[2]);
				cusrepayInfo.setLoanerActNum((String) o[3]);
				cusrepayInfo.setLoanerBankName((String) o[4]);
				cusrepayInfo.setMonthlyRepayment(String.valueOf(o[5]));
				cusrepayInfo.setLoanPeriods((Integer) o[6]);
				cusrepayInfo.setLoanName((String) o[7]);
				cusrepayInfo.setLoanIdNo((String) o[8]);
				cusrepayInfo.setLoanMobileTel((String) o[9]);
				cusrepayInfo.setLoanEdu(String.valueOf(o[10]));
				cusrepayInfo.setLoanOrderId((String) o[11]);
				cusrepayInfo.setRepaymentBgDate((Date) o[12]);
				cusrepayInfo.setRepaymentEndDate((Date) o[13]);
				cusrepayInfo.setSalesMan((String) o[14]);
				cusrepayInfo.setOrganizationId(Integer.toString((int) o[15]));
				cusrepayInfo.setOrganizationName((String) o[16]);
				if (null != o[17]) {
					cusrepayInfo
							.setOverdueDays(((BigDecimal) o[17]).intValue());
				} else {
					cusrepayInfo.setOverdueDays(0);
				}
				cusrepayInfo.setLateFee((BigDecimal) o[18]);
				cusrepayInfo.setDefaultFnterest((BigDecimal) o[19]);
				cusrepayInfo.setRealRepmtAmt((BigDecimal) o[20]);
				if (null != o[21]) {
					cusrepayInfo.setPaidLoanPeriods(((BigDecimal) o[21])
							.intValue());
				} else {
					cusrepayInfo.setPaidLoanPeriods(0);
				}
				if (null != o[22]) {
					cusrepayInfo.setOverdueTimes(((BigDecimal) o[22])
							.intValue());
				} else {
					cusrepayInfo.setOverdueTimes(0);
				}
				if (null != o[23]) {
					cusrepayInfo.setLoanType((String) o[23]);
				}
				cusrepayInfo.setLoanTypeName(commonService.findDictName(
						"loan_type", o[23] + ""));
				cusrepayInfo.setLoanCity((String) o[24]);
				cusrepayInfo.setLcid((String) o[25]);
				listcri.add(cusrepayInfo);
			}
		}
		return listcri;
	}

	@Override
	public Long getCountoFLoanCustRepaymentDetail(Map<String, Object> map) {
		return loanCustRepaymentDetailDao.countBySql("SELECT COUNT(1) FROM ( "
				+ createContractRepaymentDetailSql(map) + " ) AS fo");
	}

	/**
	 * 导出明细汇总的sql拼接
	 */
	private String createContractRepaymentDetailSql(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT");
		sql.append(" 	lc.CONTRACT_NO");
		sql.append(" 	,lc.CONTRACT_SIGN_DATE");
		sql.append(" 	,lc.LOANER_ACT_NAME");
		sql.append(" 	,lc.LOANER_ACT_NUM");
		sql.append(" 	,lc.LOANER_BANK_NAME");
		sql.append(" 	,lc.MONTHLY_REPAYMENT");
		sql.append(" 	,lc.LOAN_PERIODS");
		sql.append(" 	,lc.LOANER");
		sql.append(" 	,lc.LOANER_IDNO");
		sql.append(" 	,lc.LOANER_TEL");
		sql.append(" 	,lc.LOAN_EDU");
		sql.append(" 	,lc.LOAN_ORDER_ID");
		sql.append(" 	,lc.REPAYMENT_BG_DATE");
		sql.append(" 	,lc.REPAYMENT_ED_DATE");
		sql.append(" 	,lo.SALESMAN");
		sql.append(" 	,lo.ORGANIZATION_ID");
		sql.append(" 	,orgz.FULL_NAME");
		sql.append(" 	,tlc.OVERDUE_DAYS");
		sql.append(" 	,tlc.LATE_FEE");
		sql.append(" 	,tlc.DEFAULT_INTEREST");
		sql.append(" 	,tlc.REAL_REPMT_AMT");
		sql.append(" 	,tlc.stat2");
		sql.append(" 	,tlc.stat3");
		sql.append(" 	,lo.LOAN_TYPE");
		sql.append(" 	,tbcar.LOAN_CITY");
		sql.append(" 	,lc.LC_ID");
		sql.append(" FROM");
		sql.append(" 	(");
		sql.append(" 		SELECT");
		sql.append(" 			tlc.CONTRACT_NO CONTRACT_NO,");
		sql.append(" 			SUM(tlc.OVERDUE_DAYS) OVERDUE_DAYS,");
		sql.append(" 			SUM(tlc.LATE_FEE) LATE_FEE,");
		sql.append(" 			SUM(tlc.DEFAULT_INTEREST) DEFAULT_INTEREST,");
		sql.append(" 			SUM(tlc.REAL_REPMT_AMT) REAL_REPMT_AMT,");
		sql.append(" 			SUM(CASE WHEN tlc.RPMT_STATUS = '2' THEN 1 ELSE 0 END ) stat2,");
		sql.append(" 			SUM(CASE WHEN tlc.RPMT_STATUS = '3' THEN 1 ELSE 0 END ) stat3");
		sql.append(" 		FROM");
		sql.append(" 			t_bp_loan_cust_repayment_detail tlc");
		sql.append(" 		WHERE");
		sql.append(" 		1=1");

		// 默认是当期
		Date currentPeriodDate = getCurrentPeriodDate();

		// 月还款日
		if (map.get("planRepmtDate") != null) {
			sql.append("  AND  tlc.PLAN_REPMT_DATE ='"
					+ new SimpleDateFormat(DateUtils.DATE_SMALL_STR).format(map
							.get("planRepmtDate")) + "'");
			currentPeriodDate = (Date) map.get("planRepmtDate");
		}

		// 查询当期还款的报表信息
		if (StringUtils.isNotBlank((String) map.get("isOverdue"))) {
			// 正常还款明细报表.1.当期的逾期天数=0
			if ("1".equals(map.get("isOverdue"))) {
				sql.append("		AND tlc.OVERDUE_DAYS = 0 AND DATE_FORMAT(tlc.PLAN_REPMT_DATE,'%Y-%m-%d') ='"
						+ new SimpleDateFormat(DateUtils.DATE_SMALL_STR)
								.format(currentPeriodDate) + "'");
			}
		}

		// 逾期
		if (StringUtils.isNotBlank((String) map.get("isOverdue"))) {
			// 逾期还款明细报表1.滞纳金>或02.罚息>0
			if ("2".equals(map.get("isOverdue"))) {
				sql.append(" AND (tlc.LATE_FEE  >  0 OR tlc.DEFAULT_INTEREST > 0 ) ");
			}
		}

		sql.append(" 		GROUP BY");
		sql.append(" 			tlc.CONTRACT_NO");
		sql.append(" 	) AS tlc");
		sql.append(" LEFT JOIN t_bp_loan_contract lc ON tlc.CONTRACT_NO = lc.CONTRACT_NO");
		sql.append(" LEFT JOIN t_bp_loan_order lo ON lo.LOAN_ORDER_ID = lc.LOAN_ORDER_ID");
		sql.append(" LEFT JOIN t_organization orgz ON orgz.ORGANIZATION_ID = lo.ORGANIZATION_ID");
		sql.append(" LEFT JOIN t_bp_credit_audit_report tbcar ON tbcar.LOAN_ORDER_ID = lo.LOAN_ORDER_ID");
		sql.append(" WHERE 1 = 1");

		if (!map.isEmpty()) {
			// 组织机构的ID
			// if (StringUtils.isNotEmpty((String) map.get("organizationId"))) {
			// sql.append(" AND lo.ORGANIZATION_ID = '"
			// + map.get("organizationId") + "'");
			// }

			// 客户名称
			if (StringUtils.isNotEmpty((String) map.get("loanName"))) {
				sql.append(" AND lc.LOANER LIKE '%" + map.get("loanName")
						+ "%' ");
			}

			// 合同编号
			if (StringUtils.isNotEmpty((String) map.get("contractNo"))) {
				sql.append("  AND lc.CONTRACT_NO LIKE '%"
						+ map.get("contractNo") + "%'");
			}

			// 还款开始日期至结束日期
			if (StringUtils.isNotEmpty((String) map.get("contractSignDateS"))) {
				if (StringUtils.isNotEmpty((String) map
						.get("contractSignDateE"))) {
					sql.append(" AND lc.CONTRACT_SIGN_DATE BETWEEN '"
							+ map.get("contractSignDateS") + "' AND '"
							+ map.get("contractSignDateE") + "'");
				} else {
					sql.append(" AND lc.CONTRACT_SIGN_DATE = str_to_date('"
							+ map.get("contractSignDateS") + "','%Y-%m-%d')");
				}

			}

			// 进件城市
			if (StringUtils.isNotBlank((String) map.get("loanCity"))) {
				sql.append(" AND tbcar.LOAN_CITY like '%" + map.get("loanCity")
						+ "%'");
			}
		}
		return sql.toString();
	}

	/**
	 * 获取当前期的还款日
	 */
	private Date getCurrentPeriodDate() {
		// 获取当期的天数是哪天，
		// 规则，本月的15号前一天都查询15号，30号同理
		// 本月的15号前两天都查询上个月的30（如果为二月则是28/29）号，30号同理
		Date currentPeriodDate = null;
		Date date = new Date();
		// 获取当前时间月份中的15号
		Date day15 = DateUtils.get15Day(date);
		Integer differenceDay15 = DateUtils.timeDifferenceDay(day15, date);
		// 判断是不是15前一天之前，如果是前一天之前，则获取上个月的30号，如果为2月则获取（28/29）
		if (differenceDay15 < -1) {
			currentPeriodDate = DateUtils.getPrev30Day(date);
		} else {
			// 获取当前时间月份中的30号，如果是二月则获取（28/29）
			Date day30 = DateUtils.get30Day(date);
			Integer differenceDay30 = DateUtils.timeDifferenceDay(day30, date);
			// 判断是否在30号前一天之前，如果在前一天之前，则获取本月分的15号
			if (differenceDay30 < -1) {
				currentPeriodDate = DateUtils.get15Day(date);
			} else {
				// 否则获取本月的30号，如果为二月则是（28/29）
				currentPeriodDate = DateUtils.get30Day(date);
			}
		}
		return currentPeriodDate;
	}

	@Override
	public List<LoanCustRepaymentDetail> findRepaymentDetailByCno(
			Map<String, Object> map, PageUtil pageUtil) {
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM LoanCustRepaymentDetail lcrd WHERE lcrd.contractNo = '"
				+ map.get("contractNo") + "'");
		Integer overdueDays = (Integer) map.get("overdueDays");
		if (null != overdueDays && 1 == overdueDays) {
			hql.append(" AND lcrd.overdueDays > 0 ");
		}
		List<LoanCustRepaymentDetail> list = loanCustRepaymentDetailDao.find(
				hql.toString(), pageUtil);
		return list;
	}

	@Override
	public Long getCountRepaymentDetail(Map<String, Object> map) {
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT COUNT(*) FROM LoanCustRepaymentDetail lcrd WHERE lcrd.contractNo = '"
				+ map.get("contractNo") + "'");
		Integer overdueDays = (Integer) map.get("overdueDays");
		if (null != overdueDays && 1 == overdueDays) {
			hql.append(" AND lcrd.overdueDays > 0 ");
		}
		List list = loanCustRepaymentDetailDao.find(hql.toString());
		return (Long) list.get(0);
	}

	@Override
	public void exportLoanCustRepaymentDetail(Map<String, Object> map)
			throws Exception {
		// 获取导出数据
		List<CustomerRepaymentInfoModel> modelList = this
				.findLoanCustRepaymentDetail(map, new PageUtil(0, 0));
		// 获取模板
		String srcXlsxPath = ServletActionContext.getServletContext()
				.getRealPath(File.separator + "excel" + File.separator + "loan")
				+ File.separator + "客户贷款明细表模板.xlsx";
		Workbook workbook = ReadExcel.openExcleFile(srcXlsxPath);
		
		// 组织表头
		SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_SMALL_STR);
		String loanCity = StringUtils.isNotBlank((String) map.get("loanCity")) ? (String) map.get("loanCity") : "全部";
		String contractNo = StringUtils.isNotBlank((String) map.get("contractNo")) ? (String) map.get("contractNo") : "全部";
		String loanName = StringUtils.isNotBlank((String) map.get("loanName")) ? (String) map.get("loanName") : "全部";
		String contractSignDateS = StringUtils.isNotBlank((String) map.get("contractSignDateS")) ? (String) map.get("contractSignDateS") : "/-/-/";
		String contractSignDateE = StringUtils.isNotBlank((String) map.get("contractSignDateE")) ? (String) map.get("contractSignDateE") : "/-/-/";
		String planRepmtDate = map.get("planRepmtDate") != null ? df.format((Date) map.get("planRepmtDate")) : df.format(getCurrentPeriodDate());
		String isOverdue = StringUtils.isNotBlank((String) map.get("isOverdue")) && map.get("isOverdue").equals("1") ? "当期正常还款" : "逾期还款";
		
		// 获取工作簿
		Sheet sheet = workbook.getSheetAt(0);// 获取页签

		// 获取第一行设置表头
		Cell cell = sheet.getRow(1).getCell(1);
		// 组织消息头部
		String title = MessageFormat.format(cell.getStringCellValue(),
				loanCity, contractNo, loanName, planRepmtDate,
				contractSignDateS, contractSignDateE, isOverdue);
		cell.setCellValue(title);
		// 判断信息是否为空
		if (Collections.listIsNotEmpty(modelList)) {
			Map<String, List<CustomerRepaymentInfoModel>> modelMap = handleCustomerRepaymentInfo(modelList);
			// 设置数据
			int count = 0;
			Set<Entry<String, List<CustomerRepaymentInfoModel>>> entrySet = modelMap
					.entrySet();
			for (Entry<String, List<CustomerRepaymentInfoModel>> entry : entrySet) {
				List<CustomerRepaymentInfoModel> list = entry.getValue();
				for (int i = 0; i < list.size(); i++) {
					// 第一行不用copy
					if (count > 0) {
						ReadExcel.copyRows(sheet, 5, 5, 4 + count);
					}
					Row row = sheet.getRow(4 + count);
					CustomerRepaymentInfoModel info = list.get(i);
					row.getCell(1).setCellValue(info.getContractNo());
					row.getCell(2).setCellValue(info.getLoanName());
					row.getCell(3).setCellValue(info.getLoanMobileTel());
					row.getCell(4).setCellValue(info.getLoanerBankName());
					row.getCell(5).setCellValue(info.getLoanerActNum());
					row.getCell(6).setCellValue(info.getLoanEdu());
					row.getCell(7).setCellValue(info.getMonthlyRepayment());
					row.getCell(8).setCellValue(info.getLateFee().doubleValue());
					row.getCell(9).setCellValue(info.getOverdueDays());
					row.getCell(10).setCellValue(info.getNow());
					row.getCell(11).setCellValue(info.getDefaultFnterest().doubleValue());
					row.getCell(12).setCellValue(info.getRealRepmtAmt() != null ? info.getRealRepmtAmt().doubleValue() : 0);
					row.getCell(13).setCellValue(info.getLoanerActNum());
					row.getCell(14).setCellValue("");
					row.getCell(15).setCellValue("");
					row.getCell(16).setCellValue("");
					row.getCell(17).setCellValue(info.getSalesMan());
					
					row.getCell(18).setCellValue("");
					row.getCell(19).setCellValue(info.getOverdueTimes());
					row.getCell(20).setCellValue(info.getLoanBgDate());
					row.getCell(21).setCellValue(info.getPaidLoanPeriods());
					row.getCell(22).setCellValue(info.getLoanPeriods());
					count++;
				}
				if (list.size() > 1) {
					sheet.addMergedRegion(new CellRangeAddress(4 + count - list.size(), 4 + count - 1, 0, 0));
				}
				sheet.getRow(4 + count - list.size()).getCell(0).setCellValue(entry.getKey());
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		response.setContentType("application/octet-stream");
		response.setHeader(
				"Content-disposition",
				"attachment;filename="
					+ URLEncoder.encode("客户贷款明细表-" 
					+ calendar.get(Calendar.YEAR)
					+ "-"+ calendar.get(Calendar.MONTH + 1)
					+ "-" + calendar.get(Calendar.DATE)
					+ ".xlsx", "utf-8"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	// 对信息进行分组
	private Map<String, List<CustomerRepaymentInfoModel>> handleCustomerRepaymentInfo(List<CustomerRepaymentInfoModel> modelList) {
		Map<String, List<CustomerRepaymentInfoModel>> map = new HashMap<String, List<CustomerRepaymentInfoModel>>();
		if (Collections.listIsNotEmpty(modelList)) {
			// 组织对应的key
			for (CustomerRepaymentInfoModel model : modelList) {
				map.put(model.getLoanCity(), null);
			}
			// 对信息进行分组
			Set<Entry<String, List<CustomerRepaymentInfoModel>>> entrySet = map	.entrySet();
			for (Entry<String, List<CustomerRepaymentInfoModel>> entry : entrySet) {
				List<CustomerRepaymentInfoModel> list = new ArrayList<CustomerRepaymentInfoModel>();
				for (CustomerRepaymentInfoModel model : modelList) {
					if (null!= entry.getKey() && entry.getKey().equals(model.getLoanCity())) {
						list.add(model);
					}
				}
				entry.setValue(list);
			}
		}
		return map;
	}

	@Override
	public LoanCustRepaymentDetail findCustRepaymentDeatilById(String id) {
		String hql = " FROM LoanCustRepaymentDetail lcrd WHERE lcrd.rdId = '" + id + "'";
		List<LoanCustRepaymentDetail> list = loanCustRepaymentDetailDao.find(hql);
		if (Collections.listIsNotEmpty(list))
			return list.get(0);
		else
			return null;
	}

	@Override
	public void updateOverdueReceivablesDetail() {
		List<LoanCustRepaymentDetail> list = loanCustRepaymentDetailDao
				.find(" FROM LoanCustRepaymentDetail o WHERE o.rpmtStatus !='2' ");
		for (LoanCustRepaymentDetail info : list) {
			if (info.getPlanRepmtDate() != null) {
//				Integer day = DateUtils.timeDifferenceDay(
//						info.getPlanRepmtDate(),
//						DateUtils.getCalendar("2015", "9", "20").getTime());
				 Integer day = DateUtils.currentTimeDifferenceDay(info.getPlanRepmtDate());
				// 逾期
				if (day > 0) {
					// 查看是否存在滞纳金，如果不存在滞纳金，则进行重新计算滞纳金
					List<LoanCustRepaymentDetail> listDetails = loanCustRepaymentDetailDao
							.find(" FROM LoanCustRepaymentDetail o WHERE o.lateFee > 0 and o.contractNo ='"+ info.getContractNo() + "' ");

					// 获取合同中的月还款额
					LoanContract contract = contractService
							.findLoanContractByContractNo(info.getContractNo());

					if (contract != null) {
						// 如果该合同不存在滞纳金，则说明此人可能没有交过滞纳金或已交过但是将滞纳金只为0了
						if (Collections.listIsEmpty(listDetails)) {
							// 计算滞纳金,同时保留小数点四位有效数字.滞纳金=月还款额*0.1
							BigDecimal multiply = contract.getMonthlyRepayment().multiply(new BigDecimal("0.1"));
							// 精确到小数点后四位
							BigDecimal multiplyFormat = NumberFormatUtil.formatNumber(2, multiply);
							// 设置滞纳金
							info.setLateFee(multiplyFormat);
						}

						// 判断天数是否在一个月之内，如果超过一个月则按照一个月进行计算罚息
						if (day <= 30) {
							// 计算罚息
							// 罚息公式:月还金额*0.0005*分期期数*逾期天数（累计）
							BigDecimal multiply = contract
									.getMonthlyRepayment()
									.multiply(new BigDecimal("0.0005"))
									.multiply(new BigDecimal(day));
							// 精确到小数点后四位
							BigDecimal multiplyFormat = NumberFormatUtil.formatNumber(2, multiply);
							// 设置罚息
							info.setDefaultInterest(multiplyFormat);
						} else {
							// 计算罚息
							BigDecimal multiply = contract
									.getMonthlyRepayment()
									.multiply(new BigDecimal("0.0005"))
									.multiply(new BigDecimal(30));
							// 精确到小数点后四位
							BigDecimal multiplyFormat = NumberFormatUtil.formatNumber(2, multiply);
							// 设置罚息
							info.setDefaultInterest(multiplyFormat);
						}

						// 如果天数超过15天，则转为已催收状态-3
						if (day >= 15) {
							// 相差15天，修改详情的状态为转催收
							info.setRpmtStatus("3");
						}

						// 如果产生逾期，则应还款金额=月还款额+滞纳金+罚息
						BigDecimal add = NumberFormatUtil.formatNumber(2, info.getLateFee().add(info.getDefaultInterest()).add(contract.getMonthlyRepayment()));
						// 设置应还款金额
						info.setPlanRepmtAmt(add);
						// 设置逾期天数
						info.setOverdueDays(day);
					}
				}
			}
		}
	}

	@Override
	public List<LoanCustRepaymentDetailModel> findCustRepaymentDetails(
			Map<String, Object> param, PageUtil page) {
		List<LoanCustRepaymentDetailModel> results = new ArrayList<LoanCustRepaymentDetailModel>();
		List<Object> list = this.loanCustRepaymentDetailDao.findBySql(
				getCustRepaymentDetailsSQL(param), page);
		if (Collections.listIsNotEmpty(list)) {
			for (Object object : list) {
				LoanCustRepaymentDetailModel model = new LoanCustRepaymentDetailModel();
				Object[] o = (Object[]) object;
				model.setLcId((String) o[0]);
				model.setLoanOrderId((String) o[1]);
				model.setContractNo((String) o[2]);
				model.setContractSignDate((Date) o[3]);
				model.setContractSignSite((String) o[4]);
				model.setLoaner((String) o[5]);
				model.setLoanerIdno((String) o[6]);
				model.setLoanEdu((BigDecimal) o[7]);
				model.setLoanPeriods((Integer) o[8]);
				model.setMonthlyRepayment((BigDecimal) o[9]);
				model.setRdId((String) o[10]);
				model.setPeriods((Integer) o[11]);
				model.setPlanRepmtDate((Date) o[12]);
				// model.setPlanRepmtAmt((BigDecimal) o[13]);
				model.setRealRepmtDate((Date) o[14]);
				model.setRealRepmtAmt((BigDecimal) o[15]);
				model.setRepmtAct((String) o[16]);
				model.setOverdueDays((Integer) o[17]);
				model.setLateFee((BigDecimal) o[18]);
				model.setDefaultInterest((BigDecimal) o[19]);
				model.setFreeInterestFee((BigDecimal) o[20]);
				model.setRpmtStatus((String) o[21]);
				model.setOperator((String) o[22]);
				model.setRemark((String) o[23]);
				model.setLoanType((String) o[24]);
				model.setLoanCity((String) o[25]);
				model.setLoanerId((String) o[26]);
				// model.setRpmtStatusName((String) o[21]);
				// 应还金额=月还金额+罚息+滞纳金
				model.setPlanRepmtAmt(model.getMonthlyRepayment()
						.add(model.getLateFee())
						.add(model.getDefaultInterest()));
				// 待还金额=应还金额-实际还款金额-免息金额
				model.setNeedPay(model.getPlanRepmtAmt()
						.subtract(model.getRealRepmtAmt())
						.subtract(model.getFreeInterestFee()));
				if (model.getMonthlyRepayment().compareTo(
						model.getRealRepmtAmt()) == -1) {
					// 如果月还款金额小于实际还款金额，待付月付金额为0
					model.setNeedMonthFee(new BigDecimal(0));
				} else {
					model.setNeedMonthFee(model.getMonthlyRepayment().subtract(
							model.getRealRepmtAmt()));
				}
				model.setNeedDefaultInterest(model.getDefaultInterest()
						.subtract(model.getFreeInterestFee()));
				results.add(model);
			}
		}
		return results;
	}

	/**
	 * 组织sql语句
	 * 
	 * @param param
	 *            参数
	 * @return 返回sql语句
	 */
	private String getCustRepaymentDetailsSQL(Map<String, Object> param) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT");
		sb.append(" lc.LC_ID");
		sb.append(" ,lc.LOAN_ORDER_ID");
		sb.append(" ,lc.CONTRACT_NO");
		sb.append(" ,lc.CONTRACT_SIGN_DATE");
		sb.append(" ,lc.CONTRACT_SIGN_SITE");
		sb.append(" ,lc.LOANER");
		sb.append(" ,lc.LOANER_IDNO");
		sb.append(" ,lc.LOAN_EDU");
		sb.append(" ,lc.LOAN_PERIODS");
		sb.append(" ,lc.MONTHLY_REPAYMENT");
		sb.append(" ,lcrd.RD_ID");
		sb.append(" ,lcrd.PERIODS");
		sb.append(" ,lcrd.PLAN_REPMT_DATE");
		sb.append(" ,lcrd.PLAN_REPMT_AMT");
		sb.append(" ,lcrd.REAL_REPMT_DATE");
		sb.append(" ,lcrd.REAL_REPMT_AMT");
		sb.append(" ,lcrd.REPMT_ACT");
		sb.append(" ,lcrd.OVERDUE_DAYS");
		sb.append(" ,lcrd.LATE_FEE");
		sb.append(" ,lcrd.DEFAULT_INTEREST");
		sb.append(" ,lcrd.FREE_INTEREST_FEE");
		sb.append(" ,dict2.DICT_NAME AS RPMT_STATUS");
		sb.append(" ,lcrd.OPERATOR");
		sb.append(" ,lcrd.REMARK");
		sb.append(" ,dict1.DICT_NAME AS LOAN_TYPE");
		sb.append(" ,car.LOAN_CITY");
		sb.append(" ,lo.LOANER_ID");
		sb.append(" FROM t_bp_loan_cust_repayment_detail lcrd ");
		sb.append(" LEFT JOIN  t_bp_loan_contract lc ON lc.CONTRACT_NO = lcrd.CONTRACT_NO");
		sb.append(" LEFT JOIN t_bp_loan_order lo ON lc.LOAN_ORDER_ID = lo.LOAN_ORDER_ID");
		sb.append(" LEFT JOIN t_bp_credit_audit_report car ON car.LOAN_ORDER_ID = lo.LOAN_ORDER_ID");
		sb.append(" LEFT JOIN (SELECT sd1.DICT_CODE,sd1.DICT_NAME FROM t_sys_dict sd1 WHERE sd1.PARENT_ID =  (SELECT sd.CODE_ID FROM t_sys_dict sd WHERE sd.DICT_CODE = 'loan_type')) AS dict1 ON dict1.DICT_CODE = lo.LOAN_TYPE");
		sb.append(" LEFT JOIN (SELECT sd1.DICT_CODE,sd1.DICT_NAME FROM t_sys_dict sd1 WHERE sd1.PARENT_ID =  (SELECT sd.CODE_ID FROM t_sys_dict sd WHERE sd.DICT_CODE = 'repmt_status')) AS dict2 ON dict2.DICT_CODE = lcrd.RPMT_STATUS");
		sb.append(" WHERE 1=1");
		if (param != null) {
			// 当期页面
			if (param.get("type") != null
					&& param.get("type").equals("CurrentPeriod")) {
				sb.append(" AND ABS(DATEDIFF(lcrd.PLAN_REPMT_DATE, '"
						+ DateUtils.getNowTime(DateUtils.DATE_SMALL_STR)
						+ "')) <= 1");
			}

			// 逾期页面
			if (param.get("type") != null
					&& param.get("type").equals("OverduePeriod")) {
				sb.append(" AND ( lcrd.LATE_FEE > 0 OR lcrd.DEFAULT_INTEREST > 0 )");
			}

			// 催收页面
			if (param.get("type") != null
					&& param.get("type").equals("AssetCollection")) {
				sb.append(" AND lcrd.RPMT_STATUS = '3'");
			}

			// 合同编号
			if (StringUtils.isNotBlank((String) param.get("contractNo"))) {
				sb.append(" AND lc.CONTRACT_NO LIKE '%"
						+ param.get("contractNo") + "%'");
			}
			// 应还款日
			if (param.get("planRepmtDate") != null) {
				DateFormat df = new SimpleDateFormat(DateUtils.DATE_FULL_STR);
				sb.append(" AND DATEDIFF(lcrd.PLAN_REPMT_DATE , '"
						+ df.format(param.get("planRepmtDate")) + "') = 0");
			}

			// 客户姓名
			if (StringUtils.isNotBlank((String) param.get("loanName"))) {
				sb.append(" AND lc.LOANER like '%" + param.get("loanName")
						+ "%'");
			}

			/*
			 * // 合同的签署日期开始 if (StringUtils.isNotBlank((String)
			 * param.get("contractSignDateS"))) {
			 * sb.append(" AND DATEDIFF(lc.CONTRACT_SIGN_DATE, '" +
			 * param.get("contractSignDateS") + "') >=0"); }
			 * 
			 * // 合同的签署日期结束 if (StringUtils.isNotBlank((String)
			 * param.get("contractSignDateE"))) {
			 * sb.append(" AND DATEDIFF(lc.CONTRACT_SIGN_DATE, '" +
			 * param.get("contractSignDateE") + "') <=0"); }
			 */
			if (StringUtils.isNotBlank((String) param.get("contractSignDateS"))) {
				if (StringUtils.isNotBlank((String) param
						.get("contractSignDateE"))) {
					sb.append(" AND lc.CONTRACT_SIGN_DATE BETWEEN '"
							+ param.get("contractSignDateS") + "' AND '"
							+ param.get("contractSignDateE") + "'");
				} else {
					sb.append(" AND lc.CONTRACT_SIGN_DATE = str_to_date('"
							+ param.get("contractSignDateS") + "','%Y-%m-%d')");
				}
			}

			// 进件城市
			if (StringUtils.isNotBlank((String) param.get("loanCity"))) {
				sb.append(" AND car.LOAN_CITY like '%" + param.get("loanCity")
						+ "%'");
			}
		}
		sb.append(" ORDER BY lc.CONTRACT_SIGN_DATE DESC, lc.CONTRACT_NO ASC, lcrd.PERIODS ASC");
		return sb.toString();
	}

	@Override
	public Long findCustRepaymentDetialsCount(Map<String, Object> param) {
		return this.loanCustRepaymentDetailDao
				.countBySql("SELECT COUNT(1) FROM ( "
						+ getCustRepaymentDetailsSQL(param) + " ) AS fo");
	}

	@Override
	public List<LoanCustRepaymentDetail> findAllRepaymentDetailByCno(String contractNo) {
		String hql = " FROM LoanCustRepaymentDetail l WHERE l.contractNo = '"+contractNo+"'";
		return loanCustRepaymentDetailDao.find(hql);
	}

	@Override
	public void deleteRepaymentDetailByCNO(String contractNo) {
		String sql = "DELETE FROM LoanCustRepaymentDetail  WHERE contractNo = '"+contractNo+"'";
		loanCustRepaymentDetailDao.executeHql(sql);
	}
}
