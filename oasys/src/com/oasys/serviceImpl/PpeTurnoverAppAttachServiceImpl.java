package com.oasys.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.PpeScrapAppAttach;
import com.oasys.model.PpeTurnoverApp;
import com.oasys.model.PpeTurnoverAttach;
import com.oasys.service.PpeTurnoverAppAttachService;
import com.oasys.service.PpeTurnoverAppService;
import com.oasys.util.Collections;

/**   
 * @Title: PpeTurnoverAppAttachServiceImpl.java 
 * @Package com.oasys.serviceImpl 
 * @Description: TODO
 * @author PANCHUANHE  
 * @date 2015年9月24日 上午11:14:51 
 * @version V1.0   
 */
@Service
public class PpeTurnoverAppAttachServiceImpl implements PpeTurnoverAppAttachService{
	
	@Autowired
	private PublicDao<PpeTurnoverAttach> publicDao;
	@Autowired
	private PpeTurnoverAppService ppeTurnoverAppService;
	@Override
	public boolean savePurchaseAppAttach(PpeTurnoverAttach ppeTurnoverAttach) {
		// TODO Auto-generated method stub
		try {
			
			if(ppeTurnoverAttach.getPtaId()==null){
				publicDao.save(ppeTurnoverAttach);
			}else{
				publicDao.update(ppeTurnoverAttach);
			}
			totalCount(ppeTurnoverAttach.getAppNo());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public boolean doDeleteById(PpeTurnoverAttach turnoverAttach) {
		// TODO Auto-generated method stub
		try {
			StringBuffer hql = new StringBuffer("delete from PpeTurnoverAttach p where p.ptaId = "+turnoverAttach.getPtaId());
			publicDao.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<PpeTurnoverAttach> findListByAppNo(String appNo) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("from PpeTurnoverAttach p where p.appNo = '"+appNo+"'");
		
		//查询出列表
		List<PpeTurnoverAttach> list = publicDao.find(hql.toString());
		return list;
	}
	@Override
	public boolean doDeleteByAppNo(PpeTurnoverAttach turnoverAttach) {
		// TODO Auto-generated method stub
		try {
			StringBuffer hql = new StringBuffer("delete from PpeTurnoverAttach p where p.appNo = '"+turnoverAttach.getAppNo()+"'");
			publicDao.executeHql(hql.toString());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<PpeTurnoverAttach> findPpeAttachList(String appNo) {
		String hql = "from PpeTurnoverAttach where appNo = '"+appNo+"'";
		List<PpeTurnoverAttach> list = publicDao.find(hql);
		return list;
	}
	@Override
	public void totalCount(String appNo) {
		// TODO Auto-generated method stub
		PpeTurnoverApp ppeTurnoverApp = ppeTurnoverAppService.findPpeTurnoverAppByAppNo(appNo);
		List<PpeTurnoverAttach> attachs = findListByAppNo(appNo);
		int sum=0;
		BigDecimal ppeTotalAmt = new BigDecimal(0.00);
		for (PpeTurnoverAttach ppeTurnoverAttach2 : attachs) {
			BigDecimal qty = new BigDecimal(ppeTurnoverAttach2.getQty());
			BigDecimal ppeAmt = ppeTurnoverAttach2.getPpeAmt();
			BigDecimal total = qty.multiply(ppeAmt);
			ppeTotalAmt=ppeTotalAmt.add(total);
			sum+=ppeTurnoverAttach2.getQty();
		}
		ppeTurnoverApp.setPpeQty(sum);
		ppeTurnoverApp.setPpeTotalAmt(ppeTotalAmt);
		ppeTurnoverAppService.savePpeTurnoverApp(ppeTurnoverApp);
	}
	@Override
	public PpeTurnoverAttach findPpeAttachList(String appNo, String ppeCode) {
		// TODO Auto-generated method stub
		String hql = "from PpeTurnoverAttach where appNo = '"+appNo+"'"+" and ppeNo ='"+ppeCode+"'";
		List<PpeTurnoverAttach> list = publicDao.find(hql);
		if (Collections.listIsNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}
}


