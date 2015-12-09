package com.oasys.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.StatusNameRef;
import com.oasys.service.StatusNameRefService;
import com.oasys.util.Collections;
import com.oasys.util.HqlUtil;
import com.oasys.util.PageUtil;

/**
 * 
 * 订单状态的service接口实现类
 * @author LiDa
 * @date 2015/9/21
 * 
 * */

@Service("statusNameRefService")
public class StatsuNameRefServiceImpl implements StatusNameRefService{


	@Autowired
	private PublicDao<StatusNameRef> publicDao;

	@Override
	public List<StatusNameRef> findstatusNameRefAllList(Map<String, Object> map,
			PageUtil pageUtil) {
		String hql = "from StatusNameRef t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return publicDao.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from StatusNameRef t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return publicDao.count(hql, map);
	}

	@Override
	public boolean persistenceStatusNameRef(StatusNameRef statusNameRef) {
		// TODO Auto-generated method stub
		publicDao.saveOrUpdate(statusNameRef);
		return true;
	}

	@Override
	public boolean deleteStatusNameRef(String id) {
		publicDao.delete(publicDao.get(StatusNameRef.class, id));
		return true;
	}

	@Override
	public StatusNameRef getstatusNameRefByStatusCode(String statusCode) {
		// TODO Auto-generated method stub
		List<StatusNameRef> list = publicDao.find("from StatusNameRef o where o.appStatusCode ='" + statusCode+ "'");
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateLoanOrderProcessStatus(String id, String processStatus) {
		// TODO Auto-generated method stub
		StatusNameRef statusNameRef = this.publicDao.get(StatusNameRef.class, id);
		statusNameRef.setAppStatusCode(StatusNameRef.class.getSimpleName()
						+ "_" + processStatus);
		publicDao.saveOrUpdate(statusNameRef);
	}

	@Override
	public StatusNameRef getStatusNameRefByStatusID(String statusID) {
		// TODO Auto-generated method stub
		List<StatusNameRef> statusList = publicDao.find("from StatusNameRef where refId="+statusID);
		StatusNameRef statusNameRef = new StatusNameRef();
		if(null != statusList && statusList.size() > 0){
			statusNameRef = statusList.get(0);
		}
		return statusNameRef;
	}
}
