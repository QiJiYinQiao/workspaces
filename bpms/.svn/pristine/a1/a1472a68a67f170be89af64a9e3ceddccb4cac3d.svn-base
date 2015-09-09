package com.bpms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.SysParameter;
import com.bpms.service.SysParameterService;

@Service
public class SysParameterServiceImpl implements SysParameterService {
	
	@Autowired
	BaseDAO<SysParameter> baseDao;

	@Override
	public SysParameter findSysParameter(String parmCode) {
		String hql = " FROM SysParameter s WHERE s.parmCode = '"+parmCode+"'";
		return baseDao.find(hql).get(0);
	}

}
