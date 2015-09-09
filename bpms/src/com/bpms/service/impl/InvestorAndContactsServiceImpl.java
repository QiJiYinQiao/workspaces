package com.bpms.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.InvestorAndContacts;
import com.bpms.service.InvestorAndContactsService;

/**
 * 投资人和联系人关联servie
 * 
 * @author liuhh
 *
 */
@Service
public class InvestorAndContactsServiceImpl implements
		InvestorAndContactsService {

	@Autowired
	private BaseDAO<InvestorAndContacts> investorAndContactsDao;

	@Override
	public boolean persistenceInvestorAndContacts(
			InvestorAndContacts investorAndContacts) {
		if (StringUtils.isBlank(investorAndContacts.getId())) {
			investorAndContactsDao.save(investorAndContacts);
		} else {
			investorAndContactsDao.update(investorAndContacts);
		}
		return true;
	}

	@Override
	public boolean deleteByContactId(String ContactId) {
		try {
			StringBuffer hql = new StringBuffer("delete from com.bpms.model.InvestorAndContacts t where 1=1 and t.contactId = '"+ContactId+"'");
			investorAndContactsDao.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteByInvestorId(String InvestorId) {
		try {
			StringBuffer hql = new StringBuffer("delete from com.bpms.model.InvestorAndContacts t where 1=1 and t.investorId = '"+InvestorId+"'");
			investorAndContactsDao.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
