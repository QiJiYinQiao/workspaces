package com.bpms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.AuditinforecordAndAttachment;
import com.bpms.service.AuditinforecordAndAttachmentService;
/**
 * 稽核信息与附件关系ServiceImpl
 * @author PANCHUANHE
 * @date 2016/6/16
 */
@Service("auditinforecordAndAttachmentService")
public class AuditinforecordAndAttachmentServiceImpl implements
		AuditinforecordAndAttachmentService {
	
	@Autowired
	private BaseDAO<AuditinforecordAndAttachment> baseDAO;

	@Override
	public boolean saveAuditinforecordAndAttachment(
			AuditinforecordAndAttachment auditinforecordAndAttachment) {
		
		if(auditinforecordAndAttachment!=null){
			baseDAO.save(auditinforecordAndAttachment);
			return true;
		}
		return false;
	}

}
