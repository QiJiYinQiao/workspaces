package com.bpms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Address;
import com.bpms.model.LoanerJoint;
import com.bpms.model.vo.LoanerJointModel;
import com.bpms.service.AddressService;
import com.bpms.service.LoanerJointService;
import com.bpms.util.Collections;

@Service("loanerJointService")
public class LoanerJointServiceImpl implements LoanerJointService {

	@Autowired
	private BaseDAO<LoanerJoint> loanerJointDao;

	@Autowired
	private AddressService addressService;

	@Override
	public void persistenceLoanerJoint(LoanerJointModel loanerJointModel) {
		LoanerJoint loanerJoint = new LoanerJoint();
		BeanUtils.copyProperties(loanerJointModel, loanerJoint);
		if (StringUtils.isBlank(loanerJoint.getLjId())) {
			loanerJointDao.save(loanerJoint);
		} else {
			loanerJointDao.update(loanerJoint);
		}

		// 获取共同贷款人各个地址保存到数据库中
		// 户籍地址
		Address residenceAddress = new Address();
		residenceAddress.setAddrId(loanerJointModel.getResidenceAddressId());
		residenceAddress.setAddrDetails(loanerJointModel.getResidenceAddrDetails());
		residenceAddress.setAddrType(StringUtils.isBlank(loanerJointModel.getResidenceAddressType())?"1":loanerJointModel.getResidenceAddressType());
		residenceAddress.setAreaId(loanerJointModel.getResidenceAreaId());
		residenceAddress.setCityId(loanerJointModel.getResidenceCityId());
		residenceAddress.setHostId(loanerJoint.getLjId());
		residenceAddress.setProvinceId(loanerJointModel.getResidenceProvinceId());
		residenceAddress.setHostType("5");
		addressService.saveAddress(residenceAddress);

		// 现住地址
		Address curAddress = new Address();
		curAddress.setAddrId(loanerJointModel.getCurAddressId());
		curAddress.setAddrDetails(loanerJointModel.getCurAddrDetails());
		curAddress.setAddrType(StringUtils.isBlank(loanerJointModel.getCurAddressType())?"2":loanerJointModel.getCurAddressType());
		curAddress.setAreaId(loanerJointModel.getCurAreaId());
		curAddress.setCityId(loanerJointModel.getCurCityId());
		curAddress.setHostId(loanerJoint.getLjId());
		curAddress.setProvinceId(loanerJointModel.getCurProvinceId());
		curAddress.setHostType("5");
		addressService.saveAddress(curAddress);

		// 公司单位地址
		Address corpAddress = new Address();
		corpAddress.setAddrId(loanerJointModel.getCorpAddressId());
		corpAddress.setAddrDetails(loanerJointModel.getCorpAddrDetails());
		corpAddress.setAddrType(StringUtils.isBlank(loanerJointModel.getCorpAddressType())?"3":loanerJointModel.getCorpAddressType());
		corpAddress.setAreaId(loanerJointModel.getCorpAreaId());
		corpAddress.setCityId(loanerJointModel.getCorpCityId());
		corpAddress.setHostId(loanerJoint.getLjId());
		corpAddress.setProvinceId(loanerJointModel.getCorpProvinceId());
		corpAddress.setHostType("5");
		addressService.saveAddress(corpAddress);
		
		// 设置增加成功的地址ID
		loanerJoint.setResidence(residenceAddress.getAddrId());
		loanerJoint.setCurAddr(curAddress.getAddrId());
		loanerJoint.setCorpAddr(corpAddress.getAddrId());
		
		// 返回修改后的ID
		loanerJointModel.setLjId(loanerJoint.getLjId());

	}

	@Override
	public LoanerJointModel findLoanerJointByOrderId(final String orderId) {
		String hql = "from LoanerJoint o where o.loanOrderId=:loanOrderId";
		List<LoanerJoint> list = loanerJointDao.find(hql, new  HashMap<String, Object>(){
			{
				put("loanOrderId",orderId);
			}
		});
		if(Collections.listIsNotEmpty(list)){
			LoanerJointModel jointModel = new LoanerJointModel();
			BeanUtils.copyProperties(list.get(0), jointModel);
			
			Address residenceAddress = addressService.findById(jointModel.getResidence());
			if(null != residenceAddress){
				jointModel.setResidenceAddrDetails(residenceAddress.getAddrDetails());
				jointModel.setResidenceAddressId(residenceAddress.getAddrId());
				jointModel.setResidenceAddressType(residenceAddress.getAddrType());
				jointModel.setResidenceAreaId(residenceAddress.getAreaId());
				jointModel.setResidenceCityId(residenceAddress.getCityId());
				jointModel.setResidenceProvinceId(residenceAddress.getProvinceId());
			}
			
			Address curAddress = addressService.findById(jointModel.getCurAddr());
			if(null != curAddress){
				jointModel.setCurAddrDetails(curAddress.getAddrDetails());
				jointModel.setCurAddressId(curAddress.getAddrId());
				jointModel.setCurAddressType(curAddress.getAddrType());
				jointModel.setCurAreaId(curAddress.getAreaId());
				jointModel.setCurCityId(curAddress.getCityId());
				jointModel.setCurProvinceId(curAddress.getProvinceId());
			}

			Address corpAddress = addressService.findById(jointModel.getCorpAddr());
			if(null != corpAddress){
				jointModel.setCorpAddrDetails(corpAddress.getAddrDetails());
				jointModel.setCorpAddressId(corpAddress.getAddrId());
				jointModel.setCorpAddressType(corpAddress.getAddrType());
				jointModel.setCorpAreaId(corpAddress.getAreaId());
				jointModel.setCorpCityId(corpAddress.getCityId());
				jointModel.setCorpProvinceId(corpAddress.getProvinceId());
			}
			return jointModel;
		}
		return null;
	}

}
