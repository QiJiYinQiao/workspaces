package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.CreditAuditReport;
import com.bpms.service.CreditAuditReportService;
import com.bpms.util.Collections;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service
public class CreditAuditReportServiceImpl implements CreditAuditReportService {

	@Autowired
	private BaseDAO<CreditAuditReport> baseDAO;

	@Override
	public boolean persistenceCreditAuditReport(
			CreditAuditReport creditAuditReport) {
		if (StringUtils.isBlank(creditAuditReport.getCarId())) {
			baseDAO.save(creditAuditReport);
		} else {
			baseDAO.update(creditAuditReport);
		}
		return true;
	}

	@Override
	public List<CreditAuditReport> findCreditAuditReport(
			Map<String, Object> map, PageUtil pageUtil) {
		String hql = "from CreditAuditReport t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countCreditAuditReport(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "select count(*) from CreditAuditReport t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

	@Override
	public CreditAuditReport findCreditAuditReportByLoanOrderId(
			String loanOrderId) {
		String hql = "from CreditAuditReport o where o.loanOrderId='"
				+ loanOrderId + "'";
		List<CreditAuditReport> list = baseDAO.find(hql);
		// 如果信审报告不存在，则是第一次进行审核，需要新增到数据库同时，将新增信息进行返回
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		} else {
			// 创建信审报告的信息
			CreditAuditReport auditReport = new CreditAuditReport();
			auditReport.setLoanOrderId(loanOrderId);
			this.persistenceCreditAuditReport(auditReport);
			return findCreditAuditReoprtById(auditReport.getCarId());
		}
	}

	@Override
	public CreditAuditReport findCreditAuditReoprtById(String id) {
		return baseDAO.get(CreditAuditReport.class, id);
	}

}
