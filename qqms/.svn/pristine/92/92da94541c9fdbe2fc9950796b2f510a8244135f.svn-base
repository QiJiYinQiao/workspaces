package com.qqms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqms.dao.PublicDao;
import com.qqms.model.Emccontact;
import com.qqms.service.EmccontactService;
/**
 * 财富客户紧急联系人serviceImpl
 * @author panchuanhe
 * @dateTime 2015/6/2
 */
@Service("emccontactServiceImpl")
public class EmccontactServiceImpl extends BaseServiceImpl implements EmccontactService {
	@Autowired
	private PublicDao<Emccontact> publicDao;
	
	/**
	 * 查询紧急联系人列表
	 */
	@Override
	public List<Emccontact> findAllList(Emccontact e) {
		StringBuffer hql = new StringBuffer();
		hql.append("from com.qqms.model.Emccontact t where 1=1 and t.contactStatus = 'A'");
		hql.append(" and t.contactId = '"+e.getContactId()+"'");//客户主键
		hql.append(" and t.cusType = 'investor'");
		List<Emccontact> list = publicDao.find(hql.toString());
		return list;
	}

	@Override
	public Long getCount(Emccontact e) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from com.qqms.model.Emccontact t where 1=1 and t.contactStatus = 'A'");
		hql.append(" and t.contactId = '"+e.getContactId()+"'");
		return publicDao.count(hql.toString());
	}
}
