package com.bpms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.FirstAuditReport;
import com.bpms.service.FirstAuditReportService;

/**
 * 初审资质分析ServiceImpl
 * 
 * @author PANCHUANHE
 * @date 2015/6/16
 */
@Service("firstAuditReportService")
public class FirstAuditReportServiceImpl implements FirstAuditReportService {

	@Autowired
	private BaseDAO<FirstAuditReport> baseDAO;

	@Override
	public boolean persistenceFirstAuditReport(FirstAuditReport firstAuditReport) {
		if (StringUtils.isBlank(firstAuditReport.getFirsId())) {
			baseDAO.save(firstAuditReport);
		} else {
			baseDAO.update(firstAuditReport);
		}
		return true;
	}

	@Override
	public FirstAuditReport findFirstAuditReportById(String id) {
		return baseDAO.get(FirstAuditReport.class, id);
	}

}
