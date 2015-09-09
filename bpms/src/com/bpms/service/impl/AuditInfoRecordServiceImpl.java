package com.bpms.service.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.AuditInfoRecord;
import com.bpms.service.AuditInfoRecordService;
/**
 * 稽核信息记录ServiceImpl
 * @author PANCHUANHE
 * @date 2016/6/16
 */
@Service("auditInfoRecordService")
public class AuditInfoRecordServiceImpl implements AuditInfoRecordService {
	
	@Autowired
	private BaseDAO<AuditInfoRecord> baseDAO;

	@Override
	public boolean saveAuditInfoRecord(AuditInfoRecord auditInfoRecord) {
		if (StringUtils.isBlank(auditInfoRecord.getAuditId())) {
			baseDAO.save(auditInfoRecord);
		} else {
			baseDAO.update(auditInfoRecord);
		}
		return true;
	}

	@Override
	public List<AuditInfoRecord> findAIRByOid(String oid) {
		String hql = "from AuditInfoRecord o where o.loanOrder.loanOrderId='"+oid+"'";
		if(null != baseDAO.find(hql))
			return baseDAO.find(hql);
		else 
			return null;
	}

	@Override
	public AuditInfoRecord findAuditByName(String name,
			String loanOrderId,String auditItem) {
		String hql = "from AuditInfoRecord o where o.loanOrder.loanOrderId='"+loanOrderId+"' AND o.name = '"+name+"' AND o.auditItem = '"+auditItem+"'";
		List<AuditInfoRecord> list = baseDAO.find(hql);
		if(list.size()>0)
			return list.get(0);
		else 
			return null;
	}

	@Override
	public boolean deleteAudit(AuditInfoRecord air) {
		baseDAO.delete(air);
		return true;
	}

}
