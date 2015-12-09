package com.oasys.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oasys.dao.PublicDao;
import com.oasys.model.AuditProcHis;
import com.oasys.service.AuditPorcService;

/**
 * 
 * @author Guo
 *
 */
@Service("auditPorcService")
public class AuditPorcServiceImpl extends WorkFlowBaseServiceImpl implements AuditPorcService {
	@Autowired
	private PublicDao<AuditProcHis> publicdao;
	
	/**
	 * 查看审批意见
	 */
	@Override
	public List<AuditProcHis> getAuditPorcList(String appNo) {
		List<AuditProcHis> list = publicdao.find("from AuditProcHis where appNo='"+appNo+"'");
		return list;
	}
	

	@Override
	public boolean getApplyStatus(String appNo) {
		boolean flag= false;
		String applyStatus=publicdao.find("select handleResult from AuditProcHis where appNo='"+appNo+"'").get(0).getHandleResult();
		if("0".equals(applyStatus)){
			flag=false;
		}else if("1".equals(applyStatus)){
			flag=true;
		}
		return flag;
	}

}
