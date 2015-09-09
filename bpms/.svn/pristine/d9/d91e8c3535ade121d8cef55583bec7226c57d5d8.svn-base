package com.bpms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.LoanorderAndAccountinfo;
import com.bpms.service.LoanorderAndAccountinfoService;

/**
 * 订单与开户行serviceImpl
 * 
 * @author panchuanhe 2015、6/30
 */
@Service("loanorderAndAccountinfoService")
public class LoanorderAndAccountinfoServiceImpl implements
		LoanorderAndAccountinfoService {

	@Autowired
	public BaseDAO<LoanorderAndAccountinfo> baseDAO;

	@Override
	public List<LoanorderAndAccountinfo> findByAccountId(String AccountId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("from com.bpms.model.LoanorderAndAccountinfo t where t.accountId = '"
					+ AccountId + "'");
			return baseDAO.find(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean deleteByAccountIdAndLoanOrderId(String accountId,
			String loanOrderId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("delete from com.bpms.model.LoanorderAndAccountinfo t where 1=1");
			hql.append(" and t.accountId = '" + accountId + "'");
			hql.append(" and t.loanOrderId = '" + loanOrderId + "'");
			baseDAO.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean saveLoanorderAndAccountinfo(LoanorderAndAccountinfo laa) {
		try {
			baseDAO.save(laa);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<LoanorderAndAccountinfo> findByLoanOrderId(String loanOrderId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("from com.bpms.model.LoanorderAndAccountinfo t where t.loanOrderId = '"
					+ loanOrderId + "'");
			return baseDAO.find(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Long findCountByLoanOrderId(String loanOrderId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("select count(*) from com.bpms.model.LoanorderAndAccountinfo t where t.loanOrderId = '"
					+ loanOrderId + "'");
			return baseDAO.count(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteByLoanOrderId(String loanOrderId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("delete from com.bpms.model.LoanorderAndAccountinfo t where 1=1");
			hql.append(" and t.loanOrderId = '" + loanOrderId + "'");
			baseDAO.executeHql(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
