package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.LoansDetails;
import com.bpms.service.LoansDetailsService;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service
public class LoansDetailsServiceImpl implements LoansDetailsService {

	@Autowired
	private BaseDAO<LoansDetails> baseDAO;

	@Override
	public boolean persistenceLoansDetails(LoansDetails loansDetails) {
		if (StringUtils.isBlank(loansDetails.getExistLoanId())) {
			baseDAO.save(loansDetails);
		} else {
			baseDAO.update(loansDetails);
		}
		return true;
	}

	@Override
	public LoansDetails findLoansDetailsById(String id) {
		return baseDAO.get(LoansDetails.class, id);
	}

	@Override
	public List<LoansDetails> findLoansDetails(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "from LoansDetails t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countLoansDetails(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from LoansDetails t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

}
