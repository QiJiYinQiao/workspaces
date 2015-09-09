package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.CreditCardsDetails;
import com.bpms.service.CreditCardsDetailsService;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service
public class CreditCardsDetailsServiceImpl implements CreditCardsDetailsService {

	@Autowired
	private BaseDAO<CreditCardsDetails> baseDAO;

	@Override
	public boolean persistenceCreditCardsDetails(
			CreditCardsDetails creditCardsDetails) {
		if (StringUtils.isBlank(creditCardsDetails.getCardInfoId())) {
			baseDAO.save(creditCardsDetails);
		} else {
			baseDAO.update(creditCardsDetails);
		}
		return true;
	}

	@Override
	public CreditCardsDetails findCreditCradsDetailsById(String id) {
		return baseDAO.get(CreditCardsDetails.class, id);
	}

	@Override
	public List<CreditCardsDetails> findCreditCardsDetails(
			Map<String, Object> map, PageUtil pageUtil) {
		String hql = "from CreditCardsDetails t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countCreditCardsDetails(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "select count(*) from CreditCardsDetails t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

}
