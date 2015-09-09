package com.bpms.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.AccountsJournal;
import com.bpms.service.AccountsJournalService;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service
public class AccountsJournalServiceImpl implements AccountsJournalService {

	@Autowired
	private BaseDAO<AccountsJournal> baseDAO;

	@Override
	public boolean persistenceAccountsJournal(AccountsJournal accountsJournal) {
		if (StringUtils.isBlank(accountsJournal.getBankFlowId())) {
			baseDAO.save(accountsJournal);
		} else {
			baseDAO.update(accountsJournal);
		}
		return true;
	}

	@Override
	public boolean deleteAccountsJournal(String id) {
		String hql = "delete from AccountsJournal o where o.bankFlowId ='" + id
				+ "'";
		return baseDAO.executeHql(hql) > 0 ? true : false;
	}

	@Override
	public AccountsJournal findAccountsJournalById(String id) {
		/*
		 * List<AccountsJournal> list = baseDAO
		 * .find("from AccountsJournal o where o.bankFlowId='" + id + "'"); if
		 * (Collections.listIsNotEmpty(list)) { return list.get(0); } else {
		 * return null; }
		 */
		return baseDAO.get(AccountsJournal.class, id);
	}

	@Override
	public List<AccountsJournal> findAccountsJournal(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "from AccountsJournal t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countAccountsJournal(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from AccountsJournal t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

}
