package com.bpms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.LoanorderAndCompany;
import com.bpms.service.LoanorderAndCompanyService;

/**
 * bpms 紧急联系人与订单servicImpl
 * 
 * @author panchuanhe
 * @date 2015/6/16
 */
@Service("loanorderAndCompanyService")
public class LoanorderAndCompanyServiceImpl implements
		LoanorderAndCompanyService {
	@Autowired
	private BaseDAO<LoanorderAndCompany> baseDAO;

	@Override
	public boolean saveLoanorderAndCompany(LoanorderAndCompany la) {
		try {
			baseDAO.save(la);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteByComIdAndLoanOrderId(String ComId, String LoanOrderId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("delete from com.bpms.model.LoanorderAndCompany t where t.comId = '"
					+ ComId + "' and t.loanOrderId = '" + LoanOrderId + "'");
			baseDAO.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<LoanorderAndCompany> findListByComId(String ComId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("from com.bpms.model.LoanorderAndCompany t where t.comId = '"
					+ ComId + "'");
			return baseDAO.find(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LoanorderAndCompany> findListByLoanOrderId(String loanOrderId) {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append("from com.bpms.model.LoanorderAndCompany t where t.loanOrderId = '"
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
			hql.append("select count(*) from com.bpms.model.LoanorderAndCompany t where t.loanOrderId = '"
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
			hql.append("delete from com.bpms.model.LoanorderAndCompany t where t.loanOrderId = '"
					+ loanOrderId + "'");
			baseDAO.executeHql(hql.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
