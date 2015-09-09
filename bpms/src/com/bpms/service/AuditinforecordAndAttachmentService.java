package com.bpms.service;

import com.bpms.model.AuditinforecordAndAttachment;

/**
 * 稽核信息与附件关系Service
 * @author PANCHUANHE
 * @date 2016/6/16
 */
public interface AuditinforecordAndAttachmentService {
    /**
     * 新增
     * @param a
     * @return
     */
	public boolean saveAuditinforecordAndAttachment(AuditinforecordAndAttachment a);
}
