package com.qqms.serviceImpl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqms.dao.PublicDao;
import com.qqms.model.Investor;
import com.qqms.service.InvestorService;
import com.qqms.util.PageUtil;

/**
 * 财富客户信息service
 * @author panchuanhe
 * @data 2015/5/22
 */
@Service("investorService")
public class InvestorServiceImpl extends BaseServiceImpl implements InvestorService{

	private PublicDao<Investor> publicDao;
	
	@SuppressWarnings("rawtypes")
	@Autowired
	public void setPublicDao(PublicDao publicDao){
		this.publicDao = publicDao;
	}
	/**
	 * 查询列表
	 */
	@Override
	public List<Investor> findList(String whereHql,PageUtil pageUtil) {
		StringBuffer hql = new StringBuffer();
		hql.append(" from com.qqms.model.Investor t where 1=1 and t.invstrStatusType = 'A'");
		if(StringUtils.isNotBlank(whereHql)){
			hql.append(whereHql);
		}
		List<Investor> list = publicDao.find(hql.toString(),pageUtil);
		return list;
	}
	/**
	 * 查询总条数
	 */
	@Override
	public Long getCount(String whereHql) {
		StringBuffer hql = new StringBuffer();
		hql.append("select count(*) from com.qqms.model.Investor t where 1=1 and t.invstrStatusType = 'A'");
		if(StringUtils.isNotBlank(whereHql)){
			hql.append(whereHql);
		}
		return publicDao.count(hql.toString());
	}
}
