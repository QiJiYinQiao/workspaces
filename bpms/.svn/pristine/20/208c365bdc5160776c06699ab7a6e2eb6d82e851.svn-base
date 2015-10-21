package com.bpms.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.OutsurveyReportSummary;
import com.bpms.service.OutSurveyReportSummaryService;

@Service("ipcOutSurveyReportSummaryService")
public class OutSurveyReportSummaryServiceImpl implements
		OutSurveyReportSummaryService {

	@Autowired
	private BaseDAO<OutsurveyReportSummary> baseDAO;
	
	@Override
	public boolean persistenceOutSurveySummaryReport(
			OutsurveyReportSummary outSurveyReportSummary) {
		if (StringUtils.isBlank(outSurveyReportSummary.getOutsurveyReportSummaryId())) {
			baseDAO.save(outSurveyReportSummary);
		} else {
			baseDAO.update(outSurveyReportSummary);
		}
		return true;
	}

	@Override
	public OutsurveyReportSummary findOutSurveyReportSummaryById(
			String loanOrderid) {
		String hql = " FROM OutsurveyReportSummary isrs WHERE isrs.loanOrderId = '"+loanOrderid+"'";
		List<OutsurveyReportSummary> list = baseDAO.find(hql);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
