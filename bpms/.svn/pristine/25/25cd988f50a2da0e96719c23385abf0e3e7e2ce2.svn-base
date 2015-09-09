package com.bpms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Address;
import com.bpms.model.Investor;
import com.bpms.service.AddressService;
import com.bpms.service.InvestOrderService;
import com.bpms.service.InvestorService;
import com.bpms.util.Constants;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

/**
 * 投资人的serviceImpl
 * 
 * @author liuhh
 *
 */
@Service
public class InvestorServiceImpl implements InvestorService {
	@Autowired
	private BaseDAO<Investor> investorDao;
	@Autowired
	private AddressService addressService;
	@Autowired
	private InvestOrderService InvestOrderServiceImpl;
	@Autowired
	private AddressService AddressServiceImpl;

	@Override
	public boolean persistenceInvestor(Investor investor) {
		Integer provinceId = investor.getProvinceId();
		Integer cityId = investor.getCityId();
		Integer areaId = investor.getAreaId();
		String addrDetails = investor.getAddressDetails();
		if (StringUtils.isBlank(investor.getInvestorId())) {
			investor.setInvestorId(null);
			investor.setInvestorStatus("A");
			investor.setCreateDate(new Date());
			investor.setCreator(Constants.getCurrendUser().getUserId());
			getAddress(investor, provinceId, cityId, areaId, addrDetails);
			investorDao.save(investor);
		} else {
			investor.setUpdateDate(new Date());
			investor.setUpdator(Constants.getCurrendUser().getUserId());
			Address address= addressService.findById(investor.getCommAddr());
			if(address!=null){
				if((address.getProvinceId().toString()).equals(provinceId)&&(address.getCityId().toString()).equals(cityId)
						&&(address.getAreaId().toString()).equals(areaId)&&address.getAddrDetails().equals(addrDetails)){					
					investorDao.update(investor);
				}else{
					getAddress(investor, provinceId, cityId, areaId,
							addrDetails);
					investorDao.update(investor);
				}
			}else{
				//判断地址是否为空
				getAddress(investor, provinceId, cityId, areaId, addrDetails);
				investorDao.update(investor);
			}
		}
		return true;
	}

	/**
	 * 
	 * @author: xujianwei
	 * @time:2015年7月16日 下午5:47:30
	 * @Title:getAddress
	 * @Description:TODO 获取通讯地址（这里描述这个方法的作用）
	 * @param investor
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @param addrDetails
	 * @throws:
	 */
	private void getAddress(Investor investor, Integer provinceId,
			Integer cityId, Integer areaId, String addrDetails) {
		if(!(provinceId==null && cityId == null && areaId==null)){
		Address address = new Address();
		address.setProvinceId(provinceId);
		address.setCityId(cityId);
		address.setAreaId(areaId);
		address.setAddrDetails(addrDetails);
		address.setAddrType("A");
		address.setHostType("A");
		address.setHostId(investor.getInvestorId());
		addressService.saveAddress(address);
		investor.setCommAddr(address.getAddrId());
		}
	}
	
	@Override
	public List<Investor> findInvestorList(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "from Investor t where 1=1 and t.investorStatus='A' ";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		hql += " order by t.createDate desc";
		List<Investor> list = investorDao.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
		return list;
	}

	@Override
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from Investor t where 1=1 and t.investorStatus='A' ";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		hql += " order by t.createDate desc";
		return investorDao.count(hql, map);
	}
	
	@Override
	public boolean delInvestor(String ids){
		boolean flag = false;
		if (StringUtils.isNotBlank(ids)) {
			String[] idsArray = ids.split(",");
			for (int i = 0; i < idsArray.length; i++) {
				String id = String.valueOf((idsArray[i]));
				Investor investor = investorDao.get(Investor.class, id);
				investor.setInvestorStatus("I");
				investorDao.deleteToUpdate(investor);
			}
			flag = true;
		}
		return flag;
	}

	@Override
	public Investor findInvestorByInvestorId(String InvestorId) {
		Investor investor = investorDao.get(Investor.class, InvestorId);
		if(investor!=null){
			String addressId = investor.getCommAddr();//地址id
			Address address = AddressServiceImpl.findById(addressId);
			if(address!=null){
				investor.setProvinceId(address.getProvinceId()==null?0:address.getProvinceId());
				investor.setCityId(address.getCityId()==null?0:address.getCityId());
				investor.setAreaId(address.getAreaId()==null?0:address.getAreaId());
				investor.setAddressDetails(address.getAddrDetails()==null?"":address.getAddrDetails());
			}
		}
		return investor;
	}
	

}
