package com.oasys.serviceImpl;

import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.BusinessTripAttach;
import com.oasys.service.BusinessTripAppService;
import com.oasys.service.BusinessTripAttachService;
import com.oasys.serviceImpl.workFlow.WorkFlowBaseServiceImpl;
@Service(value="businessTripAttachService")
public class BusinessTripAttachServiceImpl extends WorkFlowBaseServiceImpl implements BusinessTripAttachService {
	@Autowired
	private PublicDao<BusinessTripAttach> publicDao;
	@Autowired
	private BusinessTripAppService businessTripAppService;
	@Override
	public void saveOrUpdateBusinessTripAttach(BusinessTripAttach businessTripAttach){
		publicDao.saveOrUpdate(businessTripAttach);
	}
	@Override
	public boolean saveBusinessTripAttach(BusinessTripAttach businessTripAttach) {
		// TODO Auto-generated method stub
		BusinessTripAttach businessTripAttach2 = publicDao.get(BusinessTripAttach.class, businessTripAttach.getBtaId());
		businessTripAttach2.setRealBgDtime(businessTripAttach.getRealBgDtime());
		businessTripAttach2.setRealEdDtime(businessTripAttach.getRealEdDtime());
		String beginTime = DateUtil.formatDate(businessTripAttach.getRealBgDtime(), "yyyy-MM-dd HH:mm:ss");
		String endTime = DateUtil.formatDate(businessTripAttach.getRealEdDtime(), "yyyy-MM-dd HH:mm:ss");
		businessTripAttach2.setRealBtDays(businessTripAppService.sumDays(endTime, beginTime));
		publicDao.saveOrUpdate(businessTripAttach2);
		return false;
	}
	@Override
	public BusinessTripAttach findBusinessTripAttachByAppNo(String appNo) {
		// TODO Auto-generated method stub
		List<BusinessTripAttach> list = publicDao.find("from BusinessTripAttach where 1=1 and appNo='"+appNo+"'");
		BusinessTripAttach businessTripAttach = list.get(0);
		return businessTripAttach;
	}
}
