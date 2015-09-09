package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.CreditInvestigation;
import com.bpms.service.CreditInvestigationService;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service
public class CreditInvestigationServiceImpl implements
		CreditInvestigationService {

	@Autowired
	private BaseDAO<CreditInvestigation> baseDAO;

	@Override
	public boolean persistenceCreditInvestigation(
			CreditInvestigation creditInvestigation) {
		if (StringUtils.isBlank(creditInvestigation.getCreditRefId())) {
			baseDAO.save(creditInvestigation);
		} else {
			baseDAO.update(creditInvestigation);
		}
		return true;
	}
	
	@Override
	public CreditInvestigation findCreditInvestigationById(String id) {
		return baseDAO.get(CreditInvestigation.class, id);
	}

	@Override
	public List<CreditInvestigation> findCreditInvestigation(
			Map<String, Object> map, PageUtil pageUtil) {
		String hql = "from CreditInvestigation t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countCreditInvestigation(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "select count(*) from CreditInvestigation t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

}
