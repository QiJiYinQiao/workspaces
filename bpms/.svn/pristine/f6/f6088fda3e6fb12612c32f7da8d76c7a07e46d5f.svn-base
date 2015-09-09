package com.bpms.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.AccountInfo;
import com.bpms.model.FinalAuditReport;
import com.bpms.model.LoanContract;
import com.bpms.model.LoanCustRepaymentDetail;
import com.bpms.model.LoanOrder;
import com.bpms.model.MicrocreditOpinion;
import com.bpms.model.Organization;
import com.bpms.model.Users;
import com.bpms.model.vo.LoanContractInfoModel;
import com.bpms.model.vo.LoanerJointModel;
import com.bpms.service.AccountInfoService;
import com.bpms.service.AddressService;
import com.bpms.service.CommonService;
import com.bpms.service.FinalAuditReportService;
import com.bpms.service.LoanContractService;
import com.bpms.service.LoanCustRepaymentDetailService;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanerJointService;
import com.bpms.service.MicrocreditOpinionService;
import com.bpms.service.OrganizationService;
import com.bpms.service.UserService;
import com.bpms.util.Collections;
import com.bpms.util.DateUtils;
import com.bpms.util.NumberFormatUtil;
import com.bpms.util.PageUtil;
import com.bpms.util.ReadExcel;

@Service("loanContractServiceImpl")
public class LoanContractServiceImpl implements LoanContractService {

	@Autowired
	private BaseDAO<LoanContract> baseDAO;

	@Autowired
	private LoanerJointService loanerJointService;

	@Autowired
	private FinalAuditReportService finalAuditReportService;

	@Autowired
	private MicrocreditOpinionService microcreditOpinionService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private AccountInfoService accountInfoService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private UserService userService;

	@Autowired
	private LoanOrderService loanOrderService;

	@Autowired
	private LoanCustRepaymentDetailService custRepaymentDetailService;

	@Override
	public boolean persistenceLoanContract(LoanContract loanContract) {
		if (StringUtils.isBlank(loanContract.getLcId())) {
			baseDAO.save(loanContract);
		} else {
			baseDAO.update(loanContract);
		}
		return true;
	}

	@Override
	public String findJinJianNo(String no) {
		String sql = "SELECT MAX(SUBSTRING(REPLACE(lc.CONTRACT_NO,'"
				+ no
				+ "',''),15)) FROM t_bp_loan_contract lc WHERE lc.CONTRACT_NO LIKE '"
				+ no + "%'";
		List list = baseDAO.findBySQL(sql);
		if (list.size() > 0 && null != list.get(0)) {
			String jinjianNo = (String) list.get(0);
			return String.format("%08d", (Integer.parseInt(jinjianNo) + 1));
		} else {
			return "00000000";
		}
	}

	@Override
	public LoanContract findLoanContract(String id) {
		String hql = " FROM LoanContract lc WHERE lc.lcId = '" + id + "'";
		List<LoanContract> list = baseDAO.find(hql);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public LoanContract findLoanContractByLoanOrderId(final String loanOrderId) {
		String hql = "from LoanContract lc where lc.loanOrderId=:loanOrderId";
		List<LoanContract> list = baseDAO.find(hql,
				new HashMap<String, Object>() {
					{
						put("loanOrderId", loanOrderId);
					}
				});
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanContractInfoModel> findLoanConractInfoModelList(
			Map<String, Object> map, PageUtil pageUtil) {
		List<Object> list = this.baseDAO.findBySql(createContractInfoSql(map),
				pageUtil);
		List<LoanContractInfoModel> contractInfoModes = new ArrayList<LoanContractInfoModel>();
		if (Collections.listIsNotEmpty(list)) {
			for (Object object : list) {
				Object[] o = (Object[]) object;
				LoanContractInfoModel infoModel = new LoanContractInfoModel();
				if (!StringUtils.isEmpty((String) o[0])) {
					Users user1 = userService.findUserById(Integer
							.parseInt((String) o[0]));
					infoModel.setLoanReviewRommitteeName1(user1.getName());
				}
				if (!StringUtils.isEmpty((String) o[1])) {
					Users user2 = userService.findUserById(Integer
							.parseInt((String) o[1]));
					infoModel.setLoanReviewRommitteeName2(user2.getName());
				}
				if (!StringUtils.isEmpty((String) o[2])) {
					Users user3 = userService.findUserById(Integer
							.parseInt((String) o[2]));
					infoModel.setLoanReviewRommitteeName3(user3.getName());
				}
				infoModel.setContractNo((String) o[3]);
				infoModel.setLoanerActName((String) o[4]);
				infoModel.setLoanerActNum((String) o[5]);
				infoModel.setLoanerBankName((String) o[6]);
				infoModel.setContractSignDate((Date) o[7]);
				infoModel.setContractSignSite((String) o[8]);
				infoModel.setLoanBgDate((Date) o[9]);
				infoModel.setLoanEdDate((Date) o[10]);
				infoModel.setMonthlyRepayment(String.valueOf(o[11]));
				infoModel.setLoanPeriod((Integer) o[12]);
				infoModel.setRemark((String) o[13]);
				infoModel.setLoanName((String) o[14]);
				infoModel.setLoanIdNo((String) o[15]);
				infoModel.setLoanCurAddr((String) o[16]);
				infoModel.setLoanMobileTel((String) o[17]);
				infoModel.setLoanEdu(String.valueOf(o[18]));
				infoModel.setLoanInterest(String.valueOf(o[19]));
				infoModel.setLoanOrderId((String) o[20]);

				infoModel.setLoanType((String) o[21]);
				infoModel.setLoanTypeName(commonService.findDictName(
						"loan_type", o[21] + ""));

				infoModel.setSalesMan((String) o[22]);

				Organization organ = this
						.findLoanCityByOrganizationId((Integer) o[23]);
				infoModel.setOrganizationId(organ.getFullName());

				infoModel.setLoanHukouAddrId((String) o[24]);
				infoModel.setLoanHukouAddr(addressService
						.addressName((String) o[24]));

				if (!StringUtils.isEmpty((String) o[25])) {
					Users user4 = userService.findUserById(Integer
							.parseInt((String) o[25]));
					infoModel.setOperatorAName(user4.getName());
				}
				if (!StringUtils.isEmpty((String) o[26])) {
					Users user5 = userService.findUserById(Integer
							.parseInt((String) o[26]));
					infoModel.setOperatorBName(user5.getName());
				}

				infoModel.setVisitFee(String.valueOf(o[27]));
				infoModel.setMonthlyFee();
				infoModel.setLoanCity((String) o[28]);

				contractInfoModes.add(infoModel);
			}
		}
		return contractInfoModes;
	}

	@Override
	public Long getCountOfConractInfoModel(Map<String, Object> map) {
		return baseDAO.countBySql("SELECT COUNT(1) FROM ( "
				+ createContractInfoSql(map) + " ) AS fo");
	}

	@Override
	public boolean saveLoanConract(LoanContract loanContract) {
		LoanOrder loanOrder = loanOrderService.findLoanOrderById(loanContract
				.getLoanOrderId());
		if (StringUtils.isBlank(loanContract.getLcId())) {
			String contractNo = createContractNo(loanOrder);
			loanContract.setContractNo(contractNo);
		}
		loanContract.setLoaner(loanOrder.getName());
		loanContract.setLoanerIdno(loanOrder.getIdNo());
		String loanerPostalAddr = addressService.addressName(loanOrder
				.getCurAddr());
		loanContract.setLoanerPostalAddr(loanerPostalAddr);
		loanContract.setLoanerTel(loanOrder.getMobileTel());
		loanContract.setLoanerSignDate(new Date());
		LoanerJointModel loanerJoint = loanerJointService
				.findLoanerJointByOrderId(loanOrder.getLoanOrderId());
		if (null != loanerJoint) {
			loanContract.setLoanerJoint(loanerJoint.getName());
			loanContract.setLjIdno(loanerJoint.getIdNo());
			String ljPostalAddr = addressService.addressName(loanerJoint
					.getCurAddr());
			loanContract.setLjPostalAddr(ljPostalAddr);
			loanContract.setLjTel(loanerJoint.getMobileTel());
		}
		loanContract.setLender("于德建");
		loanContract.setLenderIdno("131126197912150054");
		loanContract.setObliMatchStatus("0");
		loanContract.setPurpose(loanOrder.getPurpose());
		BigDecimal loanPeriods = null;
		BigDecimal monthlyRate = null;
		FinalAuditReport finalAuditReport = finalAuditReportService
				.findFinalAuditReportByLoanOrderId(loanOrder.getLoanOrderId());
		if (null != finalAuditReport) {
			loanContract.setLoanEdu(finalAuditReport.getContractLoanAmount());
			loanPeriods = new BigDecimal(commonService.findDictName(
					"loan_period_type", finalAuditReport.getLoanPeriodType()));
			monthlyRate = new BigDecimal(finalAuditReport.getLoanInterestRate());
		} else {
			MicrocreditOpinion microcreditOpinion = microcreditOpinionService
					.findMicrocreditOpinionByOid(loanOrder.getLoanOrderId());
			loanContract.setLoanEdu(microcreditOpinion.getFinalLoanAmt());
			loanPeriods = new BigDecimal(
					commonService.findDictName("loan_period_type",
							microcreditOpinion.getFinalLoanPeriod()));
			monthlyRate = microcreditOpinion.getLoanRate();
		}
		loanContract.setLoanPeriods(loanPeriods.intValue());
		BigDecimal loanEdu = loanContract.getLoanEdu();
		loanContract.setMonthlyRate(monthlyRate);
		loanContract.setLoanInterest(monthlyRate.multiply(loanEdu).multiply(
				loanPeriods));
		BigDecimal monthlyRepayment = loanEdu.divide(
				new BigDecimal(loanContract.getLoanPeriods()), 4,
				BigDecimal.ROUND_HALF_EVEN).add(
				loanEdu.multiply(loanContract.getMonthlyRate(),
						MathContext.DECIMAL64));
		loanContract.setMonthlyRepayment(monthlyRepayment);
		loanContract.setLoanBgDate(new Date());
		List<AccountInfo> accountList = accountInfoService
				.findListByLoanerId(loanOrder.getLoanerId());
		if (null != accountList && accountList.size() > 0) {
			AccountInfo account = accountList.get(0);
			loanContract.setLoanerBankName(account.getBankName());
			loanContract.setLoanerActName(account.getAccountName());
			loanContract.setLoanerActNum(account.getAccountNum());
		}
		return this.persistenceLoanContract(loanContract);
	}

	@Override
	public boolean saveCompleteContract(LoanContract loanContract) {
		LoanContract loanCract = this.findLoanContract(loanContract.getLcId());

		loanCract.setContractSignSite(loanContract.getContractSignSite());
		loanCract.setContractSignDate(loanContract.getContractSignDate());
		if (loanCract.getContractSignDate() != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(loanCract.getContractSignDate());
			if (calendar.get(Calendar.DATE) >= 15) {
				loanCract.setMonthlyRepaymentDate(15);
			} else {
				loanCract.setMonthlyRepaymentDate(30);
			}
		}
		loanCract.setLjSignDate(loanContract.getLjSignDate());
		loanCract.setAgent(loanContract.getAgent());
		loanCract.setAgentIdno(loanContract.getAgentIdno());
		loanCract.setAgentSignDate(loanContract.getAgentSignDate());
		loanCract.setLoanEdDate(loanContract.getLoanEdDate());
		loanCract.setRepaymentBgDate(loanContract.getRepaymentBgDate());
		loanCract.setRepaymentEdDate(loanContract.getRepaymentEdDate());
		loanCract.setRemark(loanContract.getRemark());
		loanCract.setContractStatus("1");
		boolean result = this.persistenceLoanContract(loanCract);
		saveLoanCusRepaymentDetail(loanCract);
		return result;
	}

	/**
	 * 拆分客户还款明细，并保存客户还款明细
	 */
	private void saveLoanCusRepaymentDetail(LoanContract contract) {
		LoanOrder loanOrder = loanOrderService.findLoanOrderById(contract
				.getLoanOrderId());
		for (int i = 1; i <= contract.getLoanPeriods(); i++) {
			LoanCustRepaymentDetail custRepaymentDetail = new LoanCustRepaymentDetail();
			custRepaymentDetail.setContractNo(contract.getContractNo());
			custRepaymentDetail.setPeriods(i);

			// 计算还款日期
			if (contract.getContractSignDate() != null) {
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(contract.getContractSignDate());
				if (calendar.get(Calendar.DATE) >= 15) {
					custRepaymentDetail.setPlanRepmtDate(DateUtils
							.getNextMonth15Day(DateUtils.addMonth(
									contract.getLoanBgDate(), i - 1)));
				} else {
					custRepaymentDetail.setPlanRepmtDate(DateUtils
							.get30Day(DateUtils.addMonth(
									contract.getLoanBgDate(), i - 1)));
				}
			}
			custRepaymentDetail.setPlanRepmtAmt(contract.getMonthlyRepayment());
			custRepaymentDetail.setRpmtStatus("0");
			custRepaymentDetail.setOperator(loanOrder.getSalesMan());
			custRepaymentDetail.setRealRepmtAmt(new BigDecimal(0));
			custRepaymentDetail.setOverdueDays(0);
			custRepaymentDetail.setLateFee(new BigDecimal(0));
			custRepaymentDetail.setDefaultInterest(new BigDecimal(0));
			custRepaymentDetail.setFreeInterestFee(new BigDecimal(0));
			custRepaymentDetailService
					.persistenceLoanCustRepaymentDetail(custRepaymentDetail);
		}
	}

	// 生成合同编号
	private String createContractNo(LoanOrder loanOrder) {
		String contractNo = "QQJR-LO-";
		String province = "BJ-";
		String city = "BJ-";
		String deptNo = "00-";
		String jinjianNo = "00000001";
		Users user = userService.findUserById(loanOrder.getCreator());
		Organization organization = user.getOrganization();
		String regionType = organization.getRegionType();
		if (StringUtils.isBlank(regionType)) {
			deptNo = organization.getMyid() + "-";
			Organization citys = organizationService
					.findOrganizationById(organization.getPid());
			city = citys.getMyid() + "-";
			Organization provinces = organizationService
					.findOrganizationById(citys.getPid());
			province = provinces.getMyid() + "-";
		} else if ("3".equals(regionType)) {
			deptNo = "00-";
			city = organization.getMyid() + "-";
			Organization oga = organizationService
					.findOrganizationById(organization.getPid());
			province = oga.getMyid() + "-";
		} else if ("2".equals("regionType")) {

		} else if ("1".equals(regionType)) {
			deptNo = "00-";
			province = organization.getMyid() + "-";
			city = organization.getMyid() + "-";
		}
		jinjianNo = this.findJinJianNo(contractNo + province + city + deptNo);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dataStr = sdf.format(new Date());
		return contractNo + province + city + deptNo + dataStr + jinjianNo;
	}

	@Override
	public void exprotLoanConractInfoModel(Map<String, Object> map)
			throws Exception {
		List<LoanContractInfoModel> modelList = this
				.findLoanConractInfoModelList(map, new PageUtil(0, 0));
		String srcXlsxPath = ServletActionContext
				.getServletContext()
				.getRealPath(File.separator + "excel" + File.separator + "loan")
				+ File.separator + "信贷合同明细模板.xlsx";// 模板路径
		Workbook workbook = ReadExcel.openExcleFile(srcXlsxPath);// 获取工作簿
		if (Collections.listIsNotEmpty(modelList)) {
			Sheet sheet = workbook.getSheetAt(0);// 获取页签
			for (int i = 0; i < modelList.size(); i++) {
				// 第一行不用copy
				if (i > 0) {
					ReadExcel.copyRows(sheet, 5, 5, 4 + i);
				}
				LoanContractInfoModel info = modelList.get(i);
				Row row = sheet.getRow(4 + i);
				row.getCell(0).setCellValue(i + 1);
				row.getCell(1).setCellValue(info.getLoanCity());
				row.getCell(2).setCellValue(info.getContractNo());
				row.getCell(3).setCellValue(info.getContractSignDate());
				row.getCell(4).setCellValue(info.getLoanerActName());
				row.getCell(5).setCellValue(info.getLoanerBankName());
				row.getCell(6).setCellValue(info.getLoanerActNum());
				row.getCell(7).setCellValue(info.getLoanName());
				row.getCell(8).setCellValue(info.getLoanIdNo());
				row.getCell(9).setCellValue(info.getLoanHukouAddr());
				row.getCell(10).setCellValue(info.getLoanCurAddr());
				row.getCell(11).setCellValue(info.getLoanMobileTel());
				row.getCell(12).setCellValue(info.getLoanTypeName());
				row.getCell(13).setCellValue(info.getOrganizationId());
				row.getCell(14).setCellValue(
						NumberFormatUtil.formatNumber(2, info.getLoanEdu())
								.doubleValue());
				row.getCell(15).setCellValue(info.getLoanPeriod());
				row.getCell(16).setCellValue(
						NumberFormatUtil.formatNumber(2, info.getMonthlyFee())
								.doubleValue());
				row.getCell(17).setCellValue(
						NumberFormatUtil.formatNumber(2, info.getVisitFee())
								.doubleValue());
				row.getCell(18).setCellValue(
						NumberFormatUtil.formatNumber(2, info.getLoanEdu())
								.doubleValue());
				row.getCell(19).setCellValue(
						NumberFormatUtil.formatNumber(2,
								info.getMonthlyRepayment()).doubleValue());
				row.getCell(20).setCellValue(info.getMonthlyRepaymentDate());
				row.getCell(21).setCellValue(info.getRepaymentBgDate());
				row.getCell(22).setCellValue(info.getRepaymentEndDate());
				row.getCell(23).setCellValue(info.getDrawPlatform());
				row.getCell(24).setCellValue(info.getDrawDate());
				row.getCell(25).setCellValue(info.getOperatorAName());
				row.getCell(26).setCellValue(info.getOperatorBName());
				row.getCell(27)
						.setCellValue(info.getLoanReviewRommitteeName1());
				row.getCell(28)
						.setCellValue(info.getLoanReviewRommitteeName2());
				row.getCell(29)
						.setCellValue(info.getLoanReviewRommitteeName3());
				row.getCell(30).setCellValue(info.getSalesMan());
				row.getCell(31).setCellValue(info.getTeamManger());
				row.getCell(32).setCellValue(info.getRemark());
			}
		}
		
		HttpServletResponse response = ServletActionContext.getResponse();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		response.setHeader(
				"Content-disposition",
				"attachment;filename="
						+ URLEncoder.encode(
								"信贷合同明细-" + calendar.get(Calendar.YEAR) + "-"
										+ calendar.get(Calendar.MONTH) + "-"
										+ calendar.get(Calendar.DATE) + ".xlsx",
								"utf-8"));
		OutputStream ouputStream = response.getOutputStream();
		workbook.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	private Organization findLoanCityByOrganizationId(Integer id) {
		Organization organization = organizationService
				.findOrganizationById(id);
		if (null == organization)
			return null;

		if ("1".equals(organization.getRegionType())
				|| "3".equals(organization.getRegionType())) {
			return organization;
		}
		return findLoanCityByOrganizationId(organization.getPid());
	}

	// 创建合同信息SQL
	private String createContractInfoSql(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT");
		sql.append("	loh1.ASSIGNEE AS daishenwei1");
		sql.append("	,loh2.ASSIGNEE AS daishenwei2");
		sql.append("	,loh3.ASSIGNEE AS daishenwei3");
		sql.append("	,lc.CONTRACT_NO");
		sql.append("	,lc.LOANER_ACT_NAME");
		sql.append("	,lc.LOANER_ACT_NUM");
		sql.append("	,lc.LOANER_BANK_NAME");
		sql.append("	,lc.CONTRACT_SIGN_DATE");
		sql.append("	,lc.CONTRACT_SIGN_SITE");
		sql.append("	,lc.LOAN_BG_DATE");
		sql.append("	,lc.LOAN_ED_DATE");
		sql.append("	,lc.MONTHLY_REPAYMENT");
		sql.append("	,lc.LOAN_PERIODS");
		sql.append("	,lc.REMARK");
		sql.append("	,lc.LOANER");
		sql.append("	,lc.LOANER_IDNO");
		sql.append("	,lc.LOANER_POSTAL_ADDR");
		sql.append("	,lc.LOANER_TEL");
		sql.append("	,lc.LOAN_EDU");
		sql.append("	,lc.LOAN_INTEREST");
		sql.append("	,fo.*");
		sql.append("	,tbcar.LOAN_CITY");
		sql.append(" FROM");
		sql.append("	(");
		sql.append("		SELECT");
		sql.append("			lo.LOAN_ORDER_ID");
		sql.append("			,lo.LOAN_TYPE");
		sql.append("			,lo.SALESMAN");
		sql.append("			,lo.ORGANIZATION_ID");
		sql.append("			,lo.HUKOU_ADDR");
		sql.append("			,mo.OPERATOR_A");
		sql.append("			,mo.OPERATOR_B");
		sql.append("			,0 AS VISIT_FEE");
		sql.append("		FROM");
		sql.append("			t_bp_loan_order lo");
		sql.append("		INNER JOIN t_bp_order_status os ON os.STATUS_CODE = 'LoanOrder_SignatoryThrough' AND lo.STATUS_ID = os.STATUS_ID");
		sql.append("		INNER JOIN t_bp_microcredit_opinion mo ON mo.LOAN_ORDER_ID = lo.LOAN_ORDER_ID");
		sql.append("		UNION");
		sql.append("			SELECT");
		sql.append("				lo.LOAN_ORDER_ID");
		sql.append("				,lo.LOAN_TYPE");
		sql.append("				,lo.SALESMAN");
		sql.append("				,lo.ORGANIZATION_ID");
		sql.append("				,lo.HUKOU_ADDR");
		sql.append("				,'' AS OPERATOR_A");
		sql.append("				,'' AS OPERATOR_B");
		sql.append("				,far.VISIT_FEE");
		sql.append("			FROM");
		sql.append("				t_bp_loan_order lo");
		sql.append("			INNER JOIN t_bp_order_status os ON os.STATUS_CODE = 'LoanOrder_SignatoryThrough' AND lo.STATUS_ID = os.STATUS_ID");
		sql.append("			INNER JOIN t_bp_final_audit_report far ON far.LOAN_ORDER_ID = lo.LOAN_ORDER_ID");
		sql.append("	) AS fo");
		sql.append(" INNER JOIN t_bp_loan_contract lc ON lc.LOAN_ORDER_ID = fo.LOAN_ORDER_ID");
		sql.append(" LEFT JOIN t_bp_loan_order_his loh1 ON loh1.LOAN_ORDER_ID = fo.LOAN_ORDER_ID AND loh1.ROLE_ID = '10010'");
		sql.append(" LEFT JOIN t_bp_loan_order_his loh2 ON loh2.LOAN_ORDER_ID = fo.LOAN_ORDER_ID AND loh2.ROLE_ID = '10011'");
		sql.append(" LEFT JOIN t_bp_loan_order_his loh3 ON loh3.LOAN_ORDER_ID = fo.LOAN_ORDER_ID AND loh3.ROLE_ID = '10012'");
		sql.append(" LEFT JOIN t_bp_credit_audit_report tbcar ON tbcar.LOAN_ORDER_ID = fo.LOAN_ORDER_ID");
		sql.append(" WHERE");
		sql.append("	1 = 1");
		if (!map.isEmpty()) {
			// String organizationId = (String) map.get("organizationId");
			String loanName = (String) map.get("loanName");
			String loanBgDateS = (String) map.get("loanBgDateS");
			String loanBgDateE = (String) map.get("loanBgDateE");
			String contractNo = (String) map.get("contractNo");

			// if (StringUtils.isNotEmpty(organizationId)) {
			// sql.append(" AND fo.ORGANIZATION_ID = '" + organizationId + "'");
			// }

			// 借款人
			if (StringUtils.isNotEmpty(loanName)) {
				sql.append(" AND lc.LOANER LIKE '%" + loanName + "%' ");
			}

			// 合同的签署日期
			if (StringUtils.isNotEmpty(loanBgDateS)) {
				if (StringUtils.isNotEmpty(loanBgDateE)) {
					sql.append(" AND lc.CONTRACT_SIGN_DATE BETWEEN '"
							+ loanBgDateS + "' AND '" + loanBgDateE + "'");
				} else {
					sql.append(" AND lc.CONTRACT_SIGN_DATE = str_to_date('"
							+ loanBgDateS + "','%Y-%m-%d')");
				}

			}
			// 合同编号
			if (StringUtils.isNotEmpty(contractNo)) {
				sql.append("  AND lc.CONTRACT_NO LIKE '%" + contractNo + "%'");
			}

			// 进件城市
			if (StringUtils.isNotBlank((String) map.get("loanCity"))) {
				sql.append(" AND tbcar.LOAN_CITY like '%" + map.get("loanCity")
						+ "%'");
			}
		}
		return sql.toString();
	}

	@Override
	public LoanContract findLoanContractByContractNo(String contractNo) {
		List<LoanContract> list = this.baseDAO
				.find("from LoanContract o where o.contractNo = '" + contractNo
						+ "' ");
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

}
