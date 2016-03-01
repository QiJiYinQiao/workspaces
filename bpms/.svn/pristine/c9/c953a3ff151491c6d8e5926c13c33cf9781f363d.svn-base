package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.mydao.invest.InvestReportExportMapper;
import com.bpms.service.InvestReportExportService;

@Service("investReportExportService")
public class InvestReportExportServiceImpl implements InvestReportExportService {

	@Autowired
	private InvestReportExportMapper investReportExportMapper;
	
	@Override
	public List<Map<String, Object>> queryInvestorSummaryStatement(
			Map<String, Object> param) {
		return investReportExportMapper.queryInvestorSummaryStatement(param);
	}

	@Override
	public Integer queryInvestorSummaryStatementCount(Map<String, Object> param) {
		return investReportExportMapper.queryInvestorSummaryStatementCount(param);
	}

	@Override
	public Map<String, Object> queryTeamManager(String investOrderId) {
		return investReportExportMapper.queryTeamManager(investOrderId);
	}

	@Override
	public Map<String, Object> queryGroupManager(String investOrderId) {
		return investReportExportMapper.queryGroupManager(investOrderId);
	}

}
