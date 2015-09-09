package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.FinalAuditReport;
import com.bpms.service.FinalAuditReportService;
import com.bpms.util.Collections;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service("finalAuditReportService")
public class FinalAuditReportServiceImpl implements FinalAuditReportService {

	@Autowired
	private BaseDAO<FinalAuditReport> baseDAO;

	@Override
	public boolean persistenceFinalAuditReport(FinalAuditReport finalAuditReport) {
		if (StringUtils.isBlank(finalAuditReport.getFinaId())) {
			baseDAO.save(finalAuditReport);
		} else {
			baseDAO.update(finalAuditReport);
		}
		return true;
	}

	@Override
	public FinalAuditReport findFinalAuditReportByLoanOrderId(String loanOrderId) {
		String hql = "from FinalAuditReport o where o.loanOrderId='"
				+ loanOrderId + "'";
		List<FinalAuditReport> list = baseDAO.find(hql);
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<FinalAuditReport> findFinalAuditReport(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "from FinalAuditReport t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countFinalAuditReport(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from CreditAuditReport t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

}
