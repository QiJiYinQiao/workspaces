package com.bpms.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.CarInfo;
import com.bpms.service.CarInfoService;

@Service("carInfoService")
public class CarInfoServiceImpl implements CarInfoService {

	@Autowired
	private BaseDAO<CarInfo> baseDao;
	
	@Override
	public CarInfo findCarInfoByOid(String loanOrderId) {
		String hql = " FROM CarInfo ci WHERE ci.loanOrderId = '"+loanOrderId+"'";
		List<CarInfo> list = baseDao.find(hql);
		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
		
	}

	@Override
	public boolean persistenceCarInfo(CarInfo ci) {
		if (StringUtils.isBlank(ci.getCarinfoId())) {
			baseDao.save(ci);
		} else {
			baseDao.update(ci);
		}
		return true;
	}

}
