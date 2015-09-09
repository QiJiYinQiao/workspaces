package com.bpms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Loaner;
import com.bpms.model.LoanerInfoHis;
import com.bpms.service.LoanerInfoHisService;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service("loanerInfoHistoryService")
public class LoanerInfoHisServiceImpl implements LoanerInfoHisService {

	@Autowired
	public BaseDAO<LoanerInfoHis> baseDAO;

	@Override
	public List<LoanerInfoHis> findAllLoanerHistory(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "from LoanerHistory l where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("l", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("l", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countAllLoanerHistory(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from LoanerHistory l where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("l", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("l", pageUtil);
		return baseDAO.count(hql, map);
	}

	@Override
	public void saveLoanerHis(Loaner loaner) {
		LoanerInfoHis loanerHis = new LoanerInfoHis();
		BeanUtils.copyProperties(loaner, loanerHis);
		loanerHis.setLoanerStatusType("A");
		loanerHis.setChangeContents("");
		loanerHis.setChangeTiem(new Date());
		loanerHis.setChangePeople("");
		baseDAO.save(loanerHis);
	}

}
