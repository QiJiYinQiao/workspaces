package com.bpms.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Address;
import com.bpms.service.AddressService;

/**
 * 地址ServiceImpl
 * 
 * @author panchuanhe 2015/6/26
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

	@Autowired
	public BaseDAO<Address> baseDAO;

	@Override
	public boolean saveAddress(Address address) {
		try {
			if (StringUtils.isBlank(address.getAddrId())) {
				baseDAO.save(address);
			} else {
				baseDAO.update(address);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Address findById(String id) {
		try {
			Address address = null;
			if (StringUtils.isNotBlank(id)) {
				address = baseDAO.get(Address.class, id);
			}
			return address;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String addressName(String id) {
		Address address = baseDAO.get(Address.class, id);
		String sql = "SELECT t.AREA_NAME name FROM t_area t WHERE t.AREA_ID = '"+address.getProvinceId()+"'";
		String province = baseDAO.findBySQL(sql).get(0).toString();
		sql = "SELECT t.AREA_NAME name FROM t_area t WHERE t.AREA_ID = '"+address.getCityId()+"'";
		String city = baseDAO.findBySQL(sql).get(0).toString();
		sql = "SELECT t.AREA_NAME name FROM t_area t WHERE t.AREA_ID = '"+address.getAreaId()+"'";
		String area = baseDAO.findBySQL(sql).get(0).toString();
		String detail = address.getAddrDetails();
		
		return province+city+area+detail;
	}

}
