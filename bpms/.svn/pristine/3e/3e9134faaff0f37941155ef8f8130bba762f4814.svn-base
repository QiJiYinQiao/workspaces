package com.bpms.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.OutboundVerify;
import com.bpms.service.OutboundVerifyService;

/**
 * 外访问题核实的实现类
 * 
 * @author liuhh
 *
 */
@Service
public class OutboundVerifyServiceImpl implements OutboundVerifyService {

	/* 注入dao */
	@Autowired
	private BaseDAO<OutboundVerify> dao;

	@Override
	public boolean saveOutboundVerify(OutboundVerify outboundVerify) {
		if (StringUtils.isNotBlank(outboundVerify.getVerifyId())) {
			dao.update(outboundVerify);
		} else {
			dao.save(outboundVerify);
		}
		return true;
	}

	@Override
	public OutboundVerify findById(String id) {
		return dao.get(OutboundVerify.class, id);
	}

}
