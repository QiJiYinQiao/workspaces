package com.oasys.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.ConsumablesApp;
import com.oasys.model.ConsumablesAppAttach;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.PpeTurnoverAttach;
import com.oasys.service.ConsumablesAppAttachService;
import com.oasys.service.ConsumablesAppService;
/**
 * 
 * @Title: ConsumablesAppAttachServiceImpl.java 
 * @Package com.oasys.serviceImpl 
 * @Description: TODO
 * @author yuanzhongqiu  
 * @date 2015年10月12日 上午9:45:08 
 * @version V1.0
 */
@Service(value="consumablesAppAttachService")
public class ConsumablesAppAttachServiceImpl implements
		ConsumablesAppAttachService {
	
	@Autowired
	public PublicDao<ConsumablesAppAttach> publicDao;
	@Autowired
	private ConsumablesAppService consumablesAppService;
	@Override
	public List<ConsumablesAppAttach> findConsumablesAppAttachList(String appNo) {
		String hql = "from ConsumablesAppAttach where appNo = '"+appNo+"'";
		List<ConsumablesAppAttach> list = publicDao.find(hql);
		return list;
	}
	@Override
	public void saveOrUpdateConsumablesAppAttach(
			ConsumablesAppAttach consumablesAppAttach) {
		// TODO Auto-generated method stub
		BigDecimal qty = new BigDecimal(consumablesAppAttach.getQty());
		BigDecimal price = consumablesAppAttach.getPrice();
		consumablesAppAttach.setTotalAmt(qty.multiply(price));
		publicDao.saveOrUpdate(consumablesAppAttach);
		totalCount(consumablesAppAttach.getAppNo());
	}
	private void totalCount(String appNo) {
		// TODO Auto-generated method stub
		ConsumablesApp consumablesApp= consumablesAppService.getConsumablesAppByAppNo(appNo);
		List<ConsumablesAppAttach> attachs = findConsumablesAppAttachList(appNo);
		BigDecimal allAmt = new BigDecimal(0.00);
		for (ConsumablesAppAttach consumablesAppAttach : attachs) {
			BigDecimal totalAmt = consumablesAppAttach.getTotalAmt();
			allAmt=allAmt.add(totalAmt);
		}
		consumablesApp.setAllAmt(allAmt);
		consumablesAppService.addOrUpdateConsumablesEntity(consumablesApp);
	}
	@Override
	public ConsumablesAppAttach getConsumablesAppAttachByID(Integer caaId) {
		// TODO Auto-generated method stub
		return publicDao.get(ConsumablesAppAttach.class, caaId);
	}
	@Override
	public Integer delConsumablesAppAttach(String ids,String appNo) {
		// TODO Auto-generated method stub
		Integer executeHql = publicDao.executeHql("delete from ConsumablesAppAttach where caaId in ("+ids+")");
		totalCount(appNo);
		return executeHql;
	}


}
